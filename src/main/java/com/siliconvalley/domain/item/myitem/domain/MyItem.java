package com.siliconvalley.domain.item.myitem.domain;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.domain.ProfileItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "my_item")
@NoArgsConstructor
@Getter
public class MyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "item_type")
    private String itemType;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public MyItem(String itemType, Profile profile, Item item) {
        this.itemType = itemType;
        this.profile = profile;
        this.item = item;
    }

    public static MyItem toEntity(Profile profile, Item item, String itemType) {
        return MyItem.builder()
                .itemType(itemType)
                .profile(profile)
                .item(item)
                .build();
    }
}
