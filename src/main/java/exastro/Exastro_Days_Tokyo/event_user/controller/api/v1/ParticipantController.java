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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form.ParticipantForm;
import exastro.Exastro_Days_Tokyo.event_user.service.dto.ParticipantDto;

@RestController
@RequestMapping("/api/v1/participant")
public class ParticipantController extends BaseParticipantController{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//セミナー参加人数確認
	@GetMapping(value = "/count",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public long countParticipant(@RequestParam ("seminar_id") int seminarId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		Integer count = null;
		
		try{
			//セミナー参加人数を取得しリターン
			count = service.countParticipant(seminarId);
			return count;
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
	}
	
	//申込済みセミナー確認
	@GetMapping(value = "",
			produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ParticipantForm> getParticipant(@RequestParam("user_id") String userId, @RequestParam("kind_of_sso") String kindOfSso) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		List<ParticipantForm> participantList = null;
		
		try{
			//申込済みのセミナーidをリターン
			participantList = service.getParticipant(userId, kindOfSso)
					.stream()
					.map(p -> new ParticipantForm(p.getSeminarId(), p.getParticipantId(), p.getUserId(), p.getUserName(),
							p.getKindOfSso(), p.getRegisteredDate(), p.isDeleteFlag()))
					.collect(Collectors.toList());
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
		return participantList;
	}
	
	//参加者登録
	@PostMapping(
				value = "",
				consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE )
	public void saveParticipant(@RequestBody ParticipantForm participantForm) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		ParticipantDto participantDto = null;
		
		try{
			Date registerdDate = new Date(); // now
			//FormからDtoインスタンスを作成
			participantDto = new ParticipantDto(participantForm.getSeminarId(),
					participantForm.getUserId(), participantForm.getUserName(), participantForm.getKindOfSso(),
					registerdDate);
			service.saveParticipant(participantDto);
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
			throw e;
		}
	}

}
