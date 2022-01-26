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

package exastro.Exastro_Days_Tokyo.event_user.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import exastro.Exastro_Days_Tokyo.event_user.repository.config.ConnectionConfig;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.CountVO;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.ParticipantVO;

@Repository
public class ParticipantRepository extends BaseRepository {
	
	@Autowired
	public ParticipantRepository(@Qualifier("configParticipant") ConnectionConfig connectionConfig,
			RestTemplate restTemplate) {
		this.connectionConfig = connectionConfig;
		this.restTemplate = restTemplate;
	}
	
	// セミナー参加人数確認
	public CountVO countParticipant(int seminarId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/participant/count";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		CountVO resBody= null;
		try {
		
			logger.debug("restTemplate.getForEntity [apiUrl: " + apiUrl + "], [seminarId: " + seminarId + "]");
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
			
			builder.queryParam("seminar_id", seminarId);
			
			String uri = builder.toUriString();
			
			resBody = restTemplate.getForObject(uri, CountVO.class);
			
			return resBody;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	// 申込済みセミナー確認
	public List<ParticipantVO> getParticipant(String userId, String kindOfSso){
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/participant";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		ParticipantVO[] resBody = null;
		try {
			
			logger.debug("restTemplate.getForEntity [apiUrl: " + apiUrl + "]");
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
			
			builder.queryParam("user_id", userId)
				.queryParam("kind_of_sso", kindOfSso);
			
			String uri = builder.toUriString();
			
			resBody = restTemplate.getForObject(uri, ParticipantVO[].class);
			
			return Arrays.asList(resBody);
		}
		catch(Exception e) {
			throw e;
		}
	}

	// 参加者登録
	public void saveParticipant(ParticipantVO participantVo) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/participant";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		try {
			
			logger.debug("restTemplate.postForEntity [apiUrl: " + apiUrl + "]");
			restTemplate.postForObject(apiUrl, participantVo, ParticipantVO.class);

		}
		catch(Exception e) {
			throw e;
		}
	}
	
	// 参加者登録解除
	public void deleteParticipant(String userId, String kindOfSso, int seminarId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/participant";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		try {
			
			logger.debug("restTemplate.postForEntity [apiUrl: " + apiUrl + "]");
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
			
			builder.queryParam("user_id", userId)
				.queryParam("kind_of_sso", kindOfSso)
				.queryParam("seminar_id", seminarId);
			
			String uri = builder.toUriString();
			
			restTemplate.delete(uri, userId, kindOfSso, seminarId);

		}
		catch(Exception e) {
			throw e;
		}
	}

}
