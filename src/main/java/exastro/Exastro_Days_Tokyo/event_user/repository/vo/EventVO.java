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

package exastro.Exastro_Days_Tokyo.event_user.repository.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventVO {
	
	@Getter
	@Setter
	@JsonProperty("event_id")
	private int eventId;
	
	@Getter
	@Setter
	@JsonProperty("event_name")
	private String eventName;
	
	@Getter
	@Setter
	@JsonProperty("event_date")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tokyo") // 起動オプションでTimeZone変える？
	private Date eventDate;

	public EventVO() {
		
	}

	public EventVO(int eventId, String eventName,Date eventDate) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
	}
}
