<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    Account acc = (Account) session.getAttribute("account");
    if (acc == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
    <head>
        <title>User Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link rel="stylesheet" href="css/userprofile.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">  
        <link href="assets/plugins/DataTables/datatables.min.css" rel="stylesheet"> 
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        
<!--        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />-->
        <link href="css/icons.min.css" rel="stylesheet" type="text/css" />
        <link href="css/app.min.css" rel="stylesheet" type="text/css" />
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>


        <!-- Theme Styles -->
        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">
    </head>
    <body>
        <div class="connect-container align-content-stretch d-flex flex-wrap" style="width: 100%">
            <%@include file="sidebar.jsp" %>
        <div class="page-container" style="padding-left:15px">
            <%@include file="header.jsp"%>
            <div class="page-container" style="width: 100%">
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body p-0">
                            <div id="user_map" class="pro-map" >
                                <img src="https://toigingiuvedep.vn/wp-content/uploads/2021/02/hinh-nen-may-trang-nen-xanh-820x549.jpg" style="width: 100%; height: 300px"/>
                            </div>
                        </div>
                        <!--end card-body-->
                        <div class="card-body">
                            <div class="dastone-profile">
                                <div class="row">
                                    <div class="col-lg-6 align-self-center mb-3 mb-lg-0">
                                        <div class="dastone-profile-main">
                                            <div class="dastone-profile-main-pic">
                                                <img src="imgs/<%= acc.getImage()%>" alt="" height="120"
                                                     class="rounded-circle" />
                                            </div>
                                            <div class="dastone-profile_user-detail">
                                                <h5 class="dastone-user-name"><%= acc.getFullName()%></h5>
                                                <p class="mb-0 dastone-user-name-post">
                                                    <%= acc.getBio()%>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <!--end col-->

                                    <div class="col-lg-6 ms-auto align-self-center">
                                        <ul class="list-unstyled personal-detail mb-0">
                                            <li class="mt-2">
                                                <i class="fas fa-phone"> </i>
                                                <b> Phone </b> : <%= acc.getPhoneNumber()%>
                                            </li>
                                            <li class="mt-2">
                                                <i class="fas fa-solid fa-envelope"> </i>
                                                <b> Email </b> : <%= acc.getEmail()%>
                                            </li>
                                            <li class="mt-2">
                                                <i class="fas fa-calendar"></i>
                                                <b> Date of Birth </b> :
                                                <%= acc.getDateOfBirth()%>
                                            </li>
                                            <li class="mt-2">
                                                <i class="fas fa-solid fa-user"></i>
                                                <b> Gender </b> :
                                                <%= acc.getGenderOfvalue()%>
                                            </li>
                                            <li class="mt-2">
                                                <i class="fas fa-solid fa-users"></i>
                                                <b> Role </b> :
                                                <%= acc.getRole()%>
                                            </li>
                                        </ul>
                                    </div>
                                    <!--end col-->
                                </div>
                                <!--end row-->
                            </div>
                            <!--end f_profile-->
                        </div>
                        <!--end card-body-->
                    </div>
                    <!--end card-->
                </div>
                <!--end col-->
            </div>

            <div class="pb-4">
                <ul class="nav nav-pills navtab-bg">
                    <li class="nav-item">
                        <a href="#about-me" data-toggle="tab" aria-expanded="true" class="nav-link ml-0 active">
                            <i class="fas fa-solid fa-user"></i> About Me
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="#settings" data-toggle="tab" aria-expanded="false" class="nav-link">
                            <i class="fas fa-solid fa-bars"></i> Settings
                        </a>
                    </li>
                </ul>
            </div>
            <div class="tab-content">
                <div class="card tab-pane show active " id="about-me">

                    <h5 class="mb-3 mt-4 text-uppercase" style="font-size: 18px"><i class="fas fa-solid fa-list-alt"></i>
                        Projects</h5>
                    <div class="table-responsive">
                        <table class="table table-borderless mb-0">
                            <thead class="thead-light">
                                <tr>
                                    <th>Project Name</th>
                                    <th>Start Date</th>
                                    <th>Due Date</th>
                                    <th>Group Code</th>
                                    <th>Group Name</th>
                                    <th>Project Role</th>
                                    <th>Descriptions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%= acc.getProjectname()%></td>
                                    <td><%= acc.getStartDate1()%></td>
                                    <td><%= acc.getEndDate1()%></td>
                                    <td><%= acc.getGroupCode()%></td>
                                    <td><%= acc.getGroupName() %></td>
                                    <td><%= acc.getProjectRole()%></td>
                                    <td><%= acc.getDescription()%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="tab-pane" id="settings">
                    <div class="row">
                        <div class="col-lg-6 col-xl-6">
                            <div class="card">
                                <div class="card-header">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <h4 class="card-title">Personal Information</h4>
                                        </div>
                                        <!--end col-->
                                    </div>
                                    <!--end row-->
                                </div>
                                <!--end card-header-->
                                <div class="card-body">
                                    <form action="edit" method="post" enctype="multipart/form-data">
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Full Name</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <input class="form-control" name="fullName" type="text" value="<%= acc.getFullName()%>"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Contact Phone</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <div class="input-group">
                                                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                                                    <input type="tel" name="phoneNumber" class="form-control" value="<%= acc.getPhoneNumber()%>" placeholder="Phone number" aria-describedby="basic-addon1" pattern="[0-9]{10}" required/>
                                                    <p style="font-size: 12px">Note: Phone number must consist of 10 digits from 0 to 9</p>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Date of Birth</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <div class="input-group">
                                                    <span class="input-group-text"><i class="fas fa-calendar"></i></span>
                                                    <input type="date" name="dob" class="form-control" placeholder="yyyy-mm-dd" value="<%= acc.getDob()%>" min="1962-01-01" max="2004-12-31"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Gender</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <div>
                                                    <input type="radio" id="female" <%= acc.getGender()==13?"checked":""%> name= "gender" value="13">
                                                    <label for="female">Female</label>
                                                </div>
                                                <div>
                                                    <input type="radio" id="male" <%= acc.getGender()==12?"checked":""%> name= "gender" value="12">
                                                    <label for="male">Male</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label
                                                class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">About me</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <div class="input-group">
                                                    <span class="input-group-text"><i class="fas fa-solid fa-info"></i></span>
                                                    <textarea class="form-control" name="bio" rows="4" placeholder="Write something..."><%= acc.getBio()%></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Image</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <input class="form-control" type="file" name="image"  accept="imgs/*" src="<%= acc.getImage()%>"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-lg-9 col-xl-8 offset-lg-3">
                                                <button type="submit" class="btn btn-sm btn-outline-primary">Save</button>
                                            </div>
                                        </div>
                                        <%
                                            if (request.getAttribute("valid") != null) {
                                                String valid = (String) request.getAttribute("valid");
                                        %>
                                        <h4 style="color: #08aee3"><%=valid%></h4>
                                        <%
                                            }
                                        %>
                                    </form>
                                </div>

                            </div>
                        </div>
                        <!--end col-->
                        <div class="col-lg-6 col-xl-6">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Change Password</h4>
                                </div>
                                <!--end card-header-->
                                <div class="card-body">
                                    <form action="changepass" method="post">
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Current Password</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <input class="form-control" type="password" name="oldPass" placeholder="Password" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">New Password</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <input class="form-control" type="password" name="newPass" placeholder="New Password" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-xl-3 col-lg-3 text-end mb-lg-0 align-self-center">Confirm Password</label>
                                            <div class="col-lg-9 col-xl-8">
                                                <input class="form-control" type="password" name="reNewPass" placeholder="Re-Password" />
                                                <%
                                                    if (request.getAttribute("msg") != null) {
                                                        String msg = (String) request.getAttribute("msg");
                                                %>
                                                <h4 style="color: #E94B41; font-size: 15px"><%=msg%></h4>
                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-lg-9 col-xl-8 offset-lg-3">
                                                <button type="submit" class="btn btn-sm btn-outline-primary">Save</button>
                                                <button type="button" class="btn btn-sm btn-outline-danger">Cancel</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!--end card-body-->
                            </div>
                            <!--end card-->
                        </div>
                        <!-- end col -->
                    </div>
                    <!--end row-->
                </div>
            </div>
            </div>
            <%@include file="footer.jsp" %>
        </div>
        </div>
    </body>
</html>
