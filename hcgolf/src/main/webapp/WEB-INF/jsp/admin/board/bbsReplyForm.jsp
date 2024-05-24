<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script type="text/javascript" src="/_SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
		function fn_submit(v_command){
	
			if ($("#nttSj").val() == "") {
				alert("제목을 입력해주세요.");
				$("#nttSj").focus();
				return;
			}
	
	       oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
	
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/board/bbsReplyInsert.do");
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/board/bbsReplyUpdate.do");
				}
			}
			$("#frm").submit();
		}
	</script>
	
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시물 관리</a></li>
	            <li><a href="#lnk">${bdMstr.bbsNm}</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">${bdMstr.bbsNm}</h2>
	        <div class="section-wrap">
	            <div class="section">
		            <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
		            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
		            	<input type="hidden" name="bbsId" value="${bdMstr.bbsId }" >
			        	<c:if test="${searchVO.command eq 'update' }">
							<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}"/>
							<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}"/>
		            	</c:if>
						<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
						<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.atchPosblFileNumber}'/>" />
						<input type="hidden" name="nttId" value="<c:out value='${searchVO.nttId}'/>" >
						<input type="hidden" name="parnts" value="<c:out value='${searchVO.parnts}'/>" >
						<input type="hidden" name="sortOrdr" value="<c:out value='${searchVO.sortOrdr}'/>" >
						<input type="hidden" name="replyLc" value="<c:out value='${searchVO.replyLc}'/>" >
		                <fieldset>
                        	<p class="section-tit">${bdMstr.bbsNm} 답변</p>
			        		<div class="tbl-wrap new-tbl">
					            <table class="tbl03">
					                <caption class="blind">${bdMstr.bbsNm} FORM 테이블</caption>
					                <colgroup>
					                    <col style="width:15%">
					                    <col style="width:85%">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">작성자</th>
						                    <td>
							                    <c:choose>
							                    	<c:when test="${searchVO.command eq 'update' }">
							                    		<input type="text" id="ntcrNm" name="ntcrNm" class="wid200" value="${selectVO.ntcrNm}"/>
							                    	</c:when>
							                    	<c:otherwise>
							                    		<input type="text" id="ntcrNm" name="ntcrNm" class="wid200" value="${adminVO.adminName}"/>
							                    	</c:otherwise>
							                    </c:choose>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">제목</th>
						                    <td>
						                        <input type="text" class="w50" placeholder="제목을 입력해주세요" id="nttSj" name="nttSj" value="${selectVO.nttSj }">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">첨부파일</th>
						                    <td class="add-file">
												<c:forEach var="i" begin="${selectVO.fileCnt+1}" end="${bdMstr.atchPosblFileNumber}" step="1">
							                        <div class="add-file-inner">
							                            <input type="file" id="fileUp" name="inp-file${i}">
							                        </div>
												</c:forEach>
						                    </td>
						                </tr>
					        			<c:if test="${searchVO.command eq 'update' }">
							                <tr>
							                    <th class="tl">첨부파일 목록</th>
							                    <td class="add-file">
						                            <ul class="file-box-right">
					                            		<c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
															<c:param name="param_atchFileId" value="${selectVO.atchFileId}" />
															<c:param name="updateFlag" value="Y" />
															<c:param name="returnUrl" value="/_admin/board/bbsForm.do?command=update" />
														</c:import>
						                            </ul>
							                    </td>
							                </tr>
						                </c:if>
						                <tr>
						                    <th class="tl">내용</th>
						                    <td class="text-box">
				                            	<textarea name="nttCn" id="nttCn" cols="30" rows="15" class="txt" style="width:100%;">${selectVO.nttCn}</textarea>
				                            	<!-- 에디터 설정 -->
												<script type="text/javascript">
													var oEditors = [];
													nhn.husky.EZCreator.createInIFrame({
														oAppRef: oEditors,
														elPlaceHolder: "nttCn", //textarea에서 지정한 id와 일치해야 합니다.
														sSkinURI: "/_SE2/SmartEditor2Skin.html",
														fCreator: "createSEditor2"
													});
												</script>
												<!-- 에디터 설정 끝 -->
						                    </td>
						                </tr>
					                </tbody>
					            </table>
					        </div>
			            </fieldset>
		            </form>
				</div>
			</div>
            <div class="btn-box">
		        <c:if test="${searchVO.command eq 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="#lnk" onclick="javascript:history.back();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		            <a href="#lnk" onclick="javascript:history.back();" class="tbl-btn gray">취소</a>
	            </c:if>
            </div>
	    </div>
	</div>