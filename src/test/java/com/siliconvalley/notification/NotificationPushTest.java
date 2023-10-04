package com.siliconvalley.notification;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.notification.application.NotificationPushService;
import com.siliconvalley.domain.notification.dao.NotificationRepository;
import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
import com.siliconvalley.domain.post.dto.PostRankingDto;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.sse.application.SseEmitterFinder;
import com.siliconvalley.domain.sse.application.SseEmitterSender;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class NotificationPushTest {

    NotificationPushService notificationPushService;

    @Mock
    ProfileFindDao profileFindDao;

    @Mock
    SseEmitterFinder sseEmitterFinder;

    @Mock
    SseEmitterSender sseEmitterSender;

    @Mock
    EventCashRepository eventCashRepository;

    @Mock
    NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationPushService = new NotificationPushService(
                profileFindDao,
                sseEmitterFinder,
                sseEmitterSender,
                eventCashRepository,
                notificationRepository
        );
    }

    @Test
    @DisplayName("Item 알림 전송 테스트")
    void pushNotification_Item_Success() {
        // Given
        Item item = new Item(100L);
        Pix2Pix pix2Pix = mock(Pix2Pix.class);
        Subject subject = new Subject("test", "test", item, pix2Pix);
        item.addSubject(subject);

        NotificationType type = NotificationType.NEW_SUBJECT;

        Long profileId1 = 1L;
        Long profileId2 = 2L;

        Profile profile1 = mock(Profile.class);
        Profile profile2 = mock(Profile.class);

        Map<Long, SseEmitter> sseEmitterMap = new HashMap<>();
        sseEmitterMap.put(profileId1, new SseEmitter());
        sseEmitterMap.put(profileId2, new SseEmitter());

        when(profileFindDao.findOptionalById(profileId1)).thenReturn(Optional.of(profile1));
        when(profileFindDao.findOptionalById(profileId2)).thenReturn(Optional.of(profile2));
        when(sseEmitterFinder.findAll()).thenReturn(sseEmitterMap);

        // When
        notificationPushService.pushNotification(item, type);

        // Then
        verify(notificationRepository, times(2)).save(any(Notification.class));
        verify(sseEmitterSender, times(2)).send(any(SseEmitter.class), anyString(), any(NotificationResponse.class), anyLong(), anyString());
        verify(eventCashRepository, times(2)).save(anyString(), any(NotificationResponse.class));
    }
}
