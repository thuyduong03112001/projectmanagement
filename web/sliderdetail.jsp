<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Slider Detail</title>
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
        <script src="ckeditor/ckeditor.js"></script>
        <script src="js/twock.js"></script>
        <style>
            form>div>h5>span {
                width: 20%!important;
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
                                <li class="breadcrumb-item active" aria-current="page">Slider Details</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">

                            <div class="card-body">
                                <div class="card card-horizontal">
                                    <div class="card text-center">
                                        <form action="udslider" method="post" enctype="multipart/form-data">
                                            <div class="btn btn-primary btn-lg btn-block" style=" line-height: initial; font-size: 2em; padding: 2%; ">
                                                <c:if test="${publicAcc==0}">Edit</c:if> Slider
                                                </div>
                                                <div class="card-body">
                                                    Title:  &nbsp; &nbsp; <input required="" ${publicAcc==1?"readonly":""} style=" font-size: 2em; border: 2px solid #e8e8e8!important; 
                                                                             border-radius: 25px!important; margin-right: 2%; text-align: center; margin-bottom: 2%; width: 50%;
                                                                             padding: 8px; "  type="text" name="title" placeholder="Title" value="${post.title}" >
                                                <h5 class="card-title" style=" margin-bottom: 0px; ">
                                                    <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                    <input style="display: none;" type="number" value="${param.id}" name="id">
                                                </h5>
                                                <c:if test="${publicAcc==0}">
                                                    <h5 style=" padding-bottom: 2%; ">
                                                        Slider: &nbsp;
                                                        <input type="radio" value="31" name="status" required="" ${post.statusId==31?'checked':''}> Show / <input type="radio" value="30" name="status" ${post.statusId==30?'checked':''}> Hide
                                                    </h5>
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <h3>Note</h3>
                                                            <textarea 
                                                                style="color: #9c9c9c;background: transparent; padding: 1% 5%;height: 50vh;width: 90%;margin: 5%;border: 2px solid #4e4e4e;border-radius: 10px;" name="note">
                                                                ${post.note}
                                                            </textarea>
                                                        </div>
                                                         <div class="col-lg-6">
                                                            <h3>Preview Picture</h3>
                                                            <img id="pic" accesskey=""src="${post.image}" style="width: 80%;margin: auto; margin: 5%;"/>

                                                            <input onchange="loadFile(event)" type="file"  accept="imgs/*" value="${post.image}" name="img"
                                                                   style="color: #9c9c9c;background: transparent;text-align: center;width: 80%;margin: 5%;border: 2px solid #4e4e4e;border-radius: 50px;">  
                                                        </div>
                                                    </div>
                                                    <input type="submit" class="btn btn-primary" value="Save" style=" margin-top: 5%; "/>
                                                </c:if>
                                                <c:if test="${publicAcc==1}">
                                                    <h5 style=" padding-bottom: 2%; ">
                                                        <c:if test="${publicAcc==1}">
                                                            Backlink: &nbsp;
                                                            <a href="${post.backlink}">${post.backlink}</a>
                                                        </c:if>
                                                    </h5>
                                                    <img id="pic" accesskey=""src="${post.image}" style="width: 80%;margin: auto;"/>
                                                    <div style=" margin-top: 2%; ">
                                                        <h3>Note</h3>
                                                        <c:if test="${publicAcc==1}">
                                                            <pre style="padding: 5% 10%;white-space: pre-wrap;font-size: 1.5em;text-align: justify; color: #5c6662;;">${post.note==null?"Nothing to show":post.note}</pre>
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </form>
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
        <script>
            var loadFile = function (event) {
                var image = document.getElementById('pic');
                image.src = URL.createObjectURL(event.target.files[0]);
            };
            initSample();
            function myFunction() {
                var ckValue = CKEDITOR.instances.editor.getData();
                document.getElementById("editor").value = ckValue;
            }
            function myFunction1() {
                var ckValue = CKEDITOR.instances.editor1.getData();
                document.getElementById("editor1").value = ckValue;
            }
        </script>
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
