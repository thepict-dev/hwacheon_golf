<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
  * @Class Name : EgovFileList.jsp
  * @Description : 파일 목록화면
  * @Modification Information
  * @
  * @ 수정일   	      수정자		         수정내용
  * @ ----------   -----------   ---------------------------
  * @ 2009.03.26   이삼섭최초 생성      
  *   2011.07.20   옥찬우<Input>   Tag id속성 추가( Line : 68 )
  *   2018.09.11   신용호                 불필요한 function 삭제 - fn_egov_multi_selector_update_delete
  *   2019.12.11   신용호                 KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.26
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- link href="<c:url value='/css/egovframework/com/com.css' />" rel="stylesheet" type="text/css"-->

<script type="text/javascript">
	function fn_egov_downFile(atchFileId, fileSn){
		window.open("<c:url value='/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	}

	function deleteFile(atchFileId, fileSn, section, businessFlag) {
		if(confirm("첨부파일을 삭제하시겠습니까?")){
			var businessId = $("#businessId").val();
            var url = "/_cmm/fms/deleteBusinessFileInfs.do?atchFileId=" + atchFileId + "&fileSn=" + fileSn + "&businessFlag=" + businessFlag + "&businessId="+businessId;
			$.ajax({
				type: "get",
				cache: false,
				url: url,
				dataType : 'json',
				contentType : "application/json; charset=utf-8",
				success: function(result){
					if(result.message == 'Login') {
                        alert("로그인이 필요한 서비스입니다.");
                        location.href="/_admin/login.do";
                    } else if(result.message == 'Y') {
                    	console.log("atchFileId : "+result.atchFileId);
                    	alert("첨부파일 삭제에 성공하였습니다.");
                    	
                    	// 업로드 파일 테이블 목록에서 삭제
                        $("#"+section).remove();
                    } else {
                    	alert("첨부파일 삭제 오류입니다.");
                    }
				},
				error: function(err){
					alert("통신 오류가 발생하였습니다. 관리자에게 문의해주세요.");
				}
			});
		}
	}
	
</script>

<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<c:choose>
		<c:when test="${updateFlag=='Y'}">
			<tr id="${businessFlag}_${status.index}">
				<td class="tl">
					<p class="file-name">${fileVO.orignlFileNm}</p>
				</td>
				<td>
					<p class="size">${fileVO.fileMg} byte</p>
				</td>
				<td class="tc">
					<span class="manage-btn del-btn">
						<button type="button" onclick="deleteFile('${atchFileId}','${fileVO.fileSn}','${businessFlag}_${status.index}', '${businessFlag}'); return false;">삭제</button>
					</span>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td class="tl">
					<p class="file-name">
						<a href="#lnk" onclick="fn_egov_downFile('${fileVO.atchFileId}','${fileVO.fileSn}')">
							<c:choose>
								<c:when test="${fileVO.fileExtsn eq 'doc'}"><span class="ico-file doc"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'xls' || fileVO.fileExtsn eq 'xlsx'}"><span class="ico-file xls"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'ppt' || fileVO.fileExtsn eq 'pptx'}"><span class="ico-file ppt"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'pdf'}"><span class="ico-file pdf"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'hwp'}"><span class="ico-file hwp"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'zip' || fileVO.fileExtsn eq 'tar' || fileVO.fileExtsn eq 'egg'}"><span class="ico-file zip"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'jpg'}"><span class="ico-file jpg"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'png'}"><span class="ico-file png"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'jpeg'}"><span class="ico-file jpeg"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'gif'}"><span class="ico-file gif"></span></c:when>
								<c:when test="${fileVO.fileExtsn eq 'txt'}"><span class="ico-file txt"></span></c:when>
								<c:otherwise><span class="ico-file clip"></span></c:otherwise>
							</c:choose>
							${fileVO.orignlFileNm}
						</a>
					</p>
				</td>
				<td>
					<p class="size">${fileVO.fileMg} byte</p>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${fn:length(fileList) == 0}">
</c:if>