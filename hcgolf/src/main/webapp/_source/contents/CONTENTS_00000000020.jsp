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
<div class="direction-wrap">
          <div class="dir-top" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <p class="col-green">화천 산천어 파크골프장은<br>화천군 하남면에 위치해 있습니다.</p>
            <span class="this-address noto">강원도 화천군 하남면 춘화로 3061-17</span>
          </div>
          <div class="dir-map" data-aos="fade-up" data-delay="2000" data-aos-duration="1500" id="map">

          </div>
        </div>
      </div>

	<!--운영<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6f8098b0177751d61041ee041a87403e"></script>-->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=13ffd9b7d5d69901fb96703577c15ad3"></script>
	<script>
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(38.0759765, 127.688072), //지도의 중심좌표.
			level: 2 //지도의 레벨(확대, 축kli소 정도)
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		var marker = new kakao.maps.Marker({ position :  new kakao.maps.LatLng(38.0759765, 127.688072) });

		marker.setMap(map);
		map.setZoomable(false);	//확대축소 기능 OFF

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	</script>