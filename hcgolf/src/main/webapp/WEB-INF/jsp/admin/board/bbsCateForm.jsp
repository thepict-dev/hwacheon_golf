<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script>
		function fn_list(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsCateView.do");
			$("#frm").submit();
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

			var cateList_arr = new Array();
			var cateCheck_arr = new Array();
			var cateCode_arr = $("input[name=cateCode]");
			var cateName_arr = $("input[name=cateName]");
			for(var i = 0; i < cateCode_arr.length; i++){
				if(cateCode_arr[i].value.replace(/ /gi, "") == null || cateCode_arr[i].value.replace(/ /gi, "") == ""){
					alert("카테고리 코드를 입력해 주세요.");
					cateCode_arr[i].focus();
					return;
				}
				if(cateName_arr[i].value.replace(/ /gi, "") == null || cateName_arr[i].value.replace(/ /gi, "") == ""){
					alert("카테고리명을 입력해 주세요.");
					cateName_arr[i].focus();
					return;
				}
				cateList_arr.push(cateCode_arr[i].value.trim()+"@@"+cateName_arr[i].value.trim());
			}

			for(var i = 0; i < cateCode_arr.length; i++){		//카테고리 코드 중복 체크
				var value = cateCode_arr[i].value;
				if(cateCheck_arr.indexOf(value) != -1){
					alert("카테고리 코드는 중복해서 사용할 수 없습니다.");
					cateCode_arr[i].focus();
					return;
				}
				cateCheck_arr.push(value);
			}
			$("#cateListArr").val(cateList_arr);

			if (confirm('저장하시겠습니까?')) {
				$("#frm").attr("action", "/_admin/board/bbsCateSave.do");
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
	            <li><a href="#lnk">카테고리 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">
	        	<c:if test="${cateVO.cateType eq 'CATE01' }">1차</c:if>
	        	<c:if test="${cateVO.cateType eq 'CATE02' }">2차</c:if>
	        	<c:if test="${cateVO.cateType eq 'CATE03' }">3차</c:if>
	        	카테고리 관리
	        </h2>
			<div class="section-wrap res">
	            <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
					<input type="hidden" name="cateType" id="cateType" value="${cateVO.cateType }">
					<input type="hidden" name="bbsId" id="bbsId" value="${searchVO.bbsId }">
					<input type="hidden" name="cateList" id="cateListArr" value="">
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
		                    <p class="section-tit">카테고리 선택</p>
		                    <div class="tbl-wrap new-tbl">
		                        <table class="tbl03">
		                            <caption class="blind">카테고리 선택 테이블</caption>
		                            <colgroup>
		                                <col style="width:42%">
		                                <col style="width:42%">
		                                <col style="width:16%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>카테고리 코드</th>
			                                <th>카테고리명</th>
			                                <th>추가</th>
			                            </tr>
			                            <tr>
			                                <td style="text-align: center;"><input type="text" class="wid200" title="카테고리 코드 입력" id="cateCode" value=""></td>
			                                <td style="text-align: center;"><input type="text" class="wid200" title="카테고리명 입력" id="cateName" value=""></td>
			                                <td style="text-align: center;"><div><button type="button" class="add-cate manage-btn add-btn"><span class="blind">추가</span></button></div></td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">카테고리 목록</p>
		                    <div class="tbl-wrap new-tbl">
		                        <table id="itemList" class="tbl03 table-cell">
		                            <caption class="blind">카테고리 목록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%">
		                                <col style="width:20%">
			                            <col style="width:20%">
		                                <col style="width:20%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>카테고리 코드</th>
			                                <th>카테고리명</th>
				                            <th>이동</th>
			                                <th>삭제</th>
			                            </tr>
			                            <c:forEach var="result" items="${resultList}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;"><input type="text" id="codeChk" name="cateCode" class="wid200" value="${result.cateCode }"></td>
				                                <td style="text-align: center;"><input type="text" name="cateName" class="wid200" value="${result.cateName }"></td>
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
	            <a href="#lnk" onclick="javascript:fn_list('${searchVO.pageIndex}');" class="tbl-btn gray">목록</a>
	        </div>
	    </div>
	</div>