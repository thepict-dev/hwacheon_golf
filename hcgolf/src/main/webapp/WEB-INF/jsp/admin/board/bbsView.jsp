<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script>
		function fn_list(pageNo) {
			$("#pageIndex").val(pageNo);
			$("#frm").attr("action","/_admin/board/bbsList.do");
			$("#frm").submit();
		}
		
		function fn_delete() {
			if (confirm("삭제하시겠습니까?")) {
				$("#frm").attr("action","/_admin/board/bbsDelete.do");
				$("#frm").submit();
			}
		}
		
		function fn_update() {
			$("#command").val("update");
			$("#frm").attr("action","/_admin/board/bbsForm.do");
			$("#frm").submit();
		}
		
		function fn_reply() {
			$("#command").val("insert");
			$("#frm").attr("action","/_admin/board/bbsReplyForm.do");
			$("#frm").submit();
		}
		
		function fn_replyUpdate() {
			$("#replyCommand").val("update");
			$("#replyFrm").attr("action","/_admin/board/bbsReplyForm.do");
			$("#replyFrm").submit();
		}
		
		function fn_replyDelete() {
			if (confirm("답변을 삭제하시겠습니까?")) {
				$("#replyFrm").attr("action","/_admin/board/bbsReplyDelete.do");
				$("#replyFrm").submit();
			}
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시물 관리</a></li>
	            <li><a href="#lnk">${brdMstrVO.bbsNm}</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">${brdMstrVO.bbsNm}</h2>
	        <div class="section-wrap res">
	            <div class="section">
					<form id="frm" name="frm" method="post" action="">
						<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
						<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" >
						<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" >
						<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >
						<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
						<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
						<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
						<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
						<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
						<input type="hidden" name="bbsTyCode" value="<c:out value='${searchVO.bbsTyCode}'/>"/>
						<input type="hidden" name="command" id="command" value="">
						<input type="hidden" name="searchCate1" value="<c:out value='${searchVO.searchCate1}'/>"/>
						<input type="hidden" name="searchCate2" value="<c:out value='${searchVO.searchCate2}'/>"/>
						<input type="hidden" name="searchCate3" value="<c:out value='${searchVO.searchCate3}'/>"/>
				        <fieldset>
                        	<p class="section-tit">${brdMstrVO.bbsNm} VIEW</p>
							<div class="tbl-wrap new-tbl res">
					            <table class="tbl03">
					                <caption class="blind">${brdMstrVO.bbsNm} VIEW 테이블</caption>
					                <colgroup>
					                    <col style="width:20%" class="change1">
					                    <col style="width:80%" class="change2">
					                </colgroup>
					                <tbody>
						                <c:forEach var="name" items="${itemList}" varStatus="status">
						        			<c:if test="${name.fieldId eq 'NTT_SJ' }">
								                <tr>
								                	<th class="tl">${name.itemName}</th>
								                    <td>${result.nttSj}</td>
								                </tr>
							                </c:if>
						        			<c:if test="${name.fieldId eq 'NTCR_NM' }">
								                <tr>
								                    <th class="tl">${name.itemName}</th>
								                    <td>${result.frstRegisterNm}</td>
								                </tr>
							                </c:if>
							                <c:if test="${searchVO.bbsId ne 'BBSMSTR_000000000023' && searchVO.bbsId ne 'BBSMSTR_000000000024'}">
							        			<c:if test="${brdMstrVO.bbsTyCode eq 'BBST01' }">
								        			<c:if test="${brdMstrVO.fileAtchPosblAt eq 'Y' }">
							        					<c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
											                <tr class="tl">
											                	<th class="tl">${name.itemName}</th>
											                    <td>
											                        <div class="file-box">
											                            <ul class="file-box-right">
														                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
																				<c:param name="param_atchFileId" value="${result.atchFileId}" />
																			</c:import>
											                            </ul>
											                        </div>
											                    </td>
											                </tr>
										                </c:if>
									                </c:if>
						        					<c:if test="${name.fieldId eq 'THUMBNAIL' }">
										                <tr class="tl">
										                	<th class="tl">${name.itemName}</th>
										                    <td>
										                        <div class="file-box">
										                            <ul class="file-box-right">
													                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
																			<c:param name="param_atchFileId" value="${result.atchFileId}" />
																		</c:import>
										                            </ul>
										                        </div>
										                    </td>
										                </tr>
									                </c:if>
								                </c:if>
							        			<c:if test="${brdMstrVO.bbsTyCode ne 'BBST01' }">
							        				<c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
										                <tr class="tl">
										                	<th class="tl">${name.itemName}</th>
										                    <td>
										                        <div class="file-box">
										                            <ul class="file-box-right">
													                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
																			<c:param name="param_atchFileId" value="${result.atchFileId}" />
																		</c:import>
										                            </ul>
										                        </div>
										                    </td>
										                </tr>
									                </c:if>
								                </c:if>
								            </c:if>
								            <c:if test="${searchVO.bbsId eq 'BBSMSTR_000000000023' || searchVO.bbsId eq 'BBSMSTR_000000000024'}">
							        			<c:if test="${brdMstrVO.fileAtchPosblAt eq 'Y' }">
						        					<c:if test="${name.fieldId eq 'THUMBNAIL' }">
										                <tr class="tl">
										                	<th class="tl">${name.itemName}</th>
										                    <td>
										                        <div class="file-box">
										                            <ul class="file-box-right">
										                            	<c:if test="${not empty thumbnail }">
										                            		<li class="down-file">
										                            			<a href="javascript:fn_egov_downThumbnailFile('<c:out value="${thumbnail.atchFileId}"/>','thumbnail')" class="fileDown" title="첨부파일 다운로드">
													                    			<span class="ico-file jpg"></span>&nbsp;<span>${thumbnail.orignlFileNm}</span>
													                    		</a>
													                    	</li>
													                    </c:if>
										                            </ul>
										                        </div>
										                    </td>
										                </tr>
									                </c:if>
						        					<c:if test="${name.fieldId eq 'ATCH_FILE_ID' }">
										                <tr class="tl">
										                	<th class="tl">${name.itemName}</th>
										                    <td>
										                        <div class="file-box">
										                            <ul class="file-box-right">
													                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
																			<c:param name="param_atchFileId" value="${result.atchFileId}" />
																			<c:param name="viewFlag" value="view" />
																		</c:import>
										                            </ul>
										                        </div>
										                    </td>
										                </tr>
									                </c:if>
								                </c:if>
								            </c:if>
						        			<c:if test="${brdMstrVO.cateType01 eq 'Y' }">
							                	<c:if test="${name.fieldId eq 'CATE_TYPE01' }">
							                		<tr>
								                		<th class="tl">${name.itemName}</th>
								                		<td>${result.cateName01}</td>
							                		</tr>
							                	</c:if>
						                	</c:if>
						        			<c:if test="${brdMstrVO.cateType02 eq 'Y' }">
							                	<c:if test="${name.fieldId eq 'CATE_TYPE02' }">
							                		<tr>
								                		<th class="tl">${name.itemName}</th>
								                		<td>${result.cateName02}</td>
							                		</tr>
							                	</c:if>
						                	</c:if>
						        			<c:if test="${brdMstrVO.cateType03 eq 'Y' }">
							                	<c:if test="${name.fieldId eq 'CATE_TYPE03' }">
							                		<tr>
								                		<th class="tl">${name.itemName}</th>
								                		<td>${result.cateName03}</td>
							                		</tr>
							                	</c:if>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'RDCNT' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.inqireCo}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'USER_TEL' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.userTel}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'USER_CEL' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.userCel}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'ADDRESS' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.zipCode} ${result.address} ${result.detailAddr}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'EMAIL' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.email}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'START_DATE' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.startDate}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'END_DATE' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.endDate}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD1' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField1}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD2' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField2}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD3' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField3}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD4' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField4}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD5' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField5}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD6' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField6}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD7' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField7}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD8' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField8}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD9' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField9}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'TMP_FIELD10' }">
						                		<tr>
							                		<th class="tl">${name.itemName}</th>
							                		<td>${result.tmpField10}</td>
						                		</tr>
						                	</c:if>
						                	<c:if test="${name.fieldId eq 'NTT_CN' }">
								                <tr class="type-hei">
								                	<th class="tl">${name.itemName}</th>
								                    <td>${result.nttCn}</td>
								                </tr>
							                </c:if>
						                	<c:if test="${name.fieldId eq 'REG_DATE' }">
								                <tr>
								                    <th class="tl">${name.itemName}</th>
								                    <td>${result.frstRegisterPnttm}</td>
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
                <a href="#lnk" onclick="javascript:fn_list('${searchVO.pageIndex}');" class="tbl-btn">목록</a>
				<c:if test="${brdMstrVO.replyPosblAt eq 'Y' }">
					<c:if test="${reply_result eq '' || reply_result eq null }">
                		<a href="#lnk" onclick="javascript:fn_reply('insert');" class="tbl-btn sky-blue">답변</a>
					</c:if>
                </c:if>
                <a href="#lnk" onclick="javascript:fn_update();" class="tbl-btn blue">수정</a>
                <a href="#lnk" onclick="javascript:fn_delete();" class="tbl-btn red">삭제</a>
            </div>
            <c:if test="${reply_result ne '' && reply_result ne null }">
		        <div class="section-wrap mt80">
		            <div class="section">
						<form id="replyFrm" name="replyFrm" method="post" action="">
							<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
							<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
							<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
							<input type="hidden" name="bbsId" value="<c:out value='${reply_result.bbsId}'/>" >
							<input type="hidden" name="nttId" value="<c:out value='${reply_result.nttId}'/>" >
							<input type="hidden" name="nttSj" value="<c:out value='${reply_result.nttSj}'/>" >
							<input type="hidden" name="parnts" value="<c:out value='${reply_result.parnts}'/>" >
							<input type="hidden" name="sortOrdr" value="<c:out value='${reply_result.sortOrdr}'/>" >
							<input type="hidden" name="replyLc" value="<c:out value='${reply_result.replyLc}'/>" >
							<input type="hidden" name="command" id="replyCommand" value="">
					        <fieldset>
	                        	<p class="section-tit">${result.nttSj} 답변</p>
								<div class="tbl-wrap new-tbl">
						            <table class="tbl03">
						                <caption class="blind">${brdMstrVO.bbsNm} VIEW 테이블</caption>
						                <colgroup>
						                    <col style="width:15%">
						                    <col style="width:85%">
						                </colgroup>
						                <tbody>
						                <tr>
						                	<th class="tl">제목</th>
						                    <td>${reply_result.nttSj}</td>
						                </tr>
						                <tr>
						                    <th class="tl">작성자</th>
						                    <td>${reply_result.frstRegisterNm}</td>
						                </tr>
						                <tr>
						                    <th class="tl">작성일</th>
						                    <td>${reply_result.frstRegisterPnttm}</td>
						                </tr>
						                <tr class="tl">
						                	<th class="tl">첨부파일</th>
						                    <td>
						                        <div class="file-box">
						                            <ul class="file-box-right">
									                    <c:import url="/_cmm/fms/selectFileInfs.do" charEncoding="utf-8">
															<c:param name="param_atchFileId" value="${reply_result.atchFileId}" />
														</c:import>
						                            </ul>
						                        </div>
						                    </td>
						                </tr>
						                <tr>
						                	<th class="tl">내용</th>
						                    <td>${reply_result.nttCn}</td>
						                </tr>
						                </tbody>
						            </table>
				        		</div>
				            </fieldset>
			            </form>
					</div>
				</div>
	            <div class="btn-box">
	                <a href="#lnk" onclick="javascript:fn_replyUpdate('update');" class="tbl-btn blue">수정</a>
	                <a href="#lnk" onclick="javascript:fn_replyDelete();" class="tbl-btn red">삭제</a>
	            </div>
            </c:if>
	    </div>
	</div>