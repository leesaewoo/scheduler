package com.saewoo.scheduler.scheduler.controller;

import com.saewoo.scheduler.scheduler.dto.SchedulePatchDto;
import com.saewoo.scheduler.scheduler.dto.SchedulePostDto;
import com.saewoo.scheduler.scheduler.dto.ScheduleResponseDto;
import com.saewoo.scheduler.scheduler.dto.SchedulesResponseDto;
import com.saewoo.scheduler.scheduler.entity.Schedule;
import com.saewoo.scheduler.scheduler.mapper.ScheduleMapper;
import com.saewoo.scheduler.scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.util.List;

@RestController
@RequestMapping("/test")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity postSchedule(@RequestBody @Valid SchedulePostDto schedulePostDto) {
        Schedule schedule = scheduleMapper.schedulePostDtoToSchedulerEntity(schedulePostDto);

        Schedule postedSchedule = scheduleService.createSchedule(schedule);

        ScheduleResponseDto response = scheduleMapper.scheduleEntityToSchedulerResponseDto(postedSchedule);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity getSchedule(@PathVariable("scheduleId") @Positive long scheduleId) {
        Schedule foundScheduleEntity = scheduleService.findSchedule(scheduleId);

        ScheduleResponseDto response = scheduleMapper.scheduleEntityToSchedulerResponseDto(foundScheduleEntity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getSchedules() {
        List<Schedule> scheduleList = scheduleService.findSchedules();

        SchedulesResponseDto responses = scheduleMapper.schedulListToSchedulesResponseDto(scheduleList);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PatchMapping("{scheduleId}")
    public ResponseEntity patchSchedule(@PathVariable("scheduleId") @Positive long scheduleId,
                                        @RequestBody @Valid SchedulePatchDto schedulePatchDto) {
        Schedule schedule = scheduleMapper.schedulePatchDtoToSchedule(schedulePatchDto);
        schedule.setScheduleId(scheduleId);
        schedule = scheduleService.updateSchedule(schedule);

        ScheduleResponseDto response = scheduleMapper.scheduleEntityToSchedulerResponseDto(schedule);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteSchedules() {
        scheduleService.deleteSchedules();

        return new ResponseEntity<>("Deleted All Schedule", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedules(@PathVariable("scheduleId") @Positive long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        String response = "Deleted Schedule (Schedule Id : " + scheduleId + ")";
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
