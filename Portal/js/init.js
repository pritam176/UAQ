$(function() {

    var $windowWidth = $(window).width();
    var $windowHeight = $(window).height();


    // Mobile Slider Menu
    function resize() {
        var height = $(window).height();
        $('.mobileMenu').css('height', height);
    }
    resize();

    $(window).resize(function() {
        resize();
    });

    $(document).ready(function() { /* stickey  header style */
        homeSlider = $("._slider").bxSlider({});
		
		//vid.prop('muted', true);
		var sizep = $(".main-content-wrap p").css('font-size');
		var sizelbl = $(".content-filter-wrap .form-group .form-lbl").css('font-size');
		sizelbl =parseInt(sizelbl, 10);
		//var sizea = $(".department-listing ul li a").css('font-size');
		
        $(".dropdown-menu li a").click(function() {
            valsel = $(this).text();
            $(".dropdown-toggle").find('.sele').html(valsel);
            $(".dropdown-toggle").find('.sele').text();
		
				sizepNormal =parseInt(sizep, 10);
				if(isNaN(sizepNormal)){
					sizepNormal=14;
				}
			
            if (valsel == "A") {
				fontSize = sizepNormal;
				fontH3 = 25;
				fontH4 = 16;
				changeFont(fontSize, fontH3, fontH4);
            } else if (valsel == "A+") {
				fontSize = sizepNormal+2;
				fontH3 = 26;
				fontH4 = 17;
               	changeFont(fontSize, fontH3, fontH4);
            }
            if (valsel == "A++") {
				fontSize = sizepNormal+4;
				fontH3 = 28;
				fontH4 = 18;
				changeFont(fontSize, fontH3, fontH4);
                
            }
 function changeFont(fontSize, fontH3, fontH4)
	 {
			var normalFont =14;
				$(".main-content-wrap p").css("font-size", fontSize);
                $(".main-content-wrap span").css("font-size", fontSize);
                $(".main-content-wrap div").css("font-size", fontSize);
				$(".department-listing a").css("font-size", fontSize);
				$(".service-catalogue-items li a").css("font-size", fontSize);
                $(".listing-wrap").css("font-size", fontSize);
                $(".content-filter-wrap .form-group .form-lbl").css("font-size", sizelbl);
				$(".page-nav a").css("font-size", normalFont);
				$("select") .css("font-size", normalFont);
				$(".content-filter-wrap .form-group .custom-dropdown .custom-select-box select").css("font-size", sizelbl);
				/* service catelogue */
				
				$(".panel-groupFaq1 .panel-style .panel-title").css("font-size", fontH4);
				$(".panel-groupFaq1 .panel-style .panel-body .service-catalogue-items li").css("font-size", normalFont);
				
				
				//$(".main-content-wrap h3").css("font-size", fontH3);
		 
	 }
	 
	 
        });

        $(".share-print").click(function() {
            window.print();
        });

        divHeight = $('.mainMenu').height() + 40;
        $('.mainMenu').css('top', -divHeight);
        divHeight = divHeight;
		var vid = document.getElementById("vid1");
			if(vid){
				if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {

				$("#playhomevid").show();
					$(document).on('click', '#vid1', function (e) {
						var vid = $(this).get(0);
						if (vid.paused === false) {
							vid.pause();
								$("#playhomevid").show();
						} else {
							vid.play();
							$("#playhomevid").hide();
						}

						return false;
					});
							
					$(document).on('click', '#playhomevid', function (e) {
						if (vid.paused === false) {
							vid.pause();
						} else {
							vid.play();
							$("#playhomevid").hide();
						}

						return false;
					});
				}else{	
					$("#playhomevid").hide();
					if(!!vid.canPlayType){
							vid.play();
					}
				}
			}
	
    });

    //home page menu click functionality need to be converted into objects for reuseability

    $(".menuItems>li.navItem>p .mobile-nav-arrow").off("click").on("click", handleMenuClick);

    function handleMenuClick(e) {
        e.stopPropagation();
        var $this = $(this);
        var parent = $this.parent();
        var hasSub = parent.parent('li').find(".sub-menu");
        $(".sub-menu").slideUp();
        $(".navItem").not(parent).removeClass("open");
        if (hasSub.length >= 1) {
            parent.toggleClass("open");
            hasSub.stop(true, false).slideToggle();
        }
        $this = null;
        parent = null;
    }



    $("._accordion").collapse(function() {});
    $('.collapse').on('show.bs.collapse', function(e) {
        $(".collapse.in").collapse('hide'); //hide;
    })

    $("._gallery").magnificPopup({
        delegate: 'a', // child items selector, by clicking on it popup will open
        type: 'image',
        gallery: {
            enabled: true
        }
        // other options 
    });

    //popup controls 

    $(window).on('load resize', function() {
		var window_size = $(window).width();
        var window_height = $(window).height();
		var footerheight = $('footer').outerHeight();
		var headerheight1 = $('#header').height();
		var modelheight= window_height-headerheight1;
		modelheight= modelheight-footerheight;
		modelheight= modelheight+40;
		$(".terms-model .modal-dialog").css('height',modelheight);
		
        if (window_size < 768) {
			$(".content").css("padding-bottom",footerheight);
            $('.mainMenu').hide();
        }
        if($('div').hasClass('home')) {
            if (window_size > 768) { 
                $('.submenuItemsHolder').show();
            } 
        }else {
            if (window_size > 768) {
                $('.submenuItemsHolder').show();
            } else {
               // $('.submenuItemsHolder').hide();
            }
        }
        policyHolderHeight = $(".policyHolder").height();
        if (policyHolderHeight < 17) {
            $(".hovercontainer").css("bottom", 193);
        }
        divHeight = $('.mainMenu').height() + 40;
        //$('.mainMenu').css('top',-divHeight);
        divHeight = divHeight + 400;

        $(".submenuItem").hover(function() {
            var footer_total_height = $('footer').outerHeight();
            if ($('#collapseApp').is('.in')) {
                $('.stickyBottom .submenuItemsHolder .submenuItem .hovercontainer').css('bottom', footer_total_height);
            } else if ($('#collapseSocial').is('.in')) {
                $('.stickyBottom .submenuItemsHolder .submenuItem .hovercontainer').css('bottom', footer_total_height);
            } else {
                $('.stickyBottom .submenuItemsHolder .submenuItem .hovercontainer').css('bottom', footer_total_height);
            }

        });

        if($('.mainmenu').length>0) {
                var headerheight = $('#header').height()-1;
                $('.mainmenu').css('position','fixed').css('top',headerheight).css('width','100%').css('z-index', '98');
            }

        /*$('.special-landing-wrap > .col-sm-4').each(function(){  
            
            var highestBox = 0;
            $(this).each(function(){
            
                if($(this).height() > highestBox) 
                   highestBox = $(this).height(); 
            });  
            
            $(this).height(highestBox);
            
        
        });*/

        

            if (window_size < 768) {
                if($('.content div').hasClass('special-landing-wrap')) {
                   // $('.stickyBottom .submenuItem').show();
                    $('.content').css('padding-bottom',$('.stickyBottom').height());
					 $('.content').css('padding-bottom',20);
                } else {
                    $('.stickyBottom .submenuItem').show();
                   // $('.content').css('padding-bottom',$('.stickyBottom').height());
				   $('.content').css('padding-bottom',20);
                }
            } else {
                if(!$('.content div').hasClass('special-landing-wrap')) {
					
                    $('.stickyBottom .submenuItem').show();
                    $('.content').css('padding-bottom',$('.stickyBottom').height());
                } else {
                    $('.content').css('padding-bottom',$('.stickyBottom').height());
                }
            }

    });
    //var share_FB_Url, share_twtter_Url, email_share;
    //share social callout 
    //fb share 
    $('.social-share-wrap .share-fb a').attr('href',share_FB_Url);

    //tweet share 
    $('.social-share-wrap .share-twitter a').attr('href',share_twtter_Url);

    //email share 
    $('.social-share-wrap .share-email a').attr('href',email_share);


    //$("._select").chosen({disable_search_threshold: 10}); 

    var bool = true;
    $("body").on("click", ".searchMob", function() {
		
		if($('#mobileMenu').hasClass('activeMenu')){
			
			$("#mobileMenu").hide();
			$("#mobileMenu").removeClass("activeMenu");
			
		}else{
			
			$("#mobileMenu").addClass("activeMenu");
			$("#mobileMenu").show();
			
		}
        direction = "";
        if ($(".searchMob").hasClass("arabicnav")) {

            if (bool) {
                $(".container-fluid").css('position', 'relative');
                $(".container-fluid").animate({
                    right: "-260px"

                }, 50);
                bool = false;
            } else {

                $(".container-fluid").animate({
                    right: "0px"
                }, 50);
                bool = true;
                setTimeout(function() {
                    $(".container-fluid").css('position', '');
                }, 500);

            }

        } else {


            if (bool) {
                $(".container-fluid").css('position', 'relative');
                $(".container-fluid").animate({
                    left: "-260px"

                }, 50);
                bool = false;
            } else {

                $(".container-fluid").animate({
                    left: "0px"
                }, 50);
                bool = true;
                setTimeout(function() {
                    $(".container-fluid").css('position', '');
                }, 500);

            }

        }

    });

 $('body').on('click', '.logo-holder, .mob-search, .sliderHolder, footer, .content',function(e) {
    if($('.container-fluid').css('left') == '-260px'){
			$("#mobileMenu").hide();
			$("#mobileMenu").removeClass("activeMenu");
		
    if ($(".searchMob").hasClass("arabicnav")) {
                $(".container-fluid").animate({
                    right: "0px"
                }, 50);
                bool = true;
                setTimeout(function() {
                    $(".container-fluid").css('position', '');
                }, 500);

        } else {
                $(".container-fluid").animate({
                    left: "0px"
                }, 50);
                bool = true;
                setTimeout(function() {
                    $(".container-fluid").css('position', '');
                }, 500);

        }
        }

 });

    $("body").on("click", ".searchDesktop", function() {
        $(".mainMenu").animate({
            top: "0px"
        }, 100, function() {
            setTimeout(function() {
                $('.mainMenu').css("display", "block");
                $('.mainMenu').addClass('active-slide');
            }, 200);
        });
    });
    $("body").on("click", ".menuLast img", function() {
        $(".mainMenu").animate({
            top: '-' + divHeight,
        }, 500, 'swing', function() {
            setTimeout(function() {
                $('.mainMenu').css("display", "none");
                $('.mainMenu').removeClass('active-slide');
            }, 700);
        });
    });

        $('body').on('click', '.content, footer, .sliderHolder, .mainMenu .table div ul li', function() {
            if($('.mainMenu').css('display') == 'block'){
                 $(".mainMenu").animate({
                    top: '-' + divHeight,
                }, 500, 'swing', function() {
                    setTimeout(function() {
                        $('.mainMenu').css("display", "none");
                        $('.mainMenu').removeClass('active-slide');
                    }, 700);
                });
            }
        });


    //calendar init
    $(".datepicker").datepicker({
        dateFormat: 'dd-mm-yy',
        changeMonth: true,
        changeYear: true,
        yearRange: '1950:2050',
        beforeShow: function(){$('input').blur();
		}
		  
    }).attr('readonly', 'readonly');   //Global Date Format

	/* Arabic Calendar  */
	function Arabic_lan() {
		$.datepicker.regional[ "ar" ] = { 
			closeText: "إغلاق", 
			prevText: "&#x3C;السابق", 
			nextText: "التالي&#x3E;", 
			currentText: "اليوم", 
			monthNames: [ "يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", 
			"يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر" ], 
			monthNamesShort: [ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" ], 
			dayNames: [ "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت" ], 
			dayNamesShort: [ "أحد", "اثنين", "ثلاثاء", "أربعاء", "خميس", "جمعة", "سبت" ], 
			dayNamesMin: [ "أحد", "اثنين", "ثلاثاء", "أربعاء", "خميس", "جمعة", "سبت" ], 
			weekHeader: "أسبوع", 
			firstDay: 0, 
				isRTL: true, 
			showMonthAfterYear: false, 
			yearSuffix: "" };
		$.datepicker.setDefaults($.datepicker.regional['ar']);
	} 
	
	if( $("body").css('direction') == 'rtl') {
		Arabic_lan();
	}
   /*  calendar ends here */	
    /* populating input field */

    $("body").on ('change', "input:file", function(event) {
		var files;
		/* Changes From OutSource Team */
        if($(this).attr('name').indexOf('].attachmentFile') > -1){	
			var file_fakeremove = $(this).val(); 
			file_fakeremove = file_fakeremove.replace("C:\\fakepath\\", "");
			$(this).parent().parent().parent().find(".file-upload-option-view").val(file_fakeremove);
			return;
		}
        /* Changes From OutSource Team */
		
		if(this.value !=""){
		thisid= "#"+$(this).attr("id");
		
		//alert("thisid" + thisid);
		$(this).attr('disabled', true);
		$(this).parent().append('<div class="brows-loading"> <img src="/img/brows-loader.gif"></div>');
		$(this).parent().addClass("loading-time");
		files=event.target.files;
		//console.log("fileupload clicked");
		var oMyForm = new FormData();
		oMyForm.append("file", files[0]);
		var superparent = $(this).parent().parent().parent();
		var hiddenName=$(this).attr("id");
		$error_failed = $(this).attr("error-failed");
		hiddenName = hiddenName+"_name";
		var allowedtype= ""; 
		/* var ext = this.value.match(/\.(.+)$/);

		 if(ext==null){
			 ext="exe"; 
		 }else{
			ext = this.value.match(/\.(.+)$/)[1];
		 }
		 */
		 var ext = this.value.substr((this.value.lastIndexOf('.') + 1));
		//alert("test1"+ ext);

		 if(ext==null){
			 ext="exe"; /* fake maping */
		 }else{
			ext = this.value.substr((this.value.lastIndexOf('.') + 1));
		 }
		 
		 ext = ext.toLowerCase();

		 
		switch(ext)
		{
			case 'jpg':
			case 'jpeg':
			case 'bmp':
			case 'gif':
			case 'png':
			case 'doc':
			case 'docx':
			case 'pdf':
				allowedtype=1;	
				break;
			default:
				allowedtype=0;
		}
		
		if(files[0].size > 2000000){
				//alert(files[0].size);
			$(thisid).attr('disabled', false);
			$(thisid).parent().removeClass("loading-time");	
			$(".brows-loading").detach();	
			$error_size = $(this).attr("error-size");
			labelfor= superparent.find('input[type="text"]').attr("name");
			superparent.find('label.error').remove();
			superparent.find('input[type="text"]').val("");
			var sizelabel= "<label for='"+labelfor+"' class='error'>"+$error_size+"</label>"
			superparent.find('input[type="text"]').after(sizelabel);
			$("#"+hiddenName).val("");
			return false;
		}else if(allowedtype==0){
			$(".brows-loading").detach();
			$(thisid).attr('disabled', false);
			$(thisid).parent().removeClass("loading-time");		
			$error_type = $(this).attr("error-extention");
			superparent.find('input[type="text"]').val("");
			superparent.find('label.error').remove();
			labelfor= superparent.find('input[type="text"]').attr("name");
			var sizelabel= "<label for='"+labelfor+"' class='error'>"+$error_type+"</label>"
			superparent.find('input[type="text"]').after(sizelabel);
			$("#"+hiddenName).val("");
			return false;
			
		}else{
					
			$.ajax({
				url : "/en/uploadFile.html",
				data : oMyForm,
				type : "POST",
				enctype: 'multipart/form-data',
				processData: false, 
				contentType:false,
				success : function(result) {
					resultval= result.split("-")[0];
					if(resultval.split("-")[0] !=0 && result!= "null-null"){
						
						superparent.find('label.error').remove();
						var fileName = files[0].name;
						fileName= fileName.replace("C:\\fakepath\\", "");
						superparent.find('input[type="text"]').val(fileName);
						$("#"+hiddenName).val(result);
						$(".brows-loading").detach();
						$(thisid).attr('disabled', false);	
						$(thisid).parent().removeClass("loading-time");	
						
					}else{
						//alert("test failed");
						$(".brows-loading").detach();
						$(thisid).attr('disabled', false);	
						$(thisid).parent().removeClass("loading-time");		
						superparent.find('input[type="text"]').val("");
						superparent.find('label.error').remove();
						labelfor= superparent.find('input[type="text"]').attr("name");
						var sizelabel= "<label for='"+labelfor+"' class='error'>"+$error_failed+"</label>"
						superparent.find('input[type="text"]').after(sizelabel);
						$("#"+hiddenName).val("");
					}
					
					
				},
				error : function(result){
					$(".brows-loading").detach();
					$(thisid).attr('disabled', false);	
					$(thisid).parent().removeClass("loading-time");		
					superparent.find('input[type="text"]').val("");
					superparent.find('label.error').remove();
					$error_failed = $(this).attr("error-failed");
					labelfor= superparent.find('input[type="text"]').attr("name");
					var sizelabel= "<label for='"+labelfor+"' class='error'>"+$error_failed+"</label>"
					superparent.find('input[type="text"]').after(sizelabel);
					$("#"+hiddenName).val("");
					//alert('error'+JSON.stringify(result));
				}
			});
			
		
		}
		
		}
        
    });


    $(document).ready(function() {
        // site url 
        var site_Url = window.location.href;

        // $("#login-form").validate();


        //$("#username").focus(function() {
         //   $(".login-error").remove();

        //});
        //$("#password").focus(function() {
         //   $(".login-error").remove();

       // });

        //$(".degree").html("");

        $.ajax({
            method: "POST",
            url: "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D%222347115%22%20and%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys", // 
            success: function(data) {
                $(".degree").html(data.query.results.channel.item.condition.temp + "<sup>o</sup>C <sup class='weatherStatus'>"+ data.query.results.channel.item.condition.text +"</sup>");
                description = data.query.results.channel.item.description;
                var wimg = description.match(/src="(.+?[\.jpg|\.gif|\.png]")/)[1];
                wimg = wimg.split('"');
				//wimg = wimg.split('"');
				wimg="https://s.yimg.com/zz/combo?/a/i/us/we/52/"+data.query.results.channel.item.condition.code+".gif";
                $("#weatherimage").attr('src', wimg);
            },
            error: function(e) {}

        });

        /* Video gallery js */
        $('#myVideoModal').on('show.bs.modal', function(event) {
            $('body').addClass('body-gallery-overlay');
            var div = $(event.relatedTarget) // div that triggered the modal
            var recipientmp4 = div.data('videomp4')
            var recipientogg = div.data('videoogg')
                // Extract info from data-* attributes
                // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
                // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            var modal = $(this)
            var video = document.getElementsByTagName('video')[0];
            var sources = video.getElementsByTagName('source');
            sources[0].src = recipientmp4;
            sources[1].src = recipientogg;
            video.load();
        });

        $('#myVideoModal').on('hidden.bs.modal', function(event) {
            $("#video-container")[0].pause();
            $('body').removeClass('body-gallery-overlay');
        });

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
/* --------- email validation for auto populated fields */

   
   });


/* ------------ Ajax call for happiness indicator  */

	$(".rate a").on("click", function(e) {
			e.preventDefault();
		   $.ajax({
            method: "GET",
            url: "/en/smiley.html", 
            success: function(data) {
				result= JSON.parse(data);
				if(result.status =="fail"){
					feedback= $("#rate_model_feedback").attr("data-rate-feedback");
					$("#modal-lbl-head-feedback").html(feedback);
					$('#rate_modal_success').modal('show');
					return false;
				}else{

					$('#rate_modal').modal('show');
				}	
				
              
            },
            error: function(e) {
                //$('#rate_modal').modal('hide');
            }

        });
	});
	
	
	
    $(".smile").click(function() {
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
            success: function(data) {
                $('#rate_modal').modal('hide');
				result= JSON.parse(data);
				if(result.status =="fail"){
					
						feedback= $("#model_smile_question").attr("data-smile-callback-error");
						
					}else{
						feedback= $("#model_smile_question").attr("data-smile-callback-sucess");
					}
				$("#modal-lbl-head-feedback").html(feedback);
				$('#rate_modal_success').modal('show');
            },
            error: function(e) {
                $('#rate_modal').modal('hide');
            }

        });
    });

    if ($("div").hasClass("Heroslider")) {
        $("#header").addClass("homeHeader");
        $(window).on('load resize', function() {
            totalheight = $(window).height();
            totalwidth = $(window).width();
            headerheight = $("#header").height();
            footerheight = $("#footer").height();
            sliderheight = totalheight - (headerheight + footerheight);
            mobileSliderHeight = totalheight - (headerheight + footerheight);

            if (totalwidth > 990) {
                $(".sliderHolder").css("height", sliderheight + 130);
            } else if(totalwidth < 767){
               // $(".sliderHolder").css("height", mobileSliderHeight);
               $(".sliderHolder").css("height", 285);
                //console.log('height'+mobileSliderHeight);
            } 
            else {
                $(".sliderHolder").css("height", sliderheight + 100);
            }

        })
    }

	

	  if ($("div").hasClass("inner-banner-wrap")) {
		  bgimage= $(".banner-img").attr("data-bgimage");
		 $(".banner-img").css('background-image', 'url("'+bgimage+'")');
	  }

	  $("#headerSearch").click(function(){
           document.getElementById("header-search").submit();
	  });
	  
	  
	  $(".page-nav a").click(function(event){
		  event.preventDefault();
		  window.history.go(-1);
	  })
    /* Ajax call for happiness indicator ends here  */

		$("#profileModal").on('hidden.bs.modal', function (e) {
			window.location.reload();
		});
		
}); //document ready ends here

