<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import = "egovframework.breeze.code.service.CodeVO"%>
<%@ page import = "egovframework.breeze.code.web.CodeBundle"%>
<%@ page import = "java.util.*"%>
<%
	pageContext.setAttribute("newLineChar", "\n");
%>
	<script>

		function fn_list(){
			$("#frm").attr("action", "/_admin/event/eventList.do");
			$("#frm").submit();
		}
		
		function fn_eventForm() {
			$("#command").val("update");
			$("#frm").attr("action","/_admin/event/eventForm.do");
			$("#frm").submit();
		}

		function fn_delete() {
			if(confirm("삭제하시겠습니까?")){
				$("#frm").attr("action","/_admin/event/eventDelete.do");
				$("#frm").submit();
			}
		}

		function fn_applyDel(v_applyId) {
			if(confirm("삭제하시겠습니까?")){
				$("#applyId").val(v_applyId);
				$("#frm").attr("action","/_admin/event/applyDelete.do");
				$("#frm").submit();
			}
		}

		function fn_applyView(v_flag) {
			$("#ampm").val(v_flag);
			$("#frm").attr("action","/_admin/event/applyView.do");
			$("#frm").submit();
		}
		
		function fn_applyForm(v_applyId) {
			$("#command").val("update");
			$("#applyId").val(v_applyId);
			$("#frm").attr("action","/_admin/event/applyForm.do");
			$("#frm").submit();
		}

		function fn_excelDown(){
			$("#frm").attr("action","/_admin/event/eventExcelDown.do");
			$("#frm").submit();
		}

		function fn_egov_downFile(atchFileId, fileSn){
			window.open("<c:url value='/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}

	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">예약/일정 관리</a></li>
	            <li><a href="#lnk">예약관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">예약관리</h2>
			<form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="eventId" id="eventId" value="${eventVO.eventId }" >
				<input type="hidden" name="eventFlag" id="eventFlag" value="${searchVO.eventFlag }" >
				<input type="hidden" name="eventDate" value="${eventVO.eventDate }" >
				<input type="hidden" name="applyId" id="applyId" value="" >
				<input type="hidden" name="command" id="command" value="" >
				<input type="hidden" name="ampm" id="ampm" value="" >
			</form>
			<div class="section-wrap">
				<div class="section">
					<p class="section-tit">예약관리</p>
					<div class="tbl-wrap new-tbl">
						<table class="tbl03 iptwid">
							<caption class="blind">일정안내 뷰 테이블</caption>
							<colgroup>
								<col style="width:20%">
								<col style="width:40%">
								<col style="width:40%">
							</colgroup>
							<tbody>
								<tr>
									<th>행사일</th>
									<td colspan="2">
										${eventVO.eventDate}
									</td>
								</tr>
								<%-- 
								<tr>
									<th>예약 시작 일자</th>
									<td colspan="2">
										${eventVO.openDate}
									</td>
								</tr>
								<tr>
									<th>등록일</th>
									<td colspan="2">
										${eventVO.regDate}
									</td>
								</tr>
								--%>
								<tr>
									<th>신청인원</th>
									<c:choose>
										<c:when test="${eventVO.amUseFlag eq 'Y'}">
											<td class="tc">${eventVO.applyCnt} / ${eventVO.amApply}</td>
											<td class="tc" style="border-left: 1px solid #e0e2e0;"><a href="#lnk" onclick="javascript:fn_applyView('am');" class="control">신청자 관리</a></td>
										</c:when>
										<c:otherwise>
											<td class="tc" colspan="2" style="background-color: #ececec;">미사용</td>
										</c:otherwise>
									</c:choose>
								</tr>
								<%-- 
								<tr>
									<th>오후</th>
									<c:choose>
										<c:when test="${eventVO.pmUseFlag eq 'Y'}">
											<td class="tc">${eventVO.pmApplyCnt} / ${eventVO.pmApply}</td>
											<td class="tc" style="border-left: 1px solid #e0e2e0;"><a href="#lnk" onclick="javascript:fn_applyView('pm');" class="control">신청자 관리</a></td>
										</c:when>
										<c:otherwise>
											<td class="tc" colspan="2" style="background-color: #ececec;">미사용</td>
										</c:otherwise>
									</c:choose>
								</tr>
								--%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	        <div class="btn-box">
	        	<a href="#lnk" onclick="javascript:fn_eventForm();" class="tbl-btn blue">수정</a>
	        	<a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a>
				<a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
	        </div>
	    </div>
	</div>