package egovframework.com.cop.bbs.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public abstract interface EgovArticleService {

	public abstract Map<String, Object> selectArticleList(BoardVO boardVO);

	public abstract BoardVO selectArticleDetail(BoardVO boardVO);
	
	public abstract void insertArticle(Board board) throws FdlException;

	public abstract void updateArticle(Board board);

	public abstract void deleteArticle(Board board) throws Exception;

	public abstract List<BoardVO> selectNoticeArticleList(BoardVO boardVO);
	
	public abstract Map<String, Object> selectGuestArticleList(BoardVO vo);

	//답변 조회
	public abstract BoardVO selectReplyArticle(BoardVO boardVO);

	//답변 수정
	public abstract void updateReplyArticle(Board board);
	
	/*
	 * 블로그 관련
	 */
	public abstract BoardVO selectArticleCnOne(BoardVO boardVO);
	
	public abstract List<BoardVO> selectBlogNmList(BoardVO boardVO);
	
	public abstract Map<String, Object> selectBlogListManager(BoardVO boardVO);
	
	public abstract List<BoardVO> selectArticleDetailDefault(BoardVO boardVO);
	
	public abstract int selectArticleDetailDefaultCnt(BoardVO boardVO);
	
	public abstract List<BoardVO> selectArticleDetailCn(BoardVO boardVO);
	
	public abstract int selectLoginUser(BoardVO boardVO);

	public abstract void deleteAtchFileId(String atchFileId);

	public abstract BoardVO selectPrevBoardList(BoardVO board);

	public abstract BoardVO selectNextBoardList(BoardVO board);

	public abstract int getReplyAt(BoardVO board);

	public abstract int selectPwCheck(BoardVO board);
}
