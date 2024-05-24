package egovframework.breeze.admin.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class AdminVO extends BoardVO {

	private static final long serialVersionUID = 1L;
	
	private String adminId;			// 아이디
	private String adminPw;			// 비밀번호
	private String adminName;		// 이름
	private String adminRole;		// 권한
	private String regId;			// 등록자
	private String regDate;			// 등록일
	private String updId;			// 최종수정자
	private String updDate;			// 최종수정일
	private String useFlag;			// 상태 (승인: Y / 삭제: N / 잠금: L / 대기: S / 만료: E) 2020.09.02 대기, 만료 상태 추가
	private String lastIp;			// 최종 로그인 IP
	private String tryIp;			// 최근 로그인 시도 IP
	//private int failCnt;			// 로그인 실패 횟수
	//private String lockDate;		// 잠금 일시
	private String loginYN;			// 로그인 성공 여부
	private String command;			// 등록/수정 flag
	private String rownum;			// 줄번호
	
	/* 쿼리 조회 조건 필드 */
	private String userRole;		// 로그인한 사용자 권한
	private String viewFlag;		// 접근 경로
	
	// logo 경로
	private String logoFilePath;	// 로고파일 경로 
	
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
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
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String getTryIp() {
		return tryIp;
	}
	public void setTryIp(String tryIp) {
		this.tryIp = tryIp;
	}
//	public int getFailCnt() {
//		return failCnt;
//	}
//	public void setFailCnt(int failCnt) {
//		this.failCnt = failCnt;
//	}
//	public String getLockDate() {
//		return lockDate;
//	}
//	public void setLockDate(String lockDate) {
//		this.lockDate = lockDate;
//	}
	public String getLoginYN() {
		return loginYN;
	}
	public void setLoginYN(String loginYN) {
		this.loginYN = loginYN;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public String getLogoFilePath() {
		return logoFilePath;
	}
	public void setLogoFilePath(String logoFilePath) {
		this.logoFilePath = logoFilePath;
	}
	
}
