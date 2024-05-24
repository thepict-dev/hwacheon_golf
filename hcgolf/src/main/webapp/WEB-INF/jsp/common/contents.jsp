<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	// 현재 도메인 구하기
	String sDomain = request.getScheme()+"://"+request.getServerName();
%>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title><c:choose><c:when test="${menuVO.seoNaviTitle ne ''}">${menuVO.seoNaviTitle}</c:when><c:otherwise><c:choose><c:when test="${menuVO.seoPageTitle ne ''}">${menuVO.seoPageTitle}</c:when><c:otherwise>${menuVO.menuTitle }</c:otherwise></c:choose></c:otherwise></c:choose><c:if test="${mainFlag ne 'Y'}"> | ${mainVO.siteTitle }</c:if></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<meta name="description" content="${menuVO.seoDescription }">
		<meta name="keywords" content="${menuVO.seoKeywords }">
		
		<meta property="og:title" content="<c:choose><c:when test="${menuVO.seoNaviTitle ne ''}">${menuVO.seoNaviTitle}</c:when><c:otherwise><c:choose><c:when test="${menuVO.seoPageTitle ne ''}">${menuVO.seoPageTitle}</c:when><c:otherwise>${menuVO.menuTitle }</c:otherwise></c:choose></c:otherwise></c:choose><c:if test="${mainFlag ne 'Y'}"> | ${mainVO.siteTitle }</c:if>"/>
		<c:if test="${mainFlag ne 'Y'}"><meta property="og:url" content="<%=sDomain%>${currentUri }"/></c:if>
		<c:if test="${mainFlag eq 'Y'}"><meta property="og:url" content="<%=sDomain%>"/></c:if>
		<meta property="og:type" content="${menuVO.seoOgType }"> 
		<meta property="og:image" content="${menuVO.seoImageUrl }"/>
		<meta property="og:description" content="${menuVO.seoDescription }"/>
		<%-- 
		<meta name="twitter:title" content="<c:choose><c:when test="${menuVO.seoNaviTitle ne ''}">${menuVO.seoNaviTitle}</c:when><c:otherwise><c:choose><c:when test="${menuVO.seoPageTitle ne ''}">${menuVO.seoPageTitle}</c:when><c:otherwise>${menuVO.menuTitle }</c:otherwise></c:choose></c:otherwise></c:choose><c:if test="${mainFlag ne 'Y'}"> | ${mainVO.siteTitle }</c:if>"/>
		<meta name="twitter:description" content="${menuVO.seoDescription }"/>
		<meta name="twitter:image" content="${menuVO.seoImageUrl }"/>
		<meta name="twitter:card" content="${menuVO.seoTwitterCard }"> 
		--%>
		<!-- :::: head s :::: -->
			<c:import url="/_source/layout/${layoutVO.layoutId}/head.jsp"></c:import>
		<!-- :::: head e :::: -->
		
		<!-- :::: analytics head s :::: -->
		<c:forEach var="result" items="${analyticsList}" varStatus="status">
			${result.analyticsHead }
		</c:forEach>
		<!-- :::: analytics head e :::: -->
	</head>
	<body>
		<!-- :::: header s :::: -->
			<c:import url="/_source/layout/${layoutVO.layoutId}/header.jsp"></c:import>
		<!-- :::: header e :::: -->
		
		<!-- :::: contents s :::: -->
			<c:import url="/_source/contents/${contentsVO.contentsId}.jsp"></c:import>
		<!-- :::: contents e :::: -->
		
		<!-- :::: footer s :::: -->
			<c:import url="/_source/layout/${layoutVO.layoutId}/footer.jsp"></c:import>
		<!-- :::: footer e :::: -->
		
		<!-- :::: analytics body s :::: -->
		<c:forEach var="result" items="${analyticsList}" varStatus="status">
			${result.analyticsBody }
		</c:forEach>
		<!-- :::: analytics body e :::: -->
	</body>
</html>