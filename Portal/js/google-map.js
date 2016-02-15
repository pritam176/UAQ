(function( $ ) {

  $.makeMap = function( options ) {

    var defaults = {
      mapBox: '#map',
      lang: 'en',
      mapCenter: {},
      zoom: 5,
      markers: {},
      key: 'AIzaSyB4HRRc3yX_t8e7XsdHXJamaSkiUkUByYA',
      helpers: 'js/dest/map-helpers.js',
      marker: 'img/marker.png',
      closeIcon: 'img/closemap.png' // close icon for info window
    };

    $.makeMap.opt = $.extend( {}, defaults, options );

    var mapUrl = "https://maps.googleapis.com/maps/api/js?key="+$.makeMap.opt.key+"&sensor=false&language="+$.makeMap.opt.lang+"&callback=jQuery.makeMap.loadScripts";

    if(typeof google === 'object' && typeof google.maps === 'object'){
      $.makeMap.init();
    }else {
      $.getScript(mapUrl);
    }

  };//plugin

  var markers = [];
  var infoBoxes = [];

  $.makeMap.loadScripts = function(e){

    $.getScript($.makeMap.opt.helpers, function(data){
      $.makeMap.init();
    });

  };

  $.makeMap.init = function(){
    var mapOptions = {
      zoom: $.makeMap.opt.zoom,
      center: new google.maps.LatLng($.makeMap.opt.mapCenter.lat, $.makeMap.opt.mapCenter.lon),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    $.makeMap.map =  new google.maps.Map($($.makeMap.opt.mapBox)[0], mapOptions);
    addInfo();
  };


  function addInfo(){
    var counter = 0;

    for(var key  in $.makeMap.opt.markers) {
      counter++;
      var elem = $.makeMap.opt.markers[key];
      var location = new google.maps.LatLng(elem.coordinates.lat, elem.coordinates.lon);
      var info = {
        title: elem.title,
        desc: elem.description,
        link: elem.link
      };
      addMarker(location, counter, info);
      info = null;
    }//for

  }

  // ADD MARKER
  function addMarker(location, labelTxt, info) {
    var marker = new MarkerWithLabel({
      position: location,
      icon: new google.maps.MarkerImage($.makeMap.opt.marker),
      draggable: false,
      raiseOnDrag: false,
      map: $.makeMap.map,
      labelContent: labelTxt,
      labelAnchor: new google.maps.Point(9, 25),
      labelClass: "markerLabel" // the CSS class for the label
    });

    addInfoBox(marker, makeInfo(info));
    markers.push(marker);
  }//func

  //ADD WINDOW WITH INFORMATION TO MARKER

  function addInfoBox(marker, content){
    var opt = {
      content: content,
      isHidden: false,
      boxClass: 'mapWindow',
      enableEventPropagation: true,
      closeBoxURL: $.makeMap.opt.closeIcon,
      closeBoxMargin: '0px 10px 0 0',
      pixelOffset: new google.maps.Size(22, -45),
      zIndex: 888
    };

    //for mobile
    google.maps.event.addListener(marker, "click", function (e) {
      $(infoBoxes).each(function(){
        this.close($.makeMap.map, this);
      });
      iWindow.open($.makeMap.map, this);
    });

    google.maps.event.addListener(marker, "mouseover", function (e) {
      $(infoBoxes).each(function(){
        this.close($.makeMap.map, this);
      });
      iWindow.open($.makeMap.map, this);
    });

    google.maps.event.addListener($.makeMap.map, "click", function (e) {
      iWindow.close($.makeMap.map, this);
    });

    var iWindow = new InfoBox(opt);

    infoBoxes.push(iWindow);

  }//func

  //Info Window Html
  function makeInfo(info){
    var html = '<div class="mapWindowCont"><span class="mapWindowArrow"></span>';
    html += '<h4 class="title">'+info.title+'</h4>';
    html += '<p>'+info.desc+'</p>';
    html += '<p><a href="'+info.link.url+'" target="_blank" title="'+info.link.caption+'">'+info.link.title+'</a></p>';
    html += '</div>';
    return html;
  }//func

  // Click on marker on page not on map, to show appropriate marker on map
  $(document).on('click', '._mapIcon', function(){
    if( $(this).attr('data-location') ){
      var position = $(this).attr('data-location').split(',');
      $.makeMap.map.setCenter(new google.maps.LatLng(parseFloat(position[0]),parseFloat(position[1])))
      $(infoBoxes).each(function(){
        this.close($.makeMap.map, this);
      });
      var index = parseInt($(this).text())-1;
      var infoBox = infoBoxes[index];
      infoBox.open($.makeMap.map, markers[index]);
      // Scroll to map
      $('html, body').animate({
        scrollTop: $('#map').offset().top - 50 + 'px'
      }, 'fast');
    }
  });


})( jQuery );