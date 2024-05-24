package egovframework.breeze.skin.service;

import java.util.Map;

public interface SkinService {

	Map<String, Object> selectSkinList(SkinVO skinVO);
	
	SkinVO selectSkinView(SkinVO skinVO);
	
	void skinInsert(SkinVO skinVO) throws Exception;
	
	void skinUpdate(SkinVO skinVO) throws Exception;

	void skinDelete(SkinVO skinVO) throws Exception;

	void skinBakInsert(SkinVO skinVO) throws Exception;
	
	Map<String, Object> selectSkinBakList(SkinVO skinVO);
	
	SkinVO selectSkinBakView(SkinVO skinVO);
}
