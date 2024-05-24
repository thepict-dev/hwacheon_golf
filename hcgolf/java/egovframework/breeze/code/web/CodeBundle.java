package egovframework.breeze.code.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.breeze.code.service.CodeService;
import egovframework.breeze.code.service.CodeVO;

public class CodeBundle {
	private WebApplicationContext context;
    private CodeService codeService;

	public CodeBundle() throws Exception{

	}

	public CodeBundle(HttpServletRequest request){

		this.context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    this.codeService = ((CodeService)this.context.getBean("CodeService"));

	}

	/**
	 * 코드 조회
	 */
	public List<CodeVO> getCodeList(String codeMasterId) {
		CodeVO codeVO = new CodeVO();
		codeVO.setCodeMasterId(codeMasterId);

		Map<String, Object> map = this.codeService.selectCodeList(codeVO);

		List<CodeVO> codeList = (List<CodeVO>) map.get("resultList");

		return codeList;
	}

	/**
	 * 코드마스터 조회
	 */
	public List<CodeVO> getCodeMasterList() {
		CodeVO codeVO = new CodeVO();

		Map<String, Object> map = codeService.selectCodeMstrList(codeVO);

		List<CodeVO> codeMasterList = (List<CodeVO>) map.get("resultList");

		return codeMasterList;
	}
	
	/**
	 * 코드 name 조회
	 */
	/**
	 * 코드마스터 조회
	 * @throws Exception 
	 */
	public String getCodeName(String codeMasterId, String codeId) throws Exception {
		CodeVO codeVO = new CodeVO();
		codeVO.setCodeMasterId(codeMasterId);
		codeVO.setCodeId(codeId);
		
		String codeName = codeService.getCodeName(codeVO);

		return codeName;
	}
}