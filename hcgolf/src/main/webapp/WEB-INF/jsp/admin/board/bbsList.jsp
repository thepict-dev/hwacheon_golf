<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_search(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#searchCnd").val("ALL");
			$("#searchWrd").val("");
			$("#searchCate1").val("");
			$("#searchCate2").val("");
			$("#searchCate3").val("");
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
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시물 관리</a></li>
	            <li><a href="#lnk">${brdMstrVO.bbsNm}</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">${brdMstrVO.bbsNm}</h2>
	        <div class="search-box res">
	            <p class="tot-top">총 <span class="colblue">${resultCnt}</span>개</p>
	            <div class="pagenavbox mob mb20 sel-chk">
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
	                            <c:set var="cateCnd" value="0"/>
	                            <c:forEach var="itemList" items="${itemList}" varStatus="status">
								    <c:if test="${brdMstrVO.cateType01 eq 'Y'}">
					                	<c:if test="${itemList.fieldId eq 'CATE_TYPE01' }">
						                	<c:if test="${itemList.searchFlag eq 'Y' }">
						                		<c:set var="cateCnd" value="${cateCnd + 1 }"/>
					                            <select id="searchCate1" name="searchCate1" class="iptwid">
						                            <option value="" <c:if test="${searchVO.searchCate1 == ''}">selected="selected"</c:if>>${itemList.itemName } 전체</option>
						                			<c:forEach var="cateList" items="${cateList01}" varStatus="status">
							                        	<option value="${cateList.cateCode }" <c:if test="${cateList.cateCode eq searchVO.searchCate1}">selected="selected"</c:if>>${cateList.cateName }</option>
					                                </c:forEach>
					                            </select>
					                        </c:if>
					                	</c:if>
								    </c:if>
								    <c:if test="${brdMstrVO.cateType02 eq 'Y'}">
					                	<c:if test="${itemList.fieldId eq 'CATE_TYPE02' }">
						                	<c:if test="${itemList.searchFlag eq 'Y' }">
						                		<c:set var="cateCnd" value="${cateCnd + 1 }"/>
					                            <select id="searchCate2" name="searchCate2" class="iptwid">
						                            <option value="" <c:if test="${searchVO.searchCate2 == ''}">selected="selected"</c:if>>${itemList.itemName } 전체</option>
						                			<c:forEach var="cateList" items="${cateList02}" varStatus="status">
							                        	<option value="${cateList.cateCode }" <c:if test="${cateList.cateCode eq searchVO.searchCate2}">selected="selected"</c:if>>${cateList.cateName }</option>
					                                </c:forEach>
					                            </select>
					                        </c:if>
					                	</c:if>
								    </c:if>
								    <c:if test="${brdMstrVO.cateType03 eq 'Y'}">
					                	<c:if test="${itemList.fieldId eq 'CATE_TYPE03' }">
						                	<c:if test="${itemList.searchFlag eq 'Y' }">
						                		<c:set var="cateCnd" value="${cateCnd + 1 }"/>
					                            <select id="searchCate3" name="searchCate3" class="iptwid">
						                            <option value="" <c:if test="${searchVO.searchCate3 == ''}">selected="selected"</c:if>>${itemList.itemName } 전체</option>
						                			<c:forEach var="cateList" items="${cateList03}" varStatus="status">
							                        	<option value="${cateList.cateCode }" <c:if test="${cateList.cateCode eq searchVO.searchCate3}">selected="selected"</c:if>>${cateList.cateName }</option>
					                                </c:forEach>
					                            </select>
					                        </c:if>
					                	</c:if>
								    </c:if>
							    </c:forEach>
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
						    <script>
						    	$(document).ready(function(){
						    		var selboxCnd = 2 + <c:out value='${cateCnd}'/>;
						    		$('.pagenavbox').addClass('selbox'+selboxCnd);
						    	});
						    </script>
	                        <div class="tblsearch">
	                            <input type="text" id="searchWrd" name="searchWrd" title="검색어 입력" value="${searchVO.searchWrd}" onkeydown="javascript:if(event.keyCode==13) {fn_bbsList('1');}">
	                            <button type="button" class="btn_search dark-blue" onclick="javascript:fn_bbsList('1');">검색</button>
	                        </div>
	                    </fieldset>
	                </form>
	            </div>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01">
	                <caption class="blind">공지사항 목록 테이블</caption>
	                <colgroup>
		                <c:forEach var="percent" items="${itemList}" varStatus="status">
		                    <col style="width:${percent.itemPercent }%">
		                </c:forEach>
	                </colgroup>
	                <thead>
		                <tr>
			                <c:forEach var="name" items="${itemList}" varStatus="status">
			                    <th>${name.itemName }</th>
			                </c:forEach>
		                </tr>
	                </thead>
	                <%-- 
	                <colgroup>
	                    <col style="width: 10%">
		        		<c:if test="${brdMstrVO.bbsId eq 'BBSMSTR_000000000002' || brdMstrVO.bbsId eq 'BBSMSTR_000000000004'}">
	                    	<col style="width:12%">
		        		</c:if>
	                    <col style="width:">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                    <col style="width:10%">
	                </colgroup>
	                <thead>
	                <tr>
	                    <th>번호</th>
		        		<c:if test="${brdMstrVO.bbsId eq 'BBSMSTR_000000000002' || brdMstrVO.bbsId eq 'BBSMSTR_000000000004'}">
	                    	<th>카테고리</th>
		        		</c:if>
	                    <th>제목</th>
	                    <th>첨부</th>
	                    <th>작성자</th>
	                    <th>작성일자</th>
	                    <th>조회수</th>
	                </tr>
	                </thead> 
	                --%>
	                <tbody>
					<!-- :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 공지글 시작 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->
					<c:forEach var="result" items="${noticeList}" varStatus="status">
						<form name="subForm${result.nttId}" id="subForm${result.nttId}" method="post" action="<c:url value='/_admin/board/bbsView.do'/>">
							<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
							<input type="hidden" name="nttId"  value="<c:out value="${result.nttId}"/>" />
							<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
							<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>"/>
							<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
							<input type="hidden" name="searchCate1" value="<c:out value='${searchVO.searchCate1}'/>"/>
							<input type="hidden" name="searchCate2" value="<c:out value='${searchVO.searchCate2}'/>"/>
							<input type="hidden" name="searchCate3" value="<c:out value='${searchVO.searchCate3}'/>"/>
						</form>
						<tr>
			                <c:forEach var="name" items="${itemList}" varStatus="status">
			                    <c:if test="${name.fieldId eq 'ROWNUM' }"><td><span class="noti">공지</span></td></c:if>
			                    <c:if test="${name.fieldId eq 'NTT_SJ' }">
									<td class="tl">
										<a href="#lnk" onclick="fn_view('${result.nttId}');">${result.nttSj}</a>
									</td>
			                    </c:if>
			                    <c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
				                    <td>
				                    	<c:choose>
				                    		<c:when test="${result.atchFileId ne null && result.atchFileId ne ''}">
				                    			<span class="file">첨부</span>
				                    		</c:when>
				                    		<c:otherwise>
				                    		</c:otherwise>
				                    	</c:choose>
				                    </td>
			                    </c:if>
			                    <c:if test="${name.fieldId eq 'THUMBNAIL' }">
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
			                    </c:if>
			                    <c:if test="${name.fieldId eq 'NTCR_NM' }"><td>${result.frstRegisterNm}</td></c:if>
			                    <c:if test="${name.fieldId eq 'REG_DATE' }"><td>${result.frstRegisterPnttm}</td></c:if>
			                    <c:if test="${name.fieldId eq 'RDCNT' }"><td>${result.inqireCo}</td></c:if>
			                    <c:if test="${name.fieldId eq 'USER_TEL' }"><td>${result.userTel}</td></c:if>
			                    <c:if test="${name.fieldId eq 'USER_CEL' }"><td>${result.userCel}</td></c:if>
			                    <c:if test="${name.fieldId eq 'ADDRESS' }"><td>${result.zipCode} ${result.address} ${result.detailAddr}</td></c:if>
			                    <c:if test="${name.fieldId eq 'EMAIL' }"><td>${result.email}</td></c:if>
			                    <c:if test="${name.fieldId eq 'START_DATE' }"><td>${result.startDate}</td></c:if>
			                    <c:if test="${name.fieldId eq 'END_DATE' }"><td>${result.endDate}</td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD1' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField1}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD2' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField2}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD3' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField3}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD4' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField4}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD5' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField5}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD6' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField6}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD7' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField7}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD8' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField8}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD9' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField9}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD10' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField10}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'CATE_TYPE01' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.cateName01}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'CATE_TYPE02' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.cateName02}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'CATE_TYPE03' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.cateName03}</a></td></c:if>
			                </c:forEach>
		                </tr>
	                </c:forEach>
					<!-- :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 공지글 종료 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->
					<!-- :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 일반글 시작 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<form name="subForm${result.nttId}" id="subForm${result.nttId}" method="post" action="<c:url value='/_admin/board/bbsView.do'/>">
							<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
							<input type="hidden" name="nttId"  value="<c:out value="${result.nttId}"/>" />
							<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
							<input name="searchWrd" type="hidden" value="<c:out value='${searchVO.searchWrd}'/>"/>
							<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
							<input type="hidden" name="searchCate1" value="<c:out value='${searchVO.searchCate1}'/>"/>
							<input type="hidden" name="searchCate2" value="<c:out value='${searchVO.searchCate2}'/>"/>
							<input type="hidden" name="searchCate3" value="<c:out value='${searchVO.searchCate3}'/>"/>
						</form>
						<tr>
			                <c:forEach var="name" items="${itemList}" varStatus="status">
								<c:if test="${name.fieldId eq 'ROWNUM' }"><td>${result.rownum}</td></c:if>
			                    <c:if test="${name.fieldId eq 'NTT_SJ' }">
									<td class="tl">
										<a href="#lnk" onclick="fn_view('${result.nttId}');">${result.nttSj}</a>
									</td>
								</c:if>
			                    <c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
				                    <td>
				                    	<c:choose>
				                    		<c:when test="${result.atchFileId ne null && result.atchFileId ne ''}">
				                    			<span class="file">첨부</span>
				                    		</c:when>
				                    		<c:otherwise>
				                    		</c:otherwise>
				                    	</c:choose>
				                    </td>
			                    </c:if>
			                    <c:if test="${name.fieldId eq 'THUMBNAIL' }">
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
			                    </c:if>
								<c:if test="${name.fieldId eq 'NTCR_NM' }"><td>${result.frstRegisterNm}</td></c:if>
								<c:if test="${name.fieldId eq 'REG_DATE' }"><td>${result.frstRegisterPnttm}</td></c:if>
								<c:if test="${name.fieldId eq 'RDCNT' }"><td>${result.inqireCo}</td></c:if>
			                    <c:if test="${name.fieldId eq 'USER_TEL' }"><td>${result.userTel}</td></c:if>
			                    <c:if test="${name.fieldId eq 'USER_CEL' }"><td>${result.userCel}</td></c:if>
			                    <c:if test="${name.fieldId eq 'ADDRESS' }"><td>${result.zipCode} ${result.address} ${result.detailAddr}</td></c:if>
			                    <c:if test="${name.fieldId eq 'EMAIL' }"><td>${result.email}</td></c:if>
			                    <c:if test="${name.fieldId eq 'START_DATE' }"><td>${result.startDate}</td></c:if>
			                    <c:if test="${name.fieldId eq 'END_DATE' }"><td>${result.endDate}</td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD1' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField1}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD2' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField2}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD3' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField3}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD4' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField4}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD5' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField5}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD6' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField6}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD7' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField7}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD8' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField8}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD9' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField9}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'TMP_FIELD10' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.tmpField10}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'CATE_TYPE01' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.cateName01}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'CATE_TYPE02' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.cateName02}</a></td></c:if>
			                    <c:if test="${name.fieldId eq 'CATE_TYPE03' }"><td><a href="#lnk" onclick="fn_view('${result.nttId}');">${result.cateName03}</a></td></c:if>
			                </c:forEach>
						</tr>
					</c:forEach>
					<!-- :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 일반글 종료 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: -->
					<c:if test="${fn:length(resultList) == 0}">
	                    <tr>
	                        <td colspan="${fn:length(itemList)}">등록된 게시물이 없습니다.</td>
	                    </tr>
		 			</c:if>
	                </tbody>
	            </table>
	        </div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_insert();" class="tbl-btn blue">등록</a>
            </div>
            <div class="pager">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_bbsList" />
            </div>
	    </div>
	</div>