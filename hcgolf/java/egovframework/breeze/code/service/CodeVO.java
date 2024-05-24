package egovframework.breeze.code.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class CodeVO extends BoardVO {

	private static final long serialVersionUID = 1L;

    /** 번호 */
	private String rownum;

    /** 코드마스터 id */
	private String codeMasterId;

    /** 코드마스터명 */
	private String codeMasterName;

    /** 등록자 id */
	private String regId;

    /** 등록일 */
	private String regDate;

    /** 수정자 id */
	private String updId;

    /** 수정일 */
	private String updDate;

    /** 사용여부 플래그 */
	private String useFlag;

    /** 코드 id */
	private String codeId;

    /** 코드명 */
	private String codeName;

    /** 코드 순서 */
	private String codeOrder;

	/** 코드 list */
	private String codeList;

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getCodeMasterId() {
		return codeMasterId;
	}

	public void setCodeMasterId(String codeMasterId) {
		this.codeMasterId = codeMasterId;
	}

	public String getCodeMasterName() {
		return codeMasterName;
	}

	public void setCodeMasterName(String codeMasterName) {
		this.codeMasterName = codeMasterName;
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

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeOrder() {
		return codeOrder;
	}

	public void setCodeOrder(String codeOrder) {
		this.codeOrder = codeOrder;
	}

	public String getCodeList() {
		return codeList;
	}

	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}
}
