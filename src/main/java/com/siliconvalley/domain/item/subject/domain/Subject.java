package com.siliconvalley.domain.item.subject.domain;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.stage.domain.Stage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "sketch")
    private String sketch;

    @OneToOne(mappedBy = "subject", orphanRemoval = true, fetch = FetchType.LAZY)
    private Stage stage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Subject(String subjectName, String subjectImage, String sketch, Item item) {
        this.subjectName = subjectName;
        this.subjectImage = subjectImage;
        this.item = item;
        this.sketch = sketch;
    }

    public static Subject toEntity(String subjectName, String subjectImgUrl, String sketchImageUrl, Item item) {
        return Subject.builder()
                .subjectName(subjectName)
                .subjectImage(sketchImageUrl)
                .sketch(sketchImageUrl)
                .item(item)
                .build();
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
