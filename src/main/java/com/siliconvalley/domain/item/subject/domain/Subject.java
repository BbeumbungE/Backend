package com.siliconvalley.domain.item.subject.domain;

import com.siliconvalley.domain.item.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@Getter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "sub_name")
    private String subjectName;

    @Column(name = "sub_Image")
    private String subjectImage;

    @OneToMany(mappedBy = "subject")
    private List<Sketch> sketchList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Subject(String subjectName, String subjectImage, Item item) {
        this.subjectName = subjectName;
        this.subjectImage = subjectImage;
        this.item = item;
    }
}
