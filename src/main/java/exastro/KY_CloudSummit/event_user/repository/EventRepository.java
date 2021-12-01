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

package exastro.KY_CloudSummit.event_user.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import exastro.KY_CloudSummit.event_user.repository.vo.EventDetailVO;
import exastro.KY_CloudSummit.event_user.repository.vo.EventVO;

@ConfigurationProperties(prefix = "resource.event")
@Repository
public class EventRepository extends BaseRepository {
	
	public EventRepository(RestTemplate restTemplate) {
		super(restTemplate);
	}
	
	public List<EventVO> getEvent() {
		
		String apiPath = "/api/vi/event";
		String apiUrl = buildBaseUri() + apiPath;
		
		EventVO[] resBody = null;
		try {
			ResponseEntity<EventVO[]> response = restTemplate.getForEntity(apiUrl, EventVO[].class);
			resBody = response.getBody();
		}
		catch(Exception e) {
			throw e;
		}
		
		return Arrays.asList(resBody);
	}
	
	public EventDetailVO getEventDetail(int eventId) {
		return null;
	}

}
