package egovframework.breeze.survey.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.survey.service.SurveyVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("surveyDAO")
public class SurveyDAO extends EgovComAbstractDAO {
	
	// Survey
	public void insertSurvey(SurveyVO surveyVO) {
		insert("surveyMapper.insertSurvey", surveyVO);
	}
	
	public void updateSurvey(SurveyVO surveyVO) {
		update("surveyMapper.updateSurvey", surveyVO);
	}
	
	public SurveyVO selectSurveyView(SurveyVO surveyVO) {
		return selectOne("surveyMapper.selectSurveyView", surveyVO);
	}
	
	public List<?> selectSurveyList(SurveyVO surveyVO) {
		return list("surveyMapper.selectSurveyList", surveyVO);
	}
	
	public int selectSurveyListCnt(SurveyVO surveyVO) {
		return selectOne("surveyMapper.selectSurveyListCnt", surveyVO);
	}
	
	public void deleteSurvey(SurveyVO surveyVO) {
		delete("surveyMapper.deleteSurvey", surveyVO);
	}
	
	
	// Survey Quetion
	public int insertSurveyQuestion(SurveyVO surveyVO) {
		return insert("surveyMapper.insertSurveyQuestion", surveyVO);
	}
	
	public void updateSurveyQuestion(SurveyVO surveyVO) {
		update("surveyMapper.updateSurveyQuestion", surveyVO);
	}
	
	public SurveyVO selectSurveyQuestionView(SurveyVO surveyVO) {
		return selectOne("surveyMapper.selectSurveyQuestionView", surveyVO);
	}

	public List<?> selectSurveyQuestionList(SurveyVO surveyVO) {
		return list("surveyMapper.selectSurveyQuestionList", surveyVO);
	}
	
	public void deleteSurveyQuestion(SurveyVO surveyVO) {
		delete("surveyMapper.deleteSurveyQuestion", surveyVO);
	}
	
	
	// Survey Question Answer
	public void insertSurveyQuestionAnswer(SurveyVO surveyVO) {
		insert("surveyMapper.insertSurveyQuestionAnswer", surveyVO);
	}
	
	public void updateSurveyQuestionAnswer(SurveyVO surveyVO) {
		update("surveyMapper.updateSurveyQuestionAnswer", surveyVO);
	}

	public List<?> selectSurveyQuestionAnswerList(SurveyVO surveyVO) {
		return list("surveyMapper.selectSurveyQuestionAnswerList", surveyVO);
	}
	
	public void deleteSurveyQuestionAnswer(SurveyVO surveyVO) {
		update("surveyMapper.deleteSurveyQuestionAnswer", surveyVO);
	}
	
	public void deleteSurveyQuestionAnswerAll(SurveyVO surveyVO) {
		delete("surveyMapper.deleteSurveyQuestionAnswerAll", surveyVO);
	}

	public int insertSurveyRespondent(SurveyVO surveyVO) {
		return insert("surveyMapper.insertSurveyRespondent", surveyVO);
	}

	public void insertSurveyResult(SurveyVO surveyVO) {
		insert("surveyMapper.insertSurveyResult", surveyVO);
	}

	public List<?> selectSurveyResultList(SurveyVO surveyVO) {
		return list("surveyMapper.selectSurveyResultList", surveyVO);
	}

	public List<?> selectRspdentList(SurveyVO surveyVO) {
		return list("surveyMapper.selectRspdentList", surveyVO);
	}

	public int selectRspdentListCnt(SurveyVO surveyVO) {
		return selectOne("surveyMapper.selectRspdentListCnt", surveyVO);
	}
}
