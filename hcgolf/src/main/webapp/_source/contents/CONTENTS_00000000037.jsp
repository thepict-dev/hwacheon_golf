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
	String holeNo = request.getParameter("hole") == null ? "1" : request.getParameter("hole");
%>
<div class="hole-wrap">
          <select class="int webhide" id="holeSel">
            <option value="a" selected>A홀</option>
            <option value="b">B홀</option>
          </select>
          <ul class="tour-tab col2 webshow noto">
              <li class="active"><a href="/parkgolf/hole/view-a">A홀</a></li>
              <li><a href="/parkgolf/hole/view-b">B홀</a></li>
          </ul>
		  <ul class="hole-tab hole-a">
            <li id="a1"><a href="#lnk">1</a></li>
            <li id="a2"><a href="#lnk">2</a></li>
            <li id="a3"><a href="#lnk">3</a></li>
            <li id="a4"><a href="#lnk">4</a></li>
            <li id="a5"><a href="#lnk">5</a></li>
            <li id="a6"><a href="#lnk">6</a></li>
            <li id="a7"><a href="#lnk">7</a></li>
            <li id="a8"><a href="#lnk">8</a></li>
            <li id="a9"><a href="#lnk">9</a></li>
          </ul>
        </div>
        <div class="con-wrap">
          <div class="hole-con" id="con1">
            <div class="top">
              <p class="hole-info-tit">A1 Hole</p>
              <span class="hole-info">PAR4 / 92m</span>
              <p class="hole-sub noto">아름다운 북한강 수변과 함께하는 코스!<br>오른쪽 러프가 많아서 정면에서 왼쪽으로 공략 필요!</p>
              <img src="/_user/img/hole-a1.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con2">
            <div class="top">
              <p class="hole-info-tit">A2 Hole</p>
              <span class="hole-info">PAR3 / 58m</span>
              <p class="hole-sub noto">정면에 보이는 오른쪽 언덕을 공략!<br>왼쪽으로 공략 시 화단으로 공이 들어갈 수 있음!</p>
              <img src="/_user/img/hole-a2.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con3">
            <div class="top">
              <p class="hole-info-tit">A3 Hole</p>
              <span class="hole-info">PAR3 / 50m</span>
              <p class="hole-sub noto">티박스 중앙 앞쪽에 공을 위치 후 나무사이로 타격!<br>오른쪽 경사 주의!</p>
              <img src="/_user/img/hole-a3.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con4">
            <div class="top">
              <p class="hole-info-tit">A4 Hole</p>
              <span class="hole-info">PAR4 / 80m</span>
              <p class="hole-sub noto">초보자는 메타세콰이어 나무 중앙으로<br>고수는 2번, 3번 나무 사이를 공략!</p>
              <img src="/_user/img/hole-a4.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con5">
            <div class="top">
              <p class="hole-info-tit">A5 Hole</p>
              <span class="hole-info">PAR4 / 100m</span>
              <p class="hole-sub noto">사랑나무와 아름다운 꽃 그리고 호수가 어우러진 홀!<br>북한강변을 바라보고 호쾌한 스윙을 할 수 있는 곳.</p>
              <img src="/_user/img/hole-a5.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con6">
            <div class="top">
              <p class="hole-info-tit">A6 Hole</p>
              <span class="hole-info">PAR3 / 60m</span>
              <p class="hole-sub noto">오른쪽 해저드 주의.<br>정면 홀컵으로 왼쪽으로 공략!</p>
              <img src="/_user/img/hole-a6.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con7">
            <div class="top">
              <p class="hole-info-tit">A7 Hole</p>
              <span class="hole-info">PAR3 / 60m</span>
              <p class="hole-sub noto">공을 반듯이 띄워서 공략!<br>깔아서 치면 공이 오른쪽으로 향함!</p>
              <img src="/_user/img/hole-a7.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con8">
            <div class="top">
              <p class="hole-info-tit">A8 Hole</p>
              <span class="hole-info">PAR5 / 130m</span>
              <p class="hole-sub noto">두 개의 언덕을 넘어 나무사이를 통과해야하는 홀!<br>오른쪽 보다는 왼쪽으로 공략!</p>
              <img src="/_user/img/hole-a8.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con9">
            <div class="top">
              <p class="hole-info-tit">A9 Hole</p>
              <span class="hole-info">PAR4 / 70m</span>
              <p class="hole-sub noto">정면기준으로 왼쪽 첫 번째 나무 공략!<br>오른쪽으로 치면 밑으로 떨어지는 홀</p>
              <img src="/_user/img/hole-a9.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
        </div>
      </div>


<script>
	$(document).ready(function(){
		$("#holeSel").change(function(){
			location.href = "/parkgolf/hole/view-"+$("#holeSel").val();
		})
	})
	var vHoleNo = "<%=holeNo%>";
	for(var i=1; i<=9; i++) {
		if(i==Number(vHoleNo)){
			$('#a'+i).addClass('active');
			$('#con'+i).attr("style","display: flex;");
		}else{
			$('#a'+i).removeClass('active');
			$('#con'+i).attr("style","display: none;");
		}
	}
</script>