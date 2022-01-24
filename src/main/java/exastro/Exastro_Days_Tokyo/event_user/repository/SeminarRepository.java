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
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.SeminarDetailVO;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.SeminarVO;

@Repository
public class SeminarRepository extends BaseRepository {
	
	@Autowired
	public SeminarRepository(@Qualifier("configEvent") ConnectionConfig connectionConfig,
			RestTemplate restTemplate) {
		this.connectionConfig = connectionConfig;
		this.restTemplate = restTemplate;
	}
	
	public List<SeminarVO> getSeminar(int eventId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/seminar";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		SeminarVO[] resBody = null;
		try {
			
			logger.debug("restTemplate.getForEntity [apiUrl: " + apiUrl + "]");
//			resBody = restTemplate.getForObject(apiUrl, SeminarVO[].class, eventId);
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl);
			
			builder.queryParam("eventId", eventId);
			
			String uri = builder.toUriString();
			
			resBody = restTemplate.getForObject(uri, SeminarVO[].class);
			
			return Arrays.asList(resBody);
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public SeminarDetailVO getSeminarDetail(int seminarId) {
		
		logger.debug("method called. [ " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ]");
		
		String apiPath = "/api/v1/seminar/{seminarId}";
		String apiUrl = connectionConfig.buildBaseUri() + apiPath;
		
		SeminarDetailVO resBody = null;
		try {
			
			logger.debug("restTemplate.getForEntity [apiUrl: " + apiUrl + "], [seminarId: " + seminarId + "]");
			resBody = restTemplate.getForObject(apiUrl, SeminarDetailVO.class, seminarId);
			
			return resBody;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
