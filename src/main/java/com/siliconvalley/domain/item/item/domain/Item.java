package com.siliconvalley.domain.item.item.domain;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, updatable = false)
    private Long id;

    @Column(name = "item_price")
    private Long itemPrice;

    @OneToOne(mappedBy = "item")
    private Avatar avatar;

    @OneToOne(mappedBy = "item")
    private Subject subject;

    @Builder
    public Item(Long itemPrice) {
        this.itemPrice = itemPrice;
    }
}
