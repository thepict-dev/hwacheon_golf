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
<header class="header">
		<div class="header-util">
			<ul>
				<li><a href="https://www.ihc.go.kr/www/index.do" title="새창열림" target="_blank">화천군청</a></li>
				<%if(sb.getMemberId() != null){ %>
					<li class="login"><a href="/_member/logout.do">로그아웃</a></li>
				<%}else{%>
					<li class="login"><a href="/login">로그인</a></li>
				<%}%>
			</ul>
		</div>
		<div class="header-pos">
			<div class="weather">
				<p class="date" id="toDay"></p>
				<p class="ico" id="weatherType"></p>
			</div>
			<div class="inner-header">
				<h1 class="logo"><a href="/main"><img src="/_user/img/logo.svg" alt=""></a></h1>
				<div class="gnb-wrap">
					<ul>
						<li>
							<a href="#lnk">파크골프장</a>
							<div class="dep2">
								<ul>
									<li><a href="/parkgolf/greeting">인사말</a></li>
									<li><a href="/parkgolf/info">소개</a></li>
									<li><a href="/parkgolf/map">오시는길</a></li>
									<li><a href="/parkgolf/rules">이용안내</a></li>
									<li><a href="/parkgolf/hole">코스소개</a></li>
									<li><a href="/parkgolf/facilities">부대시설</a></li>
									<li><a href="/parkgolf/reservation">예약하기</a></li>
									<li><a href="/parkgolf/confirm">예약확인</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#lnk">정보마당</a>
							<div class="dep2">
								<ul>
									<li><a href="/bbs/notice">공지사항</a></li>
									<li><a href="/bbs/gallery">갤러리</a></li>
									<li><a href="/bbs/inquiry">문의하기</a></li>
								</ul>
							</div>
						</li>
						<li>
							<a href="#lnk">화천관광</a>
							<div class="dep2">
								<ul>
									<li><a href="/tour/masusalmon">산천어축제</a></li>
									<li><a href="/tour/jjokbae">쪽배축제</a></li>
									<li><a href="/tour/tomato">토마토축제</a></li>
									<li><a href="/tour/info">화천 관광정보</a></li>
									<li><a href="/tour/restaurant">음식점</a></li>
									<li><a href="/tour/stay">숙박업</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<div class="gnb-btn">
					<button type="button">메뉴 열기</button>
				</div>
			</div>
		</div>
	</header>