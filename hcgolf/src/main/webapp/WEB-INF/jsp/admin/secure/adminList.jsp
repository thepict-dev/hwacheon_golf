<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_adminList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/secure/adminList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/secure/adminForm.do");
			$("#frm").submit();
		}
		function fn_update(v_adminId) {
			$("#command").val("update");
			$("#adminId").val(v_adminId);
			$("#frm").attr("action","/_admin/secure/adminForm.do");
			$("#frm").submit();
		}
	</script>

	<div class="container-wrap">
		<div class="breadcrumbs">
			<ul>
				<li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
				<li><a href="#lnk">회원 관리</a></li>
				<li><a href="#lnk">통합관리자 관리</a></li>
			</ul>
		</div>
		<div class="container">
			<h2 class="con-tit">통합관리자 관리</h2>
			<div class="search-box res">
				<p class="tot-top">총 <span class="colblue">${resultCnt}</span>개</p>
				<div class="pagenavbox mob selbox mb20 sel-chk">
		            <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="adminId" id="adminId" value="0">
						<input type="hidden" name="command" id="command" value="">
						<fieldset>
							<legend class="blind">검색창</legend>
							<div class="tblselect">
								<select name="searchCnd" class="iptwid">
									<option value="0">전체</option>
									<option value="1" <c:if test="${searchVO.searchCnd == 1 }">selected</c:if>>이름</option>
									<option value="2" <c:if test="${searchVO.searchCnd == 2 }">selected</c:if>>아이디</option>
								</select>
							</div>
							<div class="tblsearch">
								<input type="text" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd }" onkeypress="if(event.keyCode == 13){fn_adminList(1);}">
								<button type="button" onclick="javascript:fn_adminList(1);" class="btn_search dark-blue">검색</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<div class="tbl-wrap res">
				<table class="tbl01">
					<caption class="blind">관리자 목록 테이블</caption>
					<colgroup>
	                    <col width="10%" class="colnone">
	                    <col width="28%">
	                    <col width="*">
	                    <col width="35%">
	                </colgroup>
					<thead>
						<tr>
							<th class="colnone">번호</th>
							<th>이름</th>
							<th>아이디</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${resultCnt == 0 }">
						<tr>
							<td colspan="4" class="col-change">
								등록된 관리자가 없습니다.
							</td>
						</tr>
					</c:if>
					<c:forEach var="result" items="${resultList }" varStatus="status">
						<tr>
							<td class="colnone"><c:out value="${result.rownum }"/></td>
							<td><c:out value="${result.adminName }"/></td>
							<td><c:out value="${result.adminId }"/></td>
							<td>
								<a href="javascript:void(0)" onclick="javascript:fn_update('${result.adminId }');" class="control">관리</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="btn-box">
                <a href="javascript:void(0);" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
            	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_adminList" />
            </div>
		</div>
	</div>