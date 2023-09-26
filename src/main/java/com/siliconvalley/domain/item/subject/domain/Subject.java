package com.siliconvalley.domain.item.subject.domain;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
import com.siliconvalley.domain.stage.domain.Stage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "subject", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Sketch> sketchList;

    @OneToOne(mappedBy = "subject", orphanRemoval = true, fetch = FetchType.LAZY)
    private Stage stage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(mappedBy = "subject", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Pix2Pix pix2Pix;

    @Builder
    public Subject(String subjectName, String subjectImage, Item item, Pix2Pix pix2Pix) {
        this.subjectName = subjectName;
        this.subjectImage = subjectImage;
        this.item = item;
        this.pix2Pix = pix2Pix;
    }


    public static Subject toEntity(String subjectName, String sketchImageUrl, Item item) {
        return Subject.builder()
                .subjectName(subjectName)
                .subjectImage(sketchImageUrl)
                .item(item)
                .build();
    }

    public void setPix2Pix(Pix2Pix pix2Pix){
        this.pix2Pix = pix2Pix;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
