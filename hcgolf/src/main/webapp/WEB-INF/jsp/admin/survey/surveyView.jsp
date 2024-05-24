<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	
	<script>
		function fn_list() {
			$("#frm").attr("action", "/_admin/survey/surveyList.do");
			$("#frm").submit();
		}
		
		function fn_update_question(v_questionId){
			window.open("/_admin/survey/surveyQuestionForm.do?questionId="+v_questionId+"&surveyId=${result.surveyId}&command=update", "surveyQuestionFormUpdate", "width=1040,height=800");
		}
		
		function fn_insert_question(){
			window.open("/_admin/survey/surveyQuestionForm.do?&surveyId=${result.surveyId}&command=insert", "surveyQuestionFormInsert", "width=1040,height=800");
		}
		
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">설문 관리</a></li>
	            <li><a href="#lnk">설문 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">설문 관리</h2>
	        <div class="section-wrap">
	        	<div class="section">
		        	<p class="section-tit">질문 관리</p>
		            <div class="tbl-wrap new-tbl">
		                <table class="tbl03">
		                    <caption class="blind">질문 관리 등록 테이블</caption>
		                    <colgroup>
		                        <col style="width:20%">
		                        <col style="width:80%">
		                    </colgroup>
		                    <tbody>
		                     <tr>
		                         <th colspan="2"><h2>${result.surveyTitle}</h2></th>
		                     </tr>
		                     <tr>
		                         <th class="tl">설문 조사 기간</th>
		                         <td class="text-box info-ir">
		                             ${result.startDate} ~ ${result.endDate}
		                         </td>
		                     </tr>
		                     <tr>
		                         <th class="tl">공개 여부</th>
		                         <td class="text-box info-ir">
		                         	설문조사 ${result.openFlag eq 'Y' ? '공개' : '비공개'} · 설문 결과 ${result.openResultFlag eq 'Y' ? '공개' : '비공개'}
		                         </td>
		                     </tr>
		                     <tr>
		                         <th class="tl">설문 대상 설정</th>
		                         <td class="text-box info-ir">
		                         	설문 인원 제한 ${result.limitRspdentMax eq 0 ? '없음' : result.limitRspdentMax+'명'} · 중복참여 ${result.limitRspdentDup eq 'N' ? '허용':'비허용'}
		                         </td>
		                     </tr>
		                     <tr>
		                         <td colspan="2">
		                         	<div class="mt30 mb30" >${result.surveyDesc}</div>
			                 		<c:if test="${result.startDateDiff lt 0}">
			                         	<div class="btn-box right">
								    		<a href="#lnk" onclick="javascript:fn_insert_question();" class="tbl-btn blue">질문 추가</a>
			                         	</div>
			                        </c:if>
		                         </td>
		                     </tr>
	                    	 <c:if test="${fn:length(result.questions) == 0}">
	                    		<tr>
			                     	<td colspan="2" style="text-align:center">등록된 질문이 없습니다.</td>
			                     </tr>
	                    	 </c:if>
		                    </tbody>
		                </table>
		                <!-- 질문 테이블 시작 -->
	                 	<table class="tbl03">
	                    <caption class="blind">질문 테이블</caption>
	                    <colgroup>
	                        <col style="width:;">
	                        <col style="width:176px">
	                    </colgroup>
	                    <tbody>
			                 <tr>
	                    	<c:forEach var="question" items="${result.questions}" varStatus="questionStatus">
	                    		<!-- 질문 시작 -->
	                     		<tr>
	                     			<!-- 질문 제목 시작 -->
	                     			<c:if test="${result.startDateDiff gt 0}">
			                         	<th class="tl" colspan="2">${question.questionType eq 'TEXT' ? '주관식':'객관식'} Q${question.questionCode}. ${question.questionTitle}</th>
	                     			</c:if>
	                     			<c:if test="${result.startDateDiff lt 0}">
			                         	<th class="tl">${question.questionType eq 'TEXT' ? '주관식':'객관식'} Q${question.questionCode}. ${question.questionTitle}</th>
			                         	<th class="btn-box right">
									    	<a href="#lnk" onclick="javascript:fn_update_question(${question.questionId});" class="tbl-btn">질문 수정</a>
			                         	</th>
	                     			</c:if>
	                     			<!-- 질문 제목 끝 -->
	                         	</tr>
	                         	<tr>
	                         		<c:if test="${question.questionType eq 'TEXT'}" >
	                         			<td colspan="2" class="text-box info-ir">
					                    	<textarea class="txt bg-line" name="${questionStatus.index}" id="${questionStatus.index}" title="질문 제목 입력" style="border-color: #ccc; height: 50px;"></textarea>
					                    </td>
	                         		</c:if>
	                         		<c:if test="${question.questionType ne 'TEXT'}" >
	                 					<!-- 선택지 테이블 시작 -->
				                     	<td colspan="2">
			                         		<c:forEach var="answer" items="${question.answers}" varStatus="answerStatus">
			                         			<p class="mb20 mt20">
		                         					<c:if test="${question.questionType eq 'RADIO'}" >
		                         						<input type="radio" readonly>
		                         					</c:if>
		                         					<c:if test="${question.questionType eq 'CHECK'}" >
		                         						<input type="checkbox" readonly>
		                         					</c:if>
		                         					<span>${answer.answerTitle}</span>
			                         			</p>
			                         		</c:forEach>
					                    	<c:if test="${question.useOtherSelectFlag eq 'Y'}">
					                    		<p class="mb20 mt20">
				                    				<c:if test="${question.questionType eq 'RADIO'}" >
		                         						<input type="radio">
		                         					</c:if>
		                         					<c:if test="${question.questionType eq 'CHECK'}" >
		                         						<input type="checkbox">
		                         					</c:if>
		                         					<span><input tyep="text"></input></span>
			                         			</p>
					                    	</c:if>
			                         	</td>
	                 					<!-- 선택지 테이블 끝 -->
	                         		</c:if>
	                         	</tr>
	                         	<!-- 질문 끝 -->
                   			</c:forEach>
	                    </tbody>
                     	</table>
	                 	<!-- 질문 테이블 끝 -->
		            </div>
		        </div>
	    	</div>
	        <div class="btn-box">
	            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
	        </div>
            <form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex}">
				<input type="hidden" name="searchWrd"  value="${searchVO.searchWrd}">
				<input type="hidden" name="surveyId" id="surveyId" value="">
				<input type="hidden" name="questionId" id="questionId" value="">
				<input type="hidden" name="command" id="command" value="">
            </form>
	    </div>
	</div>