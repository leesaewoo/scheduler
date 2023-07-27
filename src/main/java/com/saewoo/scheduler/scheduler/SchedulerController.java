package com.saewoo.scheduler.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Validated
@RequiredArgsConstructor
public class SchedulerController {

    private final SchedulerMapper schedulerMapper;

    @PostMapping
    public void postSchedule(@RequestBody SchedulerPostDto postDto) {

    }
}
