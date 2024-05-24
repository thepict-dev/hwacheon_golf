<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
	<head lang="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>콘텐츠 목록 | 관리자</title>
	    <link rel="stylesheet" href="/_admin/css/common.css">
	    <link rel="stylesheet" href="/_admin/css/layout.css">
	    <link rel="stylesheet" href="/_admin/css/datepicker.css">
	    <script src="/_admin/js/jquery-3.4.1.min.js"></script>
	    <script src="/_admin/js/jquery-ui-datepicker.min.js"></script>
	    <script src="/_admin/js/layout.js"></script>
	    <script src="/_admin/js/sub.js"></script>
	    <script>
		    function fn_select(v_command, v_menuId, v_menuName){
		    	if(v_command == "login"){
			    	$(opener.document).find("#loginMenuId").val(v_menuId);
			    	$(opener.document).find("#loginMenuName").val(v_menuName);
		    	}else{
			    	$(opener.document).find("#menuId").val(v_menuId);
			    	$(opener.document).find("#menuName").val(v_menuName);
		    	}
		    	self.close();
		    }
	    </script>
	</head>
	<body>
	    <div class="container type1">
	        <div class="tbl-wrap">
				<form name="frm" id="frm" method="post" action="">
		            <table class="tbl01 min-type3">
		                <caption class="blind">콘텐츠 선택 테이블</caption>
		                <colgroup>
		                    <col style="width:20%">
		                    <col style="">
		                    <col style="width:25%">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                </colgroup>
		                <thead>
		                <tr>
		                    <th>메뉴 ID</th>
		                    <th>메뉴 Name</th>
		                    <th>메뉴 Title</th>
		                    <th>메뉴 순서</th>
		                    <th>사용여부</th>
		                    <th>선택</th>
		                </tr>
		                </thead>
		                <tbody>
							<c:forEach var="result" items="${resultList}" varStatus="status">
			                    <tr>
			                        <td>${result.menuId }</td>
			                        <td class="tl">
			                        	<c:if test="${result.menuDepth eq 'dep1'}">①</c:if>
			                        	<c:if test="${result.menuDepth eq 'dep2'}">&nbsp;└②</c:if>
			                        	<c:if test="${result.menuDepth eq 'dep3'}">&nbsp;&nbsp;&nbsp;└③</c:if>
			                        	<c:if test="${result.menuDepth eq 'dep4'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└④</c:if>
			                        	<c:if test="${result.menuDepth eq 'dep5'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└⑤</c:if>
			                        	<c:if test="${result.menuDepth eq 'dep6'}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└⑥</c:if>
			                        	${result.menuName }
			                        </td>
			                        <td class="tl">${result.menuTitle }</td>
			                        <td>${result.menuNo }</td>
			                        <td>${result.menuUseFlag }</td>
				                    <td><button type="button" class="btn" onclick="javascript:fn_select('${searchVO.command }','${result.menuId}','${result.menuName}')">선택</button></td>
			                    </tr>
			                </c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
			                    <tr>
			                        <td colspan="6">등록된 메뉴가 없습니다.</td>
			                    </tr>
				 			</c:if>
		                </tbody>
		            </table>
	            </form>
	        </div>
	    </div>
	</body>
</html>