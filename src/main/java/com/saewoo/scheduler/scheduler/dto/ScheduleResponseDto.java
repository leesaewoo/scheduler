package com.saewoo.scheduler.scheduler.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.UriComponents;

import java.net.URL;

@Getter
@Builder
public class ScheduleResponseDto {

    private long id;

    private String title;

    private Integer todoOrder;

    private boolean completed;
}
