<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script>
		function fn_submit(v_command){
	
			if ($("#codeMasterName").val() == "") {
				alert("코드마스터명을 입력해주세요.");
				$("#codeMasterName").focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/code/codeMasterInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/code/codeMasterUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/code/codeMasterList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/code/codeMasterList.do");
			$("#frm").submit();
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
		        	<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="codeMasterId" value="${selectVO.codeMasterId }" >
	            	</c:if>
	                <fieldset>
            			<div class="section">
		                    <p class="section-tit">코드마스터 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                    	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">코드마스터 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%" class="change1">
		                                <col style="width:80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">코드마스터명 <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" title="코드마스터명 입력" name="codeMasterName" id="codeMasterName" value="${selectVO.codeMasterName }"></td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
				        </div>
	                </fieldset>
	            </form>
	        </div>
	        <div class="btn-box">
		        <c:if test="${searchVO.command eq 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		            <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
	        </div>
	    </div>
	</div>