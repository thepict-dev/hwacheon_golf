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
<%@ page import="java.util.List"%>
<%@ page import="egovframework.golf.service.EventVO"%>
<%@ page import="egovframework.golf.web.GolfBundle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	GolfBundle ub = new GolfBundle(request);

	List<EventVO> eventList = ub.gameEventTimeMonList("event01");
	
	String eventId = "";
	String eventDate = "";

	if(eventList.size() > 0){
		System.out.println("여긴뭐지");
		eventId = eventList.get(0).getEventId()==null?"":eventList.get(0).getEventId();
		eventDate = eventList.get(0).getEventDate()==null?"":eventList.get(0).getEventDate();
	}

	List<EventVO> timeList = ub.userEventTimeDayList(eventId,"event01");
	pageContext.setAttribute("timeList", timeList);
	pageContext.setAttribute("eventDate", eventDate);
	
%>

	<script>

		function fn_apply(eventId, eventTimeId, reqCnt){
			if(reqCnt == 0){
				alert("신청이 마감되었습니다.");
				return false;
			}else{
				$("#eventId").val(eventId);
				$("#eventTimeId").val(eventTimeId);
				$("#reqCnt").val(reqCnt);

				$("#frm").attr("action","/parkgolf/reservation/apply");
				$("#frm").submit();
			}
		}
	</script>

	<form name="frm" id="frm" method="post" action="">
		<input type="hidden" name="eventId" id="eventId" value="">
		<input type="hidden" name="eventTimeId" id="eventTimeId" value="">
		<input type="hidden" name="eventFlag" id="eventFlag" value="event01">
		<input type="hidden" name="reqCnt" id="reqCnt" value="">
	</form>

        <div class="calendar-wrap" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
          <p class="cal-tit">화천산천어파크골프장 예약</p>
          <span class="cal-sub noto">화천산천어파크골프장은 별다른 로그인 없이 <span class="mobbr">연락처만으로 간편하게 예약하실 수 있습니다.</span></span>
          <div class="calendar reservation-cal noto">
              <p class="letter-top">Calender</p>
              <div id="exhibition"></div>
              <script>
                  document.addEventListener('DOMContentLoaded', function() {
                      var calendarEl = document.getElementById('exhibition');

                      var calendar = new FullCalendar.Calendar(calendarEl, {
                          aspectRatio: 1.5,
                          plugins: [ 'interaction', 'dayGrid'],
                          header: {
                              left: 'prevYear, prev',
                              center: 'title',
                              right: 'next, nextYear'
                          },
                          locale: 'ko',
                          navLinks: false,
                          eventLimit: true,
                          events: [
							<%
							for(int i=0; i<eventList.size(); i++){
								int applyCnt = eventList.get(i).getApplyCnt();
								int limitCnt = Integer.parseInt(eventList.get(i).getLimitCnt());
								int reqCnt = limitCnt - applyCnt;
							%>
								<%if(i!=0){%>,<%}%> 
								{
									title: '<%=reqCnt%>',
									start: '<%=eventList.get(i).getEventDate() %>',
									url: 'javascript:fn_apply(\'<%=eventList.get(i).getEventId()%>\',\'<%=eventList.get(i).getEventTimeId()%>\',\'<%=reqCnt%>\');'
								}
							<%
							}
							%>
                          ],
                      });

                      calendar.render();
                      $('.fc-day-top').each(function(){
                          if($(this).is('.fc-today')) return false;
                          $(this).addClass('fc-before-today');
                      })
                  });
              </script>
              <div class="cal-info">
                <p><em class="star">*</em> 일 최대 예약 가능 인원은 200명 입니다.</p>
                <p><em class="star">*</em> 일자별 예약 가능 인원이 표시됩니다.</p>
              </div>
          </div>
        </div>
      </div>