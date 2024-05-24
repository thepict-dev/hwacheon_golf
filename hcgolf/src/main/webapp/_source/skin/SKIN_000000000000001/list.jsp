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

<%
	List<BoardItemVO> itemList = bb.getBoardItemList();
%>

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
					<div class="tbl-wrap">
						<table class="tbl03 tc">
							<caption class="blind">번호, 제목, 첨부파일, 등록일, 조회 순으로 안내합니다.</caption>
							<colgroup>
								<%
								for(int i = 0; i < itemList.size(); i++){
								%>
									<%
									if(itemList.get(i).getFieldId().equals("ROWNUM")){
									%>
										<col class="col wid90 <%if(itemList.get(i).getMobFlag().equals("Y")){%>colnone<%}%>" width="<%=itemList.get(i).getItemPercent()%>%">
									<%
									}else if(itemList.get(i).getFieldId().equals("REG_DATE") || itemList.get(i).getFieldId().equals("ATCH_FILE_ID")){
									%>
										<col class="col wid120 <%if(itemList.get(i).getMobFlag().equals("Y")){%>colnone<%}%>" width="<%=itemList.get(i).getItemPercent()%>%">
									<%
									}else{
									%>
										<col class="col <%if(itemList.get(i).getMobFlag().equals("Y")){%>colnone<%}%>" width="<%=itemList.get(i).getItemPercent()%>%">
									<%
									}
									%>
								<%
								}
								%>
							</colgroup>
							<tbody>
								<%
									if(bb.getListCount() <= 0){
								%>
									<tr>
										<td colspan="<%=itemList.size()%>">등록된 게시글이 없습니다.</td>
									</tr>
								<%
									}
									for(int i=0; i<bb.getListCount() && i<bb.getPageUnit(); i++) {
										BoardVO board = bb.getBoardDataVOList(i);
										bb.setDataVO(board);
										String noticeAt = bb.getNoticeAt()==null?"1":bb.getNoticeAt();
										if(noticeAt.equals("Y")){
								%>
										<tr>
										<%
											for(int j = 0; j < itemList.size(); j++){
												if(itemList.get(j).getFieldId().equals("ROWNUM")){
										%>
												<td class="col wid90 noti <%if(itemList.get(i).getMobFlag().equals("Y")){%>colnone<%}%>"><em class="ico-noti">공지</em></td>
										<%
												} else if(itemList.get(j).getFieldId().equals("NTT_SJ")){
										%>
												<td class="col wid* post-tit tl">
													<a href="javascript:fn_view('<%=bb.getNttId()%>')" class="dot"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></a>
												</td>
										<%
												} else if(itemList.get(j).getFieldId().equals("ATCH_FILE_ID")){
										%>
												<td class="col <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%if(!bb.getDataValue(itemList.get(j).getFieldId(),board).equals("")){%><i class="ico-addfile">첨부파일</i><%}%></td>
										<%
												} else if(itemList.get(j).getFieldId().equals("REG_DATE")){
										%>
												<td class="col day <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												} else if(itemList.get(j).getFieldId().equals("RDCNT")){
										%>
												<td class="col <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><i class="ico-view"></i><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												} else {
										%>
												<td class="col <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												}
											}
										%>
										</tr>
								<%
										} else {
								%>
										<tr>
										<%
											for(int j = 0; j < itemList.size(); j++){
												if(itemList.get(j).getFieldId().equals("ROWNUM")){
										%>
												<td class="col wid90 <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												} else if(itemList.get(j).getFieldId().equals("NTT_SJ")){
										%>
												<td class="col wid* post-tit tl">
													<a href="javascript:fn_view('<%=bb.getNttId()%>')" class="dot"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></a>
												</td>
										<%
												} else if(itemList.get(j).getFieldId().equals("ATCH_FILE_ID")){
										%>
												<td class="col <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%if(!bb.getDataValue(itemList.get(j).getFieldId(),board).equals("")){%><i class="ico-addfile">첨부파일</i><%}%></td>
										<%
												} else if(itemList.get(j).getFieldId().equals("REG_DATE")){
										%>
												<td class="col day <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												} else if(itemList.get(j).getFieldId().equals("RDCNT")){
										%>
												<td class="col <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><i class="ico-view"></i><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												} else {
										%>
												<td class="col <%if(itemList.get(j).getMobFlag().equals("Y")){%>colnone<%}%>"><%=bb.getDataValue(itemList.get(j).getFieldId(),board)%></td>
										<%
												}
											}
										%>
										</tr>
								<%
										}
									}
								%>
							</tbody>
						</table>
						<div class="pager">
							<%String pageIndex=(request.getParameter("pageIndex")==null?"1":request.getParameter("pageIndex")); %>
							<%=bb.getPaging(10).replace("><","> <").replace(">" + pageIndex + "</a>",">" + pageIndex + "</a>")%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>