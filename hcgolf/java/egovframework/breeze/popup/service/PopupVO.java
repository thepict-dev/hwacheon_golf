package egovframework.breeze.popup.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class PopupVO extends BoardVO{

	private static final long serialVersionUID = 1L;
	
	private String rownum;		// 번호
	private String popupId;		// 팝업 ID
	private String popupTitle;	// 제목
	private String category;	// 구분
	private String startDate;	// 시작일
	private String endDate;		// 종료일
	private String viewFlag;	// 출력여부
	private String atchFileId;	// 첨부파일
	private String url;			// 링크
	private String target;		// 링크 타겟
	private String altText;		// 대체텍스트
	private String ir;			// ir
	private String sizeWidth;	// 창크기-너비
	private String sizeHeight;	// 창크기-높이
	private String viewWidth;	// 창위치-가로
	private String viewHeight;	// 창위치-세로
	private String flag;		// 플래그
	private String regId;		// 등록자
	private String regDate;		// 등록일
	private String updId;		// 최종수정자
	private String updDate;		// 최종수정일
	private String useFlag;		// 사용여부
	private int popupOrder;		// 표시 순서
	
	private String command;		// 등록/수정 flag
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getPopupId() {
		return popupId;
	}
	public void setPopupId(String popupId) {
		this.popupId = popupId;
	}
	public String getPopupTitle() {
		return popupTitle;
	}
	public void setPopupTitle(String popupTitle) {
		this.popupTitle = popupTitle;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getAltText() {
		return altText;
	}
	public void setAltText(String altText) {
		this.altText = altText;
	}
	public String getIr() {
		return ir;
	}
	public void setIr(String ir) {
		this.ir = ir;
	}
	public String getSizeWidth() {
		return sizeWidth;
	}
	public void setSizeWidth(String sizeWidth) {
		this.sizeWidth = sizeWidth;
	}
	public String getSizeHeight() {
		return sizeHeight;
	}
	public void setSizeHeight(String sizeHeight) {
		this.sizeHeight = sizeHeight;
	}
	public String getViewWidth() {
		return viewWidth;
	}
	public void setViewWidth(String viewWidth) {
		this.viewWidth = viewWidth;
	}
	public String getViewHeight() {
		return viewHeight;
	}
	public void setViewHeight(String viewHeight) {
		this.viewHeight = viewHeight;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public int getPopupOrder() {
		return popupOrder;
	}
	public void setPopupOrder(int popupOrder) {
		this.popupOrder = popupOrder;
	}
}
