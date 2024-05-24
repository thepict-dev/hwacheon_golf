<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>



	<script>
		function fn_list() {
			if ($("#command").val() == "rspdent"){
				$("#frm").attr("action", "/_admin/survey/rspdentList.do");				
			} else {
				$("#frm").attr("action", "/_admin/survey/surveyList.do");	
			}
			$("#frm").submit();
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
		        	<c:choose>
		        		<c:when test="${searchVO.command eq 'rspond'}">
		        			<p class="section-tit">설문 응답</p>
		        		</c:when>
		        		<c:otherwise>
		        			<p class="section-tit">설문 통계</p>
		        		</c:otherwise>
		        	</c:choose>
		            <div class="tbl-wrap new-tbl">
		                <table class="tbl03">
		                    <caption class="blind">설문 결과 테이블</caption>
		                    <colgroup>
		                        <col style="width:20%">
		                        <col style="width:80%">
		                    </colgroup>
		                    <tbody>
			                    <tr>
			                         <th class="tl">설문 제목</th>
			                         <td>${result.surveyTitle}</td>
			                    </tr>
			                    <c:choose>
			        				<c:when test="${searchVO.command eq 'rspond'}">
						        		<tr>
					                         <th class="tl">응답자 아이디</th>
					                         <td>
					                             -
					                         </td>
					                     </tr>
						        		 <tr>
					                         <th class="tl">응답자 이름</th>
					                         <td>
					                             -
					                         </td>
					                     </tr>
						        		 <tr>
					                         <th class="tl">응답일</th>
					                         <td>
					                             ${result.regDate}
					                         </td>
					                     </tr>
						        	 </c:when>
				        			 <c:otherwise>
						        		 <tr>
					                         <th class="tl">설문 조사 기간</th>
					                         <td>
					                             ${result.startDate} ~ ${result.endDate}
					                         </td>
					                     </tr>
					                     <tr>
					                         <th class="tl">참여 인원</th>
					                         <td>
					                             ${result.numRspdent}
					                         </td>
					                     </tr>
				        			</c:otherwise>
				        		 </c:choose>
			                 <tr>
	                    		<c:choose>
		                    		<c:when test="${result.questions.size() eq 0}">
		                    		<tr>
		                    			<th  class="tl">설문 답변</th>
				                     	<td style="text-align:center">등록된 질문이 없습니다.</td>
				                     </tr>
		                    		</c:when>
		                    		<c:when test="${result.numRspdent eq 0}">
		                    		<tr>
		                    			<th  class="tl">설문 답변</th>
				                     	<td style="text-align:center">등록된 응답이 없습니다.</td>
				                     </tr>
		                    		</c:when>
		                    	<c:otherwise>
			                    	<c:forEach var="question" items="${result.questions}" varStatus="questionStatus">
			                    		<c:set var="sumResult" value="${question.answers.stream().reduce(0, (acc, answer) -> acc + answer.results.size()) + question.results.size()}" />
			                    		<!-- 질문 시작 -->
			                     		<tr>
				                         	<th style="font-weight: 900;text-align:left;" colspan="2">${question.questionType eq 'TEXT' ? '주관식':'객관식'} Q${question.questionCode}. ${question.questionTitle}</th>
			                         	</tr>
			                         	<tr>
		                         			<td colspan="2" style="padding-left:50px;" class="text-box">
			                         			<c:if test="${question.questionType eq 'TEXT'}" >
			                         			<!-- 주관식 선택지 시작 -->
			                         				<c:forEach var="subjectiveResult" items="${question.results}">
			                         					<c:if test="${subjectiveResult.subjectiveCn != null }">
			                         					<textarea readonly class="txt bg-line mb10" style="height:97px;">${subjectiveResult.subjectiveCn}</textarea>
			                         					</c:if>
							                    	</c:forEach>
			                         			<!-- 주관식 선택지 끝 -->
			                         			</c:if>
			                         			<c:if test="${question.questionType ne 'TEXT'}" >
			                 					<!-- 선택지 테이블 시작 -->
					                         		<c:forEach var="answer" items="${question.answers}" varStatus="answerStatus">
					                         			<c:if test="${question.questionType eq 'RADIO'}">
					                         				<c:set var="percentage" value="${Math.round(answer.results.size() / sumResult * 100)}" />
					                         			</c:if>
					                         			<c:if test="${question.questionType ne 'RADIO'}">
					                         				<c:set var="percentage" value="${Math.round(answer.results.size() / result.numRspdent * 100)}" />
					                         			</c:if>
					                         			<div class="mb20 mt20">
									       					<c:if test="${searchVO.command ne 'rspond'}">
												        		<div class="mb10"><span>${answer.answerTitle}</span></div>
										       					<div style="margin-left:12px; display:inline-block;max-width:70%; width:50%; height:9px;background:#f3f6f9; border-radius:2px;">
										       						<div class="fill" style="width:0%;height:100%; background:linear-gradient(to right, #2674e0, #45c1dc); border-radius:2px;transition: width 0.4s;" data-percentage="${percentage}%"></div>
										       					</div>
										       					<span display="inline-block"> ( ${answer.results.size()}명 / ${percentage}% )</span>
												        	</c:if>
												        	<c:if test="${searchVO.command eq 'rspond'}">
												        		<c:if test="${answer.results.size() ne 0}">
												        			<div class="mb10" style="font-weight: 800;">
												        			<span> <input type="${question.questionType eq 'RADIO' ? 'radio' : 'checkbox'}" checked readonly> ${answer.answerTitle} <!-- ✓ --></span></div>
										       					</c:if>
										       					<c:if test="${answer.results.size() eq 0}">
										       						<div class="mb10" style="color:gray;">
												        			<input type="${question.questionType eq 'RADIO' ? 'radio' : 'checkbox'}" readonly>
												        			<span>${answer.answerTitle}</span></div>
										       					</c:if>
												        	</c:if>
									       					
					                         			</div>
					                         		</c:forEach>
							                    	<c:if test="${question.useOtherSelectFlag eq 'Y'}">
							                    		<c:if test="${searchVO.command ne 'rspond'}">
							                    			<div class="mb20 mt20">
									                    		<div class="mb10"><span>기타 답변 선택</span></div>
						                         				<c:set var="percentage" value="${Math.round(question.results.size() / sumResult * 100)}" />
										       					<div style="margin-left:12px; display:inline-block;max-width:70%; width:50%; height:9px;background:#f3f6f9; border-radius:2px;">
										       						<div class="fill" style="width:0%;height:100%; background:linear-gradient(to right, #2674e0, #45c1dc); border-radius:2px;transition: width 0.4s;" data-percentage="${percentage}%"></div>
										       					</div>
												       			<span display="inline-block"> ( ${question.results.size()}명 / ${percentage}% )</span>
									       					</div>
								                    		<c:forEach var="subjectiveResult" items="${question.results}">
					                         					<c:if test="${subjectiveResult.subjectiveCn != null }">
					                         						<div class="mb10" style="border: 1px solid ghostwhite;border-radius: 3px;background:#f8fcff;padding: 1.2em;">기타답변 ${subjectiveResult.subjectiveCn}</div>
					                         					</c:if>
									                    	</c:forEach>
										      			</c:if>
										       			<c:if test="${searchVO.command eq 'rspond' && question.results.size() != 0}">
										       				<div  class="mb20 mt20">
											      				<input type="${question.questionType eq 'RADIO' ? 'radio' : 'checkbox'}" checked readonly>
											       				<span>기타의견 : ${question.results[0].subjectiveCn}</span>
										       				</div>
										       			</c:if>
							                    	</c:if>
					                         		<script>
							       						$(function(){
							       							$(".fill").each( function( _, el) { el.style.width=el.dataset.percentage });
							       						});
							       					</script>
			                 					<!-- 선택지 테이블 끝 -->
			                         			</c:if>
			                         	</tr>
			                         	<!-- 질문 끝 -->
	                     			</c:forEach>
		                    	</c:otherwise>
	                    	</c:choose>
	                    </tbody>
	                 	</table>
	                 	<!-- 질문 테이블 끝 -->
		        	</div>
		        </div>
	        </div>
	        <div class="btn-box">
	            <!-- <a href="#lnk" onclick="javascript:fn_update();" class="tbl-btn ">수정</a>
	            <a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a> -->
	            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
	        </div>
			<form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
				<input type="hidden" name="surveyId" value="<c:out value='${searchVO.surveyId}'/>" >
				<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
				<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
				<input type="hidden" name="searchCate" value="<c:out value='${searchVO.searchCate}'/>"/>
				<input type="hidden" name="questionId" id="questionId" value="">
				<input type="hidden" name="command" id="command" value="${searchVO.command}">
				<input type="hidden" name="flag" id="flag" value="list"/>
			</form>
		</div>
	</div>
