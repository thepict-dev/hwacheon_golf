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
<div class="calendar-wrap">
          <div class="box-wrap" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
              <em class="ico-done"></em>
              <p class="box-tit">예약이 신청되었습니다.</p>
              <p class="info noto">예약이 완료되면 예약 완료 문자가 발송됩니다.<br>문자로 예약정보를 확인해주세요.</p>
              <div class="board-btn-wrap noto">
                  <a href="/parkgolf/reservation" class="board-btn cancel"><span>돌아가기</span></a>
                  <a href="/parkgolf/confirm" class="board-btn bg-blue"><span>예약확인</span></a>
              </div>
          </div>
        </div>
      </div>