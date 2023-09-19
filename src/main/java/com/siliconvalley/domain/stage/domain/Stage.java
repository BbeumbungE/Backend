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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "stage", orphanRemoval = true)
    private List<Record> recordList;

    @Builder
    public Stage (int stageNum, int point) {
        this.stageNum = stageNum;
        this.point = point;
    }

    public void addSubject(Subject subject) {
        this.subject = subject;
    }
}
