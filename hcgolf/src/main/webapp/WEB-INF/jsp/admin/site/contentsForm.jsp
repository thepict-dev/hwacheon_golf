<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


	<script>
		$(document).ready(function(){
			$("#contentsType").change(function(){
				var v_type = $(this).val();
				if(v_type == "BBS"){
					$("#typeBbs").attr("style","display:block;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:none;");
				}else if(v_type == "CON"){
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:block;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:none;");
				}else if(v_type == "URL"){
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:block;");
					$("#typeMain").attr("style","display:none;");
				}else {
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:none;");
				}
			});
		});
		function fn_submit(v_command){

			if ($("#contentsName").val() == "") {
				alert("콘텐츠 이름을 입력해주세요.");
				$("#contentsName").focus();
				return;
			}
			if ($("#layoutId").val() == "") {
				alert("레이아웃을 선택해주세요.");
				$("#layoutId").focus();
				return;
			}
			if ($("#contentsType").val() == "") {
				alert("콘텐츠 타입을 선택해주세요.");
				$("#contentsType").focus();
				return;
			}
			if ($("#contentsType").val() == "CON" && $("#contentsStyle").val() == "") {
				alert("콘텐츠 스타일을 선택해주세요.");
				$("#contentsStyle").focus();
				return;
			}
			if ($("#contentsType").val() == "BBS" && $("#bbsId").val() == "") {
				alert("게시판을 선택해주세요.");
				$("#bbsId").focus();
				return;
			}
			if ($("#contentsType").val() == "URL" && $("#url").val() == "") {
				alert("URL링크를 입력해주세요.");
				$("#url").focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/contentsInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/contentsUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/site/contentsList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/site/contentsList.do");
			$("#frm").submit();
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트관리</a></li>
	            <li><a href="#lnk">콘텐츠 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">콘텐츠 관리</h2>
            <form action="" id="frm" name="frm" method="post">
            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
	        	<c:if test="${searchVO.command eq 'update' }">
            		<input type="hidden" name="contentsId" value="${contentsVO.contentsId }" >
            	</c:if>
            	
                <fieldset>
                	<div class="section-wrap res">
	                	<div class="section">
		                    <p class="section-tit">콘텐츠 정보</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">콘텐츠 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
							        	<c:if test="${searchVO.command eq 'update' }">
				                            <tr>
				                                <th class="tl">콘텐츠 ID <em class="essential">*</em></th>
				                                <td class="tl">${contentsVO.contentsId }</td>
				                            </tr>
						            	</c:if>
			                            <tr>
			                                <th class="tl">콘텐츠 이름 <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" title="콘텐츠 이름 입력" name="contentsName" id="contentsName" value="${contentsVO.contentsName }"></td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
	                	<div class="section">
		                    <p class="section-tit">레이아웃 정보</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">레이아웃 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">레이아웃 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <select name="layoutId" id="layoutId" style="width:50%;">
			                                        <option value="">선택</option>
													<c:forEach var="layoutResult" items="${layoutList}" varStatus="status">
			                                        	<option value="${layoutResult.layoutId }" <c:if test="${contentsVO.layoutId eq layoutResult.layoutId}">selected="selected"</c:if>>${layoutResult.layoutId } - ${layoutResult.layoutName }</option>
			                                        </c:forEach>
			                                    </select>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">콘텐츠 타입</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">콘텐츠 타입 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">콘텐츠 타입 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <select name="contentsType" id="contentsType">
			                                        <option value="">선택</option>
			                                        <option value="CON" <c:if test="${contentsVO.contentsType eq 'CON' }">selected="selected"</c:if>>소스코드</option>
			                                        <option value="BBS" <c:if test="${contentsVO.contentsType eq 'BBS' }">selected="selected"</c:if>>게시판</option>
			                                        <option value="URL" <c:if test="${contentsVO.contentsType eq 'URL' }">selected="selected"</c:if>>URL링크</option>
			                                    </select>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section type-con" id="typeCon" <c:if test="${contentsVO.contentsType ne 'CON' }">style="display:none;"</c:if>>
		                    <p class="section-tit">소스코드</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">소스코드 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
		                        		<tr>
		                        			<th class="tl">콘텐츠 스타일<em class="essential">*</em></th>
		                        			<td class="tl">
		                        				<select name="contentsStyle" id="contentsStyle">
			                                        <option value="">선택</option>
			                                        <option value="jsp"<c:if test="${contentsVO.contentsStyle eq 'jsp'}">selected="selected"</c:if>>페이지</option>
			                                        <option value="css"<c:if test="${contentsVO.contentsStyle eq 'css'}">selected="selected"</c:if>>CSS</option>
			                                        <option value="js"<c:if test="${contentsVO.contentsStyle eq 'js'}">selected="selected"</c:if>>JS</option>
			                                    </select>
			                                </td>
		                        		</tr>
			                            <tr>
			                            	<th class="tl">소스코드</th>
			                            	<td class="text-box info-ir">
		                        				<textarea class="txt bg-line" name="contentsContent" id="contentsContent" title="소스코드 내용 입력" style="border-color: #ccc; min-height: 500px;"><c:out value="${contentsVO.contentsContent }"/></textarea>
		                        			</td>
		                        		</tr>
		                        	</tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section type-board" id="typeBbs" <c:if test="${contentsVO.contentsType ne 'BBS' }">style="display:none;"</c:if>>
		                    <p class="section-tit">게시판</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">게시판 타입 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">공통 상단 콘텐츠</th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" name="bbsHeader" id="bbsHeader" title="게시판 공통 상단 콘텐츠 내용 입력" style="border-color: #ccc; min-height: 200px;">${contentsVO.bbsHeader }</textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <select name="bbsId" id="bbsId">
			                                        <option value="">선택</option>
													<c:forEach var="boardResult" items="${boardList}" varStatus="status">
			                                        	<option value="${boardResult.bbsId }" <c:if test="${contentsVO.bbsId eq boardResult.bbsId}">selected="selected"</c:if>>${boardResult.bbsId } - ${boardResult.bbsNm }</option>
			                                        </c:forEach>
			                                    </select>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">공통 하단 콘텐츠</th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt bg-line" name="bbsFooter" id="bbsFooter" title="게시판 공통 하단 콘텐츠 내용 입력" style="border-color: #ccc; min-height: 200px;">${contentsVO.bbsFooter }</textarea>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section type-redirection" id="typeUrl" <c:if test="${contentsVO.contentsType ne 'URL' }">style="display:none;"</c:if>>
		                    <p class="section-tit">URL링크</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">URL링크 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">URL 링크 <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" name="url" id="url" title="URL링크 입력" value="${contentsVO.url }"></td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
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
                </fieldset>
            </form>
	        </div>
            <script>
                var txtLenght = $('.txt.bg-line').length;

                for(i=0;i<=txtLenght;i++){
                    var code = $('.txt.bg-line')[i];
                    var editor = CodeMirror.fromTextArea(code, {
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