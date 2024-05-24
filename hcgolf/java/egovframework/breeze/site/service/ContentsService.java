package egovframework.breeze.site.service;

import java.util.Map;

public interface ContentsService {
	Map<String, Object> selectContentsList(ContentsVO contentsVO);
	
	ContentsVO selectContentsView(ContentsVO contentsVO);
	
	void contentsInsert(ContentsVO contentsVO) throws Exception;
	
	void contentsUpdate(ContentsVO contentsVO) throws Exception;

	void contentsDelete(ContentsVO contentsVO) throws Exception;

	void contentsBakInsert(ContentsVO contentsVO) throws Exception;
	
	Map<String, Object> selectContentsBakList(ContentsVO contentsVO);
	
	ContentsVO selectContentsBakView(ContentsVO contentsVO);
}
