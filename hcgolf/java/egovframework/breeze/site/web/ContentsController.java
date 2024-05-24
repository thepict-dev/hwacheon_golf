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
import egovframework.breeze.site.service.ContentsService;
import egovframework.breeze.site.service.ContentsVO;
import egovframework.breeze.site.service.LayoutService;
import egovframework.breeze.site.service.LayoutVO;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/site")
public class ContentsController {
	
	/** contentsService */
	@Resource(name="contentsService")
	private ContentsService contentsService;
	
	/** layoutService */
	@Resource(name = "layoutService")
	private LayoutService layoutService;

    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;

	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 리스트
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsList.do")
	public String contentsList(@ModelAttribute("searchVO") ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(contentsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(contentsVO.getPageUnit());
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

			return "/admin/site/contentsList";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 등록/수정 페이지
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsForm.do")
	public String contentsForm(@ModelAttribute("searchVO") ContentsVO contentsVO,  HttpServletRequest request, ModelMap model) throws Exception{
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
			
			//update 일때만 조회
			String command = contentsVO.getCommand() == null ? "insert" : contentsVO.getCommand();
			if(command.equals("update")) {
				contentsVO = contentsService.selectContentsView(contentsVO);
				model.addAttribute("contentsVO",contentsVO);
			}
			
			return "/admin/site/contentsForm";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 등록 action
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsInsert.do")
	public String contentsInsert(ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			
			// 등록자ID, 수정자ID 추가
			contentsVO.setRegId(user.getAdminId());
			contentsVO.setUpdId(user.getAdminId());

			// html태그 치환
			contentsVO.setContentsContent(StringUtil.getStringToTag(contentsVO.getContentsContent()));
			contentsVO.setBbsHeader(StringUtil.getStringToTag(contentsVO.getBbsHeader()));
			contentsVO.setBbsFooter(StringUtil.getStringToTag(contentsVO.getBbsFooter()));
			
			// 등록 action
			contentsService.contentsInsert(contentsVO);
			
			// 백업 action
			contentsService.contentsBakInsert(contentsVO);

			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/contentsList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 수정 action
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsUpdate.do")
	public String contentsUpdate(ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자ID 추가
			contentsVO.setUpdId(user.getAdminId());
			
			// html태그 치환
			contentsVO.setContentsContent(StringUtil.getStringToTag(contentsVO.getContentsContent()));
			contentsVO.setBbsHeader(StringUtil.getStringToTag(contentsVO.getBbsHeader()));
			contentsVO.setBbsFooter(StringUtil.getStringToTag(contentsVO.getBbsFooter()));
			
			// 수정 action
			contentsService.contentsUpdate(contentsVO);
			
			// 백업 action
			contentsService.contentsBakInsert(contentsVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/contentsForm.do");
			model.addAttribute("hiddenName1", "contentsId");
			model.addAttribute("hiddenValue1", contentsVO.getContentsId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", contentsVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", contentsVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", contentsVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 삭제 action
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsDelete.do")
	public String contentsDelete(ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 수정자ID 추가
			contentsVO.setUpdId(user.getAdminId());
			
			// delete action
			contentsService.contentsDelete(contentsVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/contentsList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 백업 list
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsBakList.do")
	public String contentsBakList(@ModelAttribute("searchVO") ContentsVO contentsVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(contentsVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(contentsVO.getPageUnit());
			paginationInfo.setPageSize(contentsVO.getPageSize());
			
			contentsVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			contentsVO.setLastIndex(paginationInfo.getLastRecordIndex());
			contentsVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = contentsService.selectContentsBakList(contentsVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/popup/admin/contentsBakList";
		}
	}
	
	/**
	 * 관리자 > 사이트관리 > 콘텐츠관리 > 백업 view
	 * @param contentsVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/contentsBakForm.do")
	public String contentsBakForm(@ModelAttribute("searchVO") ContentsVO contentsVO,  HttpServletRequest request, ModelMap model) throws Exception{
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
			
			contentsVO = contentsService.selectContentsBakView(contentsVO);
			model.addAttribute("contentsVO",contentsVO);
			
			return "/popup/admin/contentsBakForm";
		}
	}
}
