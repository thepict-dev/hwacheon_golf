package egovframework.com.cop.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.cmy.service.CommunityVO;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardCateVO;
import egovframework.com.cop.bbs.service.BoardItemVO;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;

@Repository("EgovBBSMasterDAO")
public class EgovBBSMasterDAO extends EgovComAbstractDAO {

	public List<?> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBBSMasterList", boardMasterVO);
	}

	public int selectBBSMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBBSMasterListTotCnt", boardMasterVO);
	}
	
	public BoardMasterVO selectBBSMasterDetail(BoardMasterVO boardMasterVO) {
		return (BoardMasterVO) selectOne("BBSMaster.selectBBSMasterDetail", boardMasterVO);
	}

	public void insertBBSMasterInf(BoardMaster boardMaster) {
		insert("BBSMaster.insertBBSMaster", boardMaster);
	}

	public void updateBBSMaster(BoardMaster boardMaster) {
		update("BBSMaster.updateBBSMaster", boardMaster);
	}

	public void deleteBBSMaster(BoardMaster boardMaster) {
		update("BBSMaster.deleteBBSMaster", boardMaster);
	}
	
	/*
	 * 블로그 관련
	 */
	public List<?> selectBlogMasterInfs(BoardMasterVO boardMasterVO) {
		return list("BBSMaster.selectBlogMasterList", boardMasterVO);
	}
	
	public int selectBlogMasterInfsCnt(BoardMasterVO boardMasterVO) {
		return (Integer)selectOne("BBSMaster.selectBlogMasterListTotCnt", boardMasterVO);
	}
	
	public int checkExistUser(BlogVO blogVO) {
		return (Integer)selectOne("BBSMaster.checkExistUser", blogVO);
	}
	
	public BlogVO checkExistUser2(BlogVO blogVO) {
		return (BlogVO) selectOne("BBSMaster.checkExistUser2", blogVO);
	}
	
	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		insert("BBSMaster.insertBoardBlogUserRqst", blogUser);
	}
	
	public void insertBlogMaster(Blog blog) {
		insert("BBSMaster.insertBlogMaster", blog);
	}
	
	public BlogVO selectBlogDetail(BlogVO blogVO) {
		return (BlogVO) selectOne("BBSMaster.selectBlogDetail", blogVO);
	}

	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception{
		return (List<BlogVO>) list("BBSMaster.selectBlogListPortlet", blogVO);
	}

	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) {
		return (List<BoardMasterVO>) list("BBSMaster.selectBBSListPortlet", boardMasterVO);
	}

	//항목관리
	public List<BoardItemVO> selectBBSItemList(BoardItemVO boardItemVO) {
		return (List<BoardItemVO>) list("BBSMaster.selectBBSItemList", boardItemVO);
	}
	
	public BoardItemVO selectBBSItem(BoardItemVO boardItemVO) {
		return (BoardItemVO) selectOne("BBSMaster.selectBBSItem", boardItemVO);
	}
	public List<?> getBoardField(BoardItemVO boardItemVO) {
		return list("BBSMaster.getBoardField", boardItemVO);
	}

	public void saveBBSItem(BoardItemVO boardItemVO) {
		update("BBSMaster.saveBBSItem", boardItemVO);
	}

	public void bbsItemDelete(BoardItemVO boardItemVO) {
		update("BBSMaster.bbsItemDelete", boardItemVO);
	}

	//카테고리 관리
	public List<?> selectBBSCateList(BoardCateVO boardCateVO) {
		return list("BBSMaster.selectBBSCateList", boardCateVO);
	}
	public int selectBBSCateListCnt(BoardCateVO boardCateVO) {
		return (Integer) selectOne("BBSMaster.selectBBSCateListCnt", boardCateVO);
	}

	public void saveBBSCate(BoardCateVO boardCateVO) {
		update("BBSMaster.saveBBSCate", boardCateVO);
	}

	public void bbsCateDelete(BoardCateVO boardCateVO) {
		update("BBSMaster.bbsCateDelete", boardCateVO);
	}

	public void insertDefaultBBSItem(BoardItemVO boardItemVO) {
		insert("BBSMaster.insertDefaultBBSItem", boardItemVO);
	}

	public String selectCateName(BoardCateVO cate) {
		return (String) selectOne("BBSMaster.selectCateName", cate);
	}

	public void insertDefaultBBSCate(BoardCateVO boardCateVO) {
		insert("BBSMaster.insertDefaultBBSCate", boardCateVO);
	}

	public void minutesCateDelete(BoardCateVO boardCateVO) {
		update("BBSMaster.minutesCateDelete", boardCateVO);
	}
}
