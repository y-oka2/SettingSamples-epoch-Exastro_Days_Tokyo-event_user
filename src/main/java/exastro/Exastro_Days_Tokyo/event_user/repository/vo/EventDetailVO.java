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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class EventDetailVO {
	
	@Getter
	@Setter
	private int eventId;
	
	@Getter
	@Setter
	private String eventName;
	
	@Getter
	@Setter
	private String eventOverview;
	
	@Getter
	@Setter
	private Date eventDate;
	
	@Getter
	@Setter
	private String eventVenue;
	
	@Getter
	@Setter
	private boolean deleteFlag;
	
	@Getter
	@Setter
	private List<Integer> speakerIDs;

	public EventDetailVO() {
		
	}

	public EventDetailVO(int eventId, String eventName, 
			String eventOverview, Date eventDate, String eventVenue, List<Integer> speakerIDs) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventOverview = eventOverview;
		this.eventDate = eventDate;
		this.eventVenue = eventVenue;
		this.speakerIDs = new ArrayList<Integer>(speakerIDs);
	}
}
