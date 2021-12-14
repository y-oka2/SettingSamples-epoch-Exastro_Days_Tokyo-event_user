package exastro.Exastro_Days_Tokyo.event_user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exastro.Exastro_Days_Tokyo.event_user.repository.EventRepository;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.EventVO;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDto;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class EventUserServiceTest {
	
	@Mock   // モックオブジェクトとして使用することを宣言
	private EventRepository event_repo;

	@InjectMocks    // モックオブジェクトの注入
	private EventUserService eventUserService;

	@Test
	public void test_getEvent() {

		// Mock設定
		when(event_repo.getEvent()).thenReturn(getEventMock0());
		
		// 対象メソッド実行
		List<EventDto> eventList = eventUserService.getEvent();
		
		// 以下、結果確認
		assertThat(eventList.isEmpty());

		// Mock設定２
		when(event_repo.getEvent()).thenReturn(getEventMock3());
		
		// 対象メソッド実行
		eventList = eventUserService.getEvent();
		
		// 以下、結果確認
		assertThat(eventList).hasSize(3);
		
		assertThat(eventList.get(0)).hasFieldOrPropertyWithValue("eventId", 1);
		assertThat(eventList.get(0)).hasFieldOrPropertyWithValue("eventName", "test_event_1");
		assertThat(eventList.get(0)).hasFieldOrPropertyWithValue("eventDate", Timestamp.valueOf("2021-01-02 03:04:05"));
		
		assertThat(eventList.get(1)).hasFieldOrPropertyWithValue("eventId", 2);
		assertThat(eventList.get(1)).hasFieldOrPropertyWithValue("eventName", "test_event_2");
		assertThat(eventList.get(1)).hasFieldOrPropertyWithValue("eventDate", Timestamp.valueOf("2021-12-31 23:59:59"));
		
		assertThat(eventList.get(2)).hasFieldOrPropertyWithValue("eventId", 3);
		assertThat(eventList.get(2)).hasFieldOrPropertyWithValue("eventName", "test_event_3");
		assertThat(eventList.get(2)).hasFieldOrPropertyWithValue("eventDate", Timestamp.valueOf("2021-01-01 01:01:01"));
		
    }
	
	// Test Data
	private List<EventVO> getEventMock0() {
		
		List<EventVO> testData = new ArrayList<>();
		
		return testData;
	}
	
	private List<EventVO> getEventMock3() {
		
		List<EventVO> testData = new ArrayList<>();
		EventVO item1 = new EventVO(1, "test_event_1", Timestamp.valueOf("2021-01-02 03:04:05"));
		EventVO item2 = new EventVO(2, "test_event_2", Timestamp.valueOf("2021-12-31 23:59:59"));
		EventVO item3 = new EventVO(3, "test_event_3", Timestamp.valueOf("2021-01-01 01:01:01"));
		
		testData.add(item1);
		testData.add(item2);
		testData.add(item3);
		
		return testData;
	}
}
