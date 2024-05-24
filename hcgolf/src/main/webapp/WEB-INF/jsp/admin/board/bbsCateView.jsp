<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script>
		function fn_list(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsMasterList.do");
			$("#frm").submit();
		}
		function fn_insertCate01(){
			$("#cateType").val("CATE01");
			$("#frm").attr("action","/_admin/board/bbsCateForm.do");
			$("#frm").submit();
		}
		function fn_insertCate02(){
			$("#cateType").val("CATE02");
			$("#frm").attr("action","/_admin/board/bbsCateForm.do");
			$("#frm").submit();
		}
		function fn_insertCate03(){
			$("#cateType").val("CATE03");
			$("#frm").attr("action","/_admin/board/bbsCateForm.do");
			$("#frm").submit();
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">카테고리 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">카테고리 관리</h2>
			<div class="section-wrap res">
	            <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
					<input type="hidden" name="cateType" id="cateType" value="">
					<input type="hidden" name="bbsId" id="bbsId" value="${searchVO.bbsId }">
					<input type="hidden" name="bbsNm" value="${brdMstrVO.bbsNm }">
					<input type="hidden" name="bbsIntrcn" value="${brdMstrVO.bbsIntrcn }">
		            <fieldset>
		                <div class="section">
		                    <p class="section-tit">게시판 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">게시판 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%" class="change1">
		                                <col style="width:80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">게시판명</th>
			                                <td class="tl">${brdMstrVO.bbsNm }</td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판ID</th>
			                                <td class="tl">${brdMstrVO.bbsId }</td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">1차 카테고리</p>
		                    <div class="add-btn-box mb10">
					            <button type="button" onclick="javascript:fn_insertCate01('${brdMstrVO.bbsId }');">1차 카테고리 관리</button>
					        </div>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">1차 카테고리 테이블</caption>
		                            <colgroup>
		                                <col style="width:16%">
		                                <col style="width:42%">
		                                <col style="width:42%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>번호</th>
			                                <th>코드</th>
			                                <th>카테고리명</th>
			                            </tr>
			                            <c:forEach var="result" items="${cateList1}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;">${result.rownum }</td>
				                                <td style="text-align: center;">${result.cateCode }</td>
				                                <td style="text-align: center;">${result.cateName }</td>
				                            </tr>
			                            </c:forEach>
										<c:if test="${fn:length(cateList1) == 0}">
						                    <tr>
						                        <td colspan="3" style="text-align:center;">등록된 카테고리가 없습니다.</td>
						                    </tr>
							 			</c:if>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">2차 카테고리</p>
		                    <div class="add-btn-box mb10">
					            <button type="button" onclick="javascript:fn_insertCate02('${brdMstrVO.bbsId }');">2차 카테고리 관리</button>
					        </div>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">2차 카테고리 테이블</caption>
		                            <colgroup>
		                                <col style="width:16%">
		                                <col style="width:42%">
		                                <col style="width:42%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>번호</th>
			                                <th>코드</th>
			                                <th>카테고리명</th>
			                            </tr>
			                            <c:forEach var="result" items="${cateList2}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;">${result.cateOrder }</td>
				                                <td style="text-align: center;">${result.cateCode }</td>
				                                <td style="text-align: center;">${result.cateName }</td>
				                            </tr>
			                            </c:forEach>
										<c:if test="${fn:length(cateList2) == 0}">
						                    <tr>
						                        <td colspan="3" style="text-align:center;">등록된 카테고리가 없습니다.</td>
						                    </tr>
							 			</c:if>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">3차 카테고리</p>
		                    <div class="add-btn-box mb10">
					            <button type="button" onclick="javascript:fn_insertCate03('${brdMstrVO.bbsId }');">3차 카테고리 관리</button>
					        </div>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">3차 카테고리 테이블</caption>
		                            <colgroup>
		                                <col style="width:16%">
		                                <col style="width:42%">
		                                <col style="width:42%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>번호</th>
			                                <th>코드</th>
			                                <th>카테고리명</th>
			                            </tr>
			                            <c:forEach var="result" items="${cateList3}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;">${result.cateOrder }</td>
				                                <td style="text-align: center;">${result.cateCode }</td>
				                                <td style="text-align: center;">${result.cateName }</td>
				                            </tr>
			                            </c:forEach>
										<c:if test="${fn:length(cateList3) == 0}">
						                    <tr>
						                        <td colspan="3" style="text-align:center;">등록된 카테고리가 없습니다.</td>
						                    </tr>
							 			</c:if>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		            </fieldset>
		        </form>
			</div>
	        <div class="btn-box">
	            <a href="#lnk" onclick="javascript:fn_list('${searchVO.pageIndex}');" class="tbl-btn gray">목록</a>
	        </div>
	    </div>
	</div>