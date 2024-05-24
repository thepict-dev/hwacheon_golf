<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.admin.service.AdminVO"%>
<%@ page import="org.springframework.web.util.UrlPathHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	// 관리자 로그인 세션
	AdminVO user = (AdminVO) request.getSession().getAttribute("adminVO");

	UrlPathHelper urlPathHelper = new UrlPathHelper();
	String originalURL = urlPathHelper.getOriginatingRequestUri(request);
	String url[] = originalURL.split("/");
	String depth1 = "";
	String depth2 = "";
	depth1 = url[url.length-2];
	depth2 = url[url.length-1];
%>
	<script>
		console.log("originalURL::<%=originalURL%>");
		console.log("depth1::<%=depth1%>");
		console.log("depth2::<%=depth2%>");
		
		function fn_logout(){
			var msg = confirm("로그아웃 하시겠습니까?");
			if(msg){
				location.href = "/_admin/adminLogout.do";
			}
		}
	</script>
	
	
	<%
		if(user != null){
	%>
			<div class="wrapper">
			    <header class="header">
			        <h1 class="logo"><a href="/_admin/main.do"><img src="/_admin/img/admin_logo.png" alt="로고" style="max-width:165px;"></a></h1>
			        <ul class="util">
			            <li><a href="/_admin/main.do"><img src="/_admin/img/home.png" alt="아이콘 홈">Home</a></li>
			            <li><a href="#lnk" onclick="fn_logout();"><img src="/_admin/img/logout.png" alt="아이콘 로그아웃">Logout</a></li>
			        </ul>
			    </header>
			    
				<div class="menu-wrap">
					<div class="menu">
						<ul class="tab-list">
						
							<%if(depth1 != null && depth1.equals("secure")){ %>
							<li class="active">
								<a href="#none" class="sec"><i class="icon sec"></i><span>보안 관리</span></a>
								<ul class="dep2" style="display: block;">
							<%} else {%>
							<li>
								<a href="#none" class="sec"><i class="icon sec"></i><span>보안 관리</span></a>
								<ul class="dep2">
							<%}%>
									<%-- <li><a href="/_admin/secure/defaultForm.do">관리자 기본설정</a></li> --%>
									<li><a href="/_admin/secure/adminList.do">관리자 관리</a></li>
									<li><a href="/_admin/secure/accessList.do">접근제한 관리</a></li>
								</ul> 
							</li>
							
							<%if(user.getAdminId().equals("admin")){ %>
								<%if(depth1 != null && depth1.equals("site")){ %>
								<li class="active">
									<a href="#none" class="site"><i class="icon site"></i><span>사이트 관리</span></a>
									<ul class="dep2" style="display: block;">
								<%} else {%>
								<li>
									<a href="#none" class="site"><i class="icon site"></i><span>사이트 관리</span></a>
									<ul class="dep2">
								<%} %>
										<li><a href="/_admin/site/siteForm.do">사이트 기본설정</a></li>
										<li><a href="/_admin/site/layoutList.do">레이아웃 관리</a></li>
										<li><a href="/_admin/site/menuList.do">메뉴 관리</a></li>
										<li><a href="/_admin/site/contentsList.do">콘텐츠 관리</a></li>
										<li><a href="/_admin/site/atchList.do">첨부파일 관리</a></li>
										<%-- <li><a href="/_admin/site/analyticsList.do">애널리틱스 관리</a></li> --%>
									</ul>
								</li>

								<%if(depth1 != null && depth1.equals("code")){ %>
								<li class="active">
									<a href="/_admin/code/codeMasterList.do" class="site"><i class="icon site"></i><span>코드 관리</span></a>
								<%} else {%>
								<li>
									<a href="/_admin/code/codeMasterList.do" class="site"><i class="icon site"></i><span>코드 관리</span></a>
								<%} %>
								</li>
							<%} %>
							
							<%-- 
							<%if(depth1 != null && depth1.equals("member")){ %>
							<li class="active">
								<a href="#none" class="site"><i class="icon member"></i><span>회원 관리</span></a>
								<ul class="dep2" style="display: block;">
							<%} else {%>
							<li>
								<a href="#none" class="site"><i class="icon member"></i><span>회원 관리</span></a>
								<ul class="dep2">
							<%}%>
									<li><a href="/_admin/member/memberList.do">회원 관리</a></li>
									<li><a href="/_admin/member/departmentList.do">소속 관리</a></li>
								</ul>
							</li>
						 	--%>
						 	
							<%if(depth1 != null && (depth1.equals("popup") || depth1.equals("contact"))){ %>
							<li class="active">
								<a href="#none" class="con"><i class="icon con"></i><span>메인 관리</span></a>
								<ul class="dep2" style="display: block;">
							<%} else { %>
							<li>
								<a href="#none" class="con"><i class="icon con"></i><span>메인 관리</span></a>
								<ul class="dep2">
							<%} %>
									<%-- <li><a href="/_admin/popup/infozoneList.do">인포존 관리</a></li> --%>
									<li><a href="/_admin/popup/popupList.do">팝업 관리</a></li>
									<%-- <li><a href="/_admin/popup/bannerList.do">배너 관리</a></li> --%>
								</ul>
							</li>
							
							<%if(depth1 != null && depth1.equals("event")){ %>
							<li class="active">
								<a href="#none" class="board"><i class="icon board"></i><span>예약 관리</span></a>
								<ul class="dep2" style="display: block;">
							<%} else {%>
							<li>
								<a href="#none" class="board"><i class="icon board"></i><span>예약 관리</span></a>
								<ul class="dep2">
							<%} %>
									<li><a href="/_admin/event/eventList.do">예약 관리</a></li>
								</ul>
							</li>

							<%if(depth1 != null && depth1.equals("board")){ %>
							<li class="active">
								<a href="#none" class="board"><i class="icon board"></i><span>게시판 관리</span></a>
								<ul class="dep2" style="display: block;">
							<%} else {%>
							<li>
								<a href="#none" class="board"><i class="icon board"></i><span>게시판 관리</span></a>
								<ul class="dep2">
							<%} %>
									<li><a href="/_admin/board/bbsMasterList.do">게시판 관리</a></li>
									<li><a href="/_admin/board/bbsList.do">게시물 관리</a></li>
									<%if(user.getAdminId().equals("admin")){ %>
										<li><a href="/_admin/board/skinList.do">스킨 관리</a></li>
									<%} %>
								</ul>
							</li>
							
						</ul>
					</div>
					<button type="button" class="menu-btn-web on">
					    <span></span>
					</button>
				</div>
	<%
		}
	%>
	