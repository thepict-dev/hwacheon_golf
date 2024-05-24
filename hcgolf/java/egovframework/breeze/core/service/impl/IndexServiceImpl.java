package egovframework.breeze.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.core.service.IndexService;
import egovframework.breeze.core.service.IndexVO;
import egovframework.breeze.popup.service.PopupVO;
import egovframework.breeze.site.service.MenuVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("indexService")
public class IndexServiceImpl extends EgovAbstractServiceImpl implements IndexService {

	@Resource(name="indexDAO")
	private IndexDAO indexDAO;


	@Override
	public MenuVO selectMenu(MenuVO menuVO) {
		return indexDAO.selectMenu(menuVO);
	}

	@Override
	public List<BoardVO> selectMainBoardList(BoardVO boardVO) throws Exception {
		return indexDAO.selectMainBoardList(boardVO);
	}

	@Override
	public List<PopupVO> selectMainPopupList(PopupVO popupVO) throws Exception {
		return indexDAO.selectMainPopupList(popupVO);
	}
	
	@Override
	public IndexVO selectSiteSetting(IndexVO indexVO) {
		return indexDAO.selectSiteSetting(indexVO);
	}

	@Override
	public void siteUpdate(IndexVO indexVO) throws Exception {
		indexDAO.siteUpdate(indexVO);
	}
}
