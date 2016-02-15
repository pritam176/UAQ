function createDynamicPage(){
    //ajax call 
    $.getJSON("http://uaqportal/img_sample.json", function(data) {
        var imageList = " ";
        $.each(data.Galleryimages, function(){
            imageList='<li><a class="thumb" href="'+this.thumbnail+'" data-subimg="'+this.medium_img+'"><img src="'+this.large_img+'" /></a></li>';
            console.log(this.thumbnail);
        });

        //populate html
        $('.thumbs').html(imageList);

        $('div.content').css('display', 'block');

                //controll opacity 
                var onMouseOutOpacity = 0.67;
                $('#thumbs ul.thumbs li').opacityrollover({
                    mouseOutOpacity:   onMouseOutOpacity,
                    mouseOverOpacity:  1.0,
                    fadeSpeed:         'fast',
                    exemptionSelector: '.selected'
                });
                
                // Initialize Gallery
                var gallery = $('#thumbs').galleriffic({
                    delay:                     2500,
                    numThumbs:                 10000000000000,
                    preloadAhead:              10000000000000,
                    enableTopPager:            false,
                    enableBottomPager:         true,
                    maxPagesToShow:            10000000000000,
                    imageContainerSel:         '#slideshow',
                    controlsContainerSel:      '#controls',
                    captionContainerSel:       '#caption',
                    loadingContainerSel:       '#loading',
                    renderSSControls:          true,
                    renderNavControls:         true,
                    playLinkText:              '',
                    pauseLinkText:             'Pause Slideshow',
                    prevLinkText:              '&lsaquo; Previous',
                    nextLinkText:              'Next &rsaquo;',
                    nextPageLinkText:          'Next &rsaquo;',
                    prevPageLinkText:          '&lsaquo; Prev',
                    enableHistory:             false,
                    autoStart:                 false,
                    syncTransitions:           true,
                    defaultTransitionDuration: 900,
                    onSlideChange:             function(prevIndex, nextIndex) {
                        this.find('ul.thumbs').children()
                            .eq(prevIndex).fadeTo('fast', onMouseOutOpacity).end()
                            .eq(nextIndex).fadeTo('fast', 1.0);
                    },
                    onPageTransitionOut:       function(callback) {
                        this.fadeTo('fast', 0.0, callback);
                    },
                    onPageTransitionIn:        function() {
                        this.fadeTo('fast', 1.0);
                    }
                });
            });
            
            $('#myImageModal').on('#preview-image', function(e) {
                e.preventDefault;
                $("#myImageModal").modal('show');
            });

}

function previewImage(){
    $('body').addClass('image-gallery-overlay');
    setTimeout(function() {
        $("#large-image-modal").modal('show');
    }, 100);
}

$('#large-image-modal').on('hidden.bs.modal', function () {
    $('body').removeClass('image-gallery-overlay');
});

// init ajax call 
//createDynamicPage();