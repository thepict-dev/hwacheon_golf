$(function(){
  //main visual slide
  var mainSlide = new Swiper('.main-slide', {
      speed: 1000,
      touchReleaseOnEdges: true,
      // autoplay: {
      //       delay: 3500,
      //       disableOnInteraction:false
      //   },
      pagination: {
        el: '.main-slide .swiper-pagination',
        type: 'bullets',
        clickable: 'true',
      },
  });

});
