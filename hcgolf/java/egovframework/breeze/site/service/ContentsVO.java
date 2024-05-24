package egovframework.breeze.site.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class ContentsVO extends BoardVO {

	private static final long serialVersionUID = 1L;
	
	private String rownum;			// 번호
	private String contentsId;		// 컨텐츠 id
	private String contentsName;	// 컨텐츠명
	private String contentsType;	// 컨텐츠 타입: CONS(소스코드)/BBS(게시판)/URL(URL링크)
	
	private String layoutId;		// 레이아웃 ID
	
	private String contentsStyle;	// 컨텐츠 스타일: css, js, 페이지(jsp)
	private String contentsContent;	// 컨텐츠 내용
	
	private String bbsId;			// 게시판ID
	private String bbsHeader;		// 게시판 - 공통상단
	private String bbsFooter;		// 게시판 - 공통하단

	private String url;				// url 링크
	
	private String regId;			// 등록자
	private String regDate;			// 등록일
	private String updId;			// 최종수정자
	private String updDate;			// 최종수정일
	private String useFlag;			// 사용여부
	private String command;			// 등록/수정 flag
	
	private String bakContentsId;	// 백업 컨텐츠 Id

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getContentsId() {
		return contentsId;
	}

	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	public String getContentsName() {
		return contentsName;
	}

	public void setContentsName(String contentsName) {
		this.contentsName = contentsName;
	}

	public String getContentsType() {
		return contentsType;
	}

	public void setContentsType(String contentsType) {
		this.contentsType = contentsType;
	}

	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public String getContentsStyle() {
		return contentsStyle;
	}

	public void setContentsStyle(String contentsStyle) {
		this.contentsStyle = contentsStyle;
	}

	public String getContentsContent() {
		return contentsContent;
	}

	public void setContentsContent(String contentsContent) {
		this.contentsContent = contentsContent;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsHeader() {
		return bbsHeader;
	}

	public void setBbsHeader(String bbsHeader) {
		this.bbsHeader = bbsHeader;
	}

	public String getBbsFooter() {
		return bbsFooter;
	}

	public void setBbsFooter(String bbsFooter) {
		this.bbsFooter = bbsFooter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getBakContentsId() {
		return bakContentsId;
	}

	public void setBakContentsId(String bakContentsId) {
		this.bakContentsId = bakContentsId;
	}
	
}
