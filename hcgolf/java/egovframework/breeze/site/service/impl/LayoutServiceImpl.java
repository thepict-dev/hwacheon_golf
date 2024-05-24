package egovframework.breeze.site.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.site.service.LayoutService;
import egovframework.breeze.site.service.LayoutVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("layoutService")
public class LayoutServiceImpl extends EgovAbstractServiceImpl implements LayoutService {

	@Resource(name="layoutDAO")
	private LayoutDAO layoutDAO;
	
	@Resource(name = "layoutIdService")
	private EgovIdGnrService idgenService;

	@Resource(name = "bakLayIdService")
	private EgovIdGnrService bakIdgenService;
	
	@Override
	public Map<String, Object> selectLayoutList(LayoutVO layoutVO) {
		List<?> list = layoutDAO.selectLayoutList(layoutVO);

		int cnt = layoutDAO.selectLayoutListCnt(layoutVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public LayoutVO selectLayoutView(LayoutVO layoutVO) {
		return layoutDAO.selectLayoutView(layoutVO);
	}

	@Override
	public void layoutInsert(LayoutVO layoutVO) throws Exception {
		String layoutId = idgenService.getNextStringId();
		layoutVO.setLayoutId(layoutId);
		layoutDAO.layoutInsert(layoutVO);
	}

	@Override
	public void layoutUpdate(LayoutVO layoutVO) throws Exception {
		layoutDAO.layoutUpdate(layoutVO);
	}

	@Override
	public void layoutDelete(LayoutVO layoutVO) throws Exception {
		layoutDAO.layoutDelete(layoutVO);
	}

	@Override
	public void layoutBakInsert(LayoutVO layoutVO) throws Exception {
		String bakLayoutId = bakIdgenService.getNextStringId();
		layoutVO.setBakLayoutId(bakLayoutId);
		layoutDAO.layoutBakInsert(layoutVO);
	}
	
	@Override
	public Map<String, Object> selectLayoutBakList(LayoutVO layoutVO) {
		List<?> list = layoutDAO.selectLayoutBakList(layoutVO);

		int cnt = layoutDAO.selectLayoutListBakCnt(layoutVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public LayoutVO selectLayoutBakView(LayoutVO layoutVO) {
		return layoutDAO.selectLayoutBakView(layoutVO);
	}
}
