package egovframework.com.cop.bbs.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BoardCateVO extends BoardMasterVO implements Serializable {

	/**카테고리 ID*/
	private String cateId;

	/**게시판 ID*/
	private String bbsId;

	/**카테고리 구분 (1차-CATE01, 2차-CATE02, 3차-CATE03)*/
	private String cateType;

	/**카테고리 코드*/
	private String cateCode;

	/**카테고리명*/
	private String cateName;

	/**카테고리 순서*/
	private String cateOrder;

	/**카테고리 list*/
	private String cateList;

	/**순서*/
	private String rownum;

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getCateType() {
		return cateType;
	}

	public void setCateType(String cateType) {
		this.cateType = cateType;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCateOrder() {
		return cateOrder;
	}

	public void setCateOrder(String cateOrder) {
		this.cateOrder = cateOrder;
	}

	public String getCateList() {
		return cateList;
	}

	public void setCateList(String cateList) {
		this.cateList = cateList;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
}
