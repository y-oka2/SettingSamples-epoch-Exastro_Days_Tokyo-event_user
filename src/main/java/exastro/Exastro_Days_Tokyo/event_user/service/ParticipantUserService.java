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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exastro.Exastro_Days_Tokyo.event_user.repository.ParticipantRepository;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.EventDetailDto;

@Service
public class ParticipantUserService implements ParticipantService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ParticipantRepository repository;
	
	public ParticipantUserService() {
		
	}

	public EventDetailDto getEventDetail(int event_id) {

		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		EventDetailDto eventDetail = null;
		
		try {
			repository.getParticipant();
		}
		catch(Exception e) {
			throw e;
		}
		
		return eventDetail;
	}

}
