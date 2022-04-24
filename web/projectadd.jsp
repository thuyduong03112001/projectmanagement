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
                                <li class="breadcrumb-item"><a href="projectList">ProjectList</a></li>
                                <li class="breadcrumb-item active" aria-current="page">ProjectDetail</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <!--form update project-->
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Update Project</h5>
                                <form action="projectadd" method="Post" class="needs-validation" novalidate >
                                    <div class="form-row">

                                        <div class="col-md-3 mb-3">
                                            <label for="Name">Name</label>
                                            <input type="text" class="form-control" id="Name" placeholder="Name" name="Name" value="" required>
                                            <div class="invalid-feedback">
                                                Please provide a valid Name.
                                            </div>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="Group">Group</label>
                                            <select class="js-states form-control" tabindex="-1" id ="Group" name="Group" style="display: none; width: 100%"  >
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Please provide a valid Group.
                                            </div>
                                        </div>

                                        <div class="col-md-3 mb-3">
                                            <label for="Manager">Manager</label>
                                            <select class="js-states form-control" tabindex="-1" id ="Manager" name="Manager" style="display: none; width: 100%"  >
                                                <option value="1">Do Hoang Long</option>
                                                <option value="2">Nguyen Phuoc Thinh</option>
                                            </select>
                                            <div class="invalid-feedback">
                                                Please provide a valid Manager.
                                            </div>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="Description">Description</label>
                                            <input type="text" class="form-control" id="Description" placeholder="Description" value="" name="Description" required>
                                            <div class="invalid-feedback">
                                                Please provide a valid Description.
                                            </div>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="StartDate">StartDate</label>
                                            <input type="date" class="form-control" value="" required name="StartDate">
                                            <div class="invalid-feedback">
                                                Please provide a valid Date.
                                            </div>
                                        </div>
                                            
                                        <div class="col-md-3 mb-3">
                                            <label for="EndDate">EndDate</label>
                                            <input type="date" class="form-control" value="" required name="EndDate">
                                            <div class="invalid-feedback">
                                                Please provide a valid Date.
                                            </div>
                                        </div>    
                                    </div>
                                    <button class="btn btn-primary" type="submit">Save</button>
                                </form>
                            </div>
                        </div>
                        <!--form update project-->
                        
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