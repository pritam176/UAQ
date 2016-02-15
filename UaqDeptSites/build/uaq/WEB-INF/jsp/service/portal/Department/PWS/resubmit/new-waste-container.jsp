 <%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
		<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
		
		<c:set var ="deptlblattachmntmsg"><spring:message code="plz.dept.lbl.attachmnt"/></c:set>
		<c:set var ="plzdeptlblrplmntrsnmsg"><spring:message code="plz.dept.lbl.rplmntrsn"/></c:set>
		<c:set var ="plzdeptlbladdresmsg"><spring:message code="plz.dept.lbl.addres"/></c:set>
		<c:set var ="choosemapmsg"><spring:message code="choosemap"/></c:set>
		
		<c:set var ="filesize"><spring:message code="filesizemsg"/></c:set>
		<c:set var ="fileextention"><spring:message code="fileextentionmsg"/></c:set>
		<c:set var ="fileerror"><spring:message code="fileerrormsg"/></c:set>

		<c:set var ="placeholder"><spring:message code="placeholdermsg"/></c:set>
		
<div class="container-fluid">
			<div class="wrapper">
				<!-- content area -->
				<div class="mainmenu">
					<div class="col-md-12 hidden-xs hidden-sm">
						<div class="mainmenu-wrap">
							<ul class="no-list cf">
								<li>
									<a href="/${param.languageCode}/home.html"><img src="/img/home1.png" alt="Home UAQ">
									</a>
								</li>
								<li class="active"><a href="#"><spring:message code="dept.lbl.pws.wastcont" /></a>
								</li>
								
							</ul>
						</div>
					</div>
				</div>
				<!--/mainmenu -->				
				<div class="content">
					<div class="row">
					
					<!-- right col -->
					
					<!-- right col -->
			
						<div class="col-xs-12 col-sm-9 col-md-9 main-left-col">
							 <!-- page title here -->
		                        <div class="page-title-wrap">
									<div class="row">
										<div class="col-xs-12 col-sm-9 col-md-10 title-txt">
											
											<h2 class="page-title">
												<spring:message code="dept.lbl.pws.wastcont" />
												<spring:message code="label.resubmit"/>
											</h2>
											<!-- /page title -->
										</div>
										<div class="hidden-xs show-sm col-md-2 title-icon">
											<img src="/img/icons/icon-uaq.png" alt="uaq">
										</div>
									</div>
								</div>
                        	<!-- /page title here -->
						<div class="main-content-wrap">
								<div class="page-content-wrap">
										<div class="form-content cf">
											<form:form commandName="wasteContainerCommand"     id="new_waist_container_form" enctype="multipart/form-data" action="resubmitwastecontainer.html" method="POST"> 
											
												<!-- Request Detail -->	
												
											
										
										<form:hidden path="wasteCOntainoerId" id="wasteCOntainoerId" value=""/>
										<form:hidden path="requestNo" id="requestNo" value=""/>
										<form:hidden path="serviceId" id="serviceId" value=""/>
										<form:hidden path="sorceType" id="sorceType" value=""/>
										<form:hidden path="status" id="status" value=""/>
										
												
												
												
												
												
											<!-- map search element -->		
												
												
												
												
												
												
												
												<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label for="search_location" class="form-lbl"><spring:message code="dept.lbl.pws.findlocation" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                    <form:input type="text" path="findLocation" class="form-control" id="search_location" placeholder="${placeholder}"  ></form:input>
		                                                   
		                                                </div>
	                                            	</div>
	                                        	</div>
	                                        	<!-- /map search element -->

	                                        	<!-- location map -->
												<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl mandatory_lbl"><spring:message code="dept.lbl.pws.location" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                    <div id="map-canvas" class="location-map"></div>
															<div id="arror_msg">													
															<form:hidden path="longitude" value="" id="longitude" data-msg-required="${choosemapmsg}" />
															<form:hidden path="latitude" value="" id="lattitude" data-msg-required="${choosemapmsg}"/>
															</div>
		                                                </div>
	                                            	</div>
	                                        	</div>
	                                        	<!-- /location map -->

	                                        	<!-- address element -->
												<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl mandatory_lbl"><spring:message code="label.address" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                    <form:input type="text" path="address" class="form-control required"  name="address" data-msg-required="${plzdeptlbladdresmsg}" />
		                                                </div>
	                                            	</div>
	                                        	</div>
	                                        	<!-- /address element -->

	                                        	<!-- service type -->
												<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl"><spring:message code="dept.lbl.pws.servicetyp" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
			                                            	<!-- radio button -->
			                                            	<div class="col-md-3 remove-pad cf">
			                                                    <div class="radio inline-custom">
		                                                            <form:radiobutton id="new_container"  path="serviceType" value="New Container" checked="checked"></form:radiobutton>
		                                                            <label for="new_container" class="custom"><spring:message code="dept.lbl.pws.newCont" /></label>
		                                                        </div>
															</div>
															<!-- /radio button -->
															<!-- radio button -->
															<div class="col-md-3 remove-pad cf">
		                                                        <div class="radio inline-custom">
		                                                            <form:radiobutton id="replacement"  path="serviceType" value="replacement"></form:radiobutton>
		                                                            <label for="replacement" class="custom"><spring:message code="dept.lbl.pws.rplmnt" /></label>
		                                                        </div>
		                                                    </div>
															<!-- /radio button -->
															<!-- text box -->
		                                                    <div class="col-md-9 remove-pad">
		                                                    	<div class="col-md-4 remove-pad">
				                                                    <label class="form-lbl replacement-lbl"><spring:message code="dept.lbl.pws.rplmntrsn" /></label>
					                                            </div>
					                                            <div class="col-md-8 remove-pad">
				                                                    <form:input type="text" path="replacement"  id="reason_replacement" class="form-control "  name="reason" readonly="true" data-msg-required="${plzdeptlblrplmntrsnmsg}" />
				                                                </div>
		                                                    </div>
		                                                    <!-- /text box -->
		                                                </div>
	                                            	</div>
	                                        	</div>
	                                        	<!-- /address element -->

	                                        	<!-- attachments element -->
												<!-- attachments element -->
												<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl" for="files1"><spring:message code="dept.lbl.pws.attch" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                    <div class="input-group file-upload">
		                                                        <input type="text" name="files1" class="form-control" readonly data-msg-required="${deptlblattachmntmsg}" />
	                                                        	<span class="input-group-btn">
		                                                			<span class="btn btn-file">
		                                                				<spring:message code="file.browse"/>&hellip; 
		                                                				<input type="file" name="files[0]" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" id="wase_container_file_0" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" />
																	</span>
																</span>
																<form:hidden  path="wase_container_file_0_name" id="wase_container_file_0_name" value=""/>
																		
		                                                    </div>
		                                                </div>
	                                            	</div>
	                                        	</div>
	                                        	<c:if test="${status =='Failed'}">
	                                        	<div><span class="error"> ${deptlblattachmntmsg}</span></div>
	                                        	</c:if>
	                                        	<!-- /attachments element -->

	                                        	<div class="row add-more-attachments">
													<div class="col-md-12 remove-pad">
														<div class="col-md-12 remove-pad">
															<!-- text box -->
															<div class="form-group cf">
																<div class="col-md-3">
																</div>
																<div class="col-md-9">
																	<label for="addmore-file" class="form-lbl "><a href="#" id="addMorefile"> <spring:message code="dept.lbl.addmorefile" /> </a></label>
																</div>
															</div>
															<!-- /text box -->
														</div>
													</div>
												</div>
