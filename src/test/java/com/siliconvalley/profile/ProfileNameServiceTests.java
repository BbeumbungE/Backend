package com.siliconvalley.profile;

import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.exception.ProfileNameDuplicateException;
import com.siliconvalley.domain.profile.exception.ProfileNameIncludeBadWordException;
import com.siliconvalley.domain.profile.application.ProfileNameFilterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
@DisplayName("프로필 이름 생성 정책 테스트")
public class ProfileNameServiceTests {

    @InjectMocks
    private ProfileNameFilterService profileNameFilterService;

    @Mock
    private ProfileFindDao profileFindDao;

    @Test
    @DisplayName("정책에 위반되지 않는 프로필 이름 생성")
    void 유효한_프로필_이름_테스트() {
        //given
        String 유효한프로필이름 = "유효한이름";

        //when
        when(profileFindDao.existsByProfileName(유효한프로필이름)).thenReturn(false);

        //then
        assertDoesNotThrow(() -> profileNameFilterService.profileNameFilter(유효한프로필이름));
    }

    @Test
    @DisplayName("프로필 이름에 부적절한 단어 포함 시 예외 발생")
    void 부적절한_단어_테스트() {
        //given
        String 부적절한단어포함프로필이름 = "지랄";

        //then
        assertThrows(ProfileNameIncludeBadWordException.class, () -> profileNameFilterService.profileNameFilter(부적절한단어포함프로필이름));
    }

    @Test
    @DisplayName("이미 사용 중인 프로필 이름 제공 시 예외 발생")
    void 중복_프로필_이름_테스트() {
        //given
        String 중복된프로필이름 = "중복이름";

        //when
        when(profileFindDao.existsByProfileName(중복된프로필이름)).thenReturn(true);

        //then
        assertThrows(ProfileNameDuplicateException.class, () -> profileNameFilterService.profileNameFilter(중복된프로필이름));
    }
}
