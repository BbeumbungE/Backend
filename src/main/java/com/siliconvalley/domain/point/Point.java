package com.siliconvalley.domain.point;

import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "point")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point")
    private Long point;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Point(Long point, Profile profile) {
        this.point = point;
        this.profile = profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void updatePoint(Long newPointValue) {
        if (newPointValue != null) {
            this.point = newPointValue;
        }
    }
}
