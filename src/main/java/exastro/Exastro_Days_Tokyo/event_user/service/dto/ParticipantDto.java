package exastro.Exastro_Days_Tokyo.event_user.service.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

	// セミナーID	
	private int seminarId;

	// 参加者ID
	private int participantId;
	
	// ユーザーID
	private String userId;

	// ユーザー名	
	private String userName;

	// SSO種別
	private String kindOfSso;
	
	// 登録日時
	private Date registeredDate;
	
	// 論削フラグ
	private boolean deleteFlag;

//参加者登録用コンストラクタ
	public ParticipantDto(int seminarId, String userId, String userName, String kindOfSso, Date registeredDate) {
		this.seminarId = seminarId;
		this.userId = userId;
		this.userName = userName;
		this.kindOfSso = kindOfSso;
		this.registeredDate = registeredDate;
	}
	
//参加者登録解除用コンストラクタ
	public ParticipantDto(int seminarId, int participantId, String userId, String userName, String kindOfSso,
			Date registeredDate) {
		this.seminarId = seminarId;
		this.participantId = participantId;
		this.userId = userId;
		this.userName = userName;
		this.kindOfSso = kindOfSso;
		this.registeredDate = registeredDate;
	}
	
}