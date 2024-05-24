<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ page import = "egovframework.breeze.code.service.CodeVO"%>
<%@ page import = "egovframework.breeze.code.web.CodeBundle"%>
<%@ page import = "java.util.*"%>
<%
	CodeBundle cb = new CodeBundle(request);
%>


	<script>
		function fn_selectList(){
			if ($("#searchWrd").val() == ""){
				alert("검색어를 입력해 주세요.");
				$("#searchWrd").focus();
				return;
			}
			$("#searchFrm").attr("action", "/_admin/popup/popupList.do");
			$("#searchFrm").submit();
		}
		function fn_popupList(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action", "/_admin/popup/popupList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action", "/_admin/popup/popupForm.do");
			$("#frm").submit();
		}
		function fn_update(v_popupId){
			$("#command").val("update");
			$("#popupId").val(v_popupId);
			$("#frm").attr("action", "/_admin/popup/popupForm.do");
			$("#frm").submit();
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">메인 관리</a></li>
	            <li><a href="#lnk">팝업 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">팝업 관리</h2>
			<div class="search-box res">
            	<p class="tot-top">총 <span class="colblue">${resultCnt }</span>개</p>
		        <div class="pagenavbox mob selbox mb20 sel-chk">
		            <form id="searchFrm" name="searchFrm" action="" method="post">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="0">팝업 제목</option>
		                        </select>
		                    </div>
		                    <div class="tblsearch">
		                        <input id="searchWrd" name="searchWrd" type="text" title="검색어 입력" value="${searchVO.searchWrd}">
		                        <button type="button" class="btn_search dark-blue" onclick="javascript:fn_selectList();">검색</button>
		                    </div>
		                </fieldset>
		            </form>
		        </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01 min-type2">
	                <caption class="blind">팝업 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:7%">
	                    <col style="width:10%">
	                    <col style="width:*">
	                    <col style="width:13%">
	                    <col style="width:13%">
	                    <col style="width:8%">
	                    <col style="width:8%">
	                    <col style="width:13%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>카테고리</th>
	                    <th>제목</th>
	                    <th>썸네일</th>
	                    <th>게시 기간</th>
	                    <th>게시 여부</th>
	                    <th>게시 순서</th>
	                    <th>등록일</th>
	                </tr>
	                </thead>
	                <tbody>
	                <c:forEach var="result" items="${resultList}" varStatus="status">
		                <tr>
		                    <td>${result.rownum }</td>
		                    <td>
								<%
								List<CodeVO> codeList = cb.getCodeList("CODE_000000000000011");
								if(codeList.size() > 0){
                                	for(int i=0; i<codeList.size(); i++) {
								%>
									<c:set var="codeId" value="<%=codeList.get(i).getCodeId() %>"/>
									<c:if test="${result.category eq codeId}"><%=codeList.get(i).getCodeName() %></c:if>
								<%
									}
								}
								%>
		                    </td>
		                    <td class="tl dot"><a href="#" onclick="javascript:fn_update('${result.popupId}');">${result.popupTitle}</a></td>
		                    <td>
			                    <p class="thumbnail">
			                    	<c:if test="${result.atchFileId ne null && result.atchFileId ne '' }">
				                    	<img src="<c:url value='/_cmm/fms/getImage.do'/>?atchFileId=<c:out value="${result.atchFileId}"/>&thumbnail=true" alt="썸네일">
	                                </c:if>
	                                <c:if test="${result.atchFileId eq null || result.atchFileId eq '' }">
	                                	<img src="/_admin/img/no_img.jpg" alt="이미지가 없습니다."/>
	                                </c:if>
			                    </p>
			                </td>
		                    <td>${result.startDate }<br>~<br>${result.endDate }</td>
		                    <td>
		                    	<c:if test="${result.viewFlag eq 'Y'}">출력</c:if>
		                    	<c:if test="${result.viewFlag eq 'N'}">미출력</c:if>
		                    </td>
		                    <td>${result.popupOrder }</td>
		                    <td>${result.regDate }</td>
		                </tr>
	                </c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
	                    <tr>
	                        <td colspan="8">등록된 팝업이 없습니다.</td>
	                    </tr>
		 			</c:if>
	                </tbody>
	            </table>
	            <form name="frm" id="frm" method="post" action="">
					<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
					<input type="hidden" name="searchWrd"  value="<c:out value='${searchVO.searchWrd}'/>">
					<input type="hidden" name="searchCate"  value="<c:out value='${searchVO.searchCate}'/>">
					<input type="hidden" name="popupId" id="popupId" value="0">
					<input type="hidden" name="command" id="command" value="">
	            </form>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
                <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_popupList" />
            </div>
	    </div>
	</div>