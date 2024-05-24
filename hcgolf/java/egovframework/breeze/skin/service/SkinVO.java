package egovframework.breeze.skin.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class SkinVO extends BoardVO {
	
	private static final long serialVersionUID = 1L;
	
	private String rownum;		// 번호
	private String skinId;		// 스킨 id
	private String skinName;	// 스킨 이름
	private String skinList;	// 목록 스킨
	private String skinView;	// 뷰 스킨
	private String skinForm;	// 등록/수정 스킨
	private String skinType;
	private String regId;		// 등록자
	private String regDate;		// 등록일
	private String updId;		// 최종수정자
	private String updDate;		// 최종수정일
	private String useFlag;		// 사용여부
	private String command;		// 등록/수정 flag
	
	private String bakSkinId;	// 백업 스킨 Id

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getSkinId() {
		return skinId;
	}

	public void setSkinId(String skinId) {
		this.skinId = skinId;
	}

	public String getSkinName() {
		return skinName;
	}

	public void setSkinName(String skinName) {
		this.skinName = skinName;
	}

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public String getSkinList() {
		return skinList;
	}

	public void setSkinList(String skinList) {
		this.skinList = skinList;
	}

	public String getSkinView() {
		return skinView;
	}

	public void setSkinView(String skinView) {
		this.skinView = skinView;
	}

	public String getSkinForm() {
		return skinForm;
	}

	public void setSkinForm(String skinForm) {
		this.skinForm = skinForm;
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

	public String getBakSkinId() {
		return bakSkinId;
	}

	public void setBakSkinId(String bakSkinId) {
		this.bakSkinId = bakSkinId;
	}
	
	
}
