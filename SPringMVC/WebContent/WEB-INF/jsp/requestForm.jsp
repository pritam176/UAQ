<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<script src="<c:url value="/resources/js/jquery.validate.js"/>"></script>
<script src="<c:url value="/resources/js/customevalidation.js"/>"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
<%-- <script src="<c:url value="/resources/js/map.js"/>"></script> --%>

<script>
	var ctx = "${pageContext.request.contextPath}"
</script>
<div class="container pagecontent">

	<form:form role="form" commandName="requestFormCommand" name="feedbak"
		id="requestForm" method="post" action="submitRequestForm.html"
		cssClass="form-horizontal" enctype="multipart/form-data">


		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl mandatory_lbl">Product
					Type </label>
			</div>

			<div class="col-xs-10 custom-select-box cf">
				<form:select path="prouducttype" class="form-control required"
					id="prouducttype" name="sector1">
					<form:option value="">
						<spring:message code="option.select" />
					</form:option>
					<form:options items="${productList}" />
				</form:select>
			</div>
		</div>





		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl mandatory_lbl">Product
					Sub Type </label>
			</div>

			<div class="col-xs-10 custom-select-box cf">
				<form:select path="prouductSubtype" class="form-control required"
					id="prouductSubtype" name="sector1">
					<form:option value="">
						<spring:message code="option.select" />
					</form:option>
					<form:options items="${productSubTypeList}" />
				</form:select>
			</div>
		</div>


		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl ">Model No </label>
			</div>

			<div class="col-xs-10">
				<form:input path="modelno" class="form-control " />


			</div>
		</div>






		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl mandatory_lbl">Name </label>
			</div>

			<div class="col-xs-10">
				<form:input path="name" class="form-control required" />


			</div>
		</div>









		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl mandatory_lbl">Mobile
					No </label>
			</div>

			<div class="col-xs-10">
				<form:input path="mobileno" class="form-control required" />


			</div>
		</div>





		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl mandatory_lbl">Email </label>
			</div>

			<div class="col-xs-10">
				<form:input path="email" name="email" id="email"
					class="form-control required" />


			</div>
		</div>





		<div class="form-group">

			<div class="control-label col-xs-2">
				<label for="sector1" class="form-lbl ">Find Location </label>
			</div>

			<div class="col-xs-10">
				<form:input path="address.streetNo" name="streetNo" id="streetNo"
					class="form-control " />


			</div>
		</div>


		<div class="form-group">
			<div class="col-md-6">
				<div class="control-label col-xs-4">
					<label for="sector1" class="form-lbl mandatory_lbl">Chosse
						Location </label>
				</div>

				<div class="col-xs-8">
				<div id="map-canvas" class="location-map"></div>
					<form:hidden path="address.longitude" value="" id="longitude"
						class="required" />
					<form:hidden path="address.latitude" value="" id="lattitude"
						class="required" />
				</div>

			</div>

			<div class="col-md-6">
				<div class="form-group">

					<div class="control-label col-xs-2">
						<label for="sector1" class="form-lbl mandatory_lbl">Adddres
							1 </label>
					</div>

					<div class="col-xs-10">
						<form:input path="address.adress1" name="address" id="address"
							class="form-control required" />


					</div>
				</div>

				<div class="form-group">

					<div class="control-label col-xs-2">
						<label for="sector1" class="form-lbl ">Adddres 2 </label>
					</div>

					<div class="col-xs-10">
						<form:input path="address.adress2" name="address2" id="address2"
							class="form-control " />


					</div>
				</div>



				<div class="form-group">

					<div class="control-label col-xs-2">
						<label for="sector1" class="form-lbl ">PostBox </label>
					</div>

					<div class="col-xs-10">
						<form:input path="address.postbox" name="postbox" id="postbox"
							class="form-control " />


					</div>
				</div>

				<div class="form-group">

					<div class="control-label col-xs-2">
						<label for="sector1" class="form-lbl ">Land Mark </label>
					</div>

					<div class="col-xs-10">
						<form:input path="address.landMark" name="landMark" id="landMark"
							class="form-control " />


					</div>
				</div>


			</div>
		</div>






		<div class="form-group">
			<div class="col-md-6">
				<div class="form-group">
					<div class="control-label col-xs-4">
						<label for="sector1" class="form-lbl ">Product Description
						</label>
					</div>

					<div class="col-xs-8">
						<form:textarea path="description" class="form-control " rows="5"
							cols="30" />


					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="control-label col-xs-2">
					<label for="sector1" class="form-lbl ">Uplaod Description </label>
				</div>
				<div class="col-xs-10">

					<input name="descriptionFile" type="file" class="form-control "
						accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" />

				</div>

			</div>

		</div>



		<!--/ Comment Section -->
		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-10">


				<button type="submit" class="btn-default">Submit Request</button>

			</div>
		</div>

		<!-- /submit button -->


	</form:form>
</div>
<script type="text/javascript">
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
			componentRestrictions : {
				country : 'ARE'
			}
		};

		var autocomplete = new google.maps.places.Autocomplete(input,
				localityOptions);
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

			navigator.geolocation.getCurrentPosition(function(position) {

				var pos = new google.maps.LatLng(position.coords.latitude,
						position.coords.longitude);

				latPosition = position.coords.latitude;
				longPosition = position.coords.longitude;

				marker = new google.maps.Marker({
					position : pos,
					draggable : true,
					animation : google.maps.Animation.DROP,
					map : map
				});

				map.setCenter(pos);
				updatePosition();

				google.maps.event.addListener(marker, 'click', function(event) {
					updatePosition();
				});

				google.maps.event.addListener(marker, 'dragend',
						function(event) {
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

		google.maps.event
				.addListener(
						autocomplete,
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
								document.getElementById("lattitude").value = "";
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
							document.getElementById("lattitude").value = marker.position
									.lat();
							document.getElementById("longitude").value = marker.position
									.lng();
							$("#arror_msg .error").hide();
							//console.log(marker.position.lng());
							//codeLatLng();
						});

	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>