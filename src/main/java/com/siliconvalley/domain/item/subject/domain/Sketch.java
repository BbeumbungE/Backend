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
    @Column(name = "id")
    private Long id;

    @Column(name = "sketch_image_url")
    private String sketchImageUrl;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Builder
    public Sketch(String sketchImageUrl, Subject subject) {
        this.sketchImageUrl = sketchImageUrl;
        this.subject = subject;
    }

    public static Sketch toEntity(String sketchImageUrl, Subject subject) {
        return Sketch.builder()
                .sketchImageUrl(sketchImageUrl)
                .subject(subject)
                .build();
    }
}
