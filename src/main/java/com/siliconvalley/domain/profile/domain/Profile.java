package com.siliconvalley.domain.profile.domain;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.point.domain.Point;
import com.siliconvalley.domain.post.domain.Post;
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

    @Column(name = "pf_image")
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Canvas> canvasList = new ArrayList<>();

    @OneToMany(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Post> postList = new ArrayList<>();

    @OneToOne(mappedBy = "profile", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Point point;

    @Builder
    public Profile(String profileName, String profileImage,Member member) {
        this.profileName = profileName;
        this.profileImage = profileImage;
        this.member = member;
    }

    public void addCanvas(Canvas canvas){
        this.canvasList.add(canvas);
    }

    public Post addPost(Canvas canvas){
        Post post = buildPost(canvas);
        this.postList.add(post);
        return post;
    }

    public Post buildPost(Canvas canvas){
        return Post.builder()
                .profile(this)
                .canvas(canvas)
                .build();
    }
    public void setPoint(Point point) {
        this.point = point;
        point.setProfile(this);
    }

    public Point buildPoint() {
        return Point.builder()
                .point(0L)
                .build();
    }

    public void updateProfile(
            final String profileName,
            final String profileImage) {
        this.profileName = profileName;
        this.profileImage = profileImage;
    }
}
