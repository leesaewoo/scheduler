package com.saewoo.scheduler.scheduler.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.util.UriComponents;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "todos")
@Builder
@Setter
@Getter
@DynamicUpdate
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private Integer todoOrder;

    private boolean completed;

    private final Date createAt = Calendar.getInstance().getTime();

    @Builder.Default
    private Date modifiedAt = Calendar.getInstance().getTime();

    @Builder.Default
    private boolean deleted = false;
}
