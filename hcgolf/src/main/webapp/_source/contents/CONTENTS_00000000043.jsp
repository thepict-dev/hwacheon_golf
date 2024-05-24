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
              <li><a href="/tour/tomato">화천 토마토축제</a></li>
              <li class="active"><a href="/tour/info">화천 관광정보</a></li>
            </ul>
          </div>
          <div class="tour-bottom">
            <div class="tour-con">
              <div class="swiper-container imgSlide">
                <div class="slide-btn">
                  <div class="swiper-button-prev"></div>
                  <div class="swiper-button-next"></div>
                </div>
                <div class="swiper-wrapper">
                  <div class="swiper-slide">
                    <em class="slide-tit noto">평화의 댐</em>
                    <img src="/_user/img/tour-img04.jpg" alt="">
                    <p class="tour-txt noto">
                      높이 125m, 길이 601m, 최대 저수량 26억 3천만톤의 댐으로 지금은 평화를 상징하는 댐으로 널리 알려져 관광객들이 많이 찾는 곳입니다.<br>물빛누리호를 타고 평화의댐으로 가는 푸른 물길은 편안함을 안겨 주어 지친 심신을 달래고 치유해줄 것입니다. 평화의댐 뿐만아니라 세계평화의 종, 종공원, 국제평화아트파크, 평화의댐 물문화관 등의 시설이 있습니다. 그 유명한 비수구미 산채비빔밥을 같이 드시면 금상첨화입니다.
                    </p>
                  </div>
                  <div class="swiper-slide">
                    <em class="slide-tit noto">붕어섬</em>
                    <img src="/_user/img/tour-img05.jpg" alt="">
                    <p class="tour-txt noto">
                      붕어섬은 북한강 상류인 화천강 한가운데에 있는 섬으로 춘천댐 담수로 인하여 생기게 된 섬입니다. 특히 이 곳에는 화천에어링이라고 하여 카약체험, 수상자전거체험, 하늘가르기, 자전거 체험 등 다양한 다이나믹한 체험프로그램을 저렴한 가격에 즐기실 수 있으며 체험시 제공되는 화천사랑상품권으로 화천의 시장과 마트 등 여러곳에서 사용함으로써 1석2조의 효과를 보실 수 있습니다!
                    </p>
                    <div class="board-btn-wrap noto">
                        <a href="https://tour.ihc.go.kr/hb/portal/sub01_02?mode=readForm&curPage=2&articleSeq=600146&searchCategory=&searchType=&searchWord=&ltype=&cont=look01" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
                    </div>
                  </div>
                  <div class="swiper-slide">
                    <em class="slide-tit noto">동구래마을</em>
                    <img src="/_user/img/tour-img06.jpg" alt="">
                    <p class="tour-txt noto">
                      동구래마을의 주인은 꽃입니다. 단순히 넓은 터에 꽃을 잔뜩 심어놓은 그렇고 그런 꽃단지가 아닙니다. 그래서 동구래마을은 천천히 하나 하나 음미하듯 봐야합니다. 촌장님이 주시는 꽃차에는 특별함이 있습니다. 100가지 꽃을 발효시켜 만든다는 꽃차에는 자연이 담겨 있습니다. 자연이 내 몸으로 들어오는 그 순간 내 마음속의 화기가 사라지는 기분입니다. 일상에 여있던 스트레스가 사라지는 순간 입니다. 도예공방이 같이 운영되며 가을에는 들꽃마당축제를 진행합니다.
                    </p>
                    <div class="board-btn-wrap noto">
                        <a href="https://tour.ihc.go.kr/hb/portal/sub01_05?mode=readForm&curPage=&articleSeq=600193&searchCategory=&searchType=&searchWord=&ltype=&cont=look04" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
                    </div>
                  </div>
                  <div class="swiper-slide">
                    <em class="slide-tit noto">산타우체국 대한민국본점</em>
                    <img src="/_user/img/tour-img07.jpg" alt="">
                    <p class="tour-txt noto">
                      <산타클로스의 고장> 핀란드 로바니에미시 산타 우체국의 대한민국 본점입니다. 이 곳에서는 산타 할아버지에게 연중 356일 언제나 편지를보낼 수 있습니다. 크리스마스 시즌에는 핀란드 산타할아 버지의 답장을 받을 수 있어 아이들에게 특별한 추억을 만들어 줄 수 있습니다. 또 크리스마트 쿠키 만들기, 공예품 만들기 등의 체험프로 그램도 상시 운영되고, 핀란드 산타우체국에서 온 인형, 엽서 등 기념품 구입도 가능합니다
                    </p>
                    <div class="board-btn-wrap noto">
                        <a href="https://hwacheonsanta.modoo.at" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
                    </div>
                  </div>
                  <div class="swiper-slide">
                    <em class="slide-tit noto">칠성전망대</em>
                    <img src="/_user/img/tour-img08.jpg" alt="">
                    <p class="tour-txt noto">
                      칠성전망대에서는 대한민국에서는 유일하게 북으로 흘렀다가 평화의 댐으로 흐르는 금성천을 볼 수 있으며, 북한의 아름다운 산과 들의 풍광을 구경할 수 있고, DMZ내 야생동물을 관찰할 수 있다.<br>칠성전망대는 관광객의 편의를 위한 시설을 갖추었 으며, 전망대에 방문하려면 당일 신청하여 당일 관람할 수 있다.
                    </p>
                    <div class="board-btn-wrap noto">
                        <a href="https://tour.ihc.go.kr/hb/portal/sub01_03" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
                    </div>
                  </div>
                  <div class="swiper-slide">
                    <em class="slide-tit noto">화천조경철천문대</em>
                    <img src="/_user/img/tour-img09.jpg" alt="">
                    <p class="tour-txt noto">
                      조경철천문대가 위치해있는 해발 1,010m 광덕산 정상지역은 산세가 웅장하고 덕을 품는 산이라는 명성에 걸맞게 우리나라에서 유일하게 은하수 촬영이 가능한 무공해 청정지역이며, 우리 천문대는 고즈넉한 풍경 아래 우주의 신비한 천체와 밤하늘의 아름다움을 만끽할 수 있는 과학문화공간으로 조성되었습니다.
                    </p>
                    <div class="board-btn-wrap noto">
                        <a href="http://www.apollostar.kr" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>