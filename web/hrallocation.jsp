<%-- 
    Document   : hrallocation
    Created on : Feb 23, 2022, 11:35:58 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>       
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
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="/workflowbox/" style="font-size: 16px;">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page" style="font-size: 16px;">HR Allocation</li>
                            </ol>
                        </nav>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <a href="addallocation" class="btn btn-info" style="margin-bottom: 5px; font-weight: 800; font-size: 16px; margin-left: 90%">Add</a>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-sm-12">
                                <c:if test="${sessionScope.account.roleId == 5}">
                                    <form action="hr" method="post" style="margin-bottom: 20px">                                                            
                                        Group Name:
                                        <select name="groupName" style= "width: 30%" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option selected value="">---All---</option>
                                            <c:forEach items="${list1}" var="g">
                                                <option value="${g.getGroupName()}">${g.getGroupName()}</option>
                                            </c:forEach>
                                        </select>
                                        &nbsp;&nbsp;&nbsp;&nbsp; 
                                        Project Name:
                                        <select name="projectname" style= "margin-left: 20px; width: 30%; margin-right: 15px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option selected value="">---All---</option>
                                            <c:forEach items="${list2}" var="g">
                                                <option value="${g.getProjectname()}">${g.getProjectname()}</option>
                                            </c:forEach>   
                                        </select>
                                        <input type="submit" class="btn btn-primary btn-submit" style="margin-left: 40px; font-size: 17px" value="Search"/>
                                    </form>
                                </c:if>
                                <table id="zero-conf" class="display dataTable" style="width: 100%;" role="grid" aria-describedby="zero-conf_info">
                                    <thead>
                                        <tr role="row">
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width:40px;">Group Code</th>
                                            <th class="sorting_desc" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 120px;" aria-sort="descending">Project Name</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 100px;">User name</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 180px;">Full name</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 85px;">Group</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 79px;">Project Role</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 90px;">From date</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 90px;">To date</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 70px;">Effort</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.list}" var="u">
                                            <tr role="row" class="odd">
                                                <td class="">${u.getGroupCode()}</td>
                                                <td class="sorting_1">${u.getProjectname()}</td>
                                                <td>${u.getAccount()}</td>
                                                <td>${u.getFullName()}</td>
                                                <td>${u.getGroupName()}</td>
                                                <td>${u.getProjectRole()}</td>
                                                <td>${u.getStartDate1()}</td>
                                                <td>${u.getEndDate1()}</td>
                                                <td>${u.getEffort()} %</td>
                                                <td>
                                                    <a href="allocationedit?RID=${u.RID}" class="btn btn-info" style="margin-bottom: 5px; font-weight: 800">Edit</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>                                           
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp"%>
            </div>
        </div>

        <div class="mailbox-compose-overlay"></div>
        <div class="mailbox-item-overlay"></div>

        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/DataTables/datatables.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
        <script src="assets/js/pages/datatables.js"></script>
    </body>
</html>

