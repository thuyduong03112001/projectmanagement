<%-- 
    Document   : header
    Created on : Feb 9, 2022, 11:56:31 AM
    Author     : Admin
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    <style>
        .page-header:before {
            width: 100%;
            height: 100px;
            position: absolute;
            content: '';
            background: linear-gradient(100deg,#3cbd9c,#9be3bd);
            background-size: cover;
            z-index: -3;
            border-radius: 7px;
            background-position: 0px -100px;
        }

    </style>
    <body>
        <div class="page-header" style="position: sticky;top:0;z-index: 500;height: 100px;margin-bottom: 1%;">
            <nav class="navbar navbar-expand" style=" transform: translateY(-10px); ">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="navbar-search">
                    <form>
                        <div class="form-group">
                            <%Date td = new Date();
                                String b = new String("");
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                b = format.format(td);
                            %>
                            <input disabled style="text-align: center;width: 150%;padding: 0;font-size: 1.3em;" type="text" name="search" id="nav-search" placeholder="<%=b%>">
                        </div>
                    </form>
                </div>       
                <div class="collapse navbar-collapse row" id="navbarNav">
                    <div class="profile-menu" style="margin: auto;">
                        <a href="/workflowbox/#slideshow" style="font-size: 2em; color: aliceblue; font-weight: 600;">Slideshow</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/workflowbox/#blog" style="font-size: 2em; color: aliceblue; font-weight: 600;">Blog</a>
                    </div>
                </div>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#" class="nav-link" id="dark-theme-toggle"><i class="material-icons-outlined">brightness_2</i><i class="material-icons">brightness_2</i></a>
                    </li>
                    <c:if test="${sessionScope.account!=null}">
                        <li class="nav-item small-screens-sidebar-link">
                            <a href="#" class="nav-link"><i class="material-icons-outlined">menu</i></a>
                        </li>
                        <li class="nav-item nav-profile dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="imgs/${sessionScope.account.image}" alt="profile image">
                                <span style="font-size: 17px">${sessionScope.account.fullName}</span><i class="material-icons dropdown-icon">keyboard_arrow_down</i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:if test="${sessionScope.account.roleId == 6}">
                                <a class="dropdown-item" href="userlist?groupcode=${sessionScope.account.groupCode}">Dashboard</a>
                                 </c:if>
                                <c:if test="${sessionScope.account.roleId == 5}">
                                <a class="dropdown-item" href="userlist">Dashboard</a>
                                 </c:if>
                                <a class="dropdown-item" href="userprofile.jsp">Profile</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Log out</a>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account==null}">
                        <div class="page-options">
                            <a href="login.jsp" class="btn btn-info">Login</a>
                            <a href="register.jsp" class="btn btn-info">Register</a>
                        </div> 
                    </c:if>
                </ul>
            </nav>
        </div>
    </body>
</html>
