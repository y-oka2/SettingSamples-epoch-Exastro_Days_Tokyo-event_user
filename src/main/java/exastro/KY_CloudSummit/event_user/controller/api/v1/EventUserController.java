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

package exastro.KY_CloudSummit.event_user.controller.api.v1;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import exastro.KY_CloudSummit.event_user.controller.api.v1.form.EventDetailForm;
import exastro.KY_CloudSummit.event_user.service.dto.EventDetailDto;

public class EventUserController extends BaseEventController {
	
	public EventUserController() {
		
	}
	
	@GetMapping("/{event_id}")
	public EventDetailForm eventDetail(@PathVariable(value = "event_id") @Validated int event_id) {
		
		EventDetailForm eventDetail = null;
		
		try {
			EventDetailDto eventDetailDto = service.getEventDetail(event_id);
			eventDetail = new EventDetailForm();
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventDetail;
	}

}
