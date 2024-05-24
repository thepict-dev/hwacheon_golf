<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import = "java.util.*"%>

	<script type="text/javascript" src="/_SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script>

		function fn_list(){
			$("#frm").attr("action", "/_admin/event/scheduleList.do");
			$("#frm").submit();
		}

		function fn_submit(v_command){

			if($("#title").val() == ""){
				alert("제목을 입력해주세요.");
				$("#title").focus();
				return;
			}

			if($("#ntcrNm").val() == ""){
				alert("작성자를 입력해주세요.");
				$("#ntcrNm").focus();
				return;
			}

			if($("#place").val() == ""){
				alert("장소를 입력해주세요.");
				$("#place").focus();
				return;
			}

			if($("#startDate").val() == ""){
				alert("시작일을 선택해주세요.");
				$("#startDate").focus();
				return;
			}

			if($("#endDate").val() == ""){
				alert("종료일을 선택해주세요.");
				$("#endDate").focus();
				return;
			}

			oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
		
			if(v_command == 'insert'){
				if(confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/event/scheduleInsert.do");
					$("#frm").submit();
				}
			}else{
				if(confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/event/scheduleUpdate.do");
					$("#frm").submit();
				}
			}
		}
		
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/event/scheduleList.do");
				$("#frm").submit();
			}
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
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">예약/일정 관리</a></li>
	            <li><a href="#lnk">일정 등록</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">일정안내</h2>
	        <div class="section-wrap">
	             <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
					<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="scheduleId" value="${selectVO.scheduleId}">
	            	</c:if>
	            	<input type="hidden" name="command" id="command" value="${searchVO.command}">
	            	
	                <fieldset>
	                    <div class="section">
	                        <p class="section-tit">일정안내 form</p>
	                        <div class="tbl-wrap new-tbl">
	                            <table class="tbl03 iptwid">
	                                <caption class="blind">일정안내 뷰 테이블</caption>
	                                <colgroup>
					                    <col style="width:20%" class="change1">
					                    <col style="width:80%" class="change2">
	                                </colgroup>
	                                <tbody>
		                                <tr>
		                                    <th class="tl">제목</th>
		                                    <td>
							                	<input type="text" id="title" name="title" class="w50" value="${selectVO.title}"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">작성자</th>
		                                    <td>
							                    <c:choose>
							                    	<c:when test="${searchVO.command eq 'update' }">
							                    		<input type="text" id="ntcrNm" name="ntcrNm" class="w50" value="${selectVO.ntcrNm}"/>
							                    	</c:when>
							                    	<c:otherwise>
							                    		<input type="text" id="ntcrNm" name="ntcrNm" class="w50" value="${adminVO.adminName}"/>
							                    	</c:otherwise>
							                    </c:choose>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">달력 표시 컬러</th>
		                                    <td>
			                            		<input type="radio" id="calColor1" name="calColor" value="pink" checked="checked" <c:if test="${selectVO.calColor eq 'pink' }">checked="checked"</c:if>/><label for="calColor1" style="padding-right: 20px;">분홍색</label>
			                            		<input type="radio" id="calColor2" name="calColor" value="sky" <c:if test="${selectVO.calColor eq 'sky' }">checked="checked"</c:if>/><label for="calColor2" style="padding-right: 20px;">하늘색</label>
			                            		<input type="radio" id="calColor3" name="calColor" value="green" <c:if test="${selectVO.calColor eq 'green' }">checked="checked"</c:if>/><label for="calColor3" style="padding-right: 20px;">녹색</label>
			                            		<input type="radio" id="calColor4" name="calColor" value="orange" <c:if test="${selectVO.calColor eq 'orange' }">checked="checked"</c:if>/><label for="calColor4" style="padding-right: 20px;">주황색</label>
			                            		<input type="radio" id="calColor5" name="calColor" value="purple" <c:if test="${selectVO.calColor eq 'purple' }">checked="checked"</c:if>/><label for="calColor5">보라색</label>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">장소</th>
		                                    <td>
							                	<input type="text" id="place" name="place" class="w50" value="${selectVO.place}"/>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">기간</th>
		                                    <td>
					                           	<div class="datepicker-wrap disib sdate">
													<input type="text" class="datepick js-datepicker reldate maxnone" readonly="readonly" name="startDate" id="startDate" value="${selectVO.startDate}" >
						                       	</div>
						                       	~
						                       	<div class="datepicker-wrap disib edate">
						                            <input type="text" class="datepick js-datepicker reldate maxnone" readonly="readonly" name="endDate" id="endDate" value="${selectVO.endDate}">
						                        </div>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">내용</th>
						                    <td class="text-box">
				                            	<textarea name="content" id="content" cols="30" rows="15" class="txt" style="width:100%;">${selectVO.content}</textarea>
				                            	<!-- 에디터 설정 -->
												<script type="text/javascript">
													var oEditors = [];
													nhn.husky.EZCreator.createInIFrame({
														oAppRef: oEditors,
														elPlaceHolder: "content", //textarea에서 지정한 id와 일치해야 합니다.
														sSkinURI: "/_SE2/SmartEditor2Skin.html",
														fCreator: "createSEditor2"
													});
												</script>
												<!-- 에디터 설정 끝 -->
						                    </td>
		                                </tr>
		                                <tr>
		                                    <th class="tl">첨부파일</th>
						                    <td class="add-file">
												<c:forEach var="i" begin="${fileCnt+1}" end="5" step="1">
							                        <div class="add-file-inner">
							                            <!-- 
							                            <input disabled="disabled" class="upload" placeholder="선택된 파일 없음">
							                            <label for="fileUp" class="btn-sel">파일선택</label> 
							                            -->
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
															<c:param name="param_atchFileId" value="${selectVO.atchFileId}" />
															<c:param name="updateFlag" value="Y" />
															<c:param name="returnUrl" value="/_admin/event/scheduleForm.do?command=update" />
														</c:import>
						                            </ul>
							                    </td>
							                </tr>
						                </c:if>
	                                </tbody>
	                            </table>
	                        </div>
	                    </div>
	                </fieldset>
	            </form>
	        </div>
	        <div class="btn-box">
				<a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
	            <c:if test="${searchVO.command eq 'insert' }">
					<a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
	            </c:if>
	            <c:if test="${searchVO.command ne 'insert' }">
					<a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
	            </c:if>
	        </div>
	    </div>
	
	</div>