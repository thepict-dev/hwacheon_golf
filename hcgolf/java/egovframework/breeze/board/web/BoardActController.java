package egovframework.breeze.board.web;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.common.StringUtil;
import egovframework.breeze.common.ThumbnailUtil;
import egovframework.breeze.member.service.MemberVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardCateVO;
import egovframework.com.cop.bbs.service.BoardItemVO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_board")
public class BoardActController {

    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;

	@Resource(name = "EgovArticleService")
    private EgovArticleService egovArticleService;

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
     * 사용자 > 게시판 > 등록 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsInsert.do")
    public String bbsInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request) throws Exception {
	    
    	MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
		
		if(user == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}else {
			List<FileVO> result = null;
		    String atchFileId = "";
		    
		    final Map<String, MultipartFile> files = multiRequest.getFileMap();
		    if (!files.isEmpty()) {
		    	result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
		    	atchFileId = fileMngService.insertFileInfs(result);
				// 썸네일 생성
				ThumbnailUtil.generateThumbnail(atchFileId);
		    }
		    board.setAtchFileId(atchFileId);
		    board.setBbsId(board.getBbsId());
		    
		    board.setNtcrId(user.getMemberId()); //게시물 통계 집계를 위해 등록자 ID 저장
		    board.setFrstRegisterId(user.getMemberId());
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    board.setNttCn(StringUtil.getStringToTag(board.getNttCn()));
		    board.setNttSj(StringUtil.getStringToTag(board.getNttSj()));
		    
		    egovArticleService.insertArticle(board);
		    
		    String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
		    
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", returnUrl);
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", board.getBbsId());
			model.addAttribute("hiddenName2", "nttId");
			model.addAttribute("hiddenValue2", board.getNttId());
			model.addAttribute("hiddenName3", "flag");
			model.addAttribute("hiddenValue3", "view");
			model.addAttribute("BoardVO",boardVO);
			return "/common/message";
		}
	}
    
    /**
     * 사용자 > 게시판 > 비밀번호 체크 후 등록 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsInsertPw.do")
    public String bbsInsertPw(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request) throws Exception {
	    
		List<FileVO> result = null;
	    String atchFileId = "";
	    
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
	    	result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
	    	atchFileId = fileMngService.insertFileInfs(result);
			// 썸네일 생성
			ThumbnailUtil.generateThumbnail(atchFileId);
	    }
	    board.setAtchFileId(atchFileId);
	    board.setBbsId(board.getBbsId());
	    
	    board.setNtcrId(board.getNtcrNm());
	    board.setFrstRegisterId(board.getNtcrNm());
	    
	    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
	    board.setNttCn(StringUtil.getStringToTag(board.getNttCn()));
	    board.setNttSj(StringUtil.getStringToTag(board.getNttSj()));
	    
	    egovArticleService.insertArticle(board);
	    
	    String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
	    
		model.addAttribute("message", "등록이 완료되었습니다.");
		model.addAttribute("retType", ":submit");
		model.addAttribute("retUrl", returnUrl);
		model.addAttribute("hiddenName1", "bbsId");
		model.addAttribute("hiddenValue1", board.getBbsId());
		model.addAttribute("hiddenName2", "nttId");
		model.addAttribute("hiddenValue2", board.getNttId());
		model.addAttribute("hiddenName3", "flag");
		model.addAttribute("hiddenValue3", "view");
		model.addAttribute("hiddenName4", "password");
		model.addAttribute("hiddenValue4", boardVO.getPassword());
		model.addAttribute("BoardVO",boardVO);
		return "/common/message";	
	}

    /**
     * 관리자 > 게시판 관리 > 수정 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsUpdate.do")
    public String bbsUpdate(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status, HttpServletRequest request) throws Exception {
    	
    	MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
		
		if(user == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}else {
			
			BoardVO tmpBoard = egovArticleService.selectArticleDetail(boardVO);
			
			if(tmpBoard == null) {
				model.addAttribute("message", "시스템 오류입니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}else {
				String ntcrId = tmpBoard.getNtcrId()==null?"":tmpBoard.getNtcrId();
				if(ntcrId.equals(user.getMemberId())) {
					String atchFileId = boardVO.getAtchFileId();
					
				    final Map<String, MultipartFile> files = multiRequest.getFileMap();
				    if (!files.isEmpty()) {
						if ("".equals(atchFileId)) {
					    	List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
					    	atchFileId = fileMngService.insertFileInfs(result);
						    board.setAtchFileId(atchFileId);
				    	} else {
						    FileVO fvo = new FileVO();
						    fvo.setAtchFileId(atchFileId);
						    int cnt = fileMngService.getMaxFileSN(fvo);
						    List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
						    fileMngService.updateFileInfs(_result);
						}

						// 썸네일 생성
						ThumbnailUtil.generateThumbnail(atchFileId);
				    }
				    
				    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
				    board.setNttCn(StringUtil.getStringToTag(board.getNttCn()));
				    board.setNttSj(StringUtil.getStringToTag(board.getNttSj()));
				    board.setLastUpdusrId(user.getMemberId());
				    
				    egovArticleService.updateArticle(board);
				    
				    String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
				    
					model.addAttribute("message", "수정이 완료되었습니다.");
					model.addAttribute("retType", ":submit");
					model.addAttribute("retUrl", returnUrl);
					model.addAttribute("hiddenName1", "bbsId");
					model.addAttribute("hiddenValue1", board.getBbsId());
					model.addAttribute("hiddenName2", "nttId");
					model.addAttribute("hiddenValue2", board.getNttId());
					model.addAttribute("hiddenName3", "pageIndex");
					model.addAttribute("hiddenValue3", boardVO.getPageIndex());
					model.addAttribute("hiddenName4", "searchCnd");
					model.addAttribute("hiddenValue4", boardVO.getSearchCnd());
					model.addAttribute("hiddenName5", "searchWrd");
					model.addAttribute("hiddenValue5", boardVO.getSearchWrd());
					model.addAttribute("hiddenName6", "flag");
					model.addAttribute("hiddenValue6", "view");
					model.addAttribute("hiddenName7", "searchCate1");
					model.addAttribute("hiddenValue7", boardVO.getSearchCate1());
				
					return "/common/message";
				}else {
					model.addAttribute("message", "시스템 오류입니다.");
					model.addAttribute("retType", ":back");
					
					return "/common/message";
				}
			}
		}
	}
    
    /**
     * 관리자 > 게시판 관리 > 비밀번호 체크 후 수정 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsUpdatePw.do")
    public String bbsUpdatePw(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status, HttpServletRequest request) throws Exception {
    	    	
		String atchFileId = boardVO.getAtchFileId();
		
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if (!files.isEmpty()) {
			if ("".equals(atchFileId)) {
		    	List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
		    	atchFileId = fileMngService.insertFileInfs(result);
			    board.setAtchFileId(atchFileId);
	    	} else {
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
			    fileMngService.updateFileInfs(_result);
			}

			// 썸네일 생성
			ThumbnailUtil.generateThumbnail(atchFileId);
	    }
	    
	    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
	    board.setNttCn(StringUtil.getStringToTag(board.getNttCn()));
	    board.setNttSj(StringUtil.getStringToTag(board.getNttSj()));
	    board.setLastUpdusrId(board.getNtcrNm());
	    
	    egovArticleService.updateArticle(board);
	    
	    String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
	    
		model.addAttribute("message", "수정이 완료되었습니다.");
		model.addAttribute("retType", ":submit");
		model.addAttribute("retUrl", returnUrl);
		model.addAttribute("hiddenName1", "bbsId");
		model.addAttribute("hiddenValue1", board.getBbsId());
		model.addAttribute("hiddenName2", "nttId");
		model.addAttribute("hiddenValue2", board.getNttId());
		model.addAttribute("hiddenName3", "pageIndex");
		model.addAttribute("hiddenValue3", boardVO.getPageIndex());
		model.addAttribute("hiddenName4", "searchCnd");
		model.addAttribute("hiddenValue4", boardVO.getSearchCnd());
		model.addAttribute("hiddenName5", "searchWrd");
		model.addAttribute("hiddenValue5", boardVO.getSearchWrd());
		model.addAttribute("hiddenName6", "flag");
		model.addAttribute("hiddenValue6", "view");
		model.addAttribute("hiddenName7", "password");
		model.addAttribute("hiddenValue7", board.getPassword());
	
		return "/common/message";
	}

    /**
     * 사용자 > 게시판 > 삭제 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsDelete.do")
    public String bbsDelete(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board, @ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model, HttpServletRequest request) throws Exception {
    	
    	MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
		
		if(user == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}else {
			
			BoardVO tmpBoard = egovArticleService.selectArticleDetail(boardVO);
			
			if(tmpBoard == null) {
				model.addAttribute("message", "시스템 오류입니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}else {
				String ntcrId = tmpBoard.getNtcrId()==null?"":tmpBoard.getNtcrId();
				if(ntcrId.equals(user.getMemberId())) {
					// 삭제자 ID 저장
					board.setLastUpdusrId(user.getMemberId());
					egovArticleService.deleteArticle(board);
					
					String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
					
					model.addAttribute("message", "삭제가 완료되었습니다.");
					model.addAttribute("retType", ":submit");
					model.addAttribute("retUrl", returnUrl);
					model.addAttribute("hiddenName1", "bbsId");
					model.addAttribute("hiddenValue1", board.getBbsId());
					model.addAttribute("hiddenName2", "nttId");
					model.addAttribute("hiddenValue2", board.getNttId());
					model.addAttribute("hiddenName3", "flag");
					model.addAttribute("hiddenValue3", "list");
				    
				    return "/common/message";
				}else {
					model.addAttribute("message", "시스템 오류입니다.");
					model.addAttribute("retType", ":back");
					
					return "/common/message";
				}
			}
		}
    }
    
    /**
     * 사용자 > 게시판 > 삭제 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsDeletePw.do")
    public String bbsDeletePw(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board, @ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model, HttpServletRequest request) throws Exception {
    	    	
		// 삭제자 ID 저장
		board.setLastUpdusrId(board.getNtcrNm());
		egovArticleService.deleteArticle(board);
		
		String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
		
		model.addAttribute("message", "삭제가 완료되었습니다.");
		model.addAttribute("retType", ":submit");
		model.addAttribute("retUrl", returnUrl);
		model.addAttribute("hiddenName1", "bbsId");
		model.addAttribute("hiddenValue1", board.getBbsId());
		model.addAttribute("hiddenName2", "nttId");
		model.addAttribute("hiddenValue2", board.getNttId());
		model.addAttribute("hiddenName3", "flag");
		model.addAttribute("hiddenValue3", "list");
	    
	    return "/common/message";
    }
    
    /**
     * 사용자 > 게시판 > 첨부파일 삭제
     * @param fileVO
     * @param board
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteFileInfs.do")
    public String deleteFileInfs(@ModelAttribute("searchVO") FileVO fileVO, BoardVO board, HttpServletRequest request, ModelMap model) throws Exception {
    	
    	MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
		
		if(user == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}else {
			BoardVO tmpBoard = egovArticleService.selectArticleDetail(board);
			
			if(tmpBoard == null) {
				model.addAttribute("message", "시스템 오류입니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}else {
				String ntcrId = tmpBoard.getNtcrId()==null?"":tmpBoard.getNtcrId();
				if(ntcrId.equals(user.getMemberId())) {
					fileMngService.deleteFileInf(fileVO);
				    
				    List<FileVO> fileList = fileMngService.selectFileInfs(fileVO);
				    if(fileList == null || fileList.size() == 0){//리스트가 널이거나 첨부파일이 없는경우
				    	egovArticleService.deleteAtchFileId(fileVO.getAtchFileId());
				    }
				    
				    String returnUrl = (board.getReturnUrl()==null||board.getReturnUrl().equals(""))?"/":board.getReturnUrl();
				    
				    model.addAttribute("message", "첨부파일 삭제가 완료되었습니다.");
					model.addAttribute("retType", ":submit");
					model.addAttribute("retUrl", returnUrl);
					model.addAttribute("hiddenName1", "bbsId");
					model.addAttribute("hiddenValue1", board.getBbsId());
					model.addAttribute("hiddenName2", "nttId");
					model.addAttribute("hiddenValue2", board.getNttId());
					model.addAttribute("hiddenName3", "pageIndex");
					model.addAttribute("hiddenValue3", board.getPageIndex());
					model.addAttribute("hiddenName4", "searchCnd");
					model.addAttribute("hiddenValue4", board.getSearchCnd());
					model.addAttribute("hiddenName5", "searchWrd");
					model.addAttribute("hiddenValue5", board.getSearchWrd());
					model.addAttribute("hiddenName6", "flag");
					model.addAttribute("hiddenValue6", "form");
					model.addAttribute("hiddenName7", "command");
					model.addAttribute("hiddenValue7", "update");
				    
			    	return "/common/message";
				}else {
					model.addAttribute("message", "시스템 오류입니다.");
					model.addAttribute("retType", ":back");
					
					return "/common/message";
				}
			}
		}
    }
    
    /**
     */
    @RequestMapping("/pwCheck.do")
    public void pwCheck(BoardVO boardVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	JSONObject jObj = new JSONObject();
    	
    	int pwCheckCnt = egovArticleService.selectPwCheck(boardVO);
    	if(pwCheckCnt > 0) {
    		jObj.put("pwCheck", "Y");
    	}else {
    		jObj.put("pwCheck", "N");
    	}
    	response.setContentType("text/json;charset=utf-8");
    	PrintWriter pr = response.getWriter();
    	pr.write(jObj.toString());
    	pr.flush();
    	pr.close();
	}
    
    
}