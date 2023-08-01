package com.saewoo.scheduler.scheduler.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SchedulePatchDto {

    private String title;

    private Integer todoOrder;

    private boolean completed;
}
