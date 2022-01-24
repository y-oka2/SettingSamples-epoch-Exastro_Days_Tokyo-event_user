package exastro.Exastro_Days_Tokyo.event_user.service.dto;

import java.util.Date;

public class SeminarDetailDto extends SeminarDto{

//	登壇者ID
	private int speakerId;
	
//	セミナー概要
	private String seminarOverview;

//	定員
	private int capacity;

//	終了時刻は値としてもたない？

	
	public SeminarDetailDto(int seminarId, String seminarName, int blockId, String blockName, Date startDatetime ,
			int speakerId, String seminarOverview, int capacity){
		super(seminarId, seminarName, blockId, blockName, startDatetime);
		this.speakerId = speakerId;
		this.seminarOverview = seminarOverview;
		this.capacity = capacity;
	}

	public int getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(int speakerId) {
		this.speakerId = speakerId;
	}

	public String getSeminarOverview() {
		return seminarOverview;
	}

	public void setSeminarOverview(String seminarOverview) {
		this.seminarOverview = seminarOverview;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}

