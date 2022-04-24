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
        <title>Edit HR Allocation Page</title>
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
                                        <h2 class="card-title" style="font-size: 20px; text-align: center">Edit Resource Allocation Information</h2>
                                    </div>
                                    <!--end col-->
                                </div>
                                <!--end row-->
                            </div>
                            <!--end card-header-->
                            <div class="card-body">
                                <form action="editallocation" method="get">
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center" style="display: none">RID</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="RID" type="text" value="${user.getRID()}" style="display: none"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Group Code</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <select name= "GroupID" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                                    <option ${user.getGroupID()==1?"selected":""} value="1">SM01</option>
                                                    <option ${user.getGroupID()==2?"selected":""} value="2">SM02</option>
                                                    <option ${user.getGroupID()==3?"selected":""} value="3">YS01</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Project Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <select name= "ProjectId" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                                    <option ${user.getProjectId()==1?"selected":""} value="1">Project1</option>
                                                    <option ${user.getProjectId()==2?"selected":""} value="2">Project2</option>
                                                    <option ${user.getProjectId()==3?"selected":""} value="3">Project3</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Project Role</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input type="radio" id="Project Manager" name= "ProjectRoleID" ${user.getProjectRoleID()==22?"checked":""} value="22" required> &nbsp;&nbsp;
                                                <label for="Project Manager">Project Manager</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="radio" id="Developer" name= "ProjectRoleID" ${user.getProjectRoleID()==23?"checked":""} value="23" required> &nbsp;&nbsp;
                                                <label for="Developer">Developer</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="radio" id="BA" name= "ProjectRoleID" ${user.getProjectRoleID()==24?"checked":""} value="24" required> &nbsp;&nbsp;
                                                <label for="BA">BA</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="radio" id="Designer" name= "ProjectRoleID" ${user.getProjectRoleID()==25?"checked":""} value="25" required> &nbsp;&nbsp;
                                                <label for="Designer">Designer</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                                <input type="radio" id="Tester" name= "ProjectRoleID" ${user.getProjectRoleID()==26?"checked":""} value="26" required> &nbsp;&nbsp;
                                                <label for="Tester">Tester</label> &nbsp;&nbsp;&nbsp;&nbsp;
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">User Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="account" type="text" value="${user.getAccount()}" placeholder="Username" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Full Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="fullName" type="text" value="${user.getFullName()}" placeholder="Full Name" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">From Date</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="StartDate" type="date" value="${user.getStartDate()}" max="2022-12-31"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">End Date</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="EndDate" type="date" value="${user.getEndDate()}" max="2022-12-31"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Effort</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="effort" type="text" value="${user.getEffort()}" required/>
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
