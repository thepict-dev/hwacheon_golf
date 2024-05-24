<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script>
		function fn_list(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/site/atchList.do");
			$("#frm").submit();
		}
		
		function fn_delete() {
			if (confirm("삭제하시겠습니까?")) {
				$("#frm").attr("action","/_admin/site/atchDelete.do");
				$("#frm").submit();
			}
		}
		
		function fn_update() {
			$("#command").val("update");
			$("#frm").attr("action","/_admin/site/atchForm.do");
			$("#frm").submit();
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트 관리</a></li>
	            <li><a href="#lnk">첨부파일 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">첨부파일 관리</h2>
	        <div class="section-wrap res">
	            <div class="section">
					<form id="frm" name="frm" method="post" action="">
						<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="atchId" value="<c:out value='${atchVO.atchId}'/>" >
						<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
						<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
						<input type="hidden" name="command" id="command" value="">
						
				        <fieldset>
                        	<p class="section-tit">첨부파일 관리 VIEW</p>
							<div class="tbl-wrap new-tbl res">
					            <table class="tbl03">
					                <caption class="blind">첨부파일 관리 VIEW 테이블</caption>
					                <colgroup>
					                    <col style="width:20%">
					                    <col style="width:80%">
					                </colgroup>
					                <tbody>
						                <tr>
						                	<th class="tl">제목</th>
						                    <td>${atchVO.atchTitle}</td>
						                </tr>
						                <tr class="tl">
						                	<th class="tl">첨부파일</th>
						                    <td>
						                        <div class="file-box">
						                            <ul class="file-box-right">
									                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
															<c:param name="param_atchFileId" value="${atchVO.atchFileId}" />
															<c:param name="returnUrl" value="/_admin/site/atchView.do?atchId=${atchVO.atchId}" />
														</c:import>
						                            </ul>
						                        </div>
						                    </td>
						                </tr>
						                <tr class="type-hei">
						                	<th class="tl">내용</th>
						                    <td>${atchVO.atchContent}</td>
						                </tr>
					                </tbody>
					            </table>
			        		</div>
			            </fieldset>
		            </form>
				</div>
			</div>
            <div class="btn-box">
                <a href="#lnk" onclick="javascript:fn_list('${searchVO.pageIndex}');" class="tbl-btn">목록</a>
                <a href="#lnk" onclick="javascript:fn_update();" class="tbl-btn blue">수정</a>
                <a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a>
            </div>
	    </div>
	</div>