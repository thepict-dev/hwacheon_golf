package egovframework.breeze.secure.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.secure.service.DefaultService;
import egovframework.breeze.secure.service.DefaultVO;

@Service("defaultService")
public class DefaultServiceImpl implements DefaultService{

	@Resource(name="defaultDAO")
	private DefaultDAO defaultDAO;


	@Override
	public DefaultVO selectDefaultSetting(DefaultVO defaultVO) {
		return defaultDAO.selectDefaultSetting(defaultVO);
	}

	@Override
	public void defaultUpdate(DefaultVO defaultVO) throws Exception {
		defaultDAO.defaultUpdate(defaultVO);
	}

	@Override
	public void defaultReset(DefaultVO defaultVO) throws Exception {
		defaultDAO.defaultReset(defaultVO);
	}
}
