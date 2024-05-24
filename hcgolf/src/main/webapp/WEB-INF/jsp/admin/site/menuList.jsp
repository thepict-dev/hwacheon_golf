<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


	<script>
		function fn_insert(v_menuDepth, v_upperMenuId) {
			$("#command").val("insert");
			$("#menuDepth").val(v_menuDepth);
			$("#upperMenuId").val(v_upperMenuId);
			$("#frm").attr("action","/_admin/site/menuForm.do");
			$("#frm").submit();
		}
		function fn_update(v_menuId) {
			$("#command").val("update");
			$("#menuId").val(v_menuId);
			$("#frm").attr("action","/_admin/site/menuForm.do");
			$("#frm").submit();
		}
		function fn_delete(v_menuId) {
			if(confirm("메뉴를 삭제하시겠습니까?")){
				$("#menuId").val(v_menuId);
				$("#frm").attr("action","/_admin/site/menuDelete.do");
				$("#frm").submit();
			}
		}
		function fn_backup(v_menuId) {
			window.open("/_admin/site/menuBakList.do?menuId="+v_menuId, "menuBakList", "width=1040,height=800");
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
	    <div class="container">
	        <h2 class="con-tit">메뉴 관리</h2>
	        <div class="add-btn-box">
	            <button type="button" onclick="javascript:fn_insert('dep0','0');">1차 메뉴 등록</button>
	        </div>
	        <div class="tbl-wrap">
	            <table class="tbl01 list mt20">
	                <caption class="blind">메뉴 관리 목록 테이블</caption>
	                <colgroup>
						<col style="width:">
						<col style="width:30%">
						<col style="width:100px">
						<col style="width:100px">
						<col style="width:100px">
						<col style="width:80px">
						<col style="width:80px">
						<col style="width:80px">
	                </colgroup>
	                <thead>
		                <tr>
		                    <th>Name</th>
		                    <th>Title</th>
		                    <th>사용여부</th>
		                    <th>메뉴순서</th>
		                    <th>하위메뉴<br>등록</th>
		                    <!-- <th>백업</th> -->
		                    <th>수정</th>
		                    <th>삭제</th>
		                    <th>콘텐츠<br>수정</th>
		                </tr>
	                </thead>
					<c:forEach var="result" items="${resultList}" varStatus="status">
		                <c:if test="${result.menuDepth eq 'dep1'}">
                			<c:if test="${status.count != 0 }">
                				</tbody>
                			</c:if>
                			<tbody class="dep-wrap">
		                </c:if>
	                    <tr class="list-${result.menuDepth }">
	                    	<c:if test="${result.menuChildFlag ne 'N' }">
	                        	<td class="tl child-btn ${result.menuClassNo }" id="td-${result.menuId }"><div><button type="button" class="arrow-left"><p class="blind">펼치기</p></button>${result.menuName }</div></td>
	                    	</c:if>
	                    	<c:if test="${result.menuChildFlag eq 'N' }">
	                        	<td class="tl no-btn ${result.menuClassNo }"><div>${result.menuName }</div></td>
	                    	</c:if>
	                        <td class="tl">
	                        	<div>
	                        		${result.menuTitle }
		                    		<c:choose>
		                    			<c:when test="${result.menuDepth eq 'dep1'}"><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/${result.menuNameDepth1 }'">미리보기</button></span></c:when>
		                    			<c:when test="${result.menuDepth eq 'dep2'}"><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/${result.menuNameDepth1 }/${result.menuNameDepth2 }'">미리보기</button></span></c:when>
		                    			<c:when test="${result.menuDepth eq 'dep3'}"><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/${result.menuNameDepth1 }/${result.menuNameDepth2 }/${result.menuNameDepth3 }'">미리보기</button></span></c:when>
		                    			<c:when test="${result.menuDepth eq 'dep4'}"><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/${result.menuNameDepth1 }/${result.menuNameDepth2 }/${result.menuNameDepth3 }/${result.menuNameDepth4 }'">미리보기</button></span></c:when>
		                    			<c:when test="${result.menuDepth eq 'dep5'}"><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/${result.menuNameDepth1 }/${result.menuNameDepth2 }/${result.menuNameDepth3 }/${result.menuNameDepth4 }/${result.menuNameDepth5 }'">미리보기</button></span></c:when>
		                    			<c:when test="${result.menuDepth eq 'dep6'}"><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/${result.menuNameDepth1 }/${result.menuNameDepth2 }/${result.menuNameDepth3 }/${result.menuNameDepth4 }/${result.menuNameDepth5 }/${result.menuNameDepth6 }'">미리보기</button></span></c:when>
		                    			<c:otherwise><span class="manage-btn blank-btn"><button type="button" onclick="window.open('about:blank').location.href='/'">미리보기</button></span></c:otherwise>
		                    		</c:choose>
	                    		</div>
	                        </td>
	                        <td><div>${result.menuUseFlag }</div></td>
	                        <td><div>${result.menuNo }</div></td>
	                        <c:if test="${result.menuDepth eq 'dep6'}">
	                        	<td><div><span class="manage-btn add-btn"><button type="button" onclick="javascript:alert('더 이상 하위 메뉴를 등록할 수 없습니다.');">등록</button></span></div></td>
	                        </c:if>
	                        <c:if test="${result.menuDepth ne 'dep6'}">
	                        	<td><div><span class="manage-btn add-btn"><button type="button" onclick="javascript:fn_insert('${result.menuDepth }','${result.menuId }');">등록</button></span></div></td>
	                        </c:if>
	                        <!-- <td><div><span class="manage-btn backup-btn"><button type="button" onclick="javascript:fn_backup('${result.menuId}');">백업</button></span></div></td> -->
                        	<td><div><span class="manage-btn modify-btn"><button type="button" onclick="javascript:fn_update('${result.menuId }');">수정</button></span></div></td>
	                    	<c:if test="${result.menuChildFlag ne 'N' }">
		                        <td><div><span class="manage-btn delete-btn"><button type="button" onclick="javascript:alert('하위 메뉴가 존재하여 삭제가 불가능합니다. \n하위 메뉴 삭제 후 다시 시도해주세요.');">삭제</button></span></div></td>
	                    	</c:if>
	                    	<c:if test="${result.menuChildFlag eq 'N' }">
		                        <td><div><span class="manage-btn delete-btn"><button type="button" onclick="javascript:fn_delete('${result.menuId }');">삭제</button></span></div></td>
	                    	</c:if>
                        	<td><div><span class="manage-btn modify-btn"><button type="button" onclick="window.open('/_admin/site/contentsForm.do?contentsId=${result.contentsId }&command=update')">수정</button></span></div></td>
	                    </tr>
	                </c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
	                    <tr>
	                        <td colspan="8"><div>등록된 메뉴가 없습니다.</div></td>
	                    </tr>
		 			</c:if>
	            </table>
	        </div>
	        <div class="add-btn-box mt20">
	            <button type="button" onclick="javascript:fn_insert('dep0','0');">1차 메뉴 등록</button>
	        </div>
			<form name="frm" id="frm" method="post" action="">
				<input type="hidden" name="menuId" id="menuId" value="0">
				<input type="hidden" name="upperMenuId" id="upperMenuId" value="0">
				<input type="hidden" name="menuDepth" id="menuDepth" value="">
				<input type="hidden" name="command" id="command" value="">
            </form>
	    </div>
	</div>
	
	<script>
		window.onload  = function() {
			setTimeout(function() { 
				$("#td-${searchVO.menuIdDepth1}").find("button").click();
				$("#td-${searchVO.menuIdDepth2}").find("button").click();
				$("#td-${searchVO.menuIdDepth3}").find("button").click();
				$("#td-${searchVO.menuIdDepth4}").find("button").click();
				$("#td-${searchVO.menuIdDepth5}").find("button").click();
			}, 200);
		}
	</script>