package com.saewoo.scheduler.scheduler.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleId;

    private String title;

    private Integer todoOrder;

    private boolean completed;

    private final Date createAt = Calendar.getInstance().getTime();

    @Builder.Default
    private Date modifiedAt = Calendar.getInstance().getTime();

    @Builder.Default
    private boolean deleted = false;
}
