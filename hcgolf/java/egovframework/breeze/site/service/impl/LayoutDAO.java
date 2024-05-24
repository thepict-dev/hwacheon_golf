package egovframework.breeze.site.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.site.service.LayoutVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("layoutDAO")
public class LayoutDAO extends EgovComAbstractDAO {
	

	public List<?> selectLayoutList(LayoutVO layoutVO) {
		return list("layoutMapper.selectLayoutList", layoutVO);
	}

	public int selectLayoutListCnt(LayoutVO layoutVO) {
		return (Integer)selectOne("layoutMapper.selectLayoutListCnt", layoutVO);
	}

	public LayoutVO selectLayoutView(LayoutVO layoutVO) {
		return (LayoutVO)selectOne("layoutMapper.selectLayoutView", layoutVO);
	}

	public void layoutInsert(LayoutVO layoutVO) {
		insert("layoutMapper.layoutInsert", layoutVO);
	}

	public void layoutUpdate(LayoutVO layoutVO) {
		update("layoutMapper.layoutUpdate", layoutVO);
	}

	public void layoutDelete(LayoutVO layoutVO) {
		update("layoutMapper.layoutDelete", layoutVO);
	}

	public void layoutBakInsert(LayoutVO layoutVO) {
		insert("layoutMapper.layoutBakInsert", layoutVO);
	}
	
	public List<?> selectLayoutBakList(LayoutVO layoutVO) {
		return list("layoutMapper.selectLayoutBakList", layoutVO);
	}

	public int selectLayoutListBakCnt(LayoutVO layoutVO) {
		return (Integer)selectOne("layoutMapper.selectLayoutBakListCnt", layoutVO);
	}

	public LayoutVO selectLayoutBakView(LayoutVO layoutVO) {
		return (LayoutVO)selectOne("layoutMapper.selectLayoutBakView", layoutVO);
	}
}
