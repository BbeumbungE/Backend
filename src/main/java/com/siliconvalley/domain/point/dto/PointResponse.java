package com.siliconvalley.domain.point.dto;

import com.siliconvalley.domain.point.domain.Point;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointResponse {

    private Long id;
    private Long point;

    public PointResponse(Point point) {
        this.id = point.getId();
        this.point = point.getPoint();
    }
}
