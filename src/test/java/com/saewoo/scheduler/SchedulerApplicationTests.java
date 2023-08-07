package com.saewoo.scheduler;

import com.saewoo.scheduler.scheduler.entity.Schedule;
import com.saewoo.scheduler.scheduler.exception.CustomException;
import com.saewoo.scheduler.scheduler.repository.ScheduleRepository;
import com.saewoo.scheduler.scheduler.service.ScheduleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;


//TODO: 테스트 코드 작성하기, MockBean 에 대해서 더 공부하기
@DataJpaTest
class ScheduleRepositoryTest {

	@Autowired
	ScheduleRepository scheduleRepository;

	@MockBean
	ScheduleService scheduleService;

	@Test
	@DisplayName("스케쥴 빌더 테스트")
	void createSchedule() {
		//given
		Schedule schedule = Schedule.builder()
				.id(1L)
				.title("Test Title")
				.todoOrder(1)
				.completed(false)
				.build();
		//when
		//then
		Assertions.assertThat(schedule.getId()).isEqualTo(1L);
		Assertions.assertThat(schedule.isDeleted()).isFalse();
		Assertions.assertThat(schedule.getModifiedAt()).isEqualTo(schedule.getCreateAt());
	}

	@Test
	@DisplayName("DB 세이브&겟 테스트")
	void repositorySaveAndGetTest() {
		//given
		Schedule schedule1 = Schedule.builder()
				.title("Test Title1")
				.todoOrder(1)
				.completed(false)
				.build();

		Schedule schedule2 = Schedule.builder()
				.title("Test Title2")
				.todoOrder(2)
				.completed(false)
				.build();

		scheduleRepository.save(schedule1);
		scheduleRepository.save(schedule2);

		//when
		List<Schedule> result1 = scheduleRepository.findAll();
		Schedule result2 = scheduleRepository.findById(1L).orElseThrow();

		//then
		Assertions.assertThat(result1.size()).isEqualTo(2);
		Assertions.assertThat(result1.get(0).getId()).isEqualTo(1L);
		Assertions.assertThat(result1.get(1).getId()).isEqualTo(2L);
		Assertions.assertThat(result2.getTitle()).isEqualTo("Test Title1");
		Assertions.assertThat(result2.getTodoOrder()).isEqualTo(1);
	}

	@Test
	@DisplayName("DB 업데이트 테스트")
	void repositoryUpdateTest() {
		//given
		Schedule schedule = Schedule.builder()
				.title("Test Title")
				.todoOrder(1)
				.completed(false)
				.build();

		Schedule savedSchedule = scheduleRepository.save(schedule);

		savedSchedule.setCompleted(true);
		savedSchedule.setTitle("New Title");
		savedSchedule.setTodoOrder(2);

		//when

		//then
		//이전에 2개의 entity 를 repository 에 save 했기 때문에 시작되는 id의 값이 3L 이다.
		Assertions.assertThat(savedSchedule.getId()).isEqualTo(3L);

		Assertions.assertThat(savedSchedule.getTitle()).isEqualTo("New Title");
		Assertions.assertThat(savedSchedule.getTodoOrder()).isEqualTo(2);
		Assertions.assertThat(savedSchedule.isCompleted()).isTrue();
	}

	@Test
	void deleteTest() {
		Schedule schedule = Schedule.builder()
				.title("Test Title")
				.todoOrder(1)
				.completed(false)
				.build();

		schedule = scheduleService.createSchedule(schedule);

		scheduleService.deleteSchedule(schedule.getId());

		Assertions.assertThatThrownBy(() -> scheduleService.findSchedule(4L)).isInstanceOf(CustomException.class);
	}
}

class ScheduleServiceTest {

	@MockBean
	ScheduleRepository scheduleRepository;

	@Test
	void createScheduleTest() {

	}
}
