package egovframework.com.cop.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface EgovBBSMasterService {

	Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO);

	void deleteBBSMasterInf(BoardMaster boardMaster);

	void updateBBSMasterInf(BoardMaster boardMaster) throws Exception;

	BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) throws Exception;

	Map<String, Object> selectBBSMasterInfs(BoardMasterVO boardMasterVO);
	
	void insertBBSMasterInf(BoardMaster boardMaster) throws Exception;

	/*
	 * 블로그 관련
	 */
	Map<String, Object> selectBlogMasterInfs(BoardMasterVO boardMasterVO);
	
	String checkBlogUser(BlogVO blogVO);
	
	BlogVO checkBlogUser2(BlogVO blogVO);

	void insertBoardBlogUserRqst(BlogUser blogUser);
	
	void insertBlogMaster(Blog blog) throws FdlException;
	
	BlogVO selectBlogDetail(BlogVO blogVO) throws Exception;

	List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception;

	List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) throws Exception;
	
	//항목 관리
	List<BoardItemVO> selectBBSItemList(BoardItemVO boardItemVO);

	BoardItemVO selectBBSItem(BoardItemVO boardItemVO) throws Exception;

	void saveBBSItem(BoardItemVO boardItemVO) throws Exception;

	void bbsItemDelete(BoardItemVO boardItemVO);

	//필드 조회
	Map<String, Object> getBoardField(BoardItemVO boardItemVO);

	//카테고리 관리
	Map<String, Object> selectBBSCateList(BoardCateVO boardCateVO);

	void saveBBSCate(BoardCateVO boardCateVO) throws Exception;

	void bbsCateDelete(BoardCateVO boardCateVO) throws Exception;

	//게시판 default 항목 등록
	void insertDefaultBBSItem(BoardItemVO boardItemVO) throws Exception;

	String selectCateName(BoardCateVO cate) throws Exception;

	//회의록 관리 default 카테고리1 등록
	void insertDefaultBBSCate(BoardCateVO boardCateVO) throws Exception;

	//회의록 관리 default 카테고리1 삭제
	void minutesCateDelete(BoardCateVO boardCateVO) throws Exception;

}
