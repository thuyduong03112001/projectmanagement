<!DOCTYPE html>
<html lang="en">
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Project List</title>

        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/DataTables/datatables.min.css" rel="stylesheet">
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
        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <jsp:include page="sidebar.jsp"/>
            <div class="page-container">
                <jsp:include page="header.jsp"/>
                <div class="page-content">
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/workflowbox/">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">ProjectList</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <form class="col-12" action="projectList" method="post">
                                <div class="row">
                                    <div style="position: relative;" class="todo-new-task col-1">
                                        <button style="position: absolute; bottom: 0; left: 0;" onclick="location.href = 'projectadd';" class="btn btn-primary form-control">Add</button>
                                    </div>
                                    <div class="col-3">
                                        <label for="Group">Group</label>
                                        <select class="js-states form-control" tabindex="-1" id ="Group" name="groupId" style="width: 100%">
                                            <option value="">all</option>
                                            <c:forEach items="" var="ac">
                                                <option value=""></option>
                                            </c:forEach>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                        </select>
                                    </div>
                                    <div class="col-4">
                                        <label for="Project Manager">Project Manager</label>
                                        <select class="js-states form-control" tabindex="-1" id ="Project Manager" name="projectmanager" style="width: 100%">
                                            <option value="">all</option>
                                            <c:forEach items="" var="ac">
                                                <option value=""></option>
                                            </c:forEach>
                                            <option value="1">Do Hoang Long</option>
                                            <option value="2">Nguyen Phuoc Thinh</option>
                                        </select>
                                    </div>
                                    <div class="col-3">
                                        <label for="Issue">Status</label>
                                        <select class="js-states form-control" tabindex="-1" id ="Status" name="status" style="width: 100%">
                                            <option value="">all</option>
                                            <option value="1">Active</option>
                                            <option value="0">Deactive</option>
                                        </select>
                                    </div>
                                    <div style="position: relative;" class="col-1">
                                        <label for="search"> </label>
                                        <input style="position: absolute; bottom: 0; left: 0;" type="submit" id="search" class="btn btn-primary form-control" value="Search">
                                    </div>

                                </div>
                                <br>
                            </form>

                        </div>    
                        <div class="row">
                            <div class="col">
                                <!-- list start-->

                                <div class="col">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title">Project List</h5>
                                            <jsp:useBean id="pdb" class="dal.ProjectDAO"/>
                                            <table id="zero-conf" class="display" style="width:100%">
                                                <thead>
                                                    <tr>
                                                        <th>Group</th>
                                                        <th>ProjectCode</th>
                                                        <th>ProjectName</th>
                                                        <th>Manager</th>
                                                        <th>StartDate</th>
                                                        <th>EndDate</th>
                                                        <th>Status</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${sessionScope.listP}" var="project">
                                                        <tr>
                                                            <td>${project.groupId}</td> 
                                                            <td>${project.id}</td>
                                                            <td>${project.name}</td>
                                                            <td>${pdb.getManagerByGroup(project.groupId).toString()}</td>
                                                            <td>${project.startDate}</td>
                                                            <td>${project.endDate}</td>
                                                            <td>${project.status == 1?"Active":"Deactive"}</td>
                                                            <td><a href="projectDetail?id=${project.id}"><i class="fas fa-check"></i>Detail</a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!-- list end-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- Javascripts -->
    <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
    <script src="assets/plugins/bootstrap/popper.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/js/connect.min.js"></script>
    <script src="assets/js/pages/todo.js"></script>

    <script src="assets/plugins/DataTables/datatables.min.js"></script>
    <script src="assets/js/pages/datatables.js"></script>

    <script src="assets/js/pages/select2.js"></script>
    <script src="assets/plugins/select2/js/select2.full.min.js"></script>
</body>
</html>