<!-- ----- uploaded file area starts here --->
											
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl form-lbl-text"><spring:message code="label.service.uploadedfiles" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                   <c:forEach items="${attachmentPayLoad}" var="attachments">
																	
		                                                    		<c:if test="${attachments.docType=='Taken Picture'}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.url}" target="_blank" alt="${attachments.fileName}" title="${attachments.fileName}"> ${attachments.fileName}</a> <!-- please update link and file name/some name -->
																	</div>
																	</c:if>
															</c:forEach>
															
		                                                </div>
	                                            	</div>
	                                        </div>
											<!-- ----- uploaded file area ends   here --->
											<!-- reviewer atttached file start here -->
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl form-lbl-text"><spring:message code="pws.resubmitattach.reviewer" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
														
		                                                   <c:forEach items="${attachmentPayLoad}" var="attachments">
																	<c:if test="${attachments.docType=='Reviewer Supportive Attachment'}">
																	<div class="col-md-12 remove-pad attachment-btm">
																		<a href="${attachments.url}" target="_blank" alt="${attachments.fileName}" title="${attachments.fileName}"> ${attachments.fileName}</a> <!-- please update link and file name/some name -->
																	</div>
																	</c:if>
															</c:forEach>
		                                                </div>
	                                            	</div>
	                                        </div>
											<!-- reviewer atttached file ends  here -->
											
											
											
											
											
											
											<!-- Comment Section -->
											<div class="row">
	                                            	<div class="form-group cf">
		                                                <div class="col-md-3">
		                                                    <label class="form-lbl form-lbl-text"><spring:message code="label.service.optionalcomments" />
		                                                    </label>
			                                            </div>
			                                            <div class="col-md-9">
		                                                    <p>${reviewerComments}</p>
		                                                </div>
	                                            	</div>
	                                        </div>
										<!--/ Comment Section -->
												<!-- submit button -->
	                                        	<div class="row">
		                                            <div class="form-group">
		                                                <div class="col-md-offset-3 col-md-9">
		                                                <c:if test="${status ne '6' }">
		                                                    <input type="submit" class="btn form-control-btnsbmt" value='<spring:message code="label.eservice.resubmit"/>' />
		                                                 </c:if>  
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <!-- /submit button -->
	                                    	</form:form>
										</div>
								</div>
							</div>
						</div>
						<!-- /left col -->
					</div>
				</div>
				<!-- /content area -->
			</div>
		</div>
	
   <!--  <script src="/js/dest/app.js"></script> -->
    <script src="/js/libs/jquery.validate.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
	<script type="text/javascript" src="https://www.dropbox.com/static/api/2/dropins.js" id="dropboxjs" data-app-key="zsevgpc8j0wf8j7"></script>
   <script type="text/javascript">
   
   jQuery(function($) { 
	   $("#new_waist_container_form").validate(
		   {
			ignore: "",   
			rules: {
				longitude: {
					required: true
					
				},
			},
		}
	   
	   
	   ); 
	   
	  
	   
	   $('#new_waist_container_form').on('keyup keypress', function(e) {
			  var code = e.keyCode || e.which;
			  if (code == 13) { 
				e.preventDefault();
				return false;
			  }
		});
	   
	   
	   });
   
  
   function doShowCamera() {
		photoNumber++;
		Ti.App.fireEvent('choosePicture', {
			filename : photoNumber + ".png"
		});
		(".imgeplaceholder").html(filename);
	}
	
