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
        <title>Posts List</title>
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
                zoom: 75%;
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
                                <li class="breadcrumb-item active" aria-current="page">Posts List</li>
                            </ol>
                        </nav>
                        <div class="page-options">
                            <a href="newpost" class="btn btn-primary">New Post</a>
                        </div>
                    </div>
                    <div class="main-wrapper">
                        <div>
                            <div>
                                <div class="card">
                                    <div class="card-body">
                                        <div class="todo-sidebar">
                                            <div class="todo-search">
                                                <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                <form action="postlist" method="post">
                                                    <div class="row">
                                                        <div class="input-group col-lg-1" style=" height: 100%; ">
                                                            <input type="text" id="todo-search" value="${sessionScope.title1}" name="title1" class="form-control" placeholder="Title">
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <select name="status1">
                                                                <optgroup label="Status">
                                                                    <option ${sessionScope.status1==-1?"selected":""} value="-1">All Status</option>
                                                                    <c:forEach items="${stdb.getAllByType('post status')}" var="status">
                                                                        <option ${sessionScope.status1==status.id?"selected":""} value="${status.id}">${status.value}</option>
                                                                    </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-lg-4">
                                                            <div >
                                                                <c:forEach items="${stdb.getAllByType('post category')}" var="cate">
                                                                    <div style="width: 25%; float: left;">
                                                                        <input style="display: inline-block;"${sessionScope.category1.contains(cate.id)?"checked":""} name="category1" type="checkbox" value="${cate.id}">
                                                                        <p style="display: inline-block;"> ${cate.value}</p>
                                                                    </div>             
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <input type="text" id="todo-search" value="${sessionScope.author1}" name="author1" class="form-control" placeholder="Author">
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <select name="sort1">
                                                                <optgroup label="Sort by">
                                                                    <option ${sessionScope.sort1==""?"selected":""} value="">Default</option>
                                                                    <option ${sessionScope.sort1=="order by title asc"?"selected":""} value="order by title asc">Title ascending</option>
                                                                    <option ${sessionScope.sort1=="order by title desc"?"selected":""} value="order by title desc">Title descending</option>
                                                                    <option ${sessionScope.sort1=="order by category asc"?"selected":""} value="order by category asc">Category ascending</option>
                                                                    <option ${sessionScope.sort1=="order by category desc"?"selected":""} value="order by category desc">Category descending</option>
                                                                    <option ${sessionScope.sort1=="order by fullname asc"?"selected":""} value="order by fullname asc">Author ascending</option>    
                                                                    <option ${sessionScope.sort1=="order by flag asc"?"selected":""} value="order by flag asc">Featured ascending</option>
                                                                    <option ${sessionScope.sort1=="order by flag desc"?"selected":""} value="order by flag desc">Featured descending</option>
                                                                    <option ${sessionScope.sort1=="order by statusid asc"?"selected":""} value="order by statusid asc">Status ascending</option>
                                                                    <option ${sessionScope.sort1=="order by statusid desc"?"selected":""} value="order by statusid desc">Status descending</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-lg-1">
                                                            <input type="submit" class="btn btn-outline-success" value="Filter"/>
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
                                                    <th style="width: 5%!important;">Category</th>
                                                    <th style="width: 5%!important;">Status</th>
                                                    <th style="width: 5%!important;">Featured</th>
                                                    <th style="width: 25%!important; word-wrap: break-word;">Content</th>
                                                    <th style="width: 25%!important; word-wrap: break-word;">Brief</th>
                                                    <th style="width: 5%!important; word-wrap: break-word;">Thumbnail</th>
                                                    <th style="width: 5%!important; ">Author</th>
                                                    <th style="width: 5%!important;">Date</th>
                                                    <th style="width: 10%!important;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                   <jsp:useBean id="pdb" class="dal.PostDAO"/>
                                                <c:forEach items="${sessionScope.list1}" var="post">
                                                    <tr> 
                                                        <td style="width: 5%!important;">${post.getId()}</td>
                                                        <td style="width: 5%!important; word-wrap: break-word;">${post.title}</td>
                                                        <td style="width: 5%!important;">${post.category}</td>
                                                        <td style="width: 5%!important;">${post.status}</td>
                                                        <td style="width: 5%!important;">${post.flag==1?"ON":"OFF"} </td>
                                                        <td id="content${post.getId()}" style="width: 25%!important; word-wrap: break-word;"><span style="display: none">${post.content}</span>
                                                            <br><button class="btn btn-outline-success" onclick="myFunction1('content' +${post.getId()})">Show</button></td>
                                                        <td id="brief${post.getId()}" style="width: 25%!important; word-wrap: break-word;"><span style="display: none">${post.brief}</span>
                                                            <br><button class="btn btn-outline-success"  onclick="myFunction1('brief' +${post.getId()})">Show</button></td>
                                                        <td style="width: 5%!important; word-wrap: break-word;"><img style="width: 100%;" src="${post.thumbnail}"></td>
                                                        <td style="width: 5%!important;">${post.fullName}</td>
                                                        <td style="width: 5%!important; font-size: 0.8em;">${post.realDate}</td>
                                                        <td style="width: 10%!important;"><a href="blogdetail?id=${post.id}" class="btn btn-outline-success">View</a>
                                                            <a href="postdetail?id=${post.id}" class="btn btn-outline-success" 
                                                               ${pdb.getPostById(post.getId()).getHrId()!=sessionScope.account.getId()?"style='display: none;'":""}>Edit</a>
                                                            <a href="udpost?id=${post.id}&status=3"  ${pdb.getPostById(post.getId()).getHrId()!=sessionScope.account.getId()?"style='display: none;'":""} 
                                                               class="btn btn-outline-success ${post.statusId==3?"active":""}">Show</a>
                                                            <a href="udpost?id=${post.id}&status=1"  ${pdb.getPostById(post.getId()).getHrId()!=sessionScope.account.getId()?"style='display: none;'":""}
                                                               class="btn btn-outline-success ${post.statusId!=3?"active":""}">Hide</a></td>
                                                    </tr>
                                                </c:forEach>                                                   
                                            </tbody>

                                        </table>
                                        <c:if test="${sessionScope.num1>1}">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <li class="page-item"><a class="page-link" href="postlist?page=${sessionScope.page==1?1:sessionScope.page - 1}">Previous</a></li>
                                                        <c:forEach begin="${1}" end="${sessionScope.num1}" var="i">
                                                        <li class="page-item ${page==i?"active":""}"><a class="page-link" href="postlist?page=${i}">${i}</a></li>
                                                        </c:forEach>
                                                    <li class="page-item"><a class="page-link" href="postlist?page=${sessionScope.page==sessionScope.num1?sessionScope.num1:sessionScope.page + 1}">Next</a></li>
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
        <script>
            function myFunction1(ele) {
                var root = document.getElementById(ele);

                if (root.firstChild.style.display === "none") {
                    root.firstChild.style.display = "block";
                    root.lastChild.innerText = "Hide";
                } else {
                    root.firstChild.style.display = "none";
                    root.lastChild.innerText = "Show";
                }
            }
        </script>
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
