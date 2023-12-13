package com.siliconvalley.domain.canvas.dto;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasCreateDto {

    private Long profileId;
    private Subject subject;
    private String sketchUrl;


    @Builder
    public CanvasCreateDto(Long profileId, Subject subject, String sketchUrl){
        this.profileId = profileId;
        this.subject = subject;
        this.sketchUrl = sketchUrl;
    }

    public Canvas toEntity(){
        return Canvas.builder()
                .profileId(profileId).subject(subject)
                .sketch(sketchUrl)
                .build();
    }

}
