package com.saewoo.scheduler.scheduler;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SchedulerMapper {

    public SchedulerEntity schedulerPostDtoToSchedulerEntity(SchedulerPostDto schedulerPostDto) {
        if(schedulerPostDto == null) {
            return null;
        }
        else {
            SchedulerEntity schedulerEntity = SchedulerEntity.builder()
                    .title(schedulerPostDto.getTitle())
                    .todoOrder(schedulerPostDto.getTodoOrder())
                    .completed(schedulerPostDto.isCompleted())
                    .build();

            return schedulerEntity;
        }
    }

    public SchedulerResponseDto schedulerEntityToSchedulerResponseDto(SchedulerEntity schedulerEntity) {
        if(schedulerEntity == null) {
            return null;
        }
        else {
            SchedulerResponseDto schedulerResponseDto = SchedulerResponseDto.builder()
                    .id(schedulerEntity.getId())
                    .title(schedulerEntity.getTitle())
                    .todoOrder(schedulerEntity.getTodoOrder())
                    .completed(schedulerEntity.isCompleted())
                    .build();

            return schedulerResponseDto;
        }
    }
}
