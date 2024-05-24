package egovframework.breeze.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.skin.service.SkinService;
import egovframework.breeze.skin.service.SkinVO;
import egovframework.com.cop.bbs.service.BoardCateVO;
import egovframework.com.cop.bbs.service.BoardItemVO;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("/_admin/board")
public class BoardMasterController {

    @Resource(name = "EgovBBSMasterService")
    private EgovBBSMasterService egovBBSMasterService;

	@Resource(name = "EgovArticleService")
    private EgovArticleService egovArticleService;
	
	/** skinService */
	@Resource(name = "skinService")
	private SkinService skinService;
    
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
     * 관리자 > 사이트 관리 > 게시판 관리 > list
     * 
     * @param boardVO
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsMasterList.do")
    public String bbsMasterList(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
		
			PaginationInfo paginationInfo = new PaginationInfo();

			paginationInfo.setCurrentPageNo(boardMasterVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(boardMasterVO.getPageUnit());
			paginationInfo.setPageSize(boardMasterVO.getPageSize());
		
			boardMasterVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardMasterVO.setLastIndex(paginationInfo.getLastRecordIndex());
			boardMasterVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
			Map<String, Object> map = egovBBSMasterService.selectBBSMasterInfs(boardMasterVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);
		
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));	
			model.addAttribute("paginationInfo", paginationInfo);
	
