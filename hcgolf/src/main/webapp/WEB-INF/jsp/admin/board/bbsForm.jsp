<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script type="text/javascript" src="/_admin/_SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    function fn_get_address(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 추가 정보 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('zipCode').value = data.zonecode;
	                document.getElementById('address').value = roadAddr;
	
		        }
		    }).open();
	    }

		function fn_submit(v_command){
	
			if ($("#ntcrNm").val() == "") {
				alert("작성자를 입력해주세요.");
				$("#ntcrNm").focus();
				return;
			}
	
			if ($("#nttSj").val().replace(/ /gi, "") == null || $("#nttSj").val().replace(/ /gi, "") == "") {
				alert("제목을 입력해주세요.");
				$("#nttSj").focus();
				return;
			}
			<c:if test = "${nttCnFlag == true}">
				oEditors[0].exec("UPDATE_CONTENTS_FIELD", []);
			</c:if>

			if ($("#cateType01").val() == "") {
				alert("입력되지 않은 항목이 있습니다.");
				$("#cateType01").focus();
				return;
			}
	
			if ($("#cateType02").val() == "") {
				alert("입력되지 않은 항목이 있습니다.");
				$("#cateType02").focus();
				return;
			}
	
			if ($("#cateType03").val() == "") {
				alert("입력되지 않은 항목이 있습니다.");
				$("#cateType03").focus();
				return;
			}
	
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/board/bbsInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/board/bbsUpdate.do");
					$("#frm").submit();
				}
			}
		}
	
		function fn_notice(){
			if($("input:checkbox[id='noticeChk']").is(":checked")){
				$("#noticeFlag").val("Y");
			}else{
				$("#noticeFlag").val("N");
			}
		}
		function fn_cancel(v_command){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				if (v_command == "insert"){
					$("#frm").attr("action", "/_admin/board/bbsList.do");
				} else {
					$("#frm").attr("action", "/_admin/board/bbsView.do");
				}
				$("#frm").submit();
			}
		}
		function fn_list(v_command){
			if(v_command == "insert"){
				$("#frm").attr("action", "/_admin/board/bbsList.do");
			} else {
				$("#frm").attr("action", "/_admin/board/bbsView.do");
			}
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
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시물 관리</a></li>
	            <li><a href="#lnk">${bdMstr.bbsNm}</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">${bdMstr.bbsNm}</h2>
	        <div class="section-wrap res">
	            <div class="section">
		            <form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
		            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
		            	<input type="hidden" name="bbsTyCode" id="bbsTyCode" value="${searchVO.bbsTyCode }" >
		            	<input type="hidden" name="bbsId" value="${bdMstr.bbsId }" >
			        	<c:if test="${searchVO.command eq 'update' }">
		            		<input type="hidden" name="nttId" value="${selectVO.nttId }" >
							<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}"/>
							<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}"/>
							<input type="hidden" name="searchCate1" value="<c:out value='${searchVO.searchCate1}'/>"/>
							<input type="hidden" name="searchCate2" value="<c:out value='${searchVO.searchCate2}'/>"/>
							<input type="hidden" name="searchCate3" value="<c:out value='${searchVO.searchCate3}'/>"/>
		            	</c:if>
						<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
						<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.atchPosblFileNumber}'/>" />
		                <fieldset>
                        	<p class="section-tit">${bdMstr.bbsNm} FORM</p>
			        		<div class="tbl-wrap new-tbl res">
					            <table class="tbl03">
					                <caption class="blind">${bdMstr.bbsNm} FORM 테이블</caption>
					                <colgroup>
					                    <col style="width:20%" class="change1">
					                    <col style="width:80%" class="change2">
					                </colgroup>
					                <tbody>
						                <c:forEach var="name" items="${itemList}" varStatus="status">
							                <c:if test="${name.fieldId eq 'NTCR_NM' }">
								                <tr>
								                    <th class="tl">${name.itemName}</th>
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
							                </c:if>
			                    			<c:if test="${name.fieldId eq 'NTT_SJ' }">
								                <tr>
								                    <th class="tl">${name.itemName}</th>
								                    <td>
								                        <input type="text" class="w50" id="nttSj" name="nttSj" value="${selectVO.nttSj }">
							        					<c:if test="${bdMstr.noticePosblAt eq 'Y'}">
									                        <input type="checkbox" id="noticeChk" onclick="javascript:fn_notice();" <c:if test="${selectVO.noticeAt eq 'Y' }"> checked="checked"</c:if>>공지
										                    <input type="hidden" id="noticeFlag" name="noticeAt" value="${selectVO.noticeAt}">
									                    </c:if>
								                    </td>
								                </tr>
								            </c:if>
								            <c:if test="${searchVO.bbsId ne 'BBSMSTR_000000000023' && searchVO.bbsId ne 'BBSMSTR_000000000024'}">
							        			<c:if test="${bdMstr.bbsTyCode eq 'BBST01'}">
								        			<c:if test="${bdMstr.fileAtchPosblAt eq 'Y'}">
				                    					<c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
											                <tr>
											                    <th class="tl">${name.itemName}</th>
											                    <td class="add-file">
																	<c:forEach var="i" begin="${selectVO.fileCnt+1}" end="${bdMstr.atchPosblFileNumber}" step="1">
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
												                    <th class="tl">${name.itemName} 목록</th>
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
										                </c:if>
									                </c:if>
			                    					<c:if test="${name.fieldId eq 'THUMBNAIL' }">
								                        <tr>
								                            <th class="tl"><label for="inp-file">${name.itemName}</label></th>
								                            <td class="add-file">
									                        <c:if test="${selectVO.atchFileId eq null || selectVO.atchFileId == ''}">
								                            	<input type="file" onchange="checkFile(this)" id="thumbnail" name="thumbnail" class="wid50" placeholder="대표이미지(썸네일)로 등록할 이미지를 첨부해 주세요."/>
								                            </c:if>
															<p class="info">※ 대표이미지로 등록한 사진은 리스트 항목에만 노출됩니다. 동일한 사진을 보시려면 에디터에 추가해 주세요.</p></td>
								                        </tr>
									        			<c:if test="${searchVO.command eq 'update' }">
											                <tr>
											                    <th class="tl">${name.itemName} 목록</th>
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
									                </c:if>
								                </c:if>
							        			<c:if test="${bdMstr.bbsTyCode ne 'BBST01'}">
				                    				<c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
								                        <tr>
								                            <th class="tl"><label for="inp-file">${name.itemName}</label></th>
								                            <td class="add-file">
									                        <c:if test="${empty thumbnail}">
								                            	<input type="file" onchange="checkFile(this)" id="thumbnail" name="thumbnail" class="wid50" placeholder="대표이미지(썸네일)로 등록할 이미지를 첨부해 주세요."/>
								                            </c:if>
															<p class="info">※ 대표이미지로 등록한 사진은 리스트 항목에만 노출됩니다. 동일한 사진을 보시려면 에디터에 추가해 주세요.</p></td>
								                        </tr>
									        			<c:if test="${searchVO.command eq 'update' }">
											                <tr>
											                    <th class="tl">${name.itemName} 목록</th>
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
									                </c:if>
							        			</c:if>
								            </c:if>
								            <c:if test="${searchVO.bbsId eq 'BBSMSTR_000000000023' || searchVO.bbsId eq 'BBSMSTR_000000000024'}">
								            	<c:if test="${name.fieldId eq 'THUMBNAIL' }">
									            	<tr>
							                            <th class="tl"><label for="inp-file">${name.itemName}</label></th>
							                            <td class="add-file">
														<c:if test="${empty thumbnail }">
															<input type="file" onchange="checkFile(this)" id="thumbnail" name="thumbnail" class="wid50" placeholder="대표이미지(썸네일)로 등록할 이미지를 첨부해 주세요."/>
														</c:if>
														<p class="info">※ 대표이미지로 등록한 사진은 리스트 항목에만 노출됩니다. 동일한 사진을 보시려면 에디터에 추가해 주세요.</p></td>
							                        </tr>
								        			<c:if test="${searchVO.command eq 'update' }">
										                <tr>
										                    <th class="tl">${name.itemName} 목록</th>
										                    <td class="add-file">
								                            	<c:if test="${not empty thumbnail }">
									                            	<ul class="file-box-right">
									                            		<li>
									                            			<img src="/_admin/img/file.png">
										                           			${thumbnail.orignlFileNm}&nbsp;[<c:out value="${thumbnail.fileMg}"/>&nbsp;byte]
										                           			<button type="button" onClick="fn_egov_deleteThumbnailFile('<c:out value="${thumbnail.atchFileId}"/>','thumbnail');">
																				<img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_delete.gif'/>" width="19" height="18" alt="파일삭제">
																			</button>
																		</li>
									                            	</ul>
																</c:if>
										                    </td>
										                </tr>
									                </c:if>
									            </c:if>
									            <c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
									                <tr>
									                    <th class="tl">${name.itemName}</th>
									                    <td class="add-file">
															<c:forEach var="i" begin="${selectVO.fileCnt+1}" end="${bdMstr.atchPosblFileNumber}" step="1">
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
										                    <th class="tl">${name.itemName} 목록</th>
										                    <td class="add-file">
									                            <ul class="file-box-right">
								                            		<c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
																		<c:param name="param_atchFileId" value="${selectVO.atchFileId}" />
																		<c:param name="updateFlag" value="Y" />
																		<c:param name="returnUrl" value="/_admin/board/bbsForm.do?command=update" />
																		<c:param name="viewFlag" value="view" />
																	</c:import>
									                            </ul>
										                    </td>
										                </tr>
									                </c:if>
								                </c:if>
								            </c:if>
						        			<c:if test="${bdMstr.cateType01 eq 'Y'}">
							                	<c:if test="${name.fieldId eq 'CATE_TYPE01' }">
									                <tr>
									                    <th class="tl">${name.itemName}</th>
									                    <td>
						                                    <select id="cateType01" name="cateType01">
																<option value="">선택</option>
																<c:forEach var="cateList" items="${cateList01}" varStatus="status">
						                                        	<option value="${cateList.cateCode }" <c:if test="${cateList.cateCode eq selectVO.cateType01}">selected="selected"</c:if>>${cateList.cateName }</option>
						                                        </c:forEach>
						                                    </select>
									                    </td>
									                </tr>
								                </c:if>
						        			</c:if>
						        			<c:if test="${bdMstr.cateType02 eq 'Y'}">
							                	<c:if test="${name.fieldId eq 'CATE_TYPE02' }">
									                <tr>
									                    <th class="tl">${name.itemName}</th>
									                    <td>
						                                    <select id="cateType02" name="cateType02">
																<option value="">선택</option>
																<c:forEach var="cateList" items="${cateList02}" varStatus="status">
						                                        	<option value="${cateList.cateCode }" <c:if test="${cateList.cateCode eq selectVO.cateType02}">selected="selected"</c:if>>${cateList.cateName }</option>
						                                        </c:forEach>
						                                    </select>
									                    </td>
									                </tr>
								                </c:if>
						        			</c:if>
						        			<c:if test="${bdMstr.cateType03 eq 'Y'}">
							                	<c:if test="${name.fieldId eq 'CATE_TYPE03' }">
									                <tr>
									                    <th class="tl">${name.itemName}</th>
									                    <td>
						                                    <select id="cateType03" name="cateType03">
																<option value="">선택</option>
																<c:forEach var="cateList" items="${cateList03}" varStatus="status">
						                                        	<option value="${cateList.cateCode }" <c:if test="${cateList.cateCode eq selectVO.cateType03}">selected="selected"</c:if>>${cateList.cateName }</option>
						                                        </c:forEach>
						                                    </select>
									                    </td>
									                </tr>
								                </c:if>
						        			</c:if>
						                	<c:if test="${name.fieldId eq 'USER_TEL' }">
							                	<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td><input type="text" class="w50" id="userTel" name="userTel" value="${selectVO.userTel }"></td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'USER_CEL' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="userCel" name="userCel" value="${selectVO.userCel }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'ADDRESS' }">
							                	<tr>
								                	<th class="tl" scope="col">${name.itemName}</th>
								                	<td>
                                        				<div class="id-check inner-address1">
															<input type="text" id="zipCode" name="zipCode" value="${selectVO.zipCode }" class="w50 readonly" placeholder="우편번호" readonly="readonly"/>
															<button type="button" onclick="javascript:fn_get_address();" class="same-check">주소찾기</button>
														</div>
                                        				<div class="inner-address2 mt5">
															<input type="text" id="address" name="address" value="${selectVO.address }" class="readonly" placeholder="주소" readonly="readonly"/>
															<input type="text" id="detailAddr" name="detailAddr" class="mt5" value="${selectVO.detailAddr }" placeholder="상세주소(선택입력)"/>
														</div>
								                	</td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'EMAIL' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="email" name="email" value="${selectVO.email }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'START_DATE' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td>
							                           	<div class="datepicker-wrap disib sdate">
															<input type="text" class="datepick js-datepicker reldate maxnone" readonly="readonly" name="startDate" id="startDate" value="${selectVO.startDate}" >
								                       	</div>
								                	</td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'END_DATE' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td>
							                           	<div class="datepicker-wrap disib edate">
															<input type="text" class="datepick js-datepicker reldate maxnone" readonly="readonly" name="endDate" id="endDate" value="${selectVO.endDate}" >
								                       	</div>
								                	</td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD1' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td>
								                		<c:if test="${searchVO.bbsId eq 'BBSMSTR_000000000011'}">
								                			<input type="text" class="w50" id="tmpField1" name="tmpField1" value="${selectVO.tmpField1 }" onkeyup="this.value=this.value.replace(/[^0-9 -]/g,'');">
								                		</c:if>
								                		<c:if test="${searchVO.bbsId ne 'BBSMSTR_000000000011'}">
								                			<input type="text" class="w50" id="tmpField1" name="tmpField1" value="${selectVO.tmpField1 }">
								                		</c:if>
								                	</td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD2' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField2" name="tmpField2" value="${selectVO.tmpField2 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD3' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField3" name="tmpField3" value="${selectVO.tmpField3 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD4' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField4" name="tmpField4" value="${selectVO.tmpField4 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD5' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField5" name="tmpField5" value="${selectVO.tmpField5 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD6' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField6" name="tmpField6" value="${selectVO.tmpField6 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD7' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField7" name="tmpField7" value="${selectVO.tmpField7 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD8' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField8" name="tmpField8" value="${selectVO.tmpField8 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD9' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField9" name="tmpField9" value="${selectVO.tmpField9 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD10' }">
							                	<tr>
								                	<th class="tl">${name.itemName}</th>
								                	<td><input type="text" class="w50" id="tmpField10" name="tmpField10" value="${selectVO.tmpField10 }"></td>
							                	</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'NTT_CN' }">
								                <tr>
								                    <th class="tl">${name.itemName}</th>
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
							                </c:if>
						                </c:forEach>
					                </tbody>
					            </table>
					        </div>
			            </fieldset>
		            </form>
				</div>
			</div>
            <div class="btn-box">
		        <c:if test="${searchVO.command eq 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_list('insert');" class="tbl-btn">목록</a>
		            <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="#lnk" onclick="javascript:fn_cancel('insert');" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_list('update');" class="tbl-btn">목록</a>
		            <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		            <a href="#lnk" onclick="javascript:fn_cancel('update');" class="tbl-btn gray">취소</a>
	            </c:if>
            </div>
	    </div>
	</div>