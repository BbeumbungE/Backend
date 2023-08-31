package com.siliconvalley.domain.profile.domain;

import com.siliconvalley.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "profile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
    private Long id;

    @Column(name = "pf_name",length = 10)
    private String profileName;

    @Column(name = "pf_image")
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Profile(String profileName, String profileImage,Member member) {
        this.profileName = profileName;
        this.profileImage = profileImage;
        this.member = member;
    }

    public void updateProfile(
            final String profileName,
            final String profileImage) {
        this.profileName = profileName;
        this.profileImage = profileImage;
    }
}
