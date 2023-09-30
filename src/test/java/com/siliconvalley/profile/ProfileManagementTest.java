package com.siliconvalley.profile;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.profile.application.ProfileCreateService;
import com.siliconvalley.domain.profile.application.ProfileManagementService;
import com.siliconvalley.domain.profile.code.ProfileCode;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.dto.ProfileCreate;
import com.siliconvalley.domain.profile.dto.ProfileItemUpdate;
import com.siliconvalley.domain.profile.dto.ProfileNameUpdate;
import com.siliconvalley.domain.profile.exception.ProfileNameDuplicateException;
import com.siliconvalley.domain.profile.exception.ProfileNameIncludeBadWordException;
import com.siliconvalley.global.common.dto.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("프로필 생성 테스트")
public class ProfileManagementTest {

    @Autowired ProfileCreateService profileCreateService;
    @Autowired ProfileManagementService profileManagementService;
    @Autowired MemberFindDao memberFindDao;
    @Autowired ProfileRepository profileRepository;

    String memberId;
    Long profileId;
    ProfileCreate ProfileCreateDto;
    ProfileNameUpdate profileNameUpdateDto;


    @BeforeEach
    void setInit() {
        memberId = "1133c13b-4d65-4dbe-995b-06b104e4bcef";
        profileId = 4L;
    }

    @Test
    @DisplayName("프로필 생성 욕설 포함 - 예외 발생")
    void 욕설_포함_프로필_생성() {
        //Given
        ProfileCreateDto = mock(ProfileCreate.class);
        when(ProfileCreateDto.getProfileName()).thenReturn("지랄");

        // when & then
        Assertions.assertThrows(ProfileNameIncludeBadWordException.class, () -> {
            profileCreateService.createProfile(memberId, ProfileCreateDto);
        });
    }

    @Test
    @DisplayName("프로필 생성 이름 중복 - 예외 발생")
    void 중복_이름_프로필_생성() {
        //Given
        ProfileCreateDto = mock(ProfileCreate.class);
        when(ProfileCreateDto.getProfileName()).thenReturn("신태일");

        // when & then
        Assertions.assertThrows(ProfileNameDuplicateException.class, () -> {
            profileCreateService.createProfile(memberId, ProfileCreateDto);
        });
    }

    @Test
    @DisplayName("프로필 생성 - 성공")
    void 프로필_생성() {
        // Given
        ProfileCreateDto = new ProfileCreate("테스트3");

        // when
        Response response = profileCreateService.createProfile(memberId, ProfileCreateDto);

        // then
        Assertions.assertEquals(response.getStatus().getCode(), ProfileCode.CREATE_SUCCESS);
    }

    @Test
    @DisplayName("프로필 수정 이름 욕설 포함 - 예외 발생")
    void 욕설_포함_프로필_수정() {
        // Given
        profileNameUpdateDto = mock(ProfileNameUpdate.class);
        when(profileNameUpdateDto.getProfileName()).thenReturn("시발");

        // when & then
        Assertions.assertThrows(ProfileNameIncludeBadWordException.class, () -> {
            profileManagementService.updateProfileName(profileId, profileNameUpdateDto);
        });
    }

    @Test
    @DisplayName("프로필 수정 이름 중복 - 예외 발생")
    void 중복_이름_프로필_수정() {
        // Given
        profileNameUpdateDto = mock(ProfileNameUpdate.class);
        when(profileNameUpdateDto.getProfileName()).thenReturn("신태일");

        // when & then
        Assertions.assertThrows(ProfileNameDuplicateException.class, () -> {
            profileManagementService.updateProfileName(profileId, profileNameUpdateDto);
        });
    }

    @Test
    @DisplayName("프로필 수정 이름 - 성공")
    void 프로필_이름_수정() {
        // given
        profileNameUpdateDto = new ProfileNameUpdate("테스트4");

        // when
        Response response = profileManagementService.updateProfileName(profileId, profileNameUpdateDto);

        // then
        Assertions.assertEquals(response.getStatus().getCode(), ProfileCode.PATCH_SUCCESS.getCode());
    }

    @Test
    @DisplayName("프로필 수정 사진 - 성공")
    void 프로필_사진_수정() {
        // given
        ProfileItemUpdate dto = new ProfileItemUpdate(6L);

        // when
        Response response = profileManagementService.updateProfileAvatar(profileId, dto);

        // then
        Assertions.assertEquals(response.getStatus().getCode(), ProfileCode.PATCH_SUCCESS.getCode());
    }

    @Test
    @DisplayName("프로필 삭제 - 성공")
    void 프로필_삭제() {
        //given
        profileId = 7L;

        // when
        Response response = profileManagementService.deleteProfile(profileId);

        // then
        Assertions.assertEquals(response.getStatus().getCode(), ProfileCode.DELETE_SUCCESS.getCode());
    }
}
