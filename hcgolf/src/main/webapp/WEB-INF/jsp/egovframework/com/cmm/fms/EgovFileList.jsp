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
	
	function fn_egov_downThumbnailFile(atchFileId, imgFlag){
		window.open("<c:url value='/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&imgFlag="+imgFlag+"'/>");
	}

	function fn_egov_deleteFile(atchFileId, fileSn) {
		forms = document.getElementsByTagName("form");

		for (var i = 0; i < forms.length; i++) {
			if (typeof(forms[i].atchFileId) != "undefined" &&
					typeof(forms[i].fileSn) != "undefined" &&
					typeof(forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}

		//form = document.forms[0];
		if(confirm("첨부파일을 삭제하시겠습니까?")){
			form.atchFileId.value = atchFileId;
			form.imgFlag.value = '';
			form.fileSn.value = fileSn;
			form.action = "<c:url value='/_cmm/fms/deleteFileInfs.do'/>";
			form.submit();
		}
	}
	
	function fn_egov_deleteThumbnailFile(atchFileId, imgFlag) {
		forms = document.getElementsByTagName("form");
		
		for (var i = 0; i < forms.length; i++) {
			if (typeof(forms[i].atchFileId) != "undefined" &&
					typeof(forms[i].imgFlag) != "undefined" &&
					typeof(forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}

		//form = document.forms[0];
		if(confirm("첨부파일을 삭제하시겠습니까?")){
			form.atchFileId.value = atchFileId;
			form.imgFlag.value = imgFlag;
			form.fileSn.value = '';
			form.action = "<c:url value='/_cmm/fms/deleteFileInfs.do'/>";
			form.submit();
		}
	}

	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";
		} else {
			document.getElementById('file_upload_posbl').style.display = "none";
			document.getElementById('file_upload_imposbl').style.display = "block";
		}
	}
	
	function fn_egov_fileLinkCopy(atchFileId, fileSn){
		var txt = "/_cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn;
		$("#linkTarget").val(txt);
		$("#linkTarget").select();
		var succCopy = document.execCommand('copy');
		if(succCopy){
			alert("복사 되었습니다.");
		}
	}
	
	function fn_egov_imageLinkCopy(atchFileId, fileSn){
		var txt = "/_cmm/fms/getImage.do?atchFileId="+atchFileId+"&fileSn="+fileSn;
		$("#linkTarget").val(txt);
		$("#linkTarget").select();
		var succCopy = document.execCommand('copy');
		if(succCopy){
			alert("복사 되었습니다.");
		}
	}
	
	
</script>

<!-- <form name="fileForm" action="" method="post" >  -->
<input type="hidden" name="atchFileId" value="<c:out value='${atchFileId}'/>">
<input type="hidden" name="fileSn" value="${fileSn}" >
<input type="hidden" name="fileListCnt" id="fileListCnt" value="${fileListCnt}">
<input type="hidden" name="returnUrl" id="returnUrl" value="${returnUrl}">
<input type="text" name="linkTarget" id="linkTarget" value="" style="width:1px; height:1px; margin:0; padding:0; border:0; top:0; left:0; position:absolute;">
<input type="hidden" name="imgFlag" >

<!-- </form>  -->

<!--<title>파일목록</title> -->

<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<c:choose>
		<c:when test="${updateFlag=='Y'}">
			<img src="/_admin/img/file.png">
			<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
			<button type="button" onClick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');">
				<img src="<c:url value='/_admin/img/bu_icon_delete.gif'/>" width="19" height="18" alt="파일삭제">
			</button>
			<br/>
		</c:when>
		<c:otherwise>
			<li class="down-file">
				<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')" title="첨부파일 다운로드">
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
					<span><c:out value="${fileVO.orignlFileNm}"/></span><%-- &nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte] --%>
				</a>
				<c:if test="${fn:contains(returnUrl,'atchView')}">
					<button type="button" class="file-link" onClick="fn_egov_fileLinkCopy('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');">
						파일 다운로드 링크 복사
					</button>
					<c:if test="${fileVO.fileExtsn eq 'jpg' || fileVO.fileExtsn eq 'png' || fileVO.fileExtsn eq 'jpeg' || fileVO.fileExtsn eq 'gif' || fileVO.fileExtsn eq 'bmp'}">
						<button type="button" class="img-link" onClick="fn_egov_imageLinkCopy('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');">
							이미지 링크 복사
						</button>	
					</c:if>
				</c:if>
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${fn:length(fileList) == 0}">
</c:if>