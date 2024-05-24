package egovframework.breeze.secure.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminService;
import egovframework.breeze.admin.service.AdminVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/secure")
public class AdminMemberController {
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** adminService */
	@Resource(name = "adminService")
	private AdminService adminService;
	
	
	/**
	 * 관리자 페이지 > 보안 관리 > 관리자 관리 > list
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminList.do")
	public String adminList(@ModelAttribute("searchVO") AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			// 권한이 전체 관리자가 아니라면 접근 불가능
			if(!user.getAdminRole().equals("ROLE_AA")) {
				model.addAttribute("message", "접근권한이 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(adminVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(adminVO.getPageUnit());
			paginationInfo.setPageSize(adminVO.getPageSize());
			
			adminVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			adminVO.setLastIndex(paginationInfo.getLastRecordIndex());
			adminVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = adminService.selectAdminList(adminVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			return "/admin/secure/adminList";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안 관리 > 관리자 관리 > 등록/수정
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminForm.do")
	public String adminForm(@ModelAttribute("searchVO") AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			// 권한이 전체 관리자가 아니라면 접근 불가능
			if(!user.getAdminRole().equals("ROLE_AA")) {
				model.addAttribute("message", "접근권한이 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			String command = adminVO.getCommand() == null ? "insert" : adminVO.getCommand();
			if(command.equals("update")) {
				adminVO = adminService.selectAdminView(adminVO);
				model.addAttribute("resultVO", adminVO);
			}
			return "/admin/secure/adminForm";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 관리자 관리 > 등록 action
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminInsert.do")
	public String adminInsert(AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			// 권한이 전체 관리자가 아니라면 접근 불가능
			if(!user.getAdminRole().equals("ROLE_AA")) {
				model.addAttribute("message", "접근권한이 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			// 2차 중복 체크 : 통합관리자, 업무사용자
			AdminVO overlap = adminService.selectAdminView(adminVO);
			if(overlap != null) {
				model.addAttribute("message", "이미 존재하는 아이디입니다. 다시 확인해주세요.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			adminVO.setRegId(user.getAdminId());
			adminVO.setUpdId(user.getAdminId());
			
			adminService.adminInsert(adminVO);
			
			model.addAttribute("message", "등록되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/adminList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 관리자 관리 > 수정 action
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminUpdate.do")
	public String adminUpdate(AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			// 권한이 전체 관리자가 아니라면 접근 불가능
			if(!user.getAdminRole().equals("ROLE_AA")) {
				model.addAttribute("message", "접근권한이 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			adminVO.setUpdId(user.getAdminId());
			
			adminService.adminUpdate(adminVO);
			
			model.addAttribute("message", "수정되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/adminForm.do");
			model.addAttribute("hiddenName1", "adminId");
			model.addAttribute("hiddenValue1", adminVO.getAdminId());
			model.addAttribute("hiddenName2", "command");
			model.addAttribute("hiddenValue2", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 관리자 관리 > 삭제 action
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminDelete.do")
	public String adminDelete(AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			// 권한이 전체 관리자가 아니라면 접근 불가능
			if(!user.getAdminRole().equals("ROLE_AA")) {
				model.addAttribute("message", "접근권한이 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			adminVO.setUpdId(user.getAdminId());
			
			adminService.adminDelete(adminVO);
			
			model.addAttribute("message", "삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/adminList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 관리자 관리 > 관리자 등록 > 관리자 ID 1차 중복체크
	 * @param adminVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminOverlapCheck.do")
	public void adminOverlapCheck(AdminVO adminVO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jObj = new JSONObject();
		// 통합관리자/업무사용자에서 id 조회
		AdminVO overlap = adminService.selectAdminView(adminVO);
		if(overlap != null) {
			jObj.put("overlap", "Y");
		}else {
			jObj.put("overlap", "N");
		}
		response.setContentType("text/json;charset=utf-8");
		PrintWriter pr = response.getWriter();
		pr.write(jObj.toString());
		pr.flush();
		pr.close();
	}
	
	/**
	 * 관리자 페이지 > 보안관리 > 관리자 관리 > 영구삭제 action
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminDeletePermanent.do")
	public String adminDeletePermanent(AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			// 권한이 전체 관리자가 아니라면 접근 불가능
			if(!user.getAdminRole().equals("ROLE_AA")) {
				model.addAttribute("message", "접근권한이 없습니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			adminVO.setUpdId(user.getAdminId());
			
			adminService.adminDeletePermanent(adminVO);
			
			model.addAttribute("message", "영구삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/secure/adminList.do");
			
			return "/common/message";
		}
	}
}
