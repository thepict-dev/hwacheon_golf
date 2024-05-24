package egovframework.breeze.site.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.common.StringUtil;
import egovframework.breeze.common.ThumbnailUtil;
import egovframework.breeze.site.service.AtchService;
import egovframework.breeze.site.service.AtchVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/site")
public class AtchController {
	
	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@Resource(name = "atchService")
	private AtchService atchService;
	
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
	 * 관리자 페이지 > 사이트 관리 > 첨부파일 관리 > 목록
	 * @param AtchVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atchList.do")
	public String atchList(@ModelAttribute("searchVO") AtchVO atchVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		} else {
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(atchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(atchVO.getPageUnit());
			paginationInfo.setPageSize(atchVO.getPageSize());
			
			atchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			atchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			atchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String, Object> map = atchService.selectAtchList(atchVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);

			
			return "/admin/site/atchList";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 첨부파일 관리 > 등록/수정
	 * @param AtchVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atchForm.do")
	public String atchForm(@ModelAttribute("searchVO") AtchVO atchVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			//update 일때만 조회
			String command = atchVO.getCommand() == null ? "insert" : atchVO.getCommand();
			if(command.equals("update")){
				atchVO = atchService.selectAtchView(atchVO);
				model.addAttribute("atchVO", atchVO);
				
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(atchVO.getAtchFileId());
				List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
				model.addAttribute("atchFileList", atchFileList);
				model.addAttribute("fileCnt", atchFileList.size());
				
			}
			
			return "/admin/site/atchForm";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 첨부파일 관리 > 등록 action
	 * @param AtchVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atchInsert.do")
	public String atchInsert(@ModelAttribute("searchVO") AtchVO atchVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			
			// 파일 저장
			List<FileVO> result = null;
			String atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if(!files.isEmpty()){
				result = fileUtil.parseFileInf(files, "ATCH_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
				atchVO.setAtchFileId(atchFileId);
			}
			
			atchVO.setAtchContent(unscript(atchVO.getAtchContent()));	// XSS 방지
		    atchVO.setAtchContent(StringUtil.getStringToTag(atchVO.getAtchContent()));
			
			// 등록자ID, 수정자ID 추가
			atchVO.setRegId(user.getAdminId());
			atchVO.setUpdId(user.getAdminId());
			
			// insert atcion
			atchService.atchInsert(atchVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/atchList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 첨부파일 관리 > 수정 action
	 * @param AtchVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atchUpdate.do")
	public String atchUpdate(@ModelAttribute("searchVO") AtchVO atchVO, HttpServletRequest request, ModelMap model, final MultipartHttpServletRequest multiRequest) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			
			String atchFileId = atchVO.getAtchFileId();
			
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
				if ("".equals(atchFileId)) {
			    	List<FileVO> result = fileUtil.parseFileInf(files, "ATCH_", 0, atchFileId, "");
			    	atchFileId = fileMngService.insertFileInfs(result);
			    	atchVO.setAtchFileId(atchFileId);
		    	} else {
				    FileVO fvo = new FileVO();
				    fvo.setAtchFileId(atchFileId);
				    int cnt = fileMngService.getMaxFileSN(fvo);
				    List<FileVO> _result = fileUtil.parseFileInf(files, "ATCH_", cnt, atchFileId, "");
				    fileMngService.updateFileInfs(_result);
				}
		    }
		    
		    atchVO.setAtchContent(unscript(atchVO.getAtchContent()));	// XSS 방지
		    atchVO.setAtchContent(StringUtil.getStringToTag(atchVO.getAtchContent()));
		    
		    // 수정자ID 추가
		    atchVO.setUpdId(user.getAdminId());
		    
		    atchService.atchUpdate(atchVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/atchForm.do");
			model.addAttribute("hiddenName1", "atchId");
			model.addAttribute("hiddenValue1", atchVO.getAtchId());
			model.addAttribute("hiddenName2", "command");
			model.addAttribute("hiddenValue2", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 첨부파일 관리 > 글보기
	 * @param AtchVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atchView.do")
	public String atchView(@ModelAttribute("searchVO") AtchVO atchVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			atchVO = atchService.selectAtchView(atchVO);
			model.addAttribute("atchVO", atchVO);
			
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchVO.getAtchFileId());
			List<FileVO> atchFileList = fileMngService.selectFileInfs(fileVO);
			model.addAttribute("atchFileList", atchFileList);
			model.addAttribute("fileCnt", atchFileList.size());
			
			
			return "/admin/site/atchView";
		}
	}
	
	/**
	 * 관리자 페이지 > 사이트 관리 > 첨부파일 관리 > 삭제 action
	 * @param AtchVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atchDelete.do")
	public String atchDelete(@ModelAttribute("searchVO") AtchVO atchVO, HttpServletRequest request, ModelMap model) throws Exception {
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자ID 추가
			atchVO.setUpdId(user.getAdminId());
			
			// delete action
			atchService.atchDelete(atchVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/site/atchList.do");
			
			return "/common/message";
		}
	}
	
}
