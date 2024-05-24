<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	<script>
		function fn_codeMasterList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/code/codeMasterList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/code/codeMasterForm.do");
			$("#frm").submit();
		}
		function fn_codeMstrForm(v_mstrId) {
			$("#command").val("update");
			$("#codeMasterId").val(v_mstrId);
			$("#frm").attr("action","/_admin/code/codeMasterForm.do");
			$("#frm").submit();
		}
		function fn_codeForm(v_mstrId, v_mstrNm) {
			$("#codeMasterId").val(v_mstrId);
			$("#codeMasterName").val(v_mstrNm);
			$("#frm").attr("action","/_admin/code/codeForm.do");
			$("#frm").submit();
		}
		function fn_delete(v_mstrId) {
			if(confirm("코드를 삭제하시겠습니까?")){
				$("#codeMasterId").val(v_mstrId);
				$("#frm").attr("action","/_admin/code/codeMasterDelete.do");
				$("#frm").submit();
			}
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">코드 관리</a></li>
	            <li><a href="#lnk">코드 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">코드 관리</h2>
        	<div class="search-box res">
	            <p class="tot-top">총 <span class="colblue">${resultCnt}</span>개</p>
	            <div class="pagenavbox mob selbox mb20 sel-chk">
	                <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="codeMasterId" id="codeMasterId" value="0">
						<input type="hidden" name="codeMasterName" id="codeMasterName" value="">
						<input type="hidden" name="command" id="command" value="">
	                    <fieldset>
	                        <legend class="blind">검색창</legend>
	                        <div class="tblselect">
	                            <select name="searchCnd" class="iptwid">
	                                <option value="0" <c:if test="${searchVO.searchCnd == '0' || searchVO.searchCnd == ''}">selected="selected"</c:if>>코드마스터 ID+코드마스터명</option>
	                                <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if>>코드마스터 ID</option>
	                                <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if>>코드마스터명</option>
	                            </select>
	                        </div>
	                        <div class="tblsearch">
	                            <input type="text" id="searchWrd" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd}" onkeydown="javascript:if(event.keyCode==13) {fn_codeMasterList('1');}">
	                            <button type="button" class="btn_search dark-blue" onclick="javascript:fn_codeMasterList('1');">검색</button>
	                        </div>
	                    </fieldset>
	                </form>
	            </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">게시판 관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:5%">
	                    <col style="width:15%">
	                    <col style="width:*">
	                    <col style="width:15%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>코드마스터 ID</th>
	                    <th>코드마스터명</th>
	                    <th>등록일</th>
	                    <th>코드관리</th>
	                    <th>삭제</th>
	                </tr>
	                </thead>
	                <tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
			                <tr>
			                    <td>${result.rownum }</td>
			                    <td>${result.codeMasterId }</td>
			                    <td class="tl"><a href="#lnk" onclick="fn_codeMstrForm('${result.codeMasterId}');">${result.codeMasterName}</a></td>
			                    <td>${result.regDate }</td>
			                    <td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_codeForm('${result.codeMasterId }', '${result.codeMasterName }')">관리</button></span></td>
			                    <td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.codeMasterId }')">삭제</button></span></td>
			                </tr>
		                </c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="6">등록된 코드마스터가 없습니다.</td>
		                    </tr>
			 			</c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_codeMasterList" />
            </div>
	    </div>
	</div>