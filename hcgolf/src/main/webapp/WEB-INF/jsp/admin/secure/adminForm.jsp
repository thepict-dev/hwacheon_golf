<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_overlapCheck(){
			var checkId = $("#adminId").val();
			if(checkId == null || checkId == ''){
				alert("아이디를 입력해주세요.");
				$("#adminId").focus();
				return;
			}
			var url = "/_admin/secure/adminOverlapCheck.do?adminId=" + checkId;
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
						$("#adminId").val("");
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
		function fn_submit(v_command){
			if($("#adminName").val() == null || $("#adminName").val() == ""){
				alert("이름을 입력해주세요.");
				$("#adminName").focus();
				return;
			}
			if(v_command == 'insert'){
				if($("#adminId").val() == null || $("#adminId").val() == ""){
					alert("아이디를 입력해주세요.");
					$("#adminId").focus();
					return;
				}else if($("#adminId").val() != $("#isChecked").val()){
					alert("아이디 중복여부를 확인해주세요.");
					$("#adminId").focus();
					return;
				}
			}
			<c:if test="${searchVO.command eq 'insert' }">
				if($("#adminPw").val() == null || $("#adminPw").val() == ""){
					alert("비밀번호를 입력해주세요.");
					$("#adminPw").focus();
					return;
				}
				if($("#adminPw").val() != $("#adminPwChk").val()){
					alert("비밀번호를 다시 확인해주세요.");
					$("#adminPwChk").focus();
					return;
				}
			</c:if>
			<c:if test="${searchVO.command ne 'insert' }">
				if($("#adminPw").val() != null && $("#adminPw").val() != ""){
					if($("#adminPw").val() != $("#adminPwChk").val()){
						alert("비밀번호를 다시 확인해주세요.");
						$("#adminPwChk").focus();
						return;
					}
				}
			</c:if>
			/* if($("#adminRole option:selected").val() == null || $("#adminRole option:selected").val() == ""){
				alert("권한을 선택해주세요.");
				$("#adminRole").focus();
				return;
			} */
			if(v_command == 'insert'){
				if(confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/secure/adminInsert.do");
					$("#frm").submit();
				}
			}else{
				if(confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/secure/adminUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/secure/adminList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/secure/adminList.do");
			$("#frm").submit();
		}
		function fn_deletePermanent() {
			if(confirm("해당 계정을 삭제하시겠습니까?\n삭제 후에는 복구가 불가능합니다.")){
				$("#frm").attr("action","/_admin/secure/adminDeletePermanent.do");
				$("#frm").submit();
			}
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">회원 관리</a></li>
	            <li><a href="#lnk">관리자 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">관리자 관리</h2>
			<div class="section-wrap res">
				<div class="section">
		        	<form action="" id="frm" name="frm" method="post">
		        		<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
		        		<input type="hidden" name="searchCnd" value="${searchVO.searchCnd }">
		        		<input type="hidden" name="searchWrd" value="${searchVO.searchWrd }">
		        		<input type="hidden" name="adminRole" value="ROLE_AA">
		        		<input type="hidden" name="useFlag" value="Y">
                    	<fieldset>
							<p class="section-tit">관리자 정보</p>
					        <div class="tbl-wrap new-tbl res">
					            <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
					            <table class="tbl03">
					                <caption class="blind">관리자 등록 테이블</caption>
					                <colgroup>
					                    <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">이름 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" name="adminName" id="adminName" placeholder="이름 입력" title="이름 입력" class="w50" value="${resultVO.adminName }">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">아이디 <em class="essential">*</em></th>
						                    <td>
						                        <div class="id-check">
						                        	<c:if test="${searchVO.command eq 'update' }">
						                            	<input type="hidden" name="adminId" id="adminId" value="${resultVO.adminId }">
						                            	<c:out value="${resultVO.adminId }"/>
						                            </c:if>
						                        	<c:if test="${searchVO.command eq 'insert' }">
						                            	<input type="text" name="adminId" id="adminId" placeholder="아이디 입력" title="아이디 입력" class="w50" onkeyup="this.value=this.value.replace(/[^a-zA-Z_0-9.]/g,'');">
						                            	<input type="hidden" id="isChecked" value=""/>
						                            	<a href="javascript:void(0)" onclick="javascript:fn_overlapCheck();" class="same-check">중복확인</a>
						                            </c:if>
						                        </div>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">비밀번호<c:if test="${searchVO.command eq 'insert' }"> <em class="essential">*</em></c:if></th>
						                    <td>
						                        <input type="password" name="adminPw" id="adminPw" placeholder="비밀번호 입력" title="비밀번호 입력" class="w50">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">비밀번호 확인<c:if test="${searchVO.command eq 'insert' }"> <em class="essential">*</em></c:if></th>
						                    <td>
						                        <input type="password" id="adminPwChk" placeholder="비밀번호 확인" title="비밀번호 확인" class="w50">
						                    </td>
						                </tr>
						                <%--<tr>
						                    <th class="tl">권한 <em class="essential">*</em></th>
						                    <td>
						                        <select name="adminRole" id="adminRole">
						                            <option value="">선택</option>
						                            <option value="ROLE_AA" <c:if test="${resultVO.adminRole eq 'ROLE_AA' }">selected</c:if>>통합관리자</option>
						                            <option value="ROLE_BB" <c:if test="${resultVO.adminRole eq 'ROLE_BB' }">selected</c:if>>일반관리자</option>
						                        </select>
						                    </td>
						                </tr>
						                <tr>
						                	<th class="tl">상태 <em class="essential">*</em></th>
						                    <td>
						                        <select name="useFlag" id="useFlag">
						                            <option value="Y">승인</option>
						                            <option value="L" <c:if test="${resultVO.useFlag eq 'L' }">selected</c:if>>잠김</option>
						                            <option value="N" <c:if test="${resultVO.useFlag eq 'N' }">selected</c:if>>탈퇴</option>
						                        </select>
						                    </td>
						                </tr> --%>
					                </tbody>
					            </table>
					        </div>
				        </fieldset>
		            </form>
				</div>
			</div>
            <div class="btn-box">
                <c:if test="${searchVO.command eq 'insert' }">
		            <a href="javascript:void(0)" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="javascript:void(0)" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		           	<a href="javascript:void(0)" onclick="javascript:fn_deletePermanent();" class="tbl-btn red">삭제</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
            </div>
	    </div>
	</div>