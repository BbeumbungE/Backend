package com.siliconvalley.domain.item.item.domain;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import com.siliconvalley.domain.item.item.dto.AvatarItemCreateRequest;
import com.siliconvalley.domain.item.item.dto.SubjectItemCreateRequest;
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

    @OneToOne(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Avatar avatar;

    @OneToOne(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Subject subject;

    @Builder
    public Item(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setAvatar(AvatarItemCreateRequest dto) {
        Avatar avatar = buildAvatar(dto.getAvatarName(), dto.getAvatarImage());
        this.avatar = avatar;
        avatar.setItem(this); // 양방향 관계 설정
    }

    public void setSubject(SubjectItemCreateRequest dto) {
        Subject subject = buildSubject(dto.getSubjectName(), dto.getSubjectImage());
        this.subject = subject;
        subject.setItem(this); // 양방향 관계 설정
    }

    public static Avatar buildAvatar(String avatarName, String avatarImage) {
        return Avatar.builder()
                .avatarName(avatarName)
                .avatarImage(avatarImage)
                .build();
    }

    public static Subject buildSubject(String subjectName, String subjectImage) {
        return Subject.builder()
                .subjectName(subjectName)
                .subjectImage(subjectImage)
                .build();
    }
}
