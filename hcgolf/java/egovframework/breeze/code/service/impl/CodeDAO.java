package egovframework.breeze.code.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.code.service.CodeVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("CodeDAO")
public class CodeDAO extends EgovComAbstractDAO {
	//코드마스터 list
	public List<?> selectCodeMstrList(CodeVO codeVO) {
		return list("codeMapper.selectCodeMstrList", codeVO);
	}
	//코드마스터 cnt조회
	public int selectCodeMstrListCnt(CodeVO codeVO) {
		return (Integer)selectOne("codeMapper.selectCodeMstrListCnt", codeVO);
	}
	//코드마스터 detail
	public CodeVO selectCodeMstrDetail(CodeVO codeVO) {
		return (CodeVO) selectOne("codeMapper.selectCodeMstrDetail", codeVO);
	}
	//코드마스터 등록
	public void codeMasterInsert(CodeVO codeVO) {
		insert("codeMapper.codeMasterInsert", codeVO);
	}
	//코드마스터 수정
	public void codeMasterUpdate(CodeVO codeVO) {
		update("codeMapper.codeMasterUpdate", codeVO);
	}
	//코드마스터 삭제
	public void codeMasterDelete(CodeVO codeVO) {
		update("codeMapper.codeMasterDelete", codeVO);
	}
	//코드 list
	public List<?> selectCodeList(CodeVO codeVO) {
		return list("codeMapper.selectCodeList", codeVO);
	}
	public int selectCodeListCnt(CodeVO codeVO) {
		return (Integer) selectOne("codeMapper.selectCodeListCnt", codeVO);
	}
	//코드 등록
	public void codeInsert(CodeVO codeVO) {
		update("codeMapper.codeInsert", codeVO);
	}
	//코드 삭제
	public void codeDelete(CodeVO codeVO) {
		update("codeMapper.codeDelete", codeVO);
	}
	public String getCodeName(CodeVO codeVO) {
		return (String) selectOne("codeMapper.getCodeName", codeVO);
	}
}
