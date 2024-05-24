package egovframework.breeze.core.service;

public class IndexVO {

	private String defaultHeader;
	private String bbsHeader;

	
	public String getDefaultHeader() {
	    String defaultHeader = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n";
	    defaultHeader += "<%@ page import=\"egovframework.breeze.menu.web.MenuBundle\"%>			\r\n";
	    defaultHeader += "<%@ page import=\"egovframework.breeze.site.service.MenuVO\"%>			\r\n";
	    defaultHeader += "<%@ page import=\"egovframework.breeze.member.web.SessionBundle\"%>		\r\n";
	    defaultHeader += "<%@ page import=\"egovframework.breeze.member.service.MemberVO\"%>		\r\n";
	    defaultHeader += "<%@ page import=\"egovframework.breeze.code.web.CodeBundle\"%>			\r\n";
	    defaultHeader += "<%@ page import=\"egovframework.breeze.code.service.CodeVO\"%>			\r\n";
	    defaultHeader += "<%												\r\n";
	    defaultHeader += "	MenuBundle mb = new MenuBundle(request);		\r\n";
	    defaultHeader += "	SessionBundle sb = new SessionBundle(request);	\r\n";
	    defaultHeader += "	CodeBundle cb = new CodeBundle(request);		\r\n";
	    defaultHeader += "%>												\r\n";
	    
		return defaultHeader;
	}

	public void setDefaultHeader(String defaultHeader) {
		this.defaultHeader = defaultHeader;
	}
	
	public String getBbsHeader() {
	    String bbsHeader = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.board.web.BoardBundle\"%>			\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.com.cop.bbs.service.BoardVO\"%>			\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.com.cop.bbs.service.BoardMasterVO\"%>		\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.menu.web.MenuBundle\"%>			\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.site.service.MenuVO\"%>			\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.member.web.SessionBundle\"%>		\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.member.service.MemberVO\"%>		\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.code.web.CodeBundle\"%>			\r\n";
	    bbsHeader += "<%@ page import=\"egovframework.breeze.code.service.CodeVO\"%>			\r\n";
	    bbsHeader += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>		\r\n";
	    bbsHeader += "<%@ taglib prefix=\"ui\" uri=\"http://egovframework.gov/ctl/ui\"%>		\r\n";
	    bbsHeader += "<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>	\r\n";
	    bbsHeader += "<%@ taglib prefix=\"fmt\" uri=\"http://java.sun.com/jsp/jstl/fmt\"%>		\r\n";
	    bbsHeader += "<%												\r\n";
	    bbsHeader += "	BoardBundle bb = new BoardBundle(request);		\r\n";
	    bbsHeader += "	MenuBundle mb = new MenuBundle(request);		\r\n";
	    bbsHeader += "	SessionBundle sb = new SessionBundle(request);	\r\n";
	    bbsHeader += "	CodeBundle cb = new CodeBundle(request);		\r\n";
	    bbsHeader += "%>												\r\n";
		return bbsHeader;
	}

	public void setBbsHeader(String bbsHeader) {
		this.bbsHeader = bbsHeader;
	}
	

	// 관리자 > site 기본설정 VO
	private String siteId;		// 사이트 ID
	private String siteTitle;	// 사이트 타이틀
	private String siteDomain;	// 사이트 도메인
	private String menuId;		// 메인 메뉴ID
	private String menuName;	// 메인 메뉴Name
	private String loginMenuId;	// 메인 메뉴ID
	private String loginMenuName;// 메인 메뉴Name
	private String regId;		// 등록자 ID
	private String regDate;		// 등록일
	private String updId;		// 최종수정자 ID
	private String updDate;		// 최종수정일

	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSiteTitle() {
		return siteTitle;
	}
	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getLoginMenuId() {
		return loginMenuId;
	}
	public void setLoginMenuId(String loginMenuId) {
		this.loginMenuId = loginMenuId;
	}
	public String getLoginMenuName() {
		return loginMenuName;
	}
	public void setLoginMenuName(String loginMenuName) {
		this.loginMenuName = loginMenuName;
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
	
}
