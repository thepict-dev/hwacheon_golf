<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	<script>
		function fn_bbsMasterList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsMasterList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/board/bbsMasterForm.do");
			$("#frm").submit();
		}
		function fn_update(v_bbsId) {
			$("#command").val("update");
			$("#bbsId").val(v_bbsId);
			$("#frm").attr("action","/_admin/board/bbsMasterForm.do");
			$("#frm").submit();
		}
		function fn_item(v_bbsId) {
			$("#bbsId").val(v_bbsId);
			$("#frm").attr("action","/_admin/board/bbsItemView.do");
			$("#frm").submit();
		}
		function fn_cate(v_bbsId) {
			$("#bbsId").val(v_bbsId);
			$("#frm").attr("action","/_admin/board/bbsCateView.do");
			$("#frm").submit();
		}
		function fn_delete(v_bbsId) {
			if(confirm("게시판을 삭제하시겠습니까?")){
				$("#bbsId").val(v_bbsId);
				$("#frm").attr("action","/_admin/board/bbsMasterDelete.do");
				$("#frm").submit();
			}
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">게시판 관리</h2>
        	<div class="search-box res">
	            <p class="tot-top">총 <span class="colblue">${resultCnt}</span>개</p>
	            <div class="pagenavbox mob selbox mb20 sel-chk">
	                <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="bbsId" id="bbsId" value="0">
						<input type="hidden" name="command" id="command" value="">
	                    <fieldset>
	                        <legend class="blind">검색창</legend>
	                        <div class="tblselect">
	                            <select name="searchCnd" class="iptwid">
	                                <option value="0" <c:if test="${searchVO.searchCnd == '0' || searchVO.searchCnd == ''}">selected="selected"</c:if>>게시판 ID+게시판명</option>
	                                <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if>>게시판 ID</option>
	                                <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if>>게시판명</option>
	                            </select>
	                        </div>
	                        <div class="tblsearch">
	                            <input type="text" id="searchWrd" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd}" onkeydown="javascript:if(event.keyCode==13) {fn_bbsMasterList('1');}">
	                            <button type="button" class="btn_search dark-blue" onclick="javascript:fn_bbsMasterList('1');">검색</button>
	                        </div>
	                    </fieldset>
	                </form>
	            </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">게시판 관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width:6%">
	                    <col style="width:15%">
	                    <col style="width:*">
	                    <col style="width:14%">
	                    <col style="width:10%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                    <col style="width:7%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>BBS ID</th>
	                    <th>게시판명</th>
	                    <th>SKIN ID</th>
	                    <th>등록일</th>
	                    <th>항목</th>
	                    <th>카테고리</th>
	                    <th>수정</th>
	                    <th>삭제</th>
	                </tr>
	                </thead>
	                <tbody>
						<c:forEach var="result" items="${resultList}" varStatus="status">
			                <tr>
			                    <td>${result.rownum }</td>
			                    <td>${result.bbsId }</td>
			                    <td class="tl">${result.bbsNm } <span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/_admin/board/bbsList.do?bbsId=${result.bbsId }'">미리보기</button></span></td>
			                    <td>${result.skinId }</td>
			                    <td>${result.frstRegisterPnttm }</td>
			                    <td><span class="manage-btn bbsItem-btn"><button type="button" onclick="javascript:fn_item('${result.bbsId }')">항목관리</button></span></td>
			                    <td><span class="manage-btn bbsCate-btn"><button type="button" onclick="javascript:fn_cate('${result.bbsId }')">카테고리관리</button></span></td>
			                    <td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.bbsId }')">수정</button></span></td>
			                    <td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.bbsId }')">삭제</button></span></td>
			                </tr>
		                </c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="9">등록된 게시판이 없습니다.</td>
		                    </tr>
			 			</c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_bbsMasterList" />
            </div>
	    </div>
	</div>