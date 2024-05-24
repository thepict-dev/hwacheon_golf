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

		function fn_applyDel(v_applyId) {
			if(confirm("삭제하시겠습니까?")){
				$("#applyId").val(v_applyId);
				$("#frm").attr("action","/_admin/event/applyDelete.do");
				$("#frm").submit();
			}
		}

		function fn_excelDown(){
			$("#frm").attr("action","/_admin/event/eventExcelDown.do");
			$("#frm").submit();
		}

		function fn_egov_downFile(atchFileId, fileSn){
			window.open("<c:url value='/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}

		function fn_applyForm(v_applyId) {
			$("#command").val("update");
			$("#applyId").val(v_applyId);
			$("#frm").attr("action","/_admin/event/applyForm.do");
			$("#frm").submit();
		}
		
		function fn_statusUpdate(v_applyId, v_status){
			if(v_status == "Y"){
				if(confirm("승인 하시겠습니까?")){
					$("#applyId").val(v_applyId);
					$("#statusFlag").val(v_status);
					$("#frm").attr("action","/_admin/event/statusUpdate.do");
					$("#frm").submit();
				}
			}else{
				if(confirm("취소 하시겠습니까?")){
					$("#applyId").val(v_applyId);
					$("#statusFlag").val(v_status);
					$("#frm").attr("action","/_admin/event/statusUpdate.do");
					$("#frm").submit();
				}
			}
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
	    <div class="container">
	        <h2 class="con-tit">예약관리</h2>
			<form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="eventId" value="${eventVO.eventId }" >
				<input type="hidden" name="eventDate" value="${searchVO.eventDate }" >
				<input type="hidden" name="eventFlag" value="${searchVO.eventFlag }" >
				<input type="hidden" name="applyId" id="applyId" value="" >
				<input type="hidden" name="command" id="command" value="" >
				<input type="hidden" name="statusFlag" id="statusFlag" value="" >
			</form>
			<p class="tbl-tit in_block">
				사용일 : ${eventVO.eventDate }
			</p>
	        <div class="download">
	            <a href="#lnk" onclick="fn_excelDown();">엑셀내려받기</a>
	        </div>
			<div class="tbl-wrap cl-both pt20">
				<table class="tbl01">
					<caption class="blind">신청자 목록 테이블</caption>
					<colgroup>
	                    <col style="width:6%">
	                    <col style="width:8%">
	                    <col style="width:6%">
	                    <col style="width:;">
	                    <col style="width:14%">
	                    <col style="width:14%">
	                    <col style="width:10%">
	                    <col style="width:9%">
	                    <col style="width:9%">
	                    <col style="width:10%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>티업시간</th>
							<th>인원</th>
							<th>신청자</th>
							<th>연락처</th>
							<th>숙소</th>
							<th>상태</th>
							<th>승인</th>
							<th>취소</th>
							<th>예약신청일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
		                    <tr>
		                        <td>${result.rownum }</td>
		                        <td><c:if test="${result.ampm eq 'AM'}">오전</c:if><c:if test="${result.ampm ne 'AM'}">오후</c:if></td>
		                        <td>${result.adultNum }</td>
		                        <td>${result.applicant }</td>
		                        <td>${result.phone }</td>
		                        <td>${result.address }</td>
		                        <td>
		                        	<c:choose>
		                        		<c:when test="${result.statusFlag eq 'Y'}">예약확정</c:when>
		                        		<c:when test="${result.statusFlag eq 'N'}">취소</c:when>
		                        		<c:otherwise>승인대기</c:otherwise>
	                        		</c:choose>
		                        </td>
		                        <td><c:if test="${result.statusFlag eq 'D'}"><a href="javascript:void(0)" onclick="javascript:fn_statusUpdate('${result.applyId }','Y');" class="control">승인</a></c:if></td>
		                        <td><c:if test="${result.statusFlag eq 'D'}"><a href="javascript:void(0)" onclick="javascript:fn_statusUpdate('${result.applyId }','N');" class="delete">취소</a></c:if></td>
		                        <td>${result.regDate }</td>
		                    </tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="5">신청자가 없습니다.</td>
		                    </tr>
			 			</c:if>
					</tbody>
				</table>
			</div>
		    <div class="btn-box">
	        	<a href="#lnk" onclick="javascript:fn_eventForm();" class="tbl-btn blue">날짜 관리</a>
				<a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		    </div>
		</div>
	</div>