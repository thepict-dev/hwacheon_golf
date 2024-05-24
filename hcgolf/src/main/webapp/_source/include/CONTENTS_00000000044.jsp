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
<script>

		function api(){
			var date = new Date(); 
			var year = date.getFullYear(); 
			var month = new String(date.getMonth()+1);
			var day = date.getDate();
			var today = date.getDay();
			var week = new Array('일', '월', '화', '수', '목', '금', '토');
			var todayLabel = week[today];
			var toDate = year+"."+month+"."+day+" "+todayLabel;
			$("#toDay").html(toDate);
			$.ajax({
				type: "get",
				cache: false,
				url: "/_event/getWeather.do",
				dataType : 'text',
				contentType : "application/json; charset=utf-8",
				success: function(data){
					console.log("####################1");
					console.log(data);
					console.log("####################2");

					//날씨 xml
					var weathertxt = data;
					parser=new DOMParser();
					
					//날씨 xml을 파싱
					xmlDoc=parser.parseFromString(weathertxt,"text/xml");
				 
					// 특정 테그를 기준으로 변수에 담는다
					var xml = xmlDoc.getElementsByTagName('body');

					// getElementsByTagName : 태그 호출
					// childNodes : 자식 노드
					// nodeValue : 해당 노드의 값(text)
					var weatherTemp = "";
					var skyType = "";
					for (var i = 0; i < xml[0].getElementsByTagName('item').length; i++){
						var temperature = xml[0].getElementsByTagName('item')[i].getElementsByTagName('fcstValue')[0].childNodes[0].nodeValue;
						if(i == 0){
							weatherTemp = temperature;
						}else if(i == 5){
							skyType = temperature;
						}else if(i == 6 && temperature != 0){
							skyType = 5;
						}
					}
					if(weatherTemp != ""){
						if(skyType == 1){
							$(".ico").addClass("type1");
							$("#weatherType").html("맑음 <span class='tem'>"+weatherTemp+"℃</span>");
						}else if(skyType == 3){
							$(".ico").addClass("type3");
							$("#weatherType").html("구름 <span class='tem'>"+weatherTemp+"℃</span>");
						}else if(skyType == 4){
							$(".ico").addClass("type2");
							$("#weatherType").html("흐림 <span class='tem'>"+weatherTemp+"℃</span>");
						}else if(skyType == 5){
							$(".ico").addClass("type5");
							$("#weatherType").html("비/눈 <span class='tem'>"+weatherTemp+"℃</span>");
						}
					}
				},
				error: function(err){
					alert("통신 오류가 발생하였습니다. 관리자에게 문의해주세요.");
				}
			});

		}
		api();
	</script>