package egovframework.golf.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class EventVO extends BoardVO {

	private static final long serialVersionUID = 1L;

    /** 번호 */
	private String rownum;
    /** 첨부파일 */
	private String atchFileId;
    /** 등록자 ID */
	private String regId;
    /** 등록일 */
	private String regDate;
    /** 수정자 ID */
	private String updId;
    /** 수정일 */
	private String updDate;
    /** 사용여부 FLAG */
	private String useFlag;
    /** 등록/수정 FLAG */
	private String command;
    /** 첨부파일 순서 */
	private String fileSn;

	//일정 안내
	/** 일정 ID */
	private String scheduleId;
	/** 제목 */
	private String title;
	/** 작성자 */
	private String ntcrNm;
	/** 달력 표시 컬러 */
	private String calColor;
	/** 장소 */
	private String place;
	/** 시작일 */
	private String startDate;
	/** 종료일 */
	private String endDate;
	/** 내용 */
	private String content;
	/** 기준일 */
	private String date;

	//행사
	/** 행사 ID */
	private String eventId;
	/** 행사일 */
	private String eventDate;
	/** 행사시간 */
	private String eventTime;
	/** 오전 행사 진행 여부 */
	private String amUseFlag;
	/** 오전 신청그룹 */
	private String amApply;
	/** 오전 대기그룹 */
	private String amSpare;
	/** 오후 행사 진행 여부 */
	private String pmUseFlag;
	/** 오후 신청그룹 */
	private String pmApply;
	/** 오후 대기그룹 */
	private String pmSpare;
	/** 휴대폰번호 */
	private String phone;
	/** 행사 플래그 */
	private String eventFlag;
	/** 오전,오후 플래그 (오전-am, 오후-pm)  */
	private String ampm;
	/** 행사 시간 ID */
	private String eventTimeId;
	/** 행사게시일 */
	private String openDate;

	//단체해설 시간
	/** 시간 */
	private String time;
	/** 시간 사용여부 플래그 */
	private String timeFlag;
	/** 단체해설 시간체크 */
	private String eventTimeChk;

	//신청자
	/** 신청자 ID */
	private String applyId;
	/** 신청자 이름 */
	private String applicant;
	/** 신청자 나이 */
	private String age;
	/** 신청자 이메일 */
	private String email;
	/** 신청자 주소 */
	private String address;
	/** 참가자 이름 */
	private String participant;
	/** 참가자 성인 수 */
	private String adultNum;
	/** 참가자 청소년 수 */
	private String youthNum;
	/** 참가자 비고 */
	private String remark;
	/** 점심식사 선택 플래그 */
	private String lunchFlag;
	/** 상태 */
	private String statusFlag;

    /** 행사 count */
	private int eventCount;
    /** 신청자 수 */
	private int applyCnt;
	/** 토요의병놀이마당 오전 신청자 수  */
	private String amApplyCnt;
	/** 토요의병놀이마당 오후 신청자 수  */
	private String pmApplyCnt;

	/** 엑셀 플래그  */
	private String excelFlag;

	/** 수정 시 행사 일자 비교를 위해  */
	private String eventDate2;
	
	/** 사용자 단체 행사 신청 cnt */
	private String timeCnt; 
	
	/** 정원 */
	private String limitCnt;
	
	/** 대기자 */
	private String waitCnt;
	
	/** 신청 가능 여부 */
	private String applyFlag;
	
	// 문자 메시지
	private String smsTitle;
	private String smsMsg;
	// 착신자 정보 이름과 번호(이름^번호)
	private String destInfo;
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNtcrNm() {
		return ntcrNm;
	}
	public void setNtcrNm(String ntcrNm) {
		this.ntcrNm = ntcrNm;
	}
	public String getCalColor() {
		return calColor;
	}
	public void setCalColor(String calColor) {
		this.calColor = calColor;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getAmUseFlag() {
		return amUseFlag;
	}
	public void setAmUseFlag(String amUseFlag) {
		this.amUseFlag = amUseFlag;
	}
	public String getAmApply() {
		return amApply;
	}
	public void setAmApply(String amApply) {
		this.amApply = amApply;
	}
	public String getAmSpare() {
		return amSpare;
	}
	public void setAmSpare(String amSpare) {
		this.amSpare = amSpare;
	}
	public String getPmUseFlag() {
		return pmUseFlag;
	}
	public void setPmUseFlag(String pmUseFlag) {
		this.pmUseFlag = pmUseFlag;
	}
	public String getPmApply() {
		return pmApply;
	}
	public void setPmApply(String pmApply) {
		this.pmApply = pmApply;
	}
	public String getPmSpare() {
		return pmSpare;
	}
	public void setPmSpare(String pmSpare) {
		this.pmSpare = pmSpare;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEventFlag() {
		return eventFlag;
	}
	public void setEventFlag(String eventFlag) {
		this.eventFlag = eventFlag;
	}
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public String getEventTimeId() {
		return eventTimeId;
	}
	public void setEventTimeId(String eventTimeId) {
		this.eventTimeId = eventTimeId;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}
	public String getEventTimeChk() {
		return eventTimeChk;
	}
	public void setEventTimeChk(String eventTimeChk) {
		this.eventTimeChk = eventTimeChk;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public String getAdultNum() {
		return adultNum;
	}
	public void setAdultNum(String adultNum) {
		this.adultNum = adultNum;
	}
	public String getYouthNum() {
		return youthNum;
	}
	public void setYouthNum(String youthNum) {
		this.youthNum = youthNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLunchFlag() {
		return lunchFlag;
	}
	public void setLunchFlag(String lunchFlag) {
		this.lunchFlag = lunchFlag;
	}
	public int getEventCount() {
		return eventCount;
	}
	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}
	public int getApplyCnt() {
		return applyCnt;
	}
	public void setApplyCnt(int applyCnt) {
		this.applyCnt = applyCnt;
	}
	public String getAmApplyCnt() {
		return amApplyCnt;
	}
	public void setAmApplyCnt(String amApplyCnt) {
		this.amApplyCnt = amApplyCnt;
	}
	public String getPmApplyCnt() {
		return pmApplyCnt;
	}
	public void setPmApplyCnt(String pmApplyCnt) {
		this.pmApplyCnt = pmApplyCnt;
	}
	public String getExcelFlag() {
		return excelFlag;
	}
	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}
	public String getEventDate2() {
		return eventDate2;
	}
	public void setEventDate2(String eventDate2) {
		this.eventDate2 = eventDate2;
	}
	public String getTimeCnt() {
		return timeCnt;
	}
	public void setTimeCnt(String timeCnt) {
		this.timeCnt = timeCnt;
	}
	public String getLimitCnt() {
		return limitCnt;
	}
	public void setLimitCnt(String limitCnt) {
		this.limitCnt = limitCnt;
	}
	public String getWaitCnt() {
		return waitCnt;
	}
	public void setWaitCnt(String waitCnt) {
		this.waitCnt = waitCnt;
	}
	public String getApplyFlag() {
		return applyFlag;
	}
	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getSmsTitle() {
		return smsTitle;
	}
	public void setSmsTitle(String smsTitle) {
		this.smsTitle = smsTitle;
	}
	public String getSmsMsg() {
		return smsMsg;
	}
	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}
	public String getDestInfo() {
		return destInfo;
	}
	public void setDestInfo(String destInfo) {
		this.destInfo = destInfo;
	}
	
}
