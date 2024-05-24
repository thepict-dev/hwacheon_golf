<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
	<head lang="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>레이아웃 백업 | 관리자</title>
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
		                    <td class="tl"><input type="text" value="${layoutVO.layoutName }" readonly="readonly"></td>
		                </tr>
		                <tr>
		                    <th class="tl">백업일시</th>
		                    <td class="tl"><input type="text" value="${layoutVO.updDate }" readonly="readonly"></td>
		                </tr>
		                </tbody>
		            </table>
		        </div>
		        <form>
		            <fieldset>
		                <div class="section-wrap">
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
				                                <th class="tl">레이아웃 이름</th>
				                                <td class="tl"><input type="text" title="레이아웃 이름 입력" name="layoutName" id="layoutName" value="${layoutVO.layoutName }" readonly="readonly"></td>
				                            </tr>
				                            <tr>
				                                <th class="tl">Head</th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" title="html 코드 입력" name="layoutHead" id="layoutHead" style="border-color: #ccc; min-height: 300px;" readonly="readonly">${layoutVO.layoutHead }</textarea>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">Header</th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" title="html 코드 입력" name="layoutHeader" id="layoutHeader" style="border-color: #ccc; min-height: 300px;" readonly="readonly">${layoutVO.layoutHeader }</textarea>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">Footer</th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" title="html 코드 입력" name="layoutFooter" id="layoutFooter" style="border-color: #ccc; min-height: 300px;" readonly="readonly">${layoutVO.layoutFooter }</textarea>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">레이아웃 설명</th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt" title="html 코드 입력" name="layoutDescription" id="layoutDescription" readonly="readonly">${layoutVO.layoutDescription }</textarea>
				                                </td>
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