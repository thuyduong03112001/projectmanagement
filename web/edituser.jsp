<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">


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
                                    <a href="userlist" class="btn btn-info" style="margin-bottom: 15px; font-weight: 800; font-size: 16px">Back</a>                                                   
                                </div>
                                <div class="row align-items-center">
                                    <div class="col">
                                        <h2 class="card-title" style="font-size: 20px">Personal Information</h2>
                                    </div>
                                    <!--end col-->
                                </div>
                                <!--end row-->
                            </div>
                            <!--end card-header-->
                            <div class="card-body">
                                <form action="edituser" method="get">
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center" style="display: none">ID</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <input class="form-control" name="id" type="text" value="${user.getId()}" readonly style="display: none"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">User Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                                                <input class="form-control" name="account" type="text" value="${user.getAccount()}" placeholder="Username" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Full Name</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                                                <input class="form-control" name="fullName" type="text" value="${user.getFullName()}" placeholder="Full Name" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Email</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                                <input class="form-control" name="email" type="email" value="${user.getEmail()}" placeholder="Email" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Contact Phone</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <div class="input-group">
                                                <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                                <input type="tel" name="phoneNumber" class="form-control" value="${user.getPhoneNumber()}" placeholder="Phone number" aria-describedby="basic-addon1" maxlength="10" pattern="[0-9]{10}" required/>
                                            </div>
                                            <br><p style="font-size: 14px;">Note: Phone number must consist of 10 digits from 0 to 9</p>
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
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Gender</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <input name= "gender" ${user.gender==13?"checked":""} type="radio" id="female" value="13" required>
                                            <label for="female">Female</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                            <input name= "gender" ${user.gender==12?"checked":""} type="radio" id="male" value="12" required>
                                            <label for="male">Male</label>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Role</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <input type="radio" id="Admin" name= "roleId" ${user.roleId==4?"checked":""} value="4" required>
                                            <label for="Admin">Admin</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                            <input type="radio" id="HR" name= "roleId" ${user.roleId==5?"checked":""} value="5" required>
                                            <label for="HR">HR</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                            <input type="radio" id="Manager" name= "roleId" ${user.roleId==6?"checked":""} value="6" required>
                                            <label for="Manager">Manager</label> &nbsp;&nbsp;&nbsp;&nbsp;

                                            <input type="radio" id="Staff" name= "roleId" ${user.roleId==7?"checked":""} value="7" required>
                                            <label for="Staff">Staff</label> &nbsp;&nbsp;&nbsp;&nbsp;
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Status</label>
                                        <div class="col-lg-9 col-xl-8">
                                            <input type="radio" id="Registered" name= "statusId" ${user.statusId==8?"checked":""} value="8" required>
                                            <label for="Registered">Registered</label> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" id="Verified" name= "statusId" ${user.statusId==9?"checked":""} value="9" required>
                                            <label for="Verified">Verified</label> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" id="Active" name= "statusId" ${user.statusId==10?"checked":""} value="10" required>
                                            <label for="Active">Active</label> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" id="Inactive" name= "statusId" ${user.statusId==11?"checked":""} value="11" required>
                                            <label for="Inactive">Inactive</label>

                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-lg-9 col-xl-8 offset-lg-3">
                                            <button type="submit" class="btn btn-sm btn-outline-primary" style="font-size: 16px">Save</button>
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
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
    </body>
</html>
