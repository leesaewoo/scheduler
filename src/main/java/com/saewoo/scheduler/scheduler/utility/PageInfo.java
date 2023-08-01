package com.saewoo.scheduler.scheduler.utility;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageInfo {

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;
}