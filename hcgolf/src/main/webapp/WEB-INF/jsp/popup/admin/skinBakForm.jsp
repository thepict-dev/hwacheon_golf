<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<html>
	<head lang="ko">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>스킨 백업 | 관리자</title>
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
		                    <td class="tl"><input type="text" value="${skinVO.skinName }" readonly="readonly"></td>
		                </tr>
		                <tr>
		                    <th class="tl">백업일시</th>
		                    <td class="tl"><input type="text" value="${skinVO.updDate }" readonly="readonly"></td>
		                </tr>
		                </tbody>
		            </table>
		        </div>
		        <form>
		            <fieldset>
		            	<div class="section-wrap">
			                <div class="section">
			                    <p class="section-tit">스킨 정보</p>
			                    <div class="tbl-wrap new-tbl">
			                        <table class="tbl03">
			                            <caption class="blind">스킨 정보 등록 테이블</caption>
			                            <colgroup>
			                                <col style="width:20%">
			                                <col style="width:80%">
			                            </colgroup>
				                        <tbody>
				                            <tr>
				                                <th class="tl">스킨 이름 <em class="essential">*</em></th>
				                                <td class="tl"><input type="text" title="스킨 이름 입력" name="skinName" id="skinName" value="${skinVO.skinName }" readonly="readonly"></td>
				                            </tr>
				                            <tr>
				                                <th class="tl">List <em class="essential">*</em></th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" title="html 코드 입력" name="skinList" id="skinList" style="border-color: #ccc; min-height: 300px;" readonly="readonly"><c:out value="${skinVO.skinList }"/></textarea>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">View <em class="essential">*</em></th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" title="html 코드 입력" name="skinView" id="skinView" style="border-color: #ccc; min-height: 500px;" readonly="readonly"><c:out value="${skinVO.skinView }"/></textarea>
				                                </td>
				                            </tr>
				                            <tr>
				                                <th class="tl">Form <em class="essential">*</em></th>
				                                <td class="text-box info-ir">
				                                    <textarea class="txt bg-line" title="html 코드 입력" name="skinForm" id="skinForm" style="border-color: #ccc; min-height: 500px;" readonly="readonly"><c:out value="${skinVO.skinForm }"/></textarea>
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