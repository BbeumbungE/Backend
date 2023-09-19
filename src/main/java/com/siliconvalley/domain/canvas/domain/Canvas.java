package com.siliconvalley.domain.canvas.domain;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.profile.domain.Profile;
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
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sketch", nullable = false)
    private String sketch;

    @Column(name = "converted_sketch")
    private String canvas;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToOne(mappedBy = "canvas", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Post post;

    public Post buildPost(){
        Post post = Post.builder().canvas(this).build();
        this.post = post;
        return post;
    }

    @Builder
    public Canvas(Subject subject, Profile profile, String sketch){
        this.subject = subject;
        this.profile = profile;
        this.sketch = sketch;
    }

    public void updateSketch(String sketch){
        this.sketch = sketch;
    }

    public void updateCanvas(String canvasUrl){
        this.canvas = canvasUrl;
    }

}
