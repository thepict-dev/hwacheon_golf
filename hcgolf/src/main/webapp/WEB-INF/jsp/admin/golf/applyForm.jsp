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

		function fn_submit(v_command){

			if($("#applicant").val() == ""){
				alert("신청자 이름을 입력해주세요.");
				$("#applicant").focus();
				return;
			}

			if($("#age").val() == ""){
				alert("신청자 나이를 입력해주세요.");
				$("#age").focus();
				return;
			}

			if($("#tel1").val() == null || $("#tel1").val() == ""){
				alert("휴대폰 번호를 입력해주세요.");
				$("#tel1").focus();
				return;
			}
			if($("#tel2").val() == null || $("#tel2").val() == ""){
				alert("휴대폰 번호를 입력해주세요.");
				$("#tel2").focus();
				return;
			}
			if($("#tel3").val() == null || $("#tel3").val() == ""){
				alert("휴대폰 번호를 입력해주세요.");
				$("#tel3").focus();
				return;
			}
			$("#phone").val($("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val());

			if($("#email").val() == ""){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return;
			}

			if($("#address").val() == ""){
				alert("주소를 입력해주세요.");
				$("#address").focus();
				return;
			}

			if(confirm('수정하시겠습니까?')) {
				$("#frm").attr("action", "/_admin/event/applyUpdate.do");
				$("#frm").submit();
			}
		}
		
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/event/applyView.do");
				$("#frm").submit();
			}
		}

		function fn_egov_downFile(atchFileId, fileSn){
			window.open("<c:url value='/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}

		function checkFile(f){
			// files 로 해당 파일 정보 얻기.
			var checkId = f.id;
			var file = f.files;
			// file[0].name 은 파일명 입니다.
			// 정규식으로 확장자 체크
			if(!/\.(gif|png|jpg|jpeg|doc|docx|xls|xlsx|hwp|pdf)$/i.test(file[0].name)){
				alert('gif, png, jpg, jpeg, doc, docx, xls, xlsx, hwp, pdf 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
				$("#"+checkId).val("");
			// 체크를 통과했다면 종료.
			}else{ 
				return;
			}
		}

		function fn_fileDel(){
			var agent = navigator.userAgent.toLowerCase();
			if((navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {
				// ie 일때 input[type=file] init.
				$("#fileUp").replaceWith( $("#fileUp").clone(true) );
			}else {
				// other browser 일때 input[type=file] init.
				$("#fileUp").val("");
			}
			$(".upload").val("");
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
	        <div class="section-wrap">
	             <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
	            	<input type="hidden" name="eventId" id="eventId" value="${searchVO.eventId }" >
					<input type="hidden" name="eventFlag" id="eventFlag" value="${searchVO.eventFlag }" >
					<input type="hidden" name="eventDate" id="eventDate" value="${searchVO.eventDate }" >
	            	<input type="hidden" name="command" id="command" value="" >
					<input type="hidden" name="applyId" value="${searchVO.applyId }" >
					<input type="hidden" name="atchFileId" id="atchFileId" value="${selectVO.atchFileId }" >
					<input type="hidden" name="ampm" id="ampm" value="${searchVO.ampm }" >
	                <fieldset>
	                    <div class="section">
	                        <p class="section-tit">
			            		예약관리 신청자 form
	                        </p>
	                        <div class="tbl-wrap new-tbl">
	                            <table class="tbl03 iptwid">
	                                <caption class="blind">일정안내 뷰 테이블</caption>
	                                <colgroup>
	                                    <col style="width:17%">
	                                    <col style="width:8%">
	                                    <col style="width:20%">
	                                    <col style="width:8%">
	                                    <col style="width:13%">
	                                </colgroup>
	                                <tbody>
		                                <tr>
		                                    <th class="tl">참가 희망일</th>
		                                    <td colspan="4">${selectVO.eventDate}</td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">참가 시간</th>
		                                    <td colspan="4">${selectVO.eventTime}</td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">신청자 이름 <em class="essential">*</em></th>
		                                    <td colspan="4">
							                    <input type="text" id="applicant" name="applicant" class="w50" value="${selectVO.applicant}"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">신청자 나이 <em class="essential">*</em></th>
		                                    <td colspan="4">
							                    <input type="text" id="age" name="age" class="w50" value="${selectVO.age}"/>
		                                    </td>
		                                </tr>
						                <tr>
						                    <th class="tl">휴대폰 번호 <em class="essential">*</em></th>
						                    <td class="call" colspan="4">
		                                        <input type="text" id="tel1" value="${fn:split(selectVO.phone, '-')[0] }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
		                                        <em class="bar">-</em>
		                                        <input type="text" id="tel2" value="${fn:split(selectVO.phone, '-')[1] }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
		                                        <em class="bar">-</em>
		                                        <input type="text" id="tel3" value="${fn:split(selectVO.phone, '-')[2] }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
		                                        <input type="hidden" name="phone" id="phone" value="${selectVO.phone }">
						                    </td>
						                </tr>
		                                <tr>
		                                    <th class="tl">이메일 <em class="essential">*</em></th>
		                                    <td colspan="4">
							                    <input type="text" id="email" name="email" class="w50" value="${selectVO.email}"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">주소 <em class="essential">*</em></th>
		                                    <td colspan="4">
							                    <input type="text" id="address" name="address" class="w50" value="${selectVO.address}"/>
	                            				<em class="write-guide">※ 주소는 도, 시, 면, 동까지만 입력해주세요. (상세주소는 입력하지 마세요.)</em>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">참가자 이름</th>
		                                    <td colspan="4">
							                    <input type="text" id="participant" name="participant" class="w100" value="${selectVO.participant}"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">참가 인원</th>
	                                  		<td class="tc th bl">성인</td>
		                                    <td class="bl br">
							                    <input type="text" id="adultNum" name="adultNum" class="w70" value="${selectVO.adultNum}"/> 명
		                                    </td>
	                                  		<td class="tc th bl">청소년</td>
		                                    <td class="bl br">
							                    <input type="text" id="youthNum" name="youthNum" class="w70" value="${selectVO.youthNum}"/> 명
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">참가자 특이사항</th>
		                                    <td colspan="4">
							                    <input type="text" id="remark" name="remark" class="w100" value="${selectVO.remark}"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">참가자 명렬표</th>
		                                    <td colspan="4">
			                                    <div class="input-box">
						                        	<c:if test="${selectVO.atchFileId ne null && selectVO.atchFileId != ''}">
						                        		<button type="button" style="width:auto;" onclick="javascript:fn_egov_downFile('<c:out value="${selectVO.atchFileId}"/>','<c:out value="${selectVO.fileSn}"/>')" class="btn-down mr10">명렬표 다운로드</button>
						                        	</c:if>
							                        <div class="add-file-box">
							                        	<label for="fileUp">명렬표 업로드</label>
							                            <input type="file" onchange="checkFile(this)" id="fileUp" name="inp-file">
							                            <input disabled class="upload disabled">
                                   						<button type="button" onclick="javascript:fn_fileDel();" class="file-del">삭제</button>
							                        </div>
			                                    </div>
		                                    </td>
		                                </tr>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </fieldset>
	            </form>
	        </div>
	        <div class="btn-box">
	            <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
				<a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn">취소</a>
	        </div>
	    </div>
	
	</div>