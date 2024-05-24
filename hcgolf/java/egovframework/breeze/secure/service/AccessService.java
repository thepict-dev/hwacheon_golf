package egovframework.breeze.secure.service;

import java.util.Map;

public interface AccessService {
	
	Map<String, Object> selectAccessList(AccessVO accessVO);
	
	AccessVO selectAccessView(AccessVO accessVO);
	
	void accessInsert(AccessVO accessVO) throws Exception;
	
	void accessUpdate(AccessVO accessVO) throws Exception;

	void accessDelete(AccessVO accessVO) throws Exception;
	
	int selectAuthorAccess(String accessIp) throws Exception;
}
