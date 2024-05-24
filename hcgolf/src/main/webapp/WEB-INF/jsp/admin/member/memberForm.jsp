<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function fn_overlapCheck(){
			var checkId = $("#memberId").val();
			if(checkId == null || checkId == ''){
				alert("아이디를 입력해주세요.");
				$("#memberId").focus();
				return;
			}
			var url = "/_admin/member/memberOverlapCheck.do?memberId=" + checkId;
			$.ajax({
				type: "get",
				cache: false,
				url: url,
				dataType : 'json',
				contentType : "application/json; charset=utf-8",
				success: function(data){
					if(data.overlap == "Y"){
						$("#isChecked").val("");
						alert("이미 존재하는 아이디입니다. 다시 확인해주세요.");
						$("#memberId").val("");
					}else{
						$("#isChecked").val(checkId);
						alert("사용 가능한 아이디입니다.");
					}
				},
				error: function(err){
					alert("통신 오류가 발생하였습니다. 관리자에게 문의해주세요.");
				}
			});
		}
		function fn_get_address(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 추가 정보 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zipCode').value = data.zonecode;
	                document.getElementById('address').value = roadAddr;
	
		        }
		    }).open();
	    }
		function fn_submit(v_command){
			if(v_command == 'insert'){
				if($("#memberId").val() == null || $("#memberId").val() == ""){
					alert("아이디를 입력해주세요.");
					$("#memberId").focus();
					return;
				}else if($("#memberId").val() != $("#isChecked").val()){
					alert("아이디 중복여부를 확인해주세요.");
					$("#memberId").focus();
					return;
				}
			}
			<c:if test="${searchVO.command eq 'insert' }">
				if($("#memberPw").val() == null || $("#memberPw").val() == ""){
					alert("비밀번호를 입력해주세요.");
					$("#memberPw").focus();
					return;
				}
				if($("#memberPw").val() != $("#memberPwChk").val()){
					alert("비밀번호를 다시 확인해주세요.");
					$("#memberPwChk").focus();
					return;
				}
			</c:if>
			<c:if test="${searchVO.command ne 'insert' }">
				if($("#memberPw").val() != null && $("#memberPw").val() != ""){
					if($("#memberPw").val() != $("#memberPwChk").val()){
						alert("비밀번호를 다시 확인해주세요.");
						$("#memberPwChk").focus();
						return;
					}
				}
			</c:if>
			if($("#memberName").val() == null || $("#memberName").val() == ""){
				alert("이름을 입력해주세요.");
				$("#memberName").focus();
				return;
			}
			if($("#department").val() == ""){
				alert("소속을 선택해주세요.");
				$("#department").focus();
				return;
			}
			if($("#tel1").val() == null || $("#tel1").val() == ""){
				alert("연락처를 입력해주세요.");
				$("#tel1").focus();
				return;
			}
			if($("#tel1").val().length < 2){
				alert("연락처 형식이 잘못되었습니다.");
				$("#tel1").focus();
				return;
			}
			if($("#tel2").val() == null || $("#tel2").val() == ""){
				alert("연락처를 입력해주세요.");
				$("#tel2").focus();
				return;
			}
			if($("#tel2").val().length < 3){
				alert("연락처 형식이 잘못되었습니다.");
				$("#tel2").focus();
				return;
			}
			if($("#tel3").val() == null || $("#tel3").val() == ""){
				alert("연락처를 입력해주세요.");
				$("#tel3").focus();
				return;
			}
			if($("#tel3").val().length < 3){
				alert("연락처 형식이 잘못되었습니다.");
				$("#tel3").focus();
				return;
			}
			$("#telNo").val($("#tel1").val() + "-" + $("#tel2").val() + "-" + $("#tel3").val());

			if($("#email").val() == null || $("#email").val() == ""){
				alert("이메일을 입력해주세요.");
				$("#email").focus();
				return;
			}
			
			if(v_command == 'insert'){
				if(confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/member/memberInsert.do");
					$("#frm").submit();
				}
			}else{
				if(confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/member/memberUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/member/memberList.do");
				$("#frm").submit();
			}
		}
		function fn_delete() {
			if(confirm("삭제하시겠습니까?")){
				$("#frm").attr("action","/_admin/member/memberDeletePermanent.do");
				$("#frm").submit();
			}
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">회원 관리</a></li>
	            <li><a href="#lnk">회원 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">회원 관리</h2>
			<div class="section-wrap">
				<div class="section">
		        	<form action="" id="frm" name="frm" method="post">
		        		<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
		        		<input type="hidden" name="searchCnd" value="${searchVO.searchCnd }">
		        		<input type="hidden" name="searchWrd" value="${searchVO.searchWrd }">
		        		<input type="hidden" name="searchPart" value="${searchVO.searchPart }">
                    	<fieldset>
							<p class="section-tit">회원 정보</p>
					        <div class="tbl-wrap new-tbl">
					            <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
					            <table class="tbl03 iptwid mt10">
					                <caption class="blind">회원 등록 테이블</caption>
					                <colgroup>
		                                <col style="width:14%">
		                                <col>
	                                </colgroup>
					                <tbody>
			                            <tr>
			                                <th class="tl" scope="col">아이디 <em class="essential">*</em></th>
			                                <td>
						                        <div class="id-check">
						                        	<c:if test="${searchVO.command eq 'update' }">
						                            	<input type="hidden" name="memberId" id="memberId" value="${resultVO.memberId }">
						                            	<c:out value="${resultVO.memberId }"/>
						                            </c:if>
						                        	<c:if test="${searchVO.command eq 'insert' }">
						                            	<input type="text" name="memberId" id="memberId" placeholder="아이디 입력" title="아이디 입력" class="w50" onkeyup="this.value=this.value.replace(/[^a-zA-Z_0-9.]/g,'');">
						                            	<input type="hidden" id="isChecked" value=""/>
						                            	<a href="javascript:void(0)" onclick="javascript:fn_overlapCheck();" class="same-check">중복확인</a>
						                            </c:if>
						                        </div>
			                                </td>
			                            </tr>
						                <tr>
						                    <th class="tl">비밀번호<c:if test="${searchVO.command eq 'insert' }"> <em class="essential">*</em></c:if></th>
						                    <td>
						                        <input type="password" name="memberPw" id="memberPw" placeholder="비밀번호 입력" title="비밀번호 입력" class="w50" onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">비밀번호 확인<c:if test="${searchVO.command eq 'insert' }"> <em class="essential">*</em></c:if></th>
						                    <td>
						                        <input type="password" id="memberPwChk" placeholder="비밀번호 확인" title="비밀번호 확인" class="w50" onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/g,'');">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">이름 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" name="memberName" id="memberName" placeholder="이름 입력" title="이름 입력" class="w50" value="${resultVO.memberName }">
						                    </td>
						                </tr>
			                            <tr>
			                                <th class="tl" scope="col">소속 <em class="essential">*</em></th>
			                                <td>
			                                    <select class="w50" name="department" id="department">
			                                        <option value="">선택</option>
						                        	<c:forEach var="result" items="${departmentList}" varStatus="status">
			                                        	<option value="${result.departmentId }" <c:if test="${result.departmentId eq resultVO.department}">selected="selected"</c:if>>${result.departmentName }</option>
						                        	</c:forEach>
			                                    </select>
			                                </td>
			                            </tr>
						                <tr>
						                    <th class="tl">연락처 <em class="essential">*</em></th>
						                    <td class="call">
		                                        <input type="text" id="tel1" value="${fn:split(resultVO.telNo, '-')[0] }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
		                                        <em class="bar">-</em>
		                                        <input type="text" id="tel2" value="${fn:split(resultVO.telNo, '-')[1] }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
		                                        <em class="bar">-</em>
		                                        <input type="text" id="tel3" value="${fn:split(resultVO.telNo, '-')[2] }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="4">
		                                        <input type="hidden" name="telNo" id="telNo" value="${resultVO.telNo }"><em class="write-guide">※ 연락처는 '010-0000-0000' 형식으로 입력해주세요.</em>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">이메일 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" name="email" id="email" placeholder="이메일 입력" title="이메일 입력" class="w50" value="${resultVO.email }">
						                    </td>
						                </tr>
					                </tbody>
					            </table>
					        </div>
						</fieldset>
		            </form>
				</div>
	        </div>
            <div class="btn-box">
                <c:if test="${searchVO.command eq 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		           	<a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a>
		            <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
            </div>
	    </div>
	</div>