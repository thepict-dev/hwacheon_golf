package egovframework.breeze.code.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.code.service.CodeVO;
import egovframework.breeze.code.service.CodeService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/code")
public class CodeController {

    @Resource(name = "CodeService")
    private CodeService CodeService;


    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드마스터 list
     */
    @RequestMapping("/codeMasterList.do")
    public String codeMasterList(@ModelAttribute("searchVO") CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			PaginationInfo paginationInfo = new PaginationInfo();

			paginationInfo.setCurrentPageNo(codeVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(codeVO.getPageUnit());
			paginationInfo.setPageSize(codeVO.getPageSize());

			codeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			codeVO.setLastIndex(paginationInfo.getLastRecordIndex());
			codeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = CodeService.selectCodeMstrList(codeVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/admin/code/codeMasterList";
		}
    }

    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드마스터 등록/수정 FORM
     */
    @RequestMapping("/codeMasterForm.do")
    public String codeMasterForm(@ModelAttribute("searchVO") CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			//update 일때만 조회
			String command = request.getParameter("command")==null?"insert":request.getParameter("command");
			if(command.equals("update")){
				codeVO = CodeService.selectCodeMstrDetail(codeVO);
				model.addAttribute("selectVO", codeVO);
			}
			return "/admin/code/codeMasterForm";
		}
    }

    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드마스터 등록 ACTION
     */
    @RequestMapping("/codeMasterInsert.do")
    public String codeMasterInsert(CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{		    
			codeVO.setRegId(user.getAdminId()); //게시물 통계 집계를 위해 등록자 ID 저장

		    CodeService.codeMasterInsert(codeVO);

			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/code/codeMasterList.do");
			return "/common/message";
		}
	}

    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드마스터 수정 ACTION
     */
    @RequestMapping("/codeMasterUpdate.do")
    public String codeMasterUpdate(CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자ID 추가
			codeVO.setUpdId(user.getAdminId());

			CodeService.codeMasterUpdate(codeVO);

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/code/codeMasterList.do");
			model.addAttribute("hiddenName1", "pageIndex");
			model.addAttribute("hiddenValue1", codeVO.getPageIndex());
			model.addAttribute("hiddenName2", "searchCnd");
			model.addAttribute("hiddenValue2", codeVO.getSearchCnd());
			model.addAttribute("hiddenName3", "searchWrd");
			model.addAttribute("hiddenValue3", codeVO.getSearchWrd());

			return "/common/message";
		}
    }

    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드마스터 삭제 ACTION
     */
    @RequestMapping("/codeMasterDelete.do")
    public String codeMasterDelete(CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 수정자ID 추가
			codeVO.setUpdId(user.getAdminId());

			// delete action
			CodeService.codeMasterDelete(codeVO);

			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/code/codeMasterList.do");

			return "/common/message";
		}
    }

    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드 등록/수정 FORM
     */
    @RequestMapping("/codeForm.do")
    public String codeForm(@ModelAttribute("searchVO") CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			Map<String, Object> map = CodeService.selectCodeList(codeVO);
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));

			return "/admin/code/codeForm";
		}
    }

    /**
     * 관리자 > 코드 관리 > 코드 관리 > 코드 등록/수정 action
     */
    @RequestMapping("/codeInsert.do")
    public String codeInsert(CodeVO codeVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			// 삭제 action
			CodeService.codeDelete(codeVO);

			// db에 항목을 row별로 저장
			String codeListStr = codeVO.getCodeList();
			int order = 0;
			if ((codeListStr != null) && (!(codeListStr.equals("")))) {
				String[] codeList = codeListStr.split(",");
				for (int i = 0; i < codeList.length; i++) {
					order++;
					String[] codeNode = codeList[i].split("@@");
					codeVO.setCodeId(codeNode[0]);
					codeVO.setCodeName(codeNode[1]);
					codeVO.setCodeOrder(Integer.toString(order));

					// 등록 action
					CodeService.codeInsert(codeVO);
				}
			}

			model.addAttribute("message", "저장이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/code/codeMasterList.do");
			model.addAttribute("hiddenName1", "pageIndex");
			model.addAttribute("hiddenValue1", codeVO.getPageIndex());
			model.addAttribute("hiddenName2", "searchCnd");
			model.addAttribute("hiddenValue2", codeVO.getSearchCnd());
			model.addAttribute("hiddenName3", "searchWrd");
			model.addAttribute("hiddenValue3", codeVO.getSearchWrd());

			return "/common/message";
		}
    }
}