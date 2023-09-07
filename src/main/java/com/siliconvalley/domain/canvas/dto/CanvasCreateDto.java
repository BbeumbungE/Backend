package com.siliconvalley.domain.canvas.dto;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasCreateDto {

    private Profile profile;
    private Subject subject;
    private String canvasUrl;
    private String sketchUrl;

    public CanvasCreateDto(Profile profile, Subject subject, String canvasUrl, String sketchUrl){
        this.profile = profile;
        this.subject = subject;
        this.canvasUrl = canvasUrl;
        this.sketchUrl = sketchUrl;
    }

    public Canvas toEntity(){
        return Canvas.builder()
                .profile(profile).subject(subject)
                .canvas(canvasUrl).sketch(sketchUrl)
                .build();
    }

}
