<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script>
		function fn_list(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsMasterList.do");
			$("#frm").submit();
		}
		function fn_listInsert(){
			$("#itemFlag").val("list");
			$("#frm").attr("action","/_admin/board/bbsItemForm.do");
			$("#frm").submit();
		}
		function fn_viewInsert(){
			$("#itemFlag").val("view");
			$("#frm").attr("action","/_admin/board/bbsItemForm.do");
			$("#frm").submit();
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">항목 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">항목 관리</h2>
			<div class="section-wrap res">
	            <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
					<input type="hidden" name="itemFlag" id="itemFlag" value="">
					<input type="hidden" name="bbsId" id="bbsId" value="${searchVO.bbsId }">
					<input type="hidden" name="bbsNm" value="${brdMstrVO.bbsNm }">
					<input type="hidden" name="bbsIntrcn" value="${brdMstrVO.bbsIntrcn }">
		            <fieldset style="min-width: 0;">
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
		                    <p class="section-tit">LIST 항목</p>
		                    <div class="add-btn-box mb10">
					            <button type="button" onclick="javascript:fn_listInsert('${brdMstrVO.bbsId }');">항목 관리</button>
					        </div>
		                    <div class="tbl-wrap new-tbl">
		                        <table class="tbl03 type1">
		                            <caption class="blind">LIST 항목 테이블</caption>
		                            <colgroup>
		                                <col style="width:10%">
		                                <col style="width:29%">
		                                <col style="width:29%">
		                                <col style="width:10%">
		                                <col style="width:12%">
		                                <col style="width:10%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>번호</th>
			                                <th>필드 선택</th>
			                                <th>항목명</th>
			                                <th>비율</th>
			                                <th>모바일 hidden</th>
			                                <th>검색폼</th>
			                            </tr>
			                            <c:forEach var="result" items="${itemList}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;">${result.itemOrder }</td>
				                                <td style="text-align: center;">
													<c:choose>
														<c:when test="${result.fieldId eq 'ROWNUM'}">글번호</c:when>
														<c:when test="${result.fieldId eq 'NTT_SJ'}">제목</c:when>
														<c:when test="${result.fieldId eq 'NTT_CN'}">게시물 내용</c:when>
														<c:when test="${result.fieldId eq 'NTCR_NM'}">작성자</c:when>
														<c:when test="${result.fieldId eq 'USER_TEL'}">전화번호</c:when>
														<c:when test="${result.fieldId eq 'USER_CEL'}">휴대전화</c:when>
														<c:when test="${result.fieldId eq 'ADDRESS'}">주소</c:when>
														<c:when test="${result.fieldId eq 'EMAIL'}">이메일</c:when>
														<c:when test="${result.fieldId eq 'REG_DATE'}">작성일</c:when>
														<c:when test="${result.fieldId eq 'UPD_DATE'}">수정일</c:when>
														<c:when test="${result.fieldId eq 'RDCNT'}">조회수</c:when>
														<c:when test="${result.fieldId eq 'START_DATE'}">시작일</c:when>
														<c:when test="${result.fieldId eq 'END_DATE'}">종료일</c:when>
														<c:when test="${result.fieldId eq 'ATCH_FILE_ID'}">첨부파일</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD1'}">임시필드1</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD2'}">임시필드2</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD3'}">임시필드3</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD4'}">임시필드4</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD5'}">임시필드5</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD6'}">임시필드6</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD7'}">임시필드7</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD8'}">임시필드8</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD9'}">임시필드9</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD10'}">임시필드10</c:when>
														<c:when test="${result.fieldId eq 'CATE_TYPE01'}">카테고리1</c:when>
														<c:when test="${result.fieldId eq 'CATE_TYPE02'}">카테고리2</c:when>
														<c:when test="${result.fieldId eq 'CATE_TYPE03'}">카테고리3</c:when>
														<c:when test="${result.fieldId eq 'THUMBNAIL'}">썸네일</c:when>
													</c:choose>
				                                </td>
				                                <td style="text-align: center;">${result.itemName }</td>
				                                <td style="text-align: center;">${result.itemPercent } %</td>
				                                <td style="text-align: center;">
				                                	<c:choose>
				                                		<c:when test="${result.mobFlag eq 'Y'}">사용</c:when>
				                                		<c:otherwise>미사용</c:otherwise>
				                                	</c:choose>
				                                </td>
				                                <td style="text-align: center;">
				                                	<c:choose>
				                                		<c:when test="${result.searchFlag eq 'Y'}">사용</c:when>
				                                		<c:otherwise>미사용</c:otherwise>
				                                	</c:choose>
				                                </td>
				                            </tr>
			                            </c:forEach>
										<c:if test="${fn:length(itemList) == 0}">
						                    <tr>
						                        <td colspan="4" style="text-align:center;">등록된 항목이 없습니다.</td>
						                    </tr>
							 			</c:if>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">VIEW & FORM 항목</p>
		                    <div class="add-btn-box mb10">
					            <button type="button" onclick="javascript:fn_viewInsert('${brdMstrVO.bbsId }');">항목 관리</button>
					        </div>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">VIEW & FORM 항목 테이블</caption>
		                            <colgroup>
		                                <col style="width:16%">
		                                <col style="width:42%">
		                                <col style="width:42%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>번호</th>
			                                <th>필드 선택</th>
			                                <th>항목명</th>
			                            </tr>
			                            <c:forEach var="result" items="${viewList}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;">${result.itemOrder }</td>
				                                <td style="text-align: center;">
													<c:choose>
														<c:when test="${result.fieldId eq 'ROWNUM'}">글번호</c:when>
														<c:when test="${result.fieldId eq 'NTT_SJ'}">제목</c:when>
														<c:when test="${result.fieldId eq 'NTT_CN'}">게시물 내용</c:when>
														<c:when test="${result.fieldId eq 'NTCR_NM'}">작성자</c:when>
														<c:when test="${result.fieldId eq 'USER_TEL'}">전화번호</c:when>
														<c:when test="${result.fieldId eq 'USER_CEL'}">휴대전화</c:when>
														<c:when test="${result.fieldId eq 'ADDRESS'}">주소</c:when>
														<c:when test="${result.fieldId eq 'EMAIL'}">이메일</c:when>
														<c:when test="${result.fieldId eq 'REG_DATE'}">작성일</c:when>
														<c:when test="${result.fieldId eq 'UPD_DATE'}">수정일</c:when>
														<c:when test="${result.fieldId eq 'RDCNT'}">조회수</c:when>
														<c:when test="${result.fieldId eq 'START_DATE'}">시작일</c:when>
														<c:when test="${result.fieldId eq 'END_DATE'}">종료일</c:when>
														<c:when test="${result.fieldId eq 'ATCH_FILE_ID'}">첨부파일</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD1'}">임시필드1</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD2'}">임시필드2</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD3'}">임시필드3</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD4'}">임시필드4</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD5'}">임시필드5</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD6'}">임시필드6</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD7'}">임시필드7</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD8'}">임시필드8</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD9'}">임시필드9</c:when>
														<c:when test="${result.fieldId eq 'TMP_FIELD10'}">임시필드10</c:when>
														<c:when test="${result.fieldId eq 'CATE_TYPE01'}">카테고리1</c:when>
														<c:when test="${result.fieldId eq 'CATE_TYPE02'}">카테고리2</c:when>
														<c:when test="${result.fieldId eq 'CATE_TYPE03'}">카테고리3</c:when>
														<c:when test="${result.fieldId eq 'THUMBNAIL'}">썸네일</c:when>
													</c:choose>
				                                </td>
				                                <td style="text-align: center;">${result.itemName }</td>
				                            </tr>
			                            </c:forEach>
										<c:if test="${fn:length(viewList) == 0}">
						                    <tr>
						                        <td colspan="3" style="text-align:center;">등록된 항목이 없습니다.</td>
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