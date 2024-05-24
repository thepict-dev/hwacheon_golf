<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="egovframework.breeze.menu.web.MenuBundle"%>			
<%@ page import="egovframework.breeze.site.service.MenuVO"%>			
<%@ page import="egovframework.breeze.member.web.SessionBundle"%>		
<%@ page import="egovframework.breeze.member.service.MemberVO"%>		
<%@ page import="egovframework.breeze.code.web.CodeBundle"%>			
<%@ page import="egovframework.breeze.code.service.CodeVO"%>			
<%												
	MenuBundle mb = new MenuBundle(request);		
	SessionBundle sb = new SessionBundle(request);	
	CodeBundle cb = new CodeBundle(request);		
%>												
<%
	String type = request.getParameter("type") == null ? "" : request.getParameter("type");
%>
<script>
    $(function(){
      $('#infoSelect').on('change', function () {
          var url = $(this).val();
          if (url) {
              window.location = url;
          }
          return false;
      });
    });
</script>

<div class="tour-wrap">
          <div class="tour-top">
            <select class="int webhide" id="infoSelect">
              <option value="/tour/masusalmon?type=1" <%if(type.equals("") || type.equals("1")){%>selected<%}%>>얼음나라화천 산천어축제</option>
              <option value="/tour/jjokbae?type=2" <%if(type.equals("2")){%>selected<%}%>>물의나라화천 쪽배축제</option>
              <option value="/tour/tomato?type=3" <%if(type.equals("3")){%>selected<%}%>>화천토마토축제</option>
              <option value="/tour/info?type=4" <%if(type.equals("4")){%>selected<%}%>>화천 관광정보</option>
            </select>
            <ul class="tour-tab webshow noto">
              <li class="active"><a href="/tour/masusalmon">얼음나라화천 산천어축제</a></li>
              <li><a href="/tour/jjokbae">물의나라화천 쪽배축제</a></li>
              <li><a href="/tour/tomato">화천 토마토축제</a></li>
              <li><a href="/tour/info">화천 관광정보</a></li>
            </ul>
          </div>
          <div class="tour-bottom">
            <div class="tour-con">
              <img src="/_user/img/tour-img01.jpg" alt="">
              <p class="tour-txt noto">
                산천어와 수달이 사는 청정지역으로 소문난 화천에서 매년 1월 온 세상이 눈과 얼음으로 뒤덮이는 한겨울, "얼지 않은 인정, 녹지 않는 추억"을 주제로 축제가 시작됩니다. 세계 4대겨울축제, 대한민국 대표 축제, 대한민국 글로벌육성축제로 매년 100만 명 이상의 관광객이 찾고 있습니다. 산천어 얼음낚시, 산천어 맨손잡기, 산천어 수상낚시, 얼음썰매, 눈썰매, 얼음축구 등의 체험프로그램과 선등거리, 세계최대 실내빙등 관장, 산타 우체국, 창작썰메콘테스트 등 약 30여종의 다양한 페험프로그램과 볼거리가 가늑한 겨울철 이색테마 축제입니다.
              </p>
              <div class="board-btn-wrap noto">
                  <a href="#lnk" class="board-btn bg-blue"><span>자세히보기</span></a>
              </div>
            </div>
          </div>
        </div>