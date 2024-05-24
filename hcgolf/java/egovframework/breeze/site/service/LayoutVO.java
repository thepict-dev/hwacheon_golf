package egovframework.breeze.site.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class LayoutVO extends BoardVO {

	private static final long serialVersionUID = 1L;
	
	private String rownum;		// 번호
	private String layoutId;	// 레이아웃Id
	private String layoutName;	// 레이아웃명
	private String layoutHead;	// head 내용
	private String layoutHeader;// header 내용
	private String layoutFooter;// footer 내용
	private String layoutDescription;	// 레이아웃 설명
	private String regId;		// 등록자
	private String regDate;		// 등록일
	private String updId;		// 최종수정자
	private String updDate;		// 최종수정일
	private String useFlag;		// 사용여부
	private String command;		// 등록/수정 flag
	
	private String bakLayoutId;	// 백업 레이아웃 Id
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
	public String getLayoutName() {
		return layoutName;
	}
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	public String getLayoutHead() {
		return layoutHead;
	}
	public void setLayoutHead(String layoutHead) {
		this.layoutHead = layoutHead;
	}
	public String getLayoutHeader() {
		return layoutHeader;
	}
	public void setLayoutHeader(String layoutHeader) {
		this.layoutHeader = layoutHeader;
	}
	public String getLayoutFooter() {
		return layoutFooter;
	}
	public void setLayoutFooter(String layoutFooter) {
		this.layoutFooter = layoutFooter;
	}
	public String getLayoutDescription() {
		return layoutDescription;
	}
	public void setLayoutDescription(String layoutDescription) {
		this.layoutDescription = layoutDescription;
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
	public String getBakLayoutId() {
		return bakLayoutId;
	}
	public void setBakLayoutId(String bakLayoutId) {
		this.bakLayoutId = bakLayoutId;
	}
	
}