			return "/admin/board/bbsMasterList";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 등록/수정 form
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsMasterForm.do")
    public String bbsMasterForm(@ModelAttribute("searchVO") BoardMasterVO boardMasterVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// 스킨 list 조회
			SkinVO skinVO = new SkinVO();
			skinVO.setFirstIndex(0);
			skinVO.setSkinType("BBS");
			skinVO.setRecordCountPerPage(9999);
			Map<String, Object> map = skinService.selectSkinList(skinVO);
			model.addAttribute("skinList", map.get("resultList"));

			//update 일때만 조회
			String command = request.getParameter("command")==null?"insert":request.getParameter("command");
			if(command.equals("update")){
				boardMasterVO = egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
				model.addAttribute("selectVO", boardMasterVO);
			}
			return "/admin/board/bbsMasterForm";
		}
    }
	
	/**
	 * 관리자 > 사이트 관리 > 게시판 관리 > 등록 action
	 * @param layoutVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bbsMasterInsert.do")
	public String bbsMasterInsert(BoardMasterVO boardMasterVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 등록자ID, 수정자ID 추가
			boardMasterVO.setFrstRegisterId(user.getAdminId());
			boardMasterVO.setLastUpdusrId(user.getAdminId());
			
			// insert action
			egovBBSMasterService.insertBBSMasterInf(boardMasterVO);

			// db에 list 항목을 row별로 저장
			BoardItemVO boardItemVO = new BoardItemVO();
			String[] itemList = {"ROWNUM@@번호@@10@@Y@@N@@list","NTT_SJ@@제목@@*@@N@@Y@@list","ATCH_FILE_ID@@첨부파일@@10@@Y@@N@@list","NTCR_NM@@작성자@@15@@Y@@N@@list","REG_DATE@@작성일@@15@@Y@@N@@list","RDCNT@@조회수@@15@@Y@@N@@list"};
			for (int i = 0; i < itemList.length; i++) {
				String[] itemNode = itemList[i].split("@@");
				boardItemVO.setBbsId(boardMasterVO.getBbsId());
				boardItemVO.setFieldId(itemNode[0]);
				boardItemVO.setItemName(itemNode[1]);
				boardItemVO.setItemPercent(itemNode[2]);
				boardItemVO.setMobFlag(itemNode[3]);
				boardItemVO.setSearchFlag(itemNode[4]);
				boardItemVO.setItemOrder(Integer.toString(i+1));
				boardItemVO.setItemFlag(itemNode[5]);

				// 등록 action
				egovBBSMasterService.insertDefaultBBSItem(boardItemVO);
			}

			// db에 view 항목을 row별로 저장
			boardItemVO = new BoardItemVO();
			String[] itemViewList = {"NTCR_NM@@작성자@@view","NTT_SJ@@제목@@view","ATCH_FILE_ID@@첨부파일@@view","NTT_CN@@내용@@view"};
			for (int i = 0; i < itemViewList.length; i++) {
				String[] itemNode = itemViewList[i].split("@@");
				boardItemVO.setBbsId(boardMasterVO.getBbsId());
				boardItemVO.setFieldId(itemNode[0]);
				boardItemVO.setItemName(itemNode[1]);
				boardItemVO.setItemOrder(Integer.toString(i+1));
				boardItemVO.setItemFlag(itemNode[2]);

				// 등록 action
				egovBBSMasterService.insertDefaultBBSItem(boardItemVO);
			}
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsMasterList.do");
			
			return "/common/message";
		}
	}
    

    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 수정 action
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsMasterUpdate.do")
    public String bbsMasterUpdate(BoardMasterVO boardMasterVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{
			// 수정자ID 추가
			boardMasterVO.setLastUpdusrId(user.getAdminId());

		    egovBBSMasterService.updateBBSMasterInf(boardMasterVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsMasterForm.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", boardMasterVO.getBbsId());
			model.addAttribute("hiddenName2", "command");
			model.addAttribute("hiddenValue2", "update");
			
			return "/common/message";
		}
    }

    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 삭제 action
     * 
     * @param boardMasterVO
     * @param boardMaster
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsMasterDelete.do")
    public String bbsMasterDelete(BoardMasterVO boardMasterVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else{

			// 수정자ID 추가
			boardMasterVO.setLastUpdusrId(user.getAdminId());
			
			// delete action
			egovBBSMasterService.deleteBBSMasterInf(boardMasterVO);
			
			model.addAttribute("message", "삭제가 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsMasterList.do");
			
			return "/common/message";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 항목관리 view
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsItemView.do")
    public String bbsItemView(@ModelAttribute("searchVO") BoardItemVO boardItemVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			BoardMasterVO master = new BoardMasterVO();
			master = egovBBSMasterService.selectBBSMasterInf(boardItemVO);
			model.addAttribute("brdMstrVO", master);

			//list 항목 조회
			boardItemVO.setItemFlag("list");
			List<BoardItemVO> resultList1 = egovBBSMasterService.selectBBSItemList(boardItemVO);
			model.addAttribute("itemList", resultList1);

			//view&form 항목 조회
			boardItemVO.setItemFlag("view");
			List<BoardItemVO> resultList2 = egovBBSMasterService.selectBBSItemList(boardItemVO);
			model.addAttribute("viewList", resultList2);

			return "/admin/board/bbsItemView";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 항목관리 등록/수정 form
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsItemForm.do")
    public String bbsItemForm(@ModelAttribute("searchVO") BoardItemVO boardItemVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			//필드 조회
			Map<String, Object> map = egovBBSMasterService.getBoardField(boardItemVO);
			model.addAttribute("fieldList", map.get("resultList"));

			String itemFlag = request.getParameter("itemFlag")==null?"":request.getParameter("itemFlag");
			boardItemVO.setItemFlag(itemFlag);

			List<BoardItemVO> resultList = egovBBSMasterService.selectBBSItemList(boardItemVO);
			model.addAttribute("resultList", resultList);
			model.addAttribute("itemVO", boardItemVO);

			return "/admin/board/bbsItemForm";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 항목관리 저장 (등록/수정)
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsItemSave.do")
    public String bbsItemSave(@ModelAttribute("searchVO") BoardItemVO boardItemVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			// 삭제 action
			egovBBSMasterService.bbsItemDelete(boardItemVO);

			// db에 항목을 row별로 저장
			String itemListStr = boardItemVO.getItemList();
			int order = 0;
			if ((itemListStr != null) && (!(itemListStr.equals("")))) {
				String[] itemList = itemListStr.split(",");
				for (int i = 0; i < itemList.length; i++) {
					order++;
					String[] itemNode = itemList[i].split("@@");
					boardItemVO.setFieldId(itemNode[0]);
					boardItemVO.setItemName(itemNode[1]);
					if(boardItemVO.getItemFlag().equals("list")) {
						boardItemVO.setItemPercent(itemNode[2]);
						boardItemVO.setMobFlag(itemNode[3]);
						boardItemVO.setSearchFlag(itemNode[4]);
					}
					boardItemVO.setItemOrder(Integer.toString(order));

					// 등록 action
				    egovBBSMasterService.saveBBSItem(boardItemVO);
				}
			}
			
			model.addAttribute("message", "저장이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsItemView.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", boardItemVO.getBbsId());
			model.addAttribute("hiddenName2", "itemFlag");
			model.addAttribute("hiddenValue2", boardItemVO.getItemFlag());
			
			return "/common/message";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 카테고리 관리 view
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsCateView.do")
    public String bbsCateView(@ModelAttribute("searchVO") BoardCateVO boardCateVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			BoardMasterVO master = new BoardMasterVO();
			master = egovBBSMasterService.selectBBSMasterInf(boardCateVO);
			model.addAttribute("brdMstrVO", master);

			//카테고리 1 조회
			boardCateVO.setCateType("CATE01");
			Map<String, Object> map = egovBBSMasterService.selectBBSCateList(boardCateVO);
			model.addAttribute("cateList1", map.get("resultList"));

			//카테고리 2 조회
			boardCateVO.setCateType("CATE02");
			Map<String, Object> map2 = egovBBSMasterService.selectBBSCateList(boardCateVO);
			model.addAttribute("cateList2", map2.get("resultList"));

			//카테고리 3 조회
			boardCateVO.setCateType("CATE03");
			Map<String, Object> map3 = egovBBSMasterService.selectBBSCateList(boardCateVO);
			model.addAttribute("cateList3", map3.get("resultList"));

			return "/admin/board/bbsCateView";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 카테고리 관리 등록/수정 form
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsCateForm.do")
    public String bbsCateForm(@ModelAttribute("searchVO") BoardCateVO boardCateVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {

			String cateType = request.getParameter("cateType")==null?"":request.getParameter("cateType");
			boardCateVO.setCateType(cateType);

			Map<String, Object> map = egovBBSMasterService.selectBBSCateList(boardCateVO);
			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("cateVO", boardCateVO);

			return "/admin/board/bbsCateForm";
		}
    }
    
    /**
     * 관리자 > 사이트 관리 > 게시판 관리 > 카테고리관리 저장 (등록/수정)
     * 
     * @param boardMasterVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bbsCateSave.do")
    public String bbsCateSave(@ModelAttribute("searchVO") BoardCateVO boardCateVO, HttpServletRequest request, ModelMap model) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			// 삭제 action
			egovBBSMasterService.bbsCateDelete(boardCateVO);

			// db에 카테고리를 row별로 저장
			String cateListStr = boardCateVO.getCateList();
			int order = 0;
			if ((cateListStr != null) && (!(cateListStr.equals("")))) {
				String[] cateList = cateListStr.split(",");
				for (int i = 0; i < cateList.length; i++) {
					order++;
					String[] cateNode = cateList[i].split("@@");
					boardCateVO.setCateCode(cateNode[0]);
					boardCateVO.setCateName(cateNode[1]);
					boardCateVO.setCateOrder(Integer.toString(order));

					// 등록 action
				    egovBBSMasterService.saveBBSCate(boardCateVO);
				}
			}
			
			model.addAttribute("message", "저장이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/board/bbsCateView.do");
			model.addAttribute("hiddenName1", "bbsId");
			model.addAttribute("hiddenValue1", boardCateVO.getBbsId());
			model.addAttribute("hiddenName2", "cateType");
			model.addAttribute("hiddenValue2", boardCateVO.getCateType());
			
			return "/common/message";
		}
    }
    
}
