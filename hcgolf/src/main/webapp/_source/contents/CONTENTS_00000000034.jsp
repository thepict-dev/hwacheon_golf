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
<div class="info-wrap">
          <div class="info-top" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <p class="col-green">물안개 피어오르는 신비의 북한강,<br>그 북한강의 넉넉한 품에서<br>파크골프를 즐길 수 있습니다.</p>
          </div>
          <div class="video-area">
            <video class="video" src="/_user/video/video01.mp4" autoplay="" controls="" loop="" muted=""></video>
          </div>
          <div class="info-tit-bg" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <p><em>1,500m</em>전국 최장 길이의 코스 길이 조성</p>
          </div>
          <ul class="info-bottom-detail" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <li>
              <i class="ico info01"></i>
              <p>이용객의 휴식공간</p>
              <span>북한강 조망과 아름다운 "사랑나무"가 함께하는 <span class="mobbr">파크골프 이용객의 휴식공간</span></span>
            </li>
            <li>
              <i class="ico info02"></i>
              <p>즐거운 라운딩</p>
              <span>아름다운 수목과 조화를 이루는 파크골프장은<span class="mobbr">이용객의 다양하고 즐거운 라운딩 제공</span></span>
            </li>
            <li>
              <i class="ico info03"></i>
              <p>공식규격</p>
              <span>공식규격의 파크골프장으로 품격있는<span class="mobbr">대회개최와 자격시험 장소제공</span></span>
            </li>
          </ul>
        </div>
      </div>