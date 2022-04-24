<%-- 
    Document   : userdetail
    Created on : Feb 20, 2022, 12:18:55 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Detail</title>
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
    <body class="app-profile">
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
                    <div class="main-wrapper">
                        <div class="profile-header">
                            <div class="row">
                                <div class="col">
                                    <div class="profile-img">
                                        <img src="imgs/${user.getImage()}"  class="rounded-circle">
                                    </div>
                                    <div class="profile-name">
                                        <h3>${user.getFullName()}</h3>                                      
                                    </div>
                                </div>
                            </div>
                            <c:if test="${sessionScope.account.roleId == 5}">
                                <div class="row">
                                    <a href="userlist" class="btn btn-info" style="margin-bottom: 15px; font-weight: 800; font-size: 16px; margin-left: 85%">Back</a> 
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 6}">
                                <div class="row">
                                    <a href="userlist?groupcode=${sessionScope.account.groupCode}" class="btn btn-info" style="margin-bottom: 15px; font-weight: 800; font-size: 16px; margin-left: 85%">Back</a> 
                                </div>
                            </c:if>
                        </div>
                        <div class="profile-content">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title" style="font-size: 18px">About</h5>
                                            <p>Bio : ${user.getBio()}</p>
                                            <ul class="list-unstyled profile-about-list">
                                                <li><i class="material-icons">person</i><span>Username : ${user.getAccount()}</span></li>
                                                <li><i class="material-icons">work</i><span>Position : ${user.getRole()}</span></li>
                                                <li><i class="material-icons">wc</i><span>Gender : ${user.getGenderOfvalue()}</span></li>
                                                <li><i class="material-icons">adjust</i><span>Status : ${user.getStatusOfAccount()}</span></li>
                                                <li><i class="material-icons">people</i><span>Group Code : ${user.getGroupCode()}</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="card-body">
                                            <h5 class="card-title" style="font-size: 18px">Contact Info</h5>
                                            <ul class="list-unstyled profile-about-list">
                                                <li><i class="material-icons">mail_outline</i><span>Email : ${user.getEmail()}</span></li>                                              
                                                <li><i class="material-icons">local_phone</i><span>Phone Number : ${user.getPhoneNumber()}</span></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
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
