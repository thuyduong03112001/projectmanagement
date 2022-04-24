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
        <title>Blog Detail</title>
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
        <style>
            form>span {
                width: 100%!important;
            }
        </style>
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
                            <div class="card-body">
                                <div class="card card-horizontal">
                                    <div class="card text-center">
                                        <div class="btn btn-primary btn-lg btn-block" style=" line-height: initial; font-size: 2em; padding: 2%; ">
                                            ${post.title}
                                        </div>
                                        <div class="card-body">
                                            <h5 class="card-title">                             
                                                By <a href="author.html">${post.fullName}</a> /
                                                In <a href="category.html">${post.category}</a> /
                                                ${post.realDate} / 5 min read
                                            </h5>
                                            <img src="${post.thumbnail}" style="width: 80%;margin: auto;"/>
                                            <pre style="padding: 5% 10%;white-space: pre-wrap;font-size: 1.5em;text-align: justify; color: #5c6662;;">${post.content}</pre>
                                        </div>
                                        <div class="card-footer text-muted">
                                            <jsp:useBean class="dal.PostDAO" id="RelatePost"/>
                                            <div style="width: 100%;display: inline-flex;justify-content: space-between;">
                                                <!-- PREVIOUS POST -->
                                                <div>
                                                    <div>
                                                        <a href="blogdetail?id=${param.id==1?param.id:param.id - 1}"><i class="fa fa-chevron-left"></i></a>&nbsp
                                                        <a href="blogdetail?id=${param.id==1?param.id:param.id - 1}">${RelatePost.getPostById(param.id - 1).title}</a>
                                                    </div>
                                                </div>
                                                <!-- NEXT POST -->
                                                <div>
                                                    <div >
                                                        <a href="blogdetail?id=${param.id==RelatePost.all.size()?param.id:param.id + 1}">${RelatePost.getPostById(param.id + 1).title}</a>&nbsp
                                                        <a href="blogdetail?id=${param.id==RelatePost.all.size()?param.id:param.id + 1}"><i class="fa fa-chevron-right"></i></a>
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
