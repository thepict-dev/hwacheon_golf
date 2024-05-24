<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	<script>
		function fn_businessCheck(){
			var checkNo = $("#businessNo").val();
			if(checkNo == null || checkNo == ''){
				alert("사업자등록번호를 입력해주세요.");
				$("#businessNo").focus();
				return;
			}
			//자리수 체크
			if(checkNo.length != 10){
				alert("사업자등록번호가 올바르지 않습니다.");
				$("#businessNo").focus();
				return false;
			}
			//사업자번호 유효성 체크
			var bizID = $("#businessNo").val();
			// bizID는 숫자만 10자리로 해서 문자열로 넘긴다.
			var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
			var tmpBizID, i, chkSum=0, c2, remander;
			bizID = bizID.replace(/-/gi,'');
			
			for (i=0; i<=7; i++) chkSum += checkID[i] * bizID.charAt(i);
			c2 = "0" + (checkID[8] * bizID.charAt(8));
			c2 = c2.substring(c2.length - 2, c2.length);
			chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
			remander = (10 - (chkSum % 10)) % 10 ;
			
			if (Math.floor(bizID.charAt(9)) == remander) {
				//console.log("사업자번호체크 완료");
			}else{
				alert("사업자등록번호가 잘못되었습니다.");
				$("#businessNo").focus();
				return;
			}
			
			var url = "/_admin/member/businessOverlapCheck.do?businessNo=" + checkNo;
			$.ajax({
				type: "get",
				cache: false,
				url: url,
				dataType : 'json',
				contentType : "application/json; charset=utf-8",
				success: function(data){
					if(data.overlap == "Y"){
						$("#isCheckedBu").val("");
						alert("이미 등록된 사업자등록번호입니다. 다시 확인해주세요.");
						$("#businessNo").val("");
					}else{
						$("#isCheckedBu").val(checkNo);
						alert("사용 가능한 사업자등록번호입니다.");
					}
				},
				error: function(err){
					alert("통신 오류가 발생하였습니다. 관리자에게 문의해주세요.");
				}
			});
		}

		function fn_submit(v_command){
			if($("#departmentName").val() == null || $("#departmentName").val() == ""){
				alert("업체명을 입력해주세요.");
				$("#departmentName").focus();
				return;
			}

			if($("#businessNo").val() == null || $("#businessNo").val() == ""){
				alert("사업자등록번호를 입력해주세요.");
				$("#businessNo").focus();
				return;
			}else if($("#businessNo").val() != $("#isCheckedBu").val()){
				alert("사업자등록번호 중복여부를 확인해주세요.");
				$("#businessNo").focus();
				return;
			}

			if(v_command == 'insert'){
				if(confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/member/departmentInsert.do");
					$("#frm").submit();
				}
			}else{
				if(confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/member/departmentUpdate.do");
					$("#frm").submit();
				}
			}
		}

		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/member/departmentList.do");
				$("#frm").submit();
			}
		}

		function fn_delete() {
			if($("#memberCnt").val() == "0"){
				if(confirm("삭제하시겠습니까?")){
					$("#frm").attr("action","/_admin/member/departmentDelete.do");
					$("#frm").submit();
				}
			}else{
				alert("회원이 있는 소속은 삭제가 불가능합니다.");
			}
		}
	</script>


	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">회원 관리</a></li>
	            <li><a href="#lnk">소속 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">소속 관리</h2>
	        <div class="section-wrap">
	            <div class="section">
	            <form action="" id="frm" name="frm" method="post">
	        		<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }">
	        		<input type="hidden" name="searchWrd" value="${searchVO.searchWrd }">
		        	<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="departmentId" value="${searchVO.departmentId }" >
	            		<input type="hidden" id="memberCnt" value="${memberCnt }" >
	            	</c:if>
	                <fieldset>
	                    <p class="section-tit">소속 정보</p>
	                    <div class="tbl-wrap new-tbl">
	                        <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
	                        <table class="tbl03 iptwid mt10">
	                            <caption class="blind">소속 등록 테이블</caption>
	                            <colgroup>
	                                <col style="width:14%">
	                                <col>
	                            </colgroup>
	                            <tbody>
		                            <tr>
		                                <th class="tl" scope="col">업체명 <em class="essential">*</em></th>
		                                <td>
		                                    <input type="text" id="departmentName" name="departmentName" value="${resultVO.departmentName }" placeholder="업체명 입력" title="업체명 입력">
		                                </td>
		                            </tr>
		                            <tr>
		                                <th class="tl" scope="col">사업자등록번호 <em class="essential">*</em></th>
		                                <td>
						                    <div class="id-check">
				                            	<input type="text" name="businessNo" id="businessNo" placeholder="'-'없이 입력해 주세요." title="사업자등록번호 입력" class="w50" value="${resultVO.businessNo }" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10">
				                            	<input type="hidden" id="isCheckedBu" value="${resultVO.businessNo }"/>
				                            	<a href="javascript:void(0)" onclick="javascript:fn_businessCheck();" class="same-check">중복확인</a>
				                            </div>
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