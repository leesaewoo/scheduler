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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@CrossOrigin("http://todobackend.com")
@RestController
@RequestMapping
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

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSchedule(@PathVariable("id") @Positive long id) {
        Schedule foundScheduleEntity = scheduleService.findSchedule(id);

        ScheduleResponseDto response = scheduleMapper.scheduleEntityToSchedulerResponseDto(foundScheduleEntity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getSchedules(@RequestParam @Positive int pageNumber,
                                       @RequestParam @Positive int pageSize) {
        Page<Schedule> scheduleList = scheduleService.findSchedules(pageNumber, pageSize);

        SchedulesResponseDto responses = scheduleMapper.schedulListToSchedulesResponseDto(scheduleList);

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity patchSchedule(@PathVariable("id") @Positive long id,
                                        @RequestBody @Valid SchedulePatchDto schedulePatchDto) {
        Schedule schedule = scheduleMapper.schedulePatchDtoToSchedule(schedulePatchDto);
        schedule.setId(id);
        schedule = scheduleService.updateSchedule(schedule);

        ScheduleResponseDto response = scheduleMapper.scheduleEntityToSchedulerResponseDto(schedule);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteSchedules() {
        scheduleService.deleteSchedules();

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSchedules(@PathVariable("id") @Positive long id) {
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
