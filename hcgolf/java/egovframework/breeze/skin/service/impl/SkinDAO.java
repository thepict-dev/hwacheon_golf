package egovframework.breeze.skin.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.skin.service.SkinVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("skinDAO")
public class SkinDAO extends EgovComAbstractDAO {
	
	public List<?> selectSkinList(SkinVO skinVO) {
		return list("skinMapper.selectSkinList", skinVO);
	}

	public int selectSkinListCnt(SkinVO skinVO) {
		return (Integer)selectOne("skinMapper.selectSkinListCnt", skinVO);
	}

	public SkinVO selectSkinView(SkinVO skinVO) {
		return (SkinVO)selectOne("skinMapper.selectSkinView", skinVO);
	}

	public void skinInsert(SkinVO skinVO) {
		insert("skinMapper.skinInsert", skinVO);
	}

	public void skinUpdate(SkinVO skinVO) {
		update("skinMapper.skinUpdate", skinVO);
	}

	public void skinDelete(SkinVO skinVO) {
		update("skinMapper.skinDelete", skinVO);
	}

	public void skinBakInsert(SkinVO skinVO) {
		insert("skinMapper.skinBakInsert", skinVO);
	}
	
	public List<?> selectSkinBakList(SkinVO skinVO) {
		return list("skinMapper.selectSkinBakList", skinVO);
	}

	public int selectSkinListBakCnt(SkinVO skinVO) {
		return (Integer)selectOne("skinMapper.selectSkinBakListCnt", skinVO);
	}

	public SkinVO selectSkinBakView(SkinVO skinVO) {
		return (SkinVO)selectOne("skinMapper.selectSkinBakView", skinVO);
	}
}
