package egovframework.breeze.menu.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.breeze.core.service.IndexService;
import egovframework.breeze.site.service.MenuService;
import egovframework.breeze.site.service.MenuVO;

public class MenuBundle {
	
	private HttpServletRequest request;
	private MenuVO menuVO;
	
	private WebApplicationContext context;
	private IndexService indexService;
	private MenuService menuService;
	
	public MenuBundle() throws Exception
	{
		
	}
	
	/**
	 * MenuBundle request setting
	 * @param request
	 * @throws Exception
	 */
	public MenuBundle(HttpServletRequest request) throws Exception
	{
		this.request = request;
		this.menuVO = (MenuVO)request.getAttribute("menuVO");
		
		this.context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		this.indexService = ((IndexService)this.context.getBean("indexService"));
		this.menuService = ((MenuService)this.context.getBean("menuService"));
	}
	
	/**
	 * menuDepth List 조회 
	 * @param menuDepth : dep1, dep2, dep3, dep4, dep5, dep6
	 * @return
	 * @throws Exception 
	 */
	public List<MenuVO> getMenuListDepth(String menuDepth) throws Exception{
		MenuVO menu = new MenuVO();
		menu.setMenuDepth(menuDepth);
		List<MenuVO> menuList = this.menuService.getMenuListDepth(menu);
		return menuList;
		
	}
	
	/**
	 * menuId 기준 하위 메뉴 List 조회
	 * @param menuId
	 * @return
	 * @throws Exception 
	 */
	public List<MenuVO> getMenuLowList(String menuId) throws Exception{
		List<MenuVO> menuList = this.menuService.selectMenuLowList(menuId);
		return menuList;
	}
	
