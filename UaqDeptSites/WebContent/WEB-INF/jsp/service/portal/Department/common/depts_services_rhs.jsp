<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

				<div class="callout-wrap">
					<div class="callout-content">
						<div class="department-lists">
							<div class="callout-head">
								<h5 class="right-nav-title right-nav-gray-title">
									<spring:message code="label.departments" />
								</h5>
							</div>
							<div class="custom-select-box">
							   <select id="lad-departments">
									<option value="">
										<spring:message code="option.select" />
									</option>
								<select>
								
							</div>
						</div>
					</div>
				</div>
				<!-- callout -->
				<div class="callout-wrap" id="srvice-rhs" style="display:none">
					<div class="callout-head">
						<h5 class="right-nav-title">SERVICES</h5>
					</div>
					<div class="callout-content">
						<div class="right-nav">
						   <ul id="right-service-list">
							   
						   </ul>
					   </div>
					</div>	
				</div>
			<!-- /callout -->
			<script>
				jQuery(function($) { 
				
				var departments = ${departmentServicesJSON};
				var departmentotion ="<spring:message code="label.departments" />";
				var servicelist ="";
				$.each(departments, function(i,k){
					if(i!==" "){
						departmentotion += "<option vla='"+ i+"'>" + i+ "</option>";
					}
				});
				
				
				$("#lad-departments").html(departmentotion );
				$("#lad-departments").change(function(){
					cbangedval= $(this).val();
					servicelist="";
					$("#srvice-rhs").show();
					//alert(cbangedval);
					console.log(departments[cbangedval]);
					$.each(departments[cbangedval], function(i,k){
						if(k.externalLink !="" && k.serviceEnabled =="True" ){
						servicelist +="<li><a href='"+k.externalLink+"'>"+k.title+"</a></li>";
						}else{
							
							servicelist +="<li><a href='comingsoon.html'>"+k.title+"</a></li>";

						}
						
					});
					//console.log(servicelist);
					
					$("#right-service-list").html(servicelist);
				});});
			</script>
				
