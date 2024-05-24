<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_skinList(pageNo) {
			$("#pageIndex").val(pageNo);
			if ( $("#skinType").val() == "BBS" ){
				$("#frm").attr("action","/_admin/board/skinList.do");
			} else if ( $("#skinType").val() == "SRV" ){
				$("#frm").attr("action","/_admin/survey/skinList.do");
			}
			$("#frm").submit();
		}
		function fn_selectList(){
			if ($("#searchWrd").val() == "" && $("#searchCnd").val() == ""){
				alert("검색어를 입력해 주세요.");
				$("#searchWrd").focus();
				return;
			}
			if ( $("#skinType").val() == "BBS" ){
				$("#searchFrm").attr("action","/_admin/board/skinList.do");
			} else if ( $("#skinType").val() == "SRV" ){
				$("#searchFrm").attr("action","/_admin/survey/skinList.do");
			}
			$("#searchFrm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			if ( $("#skinType").val() == "BBS" ){
				$("#frm").attr("action","/_admin/board/skinForm.do");
			} else if ( $("#skinType").val() == "SRV" ){
				$("#frm").attr("action","/_admin/survey/skinForm.do");
			}
			$("#frm").submit();
		}
		function fn_update(v_skinId) {
			$("#command").val("update");
			$("#skinId").val(v_skinId);
			if ( $("#skinType").val() == "BBS" ){
				$("#frm").attr("action","/_admin/board/skinForm.do");
			} else if ( $("#skinType").val() == "SRV" ){
				$("#frm").attr("action","/_admin/survey/skinForm.do");
			}
			$("#frm").submit();
		}
		function fn_delete(v_skinId) {
			if(confirm("스킨을 삭제하시겠습니까?")){
				$("#skinId").val(v_skinId);
				if ( $("#skinType").val() == "BBS" ){
					$("#frm").attr("action","/_admin/board/skinDelete.do");
				} else if ( $("#skinType").val() == "SRV" ){
					$("#frm").attr("action","/_admin/survey/skinDelete.do");
				}
				$("#frm").submit();
			}
		}
		function fn_backup(v_skinId) {
			window.open('/_admin/${skinType eq "SRV" ? "survey":"board"}/skinBakList.do?skinId='+v_skinId+'&skinType=${skinType}', "skinBakList", "width=1040,height=800");
		}
		
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <c:if test='${skinType ne "SRV"}'>
	            	<li><a href="#lnk">게시판 관리</a></li>
	            </c:if>
	            <c:if test='${skinType eq "SRV"}'>
	            	<li><a href="#lnk">설문 관리</a></li>
	            </c:if>
	            <li><a href="#lnk">스킨 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">스킨 관리</h2>
	        <div class="search-box res">
	        	<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
	        	<div class="pagenavbox mob selbox mb20 sel-chk">
		            <form id="searchFrm" name="searchFrm" action="" method="post">
		                <fieldset>
		                    <legend class="blind">검색창</legend>
							<input type="hidden" name="skinType" id="skinType" value="${skinType}">
		                    <div class="tblselect">
		                        <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="1" <c:if test="${searchVO.searchCnd eq 1}">selected</c:if> >스킨 ID+스킨이름</option>
		                            <option value="2" <c:if test="${searchVO.searchCnd eq 2}">selected</c:if> >스킨이름</option>
		                            <option value="3" <c:if test="${searchVO.searchCnd eq 3}">selected</c:if> >스킨 ID</option>
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
	            <table class="tbl01">
	                <caption class="blind">스킨 관리 목록 테이블</caption>
	                <colgroup>
	                    <col style="width: 7%">
	                    <col style="width: 20%">
	                    <col style="width:">
	                    <col style="width: 13%">
	                    <col style="width: 7%">
	                    <col style="width: 7%">
	                    <col style="width: 7%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>SKIN ID</th>
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
			                    <td>${result.skinId }</td>
			                    <td class="tl"><a href="#lnk" onclick="javascript:fn_update('${result.skinId}');">${result.skinName }</a></td>
			                    <td>${result.updDate }</td>
			                    <td><span class="manage-btn backup-btn"><button type="button" onclick="javascript:fn_backup('${result.skinId}');">백업</button></span></td>
			                    <td><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.skinId}');">수정</button></span></td>
			                    <td><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.skinId}');">삭제</button></span></td>
			                </tr>
		                </c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
		                    <tr>
		                        <td colspan="7">등록된 스킨이 없습니다.</td>
		                    </tr>
			 			</c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_skinList" />
            </div>
			<form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
				<input type="hidden" name="searchCnd" id="searchCnd" value="${searchVO.searchCnd}">
				<input type="hidden" name="searchWrd" id="searchWrd" value="${searchVO.searchWrd}">
				<input type="hidden" name="skinId" id="skinId">
				<input type="hidden" name="skinType" id="skinType" value="${skinType}">
				<input type="hidden" name="command" id="command">
            </form>
	    </div>
	</div>