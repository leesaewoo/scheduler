package com.saewoo.scheduler.scheduler.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class SchedulePostDto {

    private String title;

    private Integer todoOrder;

    private boolean completed;
}
