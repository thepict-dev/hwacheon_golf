package egovframework.breeze.survey.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.survey.service.SurveyService;
import egovframework.breeze.survey.service.SurveyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("surveyService")
public class SurveyServiceImpl extends EgovAbstractServiceImpl implements SurveyService{

	@Resource(name="surveyDAO")
	private SurveyDAO surveyDAO;

	@Resource(name = "surveyIdService")
	private EgovIdGnrService idgenService;

	@Override
	public void insertSurvey(SurveyVO surveyVO) throws Exception {
		String surveyId = idgenService.getNextStringId();
		surveyVO.setSurveyId(surveyId);
		surveyDAO.insertSurvey(surveyVO);
	}

	@Override
	public void updateSurvey(SurveyVO surveyVO) {
		surveyDAO.updateSurvey(surveyVO);
	}

	@Override
	public SurveyVO selectSurveyView(SurveyVO surveyVO) {
		return surveyDAO.selectSurveyView(surveyVO);
	}

	@Override
	public Map<String, Object> selectSurveyList(SurveyVO surveyVO) {
		List<?> list = surveyDAO.selectSurveyList(surveyVO);
		
		int cnt = surveyDAO.selectSurveyListCnt(surveyVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}

	@Override
	public int selectSurveyListCnt(SurveyVO surveyVO) {
		return surveyDAO.selectSurveyListCnt(surveyVO);
	}
	
	@Override
	public void deleteSurvey(SurveyVO surveyVO) {
		surveyDAO.deleteSurvey(surveyVO);
	}
	
	@Override
	public int insertSurveyQuestion(SurveyVO surveyVO) {
		return surveyDAO.insertSurveyQuestion(surveyVO);
	}

	@Override
	public void updateSurveyQuestion(SurveyVO surveyVO) {
		surveyDAO.updateSurveyQuestion(surveyVO);
	}

	@Override
	public SurveyVO selectSurveyQuestionView(SurveyVO surveyVO) {
		return surveyDAO.selectSurveyQuestionView(surveyVO);
	}

	@Override
	public List<SurveyVO> selectSurveyQuestionList(SurveyVO surveyVO) {
		return (List<SurveyVO>) surveyDAO.selectSurveyQuestionList(surveyVO);
	}

	@Override
	public void deleteSurveyQuestion(SurveyVO surveyVO) {
		surveyDAO.deleteSurveyQuestion(surveyVO);
	}
	
	@Override
	public void insertSurveyQuestionAnswer(SurveyVO surveyVO) {
		surveyDAO.insertSurveyQuestionAnswer(surveyVO);
	}

	@Override
	public void updateSurveyQuestionAnswer(SurveyVO surveyVO) {
		surveyDAO.updateSurveyQuestionAnswer(surveyVO);
	}

	@Override
	public List<SurveyVO> selectSurveyQuestionAnswerList(SurveyVO surveyVO) {
		return (List<SurveyVO>) surveyDAO.selectSurveyQuestionAnswerList(surveyVO);
	}

	@Override
	public void deleteSurveyQuestionAnswer(SurveyVO surveyVO) {
		surveyDAO.deleteSurveyQuestionAnswer(surveyVO);
	}
	
	@Override
	public void deleteSurveyQuestionAnswerAll(SurveyVO surveyVO) {
		surveyDAO.deleteSurveyQuestionAnswerAll(surveyVO);
	}
	
	@Override
	public int insertSurveyRespondent(SurveyVO surveyVO) {
		return surveyDAO.insertSurveyRespondent(surveyVO);
	}
	
	@Override
	public void insertSurveyResult(SurveyVO surveyVO) {
		surveyDAO.insertSurveyResult(surveyVO);
	}

	@Override
	public List<SurveyVO> selectSurveyResultList(SurveyVO surveyVO) {
		return (List<SurveyVO>) surveyDAO.selectSurveyResultList(surveyVO);
	}

	@Override
	public Map<String, Object> selectRspdentList(SurveyVO surveyVO) {
		List<?> list = surveyDAO.selectRspdentList(surveyVO);
		
		int cnt = surveyDAO.selectRspdentListCnt(surveyVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
	}
}
