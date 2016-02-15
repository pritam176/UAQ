$(function(){

var $windowWidth = $(window).width();
var $windowHeight = $(window).height();


// Mobile Slider Menu
function resize() {
	var height = $(window).height();
	$('.mobileMenu').css('height', height);
}
	resize();
 
$(window).resize(function(){
	resize();
});


//home page menu click functionality need to be converted into objects for reuseability

$(".menuItems>li.navItem>p").off("click").on("click",handleMenuClick);

function handleMenuClick (e){
	e.stopPropagation();
	var $this = $(this);
	var parent = $this.parent();
	var hasSub = parent.find(".sub-menu")
	$(".sub-menu").slideUp();
	$(".navItem").not(parent).removeClass("open");
	if(hasSub.length >= 1){
		parent.toggleClass("open");
		hasSub.stop(true,false).slideToggle();
	}
	$this = null;
	parent = null;
}

$("._slider").bxSlider({});

$("._accordion").collapse()


$("._gallery").magnificPopup({
  delegate: 'a', // child items selector, by clicking on it popup will open
  type: 'image',
    gallery: {
    enabled: true 
  }
  // other options 
});

//$("._select").chosen({disable_search_threshold: 10}); 

		var bool = true;
			$("body").on("click",".searchMob",function(){
				if(bool){
					$(".wrapper").animate({
						left : "-260px"
					},50);
					bool = false;
				}else{
					$(".wrapper").animate({
						left : "0px"
					},50);
					bool = true;
				}
			})
			$("body").on("click",".searchDesktop",function(){
				$(".mainMenu").animate({
					top : "0px"
				})
			})
			$("body").on("click",".menuLast img",function(){
				$(".mainMenu").animate({
					top : "-300px"
				})
			})


//calendar init
$(".datepicker").datepicker({ dateFormat: 'dd-mm-yy', changeMonth: true,changeYear: true, yearRange: '1950:2050'}); //Global Date Format


	
})//document ready ends here



