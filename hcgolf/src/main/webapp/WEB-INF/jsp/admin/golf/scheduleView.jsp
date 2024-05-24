<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import = "egovframework.breeze.code.service.CodeVO"%>
<%@ page import = "egovframework.breeze.code.web.CodeBundle"%>
<%@ page import = "java.util.*"%>
<%
	pageContext.setAttribute("newLineChar", "\n");
%>
	<script>

		function fn_list(){
			$("#frm").attr("action", "/_admin/event/scheduleList.do");
			$("#frm").submit();
		}
		
		function fn_scheduleForm() {
			$("#command").val("update");
			$("#frm").attr("action","/_admin/event/scheduleForm.do");
			$("#frm").submit();
		}

		function fn_delete() {
			if(confirm("삭제하시겠습니까?")){
				$("#frm").attr("action","/_admin/event/scheduleDelete.do");
				$("#frm").submit();
			}
		}
        
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">예약/일정 관리</a></li>
	            <li><a href="#lnk">일정 상세</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">일정안내</h2>
	        <div class="section-wrap">
	             <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
	            	<input type="hidden" name="scheduleId" value="${eventVO.scheduleId }" >
	            	<input type="hidden" name="command" id="command" value="" >
	            	
	                <fieldset>
	                    <div class="section">
	                        <p class="section-tit">일정안내 view</p>
	                        <div class="tbl-wrap new-tbl">
	                            <table class="tbl03 iptwid">
	                                <caption class="blind">일정안내 뷰 테이블</caption>
	                                <colgroup>
	                                    <col style="width:14%">
	                                    <col>
	                                </colgroup>
	                                <tbody>
		                                <tr>
		                                    <th class="tl">제목</th>
		                                    <td>
		                                        ${eventVO.title}
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">작성자</th>
		                                    <td>
		                                        ${eventVO.ntcrNm}
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">달력 표시 컬러</th>
		                                    <td>
		                                    	<c:choose>
		                                    		<c:when test="${eventVO.calColor eq 'pink'}">분홍색</c:when>
		                                    		<c:when test="${eventVO.calColor eq 'sky'}">하늘색</c:when>
		                                    		<c:when test="${eventVO.calColor eq 'green'}">녹색</c:when>
		                                    		<c:when test="${eventVO.calColor eq 'orange'}">주황색</c:when>
		                                    		<c:when test="${eventVO.calColor eq 'purple'}">보라색</c:when>
		                                    	</c:choose>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">장소</th>
		                                    <td>
		                                        ${eventVO.place}
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">기간</th>
		                                    <td>
		                                        ${eventVO.startDate} ~ ${eventVO.endDate }
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">내용</th>
		                                    <td>
		                                        ${eventVO.content}
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">첨부파일</th>
		                                    <td>
						                        <div class="file-box">
						                            <ul class="file-box-right">
									                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
															<c:param name="param_atchFileId" value="${eventVO.atchFileId}" />
														</c:import>
						                            </ul>
						                        </div>
		                                    </td>
		                                </tr>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </fieldset>
	            </form>
	        </div>
	        <div class="btn-box">
				<a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
				<a href="#lnk" onclick="javascript:fn_scheduleForm();" class="tbl-btn blue">수정</a>
		        <a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a>
	        </div>
	    </div>
	
	</div>