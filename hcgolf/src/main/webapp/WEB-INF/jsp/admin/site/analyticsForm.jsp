<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


	<script>
		function fn_submit(v_command){

			if ($("#analyticsName").val() == "") {
				alert("애널리틱스 이름을 입력해주세요.");
				$("#analyticsName").focus();
				return;
			}
			
			if(v_command == "insert"){
				if (confirm('등록하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/analyticsInsert.do");
					$("#frm").submit();
				}
			}else{
				if (confirm('수정하시겠습니까?')) {
					$("#frm").attr("action", "/_admin/site/analyticsUpdate.do");
					$("#frm").submit();
				}
			}
		}
		function fn_cancel(){
			if(confirm("작성중인 내용은 저장되지 않습니다. 그래도 돌아가시겠습니까?")){
				$("#frm").attr("action", "/_admin/site/analyticsList.do");
				$("#frm").submit();
			}
		}
		function fn_list(){
			$("#frm").attr("action", "/_admin/site/analyticsList.do");
			$("#frm").submit();
		}
	</script>
		
	<div class="container-wrap">
		<div class="breadcrumbs">
			<ul>
				<li class="webshow"><a href="#lnk"><img src="/_admin/img/home.png"></a></li>
				<li><a href="#lnk">사이트관리</a></li>
				<li><a href="#lnk">애널리틱스 관리</a></li>
			</ul>
		</div>
		<div class="container scroll">
			<h2 class="con-tit">애널리틱스 관리</h2>
			<div class="section-wrap res">
				<div class="section">
				<form action="" id="frm" name="frm" method="post">
					<input type="hidden" name="pageIndex" value="${searchVO.pageIndex }" >
		        	<c:if test="${searchVO.command eq 'update' }">
	            		<input type="hidden" name="analyticsId" value="${analyticsVO.analyticsId }" >
	            	</c:if>
					<fieldset>
						<p class="section-tit">애널리틱스 정보</p>
						<div class="tbl-wrap new-tbl res">
							<span><em class="essential">*</em> 표시는 필수 입력사항입니다.</span>
							<table class="tbl03">
								<caption class="blind">애널리틱스 관리 등록 테이블</caption>
								<colgroup>
									<col width="20%" class="change1">
                                    <col width="80%" class="change2">
								</colgroup>
								<tbody>
								<tr>
									<th class="tl">이름 <em class="essential">*</em></th>
									<td class="tl"><input type="text" id="analyticsName" name="analyticsName" title="레이아웃 이름 입력" value="${analyticsVO.analyticsName }"></td>
								</tr>
								<tr>
									<th class="tl">Head</th>
									<td class="text-box info-ir">
										<textarea class="txt bg-line" id="analyticsHead" name="analyticsHead" title="html 코드 입력" style="border-color: #ccc; min-height: 500px;"><c:out value="${analyticsVO.analyticsHead }"/></textarea>
									</td>
								</tr>
								<tr>
									<th class="tl">Body</th>
									<td class="text-box info-ir">
										<textarea class="txt bg-line" id="analyticsBody" name="analyticsBody" title="html 코드 입력" style="border-color: #ccc; min-height: 500px;"><c:out value="${analyticsVO.analyticsBody }"/></textarea>
									</td>
								</tr>
								</tbody>
							</table>
						</div>
					</fieldset>
				</form>
				</div>
			</div>
            <script>
                var txtLenght = $('.txt.bg-line').length;

                for(i=0;i<=txtLenght;i++){
                    var code = $('.txt.bg-line')[i];
                    var editor = CodeMirror.fromTextArea(code, {
                        mode: "text/html",
                        lineNumbers: true,
                        lineWrapping: true,
                        matchBrackets: true,
                        styleActiveLine: true,
                        matchTags: {bothTags: true},
                        theme: "eclipse",
                        val: code.value
                    });
                }
            </script>
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