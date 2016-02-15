<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="sliderHolder fill Heroslider home">
	<ul class="no-list _slider slider fill">
			<c:if test="${not empty homeVO.carouselVideos}">
				<li id="test" class="item fill"
					style="background-image: url('/img/uaqbg.jpg');">
					<div id="playhomevid" class="bx-next1 btn-ply" style="display:none;"></div> 
					<video id="vid1" class="videoContainer hidden-xs " loop
						style="width: 100%; z-index: 3;" muted="true" preload="auto"
						autoplay="true">
						<source src="${homeVO.carouselVideos}" type="video/mp4" />
						<source src="/img/video.webm" type="video/webm" />
						<source src="/img/Ummal-Qaiwain.ogv" type="video/ogg">
					</video></li>
		</c:if>
		<c:if test="${not empty homeVO.carouselImages}">
			<c:forEach var="carouselImage" items="${homeVO.carouselImages}"
				varStatus="carouselCount">
				<c:set var="sliderclass" scope="page">background-image:url('${carouselImage}');</c:set>
				<li class="item fill" style="${sliderclass}">
					<div class="container"></div>
				</li>
			</c:forEach>
		</c:if>

	</ul>
</div>
<!-- home page slider ends here -->