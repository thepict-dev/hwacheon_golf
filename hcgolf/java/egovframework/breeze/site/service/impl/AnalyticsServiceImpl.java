package egovframework.breeze.site.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.site.service.AnalyticsService;
import egovframework.breeze.site.service.AnalyticsVO;
import egovframework.breeze.site.service.LayoutVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("analyticsService")
public class AnalyticsServiceImpl implements AnalyticsService{
	
	@Resource(name="analyticsDAO")
	private AnalyticsDAO analyticsDAO;
	
	@Resource(name = "analyticsIdService")
	private EgovIdGnrService idgenService;
	
	@Resource(name = "bakAnaIdService")
	private EgovIdGnrService bakIdgenService;
	
	
	@Override
	public Map<String, Object> selectAnalyticsList(AnalyticsVO analyticsVO) {
		List<?> list = analyticsDAO.selectAnalyticsList(analyticsVO);

		int cnt = analyticsDAO.selectAnalyticsListCnt(analyticsVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public AnalyticsVO selectAnalyticsView(AnalyticsVO analyticsVO) {
		return analyticsDAO.selectAnalyticsView(analyticsVO);
	}

	@Override
	public void analyticsInsert(AnalyticsVO analyticsVO) throws Exception {
		String analyticsId = idgenService.getNextStringId();
		analyticsVO.setAnalyticsId(analyticsId);
		analyticsDAO.analyticsInsert(analyticsVO);
	}

	@Override
	public void analyticsUpdate(AnalyticsVO analyticsVO) throws Exception {
		analyticsDAO.analyticsUpdate(analyticsVO);
	}

	@Override
	public void analyticsDelete(AnalyticsVO analyticsVO) throws Exception {
		analyticsDAO.analyticsDelete(analyticsVO);
	}

	@Override
	public void analyticsBakInsert(AnalyticsVO analyticsVO) throws Exception {
		String bakAnalyticsId = bakIdgenService.getNextStringId();
		analyticsVO.setBakAnalyticsId(bakAnalyticsId);
		analyticsDAO.analyticsBakInsert(analyticsVO);
	}
	
	@Override
	public Map<String, Object> selectAnalyticsBakList(AnalyticsVO analyticsVO) {
		List<?> list = analyticsDAO.selectAnalyticsBakList(analyticsVO);

		int cnt = analyticsDAO.selectAnalyticsListBakCnt(analyticsVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public AnalyticsVO selectAnalyticsBakView(AnalyticsVO analyticsVO) {
		return analyticsDAO.selectAnalyticsBakView(analyticsVO);
	}
}
