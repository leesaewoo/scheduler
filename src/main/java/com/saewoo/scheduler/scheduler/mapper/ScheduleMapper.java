package com.saewoo.scheduler.scheduler.mapper;

import com.saewoo.scheduler.scheduler.dto.SchedulePatchDto;
import com.saewoo.scheduler.scheduler.dto.SchedulePostDto;
import com.saewoo.scheduler.scheduler.dto.ScheduleResponseDto;
import com.saewoo.scheduler.scheduler.dto.SchedulesResponseDto;
import com.saewoo.scheduler.scheduler.entity.Schedule;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ScheduleMapper {

    public Schedule schedulePostDtoToSchedulerEntity(SchedulePostDto schedulePostDto) {
        if(schedulePostDto == null) {
            return null;
        }
        else {
            Schedule schedule = Schedule.builder()
                    .title(schedulePostDto.getTitle())
                    .todoOrder(schedulePostDto.getTodoOrder())
                    .completed(schedulePostDto.isCompleted())
                    .build();

            return schedule;
        }
    }

    public Schedule schedulePatchDtoToSchedule(SchedulePatchDto schedulePatchDto) {
        if(schedulePatchDto == null) {
            return null;
        }
        else {
            Schedule schedule = Schedule.builder()
                    .title(schedulePatchDto.getTitle())
                    .todoOrder(schedulePatchDto.getTodoOrder())
                    .completed(schedulePatchDto.isCompleted())
                    .build();

            return schedule;
        }
    }

    public ScheduleResponseDto scheduleEntityToSchedulerResponseDto(Schedule schedule) {
        if(schedule == null) {
            return null;
        }
        else {
            ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                    .scheduleId(schedule.getScheduleId())
                    .title(schedule.getTitle())
                    .todoOrder(schedule.getTodoOrder())
                    .completed(schedule.isCompleted())
                    .build();

            return scheduleResponseDto;
        }
    }

    public SchedulesResponseDto schedulListToSchedulesResponseDto(List<Schedule> scheduleList) {
        if(scheduleList.isEmpty()) {
            return null;
        }
        else {
            SchedulesResponseDto schedulesResponseDto = SchedulesResponseDto.builder()
                    .data(scheduleList)
                    .build();

            return schedulesResponseDto;
        }
    }
}
