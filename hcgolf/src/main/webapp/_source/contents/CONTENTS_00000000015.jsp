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
<%
   request.getSession().removeAttribute("returnUrl");
%>
<div class="main-container">
        <div class="main-visual">
          <div class="swiper-container main-slide">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <img src="/_user/img/main-visual01-w.jpg" alt=""><p class="mian-txt">자연 속에서<br> 진정한 휴식과 즐거움이 있는<br>화천 산천어 파크골프장으로<br>여러분을 초대합니다.</p>
              </div>
              <div class="swiper-slide">
                <img src="/_user/img/main-visual02-w.png" alt=""><p class="mian-txt">자연 속에서<br> 진정한 휴식과 즐거움이 있는<br>화천 산천어 파크골프장으로<br>여러분을 초대합니다.</p>
              </div>
              <div class="swiper-slide">
                <img src="/_user/img/main-visual03-w.png" alt=""><p class="mian-txt">자연 속에서<br> 진정한 휴식과 즐거움이 있는<br>화천 산천어 파크골프장으로<br>여러분을 초대합니다.</p>
              </div>
            </div>
            <div class="swiper-pagination"></div>
          </div>
          <ul class="quick-menu">
            <li>
              <a href="/parkgolf/reservation" class="inner-quick">
                <p>예약<br>하기</p>
              </a>
            </li>
            <li>
              <a href="/parkgolf/confirm" class="inner-quick">
                <p>예약<br>확인</p>
              </a>
            </li>
            <li>
              <a href="/tour" class="inner-quick">
                <p>화천<br>관광</p>
              </a>
            </li>
          </ul>
        </div>
        <div class="inner-info">
          <div class="main-info top">
            <div class="top-txt">
              <h2 class="info-tit"data-aos="fade-up" data-delay="2000" data-aos-duration="1500">가슴 뛰는 추억과<br>눈부신 자연이 있습니다.</h2>
              <p data-aos="fade-up" data-delay="2000" data-aos-duration="1500">물안개 피어오르는 신비의 북한강!<br>그 북한강의 넉넉한 품에서 파크골프를 즐겨보세요!</p>
              <a href="/parkgolf/info" class="lnk" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">파크골프장 소개 보러가기</a>
            </div>
            <ul class="img-wrap">
              <li data-aos="fade-up" data-delay="2000" data-aos-duration="1500"><img src="/_user/img/img-main-info01.jpg" alt=""></li>
              <li data-aos="fade-up" data-delay="2000" data-aos-duration="1500"><img src="/_user/img/img-main-info02.jpg" alt=""></li>
            </ul>
          </div>
          <div class="webshow pd" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <img src="../img/img-mid01.jpg" alt="">
          </div>
          <div class="main-info bottom">
            <div class="top-txt">
              <h2 class="info-tit" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">자연 속 또 하나의<br>아름다운 자연이 있습니다.</h2>
              <p data-aos="fade-up" data-delay="2000" data-aos-duration="1500">자연 속 또 하나의 아름다운 자연이 있습니다.<br>북한강 최상류 1급수가 흐르는 수변에서 즐기는 파크골프! <br>힐링, 그 이상의 가치를 느끼실 수 있습니다!</p>
              <a data-aos="fade-up" data-delay="2000" data-aos-duration="1500" href="/parkgolf/hole" class="lnk">홀 소개 보러가기</a>
            </div>
            <ul class="img-wrap">
              <li data-aos="fade-up" data-delay="2000" data-aos-duration="1500"><img src="/_user/img/img-main-info03.jpg" alt=""></li>
              <li data-aos="fade-up" data-delay="2000" data-aos-duration="1500"><img src="/_user/img/img-main-info04.jpg" alt=""></li>
            </ul>
          </div>
        </div>
        <div class="map-wrap">
          <div class="tit-area">
            <video class="video-tit" src="/_user/video/home.mp4" autoplay="" loop="" muted=""></video>
            <p data-aos="fade-up" data-delay="2000" data-aos-duration="1500">화천, 체험과 휴식을 즐기는 그 이상의 가치</p>
            <h2 data-aos="fade-up" data-delay="2000" data-aos-duration="1500">MAP</h2>
          </div>
          <div class="map-video" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <img src="/_user/img/map.jpg" alt="">
          </div>
        </div>
      </div>