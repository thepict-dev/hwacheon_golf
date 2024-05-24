<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	<script>
		function fn_submit(){
			if($("#defaultName").val() == null || $("#defaultName").val() == ''){
				alert("고객명을 입력해주세요.");
				$("#defaultName").focus();
				return;
			}
			
			if(confirm('저장하시겠습니까?')) {
				$("#frm").attr("action", "/_admin/secure/defaultUpdate.do");
				$("#frm").submit();
			}
		}

		function fn_reset(){
			if(confirm('초기화하시겠습니까?')) {
				$("#frm").attr("action", "/_admin/secure/defaultReset.do");
				$("#frm").submit();
			}
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">보안 관리</a></li>
	            <li><a href="#lnk">관리자 기본설정</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">관리자 기본설정</h2>
	        <div class="section-wrap res">
	            <div class="section">
		        	<form action="" id="frm" name="frm" method="post" enctype="multipart/form-data">
			            <input type="hidden" name="logoFileId" value="${defaultVO.logoFileId}">
		        		<input type="hidden" name="logoFilePath" value="${defaultVO.logoFilePath}">
		        		<input type="hidden" name="ipLimitFlag" value="${defaultVO.ipLimitFlag}">
		        		<input type="hidden" name="returnUrl" value="/_admin/secure/defaultForm.do">
		        		
		                <fieldset>
                        	<p class="section-tit">기본설정</p>
                        	<div class="tbl-wrap new-tbl res">
					            <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
					            <table class="tbl03">
					                <caption class="blind">관리자 기본설정 테이블</caption>
					                <colgroup>
					                    <col style="width:15%">
					                    <col style="width:85%">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">고객명 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="고객명 입력" name="defaultName" id="defaultName" value="${defaultVO.defaultName}" maxlength="20"/>
						                    </td>
						                </tr>
						                <tr>
						                    <th class="tl">로고 <em class="essential">*</em></th>
						                    <td class="add-file">
					                            <c:if test="${ defaultVO.logoFileId ne null }">
					                    			<img style="max-width:100%" src="${defaultVO.logoFilePath}" alt="로고">
					                    		</c:if>
						                        <div class="add-file-inner">
						                            <input disabled="disabled" class="upload" placeholder="선택된 파일 없음">
						                            <label for="pcImg" class="btn-sel">파일선택</label>
						                            <input type="file" id="pcImg" class="blind" name="pcImg">
						                        </div>
						                    </td>
										</tr>
						                <%--
						                <tr>
						                    <th class="tl">접근제한 사용여부 <em class="essential">*</em></th>
						                    <td>
						                        <div class="radio-wrap pt12" style="color: rgb(0, 0, 0);">
						                            <input type="radio" value="N" name="ipLimitFlag" <c:if test="${defaultVO.ipLimitFlag ne 'Y'}">checked</c:if> >미사용
						                            <input type="radio" value="Y" name="ipLimitFlag" <c:if test="${defaultVO.ipLimitFlag eq 'Y'}">checked</c:if> >사용
						                        </div>
						                    </td>
						                </tr>
						                --%>
					                </tbody>
					            </table>
					        </div>
						</fieldset>
				    </form>
				</div>
			</div>
            <div class="btn-box">
	            <a href="#lnk;" onclick="javascript:fn_reset();" class="tbl-btn red">초기화</a>
	            <a href="#lnk;" onclick="javascript:fn_submit();" class="tbl-btn blue">저장</a>
            </div>
	    </div>
	</div>