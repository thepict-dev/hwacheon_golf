package egovframework.breeze.admin.web;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.admin.service.AdminService;
import egovframework.breeze.admin.service.AdminVO;
import egovframework.breeze.common.RSA;
import egovframework.breeze.secure.service.AccessService;
import egovframework.breeze.secure.service.DefaultService;
import egovframework.breeze.secure.service.DefaultVO;
import egovframework.com.cmm.EgovMessageSource;

@Controller
@RequestMapping("/_admin")
public class AdminController {

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** adminService */
	@Resource(name = "adminService")
	private AdminService adminService;
	
	/** accessService */
	@Resource(name = "accessService")
	private AccessService accessService;

	@Resource(name="defaultService")
	private DefaultService defaultService;
	

	
	/**
	 * 관리자 > 로그인페이지
	 * @return
	 */
	@RequestMapping(value = "/login.do")
	public String login(HttpServletRequest request, ModelMap model) throws Exception {
		String ip = request.getRemoteAddr();
		System.out.println("클라이언트IP===========" + ip);

		// ::::::::::::::::::::::::::: breeze 기본설정 조회 시작 :::::::::::::::::::::::::::
		DefaultVO defaultVO = new DefaultVO();
		defaultVO.setDefaultId("breeze");
		defaultVO = defaultService.selectDefaultSetting(defaultVO);
		String ipLimitFlag = "N";
		if(defaultVO != null) {
			ipLimitFlag = defaultVO.getIpLimitFlag();
			model.addAttribute("logoFilePath", defaultVO.getLogoFilePath());
		}else {
			model.addAttribute("logoFilePath", "/_admin/img/breeze_logo_login.png");
		}
		System.out.println("#####################################"+ipLimitFlag);
		// ::::::::::::::::::::::::::: breeze 기본설정 조회 종료 :::::::::::::::::::::::::::
		
		if(ipLimitFlag.equals("Y")) {
			int author = accessService.selectAuthorAccess(ip);
			if(author > 0) {
			    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
				if (user == null) {
					
					return "/admin/login";
				}else {
					return "redirect:/_admin/main.do";
				}
			}else {
				return "/common/error";
			}
		}else {
		    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
			if (user == null) {
				
				return "/admin/login";
			}else {
				return "redirect:/_admin/main.do";
			}
		}
	}
	
	/**
	 * 관리자 > 로그인 action
	 * @param adminVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminLogin.do")
	public String adminLogin(@ModelAttribute("adminVO") AdminVO adminVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		HttpSession session = request.getSession();
		//PrivateKey privateKey = (PrivateKey) session.getAttribute(RSA.RSA_WEB_KEY);
		
		// 로그인 틀린횟수 조회
        String failCnt = session.getAttribute("failCnt") == null ? "0" : (String) session.getAttribute("failCnt");
        int i_failCnt = Integer.parseInt(failCnt);
        
        // 현재시간 구하기
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String sysDate = sdf.format(now);
        
		// 로그인 최종시간 조회
        String lastDate = session.getAttribute("lastDate") == null ? sysDate : (String) session.getAttribute("lastDate");
        long l_sysDate = Long.parseLong(sysDate);
        long l_lastDate = Long.parseLong(lastDate);
        long diffTime = l_sysDate - l_lastDate;
		
		String ip = request.getRemoteAddr();
		
		if(i_failCnt > 4 && diffTime < 500){
			model.addAttribute("message", "로그인을 5회 실패하여 5분 동안 로그인이 차단됩니다." + diffTime);
			return "forward:/_admin/login.do";
        }
		
		// 복호화
        //System.out.println("1::::::::::::::::::::::::::::::"+adminVO.getAdminId());
        //System.out.println("1::::::::::::::::::::::::::::::"+adminVO.getAdminPw());
        //adminVO.setAdminId(RSA.decryptRsa(privateKey, adminVO.getAdminId()));
        //adminVO.setAdminPw(RSA.decryptRsa(privateKey, adminVO.getAdminPw()));
        //System.out.println("2::::::::::::::::::::::::::::::"+adminVO.getAdminId());
        //System.out.println("2::::::::::::::::::::::::::::::"+adminVO.getAdminPw());
		
		// 아이디 존재 여부 확인
        // 통합관리자 먼저 조회 후, null이면 업무사용자 조회
		AdminVO existVO = adminService.selectAdminView(adminVO);
		
		// 통합관리자 또는 업무사용자 아이디가 존재 한다면
		if(existVO != null) {
			// 삭제 상태일 때 처리
			if(existVO.getUseFlag().equals("N")) {
				model.addAttribute("message", "탈퇴한 아이디입니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}
			// 승인 상태일 때 처리
			else {
				// 1. 일반 로그인 처리
				AdminVO resultVO = adminService.adminLogin(adminVO);
		
				if (resultVO != null && resultVO.getAdminId() != null && !resultVO.getAdminId().equals("")) {
					
					// ::::::::::::::::::::::::::: Logo path 조회 시작 :::::::::::::::::::::::::::
					DefaultVO defaultVO = new DefaultVO();
					defaultVO.setDefaultId("breeze");
					defaultVO = defaultService.selectDefaultSetting(defaultVO);
					if(defaultVO != null) {
						resultVO.setLogoFilePath(defaultVO.getLogoFilePath());
					}else {
						resultVO.setLogoFilePath("/_admin/img/breeze_logo.png");
					}
					// ::::::::::::::::::::::::::: Logo path 조회 종료 :::::::::::::::::::::::::::
					
					// 로그인 시도 기록
					resultVO.setTryIp(ip);
					resultVO.setLoginYN("Y");
					adminService.adminLoginUpdate(resultVO);
					
					// 2-1. 로그인 정보를 세션에 저장
					request.getSession().setAttribute("adminVO", resultVO);
					model.addAttribute("adminVO", resultVO);
					
					// 개인키 삭제
			        session.removeAttribute(RSA.RSA_WEB_KEY);
					
					// 틀린횟수/로그인 최종시간 초기화
			        session.removeAttribute("failCnt");
			        session.removeAttribute("lastDate");
			        
					return "redirect:/_admin/main.do";
					
				} else {
					i_failCnt++;
					failCnt = String.valueOf(i_failCnt);
			        System.out.println("failCnt::::::::::::::::::::::::::::::::"+failCnt);
					session.setAttribute("failCnt", failCnt);
					if(i_failCnt > 4){
						session.setAttribute("lastDate", sysDate);
					}
			
					model.addAttribute("message", "입력하신 정보가 일치하지 않습니다.<br>로그인 시도 5회 이상 실패 시 5분 동안 로그인이 차단됩니다.<br>(로그인 실패 : "+failCnt+"회)");
					model.addAttribute("retType", ":back");
					
					return "/common/message";
				}
			}
		} else {
			model.addAttribute("message", "입력하신 정보가 일치하지 않습니다.");
			model.addAttribute("retType", ":back");
			
			return "/common/message";
		}
		
	}
	
	/**
	 * 관리자 > logout action
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminLogout.do")
	public String adminLogout(HttpServletRequest request, ModelMap model) throws Exception {

		request.getSession().setAttribute("adminVO", null);

		return "redirect:/_admin/login.do";
	}

	/**
	 * 관리자 > 메인페이지
	 * @return
	 */
	@RequestMapping(value = "/main.do")
	public String main(HttpServletRequest request) throws Exception {

	    AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");
		if (user == null) {
			return "redirect:/_admin/login.do";
		}else {
			return "/admin/main";
		}
	}
	
}
