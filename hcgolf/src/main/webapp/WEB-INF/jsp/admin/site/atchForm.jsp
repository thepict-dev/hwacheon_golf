<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<script type="text/javascript" src="/_SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
		function fn_submit(v_command){

			if ($("#atchTitle").val() == "") {
				alert("첨부파일 이름을 입력해주세요.");
				$("#atchTitle").focus();
				return;
			}
			
			oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/atchInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/atchUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/site/atchList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/site/atchList.do");
			$("#frm").submit();
		}
		function checkFile(f){
			// files 로 해당 파일 정보 얻기.
			var checkId = f.id;
			var file = f.files;
			// file[0].name 은 파일명 입니다.
			// 정규식으로 확장자 체크
			if(!/\.(gif|png|jpg|jpeg|doc|docx|xls|xlsx|hwp|pdf)$/i.test(file[0].name)){
				alert('gif, png, jpg, jpeg, doc, docx, xls, xlsx, hwp, pdf 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
				$("#"+checkId).val("");
			// 체크를 통과했다면 종료.
			}else{ 
				return;
			}
		}
	</script>
		
	<div class="container-wrap">
		<div class="breadcrumbs">
			<ul>
				<li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
				<li><a href="#lnk">사이트관리</a></li>
				<li><a href="#lnk">첨부파일 관리</a></li>
			</ul>
		</div>
		<div class="container scroll">
			<h2 class="con-tit">첨부파일 관리</h2>
			<div class="section-wrap res">
				<div class="section">
				<form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
					<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
		        	<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="atchId" value="${atchVO.atchId }" >
	            	</c:if>
					<fieldset>
						<p class="section-tit">첨부파일 정보</p>
						<div class="tbl-wrap new-tbl res">
							<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
							<table class="tbl03">
								<caption class="blind">첨부파일 관리 등록 테이블</caption>
								<colgroup>
									<col width="20%" class="change1">
                                    <col width="80%" class="change2">
								</colgroup>
								<tbody>
								<tr>
									<th class="tl">제목 <em class="essential">*</em></th>
									<td class="tl"><input type="text" id="atchTitle" name="atchTitle" title="제목 입력" value="${atchVO.atchTitle }"></td>
								</tr>
								<tr>
				                    <th class="tl">첨부파일</th>
				                    <td class="add-file">
										<c:forEach var="i" begin="${fileCnt+1}" end="5" step="1">
					                        <div class="add-file-inner">
					                            <input type="file" onchange="checkFile(this)" id="fileUp${i}" name="inp-file${i}">
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
													<c:param name="param_atchFileId" value="${atchVO.atchFileId}" />
													<c:param name="updateFlag" value="Y" />
													<c:param name="returnUrl" value="/_admin/site/atchForm.do?command=update" />
												</c:import>
				                            </ul>
					                    </td>
					                </tr>
				                </c:if>
				                
				                <tr>
				                    <th class="tl">내용</th>
				                    <td class="text-box">
		                            	<textarea name="atchContent" id="atchContent" cols="30" rows="15" class="txt" style="width:100%;">${atchVO.atchContent}</textarea>
		                            	<!-- 에디터 설정 -->
										<script type="text/javascript">
											var oEditors = [];
											nhn.husky.EZCreator.createInIFrame({
												oAppRef: oEditors,
												elPlaceHolder: "atchContent", //textarea에서 지정한 id와 일치해야 합니다.
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
		            <a href="javascript:void(0);" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="javascript:void(0);" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="javascript:void(0);" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="javascript:void(0);" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="javascript:void(0);" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		            <a href="javascript:void(0);" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
			</div>
		</div>
	</div>