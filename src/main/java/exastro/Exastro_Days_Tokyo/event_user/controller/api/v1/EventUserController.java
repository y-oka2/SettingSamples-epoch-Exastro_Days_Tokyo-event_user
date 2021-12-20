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

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.EventDetailForm;
import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.EventTimetableForm;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDetailDto;

@RestController
@RequestMapping("/api/v1/event")
public class EventUserController extends BaseEventController {
	
	public EventUserController() {
		
	}
	
	@GetMapping("/{event_id}")
	public EventDetailForm eventDetail(@PathVariable(value = "event_id") @Validated int event_id) {
		
		EventDetailForm eventDetail = null;
		
		try {
			EventDetailDto eventDetailDto = service.getEventDetail(event_id);
			eventDetail = new EventDetailForm(eventDetailDto.getEventId(), eventDetailDto.getEventName(),
					eventDetailDto.getEventOverview(), eventDetailDto.getEventDate(), eventDetailDto.getEventVenue(),
					eventDetailDto.getSpeakerIDs());
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventDetail;
	}
	
	@GetMapping("/{event_id}/timetable")
	public EventTimetableForm eventTimetable(@PathVariable(value = "event_id") @Validated int event_id) {
		
		EventTimetableForm eventTimetableData = null;
		
		try {
			EventDetailDto eventDetailDto = service.getEventDetail(event_id);
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventTimetableData;
	}

}
