package com.siliconvalley.domain.notification.domain;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private NotificationType type;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile receiver;

    @Builder
    private Notification(String content, NotificationType type, Profile receiver) {
        this.content = content;
        this.type = type;
        this.receiver = receiver;
    }

    public static Notification toAvatarItemNotification(Profile profile, Item item, NotificationType type) {
        return Notification.builder()
                .receiver(profile)
                .content(item.getAvatar().getAvatarName() + type.getMessage())
                .type(type)
                .build();
    }
    public static Notification toSubjectItemNotification(Profile profile, Item item, NotificationType type) {
        return Notification.builder()
                .receiver(profile)
                .content(item.getSubject().getSubjectName() + type.getMessage())
                .type(type)
                .build();
    }

    public static Notification toRankingNotification(Profile profile, NotificationType type) {
        return Notification.builder()
                .receiver(profile)
                .content(type.getMessage())
                .type(type)
                .build();
    }
}
