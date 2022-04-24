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
        <title>Timesheet</title>

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
                                <li class="breadcrumb-item"><a href="timesheet">Timesheet</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Review</li>
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
                                                <button onclick="location.href = 'timesheetadd';" class="btn btn-primary btn-block">Approve</button>
                                            </div>
                                            <br>
                                            <div class="todo-new-task">
                                                <button onclick="location.href = 'timesheetReview';" class="btn btn-primary btn-block" ${sessionScope.projectRole != "PM"?"disabled":""}>Review</button>
                                            </div>
                                            <div class="divider"></div>
                                            <!-- under -->
                                            <form action="timesheetReview" method="post">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <label>Start</label>
                                                        <input class="js-states form-control" type="date" name="start">
                                                        <label>End</label>
                                                        <input class="js-states form-control" type="date" name="end">
                                                    </div>
                                                    <br>
                                                    <br>
                                                    <div class="col-12">
                                                        <label for="Project">Project</label>
                                                        <select class="js-states form-control" tabindex="-1" id ="Project" name="project" style="width: 100%">
                                                            <option value="">all</option>
                                                            <c:forEach items="${sessionScope.listProject}" var="project">
                                                                <option value="${project.id}">${project.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                    <div class="col-12">
                                                        <label for="Process">Process</label>
                                                        <select class="js-states form-control" tabindex="-1" id ="Process" name="process" style="width: 100%">
                                                            <option value="">all</option>
                                                            <c:forEach items="${sessionScope.ListProcess}" var="process">
                                                                <option value="${process.id}">${process.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>

                                                </div>
                                                <br>
                                                <input type="submit" class="btn btn-primary" value="Search">
                                            </form>
                                            <!-- under -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-9">
                                <!-- list start-->
                                <div class="row">
                                    <div class="col">
                                        <div class="card">
                                            <div class="card-body">
                                                <h5 class="card-title">List</h5>
                                                <table id="zero-conf" class="display" style="width:100%">
                                                    <thead>
                                                        <tr>
                                                            <th>Date</th>
                                                            <th>Staff Name</th>
                                                            <th>Title</th>
                                                            <th>Project</th>
                                                            <th>Process</th>
                                                            <th>Duration</th>
                                                            <th>Status</th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${sessionScope.listR}" var="t">
                                                            <tr>
                                                                <td>${t.realDate}</td>
                                                                <td>${t.acc.fullName}</td>
                                                                <td>${t.title}</td>
                                                                <td>${t.projectName}</td>
                                                                <td>${t.process}</td>
                                                                <td>${t.duration}</td>
                                                                <td>${t.status}</td>
                                                                <td>
                                                                    <a data-toggle="modal" data-target="#reject${t.id}" href="timesheetApprove?action=reject&tid=${t.id}" ${t.status == "Submitted"?"":"hidden"}><i class="fas fa-trash" ></i>Reject</a>
                                                                </td>
                                                                <td >
                                                                    <a href="timesheetApprove?action=approve&tid=${t.id}" ${t.status == "Submitted"?"":"hidden"}><i class="fas fa-check" ></i>Approve</a>
                                                                </td>
                                                            </tr>
                                                        <div class="modal fade" id="reject${t.id}" tabindex="-1" role="dialog" aria-labelledby="reject${t.id}" aria-hidden="true">
                                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="reject${t.id}">Reject Reason</h5>
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                            <i class="material-icons">close</i>
                                                                        </button>
                                                                    </div>
                                                                        <form action="timesheetApprove" method="post">
                                                                        <div class="modal-body">
                                                                            <input type="text" name="rejectReason" class="form-control">
                                                                            <input type="text" name="tid" hidden="" value="${t.id}">
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            <button type="submit" class="btn btn-primary">Save</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                    </tbody>

                                                </table>
                                            </div>
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

        <div class="mailbox-compose-overlay"></div>
        <div class="mailbox-item-overlay"></div>

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