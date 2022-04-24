<%-- 
    Document   : sidebar
    Created on : Feb 9, 2022, 11:53:11 AM
    Author     : Admin
    Comment    : class="active-page" uss in <li> for active page
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="myFunction();" class="compact-sidebar">
        <% String uri = request.getRequestURI();
            String pageName = uri.substring(uri.lastIndexOf("/") + 1);
            if (pageName.contains(".")) {
                pageName = pageName.substring(0, pageName.indexOf("."));
            }
            if (pageName.contains("?")) {
                pageName = pageName.substring(0, pageName.indexOf("?"));
            }
            if (pageName.equals("")) {
                pageName = "home";
            }
        %>
        <div class="page-sidebar" >
            <div class="logo-box">
                <img src="imgs/logo.png" style="width: 85%;">
                <a href="#" id="sidebar-close"><i class="material-icons">close</i></a> <a href="#" id="sidebar-state" style="transform: translateY(200%);"><i class="material-icons">adjust</i><i class="material-icons compact-sidebar-icon">panorama_fish_eye</i></a></div>
            <div class="page-sidebar-inner slimscroll">
                <ul class="accordion-menu">
                    <li id="home">
                        <a href="/workflowbox/"><i class="material-icons">home</i>Home</a>
                    </li>
                    <li id="blogslist">
                        <a href="bloglist"><i class="material-icons"> article </i>Blog</a>
                    </li>
                    <li class="sidebar-title">
                        Apps
                    </li>
                    <c:if test="${sessionScope.account.roleId == 7}" >
                        <li id="staffdash">
                            <a href="staffdash" class="active"><i class="material-icons-outlined">dashboard</i>Dashboard</a>
                        </li>
                    </c:if>
                    <li >
                        <a href="deliverablelist"><i  class="material-icons-outlined"> drive_file_move </i>Deliverable</a>
                    </li>
                    <li>
                        <a href="mailbox.html"><i class="material-icons-outlined">inbox</i>Mailbox</a>
                    </li>
                    <c:if test="${sessionScope.account.roleId == 6}">
                        <li>
                            <a href="hr?groupcode=${sessionScope.account.groupCode}"><i class="material-icons-outlined">people</i>HR Allocation</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account.roleId == 5}">
                        <li>
                            <a href="hr"><i class="material-icons-outlined">people</i>HR Allocation</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account.roleId == 5}">
                        <li>
                            <a href="postlist"><i class="material-icons-outlined"> post_add </i>Posts List</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.account.roleId == 5}">
                        <li>
                            <a href="sliderlist"><i class="material-icons-outlined"> slideshow </i>Sliders List</a>
                        </li>
                    </c:if>  
                    
                    <li>
                        <a href="issuelist"><i class="material-icons">cloud_queue</i>Issue</a>
                    </li>
                    
                    <li>
                        <a href="userprofile.jsp"><i class="material-icons-outlined">account_circle</i>Profile</a>
                    </li>
                    <li>
                        <a href="file-manager.html"><i class="material-icons">cloud_queue</i>File Manager</a>
                    </li>
                    <li>
                        <a href="calendar.html"><i class="material-icons-outlined">calendar_today</i>Calendar</a>
                    </li>
                    <li>
                        <a href="todo.html"><i class="material-icons">done</i>Todo</a>
                    </li>
                     <li>
                        <a href="exportdata.jsp"><i class="material-icons"> table_view </i>Todo</a>
                    </li>
                </ul>
            </div>
        </div>
        <script>
            function myFunction() {
                document.getElementById("<%=pageName%>").className = "active-page";
            }
        </script>
    </body>
</html>
