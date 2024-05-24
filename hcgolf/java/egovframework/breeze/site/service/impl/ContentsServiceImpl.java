package egovframework.breeze.site.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.site.service.ContentsService;
import egovframework.breeze.site.service.ContentsVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("contentsService")
public class ContentsServiceImpl implements ContentsService{
	@Resource(name="contentsDAO")
	private ContentsDAO contentsDAO;
	
	@Resource(name = "contentsIdService")
	private EgovIdGnrService idgenService;
	
	@Resource(name = "bakCntIdService")
	private EgovIdGnrService bakIdgenService;
	
	
	@Override
	public Map<String, Object> selectContentsList(ContentsVO contentsVO) {
		List<?> list = contentsDAO.selectContentsList(contentsVO);
		
		int cnt = contentsDAO.selectContentsListCnt(contentsVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}
	
	@Override
	public ContentsVO selectContentsView(ContentsVO contentsVO) {
		return contentsDAO.selectContentsView(contentsVO);
	}
	
	@Override
	public void contentsInsert(ContentsVO contentsVO) throws Exception {
		String contentsId = idgenService.getNextStringId();
		contentsVO.setContentsId(contentsId);
		contentsDAO.contentsInsert(contentsVO);
	}
	
	@Override
	public void contentsUpdate(ContentsVO contentsVO) throws Exception {
		contentsDAO.contentsUpdate(contentsVO);
	}

	@Override
	public void contentsDelete(ContentsVO contentsVO) throws Exception {
		contentsDAO.contentsDelete(contentsVO);
	}

	@Override
	public void contentsBakInsert(ContentsVO contentsVO) throws Exception {
		String bakContentsId = bakIdgenService.getNextStringId();
		contentsVO.setBakContentsId(bakContentsId);
		contentsDAO.contentsBakInsert(contentsVO);
	}
	
	@Override
	public Map<String, Object> selectContentsBakList(ContentsVO contentsVO) {
		List<?> list = contentsDAO.selectContentsBakList(contentsVO);
		
		int cnt = contentsDAO.selectContentsListBakCnt(contentsVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}
	
	@Override
	public ContentsVO selectContentsBakView(ContentsVO contentsVO) {
		return contentsDAO.selectContentsBakView(contentsVO);
	}
}
