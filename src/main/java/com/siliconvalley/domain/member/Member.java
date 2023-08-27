package com.siliconvalley.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @Column(nullable = false, updatable = false)
    private String id; // Entity Key, UUID 사용

    @Column(nullable = false)
    private String userId; // 클라이언트에 공유, 사용자 식별용

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Builder
    public Member(String id, String userId,String email, String role) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }
}
