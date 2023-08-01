package com.saewoo.scheduler.scheduler.service;

import com.saewoo.scheduler.scheduler.entity.Schedule;
import com.saewoo.scheduler.scheduler.exception.CustomException;
import com.saewoo.scheduler.scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule) {
        verifyExistSchedule(schedule.getScheduleId());
        return scheduleRepository.save(schedule);
    }

    public Schedule findSchedule(long scheduleId) {
        Schedule schedule = verifyFindSchedule(scheduleId);
        return schedule;
    }

    public List<Schedule> findSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        Iterator<Schedule> iterator = schedules.iterator();

        while(iterator.hasNext()) {
            Schedule schedule = iterator.next();

            if(schedule.isDeleted()) {
                iterator.remove();
            }
        }

        return schedules;
    }

    public Schedule updateSchedule(Schedule schedule) {

        Schedule foundSchedule = verifyFindSchedule(schedule.getScheduleId());
        if(schedule.getTitle() != null && !schedule.getTitle().equals(foundSchedule.getTitle())) {
            foundSchedule.setTitle(schedule.getTitle());
        }

        if(schedule.getTodoOrder() != null && !schedule.getTodoOrder().equals(foundSchedule.getTodoOrder())) {
            foundSchedule.setTodoOrder(schedule.getTodoOrder());
        }

        if(!(schedule.isCompleted() && foundSchedule.isCompleted())) {
            foundSchedule.setCompleted(schedule.isCompleted());
        }

        foundSchedule.setModifiedAt(Calendar.getInstance().getTime());

        return foundSchedule;
    }

    public void deleteSchedules() {
        for(long scheduleId = 1 ; scheduleId < scheduleRepository.count() ; scheduleId++) {
            Schedule foundSchedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new CustomException(CustomException.ExceptionList.SCHEDULE_NOT_FOUND));
            foundSchedule.setDeleted(true);
        }
    }

    public void deleteSchedule(long scheduleId) {
        Schedule foundSchedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new CustomException(CustomException.ExceptionList.SCHEDULE_NOT_FOUND));
        foundSchedule.setDeleted(true);
    }

    private void verifyExistSchedule(long scheduleId) {
        if (scheduleRepository.findById(scheduleId).isPresent()) {
            throw new CustomException(CustomException.ExceptionList.SCHEDULE_EXIST);
        }
    }

    private Schedule verifyFindSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(CustomException.ExceptionList.SCHEDULE_NOT_FOUND));

        if(schedule.isDeleted()) {
            throw new CustomException(CustomException.ExceptionList.SCHEDULE_DELETED);
        }

        return schedule;
    }
}
