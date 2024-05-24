<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	
	<script>
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action", "/_admin/survey/surveyForm.do");
			$("#frm").submit();
		}
		function fn_update(v_surveyId) {
			$("#command").val("update");
			$("#surveyId").val(v_surveyId);
			$("#frm").attr("action", "/_admin/survey/surveyForm.do");
			$("#frm").submit();
		}
		function fn_result(v_surveyId) {
			$("#command").val("result");
			$("#surveyId").val(v_surveyId);
			$("#frm").attr("action", "/_admin/survey/surveyResult.do");
			$("#frm").submit();
		}
		function fn_rspdent(v_surveyId) {
			$("#command").val("rspdent");
			$("#surveyId").val(v_surveyId);
			$("#frm").attr("action", "/_admin/survey/rspdentList.do");
			$("#frm").submit();
		}
		function fn_view(v_surveyId){
			$("#command").val("view");
			$("#surveyId").val(v_surveyId);
			$("#frm").attr("action", "/_admin/survey/surveyView.do");
			$("#frm").submit();
		}
		function fn_delete(v_surveyId) {
			if (confirm("설문을 삭제하시겠습니까?")) {
				$("#surveyId").val(v_surveyId);
				$("#frm").attr("action", "/_admin/survey/surveyDelete.do");
				$("#frm").submit();	
			}
		}
		function fn_surveyList(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action", "/_admin/survey/surveyList.do");
			$("#frm").submit();
		}
		function fn_selectList(){
			if ($("#searchWrd").val() == "" && $("#searchCnd").val() == ""){
				alert("검색어를 입력해 주세요.");
				$("#searchWrd").focus();
				return;
			}
			$("#searchFrm").attr("action", "/_admin/survey/surveyList.do");
			$("#searchFrm").submit();
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
	        	<div class="pagenavbox selbox pb20">
		            <form id="searchFrm" name="searchFrm" action="" method="post">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1" <c:if test="${searchVO.searchCnd eq 1}">selected</c:if> >설문 ID+설문 제목</option>
		                            <option value="2" <c:if test="${searchVO.searchCnd eq 2}">selected</c:if> >설문 제목</option>
		                            <option value="3" <c:if test="${searchVO.searchCnd eq 3}">selected</c:if> >설문 ID</option>
		                            <option value="4" <c:if test="${searchVO.searchCnd eq 4}">selected</c:if> >설문 내용</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${searchVO.searchWrd}">
		                        <button type="button" class="btn_search gray" onclick="javascript:fn_selectList('1');">검색</button>
		                    </div>
		                </fieldset>
		            </form>
	        	</div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01 min-type1 ">
	                <caption class="blind">설문 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:7%">
	                    <col style="width:15%">
	                    <col style="width: ">
	                    <col style="width:14%">
	                    <col style="width:11%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
			            <col style="width:7%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>설문 ID</th>
	                    <th>제목</th>
                   		<th>스킨 ID</th>
	                    <th>설문 기간</th>
                   		<th>결과</th>
                   		<th>응답자</th>
	                    <th>질문 관리</th>
	                    <th>수정</th>
	                    <th>삭제</th>
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
			                    <td class="tl">
			                    	${result.surveyTitle}
			                    </td>
			                    <td>
			                    	${result.skinId}
			                    </td>
			                    <td>
			                    	${result.startDate}<br>${result.endDate}
			                    </td>
	                    		<td>
			                    	<div><span class="manage-btn res-btn"><button type="button" onclick="javascript:fn_result('${result.surveyId}');">결과</button></span></div>
			                    </td>
	                    		<td>
			                    	<div><span class="manage-btn resp-btn"><button type="button" onclick="javascript:fn_rspdent('${result.surveyId}');">응답자</button></span></div>
			                    </td>
			                    <td>
			                    	<div><span class="manage-btn manag-btn"><button type="button" onclick="javascript:fn_view('${result.surveyId}');">질문수정</button></span></div>
			                    </td>
			                    <td>
			                    	<div><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.surveyId}');">수정</button></span></div>
			                    </td>
			                    <td>
			                    	<div><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.surveyId}')">삭제</button></span></div>
			                    </td>
			                </tr>
		                </c:forEach>
		                <c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="10">등록된 설문이 없습니다.</td>
		                    </tr>
	                    </c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
               <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_surveyList" />
            </div>
            <form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex}">
				<input type="hidden" name="searchCnd" id="searchCnd" value="${searchVO.searchCnd}">
				<input type="hidden" name="searchWrd" id="searchWrd" value="${searchVO.searchWrd}">
				<input type="hidden" name="surveyId" id="surveyId" value="">
				<input type="hidden" name="command" id="command" value="">
            </form>
	    </div>
	</div>