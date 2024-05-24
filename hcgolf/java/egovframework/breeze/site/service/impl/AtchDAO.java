package egovframework.breeze.site.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.site.service.AnalyticsVO;
import egovframework.breeze.site.service.AtchVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("atchDAO")
public class AtchDAO extends EgovComAbstractDAO {

	public List<?> selectAtchList(AtchVO atchVO) {
		return list("atchMapper.selectAtchList", atchVO);
	}

	public int selectAtchListCnt(AtchVO atchVO) {
		return (int)selectOne("atchMapper.selectAtchListCnt", atchVO);
	}

	public AtchVO selectAtchView(AtchVO atchVO) {
		return (AtchVO)selectOne("atchMapper.selectAtchView", atchVO);
	}

	public void atchInsert(AtchVO atchVO) {
		insert("atchMapper.atchInsert", atchVO);
	}

	public void atchUpdate(AtchVO atchVO) {
		update("atchMapper.atchUpdate", atchVO);
	}

	public void atchDelete(AtchVO atchVO) {
		update("atchMapper.atchDelete", atchVO);		
	}
	
	
}
