package com.siliconvalley.domain.profile.domain;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.point.domain.Point;
import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.profile.dto.ProfileNameUpdate;
import com.siliconvalley.domain.record.domain.Record;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProfileItem profileItem;

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Canvas> canvasList = new ArrayList<>();

    @OneToOne(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Point point;

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Emotion> emotions;

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MyItem> myItemList = new ArrayList<>();

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Record> recordList;

    @OneToMany(mappedBy = "receiver", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Notification> notificationList;

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Emotion> emotionList;

    @Builder
    public Profile(String profileName, Member member, ProfileItem profileItem) {
        this.profileName = profileName;
        this.member = member;
        this.profileItem = profileItem;
    }

    public void addCanvas(Canvas canvas){
        this.canvasList.add(canvas);
    }

    public void addPoint(Point point) {
        this.point = point;
    }

    public void addMyItem(MyItem myItem) {
        this.myItemList.add(myItem);
    }

    public void addProfileAvatar(ProfileItem profileItem) {
        this.profileItem = profileItem;
    }

    public void updateProfileName(
            final ProfileNameUpdate dto) {
        this.profileName = dto.getProfileName();
    }
}
