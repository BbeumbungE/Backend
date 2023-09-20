package com.siliconvalley.domain.stage.domain;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.record.domain.Record;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stage_num")
    private int stageNum;

    @Column(name = "point")
    private int point;

    @Column(name = "time_limit")
    private int timeLimit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "stage", orphanRemoval = true)
    private List<Record> recordList;

    @Builder
    public Stage (int stageNum, int point, int timeLimit) {
        this.stageNum = stageNum;
        this.point = point;
        this.timeLimit = timeLimit;
    }

    public void addSubject(Subject subject) {
        this.subject = subject;
    }

    public void updateStage(int point, int timeLimit) {
        this.point = point;
        this.timeLimit = timeLimit;
    }
}
