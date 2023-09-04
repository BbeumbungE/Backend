package com.siliconvalley.domain.canvas.domain;

import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "canvas")
@Slf4j
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "sketch", nullable = false)
    private String sketch;

    @Column(name = "converted_sketch", nullable = false)
    private String canvas;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToOne(mappedBy = "canvas", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Post post;

    @Builder
    public Canvas(String sketch, String canvas, Profile profile, Subject subject){
        this.sketch = sketch;
        this.canvas = canvas;
        this.profile = profile;
        this.subject = subject;
    }

}
