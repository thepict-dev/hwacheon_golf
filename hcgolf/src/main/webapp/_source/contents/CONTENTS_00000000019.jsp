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
<div class="greeting-wrap">
          <div class="greeting-top" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <p class="img-wrap"><img src="/_user/img/img-greeting.jpg" alt=""></p>
            <span class="noto sign">화천군수</span>
          </div>
          <div class="greeting-bottom" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <p class="col-green">
              <span class="mobbr">자연 속에서 진정한</span> 휴식과 즐거움이 있는<br>화천 산천어 파크골프장으로 <span class="mobbr">여러분을 초대합니다.</span>
            </p>
            <span>우리 화천군의 맑고 깨끗한 하늘, 신선한 공기를 온몸으로 느낄 수 있는 아름다운 북한강변에 자연주의 명문 파크골프장으로 자연과 동화된 편안한 고품격 여가를 즐길 수 있습니다.<br><br>최고의 자연환경과 최상의 코스로 관리되는 화천 산천어 파크골프장과 국내 최초의 산천어 선상휴게실에서 여유롭고 쾌적한 라운딩으로 여러분의 즐겁고 건강한 삶과 행복한 추억을 만들어 보시기 바랍니다.<br>감사합니다.</span>
          </div>
        </div>
      </div>