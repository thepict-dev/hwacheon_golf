package egovframework.breeze.survey.service;

import java.util.List;
import java.util.Map;

public interface SurveyService {

	void insertSurvey(SurveyVO surveyVO) throws Exception;
	
	void updateSurvey(SurveyVO surveyVO);
	
	SurveyVO selectSurveyView(SurveyVO surveyVO);
	
	Map<String, Object> selectSurveyList(SurveyVO surveyVO);
	
	int selectSurveyListCnt(SurveyVO surveyVO);
	
	void deleteSurvey(SurveyVO surveyVO);
	
	
	int insertSurveyQuestion(SurveyVO surveyVO);
	
	void updateSurveyQuestion(SurveyVO surveyVO);
	
	SurveyVO selectSurveyQuestionView(SurveyVO surveyVO);

	List<SurveyVO> selectSurveyQuestionList(SurveyVO surveyVO);
	
	void deleteSurveyQuestion(SurveyVO surveyVO);
	

	void insertSurveyQuestionAnswer(SurveyVO surveyVO);

	void updateSurveyQuestionAnswer(SurveyVO surveyVO);

	List<SurveyVO> selectSurveyQuestionAnswerList(SurveyVO surveyVO);

	void deleteSurveyQuestionAnswer(SurveyVO surveyVO);
	
	void deleteSurveyQuestionAnswerAll(SurveyVO surveyVO);

	int insertSurveyRespondent(SurveyVO respondent);

	void insertSurveyResult(SurveyVO surveyVO);

	List<SurveyVO> selectSurveyResultList(SurveyVO answer);

	Map<String, Object> selectRspdentList(SurveyVO surveyVO);
}
