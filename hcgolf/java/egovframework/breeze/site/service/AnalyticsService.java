package egovframework.breeze.site.service;

import java.util.Map;

public interface AnalyticsService {
	
	Map<String, Object> selectAnalyticsList(AnalyticsVO analyticsVO);
	
	AnalyticsVO selectAnalyticsView(AnalyticsVO analyticsVO);
	
	void analyticsInsert(AnalyticsVO analyticsVO) throws Exception;
	
	void analyticsUpdate(AnalyticsVO analyticsVO) throws Exception;

	void analyticsDelete(AnalyticsVO analyticsVO) throws Exception;

	void analyticsBakInsert(AnalyticsVO analyticsVO) throws Exception; 
	
	Map<String, Object> selectAnalyticsBakList(AnalyticsVO analyticsVO);
	
	AnalyticsVO selectAnalyticsBakView(AnalyticsVO analyticsVO);

}
