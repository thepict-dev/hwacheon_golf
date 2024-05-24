<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
	<head lang="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>설문 수정 | 관리자</title>
	    <link rel="stylesheet" href="/_admin/css/common.css">
	    <link rel="stylesheet" href="/_admin/css/layout.css">
	    <link rel="stylesheet" href="/_admin/css/datepicker.css">
	    <link rel="stylesheet" href="/_admin/css/jquery.numberedtextarea.css">
	    <script src="/_admin/js/jquery-3.4.1.min.js"></script>
	    <script src="/_admin/js/jquery-ui-datepicker.min.js"></script>
	    <script src="/_admin/js/layout.js"></script>
	    <script src="/_admin/js/sub.js"></script>
	    <script src="/_admin/js/jquery.numberedtextarea.js"></script>
	    <script>
	    	// 선택지 추가
	    	function fn_add_answer(){
	    		const clone = $(".question-answer").last().clone();
	    		clone.find("input").val("");
	    		$(".question-answer").last().after(clone);
	    	}
	    	
	    	// 선택지 삭제
	    	function fn_delete_answer(btn){
	    		// 남은 선택지가 한 개인 경우, 삭제하지 않는다.
	    		if($(".question-answer").length==1){
	    			alert("선택지는 적어도 한 개 있어야 합니다.");
	    			return;
	    		}
	    		const answer=$(btn).closest(".question-answer");
	    		answer.remove();
	    	}
	    	
	    	// 질문의 답변 유형이 수정됨
	    	function fn_change_question_type(select){
	    		// 주관식 답변이 아닌 경우에만 선택지 입력창을 활성화시킨다.
	    		const active = $(select).find("option:selected").val() !== 'TEXT';
	    		
	    		$(select).closest("tr").siblings('.question-answers').toggle(active);
	    		$(select).closest("tr").siblings().find("input[name^='useOtherSelectFlag']").closest("tr").toggle(active);
	    	}
	    	
	    	function fn_submit(v_command){
	    		
	    		// 필수값이 빈 칸인지 확인한다.
	    		if ( $('#questionCode').val() == "" ) {
	    			  $('#questionCode').focus();
					alert("번호를 입력해주세요.");
					return;
				}
				if ( $('#questionTitle').val() == "" ) {
          			$('#questionTitle').focus();
					alert("질문을 입력해주세요.");
					return;
				}
				if ( $('#questionType').val() == "" ) {
          			$('#questionType').focus();
					alert("답변 유형을 선택해주세요.");
					return;
				}
				let isEmpty = false;
				$('.answerTitles').each(function(_, el){
					if ( !isEmpty && $(el).val() == "" ){
	          			$(el).focus();
						alert("선택지 내용을 입력해주세요.");
						isEmpty = true;
					}
				})
				if (isEmpty){
					return;
				}
	    		
	    		if (v_command == "update"){
					if (confirm('수정하시겠습니까?')) {
						$('#frm').attr("action", "/_admin/survey/surveyQuestionUpdate.do");
						$('#frm').submit();
					}
	    		} else if (v_command == "insert"){
					if (confirm('등록하시겠습니까?')) {
						$('#frm').attr("action", "/_admin/survey/surveyQuestionInsert.do");
						$('#frm').submit();
					}
	    		}
			}
	    	
	    	// 수정하고 있는 질문을 삭제한다.
	    	function fn_delete(){
				if (confirm("질문을 삭제하시겠습니까?")){	
					$("#frm").attr("action", "/_admin/survey/surveyQuestionDelete.do");
					$("#frm").submit();
				}
			}
	    	
	    	// 질문 작성을 종료
	    	function fn_cancel(){
	    		if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){ 
					window.close();
				}
	    	}
	    	
	    	
	    </script>
	</head>
	<body>
		<div>
		    <div class="container popup">
		       	<c:if test="${searchVO.command eq 'update'}">
		        	<h2 class="con-tit mt40">질문 수정</h2>
		        </c:if>
		        <c:if test="${searchVO.command ne 'update'}">
		        	<h2 class="con-tit mt40">질문 추가</h2>
		        </c:if>
		        <div class="section-wrap">
		        	<div class="section">
			        	<p class="section-tit">질문 정보</p>
				        <div class="tbl-wrap new-tbl">
				        	<form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
			               		<input type="hidden" name="surveyId" value="${result.surveyId}"/>
			               		<input type="hidden" name="questionId" value="${result.questionId}"/>
			                	<table class="tbl03" >
					                <caption class="blind">질문 테이블</caption>
					                <colgroup>
					                    <col style="width:20%">
					                    <col style="width:80%">
					                </colgroup>
					                <tbody>
					                	<tr>
						                    <th class="tl">번호</th>
						                    <td>
						                    	 <input type="text" title="질문 번호 입력" onclick="event.stopPropagation()" name="questionCode${questionStatus.index}" id="questionCode${resultStatus.index}" value="${result.questionCode}" > 
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">질문</th>
						                    <td class="text-box info-ir">
						                    	<textarea class="txt bg-line" name="questionTitle" id="questionTitle" title="질문 제목 입력" style="border-color: #ccc; min-height: 24px;" >${result.questionTitle}</textarea>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">답변유형</th>
						                    <td class="tl">
						                    	<select name="questionType" onchange="fn_change_question_type(this);">
						                    		<option <c:if test="${result.questionType eq 'RADIO'}">selected</c:if> value="RADIO">객관식 단일 응답</option>
						                    		<option <c:if test="${result.questionType eq 'CHECK'}">selected</c:if> value="CHECK">객관식 복수 응답</option>
						                    		<option <c:if test="${result.questionType eq 'TEXT'}">selected</c:if> value="TEXT">주관식 응답</option>
						                    	</select>
						                    </td>
						                </tr>
						                
										<!-- 선택지 목록시작 -->
					                	<tr class="question-answers" <c:if test="${result.questionType eq 'TEXT'}">style="display: none;"</c:if>>
						                    <th class="tl">선택지</th>
						                    <td class="tl">
								                <c:forEach var="answer" items="${result.answers}" varStatus="answerStatus">
							                	<!-- 선택지 시작 -->
							                	<div class="question-answer mb10">
								                    <input type="hidden" name="answerId" id="answerId" value="${answer.answerId}"/>
							                        <input type="text" name="answerTitles" class="answerTitles" value="${answer.answerTitle}">
				           							<div class="btn-box right">
							                			<a href="#lnk" onclick="javascript:fn_delete_answer(this);" class="tbl-btn red">삭제</a>
						                			</div>
							                	</div>
							                	<!-- 선택지 끝 -->
								                </c:forEach>
								                <div class="btn-box right" style="float:right" >
						                			<a href="#lnk" onclick="javascript:fn_add_answer($('.question-answer:last-child'));" class="tbl-btn blue">추가</a>
					                			</div>
						                    </td>
						                </tr>
										<!-- 선택지 목록 끝 -->
										
						                <tr <c:if test="${result.questionType eq 'TEXT'}">style="display: none;"</c:if> >
						                    <th class="tl">기타입력란</th>
						                    <td class="tl">
						                        <label for="limtiRspdentDup_Y">사용</label>
						                        <input type="radio" name="useOtherSelectFlag" id="useOtherSelectFlag_Y" <c:if test="${result.useOtherSelectFlag eq 'Y'}">checked</c:if> value="Y">
						                        <label for="limtiRspdentDup_N">미사용</label>
						                        <input type="radio" name="useOtherSelectFlag" id="useOtherSelectFlag_N" <c:if test="${result.useOtherSelectFlag ne 'Y'}">checked</c:if> value="N" >
						                    </td>
						                </tr>
					                </tbody>
				                </table>
			                </form>
			            </div>
			    	</div>
			   	</div>
	            <div class="tbl-wrap mt40">
		            <div class="btn-box">
		            	<c:if test="${searchVO.command eq 'update'}">
			                <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
			                <a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a>
			                <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn">취소</a>
			            </c:if>
			            <c:if test="${searchVO.command ne 'update'}">
			                <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
			                <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn">취소</a>
			            </c:if>
		            </div>
	        	</div>
		    </div>
		</div>
	</body>
</html>