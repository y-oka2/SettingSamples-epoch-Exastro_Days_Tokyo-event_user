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

import java.sql.Timestamp;

public class EventForm {

	private int eventId;
	private String eventName;
	private Timestamp eventDate;

	public EventForm() {
		
	}

	public EventForm(int eventId, String eventName, Timestamp eventDate) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public Timestamp getEventDate() {
		return eventDate;
	}
	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}
}

