package egovframework.breeze.secure.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.secure.service.AccessService;
import egovframework.breeze.secure.service.AccessVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("accessService")
public class AccessServiceImpl implements AccessService{

	@Resource(name="accessDAO")
	private AccessDAO accessDAO;
	
	@Resource(name = "accessIdService")
	private EgovIdGnrService idgenService;
	
	@Override
	public Map<String, Object> selectAccessList(AccessVO accessVO){
		List<?> list = accessDAO.selectAccessList(accessVO);
		int cnt = accessDAO.selectAccessListCnt(accessVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}
	
	@Override
	public AccessVO selectAccessView(AccessVO accessVO) {
		return accessDAO.selectAccessView(accessVO);
	}
	
	@Override
	public void accessInsert(AccessVO accessVO) throws Exception{
		String accessId = idgenService.getNextStringId();
		accessVO.setAccessId(accessId);
		accessDAO.accessInsert(accessVO);
	}
	
	@Override
	public void accessUpdate(AccessVO accessVO) throws Exception{
		accessDAO.accessUpdate(accessVO);
	}

	@Override
	public void accessDelete(AccessVO accessVO) throws Exception{
		accessDAO.accessDelete(accessVO);
	}
	
	@Override
	public int selectAuthorAccess(String accessIp) throws Exception{
		return accessDAO.selectAuthorAccess(accessIp);
	}
	
}
