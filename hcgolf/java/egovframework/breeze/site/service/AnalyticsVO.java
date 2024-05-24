package egovframework.breeze.site.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class AnalyticsVO extends BoardVO {
	
	private static final long serialVersionUID = 1L;
	
	private String rownum;			// 번호
	private String analyticsId;		// 애널리틱스Id
	private String analyticsName;	// 애널리틱스명
	private String analyticsHead;	// head 내용
	private String analyticsBody;	// body 내용
	private String regId;			// 등록자
	private String regDate;			// 등록일
	private String updId;			// 최종수정자
	private String updDate;			// 최종수정일
	private String useFlag;			// 사용여부
	private String command;			// 등록/수정 flag
	
	private String bakAnalyticsId;  // 백업 애널리틱스 Id
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getAnalyticsId() {
		return analyticsId;
	}
	public void setAnalyticsId(String analyticsId) {
		this.analyticsId = analyticsId;
	}
	public String getAnalyticsName() {
		return analyticsName;
	}
	public void setAnalyticsName(String analyticsName) {
		this.analyticsName = analyticsName;
	}
	public String getAnalyticsHead() {
		return analyticsHead;
	}
	public void setAnalyticsHead(String analyticsHead) {
		this.analyticsHead = analyticsHead;
	}
	public String getAnalyticsBody() {
		return analyticsBody;
	}
	public void setAnalyticsBody(String analyticsBody) {
		this.analyticsBody = analyticsBody;
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
	public String getBakAnalyticsId() {
		return bakAnalyticsId;
	}
	public void setBakAnalyticsId(String bakAnalyticsId) {
		this.bakAnalyticsId = bakAnalyticsId;
	}
	
	
}
