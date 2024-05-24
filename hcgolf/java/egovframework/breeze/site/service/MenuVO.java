package egovframework.breeze.site.service;

import egovframework.com.cop.bbs.service.BoardVO;

public class MenuVO extends BoardVO {

	private static final long serialVersionUID = 1L;

	private String menuClassNo;		// 메뉴 class 번호

	private String rownum;			// 번호
	private String menuId;			// 메뉴Id
	private String upperMenuId;		// 부모 메뉴Id
	private String menuName;		// 메뉴명
	private String menuName2;		// 메뉴명2
	private String menuTitle;		// head 내용
	private String menuUseFlag;		// 사용여부
	private String menuViewFlag;	// 노출여부
	private String menuChildFlag;	// 하위뎁스 여부
	private String menuDepth;		// 메뉴 Depth 깊이 (dep1 ~ dep6)
	
	private String menuIdDepth1;	// 메뉴 1Dep id
	private String menuNameDepth1;	// 메뉴 1Dep name
	private String menuIdDepth2;	// 메뉴 2Dep id
	private String menuNameDepth2;	// 메뉴 2Dep name
	private String menuIdDepth3;	// 메뉴 3Dep id
	private String menuNameDepth3;	// 메뉴 3Dep name
	private String menuIdDepth4;	// 메뉴 4Dep id
	private String menuNameDepth4;	// 메뉴 4Dep name
	private String menuIdDepth5;	// 메뉴 5Dep id
	private String menuNameDepth5;	// 메뉴 5Dep name
	private String menuIdDepth6;	// 메뉴 6Dep id
	private String menuNameDepth6;	// 메뉴 6Dep name
	
	private String seoPageTitle;	// SEO - 페이지 명
	private String seoNaviTitle;	// SEO - 네비게이션 명
	private String seoKeywords;		// SEO - 키워드
	private String seoDescription;	// SEO - 설명
	private String seoImageUrl;		// SEO - 이미지URL
	private String seoOgType;		// SEO - 페이지 유형
	private String seoTwitterCard;	// SEO - 카드의 종류

	private String contentsId;		// 콘텐츠 ID
	private String contentsName;	// 콘텐츠 명
	
	private String menuNo;		// 메뉴순서
	
	private String regId;		// 등록자
	private String regDate;		// 등록일
	private String updId;		// 최종수정자
	private String updDate;		// 최종수정일
	private String useFlag;		// 사용여부
	
	private String command;		// 등록/수정 flag
	
	public String getMenuClassNo() {
		return menuClassNo;
	}

	public void setMenuClassNo(String menuClassNo) {
		this.menuClassNo = menuClassNo;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName2() {
		return menuName2;
	}

	public void setMenuName2(String menuName2) {
		this.menuName2 = menuName2;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getMenuUseFlag() {
		return menuUseFlag;
	}

	public void setMenuUseFlag(String menuUseFlag) {
		this.menuUseFlag = menuUseFlag;
	}

	public String getMenuViewFlag() {
		return menuViewFlag;
	}

	public void setMenuViewFlag(String menuViewFlag) {
		this.menuViewFlag = menuViewFlag;
	}

	public String getMenuChildFlag() {
		return menuChildFlag;
	}

	public void setMenuChildFlag(String menuChildFlag) {
		this.menuChildFlag = menuChildFlag;
	}

	public String getMenuDepth() {
		return menuDepth;
	}

	public void setMenuDepth(String menuDepth) {
		this.menuDepth = menuDepth;
	}

	public String getMenuIdDepth1() {
		return menuIdDepth1;
	}

	public void setMenuIdDepth1(String menuIdDepth1) {
		this.menuIdDepth1 = menuIdDepth1;
	}

	public String getMenuNameDepth1() {
		return menuNameDepth1;
	}

	public void setMenuNameDepth1(String menuNameDepth1) {
		this.menuNameDepth1 = menuNameDepth1;
	}

	public String getMenuIdDepth2() {
		return menuIdDepth2;
	}

	public void setMenuIdDepth2(String menuIdDepth2) {
		this.menuIdDepth2 = menuIdDepth2;
	}

	public String getMenuNameDepth2() {
		return menuNameDepth2;
	}

	public void setMenuNameDepth2(String menuNameDepth2) {
		this.menuNameDepth2 = menuNameDepth2;
	}

	public String getMenuIdDepth3() {
		return menuIdDepth3;
	}

	public void setMenuIdDepth3(String menuIdDepth3) {
		this.menuIdDepth3 = menuIdDepth3;
	}

	public String getMenuNameDepth3() {
		return menuNameDepth3;
	}

	public void setMenuNameDepth3(String menuNameDepth3) {
		this.menuNameDepth3 = menuNameDepth3;
	}

	public String getMenuIdDepth4() {
		return menuIdDepth4;
	}

	public void setMenuIdDepth4(String menuIdDepth4) {
		this.menuIdDepth4 = menuIdDepth4;
	}

	public String getMenuNameDepth4() {
		return menuNameDepth4;
	}

	public void setMenuNameDepth4(String menuNameDepth4) {
		this.menuNameDepth4 = menuNameDepth4;
	}

	public String getMenuIdDepth5() {
		return menuIdDepth5;
	}

	public void setMenuIdDepth5(String menuIdDepth5) {
		this.menuIdDepth5 = menuIdDepth5;
	}

	public String getMenuNameDepth5() {
		return menuNameDepth5;
	}

	public void setMenuNameDepth5(String menuNameDepth5) {
		this.menuNameDepth5 = menuNameDepth5;
	}

	public String getMenuIdDepth6() {
		return menuIdDepth6;
	}

	public void setMenuIdDepth6(String menuIdDepth6) {
		this.menuIdDepth6 = menuIdDepth6;
	}

	public String getMenuNameDepth6() {
		return menuNameDepth6;
	}

	public void setMenuNameDepth6(String menuNameDepth6) {
		this.menuNameDepth6 = menuNameDepth6;
	}

	public String getSeoPageTitle() {
		return seoPageTitle;
	}

	public void setSeoPageTitle(String seoPageTitle) {
		this.seoPageTitle = seoPageTitle;
	}

	public String getSeoNaviTitle() {
		return seoNaviTitle;
	}

	public void setSeoNaviTitle(String seoNaviTitle) {
		this.seoNaviTitle = seoNaviTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String getSeoImageUrl() {
		return seoImageUrl;
	}

	public void setSeoImageUrl(String seoImageUrl) {
		this.seoImageUrl = seoImageUrl;
	}

	public String getSeoOgType() {
		return seoOgType;
	}

	public void setSeoOgType(String seoOgType) {
		this.seoOgType = seoOgType;
	}

	public String getSeoTwitterCard() {
		return seoTwitterCard;
	}

	public void setSeoTwitterCard(String seoTwitterCard) {
		this.seoTwitterCard = seoTwitterCard;
	}


	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
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

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	
}
