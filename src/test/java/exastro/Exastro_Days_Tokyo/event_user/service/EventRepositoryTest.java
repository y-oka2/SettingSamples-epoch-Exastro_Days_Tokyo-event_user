package exastro.Exastro_Days_Tokyo.event_user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exastro.Exastro_Days_Tokyo.event_user.repository.EventRepository;
import exastro.Exastro_Days_Tokyo.event_user.repository.config.ConnectionConfig;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.EventVO;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryTest {
	
	private MockRestServiceServer mockServer;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Mock   // モックオブジェクトとして使用することを宣言
	protected RestTemplate restTemplate;
	
	@Mock   // モックオブジェクトとして使用することを宣言
	protected ConnectionConfig connectionConfig;
	
	@InjectMocks    // モックオブジェクトの注入
	private EventRepository eventRepository;
	
	@BeforeEach
	public void setup() {
		
		when(connectionConfig.buildBaseUri()).thenReturn("http://localhost:8080");
//		when(connectionConfig.getProtocol()).thenReturn("http");
//		when(connectionConfig.getHost()).thenReturn("localhost");
//		when(connectionConfig.getPort()).thenReturn("8080");
		
		mockServer = MockRestServiceServer.bindTo(restTemplate).build();
//		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	@Test
	public void test_getEvent() throws JsonProcessingException {
		// Mock設定
		mockServer.expect(requestTo("http://localhost:8080" + "/api/v1/event"))
//		mockServer.expect(requestTo(Mockito.anyString()))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(mapper.writeValueAsString(getEventMock0()), MediaType.APPLICATION_JSON));
		
		// 対象メソッド実行
		List<EventVO> eventList = eventRepository.getEvent();
		
		// 以下、結果確認
		assertThat(eventList.isEmpty());
		
		// Mock設定２
		mockServer.expect(requestTo("http://localhost:8080" + "/api/v1/event"))
				.andRespond(withSuccess(mapper.writeValueAsString(getEventMock3()), MediaType.APPLICATION_JSON));
		
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
	
	private EventVO[] getEventMock3() {
		
		EventVO item1 = new EventVO(1, "test_event_1", Timestamp.valueOf("2021-01-02 03:04:05"));
		EventVO item2 = new EventVO(2, "test_event_2", Timestamp.valueOf("2021-12-31 23:59:59"));
		EventVO item3 = new EventVO(3, "test_event_3", Timestamp.valueOf("2021-01-01 01:01:01"));
		
		EventVO[] testData = {item1, item2, item3};
		
		return testData;
	}
}
