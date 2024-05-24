<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
	<head lang="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>애널리틱스 백업 | 관리자</title>
	    <link rel="stylesheet" href="/_admin/css/common.css">
	    <link rel="stylesheet" href="/_admin/css/layout.css">
	    <link rel="stylesheet" href="/_admin/css/datepicker.css">
	    <script src="/_admin/js/jquery-3.4.1.min.js"></script>
	    <script src="/_admin/js/jquery-ui-datepicker.min.js"></script>
	    <script src="/_admin/js/layout.js"></script>
	    <script src="/_admin/js/sub.js"></script>
	    <script>
		    function fn_analyticsBakList(pageNo) {
				$("#pageIndex").val(pageNo);
				$("#frm").attr("action","/_admin/site/analyticsBakList.do");
				$("#frm").submit();
			}
		    function fn_view(v_bakAnalyticsId) {
		    	window.open("/_admin/site/analyticsBakForm.do?bakAnalyticsId="+v_bakAnalyticsId, "analyticsBakView", "width=1040,height=800");
			}
	    </script>
	</head>
	<body>
		<div class="container type1">
			<div class="tbl-wrap">
	            <table class="tbl01 min-type3">
	                <caption class="blind">애널리틱스 백업 테이블</caption>
	                <colgroup>
	                    <col style="width: 10%">
	                    <col style="width:">
	                    <col style="width:13%">
	                    <col style="width:25%">
	                    <col style="width:12%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>제목</th>
	                    <th>작업자</th>
	                    <th>백업일</th>
	                    <th>소스보기</th>
	                </tr>
	                </thead>
	                <tbody>
	                	<c:forEach var="result" items="${resultList}" varStatus="status">
	                		<tr>
	                			<td>${result.rownum }</td>
	                			<td class="tl">${result.analyticsName }</td>
	                			<td>${result.updId }</td>
	                			<td>${result.updDate }</td>
	                			<td><button type="button"  class="btn" onclick="javascript:fn_view('${result.bakAnalyticsId}')">보기</button></td>
	                		</tr>
	                	</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
                        		<td colspan="5">등록된 리스트가 없습니다.</td>
		                    </tr>
			 			</c:if>
	                </tbody>
				</table>
				<div class="pager">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_analyticsBakList" />
				</div>
			    <div class="btn-box">
	                <a href="#lnk" onclick="self.close();" class="tbl-btn blue">닫기</a>
	            </div>
			</div>
		</div>
		<form name="frm" id="frm" method="post" action="">
			<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
			<input type="hidden" name="analyticsId" id="analyticsId" value="<c:out value='${searchVO.analyticsId}'/>">
			<input type="hidden" name="bakAnalyticsId" id="bakAnalyticsId" value="0">
			<input type="hidden" name="rownum" id="rownum" value="0">
		</form>
	</body>
</html>