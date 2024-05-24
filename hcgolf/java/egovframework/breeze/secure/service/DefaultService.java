package egovframework.breeze.secure.service;

public interface DefaultService {

	public DefaultVO selectDefaultSetting(DefaultVO defaultVO);
	
	public void defaultUpdate(DefaultVO defaultVO) throws Exception;
	
	public void defaultReset(DefaultVO defaultVO) throws Exception;
	
}
