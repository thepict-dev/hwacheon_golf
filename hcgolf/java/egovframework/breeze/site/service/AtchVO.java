package egovframework.breeze.site.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class AtchVO extends BoardVO {
	
	private static final long serialVersionUID = 1L;
	
	private String rownum;			// 번호
	private String atchId;			// 첨부파일 아이디
	private String atchTitle;		// 첨부파일 제목
	private String atchContent;		// 첨부파일 내용
	private String atchFileId;		// 첨부파일 파일 아이디
	private String regId;			// 등록자
	private String regDate;			// 등록일
	private String updId;			// 최종수정자
	private String updDate;			// 최종수정일
	private String useFlag;			// 사용여부
	private String command;			// 등록/수정 flag
	
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getAtchId() {
		return atchId;
	}
	public void setAtchId(String atchId) {
		this.atchId = atchId;
	}
	public String getAtchTitle() {
		return atchTitle;
	}
	public void setAtchTitle(String atchTitle) {
		this.atchTitle = atchTitle;
	}
	public String getAtchContent() {
		return atchContent;
	}
	public void setAtchContent(String atchContent) {
		this.atchContent = atchContent;
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
	
}
