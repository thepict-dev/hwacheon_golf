<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_departmentList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/member/departmentList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/member/departmentForm.do");
			$("#frm").submit();
		}
		function fn_update(v_departmentId) {
			$("#command").val("update");
			$("#departmentId").val(v_departmentId);
			$("#frm").attr("action","/_admin/member/departmentForm.do");
			$("#frm").submit();
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
	    <div class="container">
	        <h2 class="con-tit">소속 관리</h2>
	        <div class="search-box">
	            <p class="tot-top">총 <span class="colblue">${resultCnt }</span>개</p>
	            <div class="pagenavbox selbox pb20">
	                <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="departmentId" id="departmentId" value="">
						<input type="hidden" name="command" id="command" value="">
	                    <fieldset>
	                        <legend class="screen-out">검색창</legend>
	                        <div class="tblsearch">
	                            <input type="text" name="searchWrd" title="검색어 입력" placeholder="업체명 검색" value="${searchVO.searchWrd}">
	                            <button type="button" onclick="javascript:fn_departmentList(1);" class="btn_search dark-blue">검색</button>
	                        </div>
	                    </fieldset>
	                </form>
	            </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">소속관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width: 10%">
	                    <col style="width:">
	                    <col style="width: 20%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th>번호</th>
		                    <th>업체명</th>
		                    <th>등록일</th>
		                </tr>
	                </thead>
	                <tbody>
						<c:forEach var="result" items="${resultList }" varStatus="status">
							<tr>
								<td>${result.rownum }</td>
								<td class="tl"><a href="javascript:void(0)" onclick="javascript:fn_update('${result.departmentId }');"><c:out value="${result.departmentName }"/></a></td>
								<td><c:out value="${result.regDate }"/></td>
							</tr>
						</c:forEach>
						<c:if test="${resultCnt == 0 }">
							<tr>
								<td colspan="3">
									등록된 소속이 없습니다.
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
            	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_departmentList" />
            </div>
	    </div>
	</div>