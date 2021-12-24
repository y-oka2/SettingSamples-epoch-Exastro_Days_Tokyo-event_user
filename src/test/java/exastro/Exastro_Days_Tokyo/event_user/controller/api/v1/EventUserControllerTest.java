/*   Copyright 2021 NEC Corporation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package exastro.Exastro_Days_Tokyo.event_user.controller.api.v1;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import exastro.Exastro_Days_Tokyo.event_user.service.EventService;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDto;

@ExtendWith(SpringExtension.class)
public class EventUserControllerTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
				.andExpect(jsonPath("$[0].event_date").value("2021-01-02T03:04:05.000Z"))
				
				.andExpect(jsonPath("$[1].event_id").value(2))
				.andExpect(jsonPath("$[1].event_name").value("test_event_2"))
				.andExpect(jsonPath("$[1].event_date").value("2021-12-31T23:59:59.000Z"))
				
				.andExpect(jsonPath("$[2].event_id").value(3))
				.andExpect(jsonPath("$[2].event_name").value("test_event_3"))
				.andExpect(jsonPath("$[2].event_date").value("2021-01-01T01:01:01.000Z"))
				.andReturn();
		
		logger.info("external response : {}", mvcResult.getResponse().getContentAsString());
		
    }
	
	// Test Data
	private List<EventDto> getEventMock0() {
		
		List<EventDto> testData = new ArrayList<>();
		
		return testData;
	}
	
	private List<EventDto> getEventMock3() throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		List<EventDto> testData = new ArrayList<>();
		EventDto item1 = new EventDto(1, "test_event_1", dateFormat.parse("2021-01-02 03:04:05"));
		EventDto item2 = new EventDto(2, "test_event_2", dateFormat.parse("2021-12-31 23:59:59"));
		EventDto item3 = new EventDto(3, "test_event_3", dateFormat.parse("2021-01-01 01:01:01"));
		
		testData.add(item1);
		testData.add(item2);
		testData.add(item3);
		
		return testData;
	}
}
