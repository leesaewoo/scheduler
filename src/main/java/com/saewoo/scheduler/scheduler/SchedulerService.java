package com.saewoo.scheduler.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public SchedulerEntity createSchedule(SchedulerEntity schedulerEntity) {

        verifyExistSchedule(schedulerEntity.getId());

        return schedulerRepository.save(schedulerEntity);
    }

    private void verifyExistSchedule(long id) {
        if(schedulerRepository.findById(id).isPresent()) {
            throw new RuntimeException(); 
            //TODO: Custom Exception 작성하기
        }
    }
}
