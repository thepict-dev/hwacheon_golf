package egovframework.breeze.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.breeze.member.service.MemberService;
import egovframework.breeze.member.service.MemberVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {

	@Resource(name="memberDAO")
	private MemberDAO memberDAO;

    @Resource(name = "departmentIdService")
    private EgovIdGnrService idgenService;


	// 관리자 > 회원 관리 > 회원  리스트
    @Override
 	public Map<String, Object> selectMemberList(MemberVO vo) throws Exception{
    	List<?> list = memberDAO.selectMemberList(vo);
    	int cnt = memberDAO.selectMemberListCnt(vo);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
    }

	// 관리자 > 회원 관리 > 회원  조회
    @Override
    public MemberVO selectMemberView(MemberVO vo) throws Exception{
    	return memberDAO.selectMemberView(vo);
    }

	// 관리자 > 회원 관리 > 회원 등록
    @Override
    public void memberInsert(MemberVO vo) throws Exception{
    	String enpassword = EgovFileScrty.encryptPassword(vo.getMemberPw(), vo.getMemberId());
    	vo.setMemberPw(enpassword);
    	memberDAO.memberInsert(vo);
    }

	// 관리자 > 회원 관리 > 회원 수정
    @Override
    public void memberUpdate(MemberVO vo) throws Exception{
    	if(vo.getMemberPw() != null && !vo.getMemberPw().equals("")) {
	    	String enpassword = EgovFileScrty.encryptPassword(vo.getMemberPw(), vo.getMemberId());
	    	vo.setMemberPw(enpassword);
    	}
    	memberDAO.memberUpdate(vo);
    }

	// 관리자 > 회원 관리 > 회원 삭제
    @Override
    public void memberDeletePermanent(MemberVO vo) throws Exception{
    	memberDAO.memberDeletePermanent(vo);
    }


	// 관리자 > 회원 관리 > 소속  리스트
    @Override
 	public Map<String, Object> selectDepartmentList(MemberVO vo) throws Exception{
    	List<?> list = memberDAO.selectDepartmentList(vo);
    	int cnt = memberDAO.selectDepartmentListCnt(vo);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", list);
		map.put("resultCnt", Integer.toString(cnt));
		
		return map;
    }

	// 관리자 > 회원 관리 > 소속  조회
    @Override
    public MemberVO selectDepartmentView(MemberVO vo) throws Exception{
    	return memberDAO.selectDepartmentView(vo);
    }
    
    // 사업자 등록 번호 중복 조회
    @Override
 	public int businessOverlapCheck(MemberVO vo) throws Exception{
    	return memberDAO.businessOverlapCheck(vo);
    }

	// 관리자 > 회원 관리 > 소속 등록
    @Override
    public void departmentInsert(MemberVO vo) throws Exception{
		
		//소속 ID 채번
		String departmentId = idgenService.getNextStringId();
		vo.setDepartmentId(departmentId);

    	memberDAO.departmentInsert(vo);
    }

	// 관리자 > 회원 관리 > 소속 수정
    @Override
    public void departmentUpdate(MemberVO vo) throws Exception{
    	memberDAO.departmentUpdate(vo);
    }

	// 관리자 > 회원 관리 > 소속 삭제
    @Override
    public void departmentDelete(MemberVO vo) throws Exception{
    	memberDAO.departmentDelete(vo);
    }
    
    // 관리자 > 회원 관리 > 회원 form > 소속 list 조회
    @Override
    public List<?> departmentList(MemberVO vo) throws Exception{
    	List<?> list = memberDAO.departmentList(vo);
    	
    	return list;
    }
	
	// 관리자 > 회원 관리 > 소속 form > 해당 소속인 회원이 있는지 확인
	@Override
	public int selectMemberCnt(MemberVO vo) {
		return memberDAO.selectMemberCnt(vo);
	}

	// 사용자 > 로그인 체크
	@Override
	public MemberVO selectMemberloginCheck(MemberVO memberVO) throws Exception {
		String enpassword = EgovFileScrty.encryptPassword(memberVO.getMemberPw(), memberVO.getMemberId());
		memberVO.setMemberPw(enpassword);
		return memberDAO.selectMemberloginCheck(memberVO);
	}

}
