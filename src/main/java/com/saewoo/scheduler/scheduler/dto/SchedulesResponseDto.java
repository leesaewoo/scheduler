package com.saewoo.scheduler.scheduler.dto;

import com.saewoo.scheduler.scheduler.utility.PageInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SchedulesResponseDto<T> {
    private T data;

    private PageInfo pageInfo;
}
