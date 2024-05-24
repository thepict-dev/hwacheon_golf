<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	<script>
		function fn_submit(v_command){
			if($("#accessIp").val() == null || $("#accessIp").val() == ''){
				alert("허용IP주소를 입력해주세요.");
				$("#accessIp").focus();
				return;
			}
			if($("#accessDescription").val() == null || $("#accessDescription").val() == ''){
				alert("내용을 입력해주세요.");
				$("#accessDescription").focus();
				return;
			}
			if(v_command == 'insert'){
				if(confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/secure/accessInsert.do");
					$("#frm").submit();
				}
			}else{
				if(confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/secure/accessUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/secure/accessList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/secure/accessList.do");
			$("#frm").submit();
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">보안 관리</a></li>
	            <li><a href="#lnk">접근제한 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scoll">
	        <h2 class="con-tit">접근제한 관리</h2>
	        <div class="section-wrap">
	            <div class="section">
		        	<form action="" id="frm" name="frm" method="post">
		            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
			        	<c:if test="${searchVO.command eq 'update' }">
		            		<input type="hidden" name="accessId" value="${accessVO.accessId }" >
		            	</c:if>
		                <fieldset>
                        	<p class="section-tit">접근제한 정보</p>
                        	<div class="tbl-wrap new-tbl">
					            <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
					            <table class="tbl03">
					                <caption class="blind">접근제한 수정 테이블</caption>
					                <colgroup>
					                    <col style="width:15%">
					                    <col style="width:85%">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">허용 IP 주소 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="허용 IP 주소 입력" name="accessIp" id="accessIp" value="${accessVO.accessIp}" onkeyup="this.value=this.value.replace(/[^0-9.:]/g,'');"/>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">내용 <em class="essential">*</em></th>
						                    <td class="text-box info-ir">
						                        <textarea class="txt" title="내용 입력" name="accessDescription" id="accessDescription">${accessVO.accessDescription}</textarea>
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
		            <a href="javascript:void(0)" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="javascript:void(0)" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		            <a href="javascript:void(0)" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
            </div>
	    </div>
	</div>