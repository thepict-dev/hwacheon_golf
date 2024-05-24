<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_accessList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/secure/accessList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/secure/accessForm.do");
			$("#frm").submit();
		}
		function fn_update(v_accessId) {
			$("#command").val("update");
			$("#accessId").val(v_accessId);
			$("#frm").attr("action","/_admin/secure/accessForm.do");
			$("#frm").submit();
		}
		function fn_delete(v_accessId) {
			if(confirm("해당 IP를 삭제하시겠습니까?")){
				$("#accessId").val(v_accessId);
				$("#frm").attr("action","/_admin/secure/accessDelete.do");
				$("#frm").submit();
			}
		}
		
		function fn_default() {
			if(confirm("저장 하시겠습니까??")){
				$("#defaultFrm").attr("action","/_admin/secure/defaultUpdate.do");
				$("#defaultFrm").submit();
			}
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
	    <div class="container">
	        <h2 class="con-tit pb25">접근제한 관리</h2>
	        <form name="defaultFrm" id="defaultFrm" method="post" action="" enctype="multipart/form-data">
	        	<input type="hidden" name="returnUrl" value="/_admin/secure/accessList.do">
	        	<input type="hidden" name="logoFileId" value="${defaultVO.logoFileId}">
				<input type="hidden" name="logoFilePath" value="${defaultVO.logoFilePath}">
				<input type="hidden" name="defaultName" value="${defaultVO.defaultName}">
				
				<div class="radio-wrap mb20">
				    <div class="un-use"><input type="radio" value="N" name="ipLimitFlag" <c:if test="${defaultVO.ipLimitFlag ne 'Y'}">checked</c:if> ><label>미사용</label></div>
				    <div class="use"><input type="radio" value="Y" name="ipLimitFlag" <c:if test="${defaultVO.ipLimitFlag eq 'Y'}">checked</c:if> ><label>사용</label></div>
				    <div class="add-btn-box">
					  <button type="button" onclick="fn_default();">저장</button>
					</div>
				</div>
            </form>
			<!-- <p class="tot-top" style="color:red;">※ 접근제한 관리는 보안관리 > 관리자 기본설정 > 접근제한 사용여부를 '사용'으로 설정하셔야 적용됩니다.</p><br> -->
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">접근제한 관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:10%">
	                    <col style="width:20%">
	                    <col style="width:">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th>번호</th>
		                    <th>접근 IP</th>
		                    <th>내용</th>
		                    <th>수정</th>
		                    <th>삭제</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:if test="${resultCnt == 0 }">
	                		<tr>
	                			<td colspan="5">등록된 IP정보가 없습니다.</td>
	                		</tr>
	                	</c:if>
	                	<c:forEach var="result" items="${resultList }" varStatus="status">
	                		<tr>
	                			<td><c:out value="${result.rownum }"/></td>
	                			<td><c:out value="${result.accessIp }"/></td>
	                			<td class="tl"><c:out value="${result.accessDescription }"/></td>
	                			<td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.accessId}');">수정</button></span></td>
	                			<td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.accessId}');">삭제</button></span></td>
	                		</tr>
	                	</c:forEach>
	                </tbody>
	            </table>
	            <form name="frm" id="frm" method="post" action="">
					<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
					<input type="hidden" name="accessId" id="accessId" value="0">
					<input type="hidden" name="command" id="command" value="">
	            </form>
	        </div>
            <div class="btn-box">
                <a href="javascript:void(0);" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
            	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_accessList" />
            </div>
	    </div>
	</div>