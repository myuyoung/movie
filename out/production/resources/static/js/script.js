$(function(){
    //.menu의 다음 형제 객체 숨김
    $(".menu").next().hide();
    //주메뉴에 마우스 오버하면 그 메뉴의 서브메뉴 나타남
    $("nav > ul > li").hover(function(){
        if($(this).index() != 4){
            $(".sub").hide();
            $(this).find(".sub").show();
            $(".sub-bg").show();
        }else{
            $(".sub").hide();
            $(".sub-bg").hide();
        }
    });
    //header영역에서 마우스 아웃하면 서브메뉴와 서브메뉴 배경 사라짐
    $("header").mouseleave(function(){
        $(".sub").hide();
        $(".sub-bg").hide();
    });

    //section2 의 찜 버튼 클릭하면 하트 색상 변경
    $(".box-office .btns button").click(function(){
        $(this).toggleClass("active");
    });
    //swiper slide
    var swiper = new Swiper(".mySwiper1", {
      slidesPerView: 4,
      spaceBetween: 30,
      loop:true,
      navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        }
    });
    //swiper slide
    var swiper = new Swiper(".mySwiper2", {
      slidesPerView: 6,
      spaceBetween: 16,
      loop:true,
      navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        }
    });
});