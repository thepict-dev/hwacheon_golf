package egovframework.breeze.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.core.service.IndexVO;
import egovframework.breeze.popup.service.PopupVO;
import egovframework.breeze.site.service.MenuVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.cop.bbs.service.BoardVO;

@Repository("indexDAO")
public class IndexDAO extends EgovComAbstractDAO {


	public MenuVO selectMenu(MenuVO menuVO) {
		return (MenuVO) selectOne("indexMapper.selectMenu", menuVO);
	}

	public List<BoardVO> selectMainBoardList(BoardVO boardVO) {
		return (List<BoardVO>) list("indexMapper.selectMainBoardList", boardVO);
	}

	public List<PopupVO> selectMainPopupList(PopupVO popupVO) {
		return (List<PopupVO>) list("indexMapper.selectMainPopupList", popupVO);
	}

	public IndexVO selectSiteSetting(IndexVO indexVO) {
		return (IndexVO) selectOne("indexMapper.selectSiteSetting", indexVO);
	}

	public void siteUpdate(IndexVO indexVO) {
		update("indexMapper.siteUpdate", indexVO);
	}
}
