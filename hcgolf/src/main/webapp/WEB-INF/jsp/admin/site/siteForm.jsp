<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

	
	<script>
		function fn_submit(){
			if($("#siteTitle").val() == null || $("#siteTitle").val() == ''){
				alert("사이트 타이틀을 입력해주세요.");
				$("#siteTitle").focus();
				return;
			}
			/* 
			if($("#siteDomain").val() == null || $("#siteDomain").val() == ''){
				alert("사이트 도메인을 입력해주세요.");
				$("#siteDomain").focus();
				return;
			}
			*/
			if($("#menuId").val() == null || $("#menuId").val() == ''){
				alert("메인페이지를 선택해주세요.");
				$("#menuName").focus();
				return;
			}
			if(confirm('저장하시겠습니까?')) {
				$("#frm").attr("action", "/_admin/site/siteUpdate.do");
				$("#frm").submit();
			}
		}

		function fn_select(v_command) {
			window.open("/_admin/site/selectMenuList.do?command="+v_command, "selectMenuList", "width=1500,height=850");
		}
	</script>
	<div class="container-wrap">
	    <div class="breadcrumbs">
	        <ul>
	            <li><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
	            <li><a href="#lnk">사이트 관리</a></li>
	            <li><a href="#lnk">사이트 기본설정</a></li>
	        </ul>
	    </div>
	    <div class="container scroll">
	        <h2 class="con-tit">사이트 기본설정</h2>
	        <div class="section-wrap res">
	            <div class="section">
		        	<form action="" id="frm" name="frm" method="post">
		                <fieldset>
                        	<p class="section-tit">기본설정</p>
                        	<div class="tbl-wrap new-tbl res">
					            <span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
					            <table class="tbl03">
					                <caption class="blind">사이트 기본설정 테이블</caption>
					                <colgroup>
					                    <col style="width:15%">
					                    <col style="width:85%">
					                </colgroup>
					                <tbody>
						                <tr>
						                    <th class="tl">사이트 타이틀 <em class="essential">*</em></th>
						                    <td>
						                        <input type="text" title="사이트 타이틀 입력" name="siteTitle" id="siteTitle" value="${indexVO.siteTitle}" maxlength="20"/>
						                    </td>
						                </tr>
						                <%-- 
						                <tr>
						                    <th class="tl">사이트 도메인</th>
						                    <td>
						                        <input type="text" title="사이트 도메인 입력" name="siteDomain" id="siteDomain" value="${indexVO.siteDomain}" maxlength="100"/>
						                    </td>
						                </tr>
						                --%>
			                            <tr>
			                                <th class="tl">메인페이지 선택 <em class="essential">*</em></th>
			                                <td class="tl">
			                                	<input type="hidden" name="menuId" id="menuId" value="${indexVO.menuId }"/>
			                                	<input type="text" name="menuName" id="menuName" value="${indexVO.menuName }" readonly="readonly" style="width:50%"/>
			                                	<div class="pagenavbox" style="display: inline;">
			                                		<button type="button" class="btn_search gray" onclick="javascript:fn_select('main');">페이지 조회</button>
			                                	</div>
			                                </td>
			                            </tr>
			                            <%-- 
			                            <tr>
			                                <th class="tl">로그인페이지 선택</th>
			                                <td class="tl">
			                                	<input type="hidden" name="loginMenuId" id="loginMenuId" value="${indexVO.loginMenuId }"/>
			                                	<input type="text" name="loginMenuName" id="loginMenuName" value="${indexVO.loginMenuName }" readonly="readonly" style="width:50%"/>
			                                	<div class="pagenavbox" style="display: inline;">
			                                		<button type="button" class="btn_search gray" onclick="javascript:fn_select('login');">페이지 조회</button>
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
	            <a href="#lnk;" onclick="javascript:fn_submit();" class="tbl-btn blue">저장</a>
            </div>
	    </div>
	</div>