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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import exastro.Exastro_Days_Tokyo.event_user.service.ParticipantService;

public class BaseParticipantController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ParticipantService service;
	
	//参加者登録解除
	@DeleteMapping(
				value = "",
				produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteParticipant(@RequestParam("user_id") String userId, @RequestParam("kind_of_sso") String kindOfSso, @RequestParam("seminar_id") int seminarId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		try{
			service.deleteParticipant(userId, kindOfSso, seminarId);
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
	}

}
