function initialize() {
		
		
		var mapOptions = {
				center : new google.maps.LatLng(-33.8688, 151.2195),
				zoom : 13
			};
		var map = new google.maps.Map(document.getElementById('map-canvas'),
					mapOptions);

		var input = /** @type {HTMLInputElement} */
		(document.getElementById('streetNo'));

		var types = document.getElementById('type-selector');
		//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
		// map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);
		var localityOptions = {
				//types: ['geocode'], 
				componentRestrictions: {
					country: 'ARE'
				}
			};
		

		var autocomplete = new google.maps.places.Autocomplete(input, localityOptions);
		autocomplete.bindTo('bounds', map);

		var infowindow = new google.maps.InfoWindow();
		
		//var place = autocomplete.getPlace();
		 
		
		
		var marker = new google.maps.Marker({
			map : map,

			draggable : true,
			anchorPoint : new google.maps.Point(0, -29)
		});
		
		var map;
    var marker;
    var infowindowPhoto = new google.maps.InfoWindow();
    var latPosition;
    var longPosition;
  
    

    
        if (navigator.geolocation) {
            
            navigator.geolocation.getCurrentPosition(function (position) {
                
                var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    
                latPosition = position.coords.latitude;
                longPosition = position.coords.longitude;
    
                marker = new google.maps.Marker({
                    position: pos,
                    draggable: true,
                    animation: google.maps.Animation.DROP,
                    map: map
                });
                
                map.setCenter(pos);
                updatePosition();
    
                google.maps.event.addListener(marker, 'click', function (event) {
                    updatePosition();
                });
    
                google.maps.event.addListener(marker, 'dragend', function (event) {
                    updatePosition();
                });
            });
        }

    
    function updatePosition() {
    
        latPosition = marker.getPosition().lat();
        longPosition = marker.getPosition().lng();
    
        //contentString = '<div id="iwContent">Lat: <span id="latbox">' + latPosition + '</span><br />Lng: <span id="lngbox">' + longPosition + '</span></div>';
        
        document.getElementById('lattitude').value = latPosition;
        document.getElementById('longitude').value = longPosition;
        
        //infowindowPhoto.setContent(contentString);
        //infowindowPhoto.open(map, marker);
    }
	
		/*google.maps.event.addListener(map, 'click', function(event) {
			console.log(event.latLng);
			$("#arror_msg .error").hide();
			marker.setPosition(event.latLng);
			marker.setVisible(true);
			document.getElementById("lattitude").value = event.latLng.lat();
			document.getElementById("longitude").value = event.latLng.lng();
			console.log(event.latLng.lat());	
			 
		});
		google.maps.event.addListener(marker, 'dragend', function(event) {
			document.getElementById("lattitude").value = event.latLng.lat();
			document.getElementById("longitude").value = event.latLng.lng();
			lat = event.latLng.lat();
			lng = event.latLng.lng();
			//codeLatLng();
			console.log(event.latLng.lat())
		});
		
		//browser location
		navigator.geolocation.getCurrentPosition(function(position) {
		     initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
		     map.setCenter(initialLocation);
		   }, function() {
		     
		   });*/
		
		google.maps.event.addListener(autocomplete,
						'place_changed',
						function() {
							infowindow.close();
							marker.setVisible(false);
							var place = autocomplete.getPlace();
							//console.log(place.formatted_address);
							//codeLatLng();
							//console.log(place);
							if (!place.geometry) {
								//window
								//	.alert("Autocomplete's returned place contains no geometry");
							    document.getElementById("lattitude").value ="";
								document.getElementById("longitude").value = "";
								return;
							}

							// If the place has a geometry, then present it on a map.
							if (place.geometry.viewport) {
								map.fitBounds(place.geometry.viewport);
							} else {
								map.setCenter(place.geometry.location);
								map.setZoom(17); // Why 17? Because it looks good.
							}
							marker.setPosition(place.geometry.location);
							marker.setVisible(true);
							//current lat/lon when autocomlete put marker
							document.getElementById("lattitude").value = marker.position.lat();
							document.getElementById("longitude").value = marker.position.lng();
							$("#arror_msg .error").hide();
							//console.log(marker.position.lng());
							//codeLatLng();
						});
		

	}
		
