package egovframework.com.cop.bbs.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BoardItemVO extends BoardMasterVO implements Serializable {

	/**항목 ID*/
	private String itemId;

	/**게시판 ID*/
	private String bbsId;

	/**필드 ID*/
	private String fieldId;

	/**항목명*/
	private String itemName;

	/**항목 넓이비율*/
	private String itemPercent;

	/**항목 순서*/
	private String itemOrder;

	/**항목 flag (list, view)*/
	private String itemFlag;

	/**필드 NAME*/
	private String fieldName;

	/**항목 list*/
	private String itemList;

	/**모바일 hidden flag*/
	private String mobFlag;

	/**검색폼 추가 flag*/
	private String searchFlag;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPercent() {
		return itemPercent;
	}

	public void setItemPercent(String itemPercent) {
		this.itemPercent = itemPercent;
	}

	public String getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(String itemOrder) {
		this.itemOrder = itemOrder;
	}

	public String getItemFlag() {
		return itemFlag;
	}

	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getItemList() {
		return itemList;
	}

	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

	public String getMobFlag() {
		return mobFlag;
	}

	public void setMobFlag(String mobFlag) {
		this.mobFlag = mobFlag;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
}
