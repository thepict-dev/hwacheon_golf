<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	<script>
		function fn_list() {
			$("#frm").attr("action", "/_admin/survey/surveyList.do");
			$("#frm").submit();
		}
		function fn_selectList(){
			if ($("#searchWrd").val() == "" && $("#searchCnd").val() == ""){
				alert("검색어를 입력해 주세요.");
				$("#searchWrd").focus();
				return;
			}
			$("#searchFrm").attr("action", "/_admin/survey/rspdentList.do");
			$("#searchFrm").submit();
		}
		function fn_result(v_rspdentId){
			$("#rspdentId").val(v_rspdentId);
			$("#frm").attr("action", "/_admin/survey/surveyResult.do");
			$("#frm").submit();
		}
		function fn_rspdentList(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action", "/_admin/survey/rspdentList.do");
			$("#frm").submit();
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">설문 관리</a></li>
				<li><a href="#lnk">설문 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
        	<h2 class="con-tit">설문 관리</h2>
        	<div class="search-box">
        		<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
		        <div class="pagenavbox selbox pb20 ">
		            <form id="searchFrm" name="searchFrm" action="" method="post">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1">아이디</option>
		                            <option value="1">이름</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${search.searchWrd}">
		                        <button type="button" class="btn_search gray" onclick="javascript:fn_selectList();">검색</button>
		                    </div>
		                </fieldset>
		            </form>
		        </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">응답자 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:5%">
	                    <col style="width:14%">
	                    <col style="width:">
	                    <col style="width:">
	                    <col style="width:10%">
			            <col style="width:7%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>SRV ID</th>
	                    <th>아이디</th>
	                    <th>이름</th>
	                    <th>응답일</th>
	                    <th>설문 결과</th>
	                </tr>
	                </thead>	
	                <tbody>
	                	<c:forEach var="result" items="${resultList}" varStatus="status">
			                <tr>
			                    <td>
			                    	${result.rownum}
			                    </td>
			                    <td>
			                    	${result.surveyId}
			                    </td>
			                    <td>
			                    	-<%-- ${result.mberId} --%>
			                    </td>
			                    <td>
			                    	-
			                    </td>
			                    <td>
			                    	${result.regDate.replaceAll(" [0-9][0-9]:[0-9][0-9]:[0-9][0-9].[0-9]", "")}
			                    </td>
			                    <td>
			                    	<div><span class="manage-btn bbsItem-btn"><button type="button" onclick="javascript:fn_result('${result.rspdentId }')">결과</button></span></div>
			                    </td>
			                </tr>
		                </c:forEach>
		                <c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="6">응답자가 없습니다.</td>
		                    </tr>
	                    </c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
	            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
	        </div>
            <div class="pager">
               <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_rspdentList" />
            </div>
            <form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex}">
				<input type="hidden" name="searchWrd" id="searchWrd" value="${searchVO.searchWrd}">
				<input type="hidden" name="surveyId" id="surveyId" value="${searchVO.surveyId }">
				<input type="hidden" name="rspdentId" id="rspdentId" value="">
				<input type="hidden" name="command" id="command" value="rspond">
            </form>
	    </div>
	</div>