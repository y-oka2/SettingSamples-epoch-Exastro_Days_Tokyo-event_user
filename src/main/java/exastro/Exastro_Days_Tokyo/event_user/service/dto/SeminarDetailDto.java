package exastro.Exastro_Days_Tokyo.event_user.service.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SeminarDetailDto extends SeminarDto{

//	登壇者ID
	private Integer speakerId;
	
//	セミナー概要
	private String seminarOverview;

//	定員
	private Integer capacity;
	
	public SeminarDetailDto(int seminarId, String seminarName, int blockId, String blockName, Date startDatetime ,
			Integer speakerId, String seminarOverview, Integer capacity){
		super(seminarId, seminarName, blockId, blockName, startDatetime);
		this.speakerId = speakerId;
		this.seminarOverview = seminarOverview;
		this.capacity = capacity;
	}
	
}

