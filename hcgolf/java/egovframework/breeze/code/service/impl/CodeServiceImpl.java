package egovframework.breeze.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.code.service.CodeService;
import egovframework.breeze.code.service.CodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("CodeService")
public class CodeServiceImpl extends EgovAbstractServiceImpl implements CodeService {

	@Resource(name = "CodeDAO")
    private CodeDAO CodeDao;

    @Resource(name = "codeIdService")
    private EgovIdGnrService idgenService;
	//코드마스터 list
	@Override
	public Map<String, Object> selectCodeMstrList(CodeVO codeVO) {
		List<?> result = CodeDao.selectCodeMstrList(codeVO);
		int cnt = CodeDao.selectCodeMstrListCnt(codeVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	//코드마스터 detail
	@Override
	public CodeVO selectCodeMstrDetail(CodeVO codeVO) {
		return CodeDao.selectCodeMstrDetail(codeVO);
	}
	//코드마스터 등록
	@Override
	public void codeMasterInsert(CodeVO codeVO) throws Exception {
		
		//코드 ID 채번
		String codeMasterId = idgenService.getNextStringId();
		codeVO.setCodeMasterId(codeMasterId);
		
		CodeDao.codeMasterInsert(codeVO);

	}
	//코드마스터 수정
	@Override
	public void codeMasterUpdate(CodeVO codeVO) throws Exception {
		CodeDao.codeMasterUpdate(codeVO);
		
	}
	//코드마스터 삭제
	@Override
	public void codeMasterDelete(CodeVO codeVO) {
		CodeDao.codeMasterDelete(codeVO);	
	}
	//코드 list
	@Override
	public Map<String, Object> selectCodeList(CodeVO codeVO) {
		List<?> result = CodeDao.selectCodeList(codeVO);
    	int cnt = CodeDao.selectCodeListCnt(codeVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	//코드 등록
	@Override
	public void codeInsert(CodeVO codeVO) throws Exception {
		CodeDao.codeInsert(codeVO);
		
	}
	//코드 삭제
	@Override
	public void codeDelete(CodeVO codeVO) {
		CodeDao.codeDelete(codeVO);	
	}
	
	//코드 name 조회
	@Override
	public String getCodeName(CodeVO codeVO) throws Exception {
		return CodeDao.getCodeName(codeVO);
	}
}
