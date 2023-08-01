package com.saewoo.scheduler.scheduler.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SchedulesResponseDto<T> {
    private T data;
}
