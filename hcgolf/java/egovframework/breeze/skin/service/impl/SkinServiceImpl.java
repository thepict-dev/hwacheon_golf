package egovframework.breeze.skin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.skin.service.SkinService;
import egovframework.breeze.skin.service.SkinVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("skinService")
public class SkinServiceImpl extends EgovAbstractServiceImpl implements SkinService {

	@Resource(name="skinDAO")
	private SkinDAO skinDAO;
	
	@Resource(name = "skinIdService")
	private EgovIdGnrService idgenService;

	@Resource(name = "bakSknIdService")
	private EgovIdGnrService bakIdgenService;
	
	@Override
	public Map<String, Object> selectSkinList(SkinVO skinVO) {
		List<?> list = skinDAO.selectSkinList(skinVO);

		int cnt = skinDAO.selectSkinListCnt(skinVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public SkinVO selectSkinView(SkinVO skinVO) {
		return skinDAO.selectSkinView(skinVO);
	}

	@Override
	public void skinInsert(SkinVO skinVO) throws Exception {
		String skinId = idgenService.getNextStringId();
		skinVO.setSkinId(skinId);
		skinDAO.skinInsert(skinVO);
	}

	@Override
	public void skinUpdate(SkinVO skinVO) throws Exception {
		skinDAO.skinUpdate(skinVO);
	}

	@Override
	public void skinDelete(SkinVO skinVO) throws Exception {
		skinDAO.skinDelete(skinVO);
	}

	@Override
	public void skinBakInsert(SkinVO skinVO) throws Exception {
		String bakSkinId = bakIdgenService.getNextStringId();
		skinVO.setBakSkinId(bakSkinId);
		skinDAO.skinBakInsert(skinVO);
	}
	
	@Override
	public Map<String, Object> selectSkinBakList(SkinVO skinVO) {
		List<?> list = skinDAO.selectSkinBakList(skinVO);

		int cnt = skinDAO.selectSkinListBakCnt(skinVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public SkinVO selectSkinBakView(SkinVO skinVO) {
		return skinDAO.selectSkinBakView(skinVO);
	}
}
