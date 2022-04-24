<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>       
        <title>User List Page</title>
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
                                <li class="breadcrumb-item"><a href="/workflowbox/">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">User List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-sm-12">
                                
                                <form action="userlist" method="post" style="margin-bottom: 20px">                               
                                    Gender:         
                                    <select name="genderOfvalue" style= "margin-left: 10px; width: 20%; margin-right: 20px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list1}" var="g">
                                            <option value="${g.getGenderOfvalue()}">${g.getGenderOfvalue()}</option>
                                        </c:forEach>   
                                    </select>
                                    Group Code:
                                    <select name="groupCode" style= "width: 20%; margin-right: 20px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list2}" var="g">
                                            <option value="${g.getGroupCode()}">${g.getGroupCode()}</option>
                                        </c:forEach>
                                    </select>

                                    Status:
                                    <select name="StatusOfAccount" style= "margin-left: 10px; width: 20%; margin-right: 15px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list3}" var="g">
                                            <option value="${g.getStatusOfAccount()}">${g.getStatusOfAccount()}</option>
                                        </c:forEach>   
                                    </select>
                                    <input type="submit" class="btn btn-primary btn-submit" style="margin-left: 20px; font-size: 17px" value="Search"/>
                                </form>
                                
                                <table id="zero-conf" class="display dataTable" style="width: 100%;" role="grid" aria-describedby="zero-conf_info">
                                    <thead>
                                        <tr role="row">
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" aria-label="Name: activate to sort column ascending" style="width: 128px;">Group Code</th>
                                            <th class="sorting_desc" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 190px;" aria-sort="descending">Full Name</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 92px;">Gender</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 32px;">Email</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 85px;">Mobile</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 79px;">Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.list}" var="u">
                                            <tr role="row" class="odd">
                                                <td class="">${u.getGroupCode()}</td>
                                                <td class="sorting_1">${u.getFullName()}</td>
                                                <td>${u.getGenderOfvalue()}</td>
                                                <td>${u.getEmail()}</td>
                                                <td>${u.getPhoneNumber()}</td>
                                                <td>${u.getStatusOfAccount()}</td>
                                                <td>
                                                    <a href="userdetail?id=${u.id}" class="btn btn-success" style="margin-bottom: 5px; font-weight: 800">View</a>                                                   
                                                    <c:if test="${sessionScope.account.roleId == 5}">
                                                        <a href="editservlet?id=${u.id}" class="btn btn-info" style="margin-bottom: 5px; font-weight: 800; width: 78.94px">Edit</a> 
                                                    </c:if>
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
