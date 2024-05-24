<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


	<script>
		function fn_submit(v_command){

			if ($("#layoutName").val() == "") {
				alert("레이아웃 이름을 입력해주세요.");
				$("#layoutName").focus();
				return;
			}
			if (editor0.getValue() == "") {
				alert("head 내용을 입력해주세요.");
				editor0.focus();
				return;
			}
			if (editor1.getValue() == "") {
				alert("header 내용을 입력해주세요.");
				editor1.focus();
				return;
			}
			if (editor2.getValue() == "") {
				alert("footer 내용을 입력해주세요.");
				editor2.focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/layoutInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/layoutUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/site/layoutList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/site/layoutList.do");
			$("#frm").submit();
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트관리</a></li>
	            <li><a href="#lnk">레이아웃 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">레이아웃 관리</h2>
	        <div class="section-wrap res">
            	<div class="section">
		            <form action="" id="frm" name="frm" method="post">
		            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
			        	<c:if test="${searchVO.command eq 'update' }">
		            		<input type="hidden" name="layoutId" value="${layoutVO.layoutId }" >
		            	</c:if>
		            	
		                <fieldset>
		                    <p class="section-tit">레이아웃 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                    	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03 iptwid">
		                            <caption class="blind">레이아웃 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">레이아웃 이름 <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" title="레이아웃 이름 입력" name="layoutName" id="layoutName" value="${layoutVO.layoutName }"></td>
			                            </tr>
			                            <tr>
			                                <th class="tl">Head <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" title="html 코드 입력" name="layoutHead" id="layoutHead" style="border-color: #ccc; min-height: 300px;"><c:out value="${layoutVO.layoutHead }"/></textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">Header <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" title="html 코드 입력" name="layoutHeader" id="layoutHeader" style="border-color: #ccc; min-height: 500px;"><c:out value="${layoutVO.layoutHeader }"/></textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">Footer <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" title="html 코드 입력" name="layoutFooter" id="layoutFooter" style="border-color: #ccc; min-height: 500px;"><c:out value="${layoutVO.layoutFooter }"/></textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">레이아웃 설명</th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt" title="html 코드 입력" name="layoutDescription" id="layoutDescription">${layoutVO.layoutDescription }</textarea>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </fieldset>
		            </form>
				</div>
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