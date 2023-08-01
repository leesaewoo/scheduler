package com.saewoo.scheduler.scheduler.repository;

import com.saewoo.scheduler.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
