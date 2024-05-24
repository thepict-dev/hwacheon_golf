package egovframework.breeze.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.breeze.member.service.MemberVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends EgovComAbstractDAO {


	// 관리자 > 회원 관리 > 회원  리스트
	public List<?> selectMemberList(MemberVO vo){
		return list("memberMapper.selectMemberList", vo);
	}
	public int selectMemberListCnt(MemberVO vo) {
		return (Integer) selectOne("memberMapper.selectMemberListCnt", vo);
	}

	// 관리자 > 회원 관리 > 회원  조회
	public MemberVO selectMemberView(MemberVO vo) {
		return (MemberVO) selectOne("memberMapper.selectMemberView", vo);
	}
	
	// 관리자 > 회원 관리 > 회원 등록
	public void memberInsert(MemberVO vo) {
		insert("memberMapper.memberInsert", vo);
	}

	// 관리자 > 회원 관리 > 회원 수정
	public void memberUpdate(MemberVO vo) {
		update("memberMapper.memberUpdate", vo);
	}

	// 관리자 > 회원 관리 > 회원 삭제
	public void memberDeletePermanent(MemberVO vo) {
		update("memberMapper.memberDeletePermanent", vo);
	}

	// 관리자 > 회원 관리 > 소속  리스트
	public List<?> selectDepartmentList(MemberVO vo){
		return list("memberMapper.selectDepartmentList", vo);
	}
	public int selectDepartmentListCnt(MemberVO vo) {
		return (Integer) selectOne("memberMapper.selectDepartmentListCnt", vo);
	}

	// 관리자 > 회원 관리 > 소속  조회
	public MemberVO selectDepartmentView(MemberVO vo) {
		return (MemberVO) selectOne("memberMapper.selectDepartmentView", vo);
	}
	
	// 사업자 번호 중복 조회
	public int businessOverlapCheck(MemberVO vo) {
		return (Integer) selectOne("memberMapper.businessOverlapCheck", vo);
	}
	
	// 관리자 > 회원 관리 > 소속 등록
	public void departmentInsert(MemberVO vo) {
		insert("memberMapper.departmentInsert", vo);
	}

	// 관리자 > 회원 관리 > 소속 수정
	public void departmentUpdate(MemberVO vo) {
		update("memberMapper.departmentUpdate", vo);
	}

	// 관리자 > 회원 관리 > 소속 삭제
	public void departmentDelete(MemberVO vo) {
		update("memberMapper.departmentDelete", vo);
	}
	
	// 관리자 > 회원 관리 > 회원 form > 소속 list 조회
	public List<?> departmentList(MemberVO vo){
		return list("memberMapper.departmentList", vo);
	}

	// 관리자 > 회원 관리 > 소속 form > 해당 소속인 회원이 있는지 확인
	public int selectMemberCnt(MemberVO vo) {
		return (Integer)selectOne("memberMapper.selectMemberCnt", vo);
	}

	// 사용자 > 로그인 체크
	public MemberVO selectMemberloginCheck(MemberVO memberVO) {
		return (MemberVO) selectOne("memberMapper.selectMemberloginCheck", memberVO);
	}
}
