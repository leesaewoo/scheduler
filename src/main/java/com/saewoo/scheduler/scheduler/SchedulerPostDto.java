package com.saewoo.scheduler.scheduler;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class SchedulerPostDto {

    @NotBlank
    private String title;

    @NotEmpty
    @Range(min = 1, max = 255)
    private int todoOrder;

    @NotEmpty
    private boolean completed;
}
