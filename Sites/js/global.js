var alquwain = {
    init: function () {
        alquwain.bind();
        if ($('.image-gallery').length > 0) {
            alquwain.imageGallery();
        }
        /*** Home page video gallery trigger init() ***/
            if ($('.show-video-gallery').length > 0) {
            alquwain.homePageVideoGallery();
        }
        /*** Home page image gallery trigger init() ***/
        if ($('.show-image-gallery').length > 0) {
            alquwain.homePageImageGallery();
        }

        if ($('.video-gallery-page').length > 0) {
            alquwain.videoGallery();
        }
        if ($('.picker-input').length > 0) {
            $('.picker-input').datetimepicker({
                //language:  calLanguage,
                format: 'dd-mm-yyyy',
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 2,
                startView: 2,
                minView: 2,
                //endDate: '+0d'
                //startDate: todayDate
            });
        }
        if ($('.panel-group').length > 0) {
            alquwain.accordion();
        }

        alquwain.radioCheckbox();
    },
    bind: function () {
        alquwain.happinessOverlay();
        alquwain.searchWrapper();
        alquwain.mobileMenu();

        //$('.mobile-menu .has-submenu').not('.mobile-menu .has-submenu.active').find('.mobile-sub-nav').slideUp();

        if ($('.print-icon').length > 0) {
            $('.print-icon').on('click', function (e) {
                e.preventDefault();
                window.print();
            })
        }
        if ($('.font-changers').length > 0) {
            alquwain.fontResizer();
        }

    },
    fontResizer: function () {
        var originalSize = 14;
		var originalSizeh3 =20; 
		var originalSizetitle =18; 
		

        $('.decrease-font').on('click', function (e) {
            e.preventDefault();
            if (originalSize >= 14) {
                $('.page-content-wrapper').css('font-size', originalSize - 2);
				$('.content-wrapper .news-list .events-content-wrapper p').css('font-size', originalSize - 2);
                $('.content-wrapper .events-list p.date-stamp').css('font-size', '10px');
				$('.content-wrapper .news-list .events-content-wrapper .events-list-title a').css('font-size', originalSizeh3 - 2);
				$('.page-content-wrapper .content-wrapper .main-title').css('font-size', originalSizetitle - 2);

				
            }
        });
        $('.reset-font').on('click', function (e) {
            e.preventDefault();
            $('.page-content-wrapper').css('font-size', originalSize);
			$('.content-wrapper .news-list .events-content-wrapper p').css('font-size', originalSize);
            $('.content-wrapper .events-list p.date-stamp').css('font-size', '12px');
			$('.content-wrapper .news-list .events-content-wrapper .events-list-title a').css('font-size', originalSizeh3);
							$('.page-content-wrapper .content-wrapper .main-title').css('font-size', originalSizetitle);


        });
        $('.increase-font').on('click', function (e) {
            e.preventDefault();
            if (originalSize <= 16) {
                $('.page-content-wrapper').css('font-size', originalSize + 2);
                $('.content-wrapper .news-list .events-content-wrapper p').css('font-size', originalSize + 2);
                $('.content-wrapper .events-list p.date-stamp').css('font-size', '14px');
                $('.content-wrapper .events-list p.date-stamp').css('font-size', '14px');
				$('.content-wrapper .news-list .events-content-wrapper .events-list-title a').css('font-size', originalSizeh3+2);
				$('.page-content-wrapper .content-wrapper .main-title').css('font-size', originalSizetitle+2);
				
				
				
            }
        })
    },
    happinessOverlay: function () {
        $('.feedback .feedback-link').on('click', function (e) {
			 $.ajax({
			 method: "GET",
            url: "/en/smiley.html", 
				success: function(data) {
					result= JSON.parse(data);
					if (result.status == "fail") {
						feedback = $("#model_smile_question").attr("data-smile-callback-error");
						 $(".service-feedbackcall").show();
							$(".service-feedback").hide();
							$(".service-feedbackcall").html(feedback);

					}else{} 

           
				}
			 });

            e.preventDefault();
            $('.overlay-wrapper').show();
            $(".service-feedback").show();
            $(".service-feedbackcall").hide();
        });
        $('.feedback-wrapper .close-icon > i').on('click', function (e) {
            e.preventDefault();
            $('.overlay-wrapper').hide();
            $(".service-feedbackcall").hide();
            $(".service-feedback").show();
        })
    },
    searchWrapper: function () {
        $('.search-icon').on('click', function (e) {
            e.preventDefault();
            $(this).addClass('hidden');
            $('.search-form').toggleClass('hidden');
        });
        $('.close-search').on('click', function (e) {
            e.preventDefault();
            $(this).closest('.search-form').addClass('hidden');
            $('.search-icon').removeClass('hidden');
        })
    },
    mobileMenu: function () {
        $('.mobile-nav a').on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            $('.container').toggleClass('container-pushed');
			$('.bg-wrapper').toggleClass('bg-wrapper-fixed');
            $('body').css('overflow-x', 'hidden');
            $('.mobile-menu').toggleClass('mobile-nav-show');
        });

        $('.container').on('click', function () {
            if ($('.container').hasClass('container-pushed')) {
                $('.container').removeClass('container-pushed');
                $('.bg-wrapper').removeClass('bg-wrapper-fixed');
                $('body').removeAttr('style');
                $('.mobile-menu').removeClass('mobile-nav-show');
                //$('.has-submenu .mobile-sub-nav').css('display','none');
                $('.has-submenu .dropdown-nav').removeClass('active');
                $('.has-submenu').removeClass('active');
                $('.has-submenu .mobile-sub-nav').removeClass('active');
            }
        });
        $('.has-submenu .dropdown-nav').on('click', function (event) {
			//$('.has-submenu').removeClass('active');
            if ($(this).next('.mobile-sub-nav').hasClass('active')) {
                $('.mobile-sub-nav').slideUp('slow', function() {
                    $(this).removeClass('active');
                    $(this).parent('.has-submenu').removeClass('active');
                });
                
            } else {
                $('.mobile-sub-nav').slideUp('slow').removeClass('active');
                $(this).parent('.has-submenu').addClass('active');
                $(this).next('.mobile-sub-nav').addClass('active').slideDown('slow');

            }
        }); 


    },
    imageGallery: function () {
        
    },
    homePageVideoGallery: function () {
        $('#video-gallery').lightGallery({
            showThumbByDefault: true,
            videoAutoplay: false,
			onOpen        : function(el) {}, // Executes immediately after the gallery is loaded.
        onLoadComplete: function(el) {}, // Executes immediately after each object loaded is loaded.
        onSlideBefore : function(el) {

		$("video").each(function(){
		$(this).stop();
		$(this).get(0).pause();
		//$(this).pause();
		})


}, // Executes immediately before each transition.
        onSlideAfter  : function(el) {}, // Executes immediately after each transition.
        onSlideNext   : function(el) {}, // Executes immediately before each "Next" transition.
        onSlidePrev   : function(el) {}, // Executes immediately before each "Prev" transition.
        onBeforeClose : function(el) {}, // Executes immediately before the start of the close process.
        onCloseAfter  : function(el) {}, 



});
        $(document).on('click', '.show-video-gallery', function(e) {}).children('.video-gallery').on('click', function(event) {
            event.stopPropagation();
        });
    },
    homePageImageGallery: function () {

        $('.show-image-gallery').on('click', function (e) {
            //alert();
            $('.image-gallery-home .gallery a').eq(0).trigger('click');

        });
        $(".image-gallery-home .gallery").lightGallery({
            showThumbByDefault: true,
            closable: false,
            enableTouch: true,
            enableDrag: true,
            onCloseAfter: function (el) {

            }
        });
    },
    videoGallery: function () {
        //$(".video-gallery").lightGallery({
            /*showThumbByDefault: true,
            closable: false,
            enableTouch: true,
            enableDrag: true,
            videoAutoplay: false,
            youtubePlayerParams: {
                enablejsapi: 1,
            },
            onSlideNext: function (el) {
                pauseAllYoutube();
            },
            onSlidePrev: function (el) {
                pauseAllYoutube();
            },
            onSlideBefore: function (el) {
                pauseAllYoutube();
            },
            onSlideAfter: function (el) {
                pauseAllYoutube();
            },*/
        //});

        function pauseAllYoutube() {
            $('iframe[src*="youtube.com"]').each(function () {
                var iframe = $(this)[0].contentWindow;
                iframe.postMessage('{"event":"command","func":"pauseVideo","args":""}', '*');
            });
        }
    },

    accordion: function () {
          $('.panel-group').find('.panel-heading').on('click', function (e) {
            if (!$(this).hasClass('active')) {
                e.preventDefault();

                $('.panel-heading').removeClass('active');
                $(this).addClass('active');

                //Expand or collapse this panel
                $(this).next().slideDown('fast');
                //Hide the other panels
                $(".panel-collapse").not($(this).next()).slideUp('fast');
            } else {
                e.preventDefault();

                $('.panel-heading').removeClass('active');
                

                //Expand or collapse this panel
                $(this).next().slideUp('fast');
                //Hide the other panels
                $(".panel-collapse").not($(this).next()).slideUp('fast');
            
            }
        });/**/
    },
    radioCheckbox: function () {
        if ($('.question input').length > 0) {
            $('.question input').iCheck({
                checkboxClass: 'icheckbox_square-red',
                radioClass: 'iradio_square-red',
                increaseArea: '20%'
            });
        }
    }

}
$(".smile").click(function () {
    var ajaxurl = $(this).attr("data-smile");
    var ajaxval = $(this).attr("data-smile-val");
    var ajaxqstn = $("#model_smile_question").attr("data-smile-question");
    $.ajax({
        method: "POST",
        url: ajaxurl, // 
        data: {
            answer: ajaxval,
            question: ajaxqstn
        }, // 
        success: function (data) {
            result = JSON.parse(data);
            //$(".service-feedback").html(result.message);
            //$('.overlay-wrapper').css('display', 'none');
            if (result.status == "fail") {
                feedback = $("#model_smile_question").attr("data-smile-callback-error");

            } else {
                feedback = $("#model_smile_question").attr("data-smile-callback-sucess");

            }

            $(".service-feedbackcall").show();
            $(".service-feedback").hide();
            $(".service-feedbackcall").html(feedback);

        },
        error: function (e) {
            //$('#rate_modal').modal('hide');
        }

    });
});
$(function () {
    alquwain.init();
});
$(document).ready(function () {
	
	$(".back-to-top a").click(function(){
		window.scrollTo(500, 0);	
		return false;
	})
	
	
    $(".share-all").click(function () {
        var X = $(this).attr('id');

        if (X == 1) {
            $(".submenu").hide();
            $(this).attr('id', '0');
        } else {

            $(".submenu").show();
            $(this).attr('id', '1');
        }

    });

    //Mouseup textarea false
    $(".submenu").mouseup(function () {
        return false
    });
    $(".share-all").mouseup(function () {
        return false
    });


    //Textarea without editing.
    $(document).mouseup(function () {
        $(".submenu").hide();
        $(".share-all").attr('id', '');
    });
	
	 $('.submenu .share-fb a').attr('href',share_FB_Url);
	 
	 if($(".social-footer").length ==0){
		 $("footer  .row .col-lg-6.col-sm-7.col-xs-12").css("padding-top", "10px");
		 
	 }
	 
});
//$('#html5-videos').lightGallery();

