<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_layoutList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/site/layoutList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/site/layoutForm.do");
			$("#frm").submit();
		}
		function fn_update(v_layoutId) {
			$("#command").val("update");
			$("#layoutId").val(v_layoutId);
			$("#frm").attr("action","/_admin/site/layoutForm.do");
			$("#frm").submit();
		}
		function fn_delete(v_layoutId) {
			if(confirm("레이아웃을 삭제하시겠습니까?")){
				$("#layoutId").val(v_layoutId);
				$("#frm").attr("action","/_admin/site/layoutDelete.do");
				$("#frm").submit();
			}
		}
		function fn_backup(v_layoutId) {
			window.open("/_admin/site/layoutBakList.do?layoutId="+v_layoutId, "layoutBakList", "width=1040,height=800");
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트관리</a></li>
	            <li><a href="#lnk">레이아웃 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">레이아웃 관리</h2>
	        <div class="search-box res">
	        	<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
	        	<div class="pagenavbox mob selbox mb20 sel-chk">
		            <form id="frm" name="frm" action="" method="post">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="layoutId" id="layoutId" value="0">
						<input type="hidden" name="command" id="command" value="">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1" <c:if test="${searchVO.searchCnd eq 1}">selected</c:if> >레이아웃 ID+레이아웃 이름</option>
		                            <option value="2" <c:if test="${searchVO.searchCnd eq 2}">selected</c:if> >레이아웃 이름</option>
		                            <option value="3" <c:if test="${searchVO.searchCnd eq 3}">selected</c:if> >레이아웃 ID</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${searchVO.searchWrd}">
		                        <button type="button" class="btn_search dark-blue" onclick="javascript:fn_layoutList('1');">검색</button>
		                    </div>
		                </fieldset>
		            </form>
	        	</div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">레이아웃 관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:7%">
	                    <col style="width:17%">
	                    <col style="width:">
	                    <col style="width:17%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th>번호</th>
		                    <th>레이아웃 ID</th>
		                    <th>이름</th>
		                    <th>최종수정일</th>
	                    	<th>백업</th>
		                    <th>수정</th>
		                    <th>삭제</th>
		                </tr>
	                </thead>
	                <tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
			                <tr>
			                    <td>${result.rownum }</td>
			                    <td>${result.layoutId }</td>
			                    <td class="tl"><a href="#lnk" onclick="javascript:fn_update('${result.layoutId}');">${result.layoutName }</a></td>
			                    <td>${result.updDate }</td>
			                    <td><span class="manage-btn backup-btn"><button type="button" onclick="javascript:fn_backup('${result.layoutId}');">백업</button></span></td>
			                    <td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.layoutId}');">수정</button></span></td>
			                    <td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.layoutId}');">삭제</button></span></td>
			                </tr>
		                </c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="7">등록된 레이아웃이 없습니다.</td>
		                    </tr>
			 			</c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_layoutList" />
            </div>
	    </div>
	</div>