	/**
	 * 사용중인 모든 메뉴 조회
	 * @param menuId
	 * @return
	 * @throws Exception 
	 */
	public List<MenuVO> getMenuAllList() throws Exception{
		// MenuVO 초기화
		MenuVO menuVO = new MenuVO();
		
		// 전체 list 선언
		List<MenuVO> menuList = new ArrayList<>();
		
		// :::::::::::::::::::::::::::::::::::::::::::::: 1Dep list 조회 ::::::::::::::::::::::::::::::::::::::::::::::
		menuVO.setMenuDepth("dep1");
		List<MenuVO> menuListDep1 = menuService.getMenuListDepth(menuVO);
		if(menuListDep1.size() > 0) {
			for(int i=0;i<menuListDep1.size();i++) {
				MenuVO menuDep1 = new MenuVO();
				menuDep1.setMenuDepth("dep1");
				menuDep1.setMenuId(menuListDep1.get(i).getMenuId());
				menuDep1.setMenuName(menuListDep1.get(i).getMenuName());
				menuDep1.setMenuTitle(menuListDep1.get(i).getMenuTitle());
				menuDep1.setMenuUseFlag(menuListDep1.get(i).getMenuUseFlag());
				menuDep1.setMenuNo(menuListDep1.get(i).getMenuNo());
				menuDep1.setMenuClassNo((i+1)+"");
				menuDep1.setMenuNameDepth1(menuListDep1.get(i).getMenuNameDepth1());
				
				menuVO.setMenuDepth("dep2");
				menuVO.setUpperMenuId(menuListDep1.get(i).getMenuId());
				List<MenuVO> menuListDep2 = menuService.getMenuListDepth(menuVO);
				// 자식 Depth가 있는지 체크 후 add
				if(menuListDep2.size() > 0) {
					menuDep1.setMenuChildFlag("Y");
				}else {
					menuDep1.setMenuChildFlag("N");
				}
				menuList.add(menuDep1);
				//System.out.println("dep1:::"+menuListDep1.get(i).getMenuId()+"##"+menuListDep1.get(i).getMenuName()+"##"+menuListDep1.get(i).getMenuTitle());
				
				// :::::::::::::::::::::::::::::::::::::::::::::: 2Dep list 조회 시작 ::::::::::::::::::::::::::::::::::::::::::::::
				if(menuListDep2.size() > 0) {
					for(int j=0;j<menuListDep2.size();j++) {
						MenuVO menuDep2 = new MenuVO();
						menuDep2.setMenuDepth("dep2");
						menuDep2.setMenuId(menuListDep2.get(j).getMenuId());
						menuDep2.setMenuName(menuListDep2.get(j).getMenuName());
						menuDep2.setMenuTitle(menuListDep2.get(j).getMenuTitle());
						menuDep2.setMenuUseFlag(menuListDep2.get(j).getMenuUseFlag());
						menuDep2.setMenuNo(menuListDep2.get(j).getMenuNo());
						menuDep2.setMenuClassNo((i+1)+"-"+(j+1));
						menuDep2.setMenuNameDepth1(menuListDep2.get(j).getMenuNameDepth1());
						menuDep2.setMenuNameDepth2(menuListDep2.get(j).getMenuNameDepth2());
						
						menuVO.setMenuDepth("dep3");
						menuVO.setUpperMenuId(menuListDep2.get(j).getMenuId());
						List<MenuVO> menuListDep3 = menuService.getMenuListDepth(menuVO);
						// 자식 Depth가 있는지 체크 후 add
						if(menuListDep3.size() > 0) {
							menuDep2.setMenuChildFlag("Y");
						}else {
							menuDep2.setMenuChildFlag("N");
						}
						menuList.add(menuDep2);
						//System.out.println("dep2:::"+menuListDep2.get(j).getMenuId()+"##"+menuListDep2.get(j).getMenuName()+"##"+menuListDep2.get(j).getMenuTitle());
						
						// :::::::::::::::::::::::::::::::::::::::::::::: 3Dep list 조회 시작 ::::::::::::::::::::::::::::::::::::::::::::::
						if(menuListDep3.size() > 0) {
							for(int k=0;k<menuListDep3.size();k++) {
								MenuVO menuDep3 = new MenuVO();
								menuDep3.setMenuDepth("dep3");
								menuDep3.setMenuId(menuListDep3.get(k).getMenuId());
								menuDep3.setMenuName(menuListDep3.get(k).getMenuName());
								menuDep3.setMenuTitle(menuListDep3.get(k).getMenuTitle());
								menuDep3.setMenuUseFlag(menuListDep3.get(k).getMenuUseFlag());
								menuDep3.setMenuNo(menuListDep3.get(k).getMenuNo());
								menuDep3.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1));
								menuDep3.setMenuNameDepth1(menuListDep3.get(k).getMenuNameDepth1());
								menuDep3.setMenuNameDepth2(menuListDep3.get(k).getMenuNameDepth2());
								menuDep3.setMenuNameDepth3(menuListDep3.get(k).getMenuNameDepth3());
								
								menuVO.setMenuDepth("dep4");
								menuVO.setUpperMenuId(menuListDep3.get(k).getMenuId());
								List<MenuVO> menuListDep4 = menuService.getMenuListDepth(menuVO);
								// 자식 Depth가 있는지 체크 후 add
								if(menuListDep4.size() > 0) {
									menuDep3.setMenuChildFlag("Y");
								}else {
									menuDep3.setMenuChildFlag("N");
								}
								menuList.add(menuDep3);
								//System.out.println("dep3:::"+menuListDep3.get(k).getMenuId()+"##"+menuListDep3.get(k).getMenuName()+"##"+menuListDep3.get(k).getMenuTitle());
								
								// :::::::::::::::::::::::::::::::::::::::::::::: 4Dep list 조회 시작 ::::::::::::::::::::::::::::::::::::::::::::::
								if(menuListDep4.size() > 0) {
									for(int l=0;l<menuListDep4.size();l++) {
										MenuVO menuDep4 = new MenuVO();
										menuDep4.setMenuDepth("dep4");
										menuDep4.setMenuId(menuListDep4.get(l).getMenuId());
										menuDep4.setMenuName(menuListDep4.get(l).getMenuName());
										menuDep4.setMenuTitle(menuListDep4.get(l).getMenuTitle());
										menuDep4.setMenuUseFlag(menuListDep4.get(l).getMenuUseFlag());
										menuDep4.setMenuNo(menuListDep4.get(l).getMenuNo());
										menuDep4.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1)+"-"+(l+1));
										menuDep4.setMenuNameDepth1(menuListDep4.get(l).getMenuNameDepth1());
										menuDep4.setMenuNameDepth2(menuListDep4.get(l).getMenuNameDepth2());
										menuDep4.setMenuNameDepth3(menuListDep4.get(l).getMenuNameDepth3());
										menuDep4.setMenuNameDepth4(menuListDep4.get(l).getMenuNameDepth4());

										menuVO.setMenuDepth("dep5");
										menuVO.setUpperMenuId(menuListDep4.get(l).getMenuId());
										List<MenuVO> menuListDep5 = menuService.getMenuListDepth(menuVO);
										// 자식 Depth가 있는지 체크 후 add
										if(menuListDep5.size() > 0) {
											menuDep4.setMenuChildFlag("Y");
										}else {
											menuDep4.setMenuChildFlag("N");
										}
										menuList.add(menuDep4);
										//System.out.println("dep4:::"+menuListDep4.get(l).getMenuId()+"##"+menuListDep4.get(l).getMenuName()+"##"+menuListDep4.get(l).getMenuTitle());
										
										// :::::::::::::::::::::::::::::::::::::::::::::: 5Dep list 조회 시작 ::::::::::::::::::::::::::::::::::::::::::::::
										if(menuListDep5.size() > 0) {
											for(int m=0;m<menuListDep5.size();m++) {
												MenuVO menuDep5 = new MenuVO();
												menuDep5.setMenuDepth("dep5");
												menuDep5.setMenuId(menuListDep5.get(m).getMenuId());
												menuDep5.setMenuName(menuListDep5.get(m).getMenuName());
												menuDep5.setMenuTitle(menuListDep5.get(m).getMenuTitle());
												menuDep5.setMenuUseFlag(menuListDep5.get(m).getMenuUseFlag());
												menuDep5.setMenuNo(menuListDep5.get(m).getMenuNo());
												menuDep5.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1)+"-"+(l+1)+"-"+(m+1));
												menuDep5.setMenuNameDepth1(menuListDep5.get(m).getMenuNameDepth1());
												menuDep5.setMenuNameDepth2(menuListDep5.get(m).getMenuNameDepth2());
												menuDep5.setMenuNameDepth3(menuListDep5.get(m).getMenuNameDepth3());
												menuDep5.setMenuNameDepth4(menuListDep5.get(m).getMenuNameDepth4());
												menuDep5.setMenuNameDepth5(menuListDep5.get(m).getMenuNameDepth5());

												menuVO.setMenuDepth("dep6");
												menuVO.setUpperMenuId(menuListDep5.get(m).getMenuId());
												List<MenuVO> menuListDep6 = menuService.getMenuListDepth(menuVO);
												// 자식 Depth가 있는지 체크 후 add
												if(menuListDep6.size() > 0) {
													menuDep5.setMenuChildFlag("Y");
												}else {
													menuDep5.setMenuChildFlag("N");
												}
												menuList.add(menuDep5);
												//System.out.println("dep5:::"+menuListDep5.get(m).getMenuId()+"##"+menuListDep5.get(m).getMenuName()+"##"+menuListDep5.get(m).getMenuTitle());
												
												// :::::::::::::::::::::::::::::::::::::::::::::: 6Dep list 조회 시작 ::::::::::::::::::::::::::::::::::::::::::::::
												if(menuListDep6.size() > 0) {
													for(int n=0;n<menuListDep6.size();n++) {
														MenuVO menuDep6 = new MenuVO();
														menuDep6.setMenuDepth("dep6");
														menuDep6.setMenuId(menuListDep6.get(n).getMenuId());
														menuDep6.setMenuName(menuListDep6.get(n).getMenuName());
														menuDep6.setMenuTitle(menuListDep6.get(n).getMenuTitle());
														menuDep6.setMenuUseFlag(menuListDep6.get(n).getMenuUseFlag());
														menuDep6.setMenuNo(menuListDep6.get(n).getMenuNo());
														menuDep6.setMenuChildFlag("N");
														menuDep6.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1)+"-"+(l+1)+"-"+(m+1)+"-"+(n+1));
														menuDep6.setMenuNameDepth1(menuListDep6.get(n).getMenuNameDepth1());
														menuDep6.setMenuNameDepth2(menuListDep6.get(n).getMenuNameDepth2());
														menuDep6.setMenuNameDepth3(menuListDep6.get(n).getMenuNameDepth3());
														menuDep6.setMenuNameDepth4(menuListDep6.get(n).getMenuNameDepth4());
														menuDep6.setMenuNameDepth5(menuListDep6.get(n).getMenuNameDepth5());
														menuDep6.setMenuNameDepth6(menuListDep6.get(n).getMenuNameDepth6());
														
														menuList.add(menuDep6);
														//System.out.println("dep6:::"+menuListDep6.get(n).getMenuId()+"##"+menuListDep6.get(n).getMenuName()+"##"+menuListDep6.get(n).getMenuTitle());
													}
												}
												// :::::::::::::::::::::::::::::::::::::::::::::: 6Dep list 조회 종료 ::::::::::::::::::::::::::::::::::::::::::::::
												
											}
										}
										// :::::::::::::::::::::::::::::::::::::::::::::: 5Dep list 조회 종료 ::::::::::::::::::::::::::::::::::::::::::::::
										
									}
								}
								// :::::::::::::::::::::::::::::::::::::::::::::: 4Dep list 조회 종료 ::::::::::::::::::::::::::::::::::::::::::::::
								
							}
						}
						// :::::::::::::::::::::::::::::::::::::::::::::: 3Dep list 조회 종료 ::::::::::::::::::::::::::::::::::::::::::::::

					}
				}
				// :::::::::::::::::::::::::::::::::::::::::::::: 2Dep list 조회 종료 ::::::::::::::::::::::::::::::::::::::::::::::
				
			}
		}
		// :::::::::::::::::::::::::::::::::::::::::::::: 1Dep list 종료 ::::::::::::::::::::::::::::::::::::::::::::::
		
		
		return menuList;
	}
	
	public String getMenuUrl() {
		String url = "";
		String menuDepth = this.menuVO.getMenuDepth();
		if(menuDepth != null && !menuDepth.equals("")) {
			if(menuDepth.equals("dep1")) {
				url = "/" + this.menuVO.getMenuNameDepth1();
			}else if(menuDepth.equals("dep2")) {
				url = "/" + this.menuVO.getMenuNameDepth1() + "/" + this.menuVO.getMenuNameDepth2();
			}else if(menuDepth.equals("dep3")) {
				url = "/" + this.menuVO.getMenuNameDepth1() + "/" + this.menuVO.getMenuNameDepth2() + "/" + this.menuVO.getMenuNameDepth3();
			}else if(menuDepth.equals("dep4")) {
				url = "/" + this.menuVO.getMenuNameDepth1() + "/" + this.menuVO.getMenuNameDepth2() + "/" + this.menuVO.getMenuNameDepth3() + "/" + this.menuVO.getMenuNameDepth4();
			}else if(menuDepth.equals("dep5")) {
				url = "/" + this.menuVO.getMenuNameDepth1() + "/" + this.menuVO.getMenuNameDepth2() + "/" + this.menuVO.getMenuNameDepth3() + "/" + this.menuVO.getMenuNameDepth4() + "/" + this.menuVO.getMenuNameDepth5();
			}else if(menuDepth.equals("dep6")) {
				url = "/" + this.menuVO.getMenuNameDepth1() + "/" + this.menuVO.getMenuNameDepth2() + "/" + this.menuVO.getMenuNameDepth3() + "/" + this.menuVO.getMenuNameDepth4() + "/" + this.menuVO.getMenuNameDepth5() + "/" + this.menuVO.getMenuNameDepth6();
			}
		}
		return url;
	}
	
	public String getMenuIdTitle(String menuId) throws Exception {
		return this.menuService.getMenuIdTitle(menuId);
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuId() {
		return this.menuVO.getMenuId();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getUpperMenuId() {
		return this.menuVO.getUpperMenuId();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuName() {
		return this.menuVO.getMenuName();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuTitle() {
		return this.menuVO.getMenuTitle();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuUseFlag() {
		return this.menuVO.getMenuUseFlag();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuViewFlag() {
		return this.menuVO.getMenuViewFlag();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuDepth() {
		return this.menuVO.getMenuDepth();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuIdDepth1() {
		return this.menuVO.getMenuIdDepth1();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNameDepth1() {
		return this.menuVO.getMenuNameDepth1();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuIdDepth2() {
		return this.menuVO.getMenuIdDepth2();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNameDepth2() {
		return this.menuVO.getMenuNameDepth2();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuIdDepth3() {
		return this.menuVO.getMenuIdDepth3();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNameDepth3() {
		return this.menuVO.getMenuNameDepth3();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuIdDepth4() {
		return this.menuVO.getMenuIdDepth4();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNameDepth4() {
		return this.menuVO.getMenuNameDepth4();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuIdDepth5() {
		return this.menuVO.getMenuIdDepth5();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNameDepth5() {
		return this.menuVO.getMenuNameDepth5();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuIdDepth6() {
		return this.menuVO.getMenuIdDepth6();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNameDepth6() {
		return this.menuVO.getMenuNameDepth6();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoPageTitle(){
		return this.menuVO.getSeoPageTitle();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoNaviTitle(){
		return this.menuVO.getSeoNaviTitle();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoKeywords(){
		return this.menuVO.getSeoKeywords();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoDescription(){
		return this.menuVO.getSeoDescription();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoImageUrl(){
		return this.menuVO.getSeoImageUrl();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoOgType(){
		return this.menuVO.getSeoOgType();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getSeoTwitterCard(){
		return this.menuVO.getSeoTwitterCard();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getMenuNo(){
		return this.menuVO.getMenuNo();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getContentsId(){
		return this.menuVO.getContentsId();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getRegId(){
		return this.menuVO.getRegId();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getRegDate(){
		return this.menuVO.getRegDate();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getUpdId(){
		return this.menuVO.getUpdId();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getUpdDate(){
		return this.menuVO.getUpdDate();
	}
	
	/**
	 * MenuVO Data 조회(request)
	 * @return
	 */
	public String getUseFlag(){
		return this.menuVO.getUseFlag();
	}
	
	public void setMenuVO(MenuVO menu) {
		this.menuVO = menu;
	}
}
