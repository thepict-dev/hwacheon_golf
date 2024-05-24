<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%												
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>



        <div class="calendar-wrap">
            <div class="box-wrap login bg-none" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
                <em class="ico-done login"></em>
                <div class="btn-wrap noto">
				<%
					//개발서버
					String clientId = "SpTYYF4tvPMxqsSVslso";
					String redirectURI = URLEncoder.encode("http://localhost:8080/_member/naverLoginAction.do", "UTF-8");
					//운영서버
					//String clientId = "1_KsnaDm7xhxlqYb76PV";
					//String redirectURI = URLEncoder.encode("https://hspg.ihc.go.kr/_member/naverLoginAction.do", "UTF-8");
					
					SecureRandom random = new SecureRandom();
					String state = new BigInteger(130, random).toString();
					String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
					apiURL += "&client_id=" + clientId;
					apiURL += "&redirect_uri=" + redirectURI;
					apiURL += "&state=" + state;
					session.setAttribute("state", state);
				%>
                  <a href="<%=apiURL %>" role="button" class="class-btn naver"><span class="ico-naver"></span>네이버 아이디로 로그인</a>
                  <div class="kakao-wrap">
                      <a href="#lnk" role="button" class="class-btn kakao"><span class="ico-kakao"></span>카카오 로그인</a>
                      <a href="#lnk" role="button" class="hidden" id="kakao-login-btn"><img id="kakao-login-btn" src="https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png" onmouseover="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium_press.png'" onmouseout="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png'" style="cursor: pointer"></a>
                  </div>
                </div>
                <div class="border-box noto">
                  본인명의의 SNS 계정으로 로그인을 신청해 주십시오. 타인의 개인정보를 도용하는 경우 서비스 이용제한 및 법적인 제재를 받을 수 있습니다.
                </div>
            </div>
          </div>
      </div>

		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		<script type="text/javascript">
			// 개발
			Kakao.init('13ffd9b7d5d69901fb96703577c15ad3'); //여기서 아까 발급받은 키 중 javascript키를 사용해준다.
			// 운영
			//Kakao.init('6f8098b0177751d61041ee041a87403e');
			
			Kakao.Auth.createLoginButton({ 
				container: '#kakao-login-btn', 
				success: function(authObj) { 
					Kakao.API.request({
					   url: '/v2/user/me',
					   success: function(res) {
							$("#kakaoId").val(res.id);
							$("#kakaoFrm").submit();
						}
					})
				}, 
				fail: function(err) { 
					alert(JSON.stringify(err)); 
				} 
			});
		</script>
		<form action="/_member/kakaoLoginAction.do" name="kakaoFrm" id="kakaoFrm" method="post">
			<input type="hidden" name="kakaoId" id="kakaoId" value="">                           	
		</form>