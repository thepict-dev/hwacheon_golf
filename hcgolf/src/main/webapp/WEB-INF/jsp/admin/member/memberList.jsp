<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


	<script>
		function fn_adminList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/member/memberList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/member/memberForm.do");
			$("#frm").submit();
		}
		function fn_update(v_memberId) {
			$("#command").val("update");
			$("#memberId").val(v_memberId);
			$("#frm").attr("action","/_admin/member/memberForm.do");
			$("#frm").submit();
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
		<div class="container">
			<h2 class="con-tit">회원 관리</h2>
			<div class="search-box">
            	<p class="tot-top">총 <span class="colblue">${resultCnt }</span>명</p>
	            <div class="pagenavbox selbox pb20">
		            <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="memberId" id="memberId" value="">
						<input type="hidden" name="command" id="command" value="">
						<fieldset>
							<legend class="blind">검색창</legend>
							<div class="tblselect">
		                        <select id="searchPart" name="searchPart" class="iptwid" onchange="javascript:fn_adminList('1');">
		                            <option value="" <c:if test="${searchVO.searchPart == ''}">selected="selected"</c:if>>소속 전체</option>
		                			<c:forEach var="result" items="${departmentList}" varStatus="status">
			                        	<option value="${result.departmentId }" <c:if test="${result.departmentId == searchVO.searchPart}">selected="selected"</c:if>>${result.departmentName }</option>
	                                </c:forEach>
		                        </select>
								<select name="searchCnd" class="iptwid">
									<option value="0" <c:if test="${searchVO.searchCnd == 0 }">selected</c:if>>전체</option>
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
			<div class="tbl-wrap">
				<table class="tbl01">
					<caption class="blind">회원 목록 테이블</caption>
					<colgroup>
	                    <col style="width: 10%">
	                    <col style="width: 20%">
	                    <col style="width: 20%">
	                    <col style="width:">
	                    <col style="width: 20%">
					</colgroup>
					<thead>
		                <tr>
		                    <th>번호</th>
		                    <th>이름</th>
		                    <th>아이디</th>
		                    <th>소속</th>
		                    <th>관리</th>
		                </tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${resultList }" varStatus="status">
							<tr>
								<td>${result.rownum }</td>
								<td><c:out value="${result.memberName }"/></td>
								<td><c:out value="${result.memberId }"/></td>
								<td><c:out value="${result.departmentName }"/></td>
								<td>
									<a href="javascript:void(0)" onclick="javascript:fn_update('${result.memberId }');" class="control">관리</a>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${resultCnt == 0 }">
							<tr>
								<td colspan="5">
									등록된 회원이 없습니다.
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
            	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_adminList" />
            </div>
		</div>
	</div>