/* ------- back button ---------------- */

	$(".back-btn a").click(function(event){
		  window.history.go(-1);
		  return false;
	});

/* -------- back button ------------- */

//search placeholder controller 
$(function(){
    $('#searchItemInput').data('holder',$('#searchItemInput').attr('placeholder'));
    $('#searchItemInput').focusin(function(){
        $(this).attr('placeholder','');
    });
    $('#searchItemInput').focusout(function(){
        $(this).attr('placeholder',$(this).data('holder'));
    });
});


$(".dropdown-nav").click(function(e){
   /* if($('.has-submenu').hasClass('active')){
       $('.has-submenu .dropdown-nav').removeClass('active');
           $('.has-submenu').removeClass('active');
           $('.has-submenu .mobile-sub-nav').removeClass('active');
    $('.has-submenu .dropdown-nav').slideUp('slow');
    return false;
    e.preventDefault();

    }*/	
});

$(window).on('load resize', function() {
var windowSize = $(window).width();
    if(windowSize>768) {
		$(".mobile-nav").addClass("show-on-mobile");
        $('.container').removeClass('container-pushed');
        $('body').removeAttr('style');
        $('.mobile-menu').removeClass('mobile-nav-show');
        $('.has-submenu .mobile-sub-nav').css('display','none');
        $('.has-submenu .dropdown-nav').removeClass('active');
        $('.has-submenu').removeClass('active');
        $('.has-submenu .mobile-sub-nav').removeClass('active');
    }
	if(windowSize<768) {
		/* hiding mobile menu from home page */
	
	 if($(".home-menu-wrapper").length == 1){
			$(".mobile-nav").removeClass("show-on-mobile");
			$(".mobile-nav").addClass("hide-on-mobile");
		 }else{
			$(".mobile-nav").removeClass("hide-on-mobile"); 
			$(".mobile-nav").addClass("show-on-mobile");
		 }
	/* hiding mobile menu from home page ends here */
	}
if(windowSize > 1199) {
    $(".mobile-nav").addClass("show-on-mobile");
    // home page animation
    $(".home-menu-wrapper ul.menu-wrapper-list > li:first-child").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', left:'0', bottom:'256px'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', left:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:first-child + li").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', left:'0', bottom:'256px'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', left:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:nth-child(3n)").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', right:'0', bottom:'256px'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', right:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:nth-child(4n)").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', right:'0', bottom:'256px'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', right:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:nth-child(5n)").hover(function(){
        //$(this).find('.hover-container').show();
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', left:'0', bottom:'0', top:'-256'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', left:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:nth-child(6n)").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', left:'0', bottom:'0', top:'-256'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', left:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:nth-child(7n)").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', right:'0', top:'-256px'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', right:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });

    $(".home-menu-wrapper ul.menu-wrapper-list > li:nth-child(8n)").hover(function(){
        $(this).find('.hover-container').show();
        $(this).find('.hover-container').stop().animate({visibility: 'visible', width: '542px', height:'482px', right:'0', top:'-256px'}, 100);
    }, function(){
        $(this).find('.hover-container').stop().animate({width: '220px', height:'180px', right:'0', top:'0', visibility: 'hidden'}, 100);
        $(this).find('.hover-container').hide();
    });
    } else {
        $(".home-menu-wrapper ul.menu-wrapper-list > li").hover(function(){
           $(".home-menu-wrapper ul.menu-wrapper-list > li .hover-container").hide();                                                       
        });
    }
	
});


