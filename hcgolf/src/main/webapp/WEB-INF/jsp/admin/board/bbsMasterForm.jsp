<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script>
		function fn_submit(v_command){
	
			if ($("#bbsNm").val() == "") {
				alert("게시판 명을 입력해주세요.");
				$("#bbsNm").focus();
				return;
			}
			if ($("#bbsIntrcn").val() == "") {
				alert("게시판 설명을 입력해주세요.");
				$("#bbsIntrcn").focus();
				return;
			}
			if ($("#bbsTyCode").val() == "") {
				alert("게시판 Type을 입력해주세요.");
				$("#bbsTyCode").focus();
				return;
			}
			if ($("#skinId").val() == "") {
				alert("게시판 스킨을 선택해주세요.");
				$("#skinId").focus();
				return;
			}
			if ($("#bbsOrder").val() == "") {
				alert("게시판 순서를 입력해주세요.");
				$("#bbsOrder").focus();
				return;
			}
			if ($("#bbsPageUnit").val() == "") {
				alert("게시물 출력 개수를 입력해주세요.");
				$("#bbsPageUnit").focus();
				return;
			}
			var checkVal = $("input[name='fileAtchPosblAt']:checked").val();
			if (checkVal == 'Y'){
				if ($("#atchPosblFileNumber").val() == "") {
					alert("첨부파일 개수를 입력해주세요.");
					$("#atchPosblFileNumber").focus();
					return;
				}
				if ($("#atchPosblFileSize").val() == "") {
					alert("첨부파일 최대용량을 입력해주세요.");
					$("#atchPosblFileSize").focus();
					return;
				}
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/board/bbsMasterInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/board/bbsMasterUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/board/bbsMasterList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/board/bbsMasterList.do");
			$("#frm").submit();
		}
		
        $(document).ready(function(){
            if($("input:radio[name='fileAtchPosblAt'][value='Y']").is(':checked')){
                $('.use-file').addClass('active');
            }
            $("input:radio[name='fileAtchPosblAt'][value='Y']").click(function(){
                $(this).val(':checked');
                if($(this).is(':checked')){
                    $('.use-file').addClass('active');
                    $(this).val('Y');
                }
            });
            $("input:radio[name='fileAtchPosblAt'][value='N']").click(function(){
                $(this).val(':checked');
                if($(this).is(':checked')){
                    $('.use-file').removeClass('active');
    	        	<c:if test="${searchVO.command eq 'insert' }">
                        $("#atchPosblFileNumber").val(5);
                        $("#atchPosblFileSize").val(20);
                    </c:if>
                    $(this).val('N');
                }
            });
        });
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	            <li><a href="#lnk">게시판 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">게시판 관리</h2>
	        <div class="section-wrap res">
	            <form action="" id="frm" name="frm" method="post">
	            	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
					<input type="hidden" name="searchWrd" value="<c:out value='${searchVO.searchWrd}'/>"/>
					<input type="hidden" name="searchCnd" value="<c:out value='${searchVO.searchCnd}'/>"/>
		        	<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="bbsId" value="${selectVO.bbsId }" >
	            	</c:if>
	                <fieldset>
            			<div class="section">
		                    <p class="section-tit">게시판 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                    	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">게시판 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">게시판 명 <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" title="게시판 명 입력" name="bbsNm" id="bbsNm" value="${selectVO.bbsNm }"></td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판 설명 <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
			                                    <textarea class="txt" title="게시판 설명 입력" name="bbsIntrcn" id="bbsIntrcn">${selectVO.bbsIntrcn }</textarea>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판 Type <em class="essential">*</em></th>
			                                <td class="text-box info-ir">
					        					<c:if test="${searchVO.command eq 'insert' }">
				                                    <select name="bbsTyCode" id="bbsTyCode">
				                                        <option value="">선택</option>
				                                        <option value="BBST01" selected="selected">일반 게시판</option>
				                                        <option value="BBST02">갤러리 게시판</option>
				                                    </select>
					        					</c:if>
					        					<c:if test="${searchVO.command ne 'insert' }">
				                                    <select name="bbsTyCode" id="bbsTyCode">
				                                        <option value="">선택</option>
				                                        <option value="BBST01" <c:if test="${selectVO.bbsTyCode eq 'BBST01'}">selected="selected"</c:if>>일반 게시판</option>
				                                        <option value="BBST02" <c:if test="${selectVO.bbsTyCode eq 'BBST02'}">selected="selected"</c:if>>갤러리 게시판</option>
				                                    </select>
					        					</c:if>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판 스킨 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <select name="skinId" id="skinId">
			                                        <option value="">선택</option>
													<c:forEach var="skinResult" items="${skinList}" varStatus="status">
			                                        	<option value="${skinResult.skinId }" <c:if test="${selectVO.skinId eq skinResult.skinId}">selected="selected"</c:if>>${skinResult.skinId } - ${skinResult.skinName }</option>
			                                        </c:forEach>
			                                    </select>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판 순서 <em class="essential">*</em></th>
			                                <td class="tl"><input class="ipWid wid200" style="text-align: right;" placeholder="0" type="number" title="게시판 순서 입력" name="bbsOrder" id="bbsOrder" value="${selectVO.bbsOrder }"></td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시물 출력 개수 <em class="essential">*</em></th>
				        					<c:if test="${searchVO.command eq 'insert' }">
			                                	<td class="tl"><input class="ipWid wid200" style="text-align: right;" placeholder="0" type="number" title="게시물 출력 개수 입력" name="bbsPageUnit" id="bbsPageUnit" value="10"></td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
			                                	<td class="tl"><input class="ipWid wid200" style="text-align: right;" placeholder="0" type="number" title="게시물 출력 개수 입력" name="bbsPageUnit" id="bbsPageUnit" value="${selectVO.bbsPageUnit }"></td>
				        					</c:if>
			                            </tr>
			                            <tr>
			                                <th class="tl">답글 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="replyPosblAt" value="Y" <c:if test="${selectVO.replyPosblAt eq 'Y'}">checked="checked"</c:if>><label for="use">사용</label>
			                                        <input type="radio" name="replyPosblAt" value="N" <c:if test="${selectVO.replyPosblAt ne 'Y'}">checked="checked"</c:if>><label for="un-use">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">비밀글 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="secretPosblAt" value="Y" <c:if test="${selectVO.secretPosblAt eq 'Y'}">checked="checked"</c:if>><label for="use">사용</label>
			                                        <input type="radio" name="secretPosblAt" value="N" <c:if test="${selectVO.secretPosblAt ne 'Y'}">checked="checked"</c:if>><label for="un-use">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">공지글 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="noticePosblAt" value="Y" <c:if test="${selectVO.noticePosblAt eq 'Y'}">checked="checked"</c:if>><label for="use">사용</label>
			                                        <input type="radio" name="noticePosblAt" value="N" <c:if test="${selectVO.noticePosblAt ne 'Y'}">checked="checked"</c:if>><label for="un-use">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">게시판 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="useAt" value="Y" <c:if test="${selectVO.useAt ne 'N'}">checked="checked"</c:if>><label for="use3">사용</label>
			                                        <input type="radio" name="useAt" value="N" <c:if test="${selectVO.useAt eq 'N'}">checked="checked"</c:if>><label for="un-use3">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
				        </div>
				        <div class="section">
		                    <p class="section-tit">첨부파일 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">게시판 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">첨부파일 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="fileAtchPosblAt" value="Y" <c:if test="${selectVO.fileAtchPosblAt ne 'N'}">checked="checked"</c:if>><label for="use2">사용</label>
			                                        <input type="radio" name="fileAtchPosblAt" value="N" <c:if test="${selectVO.fileAtchPosblAt eq 'N'}">checked="checked"</c:if>><label for="un-use2">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr class="use-file">
			                                <th class="tl">첨부파일 개수 <em class="essential">*</em></th>
				        					<c:if test="${searchVO.command eq 'insert' }">
			                                	<td class="tl"><input class="ipWid wid200" maxlength="2" style="text-align: right;" type="text" title="첨부파일 개수 입력" name="atchPosblFileNumber" id="atchPosblFileNumber" value="5" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
			                                	<td class="tl"><input class="ipWid wid200" maxlength="2" style="text-align: right;" type="text" title="첨부파일 개수 입력" name="atchPosblFileNumber" id="atchPosblFileNumber" value="${selectVO.atchPosblFileNumber }" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
				        					</c:if>
			                            </tr>
			                            <tr class="use-file">
			                                <th class="tl">첨부파일 최대용량 <em class="essential">*</em></th>
				        					<c:if test="${searchVO.command eq 'insert' }">
			                                	<td class="tl"><input class="ipWid wid200" maxlength="4" style="text-align: right;" type="text" title="첨부파일 최대용량 입력" name="atchPosblFileSize" id="atchPosblFileSize" value="20" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">MB</td>
				        					</c:if>
				        					<c:if test="${searchVO.command ne 'insert' }">
			                                	<td class="tl"><input class="ipWid wid200" maxlength="4" style="text-align: right;" type="text" title="첨부파일 최대용량 입력" name="atchPosblFileSize" id="atchPosblFileSize" value="${selectVO.atchPosblFileSize }" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">MB</td>
				        					</c:if>
			                            </tr>
	                            	</tbody>
	                            </table>
		                    </div>
				        </div>
				        <div class="section">
		                    <p class="section-tit">카테고리 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">게시판 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">카테고리1 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="cateType01" value="Y" <c:if test="${selectVO.cateType01 eq 'Y'}">checked="checked"</c:if>><label for="cateUse1">사용</label>
			                                        <input type="radio" name="cateType01" value="N" <c:if test="${selectVO.cateType01 ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse1">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">카테고리2 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="cateType02" value="Y" <c:if test="${selectVO.cateType02 eq 'Y'}">checked="checked"</c:if>><label for="cateUse2">사용</label>
			                                        <input type="radio" name="cateType02" value="N" <c:if test="${selectVO.cateType02 ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse2">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">카테고리3 사용여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="cateType03" value="Y" <c:if test="${selectVO.cateType03 eq 'Y'}">checked="checked"</c:if>><label for="cateUse3">사용</label>
			                                        <input type="radio" name="cateType03" value="N"<c:if test="${selectVO.cateType03 ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse3">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
	                            	</tbody>
	                            </table>
		                    </div>
				        </div>
				        <div class="section">
		                    <p class="section-tit">사용자 버튼 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                        <table class="tbl03">
		                            <caption class="blind">게시판 관리 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">로그인 체크여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="loginChkAt" value="Y" <c:if test="${selectVO.loginChkAt eq 'Y'}">checked="checked"</c:if>><label for="use3">사용</label>
			                                        <input type="radio" name="loginChkAt" value="N" <c:if test="${selectVO.loginChkAt ne 'Y'}">checked="checked"</c:if>><label for="un-use3">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">글쓰기 버튼 출력여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="writeBtnFlag" value="Y" <c:if test="${selectVO.writeBtnFlag eq 'Y'}">checked="checked"</c:if>><label for="cateUse1">사용</label>
			                                        <input type="radio" name="writeBtnFlag" value="N" <c:if test="${selectVO.writeBtnFlag ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse1">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">수정 버튼 출력여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="updateBtnFlag" value="Y" <c:if test="${selectVO.updateBtnFlag eq 'Y'}">checked="checked"</c:if>><label for="cateUse2">사용</label>
			                                        <input type="radio" name="updateBtnFlag" value="N" <c:if test="${selectVO.updateBtnFlag ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse2">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">삭제 버튼 출력여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="deleteBtnFlag" value="Y" <c:if test="${selectVO.deleteBtnFlag eq 'Y'}">checked="checked"</c:if>><label for="cateUse3">사용</label>
			                                        <input type="radio" name="deleteBtnFlag" value="N"<c:if test="${selectVO.deleteBtnFlag ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse3">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">답변 버튼 출력여부 <em class="essential">*</em></th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="replyBtnFlag" value="Y" <c:if test="${selectVO.replyBtnFlag eq 'Y'}">checked="checked"</c:if>><label for="cateUse3">사용</label>
			                                        <input type="radio" name="replyBtnFlag" value="N" <c:if test="${selectVO.replyBtnFlag ne 'Y'}">checked="checked"</c:if>><label for="un-cateUse3">미사용</label>
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
		        <c:if test="${searchVO.command eq 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="#lnk" onclick="javascript:fn_submit('insert');" class="tbl-btn blue">등록</a>
		            <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
		        <c:if test="${searchVO.command ne 'insert' }">
		            <a href="#lnk" onclick="javascript:fn_list();" class="tbl-btn">목록</a>
		            <a href="#lnk" onclick="javascript:fn_submit('update');" class="tbl-btn blue">수정</a>
		            <a href="#lnk" onclick="javascript:fn_cancel();" class="tbl-btn gray">취소</a>
	            </c:if>
	        </div>
	    </div>
	</div>