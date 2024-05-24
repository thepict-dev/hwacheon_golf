package egovframework.breeze.site.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.site.service.ContentsService;
import egovframework.breeze.site.service.ContentsVO;
import egovframework.breeze.site.service.LayoutService;
import egovframework.breeze.site.service.LayoutVO;
import egovframework.breeze.site.service.MenuService;
import egovframework.breeze.site.service.MenuVO;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/site")
public class MenuController {

	/** menuService */
	@Resource(name = "menuService")
	private MenuService menuService;
    
	/** contentsService */
    @Resource(name = "contentsService")
    private ContentsService contentsService;

	/** layoutService */
	@Resource(name = "layoutService")
	private LayoutService layoutService;

    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;
	
	
	
	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 리스트
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuList.do")
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
			return "/admin/site/menuList";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 등록/수정 페이지
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menuForm.do")
	public String menuForm(@ModelAttribute("searchVO") MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// update 일 때 해당 메뉴 조회 
			String command = menuVO.getCommand() == null ? "insert" : menuVO.getCommand();
			if(command.equals("update")){
				menuVO = menuService.selectMenuView(menuVO);
				model.addAttribute("menuVO", menuVO);
			}else {
				// insert이면서 하위메뉴 등록 일 때 상위메뉴 조회
				if(!menuVO.getMenuDepth().equals("dep0")) {
					menuVO.setMenuId(menuVO.getUpperMenuId());
					menuVO = menuService.selectMenuView(menuVO);
					menuVO.setUpperMenuId(menuVO.getMenuId());
					model.addAttribute("upperVO", menuVO);
				}
			}
			
			return "/admin/site/menuForm";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 등록 action
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menuInsert.do")
	public String layoutInsert(MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 메뉴이름 중복체크
			int menuNameOverlapChk = menuService.menuNameOverlapChk(menuVO);
			if(menuNameOverlapChk > 0) {
				model.addAttribute("message", "같은 메뉴명이 존재합니다. 메뉴명 확인 후 다시 시도해 주세요.");
				model.addAttribute("retType", ":back");
				return "/common/message";
			}

			// 등록자ID, 수정자ID 추가
			menuVO.setRegId(user.getAdminId());
			menuVO.setUpdId(user.getAdminId());
			
			// 등록 action
			menuService.menuInsert(menuVO);
			
			// 등록된 메뉴 조회
			menuVO = menuService.selectMenuView(menuVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/menuList.do");
			model.addAttribute("hiddenName1", "menuIdDepth1");
			model.addAttribute("hiddenValue1", menuVO.getMenuIdDepth1());
			model.addAttribute("hiddenName2", "menuIdDepth2");
			model.addAttribute("hiddenValue2", menuVO.getMenuIdDepth2());
			model.addAttribute("hiddenName3", "menuIdDepth3");
			model.addAttribute("hiddenValue3", menuVO.getMenuIdDepth3());
			model.addAttribute("hiddenName4", "menuIdDepth4");
			model.addAttribute("hiddenValue4", menuVO.getMenuIdDepth4());
			model.addAttribute("hiddenName5", "menuIdDepth5");
			model.addAttribute("hiddenValue5", menuVO.getMenuIdDepth5());
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 수정 action
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menuUpdate.do")
	public String menuUpdate(MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 메뉴이름 중복체크
			if(!menuVO.getMenuName().equals(menuVO.getMenuName2())) {
				int menuNameOverlapChk = menuService.menuNameOverlapChk2(menuVO);
				if(menuNameOverlapChk > 0) {
					model.addAttribute("message", "같은 메뉴명이 존재합니다. 메뉴명 확인 후 다시 시도해 주세요.");
					model.addAttribute("retType", ":back");
					return "/common/message";
				}
			}

			// 수정자ID 추가
			menuVO.setUpdId(user.getAdminId());
			
			// 수정 action
			menuService.menuUpdate(menuVO);
			
			// Depth name값 수정
			if(!menuVO.getMenuDepth().equals("dep6")) {
				menuService.menuDepthUpdate(menuVO);
			}
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/menuForm.do");
			model.addAttribute("hiddenName1", "command");
			model.addAttribute("hiddenValue1", "update");
			model.addAttribute("hiddenName2", "menuId");
			model.addAttribute("hiddenValue2", menuVO.getMenuId());
			model.addAttribute("hiddenName3", "menuDepth");
			model.addAttribute("hiddenValue3", menuVO.getMenuDepth());
			
			return "/common/message";
		}
	}

	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 삭제 action
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menuDelete.do")
	public String menuDelete(MenuVO menuVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// return 메뉴정보에 필요한 삭제된 메뉴 조회
			menuVO = menuService.selectMenuView(menuVO);
			
			// 수정자ID 추가
			menuVO.setUpdId(user.getAdminId());
			
			// delete action
			menuService.menuDelete(menuVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/menuList.do");
			model.addAttribute("hiddenName1", "menuIdDepth1");
			model.addAttribute("hiddenValue1", menuVO.getMenuIdDepth1());
			model.addAttribute("hiddenName2", "menuIdDepth2");
			model.addAttribute("hiddenValue2", menuVO.getMenuIdDepth2());
			model.addAttribute("hiddenName3", "menuIdDepth3");
			model.addAttribute("hiddenValue3", menuVO.getMenuIdDepth3());
			model.addAttribute("hiddenName4", "menuIdDepth4");
			model.addAttribute("hiddenValue4", menuVO.getMenuIdDepth4());
			model.addAttribute("hiddenName5", "menuIdDepth5");
			model.addAttribute("hiddenValue5", menuVO.getMenuIdDepth5());
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 콘텐츠 선택 list
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="selectConList.do")
	public String selectConList(@ModelAttribute("searchVO") ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(contentsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(10);
			paginationInfo.setPageSize(contentsVO.getPageSize());
			
			contentsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			contentsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			contentsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = contentsService.selectContentsList(contentsVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "/popup/admin/selectConList";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 메뉴관리 > 콘텐츠 선택 view(미리보기)
	 * @param menuVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="selectConForm.do")
	public String selectConView(@ModelAttribute("searchVO") ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 레이아웃 list 조회
			LayoutVO layoutVO = new LayoutVO();
			layoutVO.setFirstIndex(0);
			layoutVO.setRecordCountPerPage(9999);
			Map<String, Object> map = layoutService.selectLayoutList(layoutVO);
			model.addAttribute("layoutList", map.get("resultList"));
			
			// 게시판 list 조회
			BoardMasterVO boardMasterVO = new BoardMasterVO();
			boardMasterVO.setFirstIndex(0);
			boardMasterVO.setRecordCountPerPage(9999);
			Map<String, Object> map2 = egovBBSMasterService.selectBBSMasterInfs(boardMasterVO);
			model.addAttribute("boardList", map2.get("resultList"));
						
			contentsVO = contentsService.selectContentsView(contentsVO);
			model.addAttribute("contentsVO", contentsVO);
			
			return "/popup/admin/contentsBakForm";
		}
	}
}
