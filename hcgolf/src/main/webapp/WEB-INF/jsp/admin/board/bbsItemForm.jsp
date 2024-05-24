<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script>
		$(document).ready(function(){
			$("#itemName").val($("#fieldId option:selected").text());
			if($("#fieldId").val() == "NTT_SJ"){
				$("#itemPercent").val("*");
			}else{
				$("#itemPercent").val("0");
			}
		});
		function fn_list(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsItemView.do");
			$("#frm").submit();
		}
		function setItemName(){
			$("#itemName").val($("#fieldId option:selected").text());
			if($("#fieldId").val() == "NTT_SJ"){
				$("#itemPercent").val("*");
			}else{
				$("#itemPercent").val("0");
			}
		}
		function moveUp(el){
			var $tr = $(el).closest('tr'); // 클릭한 버튼이 속한 tr 요소
			if ($tr.index()===1){
				return;
			}
			$tr.prev().before($tr); // 선택한 tr 의 이전 tr 앞에 선택한 tr 넣기
		}
	
		function moveDown(el){
			var $tr = $(el).closest('tr'); // 클릭한 버튼이 속한 tr 요소
			$tr.next().after($tr); // 선택한 tr 의 다음 tr 뒤에 선택한 tr 넣기
		}
	
		function fn_submit(){
			if($('#itemFlag').val() == 'list'){		// list 항목일 때 제목 필드 필수값 체크
				var value = "";
				$("[id^='codeChk']").each(function(index){
					value += $(this).text();
				});
				if(value.indexOf("제목") == -1){
					alert("제목은 필수입니다.");
					$("#fieldId").focus();
					return false;
				}
			}
			
			var itemList_arr = new Array();
			var fieldId_arr = $("input[name=fieldId]");
			var itemName_arr = $("input[name=itemName]");
			var itemPercent_arr = $("input[name=itemPercent]");
			var mobFlag_arr = $("input[name=mobFlag]");
			var searchFlag_arr = $("input[name=searchFlag]");
			for(var i = 0; i < fieldId_arr.length; i++){
				if(itemName_arr[i].value.replace(/ /gi, "") == null || itemName_arr[i].value.replace(/ /gi, "") == ""){
					alert("항목명을 입력해 주세요.");
					itemName_arr[i].focus();
					return;
				}
				var special_pattern = /[~!@#$%^&*()_+|<>?:{},]/;
				if (special_pattern.test(itemName_arr[i].value) == true) {
					alert("특수문자는 사용할 수 없습니다.");
					itemName_arr[i].focus();
					return;
				}
				if($('#itemFlag').val() == 'list'){
					if(itemPercent_arr[i].value.replace(/ /gi, "") == null || itemPercent_arr[i].value.replace(/ /gi, "") == "" || itemPercent_arr[i].value.replace(/ /gi, "") == 0){
						alert("비율을 입력해 주세요.");
						itemPercent_arr[i].focus();
						return;
					}
					var percent_pattern = /[~!@#$%^&()_+|<>?:{},]/;
					if (percent_pattern.test(itemPercent_arr[i].value) == true) {
						alert("특수문자는 사용할 수 없습니다.");
						itemPercent_arr[i].focus();
						return;
					}
				}
				if($('#itemFlag').val() == 'list'){
					itemList_arr.push(fieldId_arr[i].value+"@@"+itemName_arr[i].value.trim()+"@@"+itemPercent_arr[i].value.trim()+"@@"+mobFlag_arr[i].value+"@@"+searchFlag_arr[i].value);
				}else{
					itemList_arr.push(fieldId_arr[i].value+"@@"+itemName_arr[i].value.trim());
				}
			}
			$("#itemListArr").val(itemList_arr);

			if (confirm('저장하시겠습니까?')) {
				$("#frm").attr("action", "/_admin/board/bbsItemSave.do");
				$("#frm").submit();
			}
		}

		function fn_mobile(index){
			if($("#mobChk"+index).is(":checked")){
				$("#mobChk"+index).val("Y");
			}else{
				$("#mobChk"+index).val("N");
			}
		}

		function fn_searchForm(index){
			if($("#searchChk"+index).is(":checked")){
				$("#searchChk"+index).val("Y");
			}else{
				$("#searchChk"+index).val("N");
			}
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
	        <h2 class="con-tit">
	        	<c:if test="${itemVO.itemFlag eq 'list' }">LIST</c:if>
	        	<c:if test="${itemVO.itemFlag eq 'view' }">VIEW & FORM</c:if>
	        	항목 관리
	        </h2>
	        <div class="section-wrap res">
	            <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
					<input type="hidden" name="itemFlag" id="itemFlag" value="${itemVO.itemFlag }">
					<input type="hidden" name="bbsId" id="bbsId" value="${searchVO.bbsId }">
					<input type="hidden" name="itemList" id="itemListArr" value="">
		            <fieldset style="min-width: 0;">
		                <div class="section">
		                    <p class="section-tit">게시판 정보</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <!-- <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span> -->
		                        <table class="tbl03">
		                            <caption class="blind">게시판 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%" class="change1">
		                                <col style="width:80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">게시판명</th>
			                                <td class="tl">${searchVO.bbsNm }</td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판ID</th>
			                                <td class="tl">${searchVO.bbsId }</td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">항목 선택</p>
		                    <div class="tbl-wrap new-tbl">
                                <c:if test="${itemVO.itemFlag eq 'list'}">
			                    	<span>필드 선택에서 제목은 필수 값이며, 제목의 비율은 <em class="essential red">*</em>값으로 자동 입력되지만 수정가능합니다. <em class="essential red">( * = 100% - 다른 항목 비율 )</em></span>
                                </c:if>
		                        <table class="tbl03">
		                            <caption class="blind">항목 선택 테이블</caption>
		                            <colgroup>
		                                <col style="width:25%">
		                                <col style="width:15%">
			                            <c:if test="${itemVO.itemFlag eq 'list'}">
		                                	<col style="width:20%">
		                                	<col style="width:15%">
		                                	<col style="width:8%">
		                                </c:if>
		                                <col style="width:10%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>필드 선택</th>
			                                <th>항목명</th>
			                                <c:if test="${itemVO.itemFlag eq 'list'}">
			                                	<th>비율</th>
			                                	<th>모바일 hidden</th>
			                                	<th>검색폼</th>
			                                </c:if>
			                                <th>추가</th>
			                            </tr>
			                            <tr>
			                                <td style="text-align: center;">
			                                    <select id="fieldId" onchange="setItemName();">
													<c:forEach var="fieldList" items="${fieldList}" varStatus="status">
			                                        	<option value="${fieldList.fieldId }">${fieldList.fieldName }</option>
			                                        </c:forEach>
			                                    </select>
			                                </td>
			                                <td style="text-align: center;"><input type="text" title="항목명 입력" id="itemName" value=""></td>
			                                <c:if test="${itemVO.itemFlag eq 'list'}">
			                                	<td style="text-align: center;"><input type="text" class="cal30" title="항목비율 입력" id="itemPercent" value=""> %</td>
				                                <td style="text-align: center;">
				                                	<input type="checkbox" id="mobChkFlag" value="Y">
				                                </td>
				                                <td style="text-align: center;">
				                                	<input type="checkbox" id="searchChkFlag" value="Y">
				                                </td>
			                                </c:if>
			                                <td style="text-align: center;"><div><button type="button" class="add-item manage-btn add-btn"><span class="blind">추가</span></button></div></td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">항목 목록</p>
		                    <div class="tbl-wrap new-tbl">
		                        <table id="itemList" class="tbl03 table-cell">
		                            <caption class="blind">항목 목록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%">
		                                <col style="width:20%">
			                            <c:if test="${itemVO.itemFlag eq 'list'}">
			                                <col style="width:20%">
			                           		<col style="width:15%">
		                                	<col style="width:8%">
		                                </c:if>
			                            <col style="width:10%">
		                                <col style="width:10%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>필드 선택</th>
			                                <th>항목명</th>
			                                <c:if test="${itemVO.itemFlag eq 'list'}">
				                                <th>비율</th>
			                                	<th>모바일 hidden</th>
			                                	<th>검색폼</th>
			                                </c:if>
				                            <th>이동</th>
			                                <th>삭제</th>
			                            </tr>
			                            <c:forEach var="result" items="${resultList}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;" id="codeChk">
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
				                                	<input type="hidden" name="fieldId" value="${result.fieldId }">
				                                </td>
				                                <td style="text-align: center;"><input type="text" name="itemName" value="${result.itemName }"></td>
			                                	<c:if test="${itemVO.itemFlag eq 'list'}">
					                                <td style="text-align: center;"><input type="text" name="itemPercent" class="cal30" value="${result.itemPercent }"> %</td>
					                                <td style="text-align: center;">
					                                	<input type="checkbox" id="mobChk${status.index}" onclick="javascript:fn_mobile('${status.index}');" name="mobFlag" value="${result.mobFlag }" <c:if test="${result.mobFlag eq 'Y' }"> checked="checked"</c:if>>
					                                </td>
					                                <td style="text-align: center;">
					                                	<input type="checkbox" id="searchChk${status.index}" onclick="javascript:fn_searchForm('${status.index}');" name="searchFlag" value="${result.searchFlag }" <c:if test="${result.searchFlag eq 'Y' }"> checked="checked"</c:if>>
					                                </td>
				                                </c:if>
					                            <td style="text-align: center;"><a href="#" onclick="moveUp(this);return false;">▲</a><a href="#" onclick="moveDown(this);return false;">▼</a></td>
				                                <td style="text-align: center;"><div><button type="button" class="del-item manage-btn delete-btn"><span class="blind">삭제</span></button></div></td>
				                            </tr>
			                            </c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		            </fieldset>
		        </form>
			</div>
	        <div class="btn-box">
		        <a href="#lnk" onclick="javascript:fn_submit();" class="tbl-btn blue">저장</a>
	            <a href="#lnk" onclick="javascript:fn_list('${searchVO.pageIndex}');" class="tbl-btn gray">뒤로</a>
	        </div>
	    </div>
	</div>