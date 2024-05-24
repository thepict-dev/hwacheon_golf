package egovframework.breeze.member.web;

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

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.member.service.MemberService;
import egovframework.breeze.member.service.MemberVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/member")
public class DepartmentController {

	/** memberService */
	@Resource(name = "memberService")
	private MemberService memberService;

	/**
	 * 관리자 > 회원 관리 > 소속 리스트
	 * @param memberVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/departmentList.do")
	public String departmentList(@ModelAttribute("searchVO") MemberVO memberVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();

			paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
			paginationInfo.setPageSize(memberVO.getPageSize());

			memberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			memberVO.setLastIndex(paginationInfo.getLastRecordIndex());
			memberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = memberService.selectDepartmentList(memberVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/admin/member/departmentList";
		}
	}

	/**
	 * 관리자 > 회원 관리 > 소속 등록/수정 form
	 * @param memberVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/departmentForm.do")
	public String departmentForm(@ModelAttribute("searchVO") MemberVO memberVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			String command = memberVO.getCommand() == null ? "insert" : memberVO.getCommand();
			if(command.equals("update")) {
				memberVO = memberService.selectDepartmentView(memberVO);
				model.addAttribute("resultVO", memberVO);

				//해당 소속인 회원이 있는지 확인
				MemberVO member = new MemberVO();
				member.setDepartment(memberVO.getDepartmentId());

				int memberCnt = memberService.selectMemberCnt(member);
				model.addAttribute("memberCnt", Integer.toString(memberCnt));
			}

			return "/admin/member/departmentForm";
		}
	}

	/**
	 * 관리자 > 회원 관리 > 소속 관리 > 사업자 번호 중복 확인
	 * @param adminVO
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/businessOverlapCheck.do")
	public void businessOverlapCheck(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jObj = new JSONObject();
		int count = memberService.businessOverlapCheck(memberVO);
		if(count > 0) {
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
	 * 관리자 > 회원 관리 > 소속 등록 action
	 * @param memberVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/departmentInsert.do")
	public String departmentInsert(MemberVO memberVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			memberVO.setRegId(user.getAdminId());

			memberService.departmentInsert(memberVO);

			model.addAttribute("message", "등록되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/member/departmentList.do");

			return "/common/message";
		}
	}

	/**
	 * 관리자 > 회원 관리 > 소속 수정 action
	 * @param memberVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/departmentUpdate.do")
	public String departmentUpdate(MemberVO memberVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {

			// 수정자 ID입력
			memberVO.setUpdId(user.getAdminId());

			memberService.departmentUpdate(memberVO);

			model.addAttribute("message", "수정되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/member/departmentForm.do");
			model.addAttribute("hiddenName1", "departmentId");
			model.addAttribute("hiddenValue1", memberVO.getDepartmentId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", memberVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchWrd");
			model.addAttribute("hiddenValue3", memberVO.getSearchWrd());
			model.addAttribute("hiddenName4", "command");
			model.addAttribute("hiddenValue4", "update");

			return "/common/message";
		}
	}

	/**
	 * 관리자 > 회원 관리 > 소속 삭제 action
	 * @param memberVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/departmentDelete.do")
	public String departmentDelete(MemberVO memberVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {

			// 삭제자 ID입력
			memberVO.setUpdId(user.getAdminId());

			//해당 소속인 회원이 있는지 확인
			MemberVO member = new MemberVO();
			member.setDepartment(memberVO.getDepartmentId());

			int memberCnt = memberService.selectMemberCnt(member);

			if(memberCnt == 0) {
				// delete action
				memberService.departmentDelete(memberVO);
			}else {
				model.addAttribute("message", "회원이 있는 소속은 삭제가 불가능합니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}

			model.addAttribute("message", "삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/member/departmentList.do");

			return "/common/message";
		}
	}

}
