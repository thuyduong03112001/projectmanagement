<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Blogs List</title>
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">   

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
    </head>
    <body>

        <div class='loader'>
            <div class='spinner-grow text-primary' role='status'>
                <span class='sr-only'>Loading...</span>
            </div>
        </div>
        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <jsp:include page="sidebar.jsp"/>
            <div class="page-container">
                <jsp:include page="header.jsp"/>
                <div class="page-content">
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Blogs List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-lg-3">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="todo-sidebar">
                                            <div class="todo-new-task">
                                                <h5 class="btn btn-primary btn-block active" style=" margin-bottom: 10%; ">Filter</h5>
                                            </div>
                                            <div class="todo-search">
                                                <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                <form action="bloglist" method="post">
                                                    <div class="input-group"  style="padding: 3% 0!important;">
                                                        <input type="text" id="todo-search" value="${sessionScope.title}" name="title" class="form-control" placeholder="Title">
                                                    </div>

                                                    <div class="divider"></div>
                                                    <p class="btn btn-secondary btn-block active">Categories</p>
                                                    <div >
                                                        <c:forEach items="${stdb.getAllByType('post category')}" var="cate">
                                                            <div style="width: 50%; float: left;">
                                                                <input style="display: inline-block;"${sessionScope.category.contains(cate.id)?"checked":""} name="category" type="checkbox" value="${cate.id}">
                                                                <p style="display: inline-block;"> ${cate.value}</p>
                                                            </div>             
                                                        </c:forEach>
                                                    </div>

                                                    <div class="divider" style=" float: right; "></div>
                                                    <div class="input-group"  style="padding: 3% 0!important;">
                                                        <input type="text" id="todo-search" value="${sessionScope.brief}" name="brief" class="form-control" placeholder="Brief">
                                                    </div>
                                                    <div style="text-align: center; margin-top: 10%; ">
                                                        <input type="submit" class="btn btn-outline-success" value="GO"/>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-9">
                                <div class="card">
                                    <div class="card-body">
                                        <c:forEach items="${sessionScope.list}" var="post">
                                            <div class="card card-horizontal">
                                                <div class="card-body" style="display: flex;">

                                                    <div class="col-lg-6">  
                                                        <div>
                                                            <h6 class="alert-heading"><a href="blogdetail?id=${post.getId()}" style="font-size: 2em;">${post.title}</a></h6>
                                                            <p style="display: inline;">Category: </p><a href="category.html">${post.category}</a>
                                                            <pre style="white-space: pre-wrap; font-size: 1.5em; color: #9c9c9c;">${post.brief}</pre>
                                                            <hr>
                                                            <div style="display: inline;">
                                                                By <a href="author.html">${post.fullName}</a>
                                                            </div>
                                                            <div style="display: inline;">
                                                                / <a>${post.realDate}</a> /
                                                            </div>
                                                            <div style="display: inline;">5 min read</div>
                                                        </div>

                                                    </div>
                                                    <div class="col-lg-6">
                                                        <a class="card-featured-img" href="blogdetail?id=${post.getId()}"><img style=" width: 100%; " src="${post.thumbnail}"/></a>
                                                            <c:if test="${post.flag==1}">
                                                            <img src="imgs/featured-label.png" style="position: absolute;width: 40%;right: 3.3%;background: none;"/> 
                                                        </c:if> 
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <c:set var="page" value="${sessionScope.page}"/>
                                        <c:if test="${nothing!=null}">
                                            <img style=" width: 92.5%; " src="imgs/product_not_found.jpg"/>
                                        </c:if>
                                        <c:if test="${sessionScope.num>1}">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <li class="page-item"><a class="page-link" href="bloglist?page=${sessionScope.page==1?1:sessionScope.page - 1}">Previous</a></li>
                                                        <c:forEach begin="${1}" end="${sessionScope.num}" var="i">
                                                        <li class="page-item ${page==i?"active":""}"><a class="page-link" href="bloglist?page=${i}">${i}</a></li>
                                                        </c:forEach>
                                                    <li class="page-item"><a class="page-link" href="bloglist?page=${sessionScope.page==sessionScope.num?sessionScope.num:sessionScope.page + 1}">Next</a></li>
                                                </ul>
                                            </nav>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp"%>
            </div>
        </div>

        <div class="mailbox-compose-overlay"></div>
        <div class="mailbox-item-overlay"></div>

        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
        <script src="assets/js/pages/todo.js"></script>
        <script src="assets/js/pages/select2.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/select2/js/select2.full.min.js"></script>
    </body>
</html>
