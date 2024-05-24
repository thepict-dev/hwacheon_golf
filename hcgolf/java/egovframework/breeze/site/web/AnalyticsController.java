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
import egovframework.breeze.site.service.AnalyticsService;
import egovframework.breeze.site.service.AnalyticsVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/site")
public class AnalyticsController {

	@Resource(name = "analyticsService")
	private AnalyticsService analyticsService;
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 목록
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/analyticsList.do")
	public String analyticsList(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		} else {
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(analyticsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(analyticsVO.getPageUnit());
			paginationInfo.setPageSize(analyticsVO.getPageSize());
			
			analyticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			analyticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			analyticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = analyticsService.selectAnalyticsList(analyticsVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/admin/site/analyticsList";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 등록/수정
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "analyticsForm.do")
	public String analyticsForm(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			//update 일때만 조회
			String command = analyticsVO.getCommand() == null ? "insert" : analyticsVO.getCommand();
			if(command.equals("update")){
				analyticsVO = analyticsService.selectAnalyticsView(analyticsVO);
				model.addAttribute("analyticsVO", analyticsVO);
			}
			
			return "/admin/site/analyticsForm";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 등록 action
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "analyticsInsert.do")
	public String analyticsInsert(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 등록자ID, 수정자ID 추가
			analyticsVO.setRegId(user.getAdminId());
			analyticsVO.setUpdId(user.getAdminId());

			// html태그 치환
			analyticsVO.setAnalyticsHead(StringUtil.getStringToTag(analyticsVO.getAnalyticsHead()));
			analyticsVO.setAnalyticsBody(StringUtil.getStringToTag(analyticsVO.getAnalyticsBody()));
			
			// 등록 action
			analyticsService.analyticsInsert(analyticsVO);
			
			// 백업 action
			analyticsService.analyticsBakInsert(analyticsVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/analyticsList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 수정 action
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "analyticsUpdate.do")
	public String analyticsUpdate(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자ID 추가
			analyticsVO.setUpdId(user.getAdminId());

			// html태그 치환
			analyticsVO.setAnalyticsHead(StringUtil.getStringToTag(analyticsVO.getAnalyticsHead()));
			analyticsVO.setAnalyticsBody(StringUtil.getStringToTag(analyticsVO.getAnalyticsBody()));
			
			// 수정 action
			analyticsService.analyticsUpdate(analyticsVO);
			
			// 백업 action
			analyticsService.analyticsBakInsert(analyticsVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/analyticsForm.do");
			model.addAttribute("hiddenName1", "analyticsId");
			model.addAttribute("hiddenValue1", analyticsVO.getAnalyticsId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", analyticsVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", analyticsVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", analyticsVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 삭제 action
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "analyticsDelete.do")
	public String analyticsDelete(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자ID 추가
			analyticsVO.setUpdId(user.getAdminId());
			
			// 삭제 action
			analyticsService.analyticsDelete(analyticsVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/analyticsList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 백업 list
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/analyticsBakList.do")
	public String analyticsBakList(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		} else {
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(analyticsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(20);
			paginationInfo.setPageSize(analyticsVO.getPageSize());
			
			analyticsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			analyticsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			analyticsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = analyticsService.selectAnalyticsBakList(analyticsVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/popup/admin/analyticsBakList";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 애널리틱스 관리 > 백업 view
	 * @param analyticsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "analyticsBakForm.do")
	public String analyticsBakForm(@ModelAttribute("searchVO") AnalyticsVO analyticsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			analyticsVO = analyticsService.selectAnalyticsBakView(analyticsVO);
			model.addAttribute("analyticsVO", analyticsVO);
			
			return "/popup/admin/analyticsBakForm";
		}
	}
}
