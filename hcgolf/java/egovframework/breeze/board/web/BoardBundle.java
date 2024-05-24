package egovframework.breeze.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.UsrPaginationRenderer;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cop.bbs.service.BoardCateVO;
import egovframework.com.cop.bbs.service.BoardItemVO;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovArticleService;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class BoardBundle {
    
	private HttpServletRequest request;
	private BoardVO boardVO;
	private BoardMasterVO boardMasterVO;
	private List<BoardVO> boardDataList;
	private int boardDataCnt = 0;
	private int noticeListCnt = 0;
	private List<BoardCateVO> categoryList1;
	private List<BoardCateVO> categoryList2;
	private List<BoardCateVO> categoryList3;
	private WebApplicationContext context;
	private EgovArticleService egovArticleService;
	private EgovBBSMasterService egovBBSMasterService;
	private EgovFileMngService egovFileMngService;
	
	private List<FileVO> fileList;
	private List<BoardItemVO> itemList;
	private List<BoardVO> noticeList;
	    
	private PaginationInfo paginationInfo;
	private UsrPaginationRenderer paging;
	
	private boolean isManager = false;
	private String pattern = "yyyy-MM-dd";
	private int pageUnit = 10;
	private int pageIndex = 1;
	
	private int index;
	
	public BoardBundle() throws Exception{
		
	}
	
	/**
	 * BoardBundle request setting 
	 * @param request
	 * @throws Exception
	 */
	public BoardBundle(HttpServletRequest request) throws Exception{
		this.request = request;
		this.boardVO = (BoardVO)request.getAttribute("boardVO");
		if(this.boardVO == null)
		{
			this.boardVO = new BoardVO();
		}
		
		this.boardMasterVO = (BoardMasterVO)request.getAttribute("brdMstrVO");
		if(this.boardMasterVO == null)
		{
			this.boardMasterVO = new BoardMasterVO();
		}
		
		this.boardDataList = ((List)request.getAttribute("boardDataList"));
		if(this.boardDataList != null && this.boardDataList.size() > 0) {
			for (int i = 0; i < this.boardDataList.size(); i++) {
				boardDataList.get(i).setNoticeAt("N");
			}
		}
		
		if(request.getAttribute("boardDataCnt") != null) {
			this.boardDataCnt = Integer.parseInt((String) request.getAttribute("boardDataCnt"));
		}
		
		this.noticeList = ((List)request.getAttribute("noticeList"));
		if(request.getAttribute("noticeListCnt") != null) {
			this.noticeListCnt = Integer.parseInt((String) request.getAttribute("noticeListCnt"));
		}
		
		this.itemList = ((List)request.getAttribute("itemList"));
		
		this.fileList = ((List)request.getAttribute("fileList"));
		
		if(this.noticeList != null) {
			this.noticeList.addAll(this.boardDataList);
		}
		
		this.boardDataList = this.noticeList;
		
		//this.boardDataCnt = this.boardDataCnt + this.noticeListCnt;
		
		
		this.paginationInfo = ((PaginationInfo)request.getAttribute("paginationInfo"));
	    if(this.paginationInfo == null)
	    {
	    	this.paginationInfo = new PaginationInfo();
	    }
	    
	    this.paging = new UsrPaginationRenderer();
	    
	    this.categoryList1 = ((List)request.getAttribute("categoryList1"));
	    this.categoryList2 = ((List)request.getAttribute("categoryList2"));
	    this.categoryList3 = ((List)request.getAttribute("categoryList3"));
	    
	    this.pageUnit = (int)(request.getAttribute("pageUnit")==null?10:request.getAttribute("pageUnit"));
		this.pageIndex = (int)(request.getAttribute("pageIndex")==null?1:request.getAttribute("pageIndex"));

	    this.context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    this.egovArticleService = ((EgovArticleService)this.context.getBean("EgovArticleService"));
	    this.egovBBSMasterService = ((EgovBBSMasterService)this.context.getBean("EgovBBSMasterService"));
	    this.egovFileMngService = ((EgovFileMngService)this.context.getBean("EgovFileMngService"));
	    
	    if (request.getParameter("pageUnit") != null) {
	    	this.index = Integer.parseInt(request.getParameter("pageUnit"));
	    	if (this.boardDataList != null) this.boardVO = ((BoardVO)this.boardDataList.get(this.index));
	    }
	}
	
	/**
	 * boardList 조회
	 * @param bbsId
	 * @param rowCount
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> getBoardDataList(String bbsId, int rowCount) throws Exception{
		return getBoardDataList(bbsId, null, null, null, null, null, rowCount, null);
	}
	public List<BoardVO> getBoardDataList(String bbsId, int rowCount, String cateType01) throws Exception{
		return getBoardDataList(bbsId, null, null, cateType01, null, null, rowCount, null);
	}
	
	/**
	 * boardList 조회 
	 * @param bbsId
	 * @param searchCnd
	 * @param searchWrd
	 * @param cateType01
	 * @param cateType02
	 * @param cateType03
	 * @param rowCount
	 * @param orderByQuery
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> getBoardDataList(String bbsId, String searchCnd, String searchWrd, String cateType01, String cateType02, String cateType03, int rowCount, String orderByQuery) throws Exception{
		BoardVO board = new BoardVO();
		
		board.setBbsId(bbsId);
		board.setSearchCnd(searchCnd);
		board.setSearchWrd(searchWrd);
		board.setSearchCate1(cateType01);
		board.setSearchCate2(cateType02);
		board.setSearchCate3(cateType03);
		
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(pageUnit);
		paginationInfo.setPageSize(board.getPageSize());
		paginationInfo.setTotalRecordCount(rowCount);
		
		board.setFirstIndex(paginationInfo.getFirstRecordIndex());
		board.setLastIndex(paginationInfo.getLastRecordIndex());
		board.setRecordCountPerPage(paginationInfo.getTotalRecordCount());
		Map<String, Object> map = this.egovArticleService.selectArticleList(board);//2011.09.07
		
		List<BoardVO> boardDataList = (List<BoardVO>) map.get("resultList");
		
		return boardDataList;
	}
	
	/**
	 * BoardVO 조회
	 * @param nttId
	 * @return
	 * @throws Exception
	 */
	public BoardVO getBoardVO(long nttId) throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setNttId(nttId);
		boardVO = this.egovArticleService.selectArticleDetail(boardVO);
		
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(boardVO.getAtchFileId());
		boardVO.setFileList(this.egovFileMngService.selectFileInfs(fileVO));
		return boardVO;
	}
	
	/**
	 * BoardVO 조회(request)
	 * @return
	 * @throws Exception
	 */
	public BoardVO getBoardVO() throws Exception
	{
		return this.boardVO;
	}
	
	/**
	 * BoardMasterVO 조회
	 * @param bbsId
	 * @return
	 * @throws Exception
	 */
	public BoardMasterVO getBoardMasterVO(String bbsId) throws Exception {
		BoardMasterVO boardVO = new BoardMasterVO();
	    boardVO.setBbsId(bbsId);
	    return (boardVO = this.egovBBSMasterService.selectBBSMasterInf(boardVO));
	}
	
	/**
	 * BoardMasterVO 조회(request)
	 * @return
	 */
	public BoardMasterVO getBoardMasterVO() {
		return this.boardMasterVO;
	}
	
	/**
	 * 필드별 BoardData 조회
	 * @param fieldId
	 * @param board
	 * @return
	 */
	public String getDataValue(String fieldId, BoardVO board) {
		String tmp = "";
		if(fieldId.equals("NTT_CN")) {
			tmp = board.getNttCn(); 
		}else if(fieldId.equals("ROWNUM")) {
			tmp = board.getRownum();
		}else if(fieldId.equals("ADDRESS")) {
			tmp = board.getAddress(); 
		}else if(fieldId.equals("DETAIL_ADDR")) {
			tmp = board.getDetailAddr(); 
		}else if(fieldId.equals("UPD_DATE")) {
			tmp = board.getLastUpdusrPnttm(); 
		}else if(fieldId.equals("START_DATE")) {
			tmp = board.getStartDate(); 
		}else if(fieldId.equals("ZIP_CODE")) {
			tmp = board.getZipCode(); 
		}else if(fieldId.equals("EMAIL")) {
			tmp = board.getEmail(); 
		}else if(fieldId.equals("TMP_FIELD1")) {
			tmp = board.getTmpField1(); 
		}else if(fieldId.equals("TMP_FIELD2")) {
			tmp = board.getTmpField2(); 
		}else if(fieldId.equals("TMP_FIELD3")) {
			tmp = board.getTmpField3(); 
		}else if(fieldId.equals("TMP_FIELD4")) {
			tmp = board.getTmpField4(); 
		}else if(fieldId.equals("TMP_FIELD5")) {
			tmp = board.getTmpField5(); 
		}else if(fieldId.equals("TMP_FIELD6")) {
			tmp = board.getTmpField6(); 
		}else if(fieldId.equals("TMP_FIELD7")) {
			tmp = board.getTmpField7(); 
		}else if(fieldId.equals("TMP_FIELD8")) {
			tmp = board.getTmpField8(); 
		}else if(fieldId.equals("TMP_FIELD9")) {
			tmp = board.getTmpField9(); 
		}else if(fieldId.equals("TMP_FIELD10")) {
			tmp = board.getTmpField10(); 
		}else if(fieldId.equals("REG_DATE")) {
			tmp = board.getFrstRegisterPnttm(); 
		}else if(fieldId.equals("NTCR_NM")) {
			tmp = board.getFrstRegisterNm(); 
		}else if(fieldId.equals("USER_TEL")) {
			tmp = board.getUserTel(); 
		}else if(fieldId.equals("NTT_SJ")) {
			tmp = board.getNttSj(); 
		}else if(fieldId.equals("RDCNT")) {
			tmp = board.getInqireCo()+""; 
		}else if(fieldId.equals("END_DATE")) {
			tmp = board.getEndDate();
		}else if(fieldId.equals("ATCH_FILE_ID")) {
			tmp = board.getAtchFileId(); 
		}else if(fieldId.equals("CATE_TYPE01")) {
			tmp = board.getCateType01(); 
		}else if(fieldId.equals("CATE_TYPE02")) {
			tmp = board.getCateType02(); 
		}else if(fieldId.equals("CATE_TYPE03")) {
			tmp = board.getCateType03();
		}else if(fieldId.equals("CATE_NAME01")) {
			tmp = board.getCateName01();
		}else if(fieldId.equals("CATE_NAME02")) {
			tmp = board.getCateName02(); 
		}else if(fieldId.equals("CATE_NAME03")) {
			tmp = board.getCateName03();
		}else if(fieldId.equals("USER_CEL")) {
			tmp = board.getUserCel(); 
		}
		
		return tmp;
	}
	
	public List<BoardVO> getNoticeList(){
		return this.noticeList;
	}
	
	public List<BoardVO> getNoticeList(String bbsId){
		BoardVO board = new BoardVO();
		
		board.setBbsId(bbsId);
		board.setNoticeAt("Y");
		
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(9999);
		paginationInfo.setPageSize(10);
		
		board.setFirstIndex(paginationInfo.getFirstRecordIndex());
		board.setLastIndex(paginationInfo.getLastRecordIndex());
		board.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		Map<String, Object> map = this.egovArticleService.selectArticleList(board);//2011.09.07
		
		List<BoardVO> boardDataList = (List<BoardVO>) map.get("resultList");
		
		return boardDataList;
	}
	
	public String getReplyAt(long parnts, String bbsId) {
		BoardVO board = new BoardVO();
		board.setParnts(parnts+"");
		board.setBbsId(bbsId);
		int replyCnt = this.egovArticleService.getReplyAt(board);
		if(replyCnt > 0) {
			return "Y";
		}else {
			return "N";
		}
	}
	
	public BoardVO getReplyBoardVO(long parnts, String bbsId) {
		BoardVO board = new BoardVO();
		board.setParnts(parnts+"");
		board.setBbsId(bbsId);
		return this.egovArticleService.selectReplyArticle(board);
	}
	
	public String getCategoryName(String bbsId, String cateCode, String cateType) throws Exception {
		BoardCateVO cate = new BoardCateVO();
		cate.setBbsId(bbsId);
		cate.setCateCode(cateCode);
		cate.setCateType(cateType);
		return this.egovBBSMasterService.selectCateName(cate);
	}
	
	/**
	 * Paging 조회
	 * @param pageSize
	 * @return
	 */
	public String getPaging(int pageSize)
	{
		if(this.paginationInfo == null)
		{
			return "";
		}
	    this.paginationInfo.setPageSize(pageSize);
	    String pagingStr = "";
	    try
	    {
	      pagingStr = this.paging.renderPagination(this.paginationInfo, "fn_list");
	    }
	    catch (Exception e) {
	      System.out.println("e : "+e.getMessage());
	    }
	    return pagingStr;
	}
	
	/**
	 * BoardList Count 조회
	 * @return
	 */
	public int getListCount()
	{
		if (this.boardDataList == null) return 0;
		return this.boardDataList.size();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public long getNttId() {
		if (this.boardVO == null)
		{			
			return 0;
		}
		return this.boardVO.getNttId();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getBbsId() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getBbsId();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNttSj() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getNttSj();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNttCn() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getNttCn();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getAnswerAt() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getAnswerAt();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public int getInqireCo() {
		if (this.boardVO == null)
		{			
			return 0;
		}
		return this.boardVO.getInqireCo();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getUseAt() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getUseAt();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNtceBgnde() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getNtceBgnde();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNtceEndde() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getNtceEndde();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNtcrId() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getNtcrId();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNtcrNm() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getFrstRegisterNm();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getPassword() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getPassword();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getAtchFileId() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getAtchFileId();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getNoticeAt() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getNoticeAt();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getSecretAt() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getSecretAt();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getFrstRegisterPnttm() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getFrstRegisterPnttm();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getBlogId() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getBlogId();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCategory() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCategory();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getUserTel() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getUserTel();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getUserCel() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getUserCel();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getAddress() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getAddress();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getDetailAddr() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getDetailAddr();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getZipCode() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getZipCode();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getEmail() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getEmail();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getStartDate() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getStartDate();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getEndDate() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getEndDate();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getTmpField1() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getTmpField1();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getTmpField2() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getTmpField2();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getTmpField3() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getTmpField3();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getTmpField4() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getTmpField4();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getTmpField5() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getTmpField5();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCateType01() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCateType01();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCateType02() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCateType02();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCateType03() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCateType03();
	}
	
	/**
	 * BoardDataCnt 조회(request)
	 * @return
	 */
	public int getBoardDataCnt() {
		return this.boardDataCnt;
	}
	
	/**
	 * BoardItemVO List 조회(항목 리스트)
	 * @return
	 */
	public List<BoardItemVO> getBoardItemList() {
		return this.itemList;
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public int getPageUnit() {
		return this.pageUnit;
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public int getPageIndex() {
		return this.pageIndex;
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getSearchCnd() {
		return this.boardVO.getSearchCnd();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getSearchWrd() {
		return this.boardVO.getSearchWrd();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getSearchCate1() {
		return this.boardVO.getSearchCate1();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getSearchCate2() {
		return this.boardVO.getSearchCate2();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getSearchCate3() {
		return this.boardVO.getSearchCate3();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCommand() {
		return this.boardVO.getCommand();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getFileAtchPosblAt() {
		return this.boardMasterVO.getFileAtchPosblAt();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getAtchPosblFileNumber() {
		return this.boardMasterVO.getAtchPosblFileNumber();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getWriteBtnFlag() {
		return this.boardMasterVO.getWriteBtnFlag();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getUpdateBtnFlag() {
		return this.boardMasterVO.getUpdateBtnFlag();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getDeleteBtnFlag() {
		return this.boardMasterVO.getDeleteBtnFlag();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getReplyBtnFlag() {
		return this.boardMasterVO.getReplyBtnFlag();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getSecretPosblAt() {
		return this.boardMasterVO.getSecretPosblAt();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getCate01PosblAt() {
		return this.boardMasterVO.getCateType01();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getCate02PosblAt() {
		return this.boardMasterVO.getCateType02();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getCate03PosblAt() {
		return this.boardMasterVO.getCateType03();
	}
	
	/**
	 * BoardMasterVO Data 조회(request)
	 * @return
	 */
	public String getLoginChkAt() {
		return this.boardMasterVO.getLoginChkAt();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public int getFileCnt() {
		return this.boardVO.getFileCnt();
	}
	
	/**
	 * BoardDataList에서 BoardVO 조회(request)
	 * @param index
	 * @return
	 */
	public BoardVO getBoardDataVOList(int index) {
		if (index >= getListCount()) return null;
		this.index = index;
		return ((BoardVO)this.boardDataList.get(index));
	}
	
	/**
	 * FileList 조회
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> getFileList(FileVO file) throws Exception{
		List<FileVO> result = egovFileMngService.selectFileInfs(file);
		return result;
	}

	public List<FileVO> getFileList(String atchFileId) throws Exception{
		FileVO file = new FileVO();
		file.setAtchFileId(atchFileId);
		List<FileVO> result = egovFileMngService.selectFileInfs(file);
		return result;
	}
	
	public List<FileVO> getFileList() throws Exception{
		return this.fileList;
	}
	
	/**
	 * 이전글 BoardVO 조회
	 * @return
	 */
	public BoardVO getPrevBoardVO() {
		BoardVO prevVO = this.egovArticleService.selectPrevBoardList(this.boardVO);
		return prevVO;
	}
	
	/**
	 * 다음글 BoardVO 조회
	 * @return
	 */
	public BoardVO getNextBoardVO() {
		BoardVO nextVO = this.egovArticleService.selectNextBoardList(this.boardVO);
		return nextVO;
	}
	
	/**
	 * BoardVO setting
	 * @param boardVO
	 */
	public void setDataVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}
	
	/**
	 * BoardMasterVO setting
	 * @param boardVO
	 */
	public void setBoardDataVO(BoardVO boardVO) throws Exception
	{
		BoardMasterVO boardMasterVO = new BoardMasterVO();
		this.boardMasterVO.setBbsId(boardVO.getBbsId());
		this.boardMasterVO = this.egovBBSMasterService.selectBBSMasterInf(boardMasterVO);
		this.boardVO = boardVO;
	}
	
	/**
	 * 카테고리 리스트1 조회
	 * @return
	 */
	public List<BoardCateVO> getBoardCateList1() {
		return this.categoryList1;
	}
	
	/**
	 * 카테고리 리스트2 조회
	 * @return
	 */
	public List<BoardCateVO> getBoardCateList2() {
		return this.categoryList2;
	}
	
	/**
	 * 카테고리 리스트3 조회
	 * @return
	 */
	public List<BoardCateVO> getBoardCateList3() {
		return this.categoryList3;
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCateName01() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCateName01();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCateName02() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCateName02();
	}
	
	/**
	 * BoardData 조회(request)
	 * @return
	 */
	public String getCateName03() {
		if (this.boardVO == null)
		{			
			return "";
		}
		return this.boardVO.getCateName03();
	}
	

	
}
