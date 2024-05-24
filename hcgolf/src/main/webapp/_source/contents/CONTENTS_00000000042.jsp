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
              <li><a href="/tour/masusalmon">얼음나라화천 산천어축제</a></li>
              <li><a href="/tour/jjokbae">물의나라화천 쪽배축제</a></li>
              <li class="active"><a href="/tour/tomato">화천 토마토축제</a></li>
              <li><a href="/tour/info">화천 관광정보</a></li>
            </ul>
          </div>
          <div class="tour-bottom">
            <div class="tour-con">
              <img src="/_user/img/tour-img03.jpg" alt="">
              <p class="tour-txt noto">
                스페인의 작은 마을에서 시작된 토마토축제가 화천의 사창리에서도 시작되었는데, 토마토의 브랜드 가치와 수익증대를 위해 열리기 시작했다고 합니다. 축제에 사용할 토마토는 상품가치가 떨어지는 것을 모은 것과 이벤트용으로 모아 50여톤 가까이의 토마토가 사용된다고 합니다. 축제의 하이라이트는 "황금반지를 찾아라"인데 수많은 토마토중 황금반지가 들어있는 토마토를 찾는 것입니다.
              </p>
              <div class="board-btn-wrap noto">
                  <a href="#lnk" class="board-btn bg-blue"><span>자세히보기</span></a>
              </div>
            </div>
          </div>
        </div>