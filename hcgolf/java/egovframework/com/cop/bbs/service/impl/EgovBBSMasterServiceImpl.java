package egovframework.com.cop.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.EgovBBSMasterService;
import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cop.bbs.service.Blog;
import egovframework.com.cop.bbs.service.BlogUser;
import egovframework.com.cop.bbs.service.BlogVO;
import egovframework.com.cop.bbs.service.BoardCateVO;
import egovframework.com.cop.bbs.service.BoardItemVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("EgovBBSMasterService")
public class EgovBBSMasterServiceImpl extends EgovAbstractServiceImpl implements EgovBBSMasterService {

	@Resource(name = "EgovBBSMasterDAO")
    private EgovBBSMasterDAO egovBBSMasterDao;

    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;
	
    //---------------------------------
    // 2009.06.26 : 2단계 기능 추가
    //---------------------------------
    @Resource(name = "BBSAddedOptionsDAO")
    private BBSAddedOptionsDAO addedOptionsDAO;
    ////-------------------------------
    
	@Override
	public Map<String, Object> selectNotUsedBdMstrList(BoardMasterVO boardMasterVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBBSMasterInf(BoardMaster boardMaster) {
		egovBBSMasterDao.deleteBBSMaster(boardMaster);	
	}

	@Override
	public void updateBBSMasterInf(BoardMaster boardMaster) throws Exception {
		egovBBSMasterDao.updateBBSMaster(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}
		
	}

	@Override
	public BoardMasterVO selectBBSMasterInf(BoardMasterVO boardMasterVO) throws Exception {
		BoardMasterVO resultVO = egovBBSMasterDao.selectBBSMasterDetail(boardMasterVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
    	if(EgovComponentChecker.hasComponent("EgovBBSCommentService") || EgovComponentChecker.hasComponent("EgovBBSSatisfactionService")){//2011.09.15
    	    BoardMasterVO options = addedOptionsDAO.selectAddedOptionsInf(boardMasterVO);
    	    
    	    if (options != null) {
	    		if (options.getCommentAt().equals("Y")) {
	    			resultVO.setOption("comment");
	    		}
	
	    		if (options.getStsfdgAt().equals("Y")) {
	    			resultVO.setOption("stsfdg");
	    		}
    	    } else {
    	    	resultVO.setOption("na");	// 미지정 상태로 수정 가능 (이미 지정된 경우는 수정 불가로 처리)
    	    }
    	}
        
        return resultVO;
	}

	@Override
	public Map<String, Object> selectBBSMasterInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBBSMasterInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBBSMasterInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public Map<String, Object> selectBlogMasterInfs(BoardMasterVO boardMasterVO) {
		List<?> result = egovBBSMasterDao.selectBlogMasterInfs(boardMasterVO);
		int cnt = egovBBSMasterDao.selectBlogMasterInfsCnt(boardMasterVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}

	@Override
	public void insertBBSMasterInf(BoardMaster boardMaster) throws Exception {
		
		//게시판 ID 채번
		String bbsId = idgenService.getNextStringId();
		boardMaster.setBbsId(bbsId);
		
		egovBBSMasterDao.insertBBSMasterInf(boardMaster);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardMaster.getOption().equals("comment") || boardMaster.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardMaster);
		}

	}
	
	@Override
	public String checkBlogUser(BlogVO blogVO) {

		int userCnt = egovBBSMasterDao.checkExistUser(blogVO);
		
		if (userCnt == 0) {
		    return "";
		} else {
		    return "EXIST";
		}
	}
	
	@Override
	public BlogVO checkBlogUser2(BlogVO blogVO) {
		BlogVO userBlog = egovBBSMasterDao.checkExistUser2(blogVO);
		return userBlog;
	}
	
	@Override
	public void insertBoardBlogUserRqst(BlogUser blogUser) {
		egovBBSMasterDao.insertBoardBlogUserRqst(blogUser);
	}
	
	@Override
	public void insertBlogMaster(Blog blog) throws FdlException {
		egovBBSMasterDao.insertBlogMaster(blog);
	}
	
	@Override
	public BlogVO selectBlogDetail(BlogVO blogVO) throws Exception {
		BlogVO resultVO = egovBBSMasterDao.selectBlogDetail(blogVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
	}

	@Override
	public List<BlogVO> selectBlogListPortlet(BlogVO blogVO) throws Exception{
		return egovBBSMasterDao.selectBlogListPortlet(blogVO);
	}

	@Override
	public List<BoardMasterVO> selectBBSListPortlet(BoardMasterVO boardMasterVO) throws Exception {
		return egovBBSMasterDao.selectBBSListPortlet(boardMasterVO);
	}

	//항목관리

	@Override
	public List<BoardItemVO> selectBBSItemList(BoardItemVO boardItemVO) {
		List<BoardItemVO> result = egovBBSMasterDao.selectBBSItemList(boardItemVO);
		return result;
	}

	@Override
	public BoardItemVO selectBBSItem(BoardItemVO boardItemVO) throws Exception {
		BoardItemVO resultVO = egovBBSMasterDao.selectBBSItem(boardItemVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        
        return resultVO;
	}

	@Override
	public Map<String, Object> getBoardField(BoardItemVO boardItemVO) {
		List<?> result = egovBBSMasterDao.getBoardField(boardItemVO);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		return map;
	}

	@Override
	public void saveBBSItem(BoardItemVO boardItemVO) throws Exception {
		egovBBSMasterDao.saveBBSItem(boardItemVO);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardItemVO.getOption().equals("comment") || boardItemVO.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardItemVO);
		}
		
	}

	@Override
	public void bbsItemDelete(BoardItemVO boardItemVO) {
		egovBBSMasterDao.bbsItemDelete(boardItemVO);	
	}

	//카테고리 관리

	@Override
	public Map<String, Object> selectBBSCateList(BoardCateVO boardCateVO) {
		List<?> result = egovBBSMasterDao.selectBBSCateList(boardCateVO);
    	int cnt = egovBBSMasterDao.selectBBSCateListCnt(boardCateVO);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));
		return map;
	}

	@Override
	public void saveBBSCate(BoardCateVO boardCateVO) throws Exception {
		egovBBSMasterDao.saveBBSCate(boardCateVO);
		
		//---------------------------------
		// 2009.06.26 : 2단계 기능 추가
		//---------------------------------
		if (boardCateVO.getOption().equals("comment") || boardCateVO.getOption().equals("stsfdg")) {
		    addedOptionsDAO.insertAddedOptionsInf(boardCateVO);
		}
		
	}

	@Override
	public void bbsCateDelete(BoardCateVO boardCateVO) {
		egovBBSMasterDao.bbsCateDelete(boardCateVO);	
	}

	@Override
	public void insertDefaultBBSItem(BoardItemVO boardItemVO) throws Exception {
		egovBBSMasterDao.insertDefaultBBSItem(boardItemVO);
	}

	@Override
	public String selectCateName(BoardCateVO cate) throws Exception {
		return egovBBSMasterDao.selectCateName(cate);
	}

	@Override
	public void insertDefaultBBSCate(BoardCateVO boardCateVO) throws Exception {
		egovBBSMasterDao.insertDefaultBBSCate(boardCateVO);
	}

	@Override
	public void minutesCateDelete(BoardCateVO boardCateVO) {
		egovBBSMasterDao.minutesCateDelete(boardCateVO);	
	}
}
