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
        <title>Dashboard</title>
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
            #google-visualization-errors-0 {
                display: none;
            }  
            #zero-conf_wrapper>div:nth-child(odd), #add-row_wrapper>div:nth-child(odd) {
                display: none;
            }
            #zero-conf, #add-row{
                text-align: center;
            }

            td {
                word-break: break-all;               
            }

            #exampleModal .modal-dialog .modal-content .modal-body .form-group>span {
                /*display: none;*/
                width: 100%!important;
            }
        </style>
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
                                <li class="breadcrumb-item"><a href="#">Apps</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Dashboard</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <form action="dashboard" method="post">
                            <div class="row" style="text-align: center;">       
                                <div class="col-lg-6 d-flex align-items-stretch">
                                    <div class="card" style="width: 100%">
                                        <div class="card-header">
                                            <ul class="nav nav-tabs card-header-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" href="#" >Timesheets</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="timesheet" >View Timesheets List</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="card-body">
                                            <div class="visitors-stats">

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="visitors-stats-info" style=" width: 100%; ">
                                                            <p>Project</p>
                                                            <jsp:useBean id="pdb" class="dal.ProjectDAO"/>
                                                            <h5><select onchange="this.form.submit()" name="projectTime" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; width: 100%;">
                                                                    <optgroup label="Your Project">
                                                                        <option ${projectTime==-1?"selected":""} value="-1">All Projects</option>
                                                                        <c:forEach items="${pdb.getAllByManager(managerId)}" var="project">
                                                                            <option ${projectTime==project.id?"selected":""} value="${project.id}">${project.name}</option>
                                                                        </c:forEach>
                                                                </select></h5>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="visitors-stats-info" style=" width: 100%; ">
                                                            <p>Project Status</p>
                                                            <h5 style=" margin-top: 7%; ">
                                                                <input  onchange="this.form.submit()" type="radio" value="1" name="statusTime" required="" ${statusTime==1?'checked':''}> 
                                                                Active / <input onchange="this.form.submit()" type="radio" value="0" name="statusTime" ${statusTime==0?'checked':''}> Inactive</h5>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div id="noneChart"></div>
                                                <div id="chart"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 d-flex align-items-stretch">
                                    <div class="card" style="width: 100%">
                                        <div class="card-header">
                                            <ul class="nav nav-tabs card-header-tabs" style="float: right;">
                                                <li class="nav-item">
                                                    <a class="nav-link" href="issuelist">Virew Issues List</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link active" href="#">Issues</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="card-body">
                                            <div class="visitors-stats">

                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="visitors-stats-info" style=" width: 100%; ">
                                                            <p>Project</p>
                                                            <h5><select onchange="this.form.submit()" name="projectIssues" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; width: 100%;">
                                                                    <optgroup label="Your Project">
                                                                        <option ${projectIssues==-1?"selected":""} value="-1">All Projects</option>
                                                                        <c:forEach items="${pdb.getAllByManager(managerId)}" var="project">
                                                                            <option ${projectIssues==project.id?"selected":""} value="${project.id}">${project.name}</option>
                                                                        </c:forEach>
                                                                </select></h5>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="visitors-stats-info" style=" width: 100%; ">
                                                            <p>Project Status</p>
                                                            <h5 style=" margin-top: 7%; ">
                                                                <input  onchange="this.form.submit()" type="radio" value="1" name="statusIssues" required="" ${statusIssues==1?'checked':''}> 
                                                                Active / <input onchange="this.form.submit()" type="radio" value="0" name="statusIssues" ${statusIssues==0?'checked':''}> Inactive</h5>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div id="noneChart2"></div>
                                                <div id="chart2"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                        </form>
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 style="font-size: 1.5em;text-align: center;" class="card-title">Issues List</h1>
                                        <table id="zero-conf" class="display" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Project ID</th>
                                                    <th>Project Name</th>
                                                    <th>Creator</th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${tableIssues}" var="issue">
                                                    <tr>
                                                        <td>${issue.title}</td>
                                                        <td>${issue.projectId}</td>
                                                        <td>${issue.projectName}</td>
                                                        <td>${issue.creator}</td>
                                                        <td>${issue.status}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 style="font-size: 1.5em;text-align: center;" class="card-title">Risks List</h1>
                                        <table id="add-row" class="display" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Project ID</th>
                                                    <th>Project Name</th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${tableRisks}" var="risk">
                                                    <tr>
                                                        <td>${risk.title}</td>
                                                        <td>${risk.projectId}</td>
                                                        <td>${risk.projectName}</td>
                                                        <td>${risk.status}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>                                 
                    </div>
                </div>
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script>
                                                                    function drawVisualization() {
                                                                        // Create and populate the data table.
                                                                        var data = google.visualization.arrayToDataTable([
            <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                                        ['Year'
            <c:forEach items="${stdb.getAllByType('timesheet status')}" var="status">
                                                                        , '${status.value}'
            </c:forEach>
                                                                        ],
            <c:forEach items="${timeList}" var="time">
                                                                        ['${time.date}', ${time.type1}, ${time.type2}, ${time.type3}],
            </c:forEach>
                                                                        ]);
            <c:if test="${timeList.size()==0}">
                                                                        drawMyVisualization();
            </c:if>
                                                                        // Create and draw the visualization.
                                                                        new google.visualization.ColumnChart(document.getElementById('chart')).
                                                                                draw(data,
                                                                                        {title: "",
                                                                                            width: 600, height: 400,
                                                                                            vAxis: {title: "Process"}, isStacked: true,
                                                                                            hAxis: {title: "Days"}}
                                                                                );
                                                                    }

                                                                    function drawMyVisualization() {
                                                                        document.getElementById('noneChart').innerHTML = "<img src='imgs/product_not_found.jpg' style=' width: 100%; '/>";
                                                                        document.getElementById('chart').style.display = 'none';
                                                                    }
                                                                    google.load("visualization", "1", {packages: ["corechart"]});
                                                                    google.setOnLoadCallback(drawVisualization);
        </script>
        <script>
            function drawVisualization() {
                // Create and populate the data table.
                var data = google.visualization.arrayToDataTable([
                ['Year'
            <c:forEach items="${stdb.getAllByType('issue type')}" var="status">
                , '${status.value}'
            </c:forEach>
                ],
            <c:forEach items="${issuesList}" var="time">
                ['${time.date}', ${time.type1}, ${time.type2}, ${time.type3}, ${time.type4}, ${time.type5}],
            </c:forEach>
                ]);
            <c:if test="${issuesList.size()==0}">
                drawMyVisualization2();
            </c:if>
                // Create and draw the visualization.
                new google.visualization.ColumnChart(document.getElementById('chart2')).
                        draw(data,
                                {title: "",
                                    width: 600, height: 400,
                                    vAxis: {title: "Numbwe Of Issues"}, isStacked: true,
                                    hAxis: {title: "Days"}}
                        );
            }
            function drawMyVisualization2() {
                document.getElementById('noneChart2').innerHTML = "<img src='imgs/product_not_found.jpg' style=' width: 100%; '/>";
                document.getElementById('chart2').style.display = 'none';
            }
            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawVisualization);
        </script>
        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
        <script src="assets/js/pages/dashboard.js"></script>
        <script src="assets/plugins/DataTables/datatables.min.js"></script>
        <script src="assets/js/pages/datatables.js"></script>
        <script src="assets/js/pages/select2.js"></script>
        <script src="assets/plugins/select2/js/select2.full.min.js"></script>
    </body>
</html>