options = {
	// Required. Called when a user selects an item in the Chooser.
	success: function(files) {
		//alert("Here's the file link: " + files[0].link)
		//$("#dropboxlink").html(files[0].link);
	},

	// Optional. Called when the user closes the dialog without selecting a file
	// and does not include any parameters.
	cancel: function() {

	},

	// Optional. "preview" (default) is a preview link to the document for sharing,
	// "direct" is an expiring link to download the contents of the file. For more
	// information about link types, see Link types below.
	linkType: "direct", // or "direct/preview"

	// Optional. A value of false (default) limits selection to a single file, while
	// true enables multiple file selection.
	multiselect: false, // or true

	// Optional. This is a list of file extensions. If specified, the user will
	// only be able to select files with these extensions. You may also specify
	// file types, such as "video" or "images" in the list. For more information,
	// see File types below. By default, all extensions are allowed.
	extensions: ['.png', '.jpg', '.jpeg'],
};

function initialize() {
	
	var longitide=parseFloat($("#longitude").val())
	var latitide=parseFloat($("#lattitude").val())
	var myLatLng = {lat:latitide, lng: longitide};
	var mapOptions = {
			center : new google.maps.LatLng(myLatLng),
			zoom : 13
		};
	var map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);

	var input = /** @type {HTMLInputElement} */
	(document.getElementById('search_location'));

	var types = document.getElementById('type-selector');
	//map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	// map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);
	var localityOptions = {
			types: ['geocode'], 
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
		position: myLatLng,
		draggable : true,
		anchorPoint : new google.maps.Point(0, -29)
	});
	
	var map;
var marker;
var infowindowPhoto = new google.maps.InfoWindow();
var latPosition;
var longPosition;

google.maps.event.addListener(marker, 'click', function (event) {
    updatePosition();
});

