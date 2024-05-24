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
<div class="flex-box">
          <div class="hole-wrap">
            <div class="hole-top"  data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
              <div class="left">
                <p class="hole-top-tit">화천 산천어 파크골프장에서<br>다양한 홀을 즐겨보세요</p>
                <span class="hole-top-sub noto webshow">아래 지도에서 원하는 홀을 클릭하면<br>해당 홀의 공략법으로 이동합니다</span>
              </div>
              <div class="right">
                <p class="tbl-tit left-bar noto">제원표 <span>Specification Table</span></p>
                <div class="tbl-wrap noto">
                  <table class="tbl tc">
                      <caption class="blind">홀명, PAR, 연장(m) 순으로 안내합니다.</caption>
                      <colgroup>
                        <col style="width: 33.333%">
                        <col style="width: 33.333%">
                        <col style="width: 33.333%">
                      </colgroup>
                      <thead>
                      <tr>
                          <th>홀명</th>
                          <th>PAR</th>
                          <th>연장(m)</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>
                          <td>A1</td>
                          <td>4</td>
                          <td>92</td>
                      </tr>
                      <tr>
                          <td>A2</td>
                          <td>3</td>
                          <td>58</td>
                      </tr>
                      <tr>
                          <td>A3</td>
                          <td>3</td>
                          <td>50</td>
                      </tr>
                      <tr>
                          <td>A4</td>
                          <td>4</td>
                          <td>80</td>
                      </tr>
                      <tr>
                          <td>A5</td>
                          <td>4</td>
                          <td>100</td>
                      </tr>
                      <tr>
                          <td>A6</td>
                          <td>3</td>
                          <td>60</td>
                      </tr>
                      <tr>
                          <td>A7</td>
                          <td>3</td>
                          <td>60</td>
                      </tr>
                      <tr>
                          <td>A8</td>
                          <td>5</td>
                          <td>130</td>
                      </tr>
                      <tr>
                          <td>A9</td>
                          <td>4</td>
                          <td>70</td>
                      </tr>
                      <tr>
                          <td>계</td>
                          <td>33</td>
                          <td>700</td>
                      </tr>
                      </tbody>
                  </table>
                  <table class="tbl tc">
                      <caption class="blind">홀명, PAR, 연장(m) 순으로 안내합니다.</caption>
                      <colgroup>
                        <col style="width: 33.333%">
                        <col style="width: 33.333%">
                        <col style="width: 33.333%">
                      </colgroup>
                      <thead>
                      <tr>
                          <th>홀명</th>
                          <th>PAR</th>
                          <th>연장(m)</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr>
                          <td>B1</td>
                          <td>4</td>
                          <td>85</td>
                      </tr>
                      <tr>
                          <td>B2</td>
                          <td>3</td>
                          <td>50</td>
                      </tr>
                      <tr>
                          <td>B3</td>
                          <td>4</td>
                          <td>90</td>
                      </tr>
                      <tr>
                          <td>B4</td>
                          <td>3</td>
                          <td>55</td>
                      </tr>
                      <tr>
                          <td>B5</td>
                          <td>3</td>
                          <td>58</td>
                      </tr>
                      <tr>
                          <td>B6</td>
                          <td>4</td>
                          <td>85</td>
                      </tr>
                      <tr>
                          <td>B7</td>
                          <td>3</td>
                          <td>55</td>
                      </tr>
                      <tr>
                          <td>B8</td>
                          <td>4</td>
                          <td>88</td>
                      </tr>
                      <tr>
                          <td>B9</td>
                          <td>5</td>
                          <td>150</td>
                      </tr>
                      <tr>
                          <td>계</td>
                          <td>33</td>
                          <td>716</td>
                      </tr>
                      </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div class="hole-view" data-aos="fade-up" data-delay="2000" data-aos-duration="1500">
            <!-- Image Map Generated by http://www.image-map.net/ -->
          <img src="/_user/img/img-map.jpg" usemap="#image-map" data-mob="/_user/img/hole-w.jpg" data-web="/_user/img/img-map.jpg">
          <map name="image-map">
              <area target="" alt="" title="a1" href="/parkgolf/hole/view-a?hole=1" coords="1237,318,37" shape="circle">
              <area target="" alt="" title="a2" href="/parkgolf/hole/view-a?hole=2" coords="947,276,39" shape="circle">
              <area target="" alt="" title="a3" href="/parkgolf/hole/view-a?hole=3" coords="686,330,41" shape="circle">
              <area target="" alt="" title="a4" href="/parkgolf/hole/view-a?hole=4" coords="384,698,39" shape="circle">
              <area target="" alt="" title="a5" href="/parkgolf/hole/view-a?hole=5" coords="99,419,38" shape="circle">
              <area target="" alt="" title="a6" href="/parkgolf/hole/view-a?hole=6" coords="319,628,39" shape="circle">
              <area target="" alt="" title="a7" href="/parkgolf/hole/view-a?hole=7" coords="140,1188,41" shape="circle">
              <area target="" alt="" title="a8" href="/parkgolf/hole/view-a?hole=8" coords="590,1194,40" shape="circle">
              <area target="" alt="" title="a9" href="/parkgolf/hole/view-a?hole=9" coords="435,1040,40" shape="circle">
              <area target="" alt="" title="b1" href="/parkgolf/hole/view-b?hole=1" coords="750,1061,37" shape="circle">
              <area target="" alt="" title="b2" href="/parkgolf/hole/view-b?hole=2" coords="856,892,38" shape="circle">
              <area target="" alt="" title="b3" href="/parkgolf/hole/view-b?hole=3" coords="457,640,36" shape="circle">
              <area target="" alt="" title="b4" href="/parkgolf/hole/view-b?hole=4" coords="671,445,39" shape="circle">
              <area target="" alt="" title="b5" href="/parkgolf/hole/view-b?hole=5" coords="855,409,38" shape="circle">
              <area target="" alt="" title="b6" href="/parkgolf/hole/view-b?hole=6" coords="1175,418,38" shape="circle">
              <area target="" alt="" title="b7" href="/parkgolf/hole/view-b?hole=7" coords="1470,386,36" shape="circle">
              <area target="" alt="" title="b8" href="/parkgolf/hole/view-b?hole=8" coords="1149,518,37" shape="circle">
              <area target="" alt="" title="b9" href="/parkgolf/hole/view-b?hole=9" coords="1691,549,37" shape="circle">
          </map>
          <script>
            $(function(){
            	$('img[usemap]').rwdImageMaps();
            	$("#img").width("100%");
            });
				  </script>
          </div>
          <div class="board-btn-wrap noto webhide">
              <a href="/parkgolf/hole/view-a?hole=1" class="board-btn bg-blue"><span>코스 보러가기</span></a>
          </div>
        </div>
      </div>