package com.siliconvalley.domain.canvas.domain;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "profile_id")
    private Long profileId;

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
    public Canvas(Subject subject, Long profileId, String sketch){
        this.subject = subject;
        this.sketch = sketch;
    }

    public void updateSketch(String sketch){
        this.sketch = sketch;
    }

    public void updateCanvas(String canvasUrl){
        this.canvas = canvasUrl;
    }

}
