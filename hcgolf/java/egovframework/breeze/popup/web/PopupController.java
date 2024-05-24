package egovframework.breeze.popup.web;

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
import egovframework.breeze.common.ThumbnailUtil;
import egovframework.breeze.popup.service.PopupService;
import egovframework.breeze.popup.service.PopupVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/popup")
public class PopupController {

	@Resource(name="popupService")
	private PopupService popupService;

	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;
	
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;
	
	/**
	 * 관리자 > 메인 관리 > 인포존 관리
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/infozoneList.do")
	public String infozoneList(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			popupVO.setFlag("INFO");

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(popupVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(popupVO.getPageUnit());
			paginationInfo.setPageSize(popupVO.getPageSize());
			
			popupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			popupVO.setLastIndex(paginationInfo.getLastRecordIndex());
			popupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String,Object> map = popupService.selectPopupList(popupVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "/admin/popup/infozoneList";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 인포존 관리 > 등록/수정 페이지
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/infozoneForm.do")
	public String infozoneForm(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// update일 때만 조회
			String command = popupVO.getCommand() == null ? "insert" : popupVO.getCommand();
			if(command.equals("update")) {
				popupVO = popupService.selectPopupView(popupVO);
				model.addAttribute("popupVO", popupVO);
				
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(popupVO.getAtchFileId());
				fileVO.setImgFlag("pcImg");
				FileVO atchFile = fileMngService.selectFileInf(fileVO);
				model.addAttribute("pcImg", atchFile);
			}
			
			return "/admin/popup/infozoneForm";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 인포존관리 / 등록 action
	 * @param popupVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/infozoneInsert.do")
	public String infozoneInsert(PopupVO popupVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// 파일 저장
			List<FileVO> result = null;
			String atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if(!files.isEmpty()){
				result = fileUtil.parseFileInf(files, "POPUP_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
				popupVO.setAtchFileId(atchFileId);
				
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(atchFileId, 350, 395);
			}
			
			// 등록자ID, 수정자ID 추가
			popupVO.setRegId(user.getAdminId());
			popupVO.setUpdId(user.getAdminId());
			
			// insert atcion
			popupService.popupInsert(popupVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/infozoneList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 인포존관리 / 수정 action
	 * @param popupVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/infozoneUpdate.do")
	public String infozoneUpdate(PopupVO popupVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model)throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			
			// 새로 첨부된 파일이 있다면
			if(!files.get("pcImg").isEmpty() ) {
				// 기존 저장된 파일 정보 삭제
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(popupVO.getAtchFileId());
				fvo.setImgFlag("pcImg");
				fvo = fileMngService.selectFileInf(fvo);
				fileMngService.deleteFileInf(fvo);
				
				// 새로 첨부된 파일 저장
				fvo = new FileVO();
				fvo.setAtchFileId(popupVO.getAtchFileId());
				int maxSn = fileMngService.getMaxFileSN(fvo);
				
				List<FileVO> result = fileUtil.parseFileInf(files, "POPUP_", maxSn, popupVO.getAtchFileId(), "");
				fileMngService.updateFileInfs(result);
				
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(popupVO.getAtchFileId(), 350, 395);
			}
			
			// 수정자ID 추가
			popupVO.setUpdId(user.getAdminId());
			
			popupService.popupUpdate(popupVO);
			
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/infozoneForm.do");
			model.addAttribute("hiddenName1", "popupId");
			model.addAttribute("hiddenValue1", popupVO.getPopupId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", popupVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", popupVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", popupVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 인포존관리 > 삭제 action
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/infozoneDelete.do")
	public String infozoneDelete(PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			// 수정자ID 추가
			popupVO.setUpdId(user.getAdminId());
			
			// delete action
			popupService.popupDelete(popupVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/infozoneList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 팝업 관리
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popupList.do")
	public String popupList(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(popupVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(popupVO.getPageUnit());
			paginationInfo.setPageSize(popupVO.getPageSize());
			
			popupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			popupVO.setLastIndex(paginationInfo.getLastRecordIndex());
			popupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			popupVO.setFlag("POPUP");
			
			Map<String,Object> map = popupService.selectPopupList(popupVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "/admin/popup/popupList";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 팝업 관리 > 등록/수정 페이지
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popupForm.do")
	public String popUpForm(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// update 일 때만 조회
			String command = popupVO.getCommand() == null ? "insert" : popupVO.getCommand();
			if(command.equals("update")) {
				popupVO = popupService.selectPopupView(popupVO);
				model.addAttribute("popupVO", popupVO);
			}
			return "/admin/popup/popupForm";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 팝업관리 / 등록 action
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popupInsert.do")
	public String popupInsert(PopupVO popupVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {
			// 파일 저장
			List<FileVO> result = null;
			String atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if(!files.isEmpty()){
				result = fileUtil.parseFileInf(files, "POPUP_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
				popupVO.setAtchFileId(atchFileId);

				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(popupVO.getAtchFileId(), Integer.parseInt(popupVO.getSizeWidth()), Integer.parseInt(popupVO.getSizeHeight()));
			}

			
			// 등록자ID, 수정자ID 추가
			popupVO.setRegId(user.getAdminId());
			popupVO.setUpdId(user.getAdminId());
			
			// insert atcion
			popupService.popupInsert(popupVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/popupList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 팝업관리 > 수정 action
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popupUpdate.do")
	public String popupUpdate(PopupVO popupVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			// 파일이 있다면 저장
			List<FileVO> result = null;
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if(!files.get("atchFile").isEmpty()){
				result = fileUtil.parseFileInf(files, "POPUP_", 0, "", "");
				String atchFileId = fileMngService.insertFileInfs(result);
				popupVO.setAtchFileId(atchFileId);
				
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(popupVO.getAtchFileId(), Integer.parseInt(popupVO.getSizeWidth()), Integer.parseInt(popupVO.getSizeHeight()));
			}
			
			
			// 수정자ID 추가
			popupVO.setUpdId(user.getAdminId());
			
			popupService.popupUpdate(popupVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/popupForm.do");
			model.addAttribute("hiddenName1", "popupId");
			model.addAttribute("hiddenValue1", popupVO.getPopupId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", popupVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", popupVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", popupVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 팝업관리 > 삭제 action
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popupDelete.do")
	public String popupDelete(PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			// 수정자ID 추가
			popupVO.setUpdId(user.getAdminId());
			
			// delete action
			popupService.popupDelete(popupVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/popupList.do");
			
			return "/common/message";
		}
	}
	

	/**
	 * 관리자 > 메인 관리 > 배너 관리 > 리스트
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bannerList.do")
	public String bannerList(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			popupVO.setFlag("BANNER");

			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(popupVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(popupVO.getPageUnit());
			paginationInfo.setPageSize(popupVO.getPageSize());
			
			popupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			popupVO.setLastIndex(paginationInfo.getLastRecordIndex());
			popupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String,Object> map = popupService.selectPopupList(popupVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
			
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "/admin/popup/bannerList";
		}
	}

	
	/**
	 * 관리자 > 메인 관리 > 배너 관리 > 등록/수정 페이지
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bannerForm.do")
	public String bannerForm(@ModelAttribute("searchVO") PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// update일 때만 조회
			String command = popupVO.getCommand() == null ? "insert" : popupVO.getCommand();
			if(command.equals("update")) {
				popupVO = popupService.selectPopupView(popupVO);
				model.addAttribute("popupVO", popupVO);
				
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(popupVO.getAtchFileId());
				fileVO.setImgFlag("pcImg");
				FileVO atchFile = fileMngService.selectFileInf(fileVO);
				model.addAttribute("pcImg", atchFile);
			}
			
			return "/admin/popup/bannerForm";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 배너 관리 / 등록 action
	 * @param popupVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bannerInsert.do")
	public String bannerInsert(PopupVO popupVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// 파일 저장
			List<FileVO> result = null;
			String atchFileId = "";
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if(!files.isEmpty()){
				result = fileUtil.parseFileInf(files, "POPUP_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
				popupVO.setAtchFileId(atchFileId);
				
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(atchFileId, 379, 120);
			}
			
			// 등록자ID, 수정자ID 추가
			popupVO.setRegId(user.getAdminId());
			popupVO.setUpdId(user.getAdminId());
			
			// insert atcion
			popupService.popupInsert(popupVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/bannerList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 배너 관리 / 수정 action
	 * @param popupVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bannerUpdate.do")
	public String bannerUpdate(PopupVO popupVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model)throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			
			// 새로 첨부된 파일이 있다면
			if(!files.get("pcImg").isEmpty() ) {
				// 기존 저장된 파일 정보 삭제
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(popupVO.getAtchFileId());
				fvo.setImgFlag("pcImg");
				fvo = fileMngService.selectFileInf(fvo);
				fileMngService.deleteFileInf(fvo);
				
				// 새로 첨부된 파일 저장
				fvo = new FileVO();
				fvo.setAtchFileId(popupVO.getAtchFileId());
				int maxSn = fileMngService.getMaxFileSN(fvo);
				
				List<FileVO> result = fileUtil.parseFileInf(files, "POPUP_", maxSn, popupVO.getAtchFileId(), "");
				fileMngService.updateFileInfs(result);
				
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(popupVO.getAtchFileId(), 379, 120);
			}
			
			// 수정자ID 추가
			popupVO.setUpdId(user.getAdminId());
			
			popupService.popupUpdate(popupVO);
			
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/bannerForm.do");
			model.addAttribute("hiddenName1", "popupId");
			model.addAttribute("hiddenValue1", popupVO.getPopupId());
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", popupVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", popupVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", popupVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 메인 관리 > 배너 관리 > 삭제 action
	 * @param popupVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bannerDelete.do")
	public String BannerDelete(PopupVO popupVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {

			// 수정자ID 추가
			popupVO.setUpdId(user.getAdminId());
			
			// delete action
			popupService.popupDelete(popupVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/popup/bannerList.do");
			
			return "/common/message";
		}
	}
}
