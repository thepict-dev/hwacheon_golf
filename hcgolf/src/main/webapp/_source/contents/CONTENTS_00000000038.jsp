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
            <option value="a">A홀</option>
            <option value="b" selected>B홀</option>
          </select>
          <ul class="tour-tab col2 webshow noto">
              <li><a href="/parkgolf/hole/view-a">A홀</a></li>
              <li class="active"><a href="/parkgolf/hole/view-b">B홀</a></li>
          </ul>
          <ul class="hole-tab hole-b">
            <li id="b1"><a href="#lnk">1</a></li>
            <li id="b2"><a href="#lnk">2</a></li>
            <li id="b3"><a href="#lnk">3</a></li>
            <li id="b4"><a href="#lnk">4</a></li>
            <li id="b5"><a href="#lnk">5</a></li>
            <li id="b6"><a href="#lnk">6</a></li>
            <li id="b7"><a href="#lnk">7</a></li>
            <li id="b8"><a href="#lnk">8</a></li>
            <li id="b9"><a href="#lnk">9</a></li>
          </ul>
        </div>
        <div class="con-wrap hole-b">
          <div class="hole-con" id="con1">
            <div class="top">
              <p class="hole-info-tit">B1 Hole</p>
              <span class="hole-info">PAR4 / 92m</span>
              <p class="hole-sub noto">아름다운 가로수를 통과해야 하는 시그니쳐 홀.<br>공을 정확하게 맞춰야 하는 고난이도 홀!</p>
              <img src="/_user/img/hole-b1.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con2">
            <div class="top">
              <p class="hole-info-tit">B2 Hole</p>
              <span class="hole-info">PAR3 / 58m</span>
              <p class="hole-sub noto">정면을 주시하여 정학하게 티샷해야 하는 홀이며<br>홀컵 후면공간이 적음!</p>
              <img src="/_user/img/hole-b2.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con3">
            <div class="top">
              <p class="hole-info-tit">B3 Hole</p>
              <span class="hole-info">PAR3 / 50m</span>
              <p class="hole-sub noto">잔디가 좋아서 풀스윙이 가능한 홀.<br>우측 경사가 있어서 정면으로 보내야 하는 홀!</p>
              <img src="/_user/img/hole-b3.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con4">
            <div class="top">
              <p class="hole-info-tit">B4 Hole</p>
              <span class="hole-info">PAR4 / 80m</span>
              <p class="hole-sub noto">비교적 쉬운 서비스 홀!<br>내리막 홀이어서 짧게 쳐야 하는 홀</p>
              <img src="/_user/img/hole-b4.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con5">
            <div class="top">
              <p class="hole-info-tit">B5 Hole</p>
              <span class="hole-info">PAR4 / 100m</span>
              <p class="hole-sub noto">초보자도 쉽게 파를<br>할 수 있는 서비스 홀!</p>
              <img src="/_user/img/hole-b5.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con6">
            <div class="top">
              <p class="hole-info-tit">B6 Hole</p>
              <span class="hole-info">PAR3 / 60m</span>
              <p class="hole-sub noto">곳곳에 장애물이 많아서<br>중앙에 있는 나무 우측으로 공략!</p>
              <img src="/_user/img/hole-b6.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con7">
            <div class="top">
              <p class="hole-info-tit">B7 Hole</p>
              <span class="hole-info">PAR3 / 60m</span>
              <p class="hole-sub noto">정면을 주시하여 정확한 퍼팅 필요!<br>주변 나무를 피해서 정교한 티샷요구!</p>
              <img src="/_user/img/hole-b7.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con8">
            <div class="top">
              <p class="hole-info-tit">B8 Hole</p>
              <span class="hole-info">PAR5 / 130m</span>
              <p class="hole-sub noto">짧은 퍼팅은 좌측으로 OB가 남.<br>정면기준으로 우측 경사면 공략!</p>
              <img src="/_user/img/hole-b8.png" alt="">
            </div>
            <div class="bot">
              <img src="/_user/img/hole-a-view01.jpg" alt="">
            </div>
          </div>
          <div class="hole-con" id="con9">
            <div class="top">
              <p class="hole-info-tit">B9 Hole</p>
              <span class="hole-info">PAR4 / 70m</span>
              <p class="hole-sub noto">호쾌함과 정교함을 요구하는<br>스트레스 풀어주는 마지막 홀!</p>
              <img src="/_user/img/hole-b9.png" alt="">
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
			$('#b'+i).addClass('active');
			$('#con'+i).attr("style","display: flex;");
		}else{
			$('#b'+i).removeClass('active');
			$('#con'+i).attr("style","display: none;");
		}
	}
</script>