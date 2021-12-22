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

package exastro.Exastro_Days_Tokyo.event_user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import exastro.Exastro_Days_Tokyo.event_user.repository.EventRepository;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDto;

public abstract class BaseEventService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected EventRepository repository;
	
	public BaseEventService() {
		
	}

	public List<EventDto> getEvent() {

		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		List<EventDto> eventList = null;
		
		try {
			eventList = repository.getEvent()
					.stream()
					.map(e -> new EventDto(e.getEventId(), e.getEventName(), e.getEventDate()))
					.collect(Collectors.toList());
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventList;
	}

}
