<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_analyticsList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/site/analyticsList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/site/analyticsForm.do");
			$("#frm").submit();
		}
		function fn_update(v_analyticsId) {
			$("#command").val("update");
			$("#analyticsId").val(v_analyticsId);
			$("#frm").attr("action","/_admin/site/analyticsForm.do");
			$("#frm").submit();
		}
		function fn_delete(v_analyticsId) {
			if(confirm("애널리틱스를 삭제하시겠습니까?")){
				$("#analyticsId").val(v_analyticsId);
				$("#frm").attr("action","/_admin/site/analyticsDelete.do");
				$("#frm").submit();
			}
		}
		function fn_backup(v_analyticsId) {
			window.open("/_admin/site/analyticsBakList.do?analyticsId="+v_analyticsId, "analyticsBakList", "width=1040,height=800");
		}
	</script>

	<div class="container-wrap">
		<div class="breadcrumbs">
			<ul>
				<li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
				<li><a href="#lnk">사이트관리</a></li>
				<li><a href="#lnk">애널리틱스 관리</a></li>
			</ul>
		</div>
		<div class="container">
			<h2 class="con-tit">애널리틱스 관리</h2>
			<div class="search-box res">
	        	<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
	        	<div class="pagenavbox mob selbox mb20 sel-chk">
		            <form id="frm" name="frm" action="" method="post">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="analyticsId" id="analyticsId" value="0">
						<input type="hidden" name="command" id="command" value="">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1" <c:if test="${searchVO.searchCnd eq 1}">selected</c:if> >애널리틱스 ID+애널리틱스 이름</option>
		                            <option value="2" <c:if test="${searchVO.searchCnd eq 2}">selected</c:if> >애널리틱스 이름</option>
		                            <option value="3" <c:if test="${searchVO.searchCnd eq 3}">selected</c:if> >애널리틱스 ID</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${searchVO.searchWrd}">
		                        <button type="button" class="btn_search dark-blue" onclick="javascript:fn_analyticsList('1');">검색</button>
		                    </div>
		                </fieldset>
		            </form>
	        	</div>
	        </div>
			<div class="tbl-wrap">
				<table class="tbl01">
					<caption class="blind">애널리틱스 관리 목록 테이블</caption>
					<colgroup>
						<col style="width:7%">
	                    <col style="width:15%">
	                    <col style="width:">
	                    <col style="width:14%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>애널리틱스 ID</th>
							<th>이름</th>
							<th>최종수정일</th>
	                    	<th>백업</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${resultCnt == 0 }">
							<tr>
								<td colspan="7">등록된 애널리틱스가 없습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
								<td>${result.rownum }</td>
								<td>${result.analyticsId }</td>
								<td class="tl"><a href="javascript:void(0);" onclick="javascript:fn_update('${result.analyticsId}');">${result.analyticsName }</a></td>
								<td>${result.updDate }</td>
	                    		<td><span class="manage-btn backup-btn"><button type="button" onclick="javascript:fn_backup('${result.analyticsId}');">백업</button></span></td>
								<td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.analyticsId}');">수정</button></span></td>
								<td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.analyticsId}');">삭제</button></span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="btn-box">
				<a href="javascript:void(0);" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
			</div>
			<div class="pager">
                <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_analyticsList" />
			</div>
		</div>
	</div>