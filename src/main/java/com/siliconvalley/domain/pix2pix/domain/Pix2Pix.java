package com.siliconvalley.domain.pix2pix.domain;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pix2pix_model")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Pix2Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "vision_name")
    private String visionName;

    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Builder
    public Pix2Pix(String modelName, String visionName, Subject subject){
        this.modelName = modelName;
        this.visionName = visionName;
        this.subject = subject;
    }

    public static Pix2Pix toEntity(Subject subject, String modelName, String visionName){
        return Pix2Pix.builder()
                .modelName(modelName)
                .visionName(visionName)
                .subject(subject)
                .build();
    }

}
