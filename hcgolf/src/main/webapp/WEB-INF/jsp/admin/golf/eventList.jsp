<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.List"%>
<%@ page import="egovframework.golf.service.EventVO"%>
<%
	// 일정 조회
	List<EventVO> eventList = (List<EventVO>) request.getAttribute("resultList");
%>
		<script>
			function fn_insert() {
				$("#command").val("insert");
				$("#frm").attr("action","/_admin/event/eventForm.do");
				$("#frm").submit();
			}
			function fn_view(v_eventId) {
				$("#eventId").val(v_eventId);
				$("#frm").attr("action","/_admin/event/applyView.do");
				$("#frm").submit();
			}
			function fn_eduView(v_eventDate) {
				$("#eventDate").val(v_eventDate);
				$("#frm").attr("action","/_admin/event/eventView.do");
				$("#frm").submit();
			}
		</script>
		
		
		<div class="container-wrap">
		    <div class="breadcrumbs">
		        <ul>
		            <li class="webshow"><a href="/_admin/main.do"><img src="/_admin/img/home.png"></a></li>
		            <li><a href="/_admin/event/scheduleList.do">예약/일정 관리</a></li>
		            <li><a>예약관리</a></li>
		        </ul>
		    </div>
		    <div class="container">
		        <h2 class="con-tit">예약관리</h2>
				<div class="link-btn">
		        	<a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
		        </div>
                <form name="frm" id="frm" method="post" action="">
					<input type="hidden" name="eventId" id="eventId" value="">
					<input type="hidden" name="command" id="command" value="">
					<input type="hidden" name="eventFlag" id="eventFlag" value="${searchVO.eventFlag }">
					<input type="hidden" name="eventDate" id="eventDate" value="">
					<input type="hidden" name="ampm" id="ampm" value="am" >
                </form>
	            <div class="fullcalendar playground">
	                <div id="list"></div>
	                <script>
	                    document.addEventListener('DOMContentLoaded', function() {
	                        var calendarEl = document.getElementById('list');
	
	                        var calendar = new FullCalendar.Calendar(calendarEl, {
	                            plugins: [ 'interaction', 'dayGrid', 'timeGrid'],
	                            header: {
	                                left: 'prev',
	                                center: 'title',
	                                right: 'next'
	                            },
	                            locale: 'ko',
	                            navLinks: false, // can click day/week names to navigate views
	                            eventLimit: true, // true일때, 많은 이벤트 숨겨서 보여줌
	                            events: [
	                    			<%
	                    			for(int i=0; i<eventList.size(); i++){
		                    		%>
										<%if(i!=0){%>,<%}%>
										{
											title: '신청 <%=eventList.get(i).getApplyCnt() %>/<%=eventList.get(i).getAmApply()%>',
											start: '<%=eventList.get(i).getEventDate() %>',
											url: 'javascript:fn_view(\'<%=eventList.get(i).getEventId() %>\');'
										}
									<%
									}
									%>
	                            ]
	                        });
	
	                        calendar.render();
	                    });
					</script>
				</div>
            </div>
        </div>