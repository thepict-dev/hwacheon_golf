package egovframework.breeze.site.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.site.service.AtchService;
import egovframework.breeze.site.service.AtchVO;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("atchService")
public class AtchServiceImpl implements AtchService{
	
	@Resource(name="atchDAO")
	private AtchDAO atchDAO;
	
	@Resource(name = "atchIdService")
	private EgovIdGnrService idgenService;

	@Override
	public Map<String, Object> selectAtchList(AtchVO atchVO) {
		List<?> list = atchDAO.selectAtchList(atchVO);

		int cnt = atchDAO.selectAtchListCnt(atchVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public AtchVO selectAtchView(AtchVO atchVO) {
		return atchDAO.selectAtchView(atchVO);
	}

	@Override
	public void atchInsert(AtchVO atchVO) throws Exception {
		String atchId = idgenService.getNextStringId();
		atchVO.setAtchId(atchId);
		atchDAO.atchInsert(atchVO);
	}

	@Override
	public void atchUpdate(AtchVO atchVO) throws Exception {
		atchDAO.atchUpdate(atchVO);
	}

	@Override
	public void atchDelete(AtchVO atchVO) throws Exception {
		atchDAO.atchDelete(atchVO);
	}
	
	
	
}
