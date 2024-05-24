<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
	<head lang="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>콘텐츠 백업 | 관리자</title>
	    <link rel="stylesheet" href="/_admin/css/codemirror.css">
	    <link rel="stylesheet" href="/_admin/css/common.css">
	    <link rel="stylesheet" href="/_admin/css/layout.css">
	    <link rel="stylesheet" href="/_admin/css/datepicker.css">
	    <link rel="stylesheet" href="/_admin/css/jquery.numberedtextarea.css">
	    <script src="/_admin/js/jquery-3.4.1.min.js"></script>
	    <script src="/_admin/js/jquery-ui-datepicker.min.js"></script>
	    <script src="/_admin/js/codemirror.js"></script>
	    <script src="/_admin/js/xml-fold.js"></script>
	    <script src="/_admin/js/matchtags.js"></script>
	    <script src="/_admin/js/active-line.js"></script>
	    <script src="/_admin/js/mode-xml.js"></script>
	    <script src="/_admin/js/layout.js"></script>
	    <script src="/_admin/js/sub.js"></script>
	    <script src="/_admin/js/jquery.numberedtextarea.js"></script>
	</head>
	<body>
		<div>
			<div class="container popup">
		        <h2 class="con-tit" style="font-size: 20px; text-align: left;">백업정보</h2>
		        <div class="tbl-wrap mt10 new-tbl">
		            <table class="tbl03">
		                <caption class="blind">백업정보 테이블</caption>
		                <colgroup>
		                    <col style="width:20%">
		                    <col style="width:80%">
		                </colgroup>
		                <tbody>
		                <tr>
		                    <th class="tl">제목</th>
		                    <td class="tl"><input type="text" value="${contentsVO.contentsName }" readonly="readonly"></td>
		                </tr>
		                <tr>
		                    <th class="tl">백업일시</th>
		                    <td class="tl"><input type="text" value="${contentsVO.updDate }" readonly="readonly"></td>
		                </tr>
		                </tbody>
		            </table>
		        </div>
	            <form action="" id="frm" name="frm" method="post">
	            	
	            	
	                <fieldset>
	                	<div class="section-wrap">
		                	<div class="section">
			                    <p class="section-tit">콘텐츠 정보</p>
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">콘텐츠 정보 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
			                            <tbody>
				                            <tr>
				                                <th class="tl">콘텐츠 이름</th>
				                                <td class="tl"><input type="text" title="콘텐츠 이름 입력" name="contentsName" id="contentsName" value="${contentsVO.contentsName }" readonly="readonly"></td>
				                            </tr>
			                            </tbody>
			                        </table>
			                    </div>
			                </div>
		                	<div class="section">
			                    <p class="section-tit">레이아웃 정보</p>
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">레이아웃 정보 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
			                            <tbody>
				                            <tr>
				                                <th class="tl">레이아웃 <em class="essential">*</em></th>
				                                <td class="tl">
				                                    <select name="layoutId" id="layoutId">
				                                        <option value="" disabled="disabled">선택</option>
														<c:forEach var="layoutResult" items="${layoutList}" varStatus="status">
				                                        	<option value="${layoutResult.layoutId }" <c:if test="${contentsVO.layoutId eq layoutResult.layoutId}">selected="selected"</c:if> disabled="disabled">${layoutResult.layoutName }</option>
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
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">콘텐츠 타입 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
			                            <tbody>
				                            <tr>
				                                <th class="tl">콘텐츠 타입 <em class="essential">*</em></th>
				                                <td class="tl">
				                                    <select name="contentsType" id="contentsType">
				                                        <option value="" disabled="disabled">선택</option>
				                                        <option value="CON" <c:if test="${contentsVO.contentsType eq 'CON' }">selected="selected"</c:if> disabled="disabled">소스코드</option>
				                                        <option value="BBS" <c:if test="${contentsVO.contentsType eq 'BBS' }">selected="selected"</c:if> disabled="disabled">게시판</option>
				                                        <option value="URL" <c:if test="${contentsVO.contentsType eq 'URL' }">selected="selected"</c:if> disabled="disabled">URL링크</option>
				                                    </select>
				                                </td>
				                            </tr>
			                            </tbody>
			                        </table>
			                    </div>
			                </div>
			                <div class="section type-con" id="typeCon" <c:if test="${contentsVO.contentsType ne 'CON' }">style="display:none;"</c:if>>
			                    <p class="section-tit">콘텐츠</p>
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">콘텐츠 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
			                            <tbody>
			                        		<tr>
			                        			<th class="tl">콘텐츠 스타일<em class="essential">*</em></th>
			                        			<td class="tl">
			                        				<select name="contentsStyle" id="contentsStyle">
				                                        <option value="" disabled="disabled">선택</option>
				                                        <option value="jsp"<c:if test="${contentsVO.contentsStyle eq 'jsp'}">selected="selected"</c:if> disabled="disabled">페이지</option>
				                                        <option value="css"<c:if test="${contentsVO.contentsStyle eq 'css'}">selected="selected"</c:if> disabled="disabled">CSS</option>
				                                        <option value="js"<c:if test="${contentsVO.contentsStyle eq 'js'}">selected="selected"</c:if> disabled="disabled">JS</option>
				                                    </select>
				                                </td>
			                        		</tr>
				                            <tr>
				                            	<th class="tl">콘텐츠</th>
				                            	<td class="text-box info-ir">
			                        				<textarea class="txt bg-line" name="contentsContent" id="contentsContent" title="콘텐츠 내용 입력" style="border-color: #ccc; min-height: 500px;" readonly="readonly">${contentsVO.contentsContent }</textarea>
			                        			</td>
			                        		</tr>
			                        	</tbody>
			                        </table>
			                    </div>
			                </div>
			                <div class="section type-board" id="typeBbs" <c:if test="${contentsVO.contentsType ne 'BBS' }">style="display:none;"</c:if>>
			                    <p class="section-tit">게시판</p>
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">게시판 타입 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
			                            <tbody>
				                            <tr>
				                                <th class="tl">공통 상단 콘텐츠</th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" name="bbsHeader" id="bbsHeader" title="게시판 공통 상단 콘텐츠 내용 입력" style="border-color: #ccc; min-height: 200px;" readonly="readonly">${contentsVO.bbsHeader }</textarea>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">게시판</th>
				                                <td class="tl">
				                                    <select name="bbsId" id="bbsId">
				                                        <option value="" disabled="disabled">선택</option>
														<c:forEach var="boardResult" items="${boardList}" varStatus="status">
				                                        	<option value="${boardResult.bbsId }" <c:if test="${contentsVO.bbsId eq boardResult.bbsId}">selected="selected"</c:if> disabled="disabled">${boardResult.bbsNm }</option>
				                                        </c:forEach>
				                                    </select>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">공통 하단 콘텐츠</th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" name="bbsFooter" id="bbsFooter" title="게시판 공통 하단 콘텐츠 내용 입력" style="border-color: #ccc; min-height: 200px;" readonly="readonly">${contentsVO.bbsFooter }</textarea>
				                                </td>
				                            </tr>
			                            </tbody>
			                        </table>
			                    </div>
			                </div>
			                <div class="section type-redirection" id="typeUrl" <c:if test="${contentsVO.contentsType ne 'URL' }">style="display:none;"</c:if>>
			                    <p class="section-tit">Redirection</p>
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">Redirection 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
			                            <tbody>
				                            <tr>
				                                <th class="tl">Redirection</th>
				                                <td class="tl"><input type="text" name="url" id="url" title="Redirection 입력" value="${contentsVO.url }" readonly="readonly"></td>
				                            </tr>
			                            </tbody>
			                        </table>
			                    </div>
			                </div>
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
	                </fieldset>
	            </form>
			    <div class="btn-box">
	                <a href="#lnk" onclick="self.close();" class="tbl-btn blue">닫기</a>
	            </div>
			</div>
		</div>
	</body>
</html>