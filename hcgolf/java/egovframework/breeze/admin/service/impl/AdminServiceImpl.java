package egovframework.breeze.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.admin.service.AdminService;
import egovframework.breeze.admin.service.AdminVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("adminService")
public class AdminServiceImpl extends EgovAbstractServiceImpl implements AdminService {

	@Resource(name="adminDAO")
	private AdminDAO adminDAO;
	
	// 관리자 로그인 처리
    @Override
	public AdminVO adminLogin(AdminVO vo) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getAdminPw(), vo.getAdminId());
    	vo.setAdminPw(enpassword);

    	// 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
    	AdminVO adminVO = adminDAO.adminLogin(vo);

    	// 3. 결과를 리턴한다.
    	if (adminVO != null && !adminVO.getAdminId().equals("") && !adminVO.getAdminPw().equals("")) {
    		return adminVO;
    	} else {
    		adminVO = new AdminVO();
    	}

    	return adminVO;
    }
    
    // 로그인 액션 기록
    @Override
 	public void adminLoginUpdate(AdminVO vo) throws Exception {
    	adminDAO.adminLoginUpdate(vo);
    }
	
    // 계정 상태 업데이트
    @Override
	public void adminStatusUpdate(AdminVO vo) throws Exception {
    	adminDAO.adminStatusUpdate(vo);
    }
    
    // 관리자 페이지 > 보안 관리 > 관리자 리스트
    @Override
 	public Map<String, Object> selectAdminList(AdminVO vo) throws Exception{
    	List<?> list = adminDAO.selectAdminList(vo);
    	int cnt = adminDAO.selectAdminListCnt(vo);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
    }
 	
 	// 관리자 페이지 > 보안 관리 > 관리자 상세조회
    @Override
    public AdminVO selectAdminView(AdminVO vo) throws Exception{
    	return adminDAO.selectAdminView(vo);
    }
 	
 	// 관리자 페이지 > 보안 관리 > 관리자 등록
    @Override
    public void adminInsert(AdminVO vo) throws Exception{
    	String enpassword = EgovFileScrty.encryptPassword(vo.getAdminPw(), vo.getAdminId());
    	vo.setAdminPw(enpassword);
    	adminDAO.adminInsert(vo);
    }
 	
 	// 관리자 페이지 > 보안 관리 > 관리자 수정
    @Override
    public void adminUpdate(AdminVO vo) throws Exception{
    	if(vo.getAdminPw() != null && !vo.getAdminPw().equals("")) {
	    	String enpassword = EgovFileScrty.encryptPassword(vo.getAdminPw(), vo.getAdminId());
	    	vo.setAdminPw(enpassword);
    	}
    	adminDAO.adminUpdate(vo);
    }
 	
 	// 관리자 페이지 > 보안 관리 > 관리자 삭제
    @Override
    public void adminDelete(AdminVO vo) throws Exception{
    	adminDAO.adminDelete(vo);
    }
    
    // 관리자 페이지 > 보안 관리 > 관리자 영구삭제
    @Override
    public void adminDeletePermanent(AdminVO vo) throws Exception{
    	adminDAO.adminDeletePermanent(vo);
    }
}
