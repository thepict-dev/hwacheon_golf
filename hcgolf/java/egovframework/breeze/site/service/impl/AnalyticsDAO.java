package egovframework.breeze.site.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.site.service.AnalyticsVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("analyticsDAO")
public class AnalyticsDAO extends EgovComAbstractDAO {
	
	public List<?> selectAnalyticsList(AnalyticsVO analyticsVO){
		return list("analyticsMapper.selectAnalyticsList", analyticsVO);
	}
	
	public int selectAnalyticsListCnt(AnalyticsVO analyticsVO){
		return (int)selectOne("analyticsMapper.selectAnalyticsListCnt", analyticsVO);
	}
	
	public AnalyticsVO selectAnalyticsView(AnalyticsVO analyticsVO){
		return (AnalyticsVO)selectOne("analyticsMapper.selectAnalyticsView", analyticsVO);
	}
	
	public void analyticsInsert(AnalyticsVO analyticsVO) {
		insert("analyticsMapper.analyticsInsert", analyticsVO);
	}

	public void analyticsUpdate(AnalyticsVO analyticsVO) {
		update("analyticsMapper.analyticsUpdate", analyticsVO);
	}

	public void analyticsDelete(AnalyticsVO analyticsVO) {
		update("analyticsMapper.analyticsDelete", analyticsVO);
	}

	public void analyticsBakInsert(AnalyticsVO analyticsVO) {
		insert("analyticsMapper.analyticsBakInsert", analyticsVO);
	}
	
	public List<?> selectAnalyticsBakList(AnalyticsVO analyticsVO){
		return list("analyticsMapper.selectAnalyticsBakList", analyticsVO);
	}
	
	public int selectAnalyticsListBakCnt(AnalyticsVO analyticsVO){
		return (int)selectOne("analyticsMapper.selectAnalyticsBakListCnt", analyticsVO);
	}
	
	public AnalyticsVO selectAnalyticsBakView(AnalyticsVO analyticsVO){
		return (AnalyticsVO)selectOne("analyticsMapper.selectAnalyticsBakView", analyticsVO);
	}
}
