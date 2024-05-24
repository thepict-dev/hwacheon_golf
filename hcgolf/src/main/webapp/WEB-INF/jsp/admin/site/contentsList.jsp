<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_contentsList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/site/contentsList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/site/contentsForm.do");
			$("#frm").submit();
		}
		function fn_update(v_contentsId) {
			$("#command").val("update");
			$("#contentsId").val(v_contentsId);
			$("#frm").attr("action","/_admin/site/contentsForm.do");
			$("#frm").submit();
		}
		function fn_delete(v_contentsId) {
			if(confirm("콘텐츠롤 삭제하시겠습니까?")){
				$("#contentsId").val(v_contentsId);
				$("#frm").attr("action","/_admin/site/contentsDelete.do");
				$("#frm").submit();
			}
		}
		function fn_backup(v_contentsId) {
			window.open("/_admin/site/contentsBakList.do?contentsId="+v_contentsId, "contentsBakList", "width=1040,height=800");
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트관리</a></li>
	            <li><a href="#lnk">콘텐츠 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">콘텐츠 관리</h2>
	        <div class="search-box res">
	        	<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
	        	<div class="pagenavbox mob selbox mb20 sel-chk">
		            <form id="frm" name="frm" action="" method="post">
		            	<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="contentsId" id="contentsId" value="0">
						<input type="hidden" name="command" id="command" value="">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1" <c:if test="${searchVO.searchCnd eq 1}">selected</c:if> >콘텐츠 ID+콘텐츠 이름</option>
		                            <option value="2" <c:if test="${searchVO.searchCnd eq 2}">selected</c:if> >콘텐츠 이름</option>
		                            <option value="3" <c:if test="${searchVO.searchCnd eq 3}">selected</c:if> >콘텐츠 ID</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${searchVO.searchWrd}">
		                        <button type="button" class="btn_search dark-blue" onclick="javascript:fn_contentsList('1');">검색</button>
		                    </div>
		                </fieldset>
		            </form>
	        	</div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">콘텐츠 관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:7%">
	                    <col style="width:15%">
	                    <col style="width:">
	                    <col style="width:9%">
	                    <col style="width:9%">
	                    <col style="width:13%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>ID</th>
	                    <th>이름</th>
	                    <th>타입</th>
	                    <th>스타일</th>
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
			                    <td>${result.contentsId }</td>
			                    <td class="tl"><a href="#lnk" onclick="javascript:fn_update('${result.contentsId}');">${result.contentsName }</a></td>
			                    <td>
			                    	<c:choose>
			                    		<c:when test="${result.contentsType eq 'CON'}">소스코드</c:when>
			                    		<c:when test="${result.contentsType eq 'BBS'}">게시판</c:when>
			                    		<c:when test="${result.contentsType eq 'SRV'}">설문조사</c:when>
			                    		<c:when test="${result.contentsType eq 'URL'}">URL링크</c:when>
			                    		<c:otherwise></c:otherwise>
			                    	</c:choose>
			                    </td>
			                    <td>
			                    	<c:choose>
			                    		<c:when test="${result.contentsStyle eq 'jsp'}">페이지</c:when>
			                    		<c:when test="${result.contentsStyle eq 'css'}">CSS</c:when>
			                    		<c:when test="${result.contentsStyle eq 'js' }">JS</c:when>
			                    		<c:otherwise></c:otherwise>
			                    	</c:choose>
			                    </td>
			                    <td>${result.updDate }</td>
			                    <td><span class="manage-btn backup-btn"><button type="button" onclick="javascript:fn_backup('${result.contentsId}');">백업</button></span></td>
			                    <td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.contentsId}');">수정</button></span></td>
			                    <td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.contentsId}');">삭제</button></span></td>
			                </tr>
		                </c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="9">등록된 콘텐츠가 없습니다.</td>
		                    </tr>
			 			</c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_contentsList" />
            </div>
	    </div>
	</div>