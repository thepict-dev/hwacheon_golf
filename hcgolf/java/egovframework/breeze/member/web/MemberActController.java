package egovframework.breeze.member.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.breeze.member.service.MemberService;
import egovframework.breeze.member.service.MemberVO;

@Controller
@RequestMapping("/_member")
public class MemberActController {

	/** memberService */
	@Resource(name = "memberService")
	private MemberService memberService;

	/**
	 * 사용자 > 로그인 > 로그인 action
	 * @param memberVO
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.do")
	public String login(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
		if(user == null) {
			MemberVO loginCheck = memberService.selectMemberloginCheck(memberVO);

			if(loginCheck == null) {
				model.addAttribute("message", "로그인에 실패하였습니다. 로그인 정보를 확인해주시기 바랍니다.");
				model.addAttribute("retType", ":back");
				
				return "/common/message";
			}else {
				request.getSession().setAttribute("loginVO", loginCheck);
			}

		    String returnUrl = (memberVO.getReturnUrl()==null || memberVO.getReturnUrl().equals("")) ? "/" : memberVO.getReturnUrl();

			model.addAttribute("retType", ":null_submit");
			model.addAttribute("retUrl", returnUrl);

			return "/common/message";
		}else {
			model.addAttribute("message", "이미 로그인 하였습니다.");
			model.addAttribute("retType", ":back");

			return "/common/message";
		}
	}

	/**
	 * 사용자 > 로그아웃
	 * @param memberVO
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout.do")
	public String logout(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");

		if(user == null) {
			model.addAttribute("message", "로그인이 필요한 서비스입니다.");
			model.addAttribute("retType", ":back");

			return "/common/message";
		}else {
			request.getSession().removeAttribute("loginVO");
		}

		model.addAttribute("retType", ":null_submit");
		model.addAttribute("retUrl", "/");

		return "/common/message";
	}

	/**
	 * 카카오 로그인 action
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/kakaoLoginAction.do")
	public String kakaoLoginAction(HttpServletRequest request, ModelMap model) throws Exception {

		MemberVO user = (MemberVO) request.getSession().getAttribute("loginVO");
    	System.out.println("::::::::::::::::::카카오 로그인 시작");

		if (user == null) {
			String kakaoId = request.getParameter("kakaoId")==null?"":request.getParameter("kakaoId");
			/*String kakaoName = request.getParameter("kakaoName")==null?"":request.getParameter("kakaoName");*/
			
			MemberVO login = new MemberVO();
			
			if(!kakaoId.equals("")){
				login.setMemberId("k"+kakaoId);
			}else{
				model.addAttribute("retType", ":submit");
				model.addAttribute("retUrl", "/login");
				model.addAttribute("message", "카카오 로그인시 오류가 발생하였습니다. 다시 시도해주시기 바랍니다.");
				
				return "/common/message";
			}
			
			/*if(!kakaoName.equals("")){
				login.setMemberName(kakaoName);
			}else{
				model.addAttribute("retType", ":submit");
				model.addAttribute("retUrl", "/utility/login");
				model.addAttribute("message", "카카오 로그인시 오류가 발생하였습니다. 다시 시도해주시기 바랍니다.");
				
				return "/common/message";
			}*/
			
			request.getSession().setAttribute("loginVO", login);
	    	System.out.println("::::::::::::::::::카카오 로그인 성공");
			
			String returnPage = (String)request.getSession().getAttribute("returnUrl");
			
			String returnUrl = (returnPage==null || returnPage.equals("")) ? "/" : returnPage;
			
			String loginParam1 = (String)request.getSession().getAttribute("loginParam1");
			String loginParam2 = (String)request.getSession().getAttribute("loginParam2");
			String loginParam3 = (String)request.getSession().getAttribute("loginParam3");
			
			String loginParamName1 = (String)request.getSession().getAttribute("loginParamName1");
			String loginParamName2 = (String)request.getSession().getAttribute("loginParamName2");
			String loginParamName3 = (String)request.getSession().getAttribute("loginParamName3");
			
			
			model.addAttribute("retType", ":null_submit");
			model.addAttribute("retUrl", returnUrl);
			model.addAttribute("hiddenName1", loginParamName1);
			model.addAttribute("hiddenValue1", loginParam1);
			model.addAttribute("hiddenName2", loginParamName2);
			model.addAttribute("hiddenValue2", loginParam2);
			model.addAttribute("hiddenName3", loginParamName3);
			model.addAttribute("hiddenValue3", loginParam3);
	    	System.out.println("::::::::::::::::::카카오 로그인 종료");
			request.getSession().removeAttribute("returnUrl");
			
