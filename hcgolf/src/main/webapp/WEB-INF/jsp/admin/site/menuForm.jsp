<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


	<script>
		$(document).ready(function(){
			$("#menuType").change(function(){
				var v_type = $(this).val();
				if(v_type == "BBS"){
					$("#typeBbs").attr("style","display:block;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:none;");
				}else if(v_type == "CON"){
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:block;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:none;");
				}else if(v_type == "URL"){
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:block;");
					$("#typeMain").attr("style","display:none;");
				}else if(v_type == "MAIN"){
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:block;");
				}else {
					$("#typeBbs").attr("style","display:none;");
					$("#typeCon").attr("style","display:none;");
					$("#typeUrl").attr("style","display:none;");
					$("#typeMain").attr("style","display:none;");
				}
			});
			$("input[name=menuName]").keyup(function(event){ 
				if (!(event.keyCode >=37 && event.keyCode<=40)) {
					var inputVal = $(this).val();
					$(this).val(inputVal.replace(/[^a-z0-9-]/gi,''));
				}
			});
		});
		
		function fn_submit(v_command){

			if ($("#menuName").val() == "") {
				alert("Name을 입력해주세요.");
				$("#menuName").focus();
				return;
			}
			if ($("#menuTitle").val() == "") {
				alert("Title을 입력해주세요.");
				$("#menuTitle").focus();
				return;
			}
			if ($("#layoutId").val() == "") {
				alert("레이아웃을 선택해주세요.");
				$("#layoutId").focus();
				return;
			}
			if ($("#menuType").val() == "") {
				alert("메뉴 타입을 입력해주세요.");
				$("#menuType").focus();
				return;
			}
			if ($("#menuNo").val() == "") {
				alert("메뉴 순서를 입력해주세요.");
				$("#menuNo").focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/menuInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/menuUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/site/menuList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/site/menuList.do");
			$("#frm").submit();
		}
		function fn_select() {
			window.open("/_admin/site/selectConList.do", "selectConList", "width=1500,height=850");
		}
	</script>
	
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트관리</a></li>
	            <li><a href="#lnk">메뉴 관리</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">메뉴 관리</h2>
            <form action="" id="frm" name="frm" method="post">
	        	<c:if test="${searchVO.command eq 'insert' }">
            		<input type="hidden" name="menuDepth" value="${searchVO.menuDepth }" >
            		<c:if test="${searchVO.menuDepth ne 'dep0' }">
		            	<input type="hidden" name="upperMenuId" value="${upperVO.upperMenuId }" >
	            		<input type="hidden" name="menuIdDepth1" value="${upperVO.menuIdDepth1 }" >
	            		<input type="hidden" name="menuIdDepth2" value="${upperVO.menuIdDepth2 }" >
	            		<input type="hidden" name="menuIdDepth3" value="${upperVO.menuIdDepth3 }" >
	            		<input type="hidden" name="menuIdDepth4" value="${upperVO.menuIdDepth4 }" >
	            		<input type="hidden" name="menuIdDepth5" value="${upperVO.menuIdDepth5 }" >
	            		<input type="hidden" name="menuIdDepth6" value="${upperVO.menuIdDepth6 }" >
	            		<input type="hidden" name="menuNameDepth1" value="${upperVO.menuNameDepth1 }" >
	            		<input type="hidden" name="menuNameDepth2" value="${upperVO.menuNameDepth2 }" >
	            		<input type="hidden" name="menuNameDepth3" value="${upperVO.menuNameDepth3 }" >
	            		<input type="hidden" name="menuNameDepth4" value="${upperVO.menuNameDepth4 }" >
	            		<input type="hidden" name="menuNameDepth5" value="${upperVO.menuNameDepth5 }" >
	            		<input type="hidden" name="menuNameDepth6" value="${upperVO.menuNameDepth6 }" >
            		</c:if>
           		</c:if>
	        	<c:if test="${searchVO.command eq 'update' }">
            		<input type="hidden" name="menuName2" value="${menuVO.menuName }" >
            		<input type="hidden" name="menuDepth" value="${menuVO.menuDepth }" >
	            	<input type="hidden" name="upperMenuId" value="${menuVO.upperMenuId }" >
            		<input type="hidden" name="menuId" value="${menuVO.menuId }" >
            		<input type="hidden" name="menuIdDepth1" value="${menuVO.menuIdDepth1 }" >
            		<input type="hidden" name="menuIdDepth2" value="${menuVO.menuIdDepth2 }" >
            		<input type="hidden" name="menuIdDepth3" value="${menuVO.menuIdDepth3 }" >
            		<input type="hidden" name="menuIdDepth4" value="${menuVO.menuIdDepth4 }" >
            		<input type="hidden" name="menuIdDepth5" value="${menuVO.menuIdDepth5 }" >
            		<input type="hidden" name="menuIdDepth6" value="${menuVO.menuIdDepth6 }" >
            		<input type="hidden" name="menuNameDepth1" value="${menuVO.menuNameDepth1 }" >
            		<input type="hidden" name="menuNameDepth2" value="${menuVO.menuNameDepth2 }" >
            		<input type="hidden" name="menuNameDepth3" value="${menuVO.menuNameDepth3 }" >
            		<input type="hidden" name="menuNameDepth4" value="${menuVO.menuNameDepth4 }" >
            		<input type="hidden" name="menuNameDepth5" value="${menuVO.menuNameDepth5 }" >
            		<input type="hidden" name="menuNameDepth6" value="${menuVO.menuNameDepth6 }" >
            	</c:if>
	            <fieldset>
	                <div class="section-wrap res">
                        <div class="section">
		                    <p class="section-tit">메뉴 정보</p>
		                    <div class="tbl-wrap new-tbl res">
		                    	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">메뉴 정보 등록 테이블</caption>
		                            <colgroup>
										<col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <c:if test="${searchVO.command eq 'insert' && searchVO.menuDepth ne 'dep0' }">
				                            <tr>
				                                <th class="tl">메뉴경로</th>
				                                <td class="tl">
				                                    <p style="margin-bottom: 10px;">
				                                    	<c:if test="${searchVO.menuDepth eq 'dep1' }">/${upperVO.menuNameDepth1 }/</c:if>
				                                    	<c:if test="${searchVO.menuDepth eq 'dep2' }">/${upperVO.menuNameDepth1 }/${upperVO.menuNameDepth2 }/</c:if>
				                                    	<c:if test="${searchVO.menuDepth eq 'dep3' }">/${upperVO.menuNameDepth1 }/${upperVO.menuNameDepth2 }/${upperVO.menuNameDepth3 }/</c:if>
				                                    	<c:if test="${searchVO.menuDepth eq 'dep4' }">/${upperVO.menuNameDepth1 }/${upperVO.menuNameDepth2 }/${upperVO.menuNameDepth3 }/${upperVO.menuNameDepth4 }/</c:if>
				                                    	<c:if test="${searchVO.menuDepth eq 'dep5' }">/${upperVO.menuNameDepth1 }/${upperVO.menuNameDepth2 }/${upperVO.menuNameDepth3 }/${upperVO.menuNameDepth4 }/${upperVO.menuNameDepth5 }/</c:if>
				                                    </p>
				                                    <p></p>
				                                </td>
				                            </tr>
			                            </c:if>
			                            <tr>
			                                <th class="tl">Name <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" name="menuName" id="menuName" title="Name 입력" value="${menuVO.menuName }" placeholder="영문 및 숫자만 입력가능합니다."></td>
			                            </tr>
			                            <tr>
			                                <th class="tl">Title <em class="essential">*</em></th>
			                                <td class="tl"><input type="text" name="menuTitle" id="menuTitle" title="Title 입력" value="${menuVO.menuTitle }"></td>
			                            </tr>
			                            <tr>
			                                <th class="tl">사용여부</th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="menuUseFlag" value="Y" id="use" <c:if test="${menuVO.menuUseFlag ne 'N'}">checked="checked"</c:if>><label for="use">사용</label>
			                                        <input type="radio" name="menuUseFlag" value="N" id="un-use" <c:if test="${menuVO.menuUseFlag eq 'N'}">checked="checked"</c:if>><label for="un-use">미사용</label>
			                                    </div>
			                                </td>
			                            </tr>
			                            <tr>
			                                <th class="tl">노출여부</th>
			                                <td class="tl">
			                                    <div class="search-word">
			                                        <input type="radio" name="menuViewFlag" value="Y" id="view" <c:if test="${menuVO.menuViewFlag ne 'N'}">checked="checked"</c:if>><label for="view">노출</label>
			                                        <input type="radio" name="menuViewFlag" value="N" id="un-view" <c:if test="${menuVO.menuViewFlag eq 'N'}">checked="checked"</c:if>><label for="un-view">비노출</label>
			                                    </div>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
		                    <p class="section-tit">SEO 정보</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <!-- <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span> -->
		                        <table class="tbl03">
		                            <caption class="blind">SEO 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
	                                        <th class="tl">Page Title
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>페이지의 제목이며, 한글 최대 32자까지 입력 가능합니다. 미입력 시 title이 사용됩니다.</span></p></div>
	                                        </th>
			                                <td class="tl"><input type="text" name="seoPageTitle" id="seoPageTitle" title="Page Title 입력" value="${menuVO.seoPageTitle }"></td>
			                            </tr>
			                            <tr>
	                                        <th class="tl">Navigation Title
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>탐색 메뉴의 제목이며, 한글 최대 32자까지 입력 가능합니다. 미입력 시 page title이 사용됩니다.</span></p></div>
	                                        </th>
			                                <td class="tl"><input type="text" name="seoNaviTitle" id="seoNaviTitle" title="Navigation Title 입력" value="${menuVO.seoNaviTitle }"></td>
			                            </tr>
			                            <tr>
	                                        <th class="tl">Keywords
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>해당 페이지의 중요한 키워드가 무엇인지 지정해줄 수 있으며, 10~20개 정도의 단어가 적당합니다.</span></p></div>
	                                        </th>
			                                <td class="tl"><input type="text" name="seoKeywords" id="seoKeywords" title="Keywords 입력" value="${menuVO.seoKeywords }"></td>
			                            </tr>
			                            <tr>
	                                        <th class="tl">Description
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>해당 페이지를 요약한 설명이며, 한글 최대 72자까지 입력 가능합니다.</span></p></div>
	                                        </th>
			                                <td class="tl"><input type="text" name="seoDescription" id="seoDescription" title="Description 입력" value="${menuVO.seoDescription }"></td>
			                            </tr>
			                            <tr>
	                                        <th class="tl">SNS Image URL
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>SNS 공유 시 미리보기에 출력 될 이미지 URL입니다. http://를 포함하여 입력해주세요.</span></p></div>
	                                        </th>
			                                <td class="tl"><input type="text" name="seoImageUrl" id="seoImageUrl" title="SNS Image URL 입력" value="${menuVO.seoImageUrl }"></td>
			                            </tr>
			                            <tr>
	                                        <th class="tl">og:type
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>페이지의 유형입니다. article, music , video, movie, website 등이 있습니다.</span></p></div>
	                                        </th>
			                                <td class="tl">
			                                	<select name="seoOgType" id="seoOgType">
				                                    <option value="website"	<c:if test="${menuVO.seoOgType == 'website' }">selected</c:if>>website</option>
				                                    <option value="article" <c:if test="${menuVO.seoOgType == 'article' }">selected</c:if>>article</option>
				                                    <option value="music" <c:if test="${menuVO.seoOgType == 'music' }">selected</c:if>>music</option>
				                                    <option value="video" <c:if test="${menuVO.seoOgType == 'video' }">selected</c:if>>video</option>
				                                    <option value="movie" <c:if test="${menuVO.seoOgType == 'movie' }">selected</c:if>>movie</option>
				                                </select>
			                                </td>
			                            </tr>
			                            <tr>
	                                        <th class="tl">twitter:card
	                                            <div class="tooltip"><i class="ico-tip"></i><p><span>카드의 종류를 선택합니다. summary, photo, player 등이 있습니다.</span></p></div>
	                                        </th>
			                                <td class="tl">
			                                	<select name="seoTwitterCard" id="seoTwitterCard">
				                                    <option value="summary" <c:if test="${menuVO.seoTwitterCard == 'summary' }">selected</c:if>>summary</option>
				                                    <option value="photo" <c:if test="${menuVO.seoTwitterCard == 'photo' }">selected</c:if>>photo</option>
				                                    <option value="player" <c:if test="${menuVO.seoTwitterCard == 'player' }">selected</c:if>>player</option>
				                                </select>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		                <div class="section">
			            	<p class="section-tit">메뉴 정보</p>
			            	<div class="tbl-wrap new-tbl res">
				            	<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">메뉴 순서 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
		                            <tr>
		                                <th class="tl">메뉴 순서 <em class="essential">*</em></th>
		                                <td class="tl"><input type="text" name="menuNo" id="menuNo" title="메뉴 순서 입력" class="w50" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" value="${menuVO.menuNo }"></td>
		                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
			            </div>
		                <div class="section">
		                    <p class="section-tit">콘텐츠 정보</p>
		                    <div class="tbl-wrap new-tbl res">
			                    <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
		                        <table class="tbl03">
		                            <caption class="blind">콘텐츠 정보 등록 테이블</caption>
		                            <colgroup>
		                                <col width="20%" class="change1">
                                    	<col width="80%" class="change2">
		                            </colgroup>
		                            <tbody>
			                            <tr>
			                                <th class="tl">콘텐츠 선택 <em class="essential">*</em></th>
			                                <td class="tl">
			                                	<input type="hidden" name="contentsId" id="contentsId" <c:if test="${searchVO.command ne 'insert' }">value="${menuVO.contentsId }"</c:if>/>
			                                	<input type="text" name="contentsName" id="contentsName" <c:if test="${searchVO.command ne 'insert' }">value="${menuVO.contentsId } - ${menuVO.contentsName }"</c:if> readonly="readonly" style="width:50%"/>
			                                	<div class="pagenavbox" style="display: inline;">
			                                		<button type="button" class="btn_search gray" onclick="javascript:fn_select();">콘텐츠 조회</button>
			                                	</div>
			                                </td>
			                            </tr>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
	                </div>
	            </fieldset>
	        </form>
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