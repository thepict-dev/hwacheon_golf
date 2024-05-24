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
		    function fn_selectConList(pageNo) {
				$("#pageIndex").val(pageNo);
				$("#frm").attr("action","/_admin/site/selectConList.do");
				$("#frm").submit();
			}
		    function fn_view(v_contentsId) {
		    	window.open("/_admin/site/selectConForm.do?contentsId="+v_contentsId, "selectConView", "width=1040,height=800");
			}
		    function fn_select(v_contentsId, v_contentsName){
		    	$(opener.document).find("#contentsId").val(v_contentsId);
		    	$(opener.document).find("#contentsName").val(v_contentsId+" - "+v_contentsName);
		    	self.close();
		    }
	    </script>
	</head>
	<body>
	    <div class="container type1">
	        <div class="tbl-wrap">
				<form name="frm" id="frm" method="post" action="">
					<div class="search-box mt40">
						<p class="tot-top">총 <span class="colbsdlue">${resultCnt}</span>개</p>
						<div class="pagenavbox selbox pb20">
							<div class="tblselect">
	                            <select name="searchCnd" class="iptwid">
	                                <option value="1" <c:if test="${searchVO.searchCnd eq '1'}">selected="selected"</c:if>>콘텐츠 ID + 이름</option>
	                                <option value="2" <c:if test="${searchVO.searchCnd eq '2'}">selected="selected"</c:if>>콘텐츠 이름</option>
	                                <option value="3" <c:if test="${searchVO.searchCnd eq '3'}">selected="selected"</c:if>>콘텐츠 ID</option>
	                            </select>
	                        </div>
							<div class="tblsearch">
								<input type="text" id="searchWrd" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd}" onkeydown="javascript:if(event.keyCode==13) {fn_selectConList('1');}">
			                    <button type="button" class="btn_search gray" onclick="javascript:fn_selectConList('1');">검색</button>
							</div>
						</div>
					</div>
		            <table class="tbl01 min-type3">
		                <caption class="blind">콘텐츠 선택 테이블</caption>
		                <colgroup>
		                    <col style="width:20%">
		                    <col style="">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                    <col style="width:18%">
		                    <col style="width:9%">
		                    <col style="width:9%">
		                </colgroup>
		                <thead>
		                <tr>
		                    <th>콘텐츠 ID</th>
		                    <th>콘텐츠 이름</th>
		                    <th>타입</th>
		                    <th>스타일</th>
		                    <th>최종수정일</th>
		                    <th>소스보기</th>
		                    <th>선택</th>
		                </tr>
		                </thead>
		                <tbody>
							<c:forEach var="result" items="${resultList}" varStatus="status">
				                <tr>
				                    <td>${result.contentsId }</td>
				                    <td class="tl">${result.contentsName }</td>
				                    <td>
					                    <c:choose>
					                    	<c:when test="${result.contentsType eq 'BBS' }">
					                    		게시판
					                    	</c:when>
					                    	<c:when test="${result.contentsType eq 'URL' }">
					                    		URL링크
					                    	</c:when>
					                    	<c:otherwise>
					                    		소스코드
					                    	</c:otherwise>
					                    </c:choose>
				                    </td>
				                    <td>
			                    		<c:choose>
				                    		<c:when test="${result.contentsStyle eq 'css' }">
				                    			CSS
				                    		</c:when>
				                    		<c:when test="${result.contentsStyle eq 'js' }">
				                    			JS
				                    		</c:when>
				                    		<c:when test="${result.contentsStyle eq 'jsp' }">
				                    			페이지
				                    		</c:when>
				                    	</c:choose>
				                    </td>
				                    <td>${result.updDate }</td>
				                    <td><button type="button" class="btn" onclick="javascript:fn_view('${result.contentsId}')">보기</button></td>
				                    <td><button type="button" class="btn" onclick="javascript:fn_select('${result.contentsId}','${result.contentsName}')">선택</button></td>
				                </tr>
			                </c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
			                    <tr>
			                        <td colspan="7">등록된 리스트가 없습니다.</td>
			                    </tr>
				 			</c:if>
		                </tbody>
		            </table>
		            <div class="pager">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_selectConList" />
		            </div>
					<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
					<input type="hidden" name="rownum" id="rownum" value="0">
	            </form>
	        </div>
	    </div>
	</body>
</html>