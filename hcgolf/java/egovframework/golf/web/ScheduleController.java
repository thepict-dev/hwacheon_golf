package egovframework.golf.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.common.StringUtil;
import egovframework.breeze.common.ThumbnailUtil;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.golf.service.EventService;
import egovframework.golf.service.EventVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/event")
public class ScheduleController {

    @Resource(name = "eventService")
    private EventService eventService;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
    
    /**
     * XSS 방지 처리.
     * 
     * @param data
     * @return
     */
    protected String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }

	/**
	 * 관리자 > 예약/일정 관리 > 일정안내 list
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scheduleList.do")
	public String scheduleList(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			PaginationInfo paginationInfo = new PaginationInfo();

			paginationInfo.setCurrentPageNo(eventVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(eventVO.getPageUnit());
			paginationInfo.setPageSize(eventVO.getPageSize());

			eventVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			eventVO.setLastIndex(paginationInfo.getLastRecordIndex());
			eventVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = eventService.selectScheduleList(eventVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			return "/admin/golf/scheduleList";
		}
	}
	
	/**
	 * 관리자 > 예약/일정 관리 > 일정안내 view
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/scheduleView.do")
	public String scheduleView(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception {

		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		} else {

			eventVO = eventService.selectScheduleDetail(eventVO);
			model.addAttribute("eventVO", eventVO);
			
			return "/admin/golf/scheduleView";
		}
	}

	/**
	 * 관리자 > 예약/일정 관리 > 일정안내 form
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/scheduleForm.do")
	public String scheduleForm(@ModelAttribute("searchVO") EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {
			String command = eventVO.getCommand() == null ? "insert" : eventVO.getCommand();
			if(command.equals("update")) {
				eventVO = eventService.selectScheduleDetail(eventVO);
				model.addAttribute("selectVO", eventVO);
				
				FileVO file = new FileVO();
				file.setAtchFileId(eventVO.getAtchFileId());
				List<FileVO> fileList = fileMngService.selectFileInfs(file);
				model.addAttribute("fileCnt", fileList.size());

			}

			return "/admin/golf/scheduleForm";
		}
	}

    /**
     * 관리자 > 예약/일정 관리 > 일정안내 등록 action
     * @param multiRequest
     * @param eventVO
     * @param bindingResult
     * @param status
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/scheduleInsert.do")
    public String scheduleInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") EventVO eventVO, BindingResult bindingResult,SessionStatus status, ModelMap model, HttpServletRequest request) throws Exception {
	       		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
		    List<FileVO> result = null;
		    String atchFileId = "";
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
		    	result = fileUtil.parseFileInf(files, "SCHE_", 0, "", "");
		    	atchFileId = fileMngService.insertFileInfs(result);
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(atchFileId);
		    }
		    eventVO.setAtchFileId(atchFileId);
		    
		    eventVO.setRegId(user.getAdminId());
		    
		    eventVO.setContent(unscript(eventVO.getContent()));	// XSS 방지
		    eventVO.setContent(StringUtil.getStringToTag(eventVO.getContent()));
		    
		    eventService.scheduleInsert(eventVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/scheduleView.do");
			model.addAttribute("hiddenName1", "scheduleId");
			model.addAttribute("hiddenValue1", eventVO.getScheduleId());
			return "/common/message";
		}
	}

    /**
     * 관리자 > 예약/일정 관리 > 일정안내 수정 action
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/scheduleUpdate.do")
    public String scheduleUpdate(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") EventVO eventVO, BindingResult bindingResult, ModelMap model,
	    SessionStatus status, HttpServletRequest request) throws Exception {

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
			String atchFileId = eventVO.getAtchFileId();
			
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
				if ("".equals(atchFileId)) {
			    	List<FileVO> result = fileUtil.parseFileInf(files, "SCHE_", 0, atchFileId, "");
			    	atchFileId = fileMngService.insertFileInfs(result);
			    	eventVO.setAtchFileId(atchFileId);
		    	} else {
				    FileVO fvo = new FileVO();
				    fvo.setAtchFileId(atchFileId);
				    int cnt = fileMngService.getMaxFileSN(fvo);
				    List<FileVO> _result = fileUtil.parseFileInf(files, "SCHE_", cnt, atchFileId, "");
				    fileMngService.updateFileInfs(_result);
				}

				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(atchFileId);
		    }
		    
		    eventVO.setContent(unscript(eventVO.getContent()));	// XSS 방지
		    eventVO.setContent(StringUtil.getStringToTag(eventVO.getContent()));
		    eventVO.setUpdId(user.getAdminId());
		    
		    eventService.scheduleUpdate(eventVO);

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/scheduleView.do");
			model.addAttribute("hiddenName1", "scheduleId");
			model.addAttribute("hiddenValue1", eventVO.getScheduleId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", eventVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", eventVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", eventVO.getSearchWrd());
		
			return "/common/message";
		}
	}

	/**
	 * 관리자 > 예약/일정 관리 > 일정안내 삭제 action
	 * @param eventVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/scheduleDelete.do")
	public String scheduleDelete(EventVO eventVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}
		else {

			// 삭제자 ID입력
			eventVO.setUpdId(user.getAdminId());

			eventService.scheduleDelete(eventVO);

			model.addAttribute("message", "삭제되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/event/scheduleList.do");

			return "/common/message";
		}
	}
}
