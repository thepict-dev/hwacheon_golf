package egovframework.breeze.member.service;

import java.util.List;
import java.util.Map;


public interface MemberService {
	
	// 관리자 > 회원 관리 > 회원  리스트
	Map<String, Object> selectMemberList(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 회원 조회
	MemberVO selectMemberView(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 회원 등록
	void memberInsert(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 회원 수정
	void memberUpdate(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 회원 삭제
	void memberDeletePermanent(MemberVO vo) throws Exception;
	
	// 관리자 > 회원 관리 > 소속  리스트
	Map<String, Object> selectDepartmentList(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 소속 조회
	MemberVO selectDepartmentView(MemberVO vo) throws Exception;
	
	// 사업자 등록 번호 중복 조회
	int businessOverlapCheck(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 소속 등록
	void departmentInsert(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 소속 수정
	void departmentUpdate(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 소속 삭제
	void departmentDelete(MemberVO vo) throws Exception;
	
	// 관리자 > 회원 관리 > 회원 form > 소속 list 조회
	List<?> departmentList(MemberVO vo) throws Exception;

	// 관리자 > 회원 관리 > 소속 form > 해당 소속인 회원이 있는지 확인
	public int selectMemberCnt(MemberVO vo);

	// 사용자 > 로그인 체크
	MemberVO selectMemberloginCheck(MemberVO memberVO) throws Exception;

}