var vid = document.getElementById("html5-videos");
if($("#html5-videos").hasClass("gallery")){
$('#html5-videos').lightGallery({
loop:false,
onOpen        : function(el) {}, // Executes immediately after the gallery is loaded.
        onLoadComplete: function(el) {}, // Executes immediately after each object loaded is loaded.
        onSlideBefore : function(el) {

		$("video").each(function(){
		$(this).stop();
		$(this).get(0).pause();
		//$(this).pause();
		})


}, // Executes immediately before each transition.
        onSlideAfter  : function(el) {}, // Executes immediately after each transition.
        onSlideNext   : function(el) {}, // Executes immediately before each "Next" transition.
        onSlidePrev   : function(el) {}, // Executes immediately before each "Prev" transition.
        onBeforeClose : function(el) {}, // Executes immediately before the start of the close process.
        onCloseAfter  : function(el) {}, 



});
}

// validation focus error handle 
        $('.form-control').focus(function(){
            if($(this).hasClass('error')) {
                $(this).next('.error').css('display','none');
            }
        }).focusout(function(){
            if($(this).hasClass('error')) {
                $(this).next('.error').css('display','inline-block');
            }
        });
		
		/* email validation for auto populated fields */
		
			$.fn.allchange = function (callback) {
				var me = this;
				var last = "";
				var infunc = function () {
					var text = $(me).val();
					if (text != last) {
						last = text;
						callback();
					}
					setTimeout(infunc, 100);
				}
				setTimeout(infunc, 100);
			};
			

$('#html5-videos li').click(function() {
          $('body').addClass('open-video-gallery');
});


