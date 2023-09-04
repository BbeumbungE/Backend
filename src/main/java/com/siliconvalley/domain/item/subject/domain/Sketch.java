package com.siliconvalley.domain.item.subject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sketch")
@NoArgsConstructor
@Getter
public class Sketch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "level")
    private int level;

    @Column(name = "sketch")
    private String sketch;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Builder
    public Sketch(int level, String sketch, Subject subject) {
        this.level = level;
        this.sketch = sketch;
        this.subject = subject;
    }
}
