package egovframework.breeze.survey.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.common.StringUtil;
import egovframework.breeze.skin.service.SkinService;
import egovframework.breeze.skin.service.SkinVO;
import egovframework.breeze.survey.service.SurveyService;
import egovframework.breeze.survey.service.SurveyVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value = "/_admin/survey")
public class SurveyController {

	@Resource(name="surveyService")
	private SurveyService surveyService;

	/** skinService */
	@Resource(name = "skinService")
	private SkinService skinService;
	
	 /**
     * XSS 방지 처리.
     * 
     * @param data
     * @return
     */
    protected String unscript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

        return ret;
    }
    
	/**
	 * 관리자 > 설문 관리 > 설문 관리
	 * @param request
	 * @param model
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"/surveyList.do"})
	public String surveyList(HttpServletRequest request, ModelMap model, @ModelAttribute("searchVO") SurveyVO surveyVO) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(surveyVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(surveyVO.getPageUnit());
			paginationInfo.setPageSize(surveyVO.getPageSize());
			
			surveyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			surveyVO.setLastIndex(paginationInfo.getLastRecordIndex());
			surveyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String,Object> map = surveyService.selectSurveyList(surveyVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "/admin/survey/surveyList";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 열람 페이지
	 * @param request
	 * @param model
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyView.do")
	public String surveyView(@ModelAttribute("searchVO") SurveyVO surveyVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// 설문조사 조회
			surveyVO = surveyService.selectSurveyView(surveyVO);
			
			// 설문조사 질문 조회 
			List<SurveyVO> questions = surveyService.selectSurveyQuestionList(surveyVO);
			surveyVO.setQuestions ( questions );
			
			// 설문조사 질문 선택지 조회
			for ( SurveyVO question : questions ) {
				List<SurveyVO> answers = surveyService.selectSurveyQuestionAnswerList(question);
				question.setAnswers(answers);
			}
			
			// 질문 번호에 따라 정렬
			Collections.sort( questions, new Comparator() {
			    public int compare(Object x, Object y) {
			        return StringUtil.compareCode( ((SurveyVO)x).getQuestionCode(), ((SurveyVO)y).getQuestionCode(), "-");
			    }
			});
			
			model.addAttribute("result", surveyVO);
			
			return "/admin/survey/surveyView";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 등록/수정 페이지
	 * @param request
	 * @param model
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyForm.do")
	public String surveyForm(@ModelAttribute("searchVO") SurveyVO surveyVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			if ( surveyVO.getCommand().equals("update")) {
				surveyVO = surveyService.selectSurveyView(surveyVO);
			}

			// 스킨 list 조회
			SkinVO skinVO = new SkinVO();
			skinVO.setFirstIndex(0);
			skinVO.setSkinType("SRV");
			skinVO.setRecordCountPerPage(9999);
			Map<String, Object> map = skinService.selectSkinList(skinVO);
			
			model.addAttribute("skinList", map.get("resultList"));
			model.addAttribute("result", surveyVO);
	
			return "/admin/survey/surveyForm";
		}
	}
	
	/**
	 * 관리자 페이지 > 설문 관리 > 설문 관리 > 질문 수정 view
	 * @param surveyVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyQuestionForm.do")
	public String surveyQuestionForm(@ModelAttribute("searchVO") SurveyVO surveyVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			if ( surveyVO.getQuestionId() !=null && surveyVO.getCommand().equals("update") ) {
				// 질문 · 질문 답변 조회
				surveyVO = surveyService.selectSurveyQuestionView(surveyVO);
				surveyVO.setAnswers( surveyService.selectSurveyQuestionAnswerList(surveyVO));	
			}
			
			// 설문조사 답변이 없는 경우 생성
			if (surveyVO.getAnswers()==null || surveyVO.getAnswers().size()==0) {
				
				List<SurveyVO> answers = new ArrayList<SurveyVO> ();	
				for (int j=1; j<=3; j++) {
					SurveyVO answer = new SurveyVO();
					answers.add(answer);
				}
				surveyVO.setAnswers(answers);
			}
			
			model.addAttribute("result", surveyVO);
			
			return "popup/admin/surveyQuestionForm";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 결과 열람 페이지
	 * @param request
	 * @param model
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyResult.do")
	public String surveyResultView(HttpServletRequest request, ModelMap model, @ModelAttribute("searchVO") SurveyVO surveyVO) throws Exception{

		String rspdentId = surveyVO.getRspdentId();
		
		// 설문조사 조회
		surveyVO = surveyService.selectSurveyView(surveyVO);
		
		// 설문조사 질문 조회 
		List<SurveyVO> questions = surveyService.selectSurveyQuestionList(surveyVO);
		surveyVO.setQuestions ( questions );
		
		for ( SurveyVO question : questions ) {
			if (question.getQuestionType().equals("RADIO") || question.getQuestionType().equals("CHECK")) {
				// 설문조사 질문 선택지 조회
				List<SurveyVO> answers = surveyService.selectSurveyQuestionAnswerList(question);
				question.setAnswers(answers);
				
				// 설문조사 결과 조회
				for ( SurveyVO answer : answers ) {
					answer.setRspdentId(rspdentId);
					List<SurveyVO> results = surveyService.selectSurveyResultList(answer);
					answer.setResults(results);
				}
				
				// 기타란 사용지 설문조사 결과 조횐
				if ( question.getUseOtherSelectFlag().equals("Y")) {
					question.setRspdentId(rspdentId);
					List<SurveyVO> results = surveyService.selectSurveyResultList(question);
					question.setResults(results);
				}
			} else if (question.getQuestionType().equals("TEXT")) {
				
				//설문조사 결과 조회
				question.setRspdentId(rspdentId);
				List<SurveyVO> results = surveyService.selectSurveyResultList(question);
				question.setResults(results);
			}
		}
		
		// 질문 번호에 따라 정렬
		Collections.sort( questions, new Comparator() {
		    public int compare(Object x, Object y) {
		        return StringUtil.compareCode( ((SurveyVO)x).getQuestionCode(), ((SurveyVO)y).getQuestionCode(), "-");
		    }
		});
		
		model.addAttribute("result", surveyVO);
		
		return "/admin/survey/surveyResult";
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 결과 > 응답자
	 * 
	 * @param request
	 * @param model
	 * @param surveyVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"/rspdentList.do"})
	public String rspdentList(HttpServletRequest request, ModelMap model, @ModelAttribute("searchVO") SurveyVO surveyVO) throws Exception{
		
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			PaginationInfo paginationInfo = new PaginationInfo();
			
			paginationInfo.setCurrentPageNo(surveyVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(surveyVO.getPageUnit());
			paginationInfo.setPageSize(surveyVO.getPageSize());
			
			surveyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			surveyVO.setLastIndex(paginationInfo.getLastRecordIndex());
			surveyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
			Map<String,Object> map = surveyService.selectRspdentList(surveyVO);
			int totCnt = Integer.parseInt((String)map.get("resultCnt"));
			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("paginationInfo", paginationInfo);
			
			return "/admin/survey/rspdentList";
		}
	}
    
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 등록 action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyInsert.do")
	public String surveyInsert(SurveyVO surveyVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {	
			surveyVO.setSurveyDesc(unscript(surveyVO.getSurveyDesc()));	// XSS 방지
			surveyVO.setSurveyDesc(StringUtil.getStringToTag(surveyVO.getSurveyDesc()));
			
			surveyVO.setRegId(user.getAdminId());
		    
			// 질문 등록
			surveyService.insertSurvey(surveyVO);
			
			model.addAttribute("message", "등록이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/survey/surveyView.do");
			model.addAttribute("hiddenName1", "surveyId");
			model.addAttribute("hiddenValue1", surveyVO.surveyId);
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 수정 action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyUpdate.do")
	public String surveyUpdate(SurveyVO surveyVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			surveyVO.setSurveyDesc(unscript(surveyVO.getSurveyDesc()));	// XSS 방지
			surveyVO.setSurveyDesc(StringUtil.getStringToTag(surveyVO.getSurveyDesc()));
			
			// 수정자 아이디 추가
			surveyVO.setUpdId(user.getAdminId());
			
			surveyService.updateSurvey(surveyVO);
			
			model.addAttribute("message", "수정이 완료되었습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/survey/surveyForm.do");
			model.addAttribute("hiddenName1", "surveyId");
			model.addAttribute("hiddenValue1", surveyVO.surveyId);
			model.addAttribute("hiddenName2", "pageIndex");
			model.addAttribute("hiddenValue2", surveyVO.getPageIndex());
			model.addAttribute("hiddenName3", "searchCnd");
			model.addAttribute("hiddenValue3", surveyVO.getSearchCnd());
			model.addAttribute("hiddenName4", "searchWrd");
			model.addAttribute("hiddenValue4", surveyVO.getSearchWrd());
			model.addAttribute("hiddenName5", "command");
			model.addAttribute("hiddenValue5", "update");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 삭제 action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyDelete.do")
	public String surveyDelete(SurveyVO surveyVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			surveyService.deleteSurvey(surveyVO);
			
			model.addAttribute("message", "설문을 삭제하였습니다.");
			model.addAttribute("retType", ":submit");
			model.addAttribute("retUrl", "/_admin/survey/surveyList.do");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 질문 등록 action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyQuestionInsert.do")
	public String surveyQuestionInsert(SurveyVO questionVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			
			// 질문 등록
			surveyService.insertSurveyQuestion(questionVO);

			// 선택지가 있는 질문이라면 선택지 등록
			if ( !questionVO.getQuestionType().equals("TEXT")) {				
				String[] answerTitles = request.getParameterValues("answerTitles");
				
				for (int i = 0; i< answerTitles.length; i++) {
					SurveyVO ansVO = new SurveyVO();
					
					ansVO.setAnswerTitle(answerTitles[i]);
					
					ansVO.setQuestionId(questionVO.getQuestionId());
					ansVO.setSurveyId(questionVO.getSurveyId());
					
					surveyService.insertSurveyQuestionAnswer(ansVO);
				}
			}
			
			model.addAttribute("message", "질문 등록이 완료되었습니다.");
			model.addAttribute("retType", ":reload_parent_close");
			
			return "/common/message";
		}		
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 질문 수정 action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyQuestionUpdate.do")
	public String surveyQuestionUpdate(SurveyVO questionVO, HttpServletRequest request, final MultipartHttpServletRequest multiRequest, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		}else {
			// 질문 수정
			surveyService.updateSurveyQuestion(questionVO);
			
			// 기존 선택지 모두 삭제
			surveyService.deleteSurveyQuestionAnswerAll(questionVO);

			// 선택지가 있는 질문이라면 선택지 등록
			if ( !questionVO.getQuestionType().equals("TEXT")) {				
				String[] answerTitles = request.getParameterValues("answerTitles");
				
				for (int i = 0; i< answerTitles.length; i++) {
					SurveyVO ansVO = new SurveyVO();
					
					ansVO.setAnswerTitle(answerTitles[i]);
					
					ansVO.setQuestionId(questionVO.getQuestionId());
					ansVO.setSurveyId(questionVO.getSurveyId());
					
					surveyService.insertSurveyQuestionAnswer(ansVO);
				}
			}
	
			model.addAttribute("message", "질문 수정이 완료되었습니다.");
			model.addAttribute("retType", ":reload_parent_close");
			
			return "/common/message";
		}
	}
	
	/**
	 * 관리자 > 설문 관리 > 설문 관리 > 설문 질문 삭제 action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/surveyQuestionDelete.do")
	public String surveyAnqueDelete(SurveyVO surveyVO, HttpServletRequest request, ModelMap model) throws Exception{
		AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if(user == null) {
			return "redirect:/_admin/login.do";
		} else {
			surveyService.deleteSurveyQuestion(surveyVO);
			
			model.addAttribute("message", "질문을 삭제했습니다.");
			model.addAttribute("retType", ":reload_parent_close");
			
			return "/common/message";
		}
	}
	
	/**
	 * 응답 추가 Action
	 * @param serveyVO
	 * @param request
	 * @param multiRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/surveyWrite.do", method = RequestMethod.POST)
	public String surveyWrite(HttpServletRequest request, SurveyVO surveyVO, ModelMap model) throws Exception {
//		LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
//    	if (user == null) {
//    		String returnUrl = "";
//    		request.getSession().setAttribute("returnUrl", returnUrl);
//    		
//			return "redirect:/site/mypage/login.do";
//		}else{
			int queListCnt = Integer.parseInt(request.getParameter("queListCnt"));
			
			SurveyVO respondent = new SurveyVO();
			respondent.setSurveyId(surveyVO.getSurveyId());
			respondent.setMberId("");
			surveyService.insertSurveyRespondent(respondent);
			
			SurveyVO result = respondent;
			for (int i = 0; i < queListCnt; i++) { //질문 갯수만큼 반복			
				result.setQuestionId(request.getParameter("question"+i));
				if(request.getParameter("questionType"+i).equals("RADIO")){//단일선택
					result.setAnswerId(request.getParameter("answer"+i));
					result.setSubjectiveCn("");
					
					surveyService.insertSurveyResult(result);
					
				}else if(request.getParameter("questionType"+i).equals("CHECK")){//다중선택
					result.setSubjectiveCn("");
					
					String[] answer_id = request.getParameterValues("answer"+i);
					
					if (answer_id!=null) {
						for (int j = 0; j < answer_id.length; j++) {
							result.setAnswerId(answer_id[j]);
							surveyService.insertSurveyResult(result);
						}						
					}
					
				}else if(request.getParameter("questionType"+i).equals("TEXT")){//주관식
					result.setAnswerId(null);
					result.setSubjectiveCn(request.getParameter("answerSubject"+i));
					surveyService.insertSurveyResult(result);
					
				}
				
				if(request.getParameter("useOtherSelectFlag"+i).equals("Y")){//단일선택(기타)
					result.setAnswerId(null);
					result.setSubjectiveCn(request.getParameter("answerSubject"+i));
					
					surveyService.insertSurveyResult(result);
				}
				
			}
			
			model.addAttribute("message", "설문 응답을 등록하였습니다.");
			model.addAttribute("retType", ":back");
			model.addAttribute("retUrl", request.getParameter("retUrl"));
			
			return "/common/message";
		}
//	}
}
