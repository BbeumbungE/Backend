package com.siliconvalley.domain.record.domain;

import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.stage.domain.Stage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "score")
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Record (int score, Stage stage, Profile profile) {
        this.score = score;
        this.stage = stage;
        this.profile = profile;
    }

    public static Record toEntity(Profile profile, Stage stage, int score) {
        return Record.builder()
                .score(score)
                .profile(profile)
                .stage(stage)
                .build();
    }

    public void updateScore(int score) {
        this.score = score;
    }



}
