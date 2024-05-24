package egovframework.breeze.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.breeze.admin.service.AdminVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("adminDAO")
public class AdminDAO extends EgovComAbstractDAO {

	// 관리자 로그인 처리
	public AdminVO adminLogin(AdminVO vo) {
		return (AdminVO) selectOne("adminMapper.adminLogin", vo);
	}
	
	// 로그인 액션 기록
	public void adminLoginUpdate(AdminVO vo) {
		update("adminMapper.adminLoginUpdate", vo);
	}
    
	// 계정 상태 업데이트
	public void adminStatusUpdate(AdminVO vo) {
		update("adminMapper.adminStatusUpdate", vo);
	}
	
	// 관리자 페이지 > 보안 관리 > 관리자 리스트
	public List<?> selectAdminList(AdminVO vo){
		return list("adminMapper.selectAdminList", vo);
	}
	
	public int selectAdminListCnt(AdminVO vo) {
		return (Integer) selectOne("adminMapper.selectAdminListCnt", vo);
	}
	
	// 관리자 페이지 > 보안 관리 > 관리자 상세조회
	public AdminVO selectAdminView(AdminVO vo) {
		return (AdminVO) selectOne("adminMapper.selectAdminView", vo);
	}
	
	// 관리자 페이지 > 보안 관리 > 관리자 등록
	public void adminInsert(AdminVO vo) {
		insert("adminMapper.adminInsert", vo);
	}
	
	// 관리자 페이지 > 보안 관리 > 관리자 수정
	public void adminUpdate(AdminVO vo) {
		update("adminMapper.adminUpdate", vo);
	}
	
	// 관리자 페이지 > 보안 관리 > 관리자 삭제
	public void adminDelete(AdminVO vo) {
		update("adminMapper.adminDelete", vo);
	}
	
	// 관리자 페이지 > 보안 관리 > 관리자 영구삭제
	public void adminDeletePermanent(AdminVO vo) {
		delete("adminMapper.adminDeletePermanent", vo);
	}
}
