package egovframework.breeze.site.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.common.StringUtil;
import egovframework.breeze.site.service.LayoutService;
import egovframework.breeze.site.service.LayoutVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/site")
public class LayoutController {

	/** layoutService */
	@Resource(name = "layoutService")
	private LayoutService layoutService;
	
	
	
	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 리스트
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/layoutList.do")
	public String layoutList(@ModelAttribute("searchVO") LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(layoutVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(layoutVO.getPageUnit());
			paginationInfo.setPageSize(layoutVO.getPageSize());
			
			layoutVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			layoutVO.setLastIndex(paginationInfo.getLastRecordIndex());
			layoutVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = layoutService.selectLayoutList(layoutVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/admin/site/layoutList";
		}
	}

	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 등록/수정 페이지
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/layoutForm.do")
	public String layoutForm(@ModelAttribute("searchVO") LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			//update 일때만 조회
			String command = layoutVO.getCommand() == null ? "insert" : layoutVO.getCommand();
			if(command.equals("update")){
				layoutVO = layoutService.selectLayoutView(layoutVO);
				model.addAttribute("layoutVO", layoutVO);
			}
			
			return "/admin/site/layoutForm";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 등록 action
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layoutInsert.do")
	public String layoutInsert(LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 등록자ID, 수정자ID 추가
			layoutVO.setRegId(user.getAdminId());
			layoutVO.setUpdId(user.getAdminId());

			// html태그 치환
			layoutVO.setLayoutHead(StringUtil.getStringToTag(layoutVO.getLayoutHead()));
			layoutVO.setLayoutHeader(StringUtil.getStringToTag(layoutVO.getLayoutHeader()));
			layoutVO.setLayoutFooter(StringUtil.getStringToTag(layoutVO.getLayoutFooter()));
			
			// 등록 action
			layoutService.layoutInsert(layoutVO);
			
			// 백업 action
			layoutService.layoutBakInsert(layoutVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/layoutList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 수정 action
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layoutUpdate.do")
	public String layoutUpdate(LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 수정자ID 추가
			layoutVO.setUpdId(user.getAdminId());

			// html태그 치환
			layoutVO.setLayoutHead(StringUtil.getStringToTag(layoutVO.getLayoutHead()));
			layoutVO.setLayoutHeader(StringUtil.getStringToTag(layoutVO.getLayoutHeader()));
			layoutVO.setLayoutFooter(StringUtil.getStringToTag(layoutVO.getLayoutFooter()));
			
			// 수정 action
			layoutService.layoutUpdate(layoutVO);
			
			// 백업 action
			layoutService.layoutBakInsert(layoutVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/layoutForm.do");
			model.addAttribute("hiddenName1", "layoutId");
			model.addAttribute("hiddenValue1", layoutVO.getLayoutId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", layoutVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", layoutVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", layoutVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 삭제 action
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/layoutDelete.do")
	public String layoutDelete(LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 수정자ID 추가
			layoutVO.setUpdId(user.getAdminId());
			
			// delete action
			layoutService.layoutDelete(layoutVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/layoutList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 백업 list
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/layoutBakList.do")
	public String layoutBakList(@ModelAttribute("searchVO") LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(layoutVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(20);
			paginationInfo.setPageSize(layoutVO.getPageSize());
			
			layoutVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			layoutVO.setLastIndex(paginationInfo.getLastRecordIndex());
			layoutVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = layoutService.selectLayoutBakList(layoutVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/popup/admin/layoutBakList";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 레이아웃관리 > 백업 view
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/layoutBakForm.do")
	public String layoutBakForm(@ModelAttribute("searchVO") LayoutVO layoutVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			layoutVO = layoutService.selectLayoutBakView(layoutVO);
			model.addAttribute("layoutVO", layoutVO);
			
			return "/popup/admin/layoutBakForm";
		}
	}
}
