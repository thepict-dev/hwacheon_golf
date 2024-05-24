<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_search(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#searchCnd").val("0");
			$("#searchWrd").val("");
			$("#frm").attr("action","/_admin/board/bbsList.do");
			$("#frm").submit();
		}
		function fn_bbsList(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsList.do");
			$("#frm").submit();
		}
		function fn_insert() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/board/bbsForm.do");
			$("#frm").submit();
		}
		
		function fn_view(nttId) {
			$("#subForm" + nttId).submit();
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시물 관리</a></li>
	            <li><a href="#lnk">${brdMstrVO.bbsNm}</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">${brdMstrVO.bbsNm}</h2>
	        <div class="search-box">
	            <p class="tot-top">총 <span class="colblue">${resultCnt}</span>개</p>
	            <div class="pagenavbox selbox pb20">
	                <form name="frm" id="frm" method="post" action="">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
						<%-- <input type="hidden" name="bbsId" value="<c:out value='${brdMstrVO.bbsId}'/>" /> --%>
						<input type="hidden" name="command" id="command" value="">
	                    <fieldset>
	                        <legend class="blind">검색창</legend>
	                        <div class="tblselect">
	                            <select name="bbsId" class="iptwid" onchange="javascript:fn_search('1');">
									<c:forEach var="result" items="${boardList}" varStatus="status">
	                                	<option value="${result.bbsId }" <c:if test="${searchVO.bbsId == result.bbsId}">selected="selected"</c:if>>${result.bbsId } - ${result.bbsNm }&emsp;</option>
									</c:forEach>
	                            </select>
	                        </div>
	                        <div class="tblselect">
	                            <select id="searchCnd" name="searchCnd" class="iptwid">
		                            <option value="ALL" <c:if test="${searchVO.searchCnd == 'ALL'}">selected="selected"</c:if>>전체</option>
		                			<c:forEach var="searchItem" items="${itemList}" varStatus="status">
		                				<c:if test="${searchItem.searchFlag eq 'Y' }">
		                					<c:if test="${searchItem.fieldId ne 'CATE_TYPE01' && searchItem.fieldId ne 'CATE_TYPE02' && searchItem.fieldId ne 'CATE_TYPE03' }">
		                                		<option value="${searchItem.fieldId }" <c:if test="${searchVO.searchCnd eq searchItem.fieldId}">selected="selected"</c:if>>${searchItem.itemName }</option>
		                                	</c:if>
		                				</c:if>
	                                </c:forEach>
	                            </select>
	                        </div>
	                        <div class="tblsearch">
	                            <input type="text" id="searchWrd" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd}" onkeydown="javascript:if(event.keyCode==13) {fn_bbsList('1');}">
	                            <button type="button" class="btn_search dark-blue" onclick="javascript:fn_bbsList('1');">검색</button>
	                        </div>
	                    </fieldset>
	                </form>
	            </div>
	        </div>
	        <div class="list-type2">
	            <ul class="gallery-list">
					<c:forEach var="result" items="${resultList}" varStatus="status">
		                <li>
							<form name="subForm${result.nttId}" id="subForm${result.nttId}" method="post" action="<c:url value='/_admin/board/bbsView.do'/>">
								<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
								<input type="hidden" name="nttId"  value="<c:out value="${result.nttId}"/>" />
								<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
								<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>"/>
								<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
								<input type="hidden" name="bbsTyCode" id="bbsTyCode" value="${brdMstrVO.bbsTyCode}">
			                    <a href="#lnk" onclick="fn_view('${result.nttId}');" class="gallery-box">
			                        <div class="img-box">
			                            <c:if test="${result.atchFileId ne null && result.atchFileId ne '' }">
		                                	<img src='<c:url value='/_cmm/fms/getImage.do'/>?atchFileId=<c:out value="${result.atchFileId}"/>&imgFlag=thumbnail&thumbnail=true' alt="${result.nttSj}"/>
		                                </c:if>
		                                <c:if test="${result.atchFileId eq null || result.atchFileId eq '' }">
		                                	<img src="/_admin/img/no_img.jpg" alt="이미지가 없습니다."/>
		                                </c:if>
			                        </div>
			                        <div class="gallery-con">
			                            <p>${result.nttSj}</p>
			                            <span class="day">${result.frstRegisterPnttm}</span>
			                        </div>
			                    </a>
		                    </form>
		                </li>
	                </c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<p>등록된 게시물이 없습니다.</p>
		 			</c:if>
	            </ul>
	        </div>
	        <script>
	            $(document).ready(function () {
	                var agent = navigator.userAgent.toLowerCase();
	                if ( (navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {
	                    forIe();
	                    function forIe() {
	                        $('.img-box img').each(function () {
	                            var Img = $(this),
	                                imgUrl = 'url(' + Img.attr('src') + ')',
	                                bgWrap = Img.parents('.img-box'),
	                                bgBox = $('<div class="bg"></div>');
	
	                            Img.hide();
	                            bgWrap.append(bgBox);
	
	                            bgWrap.css({
	                                'position': 'relative',
	                                'overflow': 'hidden',
	                                'min-height': 'auto'
	                            });
	                            bgBox.css({
	                                'background-image': imgUrl,
	                                'background-size': 'cover',
	                                'background-position': '50% 50%'
	                            });
	                        });
	                    }
	                }
	            });
	        </script>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_bbsList" />
            </div>
	    </div>
	</div>