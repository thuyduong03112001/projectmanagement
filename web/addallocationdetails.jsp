<%-- 
    Document   : addoreditHR
    Created on : Feb 26, 2022, 11:18:59 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add HR Allocation Page</title>
        <title>HR Allocation Page</title>
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">  
        <link href="assets/plugins/DataTables/datatables.min.css" rel="stylesheet"> 

        <!-- Theme Styles -->
        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">
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
                    <div class="col-lg-12 col-xl-12">
                        <div class="card">
                            <div class="card-header">
                                <div class="row">
                                    <a href="hr?groupcode=${sessionScope.account.groupCode}" class="btn btn-info" style="margin-bottom: 15px; font-weight: 800; font-size: 16px">Back</a>                                                   
                                </div>
                                <div class="row align-items-center">
                                    <div class="col">
                                        <h2 class="card-title" style="font-size: 20px; text-align: center">Add Resource Allocation Information</h2>
                                    </div>
                                    <!--end col-->
                                </div>
                                <!--end row-->
                            </div>
                            <!--end card-header-->
                            <div class="card-body">
                                <form action="addallocation" method="post">
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Group Code</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" type="number" name="GroupID" value="${r.GroupID}" min="1" max="3" required> &nbsp;&nbsp;
                                            </div>
                                            <br><p style="font-size: 13px;">Note: 1-SM01 &nbsp;&nbsp; 2-SM02 &nbsp;&nbsp; 3-YS01</p>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Project Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" type="number" name="ProjectId" value="${r.ProjectId}" min="1" max="3" required> &nbsp;&nbsp;
                                                
                                            </div>
                                            <br><p style="font-size: 13px;">Note: 1-Project1 &nbsp;&nbsp; 2-Project2 &nbsp;&nbsp; 3-Project3</p>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Project Role</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" type="number" name="ProjectRoleID" value="${r.ProjectRoleID}" min="22" max="26" required> &nbsp;&nbsp;
                                            </div>
                                            <br><p style="font-size: 13px;">Note: 22-Project Manager &nbsp;&nbsp; 23-Developer &nbsp;&nbsp; 24-BA &nbsp;&nbsp; 25-Designer &nbsp;&nbsp; 26-Tester &nbsp;&nbsp;</p>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">User Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="account" type="text" value="${r.account}" placeholder="Username" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Full Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="fullName" type="text" value="${r.fullName}" placeholder="Full Name" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">From Date</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="StartDate" type="date" value="${r.StartDate}" max="2022-12-31"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">End Date</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="EndDate" type="date" value="${r.EndDate}" max="2022-12-31"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Effort</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="effort" type="text" value="${r.effort}" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Notes</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="notes" type="text" value="${r.notes}" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-lg-9 col-xl-8 offset-lg-3">
                                            <button type="submit" class="btn btn-sm btn-outline-primary" style="font-size: 16px;">Save</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp"%>
            </div>
        </div>
        <div class="mailbox-compose-overlay"></div>
        <div class="mailbox-item-overlay"></div>
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/DataTables/datatables.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
        <script src="assets/js/pages/datatables.js"></script>
    </body>
</html>
