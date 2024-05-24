<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script>
		function fn_list(pageNo){
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/code/codeMasterList.do");
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

			var codeList_arr = new Array();
			var cateCheck_arr = new Array();
			var codeId_arr = $("input[name=codeId]");
			var codeName_arr = $("input[name=codeName]");
			for(var i = 0; i < codeId_arr.length; i++){
				if(codeId_arr[i].value.replace(/ /gi, "") == null || codeId_arr[i].value.replace(/ /gi, "") == ""){
					alert("코드 ID를 입력해 주세요.");
					codeId_arr[i].focus();
					return;
				}
				var special_pattern = /[~!@#$%^&*()+|<>?{},]/;
				if (special_pattern.test(codeId_arr[i].value) == true) {
					alert("특수문자는 사용할 수 없습니다.");
					codeId_arr[i].focus();
					return;
				}
				if(codeName_arr[i].value.replace(/ /gi, "") == null || codeName_arr[i].value.replace(/ /gi, "") == ""){
					alert("코드명을 입력해 주세요.");
					codeName_arr[i].focus();
					return;
				}
				if (special_pattern.test(codeName_arr[i].value) == true) {
					alert("특수문자는 사용할 수 없습니다.");
					codeName_arr[i].focus();
					return;
				}
				codeList_arr.push(codeId_arr[i].value.trim()+"@@"+codeName_arr[i].value.trim());
			}

			for(var i = 0; i < codeId_arr.length; i++){		//카테고리 코드 중복 체크
				var value = codeId_arr[i].value;
				if(cateCheck_arr.indexOf(value) != -1){
					alert("코드 ID는 중복해서 사용할 수 없습니다.");
					codeId_arr[i].focus();
					return;
				}
				cateCheck_arr.push(value);
			}
			$("#codeListArr").val(codeList_arr);

			if (confirm('저장하시겠습니까?\n기존 데이터에 영향이 갈 수 있습니다.')) {
				$("#frm").attr("action", "/_admin/code/codeInsert.do");
				$("#frm").submit();
			}
		}
	</script>

	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">코드 관리</a></li>
	            <li><a href="#lnk">코드 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">코드 관리</h2>
			<div class="section-wrap res">
	            <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
					<input type="hidden" name="codeMasterId" id="codeMasterId" value="${searchVO.codeMasterId }">
					<input type="hidden" name="codeList" id="codeListArr" value="">
		            <fieldset style="min-width: 0;">
		                <div class="section">
		                    <p class="section-tit">코드마스터 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">코드마스터 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%" class="change1">
		                                <col style="width:80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">코드마스터명</th>
			                                <td class="tl">${searchVO.codeMasterName }</td>
			                            </tr>
			                            <tr>
			                                <th class="tl">코드마스터 ID</th>
			                                <td class="tl">${searchVO.codeMasterId }</td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">코드 등록</p>
		                    <div class="tbl-wrap new-tbl">
		                        <table class="tbl03">
		                            <caption class="blind">코드 등록 테이블</caption>
		                            <colgroup>
		                                <col style="width:40%">
		                                <col style="width:40%">
		                                <col style="width:15%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>코드 ID</th>
			                                <th>코드명</th>
			                                <th>추가</th>
			                            </tr>
			                            <tr>
			                                <td style="text-align: center;"><input type="text" class="wid200" title="코드 ID 입력" id="codeId" value=""></td>
			                                <td style="text-align: center;"><input type="text" class="wid200" title="코드명 입력" id="codeName" value=""></td>
			                                <td style="text-align: center;"><div><button type="button" class="add-code manage-btn add-btn"><span class="blind">추가</span></button></div></td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">코드 목록</p>
		                    <div class="tbl-wrap new-tbl">
		                        <table id="itemList" class="tbl03 table-cell">
		                            <caption class="blind">코드 목록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%">
		                                <col style="width:20%">
			                            <col style="width:20%">
		                                <col style="width:20%">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th>코드 ID</th>
			                                <th>코드명</th>
				                            <th>이동</th>
			                                <th>삭제</th>
			                            </tr>
			                            <c:forEach var="result" items="${resultList}" varStatus="status">
				                            <tr>
				                                <td style="text-align: center;"><input type="text" id="codeChk" name="codeId" class="wid200" value="${result.codeId }"></td>
				                                <td style="text-align: center;"><input type="text" name="codeName" class="wid200" value="${result.codeName }"></td>
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