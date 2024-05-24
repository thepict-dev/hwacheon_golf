package egovframework.breeze.site.service;

import java.util.Map;

import egovframework.rte.fdl.cmmn.exception.FdlException;

public interface AtchService {

	Map<String, Object> selectAtchList(AtchVO atchVO);

	AtchVO selectAtchView(AtchVO atchVO);

	void atchInsert(AtchVO atchVO) throws Exception;

	void atchUpdate(AtchVO atchVO) throws Exception;

	void atchDelete(AtchVO atchVO) throws Exception;
	


}
