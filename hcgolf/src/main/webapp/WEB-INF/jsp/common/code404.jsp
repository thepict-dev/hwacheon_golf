<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Error | 서비스 이용에 불편을 드려 죄송합니다.</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="icon" href="/_user/ko/img/layout/16xfavi.ico" sizes="16x16">
    <link rel="icon" href="/_user/ko/img/layout/72xfavi.ico" sizes="72x72">
    <link rel="icon" href="/_user/ko/img/layout/114xfavi.ico" sizes="114x114">
    <link rel="shortcut icon" href="/_user/ko/img/layout/114xfavi.ico" type="image/x-icon">
    <link rel="stylesheet" href="/_admin/css/common.css">
    <link rel="stylesheet" href="/_admin/css/layout.css">
    <script src="/_admin/js/jquery-3.4.1.min.js"></script>
	<script>
    	function fn_back(){
    		//location.href = "/";
    		history.back();
    	}
    </script>
</head>
<body>
	<div class="error-wrap">
	    <div class="error">
	        <div class="error-top">
	            <img src="/_admin/img/error.png" alt="오류">
	        </div>
	        <div class="error-bottom">
	            <p>서비스 이용에 불편을 드려 죄송합니다.</p>
	            <span>현재 페이지가 존재하지 않거나, 이용할 수 없는 페이지입니다.</span>
	            <button type="button" class="go-main" onclick="javascript:fn_back();">이전 페이지로 가기</button>
	        </div>
	    </div>
	</div>
</body>
</html>
