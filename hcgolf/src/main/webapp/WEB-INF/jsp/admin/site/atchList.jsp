<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_atchList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#searchFrm").attr("action","/_admin/site/atchList.do");
			$("#searchFrm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#searchFrm").attr("action","/_admin/site/atchForm.do");
			$("#searchFrm").submit();
		}
		function fn_update(v_atchId) {
			$("#command").val("update");
			$("#atchId").val(v_atchId);
			$("#searchFrm").attr("action","/_admin/site/atchForm.do");
			$("#searchFrm").submit();
		}
		function fn_delete(v_atchId) {
			if(confirm("첨부파일 게시글을 삭제하시겠습니까?")){
				$("#atchId").val(v_atchId);
				$("#searchFrm").attr("action","/_admin/site/atchDelete.do");
				$("#searchFrm").submit();
			}
		}
		function fn_view(v_atchId) {
			$("#atchId").val(v_atchId);
			$("#searchFrm").attr("action","/_admin/site/atchView.do");
			$("#searchFrm").submit();
		}
	</script>

	<div class="container-wrap">
		<div class="breadcrumbs">
			<ul>
				<li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
				<li><a href="#lnk">사이트관리</a></li>
				<li><a href="#lnk">첨부파일 관리</a></li>
			</ul>
		</div>
		<div class="container">
			<h2 class="con-tit">첨부파일 관리</h2>
			<div class="search-box res">
	        	<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
	        	<div class="pagenavbox mob selbox mb20 sel-chk">
		            <form id="searchFrm" name="searchFrm" action="" method="post">
		            	<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="atchId" id="atchId" value="0">
						<input type="hidden" name="command" id="command" value="">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1" <c:if test="${searchVO.searchCnd eq 1}">selected</c:if> >제목 + 내용</option>
		                            <option value="2" <c:if test="${searchVO.searchCnd eq 2}">selected</c:if> >제목</option>
		                            <option value="3" <c:if test="${searchVO.searchCnd eq 3}">selected</c:if> >내용</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${searchVO.searchWrd}">
		                        <button type="button" class="btn_search dark-blue" onclick="javascript:fn_atchList('1');">검색</button>
		                    </div>
		                </fieldset>
		            </form>
	        	</div>
	        </div>
			<div class="tbl-wrap">
				<table class="tbl01">
					<caption class="blind">첨부파일 관리 목록 테이블</caption>
					<colgroup>
						<col style="width:7%">
	                    <col style="width:">
	                    <col style="width:14%">
	                    <col style="width:7%">
	                    <col style="width:7%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>첨부파일 제목</th>
							<th>최종수정일</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${resultCnt == 0 }">
							<tr>
								<td colspan="5">등록된 첨부파일 목록이 없습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
								<td>${result.rownum }</td>
								<td class="tl"><a href="javascript:void(0);" onclick="javascript:fn_view('${result.atchId}');">${result.atchTitle }</a></td>
								<td>${result.updDate }</td>
								<td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.atchId}');">수정</button></span></td>
								<td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.atchId}');">삭제</button></span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			<div class="btn-box">
				<a href="javascript:void(0);" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
			</div>
			<div class="pager">
                <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_atchList" />
			</div>
		</div>
	</div>