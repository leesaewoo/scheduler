package com.saewoo.scheduler.scheduler;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<SchedulerEntity, Long> {

}
