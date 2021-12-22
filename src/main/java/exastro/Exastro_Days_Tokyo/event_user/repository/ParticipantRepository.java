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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import exastro.Exastro_Days_Tokyo.event_user.repository.config.ConnectionConfig;
import exastro.Exastro_Days_Tokyo.event_user.repository.vo.ParticipantVO;

@Repository
public class ParticipantRepository extends BaseRepository {
	
	@Autowired
	public ParticipantRepository(@Qualifier("configParticipant") ConnectionConfig connectionConfig,
			RestTemplate restTemplate) {
		this.connectionConfig = connectionConfig;
		this.restTemplate = restTemplate;
	}

	public List<ParticipantVO> getParticipant() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
