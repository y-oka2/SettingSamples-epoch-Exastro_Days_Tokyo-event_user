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

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.EventDetailForm;
import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.ParticipantForm;
import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.SeminarForm;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDetailDto;

@RestController
@RequestMapping("/api/v1/event")
public class EventUserController extends BaseEventController {
	
	public EventUserController() {
	}
	
	@GetMapping("/{eventId}")
	public EventDetailForm eventDetail(@PathVariable(value = "eventId") @Validated int eventId) {
		
		EventDetailForm eventDetail = null;
		
		try {
			EventDetailDto eventDetailDto = service.getEventDetail(eventId);
			eventDetail = new EventDetailForm(eventDetailDto.getEventId(), eventDetailDto.getEventName(),
					eventDetailDto.getEventOverview(), eventDetailDto.getEventDate(), eventDetailDto.getEventVenue(),
					eventDetailDto.getSpeakerIDs());
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
		
		return eventDetail;
	}
	
	@GetMapping("/{eventId}/timetable")
	public List<SeminarForm> eventTimetable(@PathVariable(value = "eventId") @Validated int eventId,
			@RequestParam ("user_id") String userId, @RequestParam ("kind_of_sso") String kindOfSso, @RequestParam ("seminar_id") int seminarId) {
		
		List<SeminarForm> seminarList = null;
		List<ParticipantForm> participantList = null;

		try {
			// セミナーリストを取得
			seminarList = seminarService.getSeminar(eventId)
					.stream()
					.map(s -> new SeminarForm(s.getSeminarId(), s.getSeminarName(), s.getBlockId(), s.getBlockName(), s.getStartDatetime()))
					.collect(Collectors.toList());
			
			// 申込済みセミナーの参加者情報を取得
			participantList = participantService.getParticipant(userId, kindOfSso)
					.stream()
					.map(p -> new ParticipantForm(p.getSeminarId(), p.getParticipantId(), p.getUserId(), p.getUserName(),
							p.getKindOfSso(), p.getRegisteredDate(), p.isDeleteFlag()))
					.collect(Collectors.toList());
			
			// 申込済みのセミナーかどうか判定
			for (SeminarForm list : seminarList) {
				for (ParticipantForm list2 : participantList) {
					if (String.valueOf(list.getSeminarId()).contains(String.valueOf(list2.getSeminarId()))){
						list.setParticipated(true);
					}
				}
			}
			
			// セミナーが定員上限に達しているか判定
			for (SeminarForm list : seminarList) {
				
				// セミナーの定員を取得
				Integer capacity =  seminarService.getSeminarDetail(list.getSeminarId()).getCapacity();
				// セミナー参加人数を取得
				long countParticipant = participantService.countParticipant(list.getSeminarId());
				
				if (capacity == null) {
					list.setCapacityOver(false);
				}else if (countParticipant >= capacity) {
					System.out.println(capacity);
					list.setCapacityOver(true);	
				}
			}
			System.out.println(seminarList);
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
		return seminarList;
	}

}
