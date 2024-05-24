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
<div class="facilities-wrap">
          <div class="dir-top">
            <p class="col-green">화천 산천어 파크골프장에서<br>다양한 부대시설을 즐겨보세요.</p>
           <span class="cal-sub noto" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">화천산천어파크골프장에는 다양한 부대시설이<br> 준비되어있어 다채로운 휴식을 즐기실 수 있습니다</span>
          </div>
          <div class="bg-box-top">
            <div class="box-inner">
              <p>산천어 선상휴게실</p>
              <span class="noto">1층 - (사)대한파크골프협회 사무실, 휴게실<em class="webshow">/</em> <em class="mobbr">2층 - 교육장, 휴게실</em><em class="webshow">/</em><em class="mobbr">3층 - 야외테라스</em>
              </span>
            </div>
          </div>
          <div class="bg-box left">
            <div class="box-inner">
              <div class="txt-box"data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
                <p >교육장</p>
                <span>화천산천어파크골프장의<br>교육장에서는 빔프로젝터를 포함한<br>교육기자재들이 완비되어 있어<br>쾌적한 환경으로 교육을 진행하실 수<br>있습니다</span>
              </div>
            </div>
          </div>
          <div class="bg-box right">
            <div class="box-inner">
              <div class="txt-box" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
                <p>휴게실</p>
                <span>(사)대한파크골프협회의 사무실/휴게실은<br>편히 앉을 수 있는 테이블, 의자등이<br>비치되어 있어 라운딩 후 시원하게<br>쉬실 수 있도록 되어있습니다.</span>
              </div>
            </div>
          </div>
          <div class="bg-box left type1">
            <div class="box-inner">
              <div class="txt-box" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
                <p>야외테라스</p>
                <span>화천강변이 한눈에 보이는<br>화천산천어파크골프장의 야외테라스에서는<br>시원한 강바람과 따뜻한 햇살,<br>자연 그대로를 만끽하실 수 있습니다</span>
              </div>
            </div>
          </div>
        </div>
      </div>