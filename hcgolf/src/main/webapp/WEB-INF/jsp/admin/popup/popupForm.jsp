<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import = "egovframework.breeze.code.service.CodeVO"%>
<%@ page import = "egovframework.breeze.code.web.CodeBundle"%>
<%@ page import = "java.util.*"%>
<%
	CodeBundle cb = new CodeBundle(request);
%>


	<script>
		function fn_submit(v_command){
			
			if ($("#popupTitle").val() == "") {
				alert("제목을 입력해주세요.");
				$("#popupTitle").focus();
				return;
			}
			if ($("#startDate").val() == "") {
				alert("게시 기간을 입력해주세요.");
				$("#startDate").focus();
				return;
			}
			if ($("#endDate").val() == "") {
				alert("게시 기간을 입력해주세요.");
				$("#endDate").focus();
				return;
			}
			if ($('#atchFileId').val() == "" && $('#fileUp').get(0).files.length === 0){
				alert("이미지를 선택해 주세요.");
				$('#fileUp').focus();
				return;
			}
			if ($("#sizeWidth").val() == "") {
				alert("너비를 입력해주세요.");
				$("#sizeWidth").focus();
				return;
			}
			if ($("#sizeHeight").val() == "") {
				alert("높이을 입력해주세요.");
				$("#sizeHeight").focus();
				return;
			}
			if ($("#viewWidth").val() == "") {
				alert("창위치(가로)를 입력해주세요.");
				$("#viewWidth").focus();
				return;
			}
			if ($("#viewHeight").val() == "") {
				alert("창위치(세로)를 입력해주세요.");
				$("#viewHeight").focus();
				return;
			}
			if ($("#popupOrder").val() == "") {
				alert("게시 순서를 입력해주세요.");
				$("#popupOrder").focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')){
					$("#startDate").val( $("#startDate").val() + " " + $("#startTime").val() );
					$("#endDate").val( $("#endDate").val() + " " + $("#endTime").val() );
					$('#frm').attr("action", "/_admin/popup/popupInsert.do");
					$('#frm').submit();
				}
			} else {
				if (confirm('수정하시겠습니까?')) {
					$("#startDate").val( $("#startDate").val() + " " + $("#startTime").val() );
					$("#endDate").val( $("#endDate").val() + " " + $("#endTime").val() );
					$('#frm').attr("action", "/_admin/popup/popupUpdate.do");
					$('#frm').submit();
				}
			}
		}
		
		function fn_cancel(){
			if (confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/popup/popupList.do");
				$("#frm").submit();
			}
		}
		
		function fn_cancel(){
			$("#frm").attr("action", "/_admin/popup/popupList.do");
			$("#frm").submit();
		}
		
		function fn_delete(){
			if (confirm("삭제하시겠습니까?")){
				$("#frm").attr("action", "/_admin/popup/popupDelete.do");
				$("#frm").submit();
			}
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">메인 관리</a></li>
	            <li><a href="#lnk">팝업 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">팝업 관리</h2>
	        <div class="section-wrap res">
	            <div class="section">
		            <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
			            <input type="hidden" name="flag" value="POPUP">
						<input type="hidden" name="searchWrd"  value="<c:out value='${searchVO.searchWrd}'/>">
						<input type="hidden" name="searchCate"  value="<c:out value='${searchVO.searchCate}'/>">
						<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
			            <c:if test="${ searchVO.command ne 'insert' }">
			            	<input type="hidden" name="popupId" id="popupId" value="${popupVO.popupId}" >
			                <input type="hidden" name="atchFileId" id="atchFileId" value="${popupVO.atchFileId}">
			            </c:if>
	                    <fieldset>
                        	<p class="section-tit">팝업 정보</p>
					        <div class="tbl-wrap new-tbl res">
					            <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
					            <table class="tbl03">
					                <caption class="blind">팝업 등록 테이블</caption>
					                <colgroup>
					                    <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">팝업 제목 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" placeholder="제목을 입력해 주세요." title="제목 입력" name="popupTitle" id="popupTitle" value="${popupVO.popupTitle}">
						                    </td>
						                </tr>
										<%
										List<CodeVO> codeList = cb.getCodeList("CODE_000000000000011");
										if(codeList.size() > 0){
										%>
							                <tr>
												<th class="tl">카테고리 선택 <em class="essential">*</em></th>
												<td>
													<select name="category" id="category">
														<%
														for(int i=0; i<codeList.size();i++) {
														%>
			                                        		<c:set var="infoCate" value="<%=codeList.get(i).getCodeId() %>"/>
															<option value="<%=codeList.get(i).getCodeId() %>" <c:if test="${popupVO.category eq infoCate}">selected="selected"</c:if>><%=codeList.get(i).getCodeName() %></option>
														<%}%>
													</select>
												</td>
											</tr>
										<%}%>
						                <tr>
						                    <th class="tl">게시 기간 <em class="essential">*</em></th>
						                    <td>
							                	<div class="dsib">
						                           	<div class="datepicker-wrap disib sdate">
														<input type="text" class="datepick js-datepicker reldate maxnone" readonly="readonly" name="startDate" id="startDate" value="${popupVO.startDate.substring(0,11)}" >
							                       	</div>
							                       	<select  name="startTime" id="startTime">
							                       		<c:forEach var="hour" begin="0" end="23" step="1">
								                       		<option value="${hour}" <c:if test="${popupVO.startDate.substring(11,13) eq hour}">selected</c:if>>${String.format("%02d",hour)}:00</option>
							                       		</c:forEach> 
							                       	</select>
							                    </div>
							                       	~
							                	<div class="dsib">
							                       	<div class="datepicker-wrap disib edate">
							                            <input type="text" class="datepick js-datepicker reldate maxnone" readonly="readonly" name="endDate" id="endDate" value="${popupVO.endDate.substring(0,11)}">
							                        </div>
							                       	<select name="endTime" id="endTime">
							                       		<c:forEach var="hour" begin="0" end="23" step="1">
								                       		<option value="${hour}" <c:if test="${popupVO.endDate.substring(11,13) eq hour}">selected</c:if>>${String.format("%02d",hour)}:00</option>
							                       		</c:forEach>
							                       	</select>
						                       	</div>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">팝업 이미지 <em class="essential">*</em></th>
						                    <td class="add-file">
						                    	<c:if test="${ searchVO.command ne 'insert' }">
						                    		<img style="max-width:500px" src="<c:url value='/_cmm/fms/getImage.do'/>?atchFileId=<c:out value="${popupVO.atchFileId}"/>" alt="썸네일">
						                    	</c:if>
						                        <div class="add-file-inner">
						                            <input disabled="disabled" class="upload" placeholder="선택된 파일 없음">
						                            <label for="fileUp" class="btn-sel">파일선택</label>
						                            <input type="file" id="fileUp" class="blind" name="atchFile">
						                        </div>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">창크기-너비 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="창크기-너비 입력" name="sizeWidth" id="sizeWidth" value="${popupVO.sizeWidth}">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">창크기-높이 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="창크기-높이 입력" name="sizeHeight" id="sizeHeight" value="${popupVO.sizeHeight}">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">창위치-가로 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="창크기-가로 입력" name="viewWidth" id="viewWidth" value="${popupVO.viewWidth}">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">창위치-세로 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="창크기-세로 입력" name="viewHeight" id="viewHeight" value="${popupVO.viewHeight}">
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">URL</th>
						                    <td>
						                        <input type="text" placeholder="링크를 입력해 주세요." title="링크 주소 입력" name="url" id="url"  value="${popupVO.url}">
						                        <div class="radio-wrap pt12" style="color: rgb(0, 0, 0);">
							                        <input type="radio" name="target" value="_blank" <c:if test="${popupVO.target ne '_opener' && popupVO.target ne '_self'}">checked</c:if> >새 창
							                        <input type="radio" name="target" value="_opener" <c:if test="${popupVO.target eq '_opener'}">checked</c:if> >부모 창
							                        <input type="radio" name="target" value="_self" <c:if test="${popupVO.target eq '_self'}">checked</c:if> >현재 창
						                        </div>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">대체 텍스트</th>
						                    <td><input type="text" placeholder="대체 텍스트를 입력해 주세요." title="이미지 대체 텍스트 입력" name="altText" id="altText" value="${popupVO.altText}"></td>
						                </tr>
						                <tr>
						                    <th class="tl">게시 순서 <em class="essential">*</em></th>
						                    <td><input type="number" title="표시 순서 입력" name="popupOrder" id="popupOrder" value="<c:if test="${ searchVO.command ne 'insert' }">${popupVO.popupOrder}</c:if><c:if test="${ searchVO.command eq 'insert' }">1</c:if>"></td>
						                </tr>
						                <tr>
						                    <th class="tl">게시 여부 <em class="essential">*</em></th>
						                    <td>
						                        <div class="radio-wrap" style="color: rgb(0, 0, 0);">
						                            <input type="radio" name="viewFlag" value="Y" <c:if test="${popupVO.viewFlag ne 'N'}">checked</c:if> >출력
						                            <input type="radio" name="viewFlag" value="N" <c:if test="${popupVO.viewFlag eq 'N'}">checked</c:if> >미출력
						                        </div>
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
	                <a href="javascript:fn_cancel();" class="tbl-btn">목록</a>
	                <a href="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
	                <a href="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
            	</c:if>
            	<c:if test="${searchVO.command ne 'insert' }">
	                <a href="javascript:fn_cancel();" class="tbl-btn">목록</a>
	            	<a href="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
	                <a href="javascript:fn_delete();" class="tbl-btn red">삭제</a>
	                <a href="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
            </div>
	    </div>
	</div>