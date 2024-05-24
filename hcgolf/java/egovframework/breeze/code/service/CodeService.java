package egovframework.breeze.code.service;

import java.util.Map;

public interface CodeService {
	//코드마스터 list
	Map<String, Object> selectCodeMstrList(CodeVO codeVO);
	//코드마스터 detail조회
	public abstract CodeVO selectCodeMstrDetail(CodeVO codeVO);
	//코드마스터 등록
	void codeMasterInsert(CodeVO codeVO) throws Exception;
	//코드마스터 수정
	void codeMasterUpdate(CodeVO codeVO) throws Exception;
	//코드마스터 삭제
	void codeMasterDelete(CodeVO codeVO);
	
	//코드 list
	Map<String, Object> selectCodeList(CodeVO codeVO);
	//코드 등록
	void codeInsert(CodeVO codeVO) throws Exception;
	//코드 삭제
	void codeDelete(CodeVO codeVO);
	
	//코드 name 조회
	String getCodeName(CodeVO codeVO) throws Exception;
}
