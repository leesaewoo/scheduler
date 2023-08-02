package com.saewoo.scheduler.scheduler.mapper;

import com.saewoo.scheduler.scheduler.dto.SchedulePatchDto;
import com.saewoo.scheduler.scheduler.dto.SchedulePostDto;
import com.saewoo.scheduler.scheduler.dto.ScheduleResponseDto;
import com.saewoo.scheduler.scheduler.dto.SchedulesResponseDto;
import com.saewoo.scheduler.scheduler.entity.Schedule;
import com.saewoo.scheduler.scheduler.utility.PageInfo;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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
                    .id(schedule.getId())
                    .title(schedule.getTitle())
                    .todoOrder(schedule.getTodoOrder())
                    .completed(schedule.isCompleted())
                    .build();

            return scheduleResponseDto;
        }
    }

    public SchedulesResponseDto schedulListToSchedulesResponseDto(Page<Schedule> scheduleList) {
        if(scheduleList.isEmpty()) {
            return null;
        }
        else {
            SchedulesResponseDto schedulesResponseDto = SchedulesResponseDto.builder()
                    .data(scheduleList.getContent())
                    .pageInfo(PageInfo.builder()
                            .pageNumber(scheduleList.getNumber())
                            .pageSize(scheduleList.getSize())
                            .totalElements(scheduleList.getTotalElements())
                            .totalPages(scheduleList.getTotalPages())
                            .build())
                    .build();

            return schedulesResponseDto;
        }
    }
}
