$(function(){

  AOS.init();

  //header

  var $header = $('.header'),
      $gnbBtn = $('.gnb-btn'),
      $gnbWrap = $('.gnb-wrap'),
      $gnbLi = $('.gnb-wrap > ul > li'),
      $gnbA = $('.gnb-wrap > ul > li > a'),
      logoWhite = '/_user/img/logo.svg',
      logoColor = '/_user/img/logo-color.svg';

gnbFn();
function gnbFn(){
  if(window.innerWidth < 1024) {
    $gnbLi.off('mouseenter');
    $gnbWrap.off('mouseleave').attr('style','');

    $gnbBtn.on('click', function(){
      if(!$(this).hasClass('on')){
        $(this).addClass('on');
        $header.find('.logo img').attr('src',logoColor);
        $header.addClass('on');
        $gnbWrap.stop().slideDown();
        $('body').css('overflow','hidden');
      } else if ($(this).hasClass('on') && $gnbWrap.css('display') == 'block'){
        $(this).removeClass('on');
        $header.find('.logo img').attr('src',logoWhite);
        $header.removeClass('on');
        $gnbWrap.stop().slideUp();
        $('body').css('overflow','visible');

        $('.gnb-wrap > ul > li').removeClass('active');
        $('.gnb-wrap .dep2').hide();
      }
    });

    $gnbA.on('click', function(){
      var $thisLi = $(this).parents('li');

      if(!$thisLi.hasClass('active')){
        $thisLi.addClass('active').siblings().removeClass('active');
        $('.gnb-wrap .dep2').hide();
        $thisLi.find('.dep2').show();
      } else if ($thisLi.hasClass('active') && $thisLi.find('.dep2').css('display') == 'block') {
        $thisLi.removeClass('active');
        $thisLi.find('.dep2').hide();
      }
    });
  }
  if (window.innerWidth >= 1024){
    $gnbA.off('click');
    $gnbBtn.off('click');

    $gnbLi.on('mouseenter', function(){
      if(!$(this).hasClass('active')){
        $header.addClass('on');
        $header.find('.logo img').attr('src',logoColor);
        $('.gnb-wrap .dep2').show();
        $(this).addClass('active').siblings().removeClass('active');
      }
    });
    $gnbWrap.on('mouseleave', function(){
      $header.removeClass('on');
      $header.find('.logo img').attr('src',logoWhite);
      $('.gnb-wrap .dep2').hide();
      $('.gnb-wrap > ul > li').removeClass('active');
    });
  }
}
function gnbInt() {
  $gnbLi.removeClass('active');
  $gnbA.removeClass('active');
  $gnbWrap.attr('style','');
  $('body').attr('style','');
  $('.gnb-wrap .dep2').attr('style','');
}

  $(window).resize(function(){
    gnbFn();
  });
});
