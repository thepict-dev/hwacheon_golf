package egovframework.breeze.secure.service;

public class DefaultVO {

	private String defaultId;	// 기본설정 ID
	private String defaultName;	// 고객명
	private String logoFileId;	// 첨부파일 ID 
	private String logoFilePath;// 첨부파일 경로 
	private String ipLimitFlag;	// 접근제한 사용여부 
	
	private String regId;		// 등록자
	private String regDate;		// 등록일
	private String updId;		// 최종수정자
	private String updDate;		// 최종수정일
	
	private String returnUrl;	// 리턴페이지
	
	
	public String getDefaultId() {
		return defaultId;
	}
	public void setDefaultId(String defaultId) {
		this.defaultId = defaultId;
	}
	public String getDefaultName() {
		return defaultName;
	}
	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}
	public String getLogoFileId() {
		return logoFileId;
	}
	public void setLogoFileId(String logoFileId) {
		this.logoFileId = logoFileId;
	}
	public String getLogoFilePath() {
		return logoFilePath;
	}
	public void setLogoFilePath(String logoFilePath) {
		this.logoFilePath = logoFilePath;
	}
	public String getIpLimitFlag() {
		return ipLimitFlag;
	}
	public void setIpLimitFlag(String ipLimitFlag) {
		this.ipLimitFlag = ipLimitFlag;
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
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
}
