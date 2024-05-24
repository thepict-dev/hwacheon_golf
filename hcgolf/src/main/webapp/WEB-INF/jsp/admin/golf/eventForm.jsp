<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import = "egovframework.breeze.code.service.CodeVO"%>
<%@ page import = "egovframework.breeze.code.web.CodeBundle"%>
<%@ page import = "java.util.*"%>
<%
	CodeBundle cb = new CodeBundle(request);
%>

	<script type="text/javascript" src="/_SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
		$(document).ready(function(){
			/* var amUseAt = $("input[name='amUseFlag']:checked").val();
			var pmUseAt = $("input[name='pmUseFlag']:checked").val();
			
			if(amUseAt == 'Y'){
				$('#amApply').attr('disabled',false);
				//$('#amSpare').attr('disabled',false);
			}else{
				$('#amApply').attr('disabled',true);
				//$('#amSpare').attr('disabled',true);
			}
			
			if(pmUseAt == 'Y'){
				$('#pmApply').attr('disabled',false);
				//$('#pmSpare').attr('disabled',false);
			}else{
				$('#pmApply').attr('disabled',true);
				//$('#pmSpare').attr('disabled',true);
			} */
		});

		function fn_submit(v_command){

			if($("#eventDate").val() == ""){
				alert("일자를 선택해주세요.");
				$("#eventDate").focus();
				return;
			}
			/* 
			if($("#openDate").val() == ""){
				alert("예약 시작 일자를 선택해주세요.");
				$("#openDate").focus();
				return;
			}

			if($("#applicant").val() == ""){
				alert("이름을 입력해주세요.");
				$("#applicant").focus();
				return;
			}

			var amUseAt = $("input[name='amUseFlag']:checked").val();
			var pmUseAt = $("input[name='pmUseFlag']:checked").val();
			if(amUseAt != "Y" && pmUseAt != "Y"){
				alert("하나 이상 사용 시 등록이 가능합니다.");
				return false;
			}
		 	*/
			if(v_command == 'insert'){
				if(confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/event/eventInsert.do");
					$("#frm").submit();
				}
			}else{
				if(confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/event/eventUpdate.do");
					$("#frm").submit();
				}
			}
		}
		
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/event/eventList.do");
				$("#frm").submit();
			}
		}
		/* 
		function fn_change(v_flag){
			var flag = v_flag;
			if(flag == 'amUseY'){
				$('#amApply').attr('disabled',false);
				//$('#amSpare').attr('disabled',false);
			}else if (flag == 'amUseN'){
				$('#amApply').attr('disabled',true);
				//$('#amSpare').attr('disabled',true);
			}else if (flag == 'pmUseY'){
				$('#pmApply').attr('disabled',false);
				//$('#pmSpare').attr('disabled',false);
			}else if (flag == 'pmUseN'){
				$('#pmApply').attr('disabled',true);
				//$('#pmSpare').attr('disabled',true);
			}
		}
        */
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
	        <div class="section-wrap">
	             <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
	            	<input type="hidden" name="eventId" id="eventId" value="${selectVO.eventId }" >
					<input type="hidden" name="eventFlag" id="eventFlag" value="${searchVO.eventFlag }" >
	            	<input type="hidden" name="command" id="command" value="" >
	            	<input type="hidden" name="eventTimeChk" id="eventTimeChk" value="" >
					<input type="hidden" name="applyId" value="${searchVO.applyId }" >
					<input type="hidden" name="pmUseFlag" value="N">
					<input type="hidden" name="amApply" value="200">
					<input type="hidden" name="pmApply" value="0">
					<input type="hidden" name="amSpare" value="0">
					<input type="hidden" name="pmSpare" value="0">
					<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="eventDate2" value="${selectVO.eventDate }" >
					</c:if>
	                <fieldset>
	                    <div class="section">
	                        <p class="section-tit">예약관리</p>
	                        <div class="tbl-wrap new-tbl">
                           		<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
	                            <table class="tbl03">
	                                <caption class="blind">일정안내 뷰 테이블</caption>
	                                <colgroup>
	                                    <col style="width:17%">
	                                    <col style="width:13%">
	                                    <col style="width:20%">
	                                    <col style="width:17%">
	                                    <col style="width:13%">
	                                    <col style="width:20%">
	                                </colgroup>
	                                <tbody>
		                                <tr>
		                                    <th>일자 <em class="essential">*</em></th>
		                                    <td colspan="5">
					                           	<div class="datepicker-wrap disib sdate">
													<input type="text" class="datepick js-datepicker reldate w50" readonly="readonly" name="eventDate" id="eventDate" value="${selectVO.eventDate}" style="width:50%;">
						                       	</div>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th>사용여부</th>
		                                    <td colspan="5">
                                       			<div class="option-radio">
                                           			<label>
				                            			<input type="radio" id="amUseFlag1" name="amUseFlag" onclick="fn_change('amUseY');" value="Y" checked="checked" <c:if test="${selectVO.amUseFlag eq 'Y' }">checked="checked"</c:if>/>사용
				                            		</label>
                                           			<label>
				                            			<input type="radio" id="amUseFlag2" name="amUseFlag" onclick="fn_change('amUseN');" value="N" <c:if test="${selectVO.amUseFlag eq 'N' }">checked="checked"</c:if>/>미사용
		                                    		</label>
		                                    	</div>
		                                    </td>
	                                    </tr>
		                                <%-- 
		                                <tr>
		                                    <th>예약 시작 일자 <em class="essential">*</em></th>
		                                    <td colspan="5">
					                           	<div class="datepicker-wrap disib edate">
													<input type="text" class="datepick js-datepicker reldate w50" readonly="readonly" name="openDate" id="openDate" value="${selectVO.openDate}" >
						                       	</div>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th rowspan="3" scope="col">오전</th>
                                   			<td class="tc th bl">사용여부</td>
		                                    <td class="bl br">
                                       			<div class="option-radio">
                                           			<label>
				                            			<input type="radio" id="amUseFlag1" name="amUseFlag" onclick="fn_change('amUseY');" value="Y" checked="checked" <c:if test="${selectVO.amUseFlag eq 'Y' }">checked="checked"</c:if>/>사용
				                            		</label>
                                           			<label>
				                            			<input type="radio" id="amUseFlag2" name="amUseFlag" onclick="fn_change('amUseN');" value="N" <c:if test="${selectVO.amUseFlag eq 'N' }">checked="checked"</c:if>/>미사용
		                                    		</label>
		                                    	</div>
		                                    </td>
		                                    <th rowspan="3" scope="col">오후</th>
                                   			<td class="tc th bl">사용여부</td>
		                                    <td class="bl">
		                                        <div class="option-radio">
		                                            <label>
			                            				<input type="radio" id="pmUseFlag1" name="pmUseFlag" onclick="fn_change('pmUseY');" value="Y" checked="checked" <c:if test="${selectVO.pmUseFlag eq 'Y' }">checked="checked"</c:if>/>사용
			                            			</label>
			                            			<label>
			                            				<input type="radio" id="pmUseFlag2" name="pmUseFlag" onclick="fn_change('pmUseN');" value="N" <c:if test="${selectVO.pmUseFlag eq 'N' }">checked="checked"</c:if>/>미사용
			                            			</label>
			                            		</div>
		                                    </td>
		                                </tr> 
		                                <tr>
                                   			<td class="tc th">신청인원</td>
				        					<c:if test="${searchVO.command eq 'insert' }">
			                                    <td class="bl"><input type="text" id="amApply" name="amApply" title="신청인원 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="0"> 명</td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
			                                    <td class="bl"><input type="text" id="amApply" name="amApply" title="신청인원 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="${selectVO.amApply}"> 명</td>
				        					</c:if>
                                   			<td class="tc th">신청인원</td>
				        					<c:if test="${searchVO.command eq 'insert' }">
		                                    	<td class="bl"><input type="text" id="pmApply" name="pmApply" title="신청인원 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="0"> 명</td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
		                                    	<td class="bl"><input type="text" id="pmApply" name="pmApply" title="신청인원 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="${selectVO.pmApply}"> 명</td>
				        					</c:if>
			                            </tr>
		                                <tr>
                                   			<td class="tc th">대기그룹</td>
				        					<c:if test="${searchVO.command eq 'insert' }">
			                                    <td class="bl"><input type="text" id="amSpare" name="amSpare" title="대기그룹 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="0"> 팀</td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
			                                    <td class="bl"><input type="text" id="amSpare" name="amSpare" title="대기그룹 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="${selectVO.amSpare}"> 팀</td>
				        					</c:if>
                                   			<td class="tc th">대기그룹</td>
				        					<c:if test="${searchVO.command eq 'insert' }">
		                                    	<td class="bl"><input type="text" id="pmSpare" name="pmSpare" title="대기그룹 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="0"> 팀</td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
		                                    	<td class="bl"><input type="text" id="pmSpare" name="pmSpare" title="대기그룹 수 입력" class="w70" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" value="${selectVO.pmSpare}"> 팀</td>
				        					</c:if>
		                                </tr>
		                                --%>
	                                </tbody>
	                            </table>
	                        	<span class="pt10"><em class="essential">※</em> 신청자가 있을 경우 수정이 불가합니다.</span>
	                        </div>
	                    </div>
	                </fieldset>
	            </form>
	        </div>
	        <div class="btn-box">
	            <c:if test="${searchVO.command eq 'insert' }">
					<a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
	            </c:if>
	            <c:if test="${searchVO.command ne 'insert' }">
					<a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
	            </c:if>
				<a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn">취소</a>
	        </div>
	    </div>
	
	</div>