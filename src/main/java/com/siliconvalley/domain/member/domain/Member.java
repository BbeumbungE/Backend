package com.siliconvalley.domain.member.domain;

import com.siliconvalley.domain.profile.domain.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @Column(name = "id",nullable = false, updatable = false)
    private String id; // Entity Key, UUID 사용

    @Column(name = "user_id", nullable = false)
    private String userId; // 클라이언트에 공유, 사용자 식별용

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Profile> profileList = new ArrayList<>();

    @Builder
    public Member(String id, String userId,String email, String role) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }
}
