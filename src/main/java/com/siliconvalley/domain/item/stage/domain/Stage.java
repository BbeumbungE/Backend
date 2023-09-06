package com.siliconvalley.domain.item.stage.domain;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stage")
@NoArgsConstructor
@Getter
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stage")
    private int stage;

    @Column(name = "point")
    private int point;

    @OneToOne(mappedBy = "stage",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Subject subject;

    @Builder
    public Stage (int stage, int point, Subject subject) {
        this.stage = stage;
        this.point = point;
        this.subject = subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.setStage(this);
    }
}
