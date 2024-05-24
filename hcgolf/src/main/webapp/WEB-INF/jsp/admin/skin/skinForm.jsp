<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


	<script>
		function fn_submit(v_command){
			if ($("#skinName").val() == "") {
				alert("스킨 이름을 입력해주세요.");
				$("#skinName").focus();
				return;
			}
			if (editor0.getValue() == "") {
				alert("list 내용을 입력해주세요.");
				editor0.focus();
				return;
			}
			if (editor1.getValue() == "") {
				alert("view 내용을 입력해주세요.");
				editor1.focus();
				return;
			}
			if (editor2.getValue() == "") {
				alert("form 내용을 입력해주세요.");
				editor2.focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					if ( $("#skinType").val() == "BBS" ){
						$("#frm").attr("action","/_admin/board/skinInsert.do");
					} else if ( $("#skinType").val() == "SRV" ){
						$("#frm").attr("action","/_admin/survey/skinInsert.do");
					}
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					if ( $("#skinType").val() == "BBS" ){
						$("#frm").attr("action","/_admin/board/skinUpdate.do");
					} else if ( $("#skinType").val() == "SRV" ){
						$("#frm").attr("action","/_admin/survey/skinUpdate.do");
					}
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				if ( $("#skinType").val() == "BBS" ){
					$("#frm").attr("action","/_admin/board/skinList.do");
				} else if ( $("#skinType").val() == "SRV" ){
					$("#frm").attr("action","/_admin/survey/skinList.do");
				}
				$("#frm").submit();
			}
		}
		function fn_list(){
			if ( $("#skinType").val() == "BBS" ){
				$("#frm").attr("action","/_admin/board/skinList.do");
			} else if ( $("#skinType").val() == "SRV" ){
				$("#frm").attr("action","/_admin/survey/skinList.do");
			}
			$("#frm").submit();
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><img src="/_admin/img/home.png"></li>
	            <c:if test='${skinType ne "SRV"}'>
	            	<li><a>게시판 관리</a></li>
	            </c:if>
	            <c:if test='${skinType eq "SRV"}'>
	            	<li><a>설문 관리</a></li>
	            </c:if>
	            <li><a>스킨 관리</a></li>
	        </ul>
	    </div>
	    <div class="container">
	        <h2 class="con-tit">스킨 관리</h2>
	        <div class="section-wrap res">
	        	<div class="section">
		            <form action="" id="frm" name="frm" method="post">
		            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
						<input type="hidden" name="searchCnd" id="searchCnd" value="${searchVO.searchCnd}">
						<input type="hidden" name="searchWrd" id="searchWrd" value="${searchVO.searchWrd}">
		            	<input type="hidden" name="skinType" id="skinType" value="${skinType}" >
			        	<c:if test="${searchVO.command eq 'update' }">
		            		<input type="hidden" name="skinId" value="${skinVO.skinId }" >
		            	</c:if>
		            	
		                <fieldset>
		                    <p class="section-tit">스킨 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                    	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">스킨 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col style="width:20%" class="change1">
		                                <col style="width:80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">스킨 이름 <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" title="스킨 이름 입력" name="skinName" id="skinName" value="${skinVO.skinName }"></td>
			                            </tr>
			                            <tr>
			                                <th class="tl">List <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" title="html 코드 입력" name="skinList" id="skinList" style="border-color: #ccc; min-height: 300px;"><c:out value="${skinVO.skinList }"/></textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">View <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" title="html 코드 입력" name="skinView" id="skinView" style="border-color: #ccc; min-height: 500px;"><c:out value="${skinVO.skinView }"/></textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">Form <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" title="html 코드 입력" name="skinForm" id="skinForm" style="border-color: #ccc; min-height: 500px;"><c:out value="${skinVO.skinForm }"/></textarea>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </fieldset>
		            </form>
	        	</div>
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
            <script>
                var txtLenght = $('.txt.bg-line').length;
				var editor = [];
                for(i=0;i<=txtLenght;i++){
                    var code = $('.txt.bg-line')[i];
                    this["editor"+i] = CodeMirror.fromTextArea(code, {
                        mode: "text/html",
                        lineNumbers: true,
                        lineWrapping: true,
                        matchBrackets: true,
                        styleActiveLine: true,
                        matchTags: {bothTags: true},
                        theme: "eclipse",
                        val: code.value
                    });
                }
            </script>
	    </div>
	</div>