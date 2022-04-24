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
        <title>Post Detail</title>
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
                                <li class="breadcrumb-item active" aria-current="page">Blogs List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">

                            <div class="card-body">
                                <div class="card card-horizontal">
                                    <div class="card text-center">
                                        <form action="udpost" method="post" enctype="multipart/form-data">
                                            <div class="btn btn-primary btn-lg btn-block" style=" line-height: initial; font-size: 2em; padding: 2%; ">
                                                Edit Post
                                            </div>
                                            <div class="card-body">
                                                <h5 class="card-title">
                                                    Title:  &nbsp; &nbsp; <input required="" type="text" style=" font-size: 2em; border: 2px solid #e8e8e8!important; 
                                                           border-radius: 25px!important; margin-right: 2%; text-align: center; margin-bottom: 2%; width: 50%;
                                                           padding: 8px; " name="title" placeholder="Title" value="${post.title}"> <br>
                                                    <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                    By <a href="author.html">${post.fullName}</a> /
                                                    In 
                                                    <select name="category">
                                                        <optgroup label="category">
                                                            <c:forEach items="${stdb.getAllByType('post category')}" var="category">
                                                                <option ${post.cateId==category.id?"selected":""} value="${category.id}">${category.value}</option>
                                                            </c:forEach>
                                                    </select>
                                                    / 
                                                    <input required="" class="search" type="date" name="date" style="background: transparent; 
                                                           font-weight: 900;color: #9c9c9c; border: none; text-align: center;" value="${post.date}">
                                                    / 5 min read <input style="display: none;" type="number" value="${param.id}" name="id">
                                                </h5>
                                                <h5>
                                                    Status: &nbsp;
                                                    <select name="status"> 
                                                        <c:forEach items="${stdb.getAllByType('post status')}" var="status">
                                                            <option ${post.status==status.value?"selected":""} value="${status.id}">${status.value}</option>
                                                        </c:forEach>
                                                    </select>  &nbsp; &nbsp; &nbsp;
                                                    Featured: &nbsp;
                                                    <input type="radio" value="1" name="featured" required="" ${post.flag==1?'checked':''}> ON / <input type="radio" value="0" name="featured" ${post.flag==0?'checked':''}> OFF
                                                </h5>
                                                <img id="pic" accesskey=""src="${post.thumbnail}" style="width: 80%;margin: auto;"/>
                                                <input onchange="loadFile(event)" type="file"  accept="imgs/*" value="${post.thumbnail}" name="img"
                                                       style="color: #9c9c9c;background: transparent;text-align: center;width: 90%;margin: 5%;border: 2px solid #4e4e4e;border-radius: 50px;">                                              
                                                <div>
                                                    <h3>Content</h3>
                                                    <textarea id="editor" onchange="myFunction()" required=""
                                                              style="color: #9c9c9c;background: transparent; padding: 1% 5%;height: 50vh;width: 90%;margin: 5%;border: 2px solid #4e4e4e;border-radius: 10px;" name="content">
                                                        ${post.content}
                                                    </textarea>
                                                </div>
                                                <div>
                                                    <h3 style=" margin-top: 5%; ">Brief</h3>
                                                    <textarea id="editor1" onchange="myFunction1()" 
                                                              style="color: #9c9c9c;background: transparent; padding: 1% 5%;height: 50vh;width: 90%;margin: 5%;border: 2px solid #4e4e4e;border-radius: 10px;" name="brief">
                                                        ${post.brief}
                                                    </textarea>
                                                </div>
                                                <input type="submit" class="btn btn-primary" value="Save" style=" margin-top: 5%; "/>
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
