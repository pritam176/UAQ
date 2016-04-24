<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
<!--
-->
.fullscreen-bg {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	overflow: hidden;
	z-index: -100;
}

.fullscreen-bg__video {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.textContainer {
	height: 430px;
	line-height: 400px;
}

.textContainer h2 {
	vertical-align: middle;
	display: inline-block;
}
</style>
<div class="pagecontent pagecontent">
	<div class="container-fluid"
		<%-- style="background-image: url('<c:url value="/resources/img/wood.jpg"/>');     background-size: 100% 100%;
    background-repeat: no-repeat;  height: 500px;" --%> style="background-color:#f2f2f2;">

		<div class="row row-edge">
			<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
				<img src="<c:url value="/resources/img/logos/bff2.png"/>"
					height="334" width="400">
			</div>
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7 column_style_2">
				<div class="row homeright">
					
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><h3 class="text-sub">For many years, Plumbing Co. has been making sustainable progress possible and driving positive change on every continent. With 2014 sales and revenues of $56 billion, Plumbing Co. is the world's leading manufacturer of various products. The company principally operates through its three product segments.</h3></div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12"><p class="text-home">Sit Back and Relax <br> We are here - we 'll fix it !!</p><a href="${pageContext.request.contextPath}/requestForm.html"
						class="page-scroll btn btn-xl">CLICK TO FIX</a></div>
				


				</div>


			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 home_contact">
			
			<div class="btn btn-xl">056 7396576</div></div>
		
		</div>


	</div>


</div>

