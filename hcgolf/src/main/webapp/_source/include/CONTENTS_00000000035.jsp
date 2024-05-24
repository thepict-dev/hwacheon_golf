<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%												
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
<%@ page import="java.util.List"%>
	<div class="sub-container">
		<div class="sub-visual">
			<h2><%=mb.getMenuTitle()%></h2>
		</div>
		<ul class="breadcrumb noto">
			<li class="btn-home"><a href="#lnk">home</a></li>
			<li class="dep1">
				<a href="#lnk"><%=mb.getMenuIdTitle(mb.getMenuIdDepth1())%></a>
				<ul>
					<%
						// 1dep 조회
						List<MenuVO> dep1List = mb.getMenuListDepth("dep1");
						if(dep1List.size() > 0) {
							for(int i=0; i<dep1List.size(); i++){
								String menuIdDepth1 =  mb.getMenuIdDepth1() == null ? "" : mb.getMenuIdDepth1(); 
					%>
								<li>
									<a href="/<%=dep1List.get(i).getMenuNameDepth1()%>"><%=dep1List.get(i).getMenuTitle() %></a>
								</li>
					<%	
							}
						}
					%>
				</ul>
			</li>

			<%if(mb.getMenuIdDepth2() != null){ %>
				<li class="dep2">
				<a href="#lnk"><%=mb.getMenuIdTitle(mb.getMenuIdDepth2())%></a>
					<ul>
						<%
							// 2dep 조회
							List<MenuVO> dep2LnbList = mb.getMenuLowList(mb.getMenuIdDepth1());
							if(dep2LnbList.size() > 0) {
								for(int i=0; i<dep2LnbList.size(); i++){
									String menuIdDepth2 =  mb.getMenuIdDepth2() == null ? "" : mb.getMenuIdDepth2(); 
						%>
									<li>
										<a href="/<%=dep2LnbList.get(i).getMenuNameDepth1()%>/<%=dep2LnbList.get(i).getMenuNameDepth2()%>" <%if(dep2LnbList.get(i).getMenuNameDepth2().equals("facility") || dep2LnbList.get(i).getMenuNameDepth2().equals("vr")){%>target="_blank" title="새창 열림"<%}%>><%=dep2LnbList.get(i).getMenuTitle() %></a>
									</li>
						<%	
								}
							}
						%>
					</ul>
				</li>
			<%} %>
		</ul>