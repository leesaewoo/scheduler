package com.saewoo.scheduler.scheduler.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleResponseDto {

    private long id;

    private String title;

    private Integer todoOrder;

    private boolean completed;
}
