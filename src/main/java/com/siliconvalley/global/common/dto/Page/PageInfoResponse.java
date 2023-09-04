package com.siliconvalley.global.common.dto.Page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageInfoResponse {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PageInfoResponse(Page page) {
        this.page = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

}
