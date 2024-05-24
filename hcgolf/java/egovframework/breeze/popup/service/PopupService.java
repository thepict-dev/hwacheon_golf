package egovframework.breeze.popup.service;

import java.util.List;
import java.util.Map;

public interface PopupService {

	Map<String, Object> selectPopupList(PopupVO popupVO);
	
	public PopupVO selectPopupView(PopupVO popupVO);
	
	public void popupInsert(PopupVO popupVO) throws Exception;
	
	public void popupUpdate(PopupVO popupVO) throws Exception;
	
	public void popupDelete(PopupVO popupVO) throws Exception;

	List<PopupVO> selectMainPopupList(PopupVO popupVO) throws Exception;
}
