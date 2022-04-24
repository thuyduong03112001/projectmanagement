<!DOCTYPE html>
<html lang="en">
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Detail</title>

        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?afamily=Lato:400,700,900&display=swap" rel="stylesheet">
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


        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <jsp:include page="sidebar.jsp"/>
            <div class="page-container">
                <jsp:include page="header.jsp"/>
                <div class="page-content">
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/workflowbox/">Home</a></li>
                                <li class="breadcrumb-item"><a href="timesheet">Timesheet</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Detail</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-xl">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">Timesheet Detail</h5>
                                        <form action="TimesheetEdit" method="Post" class="needs-validation" novalidate >
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3">
                                                    <label for="Date">Date</label>
                                                    <input type="date" class="form-control" value="${timesheet.date}" required name="date" ${timesheet.status == "Approved"?"readonly":""}>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Date.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="Title">Title</label>
                                                    <input type="text" class="form-control" id="Title" placeholder="title" value="${timesheet.title}" name="title" required ${timesheet.status == "Approved"?"readonly":""}>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Title.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="Project">Project</label>
                                                    <select class="js-states form-control" tabindex="-1" id ="Project" name="project" style="display: none; width: 100%"  >
                                                        <c:forEach items="${sessionScope.listProject}" var="project">
                                                            <option value="${project.id}" ${project.id == timesheet.project.id?"selected": timesheet.status == "Approved" ?"disabled":""}>${project.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Project.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="Process">Process</label>
                                                    <select class="js-states form-control" tabindex="-1" id ="Process" name="process" style="width: 100%" tabindex="-1" aria-disabled="true">
                                                        <c:forEach items="${sessionScope.ListProcess}" var="process">
                                                            <option value="${process.id}" ${process.value == timesheet.process?"selected":timesheet.status == "Approved" ?"disabled":""} >${process.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Process.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="Duration">Duration</label>
                                                    <input type="text" class="form-control" id="Duration" placeholder="Duration" value="${timesheet.duration}" name="duration" required ${timesheet.status == "Approved"?"readonly":""}>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Duration.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="Status">Status</label>
                                                    <input type="text" class="form-control" id="Status" placeholder="Status" value="${timesheet.status}" name="status" readonly="" >
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Status.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="WorkResult">WorkResult</label>
                                                    <input type="text" class="form-control" id="WorkResult" placeholder="WorkResult" value="${timesheet.workResult}" name="workResult" required ${timesheet.status == "Approved"?"readonly":""}>
                                                    <div class="invalid-feedback">
                                                        Please provide a valid Work Result.
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <label for="RejectReason">RejectReason</label>
                                                    <input type="text" class="form-control" id="RejectReason" placeholder="RejectReason" value="${timesheet.rejectReason}" name="rejectReason" readonly="" >
                                                </div>
                                            </div>
                                            <button class="btn btn-primary" type="submit">OK</button>
                                        </form>
                                    </div>
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
        <script src="assets/js/pages/select2.js"></script>
        <script src="assets/plugins/select2/js/select2.full.min.js"></script>
    </body>
</html>