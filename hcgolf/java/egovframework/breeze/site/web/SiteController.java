package egovframework.breeze.site.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.core.service.IndexService;
import egovframework.breeze.core.service.IndexVO;
import egovframework.breeze.site.service.MenuService;
import egovframework.breeze.site.service.MenuVO;

@Controller
@RequestMapping("/_admin/site")
public class SiteController {

	/** menuService */
	@Resource(name = "menuService")
	private MenuService menuService;

	/** indexService */
	@Resource(name = "indexService")
	private IndexService indexService;

	
	/**
	 * 관리자 > 사이트관리 > 사이트 기본설정
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/siteForm.do")
	public String siteForm(IndexVO indexVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			indexVO.setSiteId("SITE_000000000000001");
			indexVO = indexService.selectSiteSetting(indexVO);
			model.addAttribute("indexVO", indexVO);
			
			return "/admin/site/siteForm";
		}
	}

	/**
	 * 관리자 > 사이트관리 > 사이트 기본설정 > 저장
	 * @param indexVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/siteUpdate.do")
	public String siteUpdate(IndexVO indexVO, HttpServletRequest request, ModelMap model)throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {
			
			// 등록자ID, 수정자ID 저장
			indexVO.setRegId(user.getAdminId());
			indexVO.setUpdId(user.getAdminId());
			
			// 저장
			indexVO.setSiteId("SITE_000000000000001");
			indexService.siteUpdate(indexVO);
			
			model.addAttribute("message", "저장이 완료되었습니다.");
			model.addAttribute("retType", ":location");
			model.addAttribute("retUrl", "/_admin/site/siteForm.do");
			
			return "/common/message";
		}
	}
	

	@RequestMapping(value = "/selectMenuList.do")
	public String menuList(@ModelAttribute("searchVO") MenuVO menuVO2, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			// MenuVO 초기화
			MenuVO menuVO = new MenuVO();
			
			// 전체 list 선언
			List<MenuVO> menuList = new ArrayList<>();
			
			// :::::::::::::::::::::::::::::::::::::::::::::: 1Dep list 조회 ::::::::::::::::::::::::::::::::::::::::::::::
			menuVO.setMenuDepth("dep1");
			List<MenuVO> menuListDep1 = menuService.selectMenuList(menuVO);
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
					menuDep1.setContentsId(menuListDep1.get(i).getContentsId());
					
					menuVO.setMenuDepth("dep2");
					menuVO.setUpperMenuId(menuListDep1.get(i).getMenuId());
					List<MenuVO> menuListDep2 = menuService.selectMenuList(menuVO);
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
							menuDep2.setMenuNo(menuListDep1.get(i).getMenuNo()+"-"+menuListDep2.get(j).getMenuNo());
							menuDep2.setMenuClassNo((i+1)+"-"+(j+1));
							menuDep2.setMenuNameDepth1(menuListDep2.get(j).getMenuNameDepth1());
							menuDep2.setMenuNameDepth2(menuListDep2.get(j).getMenuNameDepth2());
							menuDep2.setContentsId(menuListDep2.get(j).getContentsId());
							
							menuVO.setMenuDepth("dep3");
							menuVO.setUpperMenuId(menuListDep2.get(j).getMenuId());
							List<MenuVO> menuListDep3 = menuService.selectMenuList(menuVO);
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
									menuDep3.setMenuNo(menuListDep1.get(i).getMenuNo()+"-"+menuListDep2.get(j).getMenuNo()+"-"+menuListDep3.get(k).getMenuNo());
									menuDep3.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1));
									menuDep3.setMenuNameDepth1(menuListDep3.get(k).getMenuNameDepth1());
									menuDep3.setMenuNameDepth2(menuListDep3.get(k).getMenuNameDepth2());
									menuDep3.setMenuNameDepth3(menuListDep3.get(k).getMenuNameDepth3());
									menuDep3.setContentsId(menuListDep3.get(k).getContentsId());
									
									menuVO.setMenuDepth("dep4");
									menuVO.setUpperMenuId(menuListDep3.get(k).getMenuId());
									List<MenuVO> menuListDep4 = menuService.selectMenuList(menuVO);
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
											menuDep4.setMenuNo(menuListDep1.get(i).getMenuNo()+"-"+menuListDep2.get(j).getMenuNo()+"-"+menuListDep3.get(k).getMenuNo()+"-"+menuListDep4.get(l).getMenuNo());
											menuDep4.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1)+"-"+(l+1));
											menuDep4.setMenuNameDepth1(menuListDep4.get(l).getMenuNameDepth1());
											menuDep4.setMenuNameDepth2(menuListDep4.get(l).getMenuNameDepth2());
											menuDep4.setMenuNameDepth3(menuListDep4.get(l).getMenuNameDepth3());
											menuDep4.setMenuNameDepth4(menuListDep4.get(l).getMenuNameDepth4());
											menuDep4.setContentsId(menuListDep4.get(l).getContentsId());

											menuVO.setMenuDepth("dep5");
											menuVO.setUpperMenuId(menuListDep4.get(l).getMenuId());
											List<MenuVO> menuListDep5 = menuService.selectMenuList(menuVO);
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
													menuDep5.setMenuNo(menuListDep1.get(i).getMenuNo()+"-"+menuListDep2.get(j).getMenuNo()+"-"+menuListDep3.get(k).getMenuNo()+"-"+menuListDep4.get(l).getMenuNo()+"-"+menuListDep5.get(m).getMenuNo());
													menuDep5.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1)+"-"+(l+1)+"-"+(m+1));
													menuDep5.setMenuNameDepth1(menuListDep5.get(m).getMenuNameDepth1());
													menuDep5.setMenuNameDepth2(menuListDep5.get(m).getMenuNameDepth2());
													menuDep5.setMenuNameDepth3(menuListDep5.get(m).getMenuNameDepth3());
													menuDep5.setMenuNameDepth4(menuListDep5.get(m).getMenuNameDepth4());
													menuDep5.setMenuNameDepth5(menuListDep5.get(m).getMenuNameDepth5());
													menuDep5.setContentsId(menuListDep5.get(m).getContentsId());

													menuVO.setMenuDepth("dep6");
													menuVO.setUpperMenuId(menuListDep5.get(m).getMenuId());
													List<MenuVO> menuListDep6 = menuService.selectMenuList(menuVO);
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
															menuDep6.setMenuNo(menuListDep1.get(i).getMenuNo()+"-"+menuListDep2.get(j).getMenuNo()+"-"+menuListDep3.get(k).getMenuNo()+"-"+menuListDep4.get(l).getMenuNo()+"-"+menuListDep5.get(m).getMenuNo()+"-"+menuListDep6.get(n).getMenuNo());
															menuDep6.setMenuChildFlag("N");
															menuDep6.setMenuClassNo((i+1)+"-"+(j+1)+"-"+(k+1)+"-"+(l+1)+"-"+(m+1)+"-"+(n+1));
															menuDep6.setMenuNameDepth1(menuListDep6.get(n).getMenuNameDepth1());
															menuDep6.setMenuNameDepth2(menuListDep6.get(n).getMenuNameDepth2());
															menuDep6.setMenuNameDepth3(menuListDep6.get(n).getMenuNameDepth3());
															menuDep6.setMenuNameDepth4(menuListDep6.get(n).getMenuNameDepth4());
															menuDep6.setMenuNameDepth5(menuListDep6.get(n).getMenuNameDepth5());
															menuDep6.setMenuNameDepth6(menuListDep6.get(n).getMenuNameDepth6());
															menuDep6.setContentsId(menuListDep6.get(n).getContentsId());
															
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
			
			model.addAttribute("resultList", menuList);
			return "/popup/admin/selectMenuList";
		}
	}
	
}
