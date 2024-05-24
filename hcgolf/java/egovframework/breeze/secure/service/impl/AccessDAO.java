package egovframework.breeze.secure.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.secure.service.AccessVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("accessDAO")
public class AccessDAO extends EgovComAbstractDAO  {
	
	public List<?> selectAccessList(AccessVO accessVO){
		return list("accessMapper.selectAccessList", accessVO);
	}
	
	public int selectAccessListCnt(AccessVO accessVO) {
		return (Integer)selectOne("accessMapper.selectAccessListCnt", accessVO);
	}
	
	public AccessVO selectAccessView(AccessVO accessVO) {
		return (AccessVO)selectOne("accessMapper.selectAccessView", accessVO);
	}
	
	public void accessInsert(AccessVO accessVO) throws Exception{
		insert("accessMapper.accessInsert", accessVO);
	}
	
	public void accessUpdate(AccessVO accessVO) throws Exception{
		update("accessMapper.accessUpdate", accessVO);
	}

	public void accessDelete(AccessVO accessVO) throws Exception{
		update("accessMapper.accessDelete", accessVO);
	}
	
	public int selectAuthorAccess(String accessIp) {
		return (Integer)selectOne("accessMapper.selectAuthorAccess", accessIp);
	}
}