			return "/common/message";
		} else {
			return "redirect:/";
		}
	}
	
	/**
	 * 네이버 로그인 action
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/naverLoginAction.do")
	public String naverLoginAction(HttpServletRequest request, ModelMap model) throws Exception {

		MemberVO user = (MemberVO) request.getSession().getAttribute("MemberVO");
    	System.out.println("::::::::::::::::::네이버 로그인 시작");

		if (user == null) {
			//String clientId = "SpTYYF4tvPMxqsSVslso";//애플리케이션 클라이언트 아이디값"; - 개발서버
		    //String clientSecret ="lOdVBJ61Yv";//애플리케이션 클라이언트 시크릿값"; - 개발서버
		    //String redirectURI = URLEncoder.encode("http://localhost:8080", "UTF-8");// - 개발서버
		    
		    String clientId = "1_KsnaDm7xhxlqYb76PV";//애플리케이션 클라이언트 아이디값"; - 운영서버
		    String clientSecret ="sVJIgFjLY0";//애플리케이션 클라이언트 시크릿값"; - 운영서버
		    String redirectURI = URLEncoder.encode("https://hspg.ihc.go.kr", "UTF-8");// - 운영서버
		    
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		
		    String refresh_token = "";
		    StringBuffer res = new StringBuffer(); // token
		    StringBuffer res2 = new StringBuffer(); // 프로필 정보
		    
		    String msg = ""; // token
		    JSONObject jo = null; // token
		    
		    String msg2 = "";  // 프로필 정보
		    JSONObject jo2 = null;  // 프로필 정보
		    JSONObject jo3 = null;  // 프로필 정보
		    
		    BufferedReader br = null;
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;

		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      
		      if(responseCode==200) {
		    	  msg = res.toString();
		    	  jo = new JSONObject(msg);
		    	  System.out.println("1::::::::::::::::::네이버 로그인 성공"+msg);
		      }
		    } catch (JSONException e) {
		    	System.out.println("1############# : 네이버 로그인 오류입니다.");
		    	e.printStackTrace();
		    } catch (ProtocolException e) {
		    	System.out.println("2############# : 네이버 로그인 오류입니다.");
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	System.out.println("3############# : 네이버 로그인 오류입니다.");
		    	e.printStackTrace();
		    } finally {
		    	if(br != null){
		    		br.close();
		    	}
		    }
			
		    String token = "";
		    if(jo != null && jo.has("access_token")){
		    	token = jo.getString("access_token");// 네이버 로그인 접근 토큰;
		    }else{
		    	model.addAttribute("retType", ":submit");
				model.addAttribute("retUrl", "/");
				model.addAttribute("message", "로그인시 오류가 발생하였습니다.");
				return "/common/message";
		    }
		    
			String header = "Bearer " + token; // Bearer 다음에 공백 추가
			br = null;
		    try {
		        String apiURL2 = "https://openapi.naver.com/v1/nid/me";
		        URL url = new URL(apiURL2);
		        HttpURLConnection con = (HttpURLConnection)url.openConnection();
		        con.setRequestMethod("GET");
		        con.setRequestProperty("Authorization", header);
		        int responseCode = con.getResponseCode();
		        
		        if(responseCode==200) { // 정상 호출
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        } else {  // 에러 발생
		            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		        }
		        String inputLine;
		        while ((inputLine = br.readLine()) != null) {
		        	res2.append(inputLine);
		        }
		        br.close();
		        if(responseCode==200) { // 정상 호출
		        	msg2 = res2.toString();
		      	  	jo2 = new JSONObject(msg2);
		      	  	jo3 = new JSONObject(jo2.get("response").toString());
		      	  	
		      	  	MemberVO login = new MemberVO();
		      	  	login.setMemberId("n"+jo3.get("id").toString());

		      	  	// 로그인 정보를 세션에 저장
		    		request.getSession().setAttribute("loginVO", login);
			    	System.out.println("2::::::::::::::::::네이버 로그인 성공");
		        }
		        
		    } catch (JSONException e) {
		    	System.out.println("4############# : 네이버 로그인 오류입니다.");
		    	e.printStackTrace();
		    } catch (ProtocolException e) {
		    	System.out.println("5############# : 네이버 로그인 오류입니다.");
		    	e.printStackTrace();
		    } catch (IOException e) {
		    	System.out.println("6############# : 네이버 로그인 오류입니다.");
		    	e.printStackTrace();
		    } finally {
		    	if(br != null){
		    		br.close();
		    	}
		    }
			
			String returnPage = (String)request.getSession().getAttribute("returnUrl");
			
			String returnUrl = (returnPage==null || returnPage.equals("")) ? "/" : returnPage;
			
			String loginParam1 = (String)request.getSession().getAttribute("loginParam1");
			String loginParam2 = (String)request.getSession().getAttribute("loginParam2");
			String loginParam3 = (String)request.getSession().getAttribute("loginParam3");
			
			String loginParamName1 = (String)request.getSession().getAttribute("loginParamName1");
			String loginParamName2 = (String)request.getSession().getAttribute("loginParamName2");
			String loginParamName3 = (String)request.getSession().getAttribute("loginParamName3");
			
			
			model.addAttribute("retType", ":null_submit");
			model.addAttribute("retUrl", returnUrl);
			model.addAttribute("hiddenName1", loginParamName1);
			model.addAttribute("hiddenValue1", loginParam1);
			model.addAttribute("hiddenName2", loginParamName2);
			model.addAttribute("hiddenValue2", loginParam2);
			model.addAttribute("hiddenName3", loginParamName3);
			model.addAttribute("hiddenValue3", loginParam3);
	    	System.out.println("::::::::::::::::::네이버 로그인 종료");
			request.getSession().removeAttribute("returnUrl");
			
			return "/common/message";
		} else {
			return "redirect:/";
		}
	}

}
