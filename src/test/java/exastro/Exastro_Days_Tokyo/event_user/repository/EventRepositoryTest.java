package exastro.Exastro_Days_Tokyo.event_user.repository;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exastro.Exastro_Days_Tokyo.event_user.repository.vo.EventVO;

@SpringBootTest
public class EventRepositoryTest {
	
	private MockRestServiceServer mockServer;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void test_getEvent() throws JsonProcessingException {
		// Mock設定
		mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo("http://localhost:8080" + "/api/v1/event"))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(mapper.writeValueAsString(getEventMock0()), MediaType.APPLICATION_JSON));
		
		// 対象メソッド実行
		List<EventVO> eventList = eventRepository.getEvent();
		
		// 以下、結果確認
		assertThat(eventList.isEmpty());
		
		// Mock設定２
		mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo("http://localhost:8080" + "/api/v1/event"))
				.andRespond(withSuccess(getEventMock3_json(), MediaType.APPLICATION_JSON));
		
		// 対象メソッド実行
		eventList = eventRepository.getEvent();
		
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
		
		mockServer.verify();
	}
	
	// Test Data
	private EventVO[] getEventMock0() {
		
		EventVO[] testData = new EventVO[0];
		
		return testData;
	}
	
	private String getEventMock3_json() {
		
		String item1 = "{\"event_id\": 1, \"event_name\": \"test_event_1\", \"event_date\": \"2021-01-02 03:04:05\"}";
		String item2 = "{\"event_id\": 2, \"event_name\": \"test_event_2\", \"event_date\": \"2021-12-31 23:59:59\"}";
		String item3 = "{\"event_id\": 3, \"event_name\": \"test_event_3\", \"event_date\": \"2021-01-01 01:01:01\"}";
		
		String testData = "[" + item1 + "," + item2 + "," + item3 + "]";
		
		return testData;
	}
}
