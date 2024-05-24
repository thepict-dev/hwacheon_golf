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
<%@ page import = "egovframework.com.cmm.service.FileVO"%>
<%@ page import = "java.util.*" %>
	<script>
		function fn_list(){
			$("#flag").val("list");
			$("#frm").submit();
		}

		function fn_egov_downFile(atchFileId, fileSn){
			window.open("/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"");
		}
	</script>

		<div class="calendar-wrap noto">
			<div data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
				<div class="boardview">
					<div class="boardview-top">
						<div class="boardview-tit">
						<%
							String noticeAt = bb.getNoticeAt()==null?"1":bb.getNoticeAt();
							if(noticeAt.equals("Y")){
						%>
							<em class="ico-noti type1">공지</em>
						<%
							}
						%>
						<h3><%=bb.getNttSj()%></h3></div>
						<div class="boardview-info">
							<p><span class="ico-time">등록일 : </span><%=bb.getFrstRegisterPnttm()%></p>
							<p><i class="ico-view"></i><%=bb.getInqireCo()%></p>
						</div>
					</div>
					<div class="boardview-con">
						<div class="boardview-file">
							<ul>
								<%
									List<FileVO> fileList = bb.getFileList();
									for(int i=0; i < fileList.size(); i++){
								%>
								<li><a href="javascript:fn_egov_downFile('<%=fileList.get(i).getAtchFileId()%>','<%=fileList.get(i).getFileSn()%>')"><span class="ico-file"></span><span class="file-name"><%=fileList.get(i).getOrignlFileNm()%></span></a></li>
								<%
									}
								%>
							</ul>
						</div>
						<p>
							<img src='<c:url value='/_cmm/fms/getImage.do'/>?atchFileId=<c:out value="<%=fileList.get(0).getAtchFileId()%>"/>&imgFlag=thumbnail&thumbnail=true' alt="<%=fileList.get(0).getOrignlFileNm()%>"/>
							<%=bb.getNttCn()%>
						</p>
					</div>
				</div>
				<div class="btn-wrap board-btn">
					<button type="button" onclick="javascript:fn_list();" class="brown">목록</button>
				</div>
				<form id="frm" name="frm" method="post" action="<%=mb.getMenuUrl()%>">
					<input type="hidden" name="flag" id="flag" value="view">
					<input type="hidden" name="bbsId" value="<%=bb.getBbsId()%>">
					<input type="hidden" name="pageIndex" value="<%=bb.getPageIndex()%>">
					<input type="hidden" name="searchCnd" value="<%=bb.getSearchCnd()%>">
					<input type="hidden" name="searchWrd" value="<%=bb.getSearchWrd()%>">
					<input type="hidden" name="searchCate1" value="<%=bb.getSearchCate1()%>">
					<input type="hidden" name="returnUrl" id="returnUrl" value="">
				 </form>
			</div>
		</div>
	</div>