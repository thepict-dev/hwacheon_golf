package egovframework.breeze.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.breeze.member.service.MemberService;
import egovframework.breeze.member.service.MemberVO;
import egovframework.com.cop.bbs.service.EgovArticleService;

public class SessionBundle {
	
	private HttpServletRequest request;
	private WebApplicationContext context;
	private HttpSession session;
	private MemberVO loginVO;
	
	private MemberService memberService;
	
	public SessionBundle() {
	
	}
	
	/**
	 * request setting
	 * @param request
	 */
	public SessionBundle(HttpServletRequest request) {
		this.request = request;
	    this.session = request.getSession(false);
	    this.context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    this.loginVO = (MemberVO) request.getSession().getAttribute("loginVO");
	    if(this.loginVO == null) {
	    	this.loginVO = new MemberVO();
	    }
	    
	    this.memberService = ((MemberService)this.context.getBean("memberService"));
	}
	
	/**
	 * session loginVO 조회
	 * @return
	 * @throws Exception
	 */
	public MemberVO getMemberVO() throws Exception {
		return this.loginVO;
	}
	
	/**
	 * Id 기준 loginVO 조회
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public MemberVO getMemberVO(String memberId) throws Exception {
		MemberVO member = new MemberVO();
		member.setMemberId(memberId);
		return this.memberService.selectMemberView(member);
	}
	
	/**
	 * request loginVO data 조회
	 * @return
	 */
	public String getMemberId() {
		return this.loginVO.getMemberId();
	}
	
	public String getMemberName() {
		return this.loginVO.getMemberName();
	}

	public String getDepartmentName() {
		return this.loginVO.getDepartmentName();
	}
}
