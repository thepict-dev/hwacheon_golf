package egovframework.breeze.popup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.popup.service.PopupService;
import egovframework.breeze.popup.service.PopupVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("popupService")
public class PopupServiceImpl extends EgovAbstractServiceImpl implements PopupService {

	@Resource(name="popupDAO")
	private PopupDAO popupDAO;
	
	@Resource(name = "popupIdService")
	private EgovIdGnrService idgenService;
	
	@Override
	public Map<String, Object> selectPopupList(PopupVO popupVO) {
		List<?> list = popupDAO.selectPopupList(popupVO);
		
		int cnt = popupDAO.selectPopupListCnt(popupVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public void popupInsert(PopupVO popupVO) throws Exception {
		String popupId = idgenService.getNextStringId();
		popupVO.setPopupId(popupId);
		popupDAO.popupInsert(popupVO);
	}

	@Override
	public void popupUpdate(PopupVO popupVO) throws Exception {
		popupDAO.popupUpdate(popupVO);
	}

	@Override
	public void popupDelete(PopupVO popupVO) throws Exception {
		popupDAO.popupDelete(popupVO);
	}

	@Override
	public PopupVO selectPopupView(PopupVO popupVO) {
		return popupDAO.selectPopupView(popupVO);
	}

	@Override
	public List<PopupVO> selectMainPopupList(PopupVO popupVO) throws Exception {
		return (List<PopupVO>) popupDAO.selectMainPopupList(popupVO);
	}
}
