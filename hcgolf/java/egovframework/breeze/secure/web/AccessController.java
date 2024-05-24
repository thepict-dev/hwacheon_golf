package egovframework.breeze.secure.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.secure.service.AccessService;
import egovframework.breeze.secure.service.AccessVO;
import egovframework.breeze.secure.service.DefaultService;
import egovframework.breeze.secure.service.DefaultVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/secure")
public class AccessController {
	
	@Resource(name = "accessService")
	private AccessService accessService;
	
	@Resource(name="defaultService")
	private DefaultService defaultService;
	
	/**
	 * 관리자 페이지 > 보안관리 > 접근제한 관리 > 리스트
	 * @param accessVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accessList.do")
	public String accessList(@ModelAttribute("searchVO") AccessVO accessVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		// 권한이 전체 관리자가 아니라면 접근 불가능
		else if(!user.getAdminRole().equals("ROLE_AA")) {
			model.addAttribute("message", "접근권한이 없습니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}
		else {

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(accessVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(accessVO.getPageUnit());
			paginationInfo.setPageSize(accessVO.getPageSize());
			
			accessVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			accessVO.setLastIndex(paginationInfo.getLastRecordIndex());
			accessVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = accessService.selectAccessList(accessVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			DefaultVO defaultVO = new DefaultVO();
			defaultVO.setDefaultId("breeze");
			defaultVO = defaultService.selectDefaultSetting(defaultVO);
			model.addAttribute("defaultVO", defaultVO);

			return "/admin/secure/accessList";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 접근제한 관리 > 등록/수정 페이지
	 * @param accessVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accessForm.do")
	public String accessForm(@ModelAttribute("searchVO") AccessVO accessVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		// 권한이 전체 관리자가 아니라면 접근 불가능
		else if(!user.getAdminRole().equals("ROLE_AA")) {
			model.addAttribute("message", "접근권한이 없습니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}
		else {
			String command = accessVO.getCommand() == null ? "insert" : accessVO.getCommand();
			if(command.equals("update")) {
				accessVO = accessService.selectAccessView(accessVO);
				model.addAttribute("accessVO", accessVO);
			}
			return "/admin/secure/accessForm";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 접근제한 관리 > 등록 action
	 * @param accessVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accessInsert.do")
	public String accessInsert(AccessVO accessVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		// 권한이 전체 관리자가 아니라면 접근 불가능
		else if(!user.getAdminRole().equals("ROLE_AA")) {
			model.addAttribute("message", "접근권한이 없습니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}
		else {
			accessVO.setRegId(user.getAdminId());
			accessVO.setUpdId(user.getAdminId());
			
			accessService.accessInsert(accessVO);
			
			model.addAttribute("message", "등록되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/accessList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 접근제한 관리 > 수정 action
	 * @param accessVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accessUpdate.do")
	public String accessUpdate(AccessVO accessVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		// 권한이 전체 관리자가 아니라면 접근 불가능
		else if(!user.getAdminRole().equals("ROLE_AA")) {
			model.addAttribute("message", "접근권한이 없습니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}
		else {
			accessVO.setUpdId(user.getAdminId());
			
			accessService.accessUpdate(accessVO);
			
			model.addAttribute("message", "수정되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/accessForm.do");
			model.addAttribute("hiddenName1", "accessId");
			model.addAttribute("hiddenValue1", accessVO.getAccessId());
			model.addAttribute("hiddenName2", "command");
			model.addAttribute("hiddenValue2", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 접근제한 관리 > 삭제 action
	 * @param accessVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accessDelete.do")
	public String accessDelete(AccessVO accessVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		// 권한이 전체 관리자가 아니라면 접근 불가능
		else if(!user.getAdminRole().equals("ROLE_AA")) {
			model.addAttribute("message", "접근권한이 없습니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}
		else {
			accessVO.setUpdId(user.getAdminId());
			
			accessService.accessDelete(accessVO);
			
			model.addAttribute("message", "삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/accessList.do");
			
			return "/common/message";
		}
	}
}
