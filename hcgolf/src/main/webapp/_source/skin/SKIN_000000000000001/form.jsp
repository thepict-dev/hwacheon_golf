<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.board.web.BoardBundle"%>			
<%@ page import="egovframework.com.cop.bbs.service.BoardVO"%>			
<%@ page import="egovframework.com.cop.bbs.service.BoardMasterVO"%>		
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>		
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>		
<%												
	BoardBundle bb = new BoardBundle(request);		
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
<%@ page import="java.util.List"%>
<%@ page import="egovframework.golf.service.EventVO"%>
<%@ page import="egovframework.golf.web.GolfBundle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	session.setAttribute("returnUrl", "/bbs/inquiry");

	if(sb.getMemberId() == null){
%>
		<script>
			alert("로그인 후 이용 가능합니다.");
			location.href = "/login";
		</script>
<%
	}
%>


<script>

    function fn_insert(){
		if($("#ntcrNm").val() == null || $("#ntcrNm").val() ==""){
			alert("성함을 입력해주세요.");
			$("#ntcrNm").focus();
			return;
		}
		if($("#phone1").val() == null || $("#phone1").val() ==""){
			alert("연락처를 입력해주세요.");
			$("#phone1").focus();
			return;
		}
		if($("#phone2").val() == null || $("#phone2").val() ==""){
			alert("연락처를 입력해주세요.");
			$("#phone2").focus();
			return;
		}
		if($("#phone3").val() == null || $("#phone3").val() ==""){
			alert("연락처를 입력해주세요.");
			$("#phone3").focus();
			return;
		}
		$("#userCel").val($("#phone1").val()+"-"+$("#phone2").val()+"-"+$("#phone3").val());
		if($("#email").val() == null || $("#email").val() ==""){
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			return;
		}
		if($("#nttCn").val() == null || $("#nttCn").val() ==""){
			alert("내용을 입력해주세요.");
			$("#nttCn").focus();
			return;
		}
        $("#returnUrl").val("<%=mb.getMenuUrl()%>");
        $("#frm").attr("action", "/_board/bbsInsert.do");
        $("#frm").submit();
    }

    function maxLengthCheck(object){
		if(object.value.length > object.maxLength){
			object.value = object.value.slice(0, object.maxLength);
		}    
	}
</script>
    <div class="calendar-wrap">
      <p class="cal-tit" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">화천산천어파크골프장 문의하기</p>
      <span class="cal-sub noto" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">궁금한 내용을 남겨주시면 신속하게 답변드리겠습니다.</span>
      <div class="form-wrap res-show" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <form id="frm" name="frm" method="post" action="" enctype="multipart/form-data">
                <input type="hidden" name="flag" id="flag" value="form">
                <input type="hidden" name="bbsId" value="<%=bb.getBbsId()%>">
                <input type="hidden" name="returnUrl" id="returnUrl">
                <input type="hidden" name="userCel" id="userCel">
                <input type="hidden" name="nttSj" id="nttSj" value="문의하기">
              <fieldset>
                  <legend class="screen-out">문의하기 내용 입력 폼</legend>
                  <div class="form-group noto">
                      <ul class="grid-table bd-t bd-b">
                          <li>
                              <p class="tit">성함</p>
                                <div class="input-box">
                                    <input type="text" class="int type4" name="ntcrNm" id="ntcrNm">
                                </div>
                          </li>
                          <li>
                              <p class="tit">연락처</p>
                              <div class="input-box flex">
                                  <input type="text" class="int type2" id="phone1">
                                  <em class="bar">-</em>
                                  <input type="text" class="int type2" id="phone2">
                                  <em class="bar">-</em>
                                  <input type="text" class="int type2" id="phone3">
                              </div>
                          </li>
                          <li>
                              <p class="tit">문의내용</p>
                              <div class="input-box">
                                  <select class="int type4" name="cateType01">
                                      <option value="inquiry01">예약확인/취소</option>
                                      <option value="inquiry02">오시는길</option>
                                      <option value="inquiry03">부대시설</option>
                                      <option value="inquiry04">홀 관련</option>
                                      <option value="inquiry05">기타</option>
                                  </select>
                              </div>
                          </li>
                          <li>
                              <p class="tit">이메일주소</p>
                                <div class="input-box">
                                    <input type="text" class="int type4" id="email" name="email">
                                </div>
                          </li>
                          <li>
                            <p class="tit">첨부파일</p>
                            <div class="input-box">
                              <div class="file-box flex-center">
                                  <input type="file" id="upload" name="inp-file">
                                  <input disabled="disabled" class="int file-name type4">
                                  <label for="upload">파일찾기</label>
                              </div>
                            </div>
                          </li>
                          <li>
                              <p class="tit">문의내용</p>
                                <div class="input-box">
                                    <textarea class="int" name="nttCn" id="nttCn"></textarea>
                                </div>
                          </li>
                      </ul>
                  </div>
                  <div class="board-btn-wrap noto">
                      <a href="#lnk" class="board-btn bg-blue" onclick="javascript:fn_insert();"><span>문의하기</span></a>
                  </div>
              </fieldset>
          </form>
      </div>
    </div>
  </div>