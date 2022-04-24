<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Home</title>
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">


        <!-- Theme Styles -->
        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            html {
                scroll-behavior: smooth;
            }
        </style>
    </head>
    <body>
        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <%@include file="sidebar.jsp" %>
            <div class="page-container">
                <%@include file="header.jsp"%>
                <div class="page-content">
                    <div class="main-wrapper" style=" padding-top: 0px; ">
                        <jsp:useBean id="sldb" class="dal.SliderDAO"/>
                        <div class="profile-content" id="slideshow">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="post"> 
                                                <div class="post-body">
                                                    <h1 class="display-4" style="display: none; margin-bottom: 3%;text-align: center;font-weight: 600;">SlideShow</h1>
                                                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                                        <c:set var="count" value="${0}"/>
                                                        <div class="carousel-inner">
                                                            <c:forEach items="${sldb.filter('',31)}" var="slide">
                                                                <c:if test="${count!=0}">
                                                                    <div class="carousel-item">
                                                                        <img class="d-block w-100" src="${slide.image}" alt="First slide" style="height: 80vh;">
                                                                        <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,0.5);">
                                                                            <a class="text-white" target="_blank" href="sliderdetail?id=${slide.id}" >${slide.title}<i class="material-icons-outlined"> link </i></a>
                                                                            <p>${slide.note}</p>
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${count==0}">
                                                                    <div class="carousel-item active">
                                                                        <img class="d-block w-100" src="${slide.image}" alt="First slide" style="height: 80vh;">
                                                                        <div class="carousel-caption d-none d-md-block" style="background-color: rgba(0,0,0,0.5);">
                                                                            <a class="text-white" target="_blank" href="sliderdetail?id=${slide.id}" >${slide.title}<i class="material-icons-outlined"> link </i></a> 
                                                                            <p>${slide.note}</p>
                                                                        </div>
                                                                    </div>
                                                                    ${count = count+1}
                                                                </c:if>
                                                            </c:forEach>

                                                        </div>
                                                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                            <span class="sr-only">Previous</span>
                                                        </a>
                                                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                            <span class="sr-only">Next</span>
                                                        </a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                        <jsp:useBean id="post" class="dal.PostDAO"/>
                        <div class="profile-content" id="blog">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="post"> 
                                                <div class="post-body">
                                                    <h1 class="display-4" style="margin-bottom: 3%;text-align: center;font-weight: 600;">Blog</h1>
                                                    <div class="inside" style="display: flex;align-items: center;">
                                                        <div class="row">
                                                            <c:forEach items="${post.getLastFeaturedN(3)}" var="blog">
                                                                <div class="col-lg-4 d-flex align-items-stretch">
                                                                    <div class="card" style="width: 100%;">
                                                                        <div class="card-body">
                                                                            <h5 class="card-title">${blog.fullName}<span class="card-title-helper">${blog.realDate}</span></h5>
                                                                            <div class="savings-stats">
                                                                                <a href="blogdetail?id=${blog.id}"><h5>${blog.title}</h5></a>
                                                                                <span>${blog.brief}</span>
                                                                            </div>
                                                                            <a href="blogdetail?id=${blog.id}">
                                                                                <img src="${blog.thumbnail}" style=" width: 90%; margin: 5%; ">
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                </div>                      
                            </div>                           
                        </div>
                    </div>
                    <%@include file="footer.jsp" %>
                </div>
            </div>

            <!-- Javascripts -->
            <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
            <script src="assets/plugins/bootstrap/popper.min.js"></script>
            <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
            <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
            <script src="assets/plugins/jquery-sparkline/jquery.sparkline.min.js"></script>
            <script src="assets/plugins/apexcharts/dist/apexcharts.min.js"></script>
            <script src="assets/plugins/blockui/jquery.blockUI.js"></script>
            <script src="assets/plugins/flot/jquery.flot.min.js"></script>
            <script src="assets/plugins/flot/jquery.flot.time.min.js"></script>
            <script src="assets/plugins/flot/jquery.flot.symbol.min.js"></script>
            <script src="assets/plugins/flot/jquery.flot.resize.min.js"></script>
            <script src="assets/plugins/flot/jquery.flot.tooltip.min.js"></script>
            <script src="assets/js/connect.min.js"></script>
            <script src="assets/js/pages/dashboard.js"></script>
    </body>
</html>