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
	String class1 = "";
	String class2 = "";
	String class3 = "";
	String class4 = "";

	if(mb.getMenuName().equals("masusalmon")){ class1 = "active";
	}else if(mb.getMenuName().equals("jjokbae")){ class2 = "active";
	}else if(mb.getMenuName().equals("tomato")){ class3 = "active";
	}else if(mb.getMenuName().equals("info")){ class4 = "active";
	}
%>
<div class="tour-wrap">
          <div class="tour-top">
            <select class="int webhide">
              <option value="0">얼음나라화천 산천어축제</option>
              <option value="1">물의나라화천 쪽배축제</option>
              <option value="2">화천토마토축제</option>
              <option value="3">화천 관광정보</option>
            </select>
            <ul class="tour-tab webshow noto">
              <li class="<%=class1%>"><a href="#lnk">얼음나라화천 산천어축제</a></li>
              <li class="<%=class2%>"><a href="#lnk">물의나라화천 쪽배축제</a></li>
              <li class="<%=class3%>"><a href="#lnk">화천 토마토축제</a></li>
              <li class="<%=class4%>"><a href="#lnk" id="class04">화천 관광정보</a></li>
            </ul>
          </div>
          <div class="tour-bottom">
            <div class="tour-con">
              <img src="/_user/img/tour-img01.jpg" alt="">
              <p class="tour-txt noto">
                산천어와 수달이 사는 청정지역으로 소문난 화천에서 매년 1월 온 세상이 눈과 얼음으로 뒤덮이는 한겨울, "얼지 않은 인정, 녹지 않는 추억"을 주제로 축제가 시작됩니다. 세계 4대겨울축제, 대한민국 대표 축제, 대한민국 글로벌육성축제로 매년 100만 명 이상의 관광객이 찾고 있습니다. 산천어 얼음낚시, 산천어 맨손잡기, 산천어 수상낚시, 얼음썰매, 눈썰매, 얼음축구 등의 체험프로그램과 선등거리, 세계최대 실내빙등 관장, 산타 우체국, 창작썰메콘테스트 등 약 30여종의 다양한 페험프로그램과 볼거리가 가늑한 겨울철 이색테마 축제입니다.
              </p>
              <div class="board-btn-wrap noto">
                  <a href="https://www.narafestival.com/01_icenara" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
              </div>
            </div>
            <div class="tour-con">
              <img src="/_user/img/tour-img02.jpg" alt="">
              <p class="tour-txt noto">
                물 좋은 화천에 오면 모든 일이 술술 잘 풀린다는 의미를 가진 수리 화천이라는 슬로건 아래 다양한 여름체험 프로그램으로 이루어졌는데 이중에서는 수상 자전거, 카약, 야외 물놀이장, 슬라럼체험장, 나뭇잎·조각배 만들기, 강변 물놀이장, 하늘가르기 등 체험행사도 열립니다. 특히 쪽배축제의 백미인 미니창작쪽배콘테스트는 기발한 상상력의 배를 만들어 참가하여 성황을 이룹니다.
              </p>
              <div class="board-btn-wrap noto">
                  <a href="http://www.narafestival.com/02_water" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
              </div>
            </div>
            <div class="tour-con">
              <img src="/_user/img/tour-img03.jpg" alt="">
              <p class="tour-txt noto">
                스페인의 작은 마을에서 시작된 토마토축제가 화천의 사창리에서도 시작되었는데, 토마토의 브랜드 가치와 수익증대를 위해 열리기 시작했다고 합니다. 축제에 사용할 토마토는 상품가치가 떨어지는 것을 모은 것과 이벤트용으로 모아 50여톤 가까이의 토마토가 사용된다고 합니다. 축제의 하이라이트는 "황금반지를 찾아라"인데 수많은 토마토중 황금반지가 들어있는 토마토를 찾는 것입니다.
              </p>
              <div class="board-btn-wrap noto">
                  <a href="http://www.tomatofestival.co.kr" target="_blank" title="새창열림" class="board-btn bg-blue"><span>자세히보기</span></a>
              </div>
            </div>
			
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
      </div>
	  
<script>
	var vClass4 = "<%=class4%>";
	if(vClass4 == "active"){
		console.log("#######################");
		$("#class04").trigger("click");
	}
</script>