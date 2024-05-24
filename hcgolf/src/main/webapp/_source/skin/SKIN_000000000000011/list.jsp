<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.board.web.BoardBundle"%>			
<%@ page import="egovframework.com.cop.bbs.service.BoardVO"%>			
<%@ page import="egovframework.com.cop.bbs.service.BoardMasterVO"%>		
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>		
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>		
<%												
	BoardBundle bb = new BoardBundle(request);		
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
<%@ page import = "egovframework.com.cop.bbs.service.BoardItemVO"%>
<%@ page import = "egovframework.com.cmm.service.FileVO"%>
<%@ page import = "java.util.*" %>

	<script>
		function fn_list(pageIndex){
			$("#pageIndex").val(pageIndex);
			$("#listFrm").submit();
		}

		function fn_view(nttId){
			$("#nttId").val(nttId);
			$("#flag").val("view");
			$("#listFrm").submit();
		}
	</script>

		<div class="calendar-wrap noto">
			<div data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
				<form action="<%=mb.getMenuUrl()%>" name="listFrm" id="listFrm" method="post">
					<input type="hidden" name="bbsId" value="<%=bb.getBbsId()%>" />
					<input type="hidden" id="pageIndex" name="pageIndex" value="<%=bb.getPageIndex()%>" />
					<input type="hidden" id="flag" name="flag" value="list" />
					<input type="hidden" id="nttId" name="nttId" value="0" />
					<fieldset>
						<legend class="screen-out">검색어 입력 폼</legend>
						<div class="search-box tblselect">
							<select name="searchCnd" id="searchCnd" class="sel">
								<option <%if(bb.getSearchCnd().equals("ALL")){%>selected="selected"<%}%> value="ALL">전체</option>
								<option <%if(bb.getSearchCnd().equals("NTT_SJ")){%>selected="selected"<%}%> value="NTT_SJ">제목</option>
								<option <%if(bb.getSearchCnd().equals("NTT_CN")){%>selected="selected"<%}%> value="NTT_CN">내용</option>
							</select>
							<div class="input-box">
								<label>
									<input type="text" id="searchWrd" name="searchWrd" placeholder="내용을 입력해주세요." value="<%=bb.getSearchWrd()%>">
								</label>
								<button type="button" onclick="javascript:fn_list('1')" class="btn-search">검색</button>
							</div>
						</div>
					</fieldset>
				</form>
				<div class="section-inner">
					<p class="post">총 <span class="total-post"><%=bb.getBoardDataCnt()%></span>건의 게시물이 있습니다.</p>
					<div class="box-list-wrap">
						<%
							if(bb.getListCount() <= 0){
						%>
							등록된 게시물이 없습니다.
						<%
							}
							for(int i=0; i<bb.getListCount() && i<bb.getPageUnit(); i++) {
								BoardVO board = bb.getBoardDataVOList(i);
								bb.setDataVO(board);
						%>
								<div class="box">
									<a href="javascript:fn_view('<%=bb.getNttId()%>')">
										<div class="img-area">
											<%if(bb.getAtchFileId() != null && !bb.getAtchFileId().equals("")){%>
												<img src="/_cmm/fms/getImage.do?atchFileId=<%=bb.getAtchFileId()%>&imgFlag=thumbnail&thumbnail=true" alt="<%=bb.getNttSj()%>">
											<%}else{%>
												<img src="/_admin/img/no_img.jpg" alt="이미지가 없습니다.">
											<%}%>
										</div>
										<div class="box-con">
											<p class="box-tit dotdot"><%=bb.getNttSj()%></p>
											<p class="date"><%=bb.getFrstRegisterPnttm()%></p>
										</div>
									</a>
								</div>
						<%
							}
						%>
					</div>
					<div class="pager">
						<%String pageIndex=(request.getParameter("pageIndex")==null?"1":request.getParameter("pageIndex")); %>
						<%=bb.getPaging(10).replace("><","> <").replace(">" + pageIndex + "</a>",">" + pageIndex + "</a>")%>
					</div>
				</div>
			</div>
		</div>
	</div>