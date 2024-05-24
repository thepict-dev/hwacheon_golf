$(function(){
  var imgSlide = new Swiper('.imgSlide', {
      speed: 1000,
      touchReleaseOnEdges: true,
      autoHeight : true,
      navigation: {
           nextEl: '.slide-btn .swiper-button-next',
           prevEl: '.slide-btn .swiper-button-prev',
      },
  });
  //tbl ev
    var appendEv = true;
    function tblEV() {
        $('.tbl-wrap .tbl03 tbody>tr').each(function () {
            var Day = '<p>'+$(this).find('.col.day').text()+'</p>',
                DayType2 = '<div class="list-bottom"><p>'+$(this).find('.col.day').text()+'</p><i class="ico-addfile">첨부파일</i></div>'
                Noti = $(this).find('.col.noti').html(),
                $postTit = $(this).find('.post-tit');

            if($(this).find('td').hasClass('file')){
              $postTit.append(DayType2);
              $postTit.prepend(Noti);
            } else if (!$(this).find('td').hasClass('file')) {
              $postTit.append(Day);
              $postTit.prepend(Noti);
            }
        });
    }
    if(window.innerWidth < 768){
        tblEV();
        appendEv = false;
    }
    $(window).resize(function () {
        if(window.innerWidth < 768 && appendEv){
            tblEV();
            appendEv = false;
        }
        if(window.innerWidth >= 768 && appendEv == false){
            $('.tbl-wrap .col.post-tit .list-bottom, .tbl-wrap .col.post-tit em').remove();
            appendEv = true;
        }
    });

    //파일명 삽입
   $(document).on('change','.file-box #upload',function(){
       if(window.FileReader){
           var filename=$(this)[0].files[0].name;
       } else {
           var filename=$(this).val().split('/').pop().split('\\').pop();
       }

       $(this).siblings('input.file-name').val(filename);
   });

   //tab
   var $tabNav = $('.tour-tab > li'),
       tourBottom = $('.tour-bottom'),
       tabCon = $('.tour-bottom .tour-con');

  $tabNav.on('click', function(){
    var tabNum = $(this).index();

    $(this).addClass('active').siblings().removeClass('active');
    tabCon.eq(tabNum).show().siblings().hide();
    $('.tour-bottom').attr('style','');
  });
   //breadcrumb
   $('.breadcrumb > li').on('click', function(){
     if(!$(this).hasClass('active')){
       $(this).addClass('active').siblings().removeClass('active');
       $('.breadcrumb > li > ul').stop().slideUp();
       $(this).find('ul').stop().slideDown();
     } else if ($(this).hasClass('active') && $(this).find('ul').css('display') == 'block') {
       $(this).removeClass('active');
       $(this).find('ul').stop().slideUp();
     }
   });

   function imgChange() {
       // subVisual
       var $holeImg = $('.hole-view > img'),
           pcVisual = $holeImg.attr('data-web'),
           mobVisual = $holeImg.attr('data-mob');

       if (window.innerWidth <= 1023)
           $holeImg.attr('src', mobVisual);
       else
           $holeImg.attr('src', pcVisual);

       $(window).resize(function () {
           if (window.innerWidth <= 1023)
               $holeImg.attr('src', mobVisual);
           else
               $holeImg.attr('src', pcVisual);
       });
   }
   imgChange();

   //코스소개
   var $holeTab = $('.hole-tab > li'),
       $holeCon = $('.con-wrap .hole-con');

   $holeTab.on('click', function(){
     var thisNum = $(this).index();

     $(this).addClass('active').siblings().removeClass('active');
     if(window.innerWidth > 1023)
       $holeCon.eq(thisNum).css('display','flex').siblings().hide();
     else
      $holeCon.eq(thisNum).show().siblings().hide();

   });
});
