package com.siliconvalley.global.common.dto.page;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T, P> {
    private final Object data;
    private final PageInfoResponse pageInfo;

    public PageResponse(List<T> data, Page<P> page) {
        this.data = data;
        this.pageInfo = new PageInfoResponse(page);
    }

    public PageResponse(Object data, Page<P> page) {
        this.data = data;
        this.pageInfo = new PageInfoResponse(page);
    }
}
