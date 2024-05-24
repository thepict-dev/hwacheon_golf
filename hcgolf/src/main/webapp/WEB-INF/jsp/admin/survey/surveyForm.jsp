<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



	<script type="text/javascript" src="/_SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
		
		function fn_submit(v_command){

			oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
			
			if ( $("#startDate").val() > $("#endDate").val() ){
				alert("시작일은 종료일 이전이어야합니다.");
				$("#startDate").focus()
				return;
			}
			if ( $("#surveyTitle").val() == "" ) {
				alert("제목을 입력해 주세요");
				$("#surveyTitle").focus()
				return;
			}
			if ( $("#startDate, #endDate").val() == "" ) {
				alert("설문기간을 입력해 주세요");
				$("#startDate").focus()
				return;
			}
			if ( $("#surveyDesc").val() == "" ) {
				alert("설문 설명을 입력해 주세요");
				$("#surveyDesc").focus();
				return;
			}
			if ($("#skinId").val() == "") {
				alert("설문 스킨을 선택해주세요.");
				$("#skinId").focus();
				return;
			}
			
			
			// 폼 전송
			if ( v_command == "update"){
				if (confirm("수정하시겠습니까?")) {
					$("#startDate").val( $("#startDate").val() + " " + $("#startTime").val() );
					$("#endDate").val( $("#endDate").val() + " " + $("#endTime").val() );
					$("#frm").attr("action", "/_admin/survey/surveyUpdate.do");
					$("#frm").submit();
				}
				
			} else if (v_command == "insert"){
				if (confirm("등록하시겠습니까?")) {
					$("#startDate").val( $("#startDate").val() + " " + $("#startTime").val() );
					$("#endDate").val( $("#endDate").val() + " " + $("#endTime").val() );
					$("#frm").attr("action", "/_admin/survey/surveyInsert.do");
					$("#frm").submit();
				}
			}
		}
		
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/survey/surveyList.do");
				$("#frm").submit();
			}
		}
		
		function fn_list(){
			$("#frm").attr("action", "/_admin/survey/surveyList.do");
			$("#frm").submit();
		}
    	
    	$(function(){
    		
    		// 인원수 제한 사용 안함 시 0으로 설정
    		$("#UseLimitRspdentMax").change(function(){
                if ( $(this).is(":checked") ){
                	$("#limitRspdentMax").val("0");
                }
            });

        	// 달력 종료일은 시작일 이전으로 할 수 없다
        	// 달력 시작일은 종료일 이후로 할 수 없다
    		$('#startDate').datepicker("option", "maxDate", $("#endDate").val());
            $('#startDate').datepicker("option", "onClose", function (selectedDate){
                $("#endDate").datepicker( "option", "minDate", selectedDate );
            });

            $('#endDate').datepicker();
            $('#endDate').datepicker("option", "minDate", $("#startDate").val());
            $('#endDate').datepicker("option", "onClose", function (selectedDate){
                $("#startDate").datepicker( "option", "maxDate", selectedDate );
            });
    	});
    	 
    	
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
	        <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
	        	<input type="hidden" name="surveyId" value="${result.surveyId}"/>
				<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex}">
				<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}">
				<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}">
		        <div class="section-wrap">
		        	<div class="section">
		                <p class="section-tit">설문 정보</p>
			                <div class="tbl-wrap new-tbl">
			                	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
			                	<table class="tbl03">
					                <caption class="blind">설문 정보 테이블</caption>
					                <colgroup>
					                    <col style="width:15%">
					                    <col style="width:85%">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl"> 제목 <em class="essential">*</em></th>
						                    <td class="tl">
						                        <input type="text" placeholder="제목을 입력해 주세요." title="제목 입력" name="surveyTitle" id="surveyTitle" value="${result.surveyTitle}">
						                    </td>
						                </tr>
						                <tr>
						                	
						                    <th class="tl">설문 기간 <em class="essential">*</em></th>
						                    <td>
							                	<div style="display:inline;">
						                           	<div class="datepicker-wrap disib sdate" style="width: 20%;">
														<input type="text" style="width:100%" class="datepick js-datepicker reldate" readonly="readonly" name="startDate" id="startDate" value="${result.startDate.substring(0,11)}" >
							                       	</div>
							                       	<select  name="startTime" id="startTime" style="width:6em;margin-right:20px;">
							                       		<c:forEach var="hour" begin="0" end="23" step="1">
								                       		<option value="${hour}" <c:if test="${result.startDate.substring(11,13) eq hour}">selected</c:if>>${String.format("%02d",hour)}:00</option>
							                       		</c:forEach> 
							                       	</select>
							                       	~
							                    </div>
							                	<div style="display:inline;margin-left:20px;">
							                       	<div class="datepicker-wrap disib edate" style="width: 20%;">
							                            <input type="text" style="width:100%" class="datepick js-datepicker reldate" readonly="readonly" name="endDate" id="endDate" value="${result.endDate.substring(0,11)}">
							                        </div>
							                       	<select name="endTime" id="endTime" style="width:6em;">
							                       		<c:forEach var="hour" begin="0" end="23" step="1">
								                       		<option value="${hour}" <c:if test="${result.endDate.substring(11,13) eq hour}">selected</c:if>>${String.format("%02d",hour)}:00</option>
							                       		</c:forEach>
							                       	</select>
						                       	</div>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">설문 설명</th>
						                    <td class="text-box">
						                    	<textarea class="txt" name="surveyDesc" id="surveyDesc" title="설문 설명 내용 입력" style="width:100%;">${result.surveyDesc}</textarea>
					                        	<!-- 에디터 설정 -->
													<script type="text/javascript">
														var oEditors = [];
														nhn.husky.EZCreator.createInIFrame({
															oAppRef: oEditors,
															elPlaceHolder: "surveyDesc", //textarea에서 지정한 id와 일치해야 합니다.
															sSkinURI: "/_SE2/SmartEditor2Skin.html",
															fCreator: "createSEditor2"
														});
													</script>
													<!-- 에디터 설정 끝 -->
					                        </td>
						                </tr>
					                </tbody>
					            </table>
					    	</div>
			        	</div>
		        	
			            <div class="section">
			                <p class="section-tit">설문 설정</p>
			                <div class="tbl-wrap new-tbl">
				                <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
			                	<table class="tbl03">
					                <caption class="blind">설문 설정 테이블</caption>
					                <colgroup>
					                    <col style="width:16%">
					                    <col style="width:84%">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">설문 순서 <em class="essential">*</em></th>
						                    <td class="tl">
						                        <input type="text" title="설문 순서 입력" name="surveyOrder" id="surveyOrder" value="${(empty result.surveyOrder) ? 0 : result.surveyOrder}" oninput="$(this).val($(this).val().replace(/[^0-9]+/g, ''));">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">설문 공개 <em class="essential">*</em></th>
						                    <td class="tl">
						                        <input type="radio" name="openFlag" id="openFlag_Y" value="Y" <c:if test="${result.openFlag eq 'Y'}">checked</c:if> >
						                        <label for="openFlag_Y">공개</label>
						                        <input type="radio" name="openFlag" id="openFlag_N" value="N" <c:if test="${result.openFlag ne 'Y'}">checked</c:if> >
						                        <label for="openFlag_N">비공개</label>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">설문 결과 공개 <em class="essential">*</em></th>
						                    <td class="tl">
						                        <input type="radio" name="openResultFlag" id="openResultFlag_Y" value="Y" <c:if test="${result.openResultFlag eq 'Y'}">checked</c:if>>
						                        <label for="openResultFlag_Y">공개</label>
						                        <input type="radio" name="openResultFlag" id="openResultFlag_N" value="N" <c:if test="${result.openResultFlag ne 'Y'}">checked</c:if>>
						                        <label for="openResultFlag_N">비공개</label>
						                    </td>
						                </tr>
						                <tr>
			                                <th class="tl">설문 스킨 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <select name="skinId" id="skinId" style="width:calc(50% - 80px);">
			                                        <option value="">선택</option>
													<c:forEach var="skinResult" items="${skinList}" varStatus="status">
			                                        	<option value="${skinResult.skinId }" <c:if test="${result.skinId eq skinResult.skinId}">selected="selected"</c:if>>${skinResult.skinId } - ${skinResult.skinName }</option>
			                                        </c:forEach>
			                                    </select>
			                                </td>
			                            </tr>
					                </tbody>
				                </table>
							</div>
			            </div>
			            
				        <div class="section">
			                <p class="section-tit">설문 인원 제한</p>
			                <div class="tbl-wrap new-tbl"><table class="tbl03">
				                <caption class="blind">설문 대상 테이블</caption>
				                <colgroup>
				                    <col style="width:15%">
				                    <col style="width:85%">
				                </colgroup>
					                <tr>
					                    <th class="tl">인원수 제한</th>
					                    
					                    <td class="tl">
					                    	<div style="vertical-align:middle; display:inline-block">
						                        <input type="checkbox" id="UseLimitRspdentMax" checked>
						                        <label for="UseLimitRspdentMax">제한 없음</label>
					                    	</div>
					                    	<input type="text" title="설문 인원수 입력" style="width:calc(100% - 200px)" name="limitRspdentMax" id="limitRspdentMax" value="${(empty result.limitRspdentMax) ? 0 : result.limitRspdentMax}" oninput="$(this).val($(this).val().replace(/[^0-9]/g,''))">
					                    </td>
					                </tr>
					                <tr>
					                    <th class="tl">중복 참여 제한</th>
					                    <td class="tl">
					                        <input type="radio" name="limitRspdentDup" id="limitRspdentDup_Y" value="Y" <c:if test="${result.limitRspdentDup eq 'Y'}">checked</c:if>>
					                        <label for="limtiRspdentDup_Y">허용</label>
					                        <input type="radio" name="limitRspdentDup" id="limitRspdentDup_N" value="N" <c:if test="${result.limitRspdentDup ne 'Y'}">checked</c:if>>
					                        <label for="limtiRspdentDup_N">비허용</label>
					                    </td>
					                </tr>
				                </tbody>
			                </table></div>
			            </div>
		            </div>
        		</form>
	            <div class="btn-box">
	            	<c:if test="${searchVO.command ne 'update'}">
		                <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		                <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		                <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            	</c:if>
	            	<c:if test="${searchVO.command eq 'update'}">
		                <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		                <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		                <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            	</c:if>
	            </div>
	    	</div>
		</div>