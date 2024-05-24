package egovframework.breeze.core.service;

import java.util.List;

import egovframework.breeze.popup.service.PopupVO;
import egovframework.breeze.site.service.MenuVO;
import egovframework.com.cop.bbs.service.BoardVO;

public interface IndexService {

	
	MenuVO selectMenu(MenuVO menuVO);

	List<BoardVO> selectMainBoardList(BoardVO boardVO) throws Exception;	

	List<PopupVO> selectMainPopupList(PopupVO popupVO) throws Exception;	
	
	IndexVO selectSiteSetting(IndexVO indexVO);
	
	public void siteUpdate(IndexVO indexVO) throws Exception;
}
