package egovframework.breeze.secure.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.breeze.secure.service.DefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("defaultDAO")
public class DefaultDAO extends EgovComAbstractDAO  {
	

	public DefaultVO selectDefaultSetting(DefaultVO defaultVO) {
		return (DefaultVO)selectOne("defaultMapper.selectDefaultSetting", defaultVO);
	}

	public void defaultUpdate(DefaultVO defaultVO) {
		update("defaultMapper.defaultUpdate", defaultVO);
	}

	public void defaultReset(DefaultVO defaultVO) {
		delete("defaultMapper.defaultReset", defaultVO);
	}
	
}