$(window).load(function() {
        //equal height for div
        (function($) {

            $.fn.sameheight = function() {
                var maxHeight = 0,
                $this = $(this);
                thisSelector = $this.selector;

                $this.each( function() {
                    // reset the height
                    $(this).css({'height':'auto'});
                    var thisHeight = $(this).height();
                    if ( thisHeight > maxHeight ) {
                        maxHeight = thisHeight;
                    }
                });

                return $this.height(maxHeight);

            };

            $( window ).resize(function() {
                   $(thisSelector).sameheight();
                });

        })(jQuery);

        $('.special-landing-wrap .col-sm-4').sameheight();

    });
	
	
$(function () {
    $('#accordion1').on('shown.bs.collapse', function (e) {
        var offset = $(this).find('.collapse.in').prev('.panel-heading');
        if(offset) {
            $('html,body').animate({
                scrollTop: $(offset).offset().top -150
            }, 500); 
        }
    }); 
	 $(".panel-style").click(function(e){
		 if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
			console.log("iphone"); 
		 } 
		
	});
	$("body").on( "click", ".attachment-btm a", function(e) {
		//alert("notmob");
	if( navigator.userAgent.match(/Android/i)|| navigator.userAgent.match(/webOS/i)|| navigator.userAgent.match(/iPhone/i)|| navigator.userAgent.match(/iPad/i)|| navigator.userAgent.match(/iPod/i)|| navigator.userAgent.match(/BlackBerry/i)|| navigator.userAgent.match(/Windows Phone/i)){
		
			e.preventDefault();
			imgUrl= $(this).attr("href");
			$("#myImgaeModal").modal("show");
			$("#myImgaeModal #image-container").html("<img src="+imgUrl+" class='img-responsive'/>");
			e.preventDefault();
			return false;
		
	}else{
		
		//alert("notmob");
	}
	});
	
	$('.hasDatepicker ').attr('readonly', true);
	$('.hasDatepicker ').on("change", function() {
		$(this).next(".error").hide();
	  })
	$('#trigger-search').on("click",function(e){
		e.preventDefault;
		return false;
	});
	/* password strength checker */
	$('.eservice-form input[name="password"]').keyup(function(e) {
		 var strength="";
		 var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		 var mediumRegex = new RegExp("^(?=.{8,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
		 var enoughRegex = new RegExp("(?=.{8,}).*", "g");
		 var strength_var= $("#myImgaeModal").attr("data-msg-stength");
		if( $(this).val().length<=15){
		 if (false == enoughRegex.test($(this).val())) {
				 //console.log("more cnarecter");
				  $("#detach").detach();
		 } else if (strongRegex.test($(this).val())) {
				 $("#detach").detach();
				 var stronger= $("#myImgaeModal").attr("data-msg-strong");
				 var strength='<div id="detach" class="password-policy-wrap">'+strength_var+': <span for="password_register" class="password_register password-register-strong" style="display: inline-block;"></span></div>';
				  $(this).after(strength);
				  //console.log("more Strong");
		 } else if (mediumRegex.test($(this).val())) {
				 //console.log("more Medium");
				 $("#detach").detach();
				 var medium= $("#myImgaeModal").attr("data-msg-medium");
				 var strength='<div id="detach" class="password-policy-wrap">'+strength_var+': <span for="password_register" class="password_register password-register-medium" style="display: inline-block;"></span></div>';
				  $(this).after(strength);
		 } else {
				// console.log("more Weak");
				var weak= $("#myImgaeModal").attr("data-msg-weak");
				var strength='<div id="detach" class="password-policy-wrap">'+strength_var+': <span for="password_register" class="password_register password-register-weak" style="display: inline-block;"></span></div>';
				 $("#detach").detach();
				  $(this).after(strength);
		 }
		}else{
			
			$("#detach").detach();
		}	 
		 return true;
		 
	});
	
	$('.charecterKey').keypress(function (e) {
		var a = [39, 32];
		var k = e.which;

		for (i = 64; i <222; i++)
		a.push(i);

		// allow a max of 1 decimal point to be entered
		if (this.value.indexOf(".") === -1) {
			a.push(46);
		}

		if (!(a.indexOf(k) >= 0)) e.preventDefault();

		//$('span').text('KeyCode: ' + k);
	});
});
