package com.siliconvalley.domain.item.avatar.domain;

import com.siliconvalley.domain.item.item.domain.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "avatar")
@NoArgsConstructor
@Getter
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
    private Long id;

    @Column(name = "avt_name")
    private String avatarName;

    @Column(name = "avt_image")
    private String avatarImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Avatar(String avatarName, String avatarImage, Item item) {
        this.avatarName = avatarName;
        this.avatarImage = avatarImage;
        this.item = item;
    }

    public static Avatar toEntity(String avatarName, Item item, String imgUrl) {
        return Avatar.builder()
                .avatarName(avatarName)
                .item(item)
                .avatarImage(imgUrl)
                .build();
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
