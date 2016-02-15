<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Waste Container</title>
<style type="text/css">
#form{
width:1024px;
}
#address{
width: 100%;
}
#map-canvas{
width: 100%;
height: 300px;
border: thin;
    
    border-color: black;
    border-style: double;
}#mainTable{
width: 100%;
}
#mainTable tr td:first-child { 
width: 30%;
padding-right: 2%;
 }
 

#searchLocation{
 background-color: #fff;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 50%;
	}
#searchLocation:focus {
        border-color: #4d90fe;
      }
.controls {
        margin-top: 16px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }
      .error{ color: #ff0000;
    font-style: italic;
    font-weight: bold;
      }
</style>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
	function initialize() {
		
		
		var mapOptions = {
				center : new google.maps.LatLng(-33.8688, 151.2195),
				zoom : 13
			};
		 var map = new google.maps.Map(document.getElementById('map-canvas'),
					mapOptions);

		var input = /** @type {HTMLInputElement} */
		(document.getElementById('searchLocation'));

		var types = document.getElementById('type-selector');
		//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
		// map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);

		var autocomplete = new google.maps.places.Autocomplete(input);
		autocomplete.bindTo('bounds', map);

		var infowindow = new google.maps.InfoWindow();
		
		//var place = autocomplete.getPlace();
		 
		
		
		var marker = new google.maps.Marker({
			map : map,

			draggable : true,
			anchorPoint : new google.maps.Point(0, -29)
		});
		google.maps.event.addListener(map, 'click', function(event) {
			console.log(event.latLng);
			marker.setPosition(event.latLng);
			marker.setVisible(true);
		document.getElementById("lattitude").value = event.latLng.lat();
		document.getElementById("longitude").value = event.latLng.lng();
				
			   codeLatLng();
		});
		google.maps.event.addListener(marker, 'dragend', function(event) {
			document.getElementById("lattitude").value = event.latLng.lat();
			document.getElementById("longitude").value = event.latLng.lng();
			lat = event.latLng.lat();
			lng = event.latLng.lng();
			codeLatLng();
		});
		
		//browser location
		navigator.geolocation.getCurrentPosition(function(position) {
		      initialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
		      map.setCenter(initialLocation);
		    }, function() {
		      
		    });
		
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
								window
										.alert("Autocomplete's returned place contains no geometry");
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
							document.getElementById("lattitude").value = marker.position
									.lat();
							document.getElementById("longitude").value = marker.position
									.lng();
							//console.log(place);
							codeLatLng();
						});
		

	}
	function codeLatLng() {
		var lat = document.getElementById('lattitude').value;
		var lng = document.getElementById('longitude').value;
		var latlng = new google.maps.LatLng(lat, lng);
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode({
			'location' : latlng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[1]) {
					console.log(results[1].formatted_address);
					document.getElementById("address").value = results[1].formatted_address;
				} else {
					document.getElementById("address").value = "";
				}
			} else {
				document.getElementById("address").value = "";
				window.alert('Geocoder failed due to: ' + status);
			}
		});
	}
	/* function placeMarker(location) {
	    var marker = new google.maps.Marker({
	        position: location, 
	        map: map
	    });
	}
 */	google.maps.event.addDomListener(window, 'load', initialize);

	$(document).ready(function() {
		 $('#addFile').click(function() {
                     var fileIndex = $('#fileTable tr').length;
                     console.log(fileIndex)
                     $('#fileTable').append('<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>'
                                             + '   <input type="file" name="files['+ fileIndex +']" />'
                                             + '</td></tr>');
                 });
	});
</script>
</head>
<body>
	
	<div id="body">
		<div id="form">
			<form:form method="post" modelAttribute="WasteContainerBean"
				commandName="WasteContainerBean"
				action="getWasteContainerForIndividualSubmit.html"
				enctype="multipart/form-data">
				<table id="mainTable">
					<tr id="">
						<td><span>Search Location</span></td>
						<td><form:input path="searchLocation"  /> <form:errors
								path="searchLocation" /></td>
					</tr>
					<tr>
						<td><label>Location</label></td>
						<td><div id="map-canvas"></div></td>
					<tr>
						<td><label>Address</label></td>
						<td><form:input path="address" type="text" id="address"  /><td/>
						 <td>	<form:errors path="address" class="error" /></td>
					</tr>
					<form:hidden path="longitude" id="longitude" />
					<form:hidden path="lattitude" id="lattitude" />
					<tr>
						<td><label>ServiceType</label></td>
						<td>	<form:radiobutton path="serviceType" 	value="newContainer" /> <label>New Container Location</label>
								<form:radiobutton path="serviceType" 	value="replacement" /> <label>Replacement</label>
								<label>Replacement Reason</label> 
								<form:input path="replacementReason" 	type="text" /> <form:errors path="replacementReason" /></td>
					</tr>
					<tr><td><label>Attachments</label></td><td>
					<table id="fileTable">
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td><input type="file" name="files[0]" /> 
					</tr>
					
					
				</table>
				</td>
				<tr><td>&nbsp;&nbsp;</td><td><input id="addFile" type="button" value="Add File" /></td>
				<tr><td>&nbsp;&nbsp;</td><td><input type=submit value="submit" /></td>
				
				</tr>
				</table>
			</form:form>
		</div>

	</div>

</body>
</html>