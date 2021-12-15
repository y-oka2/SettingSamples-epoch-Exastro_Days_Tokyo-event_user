package exastro.Exastro_Days_Tokyo.event_user.service;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.EventUserController;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDto;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EventUserController.class)
//@ContextConfiguration(classes = {EventUserController.class})
public class EventUserControllerTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	@Autowired
	MockMvc mockMvc;
	
	@Mock   // モックオブジェクトとして使用することを宣言
	protected EventService service;

	@InjectMocks    // モックオブジェクトの注入
	private EventUserController eventUserController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(eventUserController).build();
	}

	@Test
	public void test_getEvent() throws Exception {

		// Mock設定
		when(service.getEvent()).thenReturn(getEventMock0());
		
		// 対象メソッド実行
		MvcResult mvcResult = mockMvc.perform(get("/api/v1/event"))
				// 以下、結果確認
				.andExpect(status().isOk())
				.andReturn();

	    // 取得したレスポンスをログに出力しておく
		logger.info("external response : {}", mvcResult.getResponse().getContentAsString());
		
		// Mock設定２
		when(service.getEvent()).thenReturn(getEventMock3());
		
		// 対象メソッド実行
		mvcResult = mockMvc.perform(get("/api/v1/event"))
				// 以下、結果確認
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				
				.andExpect(jsonPath("$[0].event_id").value(1))
				.andExpect(jsonPath("$[0].event_name").value("test_event_1"))
				.andExpect(jsonPath("$[0].event_date").value("2021-01-02 03:04:05"))
				
				.andExpect(jsonPath("$[1].event_id").value(2))
				.andExpect(jsonPath("$[1].event_name").value("test_event_2"))
				.andExpect(jsonPath("$[1].event_date").value("2021-12-31 23:59:59"))
				
				.andExpect(jsonPath("$[2].event_id").value(3))
				.andExpect(jsonPath("$[2].event_name").value("test_event_3"))
				.andExpect(jsonPath("$[2].event_date").value("2021-01-01 01:01:01"))
				.andReturn();
		
		logger.info("external response : {}", mvcResult.getResponse().getContentAsString());
		
    }
	
	// Test Data
	private List<EventDto> getEventMock0() {
		
		List<EventDto> testData = new ArrayList<>();
		
		return testData;
	}
	
	private List<EventDto> getEventMock3() {
		
		List<EventDto> testData = new ArrayList<>();
		EventDto item1 = new EventDto(1, "test_event_1", Timestamp.valueOf("2021-01-02 03:04:05"));
		EventDto item2 = new EventDto(2, "test_event_2", Timestamp.valueOf("2021-12-31 23:59:59"));
		EventDto item3 = new EventDto(3, "test_event_3", Timestamp.valueOf("2021-01-01 01:01:01"));
		
		testData.add(item1);
		testData.add(item2);
		testData.add(item3);
		
		return testData;
	}
}
