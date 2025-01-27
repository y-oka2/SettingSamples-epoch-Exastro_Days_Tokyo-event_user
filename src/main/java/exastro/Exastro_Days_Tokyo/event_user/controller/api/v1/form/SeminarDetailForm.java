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

package exastro.Exastro_Days_Tokyo.event_user.controller.api.v1.form;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeminarDetailForm extends SeminarForm{

//	登壇者ID
	private Integer speakerId;
	
//	セミナー概要
	private String seminarOverview;

//	定員
	private Integer capacity;
	
	public SeminarDetailForm(int seminarId, String seminarName, int blockId, String blockName, Date startDatetime ,
			Integer speakerId, String seminarOverview, Integer capacity){
		super(seminarId, seminarName, blockId, blockName, startDatetime);
		this.speakerId = speakerId;
		this.seminarOverview = seminarOverview;
		this.capacity = capacity;
	}	
}

