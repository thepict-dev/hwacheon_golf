<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_scheduleList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/event/scheduleList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/event/scheduleForm.do");
			$("#frm").submit();
		}
		function fn_view(v_scheduleId) {
			$("#scheduleId").val(v_scheduleId);
			$("#frm").attr("action","/_admin/event/scheduleView.do");
			$("#frm").submit();
		}
	</script>

	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">예약/일정 관리</a></li>
	            <li><a href="#lnk">일정 목록</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">일정안내</h2>
	        <div class="search-box">
	            <p class="tot-top">총 <span class="colblue">${resultCnt }</span>개</p>
	            <div class="pagenavbox selbox pb20">
	                <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="scheduleId" id="scheduleId" value="">
						<input type="hidden" name="command" id="command" value="">
	                    <fieldset>
	                        <legend class="screen-out">검색창</legend>
	                        <div class="tblselect">
	                            <select name="searchCnd" class="iptwid">
	                                <option value="0" <c:if test="${searchVO.searchCnd == 0 }">selected</c:if>>제목+내용</option>
	                                <option value="1" <c:if test="${searchVO.searchCnd == 1 }">selected</c:if>>제목</option>
	                                <option value="2" <c:if test="${searchVO.searchCnd == 2 }">selected</c:if>>내용</option>
	                            </select>
	                        </div>
	                        <div class="tblsearch">
	                            <input type="text" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd}">
	                            <button type="button" onclick="javascript:fn_scheduleList(1);" class="btn_search dark-blue">검색</button>
	                        </div>
	                    </fieldset>
	                </form>
	            </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">일정 목록 테이블</caption>
	                <colgroup>
	                    <col style="width: 7%">
	                    <col style="width:">
	                    <col style="width: 20%">
	                    <col style="width: 15%">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th>번호</th>
		                    <th>제목</th>
		                    <th>기간</th>
		                    <th>등록일</th>
		                </tr>
	                </thead>
	                <tbody>
						<c:forEach var="result" items="${resultList }" varStatus="status">
			                <tr>
			                    <td>${result.rownum }</td>
			                    <td><a href="#lnk" onclick="javascript:fn_view('${result.scheduleId }');">${result.title }</a></td>
			                    <td>${result.startDate } ~ ${result.endDate }</td>
			                    <td>${result.regDate }</td>
			                </tr>
						</c:forEach>
						<c:if test="${resultCnt == 0 }">
							<tr>
								<td colspan="4">
									등록된 일정이 없습니다.
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
            	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_scheduleList" />
            </div>
	    </div>
	</div>