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
        <title>Sliders List</title>
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
            form>div>div>span {
                width: 100%!important;
            }
            #zero-conf_wrapper>div:nth-child(odd) {
                display: none;
            }
            #zero-conf{
                text-align: center;
            }

            td {
                word-break: break-all;               
            }

            td span {
                display: block;
            }

            td>a, td>button {
                font-size: 0.8em!important;
                word-break: normal;
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
            <%@include file="sidebar.jsp"%>
            <div class="page-container">
                <%@include file="header.jsp"%>
                <div class="page-content">
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Sliders List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div>
                            <div>
                                <div class="card" style="padding: 0 25%;margin: 0% auto 1%;">
                                    <div class="card-body">
                                        <div class="todo-sidebar">
                                            <div class="todo-search">
                                                <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                <form action="sliderlist" method="post">
                                                    <div class="row">
                                                        <div class="input-group col-lg-6" style=" height: 100%; ">
                                                            <input type="text" id="todo-search" value="${sessionScope.search2}" name="search2" class="form-control" placeholder="Search">
                                                        </div>
                                                        <div class="col-lg-4" style=" font-size: 2em; ">
                                                            <input type="radio" value="31" name="status2" required="" ${sessionScope.status2==31?'checked':''}> Show / <input type="radio" value="30" name="status2" ${sessionScope.status2==30?'checked':''}> Hide
                                                        </div>                                                 
                                                        <div class="col-lg-2">
                                                            <input type="submit" class="btn btn-outline-success" value="Search"/>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="card">
                                    <div class="card-body">
                                        <table id="zero-conf" class="display" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th style="width: 5%!important;">ID</th>
                                                    <th style="width: 5%!important; word-wrap: break-word;">Title</th>   
                                                    <th style="width: 5%!important;">Status</th>
                                                    <th style="width: 25%!important; word-wrap: break-word;">Backlink</th>
                                                    <th style="width: 25%!important; word-wrap: break-word;">Note</th>
                                                    <th style="width: 5%!important; word-wrap: break-word;">Image</th>
                                                    <th style="width: 10%!important;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <jsp:useBean id="sldb" class="dal.SliderDAO"/>
                                                <c:forEach items="${sessionScope.list2}" var="slider">
                                                    <tr> 
                                                        <td style="width: 5%!important;">${slider.getId()}</td>
                                                        <td style="width: 5%!important; word-wrap: break-word;">${slider.title}</td>

                                                        <td style="width: 5%!important;">${slider.status}</td>

                                                        <td style="width: 25%!important; word-wrap: break-word;">${slider.backlink}</td>
                                                        <td style="width: 25%!important; word-wrap: break-word;">${slider.note}</td>
                                                        <td style="width: 5%!important; word-wrap: break-word;"><img style="width: 100%;" src="${slider.image}"></td>
                                                        <td style="width: 10%!important;">
                                                            <a href="sliderdetail?id=${slider.id}" class="btn btn-outline-success" >Edit</a>
                                                            <a href="udslider?id=${slider.id}&status=31" 
                                                               class="btn btn-outline-success ${slider.statusId==31?"active":""}">Show</a>
                                                            <a href="udslider?id=${slider.id}&status=30"
                                                               class="btn btn-outline-success ${slider.statusId==30?"active":""}">Hide</a></td>
                                                    </tr>
                                                </c:forEach>                                                   
                                            </tbody>

                                        </table>
                                        <c:if test="${sessionScope.num2>1}">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <li class="page-item"><a class="page-link" href="sliderlist?page=${sessionScope.page2==1?1:sessionScope.page - 1}">Previous</a></li>
                                                        <c:forEach begin="${1}" end="${sessionScope.num2}" var="i">
                                                        <li class="page-item ${page==i?"active":""}"><a class="page-link" href="sliderlist?page=${i}">${i}</a></li>
                                                        </c:forEach>
                                                    <li class="page-item"><a class="page-link" href="sliderlist?page=${sessionScope.page==sessionScope.num2?sessionScope.num2:sessionScope.page + 1}">Next</a></li>
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
        <script src="assets/plugins/select2/js/select2.full.min.js"></script>
        <script src="assets/js/pages/datatables.js"></script>
        <script src="assets/plugins/DataTables/datatables.min.js"></script>
    </body>
</html>
