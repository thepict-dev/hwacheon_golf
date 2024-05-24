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
	session.setAttribute("returnUrl", "/parkgolf/reservation");

	GolfBundle ub = new GolfBundle(request);

	String eventId = request.getParameter("eventId")==null?"":request.getParameter("eventId");
	String eventTimeId = request.getParameter("eventTimeId")==null?"":request.getParameter("eventTimeId");
	String eventFlag = request.getParameter("eventFlag")==null?"":request.getParameter("eventFlag");
	String reqCnt = request.getParameter("reqCnt")==null?"20":request.getParameter("reqCnt");
	int limitCnt = Integer.parseInt(reqCnt);
	if(limitCnt > 20) limitCnt = 20;
	
	// eventVO 조회
	EventVO eventVO = ub.selectEventVO(eventId, eventTimeId);
	
	if(eventId.equals("") || eventTimeId.equals("") || !eventFlag.equals("event01") || eventVO == null){
%>
		<script>
			alert("잘못된 접근입니다.");
			history.back();
		</script>
<%
	}

	pageContext.setAttribute("eventVO", eventVO);
	if(sb.getMemberId() == null){
%>
		<script>
			alert("로그인 후 이용 가능합니다.");
			location.href = "/login";
		</script>
<%
	}
	if(eventVO.getAmUseFlag().equals("N")){
%>
		<script>
			alert("파크골프장 사정으로 예약이 마감되었습니다.\n문의 사항은 033-441-0797로 전화주세요");
			history.back();
		</script>
<%
	}
%>

<script>
	function fn_list(){
		if(confirm("작성중인 내용은 저장되지 않습니다.\n그래도 돌아가시겠습니까?")){
			location.href="/parkgolf/reservation";
		}
	}
	function fn_apply(){

		if ($("#applicant").val() == "") {
			alert("신청인(단체)을 입력해 주세요.");
			$("#applicant").focus();
			return false;
		}

		if($("#phone1").val() == null || $("#phone1").val() == ""){
			alert("휴대폰 번호를 입력해주세요.");
			$("#phone1").focus();
			return;
		}
		if($("#phone2").val() == null || $("#phone2").val() == ""){
			alert("휴대폰 번호를 입력해주세요.");
			$("#phone2").focus();
			return;
		}
		if($("#phone3").val() == null || $("#phone3").val() == ""){
			alert("휴대폰 번호를 입력해주세요.");
			$("#phone3").focus();
			return;
		}
		$("#phone").val($("#phone1").val()+"-"+$("#phone2").val()+"-"+$("#phone3").val());
			
		if ($("#address").val() == "") {
			alert("숙소를 입력해 주세요.");
			$("#address").focus();
			return false;
		}

		if(confirm("정확한 정보를 입력해주셔야 예약 확인이 가능합니다.\n예약을 진행하시겠습니까?")){
			$("#frm").attr("action","/_event/eventApply.do");
			$("#frm").submit();
		}
	}
</script>

		<div class="calendar-wrap">
          <p class="cal-tit" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">신청자 정보</p>
          <span class="cal-sub noto" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">정확한 정보를 입력해주셔야 예약 확인이 가능합니다.</span>
          <div class="form-wrap res-show" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
              <form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="eventId" id="eventId" value="<%=eventId%>">
				<input type="hidden" name="eventTimeId" id="eventTimeId" value="<%=eventTimeId%>">
				<input type="hidden" name="eventFlag" id="eventFlag" value="<%=eventFlag%>">
				<input type="hidden" name="returnUrl" id="returnUrl" value="/parkgolf/reservation">
                  <fieldset>
                      <legend class="screen-out">신청자 정보 입력 폼</legend>
                      <div class="form-group noto">
                          <ul class="grid-table bd-t bd-b">
                              <li>
                                  <p class="tit">신청인(단체)</p>
                                    <div class="input-box">
                                        <input type="text" class="int type4" id="applicant" name="applicant" maxlength="6">
                                    </div>
                              </li>
                              <li>
                                  <p class="tit">연락처</p>
                                  <div class="input-box flex">
										<input type="text" id="phone1" class="int type2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
										<em class="bar">-</em>
										<input type="text" id="phone2" class="int type2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
										<em class="bar">-</em>
										<input type="text" id="phone3" class="int type2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
										<input type="hidden" name="phone" id="phone">
                                  </div>
                              </li>
                              <li>
                                  <p class="tit">티업시간</p>
                                  <div class="input-box">
                                      <select class="int type4" id="ampm" name="ampm">
                                          <option value="AM">오전</option>
                                          <option value="PM">오후</option>
                                      </select>
                                  </div>
                              </li>
                              <li>
                                  <p class="tit">인원</p>
                                  <div class="input-box">
                                      <select class="int type4" id="adultNum" name="adultNum">
										<%
											for(int i=1; i<=limitCnt; i++){
										%>
												<option value="<%=i%>"><%=i%>명</option>
										<%
											}
										%>
                                      </select>
                                  </div>
                              </li>
                              <li>
                                  <p class="tit">숙소</p>
                                    <div class="input-box">
                                        <input type="text" class="int type4" id="address" name="address">
                                    </div>
                              </li>
                          </ul>
                      </div>
                      <div class="board-btn-wrap noto">
                          <a href="#lnk" onclick="fn_list();" class="board-btn cancel"><span>돌아가기</span></a>
                          <a href="#lnk" onclick="fn_apply();" class="board-btn bg-blue"><span>예약하기</span></a>
                      </div>
                  </fieldset>
              </form>
          </div>
        </div>
      </div>