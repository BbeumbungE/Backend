package com.siliconvalley.domain.profile.domain;

import com.siliconvalley.domain.item.myitem.domain.MyItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "profile_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_item_id")
    private MyItem myItem;

    @Builder
    public ProfileItem(Profile profile, MyItem myItem) {
        this.profile = profile;
        this.myItem = myItem;
    }

    public static ProfileItem toEntity(Profile profile, MyItem myItem) {
        return ProfileItem.builder()
                .myItem(myItem)
                .profile(profile)
                .build();
    }

    public void updateMyItem(MyItem myItem) {
        this.myItem = myItem;
    }
}
