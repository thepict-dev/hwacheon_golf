package egovframework.breeze.popup.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.popup.service.PopupVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("popupDAO")
public class PopupDAO extends EgovComAbstractDAO{
	
	public List<?> selectPopupList(PopupVO popupVO){
		return list("popupMapper.selectPopupList", popupVO);
	}
	
	public PopupVO selectPopupView(PopupVO popupVO) {
		return (PopupVO)selectOne("popupMapper.selectPopupView", popupVO);
	}
	
	public void popupInsert(PopupVO popupVO) {
		insert("popupMapper.insertPopup", popupVO);
	}

	public void popupUpdate(PopupVO popupVO) {
		update("popupMapper.updatePopup", popupVO);
	}
	
	public void popupDelete(PopupVO popupVO) {
		update("popupMapper.deletePopup", popupVO);
	}

	public int selectPopupListCnt(PopupVO popupVO) {
		return (Integer)selectOne("popupMapper.selectPopupListCnt", popupVO);
	}

	public List<?> selectMainPopupList(PopupVO popupVO) {
		return list("popupMapper.selectMainPopupList", popupVO);
	}
}
