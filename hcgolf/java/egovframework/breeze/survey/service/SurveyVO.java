package egovframework.breeze.survey.service;

import java.util.List;

import egovframework.com.cop.bbs.service.BoardVO;

public class SurveyVO extends BoardVO{

	private static final long serialVersionUID = 1L;
	
	public int rownum;				// 번호
	public String regId;			// 등록자
	public String regDate;			// 등록일
	public String updId;			// 수정자
	public String updDate;			// 수정일
	public String useFlag;			// 사용여부

	public String surveyId;			// 설문 id
	public String surveyTitle;		// 설문명
	public String surveyDesc;		// 설문 설명
	public String surveyOrder;		// 게시판상에서의 설문 순서
	
	public String startDate;		// 설문조사 시작일
	public String endDate;			// 설문조사 종료일
	
	public String openFlag;			// 공개 여부
	public String openResultFlag;	// 설문 결과 공개 여부
	
	public String limitRspdentMax;	// 참여자 수 제한
	public String limitRspdentDup;	// 참여자 중복 참여 제한
	
	public String startDateDiff; 	// 시작일로부터의 날 수
	public String endDateDiff; 		// 종료일로부터의 날 수
	
	public String skinId;			// 스킨 ID
	public int numRspdent;			// 참여 인원

	public String questionId;		// 질문 ID
	public String questionTitle;	// 질문 제목
	public String questionDesc;		// 질문 설명
	public String questionCode;		// 질문 번호
	public String questionType;		// 질문 종류
	public String useOtherSelectFlag; // 기타란 이용 여부

	public String answerId;			// 선택지 ID
	public String answerTitle;		// 선택지 제목

	public String rspdentId;		// 응답자 ID
	public String mberId;			// 응답자 회원 ID

	public int resultId;			// 설문 응답 ID
	public String subjectiveCn;		// 주관식 답변
    
    private String searchCnd = "";
    private String searchWrd = "";
    
    
	private String command;			// 등록/수정 flag
	
	// 조회
	private List<SurveyVO> questions;	// 설문조사의 질문 리스트
	private List<SurveyVO> answers;		// 질문의 선택지 리스트
	private List<SurveyVO> results;		// 설문조사 결과 리스트
	
	
	public int getNumRspdent() {
		return numRspdent;
	}
	public void setNumRspdent(int numRspdent) {
		this.numRspdent = numRspdent;
	}
	public List<SurveyVO> getResults() {
		return results;
	}
	public void setResults(List<SurveyVO> results) {
		this.results = results;
	}
	public List<SurveyVO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<SurveyVO> questions) {
		this.questions = questions;
	}
	public List<SurveyVO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<SurveyVO> answers) {
		this.answers = answers;
	}
	public int getRounum() {
		return rownum;
	}
	public void setRounum(int rownum) {
		this.rownum = rownum;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
	public String getSurveyDesc() {
		return surveyDesc;
	}
	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}
	public String getSurveyOrder() {
		return surveyOrder;
	}
	public void setSurveyOrder(String surveyOrder) {
		this.surveyOrder = surveyOrder;
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
	public String getOpenFlag() {
		return openFlag;
	}
	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}
	public String getOpenResultFlag() {
		return openResultFlag;
	}
	public void setOpenResultFlag(String openResultFlag) {
		this.openResultFlag = openResultFlag;
	}
	public String getLimitRspdentMax() {
		return limitRspdentMax;
	}
	public void setLimitRspdentMax(String limitRspdentMax) {
		this.limitRspdentMax = limitRspdentMax;
	}
	public String getLimitRspdentDup() {
		return limitRspdentDup;
	}
	public void setLimitRspdentDup(String limitRspdentDup) {
		this.limitRspdentDup = limitRspdentDup;
	}
	public String getStartDateDiff() {
		return startDateDiff;
	}
	public void setStartDateDiff(String startDateDiff) {
		this.startDateDiff = startDateDiff;
	}
	public String getEndDateDiff() {
		return endDateDiff;
	}
	public void setEndDateDiff(String endDateDiff) {
		this.endDateDiff = endDateDiff;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getUseOtherSelectFlag() {
		return useOtherSelectFlag;
	}
	public void setUseOtherSelectFlag(String useOtherSelectFlag) {
		this.useOtherSelectFlag = useOtherSelectFlag;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getAnswerTitle() {
		return answerTitle;
	}
	public void setAnswerTitle(String answerTitle) {
		this.answerTitle = answerTitle;
	}
	public String getRspdentId() {
		return rspdentId;
	}
	public void setRspdentId(String rspdentId) {
		this.rspdentId = rspdentId;
	}
	public String getMberId() {
		return mberId;
	}
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public String getSubjectiveCn() {
		return subjectiveCn;
	}
	public void setSubjectiveCn(String subjectiveCn) {
		this.subjectiveCn = subjectiveCn;
	}
	public String getSearchCnd() {
		return searchCnd;
	}
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}
	public String getSearchWrd() {
		return searchWrd;
	}
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	public String getSkinId() {
		return skinId;
	}
	public void setSkinId(String skinId) {
		this.skinId = skinId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
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
}