google.maps.event.addListener(marker, 'dragend', function (event) {
    updatePosition();
});

   /*  if (navigator.geolocation) {
        
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
    } */


function updatePosition() {

    latPosition = marker.getPosition().lat();
    longPosition = marker.getPosition().lng();

    //contentString = '<div id="iwContent">Lat: <span id="latbox">' + latPosition + '</span><br />Lng: <span id="lngbox">' + longPosition + '</span></div>';
    
    document.getElementById('lattitude').value = latPosition;
    document.getElementById('longitude').value = longPosition;
    
    //infowindowPhoto.setContent(contentString);
    //infowindowPhoto.open(map, marker);
}

	google.maps.event.addListener(map, 'click', function(event) {
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
	/*
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
	google.maps.event.addDomListener(window, 'load', initialize);
		
	var selected1=document.querySelector('input[name="serviceType"]:checked').value;
	
	if(selected1 =="replacement"){
		$("#reason_replacement").addClass("required");
		$(".replacement-lbl").addClass("mandatory_lbl");
		$("#reason_replacement").attr("readonly", false);
		//$("label[for='files1']").addClass("mandatory_lbl");
	 
	 }else{
		//$("#reason_replacement").val("");
		$("#reason_replacement").removeClass("required");
		$(".replacement-lbl").removeClass("mandatory_lbl");
		$("#reason_replacement").attr("readonly", true);
		//$("label[for='files1']").removeClass("mandatory_lbl");
	 
	 }
		
		$("input[name='serviceType']").click(function(){
			 if($(this).val()=="replacement"){
			 
			    $("#reason_replacement").val("");
				$("#reason_replacement").addClass("required");
				$("#reason_replacement").attr("readonly", false);
				$("label[for='files1']").addClass("mandatory_lbl");
				$(".replacement-lbl").addClass("mandatory_lbl");
				
			 }else{
				$("#reason_replacement").val("");
				$("#reason_replacement").removeClass("required");
				$("#reason_replacement").attr("readonly", true);
				$("label[for='files1']").removeClass("mandatory_lbl");
				$(".replacement-lbl").removeClass("mandatory_lbl");
			 
			 }
			})
			/* add more files ---------------------------   */
			var certificateId = 1;
			var attach_arr = new Array();
			
		   $("#addMorefile").click(function(event){
					event.preventDefault();
					if(attach_arr.length<2){
	
					certihtml = '<div class="row certificate-last-row" id="attachid_'+certificateId+'" ><div class="col-md-12 remove-pad"><div class="col-md-12 remove-pad"><div class="form-group cf"><div class="col-md-3"><label for="file_attach_'+certificateId+'" class="form-lbl"> <spring:message code="dept.lbl.pws.attch" />  </label></div><div class="col-md-9"><div class="input-group file-upload"><input type="text" class="form-control"  name="file_attach_'+certificateId+'" data-msg-required="${deptlblattachmntmsg}" id ="file_attach_'+certificateId+'"  /><span class="input-group-btn"><span class="btn btn-file"><spring:message code="file.browse"/>&hellip; <input type="file" name="files['+certificateId+']" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" id="wase_container_file_'+certificateId+'" error-size="${filesize}" error-extention="${fileextention}" error-failed="${fileerror}" ></span></span><span class="glyphicon glyphicon-remove removeico" data-remove="attachid_'+certificateId+'"></span><input type="hidden" name="wase_container_file_'+certificateId+'_name" id="wase_container_file_'+certificateId+'_name" value=""/></div></div></div></div></div></div>';
					//alert("certihtml"+certihtml);
					
					
					$(".add-more-attachments").before(certihtml);
					
					$('#file_attach_'+certificateId).rules('add', {
						//required: true,
					});
					attach_arr.push(certificateId);
					certificateId=certificateId+1;
					if( attach_arr.length>=2){
						$("#addMorefile").hide();
					}else{
						$("#addMorefile").show();
					}
					
					
				}
			
			});
		
			$(".form-content").on("click", ".removeico", function(){
				removeId= $(this).attr("data-remove");
				$("#"+removeId).remove();
				$("#addMorefile").show();
				attach_arr.pop(removeId.split("_")[1]);
				
				
			});
			
			/* /add more files ==========================  */
		</script>