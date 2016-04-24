 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="${pageContext.request.contextPath}/home.html">
                <img class="logo" src="<c:url value="/resources/img/logos/bff.png" /> ">
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="${pageContext.request.contextPath}/services.html">Services</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="${pageContext.request.contextPath}/portfolio.html">Portfolio</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="${pageContext.request.contextPath}/about.html">About</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="${pageContext.request.contextPath}/gallery.html">Gallery</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="${pageContext.request.contextPath}/contact.html">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>