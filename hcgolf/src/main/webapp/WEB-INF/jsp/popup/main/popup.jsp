<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${popupVO.popupTitle}</title>
	</head>
	
	<script>
		const target = "${popupVO.target}";
		const url = "${popupVO.url}";
		(function resizeWindow(){
			window.resizeBy(0, 37);
		})();
		function goUrl(){
			if (target == "_opener"){
				window.opener.document.location.href = url;
				self.close();
			} else if (target == "_blank"){
				window.open(url);
			} else if (target == "_self"){
				location.href = url;
			}
		}
		function setCookie(name){
			const expires = new Date();
			expires.setDate( new Date().getDate + 1);
			document.cookie = name + "=/; path=/; expires=" + expires.toGMTString() + ";";
			self.close();
		}
	</script>
	<body style="border:0; margin:0; padding:0; width:${popupVO.sizeWidth}px; height:${popupVO.sizeHeight}px; text-dectortaion:none; overflow:hidden">
		<img src="<c:url value='/_cmm/fms/getImage.do'/>?atchFileId=<c:out value="${popupVO.atchFileId}"/>&thumbnail=true" <c:if test="${popupVO.altText ne ''}">alt="${popupVO.altText}"</c:if> <c:if test="${popupVO.url ne '' && popupVO.url ne '#'}">onclick="javascript:goUrl();" style="cursor:pointer"</c:if> />
		<div style="background-color: dimgrey; color: ghostwhite; padding: 0.5em; text-align:right">
			<label for="setCookie">오늘 하루 동안 열지 않음</label>
			<input id="setCookie" type="checkbox" onclick="setCookie('CLOSE_${popupVO.popupId}');">
		</div>
	</body>
</html>