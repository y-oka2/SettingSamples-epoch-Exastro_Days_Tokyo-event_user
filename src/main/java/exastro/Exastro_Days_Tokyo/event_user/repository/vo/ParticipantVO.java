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

package exastro.Exastro_Days_Tokyo.event_user.repository.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ParticipantVO {

	private int seminarId;
	private int participantId;
	private String userId;
	private String userName;
	private String kindOfSso;
	private Timestamp registeredDate;
	private boolean deleteFlag;
	
	public ParticipantVO(String userId, String kindOfSso) {
		this.userId = userId;
		this.kindOfSso = kindOfSso;
	}
	
	//参加者登録用コンストラクタ
	public ParticipantVO(int seminarId, String userId, String userName, String kindOfSso,
			Timestamp registeredDate) {
		this.seminarId = seminarId;
		this.userId = userId;
		this.userName = userName;
		this.kindOfSso = kindOfSso;
		this.registeredDate = registeredDate;
		this.deleteFlag = false;
	}
}