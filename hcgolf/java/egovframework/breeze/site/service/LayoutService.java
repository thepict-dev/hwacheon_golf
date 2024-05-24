package egovframework.breeze.site.service;

import java.util.Map;

public interface LayoutService {

	Map<String, Object> selectLayoutList(LayoutVO layoutVO);
	
	LayoutVO selectLayoutView(LayoutVO layoutVO);
	
	void layoutInsert(LayoutVO layoutVO) throws Exception;
	
	void layoutUpdate(LayoutVO layoutVO) throws Exception;

	void layoutDelete(LayoutVO layoutVO) throws Exception;

	void layoutBakInsert(LayoutVO layoutVO) throws Exception;
	
	Map<String, Object> selectLayoutBakList(LayoutVO layoutVO);
	
	LayoutVO selectLayoutBakView(LayoutVO layoutVO);
}
