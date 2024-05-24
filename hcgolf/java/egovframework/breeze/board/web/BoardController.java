package egovframework.breeze.board.web;

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
@RequestMapping("/_admin/board")
public class BoardController {

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
     * 관리자 > 게시판 관리 > list
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsList.do")
    public String bbsList(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
	    	
			BoardMasterVO vo = new BoardMasterVO();
	    	List<BoardMasterVO> boardList = egovBBSMasterService.selectBBSListPortlet(vo);
			model.addAttribute("boardList", boardList);
			
			if(boardList.size() == 0) {
				model.addAttribute("message", "등록된 게시판이 없습니다.");
				model.addAttribute("retType", ":back");
				return "/common/message";
			}
			
			if(boardVO.getBbsId() != null && boardVO.getBbsId() != "") {
				vo.setBbsId(boardVO.getBbsId());
			}else {
				vo.setBbsId(boardList.get(0).getBbsId());
				boardVO.setBbsId(boardList.get(0).getBbsId());
			}
			
			BoardMasterVO master = egovBBSMasterService.selectBBSMasterInf(vo);
	
			boardVO.setPageUnit(propertyService.getInt("pageUnit"));
			boardVO.setPageSize(propertyService.getInt("pageSize"));
	
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(Integer.parseInt(master.getBbsPageUnit()));
			paginationInfo.setPageSize(boardVO.getPageSize());
	
			boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
			boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
			Map<String, Object> map = egovArticleService.selectArticleList(boardVO);//2011.09.07
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			
			paginationInfo.setTotalRecordCount(totCnt);

			//카테고리 조회
			BoardCateVO boardCateVO = new BoardCateVO();
			boardCateVO.setBbsId(boardVO.getBbsId());
			if(master.getCateType01().equals("Y")) {	//카테고리 1 사용
				boardCateVO.setCateType("CATE01");
				Map<String, Object> map4 = egovBBSMasterService.selectBBSCateList(boardCateVO);
				model.addAttribute("cateList01", map4.get("resultList"));
			}
			if(master.getCateType02().equals("Y")) {	//카테고리 2 사용
				boardCateVO.setCateType("CATE02");
				Map<String, Object> map5 = egovBBSMasterService.selectBBSCateList(boardCateVO);
				model.addAttribute("cateList02", map5.get("resultList"));
			}
			if(master.getCateType03().equals("Y")) {	//카테고리 3 사용
				boardCateVO.setCateType("CATE03");
				Map<String, Object> map6 = egovBBSMasterService.selectBBSCateList(boardCateVO);
				model.addAttribute("cateList03", map6.get("resultList"));
			}

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("boardVO", boardVO);
			model.addAttribute("brdMstrVO", master);
			model.addAttribute("paginationInfo", paginationInfo);

			//항목 list 조회
			BoardItemVO boardItemVO = new BoardItemVO();
			boardItemVO.setBbsId(boardVO.getBbsId());
			boardItemVO.setItemFlag("list");
			List<BoardItemVO> resultList = egovBBSMasterService.selectBBSItemList(boardItemVO);
			model.addAttribute("itemList", resultList);

			if(master.getBbsTyCode().equals("BBST01")) {

				// 일반게시판 > 공지글 조회 후 리턴
				boardVO.setSearchNotice("Y");
				boardVO.setFirstIndex(0);
				boardVO.setRecordCountPerPage(3);
				Map<String, Object> map2 = egovArticleService.selectArticleList(boardVO);
				model.addAttribute("noticeList", map2.get("resultList"));

				return "/admin/board/bbsList";

			}else{
				return "/admin/board/bbsGallery";
			}
		}
    }
    
    /**
     * 관리자 > 게시판 관리 > 등록/수정 form
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsForm.do")
    public String bbsForm(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			BoardMasterVO bdMstr = new BoardMasterVO();
			bdMstr.setBbsId(boardVO.getBbsId());

			bdMstr = egovBBSMasterService.selectBBSMasterInf(bdMstr);
			model.addAttribute("bdMstr", bdMstr);

			//update 일때만 조회
			String command = request.getParameter("command")==null?"insert":request.getParameter("command");
			if(command.equals("update")){
			    if(bdMstr.getBbsTyCode().equals("BBST02")){
			    	FileVO fvo = new FileVO();
			    	fvo.setAtchFileId(boardVO.getAtchFileId());
			    	fvo.setImgFlag("thumbnail");
			    	FileVO thumbnail = fileMngService.selectFileInf(fvo);
			    	model.addAttribute("thumbnail", thumbnail);
			    }
			    boardVO = egovArticleService.selectArticleDetail(boardVO);
				model.addAttribute("selectVO", boardVO);
			}

			//카테고리 조회
			BoardCateVO boardCateVO = new BoardCateVO();
			boardCateVO.setBbsId(boardVO.getBbsId());
			if(bdMstr.getCateType01().equals("Y")) {	//카테고리 1 사용
				boardCateVO.setCateType("CATE01");
				Map<String, Object> map = egovBBSMasterService.selectBBSCateList(boardCateVO);
				model.addAttribute("cateList01", map.get("resultList"));
			}
			if(bdMstr.getCateType02().equals("Y")) {	//카테고리 2 사용
				boardCateVO.setCateType("CATE02");
				Map<String, Object> map2 = egovBBSMasterService.selectBBSCateList(boardCateVO);
				model.addAttribute("cateList02", map2.get("resultList"));
			}
			if(bdMstr.getCateType03().equals("Y")) {	//카테고리 3 사용
				boardCateVO.setCateType("CATE03");
				Map<String, Object> map3 = egovBBSMasterService.selectBBSCateList(boardCateVO);
				model.addAttribute("cateList03", map3.get("resultList"));
			}

			//항목 view&form 조회
			BoardItemVO boardItemVO = new BoardItemVO();
			boardItemVO.setBbsId(boardVO.getBbsId());
			boardItemVO.setItemFlag("view");
			List<BoardItemVO> resultList = egovBBSMasterService.selectBBSItemList(boardItemVO);
			for( int i = 0; i < resultList.size(); i++ ) {
				if(resultList.get(i).getFieldId().equals("NTT_CN")) {
					model.addAttribute("nttCnFlag", true);
				}
			}
			model.addAttribute("itemList", resultList);

			return "/admin/board/bbsForm";
		}
    }
    
    /**
     * 관리자 > 게시판 관리 > view
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsView.do")
    public String bbsView(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			BoardMasterVO master = new BoardMasterVO();
	
			// 조회수 증가 여부 지정
			boardVO.setPlusCount(false);
			BoardVO vo = egovArticleService.selectArticleDetail(boardVO);
		
			model.addAttribute("result", vo);
			
			master.setBbsId(boardVO.getBbsId());

			BoardMasterVO masterVo = egovBBSMasterService.selectBBSMasterInf(master);
		
			model.addAttribute("brdMstrVO", masterVo);
			
			if(masterVo.getBbsTyCode().equals("BBST02")){
		    	FileVO fvo = new FileVO();
		    	fvo.setAtchFileId(vo.getAtchFileId());
		    	fvo.setImgFlag("thumbnail");
		    	FileVO thumbnail = fileMngService.selectFileInf(fvo);
		    	model.addAttribute("thumbnail", thumbnail);
		    }

			//항목 view&form 조회
			BoardItemVO boardItemVO = new BoardItemVO();
			boardItemVO.setBbsId(boardVO.getBbsId());
			boardItemVO.setItemFlag("view");
			List<BoardItemVO> resultList = egovBBSMasterService.selectBBSItemList(boardItemVO);
			model.addAttribute("itemList", resultList);

			//답변 조회
			boardVO.setParnts(String.valueOf(boardVO.getNttId()));	// 부모글 번호
			BoardVO reply_result = egovArticleService.selectReplyArticle(boardVO);
		    model.addAttribute("reply_result", reply_result);
		
			return "/admin/board/bbsView";
		}
    }

    /**
     * 관리자 > 게시판 관리 > 등록 action
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
	       		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
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
		    
		    board.setNtcrId(user.getAdminId()); //게시물 통계 집계를 위해 등록자 ID 저장
		    board.setFrstRegisterId(user.getAdminId());
		    
		    board.setNttCn(unscript(board.getNttCn()));	// XSS 방지
		    board.setNttCn(StringUtil.getStringToTag(board.getNttCn()));
		    
		    egovArticleService.insertArticle(board);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsView.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", board.getBbsId());
			model.addAttribute("hiddenName2", "nttId");
			model.addAttribute("hiddenValue2", board.getNttId());
			return "/common/message";
		}
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

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
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
		    board.setLastUpdusrId(user.getAdminId());
		    
		    egovArticleService.updateArticle(board);

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsView.do");
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
		
			return "/common/message";
		}
	}

    /**
     * 게시물에 대한 내용을 삭제한다.
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

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 삭제자 ID 저장
			board.setLastUpdusrId(user.getAdminId());
			egovArticleService.deleteArticle(board);

			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsList.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", board.getBbsId());
			model.addAttribute("hiddenName2", "nttId");
			model.addAttribute("hiddenValue2", board.getNttId());
		    
		    return "/common/message";
		}
    }
    
    /**
     * 관리자 > 게시판 관리 > 답변 등록/수정 form
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsReplyForm.do")
    public String bbsReplyForm(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			BoardMasterVO bdMstr = new BoardMasterVO();
			bdMstr.setBbsId(boardVO.getBbsId());

			bdMstr = egovBBSMasterService.selectBBSMasterInf(bdMstr);
			model.addAttribute("bdMstr", bdMstr);

			//update 일때만 조회
			String command = request.getParameter("command")==null?"insert":request.getParameter("command");
			if(command.equals("update")){
			    boardVO = egovArticleService.selectArticleDetail(boardVO);
				model.addAttribute("selectVO", boardVO);
			}

			return "/admin/board/bbsReplyForm";
		}
    }

    /**
     * 관리자 > 게시판 관리 > 답변 등록 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsReplyInsert.do")
    public String bbsReplyInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, SessionStatus status, ModelMap model, HttpServletRequest request) throws Exception {
	       		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
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
		    board.setNtcrId(user.getAdminId()); 											//게시물 통계 집계를 위해 등록자 ID 저장
		    board.setFrstRegisterId(user.getAdminId());

		    board.setReplyAt("Y");															// 답글 플래그 Y
		    board.setParnts(Long.toString(boardVO.getNttId()));								// 부모글 번호 등록
		    board.setSortOrdr(boardVO.getSortOrdr());										// 정렬 순서 현재글(부모글)의 sortOrdr
		    board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));	// 부모글의 ReplyLc + 1
		    
		    board.setNttCn(unscript(board.getNttCn()));										// XSS 방지
		    board.setNttCn(StringUtil.getStringToTag(board.getNttCn()));
		    
		    egovArticleService.insertArticle(board);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsView.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", board.getBbsId());
			model.addAttribute("hiddenName2", "nttId");
			model.addAttribute("hiddenValue2", boardVO.getNttId());
			return "/common/message";
		}
	}

    /**
     * 관리자 > 게시판 관리 > 답변 수정 action
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsReplyUpdate.do")
    public String bbsReplyUpdate(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
	    @ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model,
	    SessionStatus status, HttpServletRequest request) throws Exception {

    	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
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
		    board.setLastUpdusrId(user.getAdminId());
		    boardVO.setNttId(Long.parseLong(board.getParnts()));
		    
		    egovArticleService.updateArticle(board);

			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsView.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", board.getBbsId());
			model.addAttribute("hiddenName2", "nttId");
			model.addAttribute("hiddenValue2", boardVO.getNttId());
			model.addAttribute("hiddenName3", "pageIndex");
			model.addAttribute("hiddenValue3", boardVO.getPageIndex());
			model.addAttribute("hiddenName4", "searchCnd");
			model.addAttribute("hiddenValue4", boardVO.getSearchCnd());
			model.addAttribute("hiddenName5", "searchWrd");
			model.addAttribute("hiddenValue5", boardVO.getSearchWrd());
		
			return "/common/message";
		}
	}

    /**
     * 게시물에 대한 답변을 삭제한다.
     * 
     * @param boardVO
     * @param board
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsReplyDelete.do")
    public String bbsReplyDelete(@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board, @ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model, HttpServletRequest request) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 삭제자 ID 저장
			board.setLastUpdusrId(user.getAdminId());
		    boardVO.setNttId(Long.parseLong(board.getParnts()));

			egovArticleService.deleteArticle(board);

			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsView.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", board.getBbsId());
			model.addAttribute("hiddenName2", "nttId");
			model.addAttribute("hiddenValue2", boardVO.getNttId());
		    
		    return "/common/message";
		}
    }
}