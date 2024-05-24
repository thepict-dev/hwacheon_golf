package egovframework.breeze.member.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class MemberVO extends BoardVO {

	private static final long serialVersionUID = 1L;

	/*회원 관리 테이블*/
	private String memberId;	// 아이디
	private String memberPw;	// 비밀번호
	private String memberName;	// 이름
	private String telNo;		// 연락처
	private String zipCode;		// 우편번호
	private String address;		// 주소
	private String detailAddr;	// 상세 주소
	private String regId;		// 등록자
	private String regDate;		// 등록일
	private String updId;		// 최종수정자
	private String updDate;		// 최종수정일
	private String command;		// 등록/수정 flag
	private String rownum;		// 줄번호
	private String adminRole;	// 권한
	private String department;	// 소속
	private String email;		// 이메일
	private String searchPart;	// 소속 검색
	
	/*소속 관리 테이블*/
	private String departmentId;	// 소속 ID
	private String departmentName;	// 소속 업체명
	private String businessNo;		// 사업자등록번호
	private String useFlag;			// 사용여부 FLAG
	private String memberCnt;		// 해당 소속 회원 cnt
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
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
	public String getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getSearchPart() {
		return searchPart;
	}
	public void setSearchPart(String searchPart) {
		this.searchPart = searchPart;
	}
	public String getMemberCnt() {
		return memberCnt;
	}
	public void setMemberCnt(String memberCnt) {
		this.memberCnt = memberCnt;
	}
}
