package com.saewoo.scheduler.scheduler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SchedulerResponseDto {

    private long id;

    private String title;

    private int todoOrder;

    private boolean completed;
}
