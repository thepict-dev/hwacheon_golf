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
          <p class="cal-tit" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">화천산천어파크골프장 예약확인</p>
          <span class="cal-sub noto" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">성함과 연락처를 알려주시면 예약을 확인해드리겠습니다</span>
          <div class="form-wrap res-show" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
              <form action="abc.php">
                  <fieldset>
                      <legend class="screen-out">신청자 정보 입력 폼</legend>
                      <div class="form-group noto">
                          <ul class="grid-table bd-t bd-b">
                              <li>
                                  <p class="tit">신청인(단체)</p>
                                    <div class="input-box">
                                        <input type="text" class="int type4">
                                    </div>
                              </li>
                              <li>
                                  <p class="tit">연락처</p>
                                  <div class="input-box flex">
                                      <input type="text" class="int type2">
                                      <em class="bar">-</em>
                                      <input type="text" class="int type2">
                                      <em class="bar">-</em>
                                      <input type="text" class="int type2">
                                  </div>
                              </li>
                          </ul>
                      </div>
                      <div class="board-btn-wrap noto">
                          <a href="#lnk" class="board-btn bg-blue"><span>예약확인</span></a>
                      </div>
                  </fieldset>
              </form>
          </div>
        </div>
      </div>