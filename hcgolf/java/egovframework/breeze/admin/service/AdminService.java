package egovframework.breeze.admin.service;

import java.util.List;
import java.util.Map;

public interface AdminService {

	// 관리자 로그인 처리
    AdminVO adminLogin(AdminVO vo) throws Exception;
    
    // 로그인 액션 기록
	void adminLoginUpdate(AdminVO vo) throws Exception;
	
	// 계정 상태 업데이트
	void adminStatusUpdate(AdminVO vo) throws Exception;
	
	// 관리자 페이지 > 보안 관리 > 관리자 리스트
	Map<String, Object> selectAdminList(AdminVO vo) throws Exception;
	
	// 관리자 페이지 > 보안 관리 > 관리자 상세조회
	AdminVO selectAdminView(AdminVO vo) throws Exception;
	
	// 관리자 페이지 > 보안 관리 > 관리자 등록
	void adminInsert(AdminVO vo) throws Exception;
	
	// 관리자 페이지 > 보안 관리 > 관리자 수정
	void adminUpdate(AdminVO vo) throws Exception;
	
	// 관리자 페이지 > 보안 관리 > 관리자 삭제
	void adminDelete(AdminVO vo) throws Exception;
	
	// 관리자 페이지 > 보안 관리 > 관리자 영구삭제
	void adminDeletePermanent(AdminVO vo) throws Exception;
}
