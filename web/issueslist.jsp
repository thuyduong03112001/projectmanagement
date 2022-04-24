<%-- 
    Document   : issueslist
    Created on : Mar 4, 2022, 9:07:54 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>       
        <title>Issues Page</title>
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
                                <li class="breadcrumb-item active" aria-current="page" style="font-size: 16px;">Issues List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-sm-12">

                                <form action="issuelist" method="post" style="margin-bottom: 20px">                                                            
                                    Issue Type:
                                    <select name="itype" style= "width: 18%" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list1}" var="i">
                                            <option value="${i.getItype()}">${i.getItype()}</option>
                                        </c:forEach>
                                    </select>
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    Project Name:
                                    <select name="projectName" style= "margin-left: 20px; width: 18%;" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list2}" var="i">
                                            <option value="${i.getProjectName()}">${i.getProjectName()}</option>
                                        </c:forEach>   
                                    </select>
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    Create Date:
                                    <select name="CreatedDate" style= "margin-left: 20px; width: 18%; margin-right: 15px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list3}" var="g">
                                            <option value="${g.getCreatedDate()}">${g.getCreatedDate()}</option>
                                        </c:forEach>   
                                    </select>
                                    <br>
                                    Create By:
                                    <select name="CreatedBy1" style= "width: 18%; margin-top: 5px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list4}" var="g">
                                            <option value="${g.getCreatedBy1()}">${g.getCreatedBy1()}</option>
                                        </c:forEach>   
                                    </select>
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    In-charge:
                                    <select name="Incharge1" style= "margin-left: 45px; width: 18%; margin-right: 15px;margin-top: 5px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list5}" var="g">
                                            <option value="${g.getIncharge1()}">${g.getIncharge1()}</option>
                                        </c:forEach>   
                                    </select>

                                    Status:
                                    <select name="istatus" style= "margin-left: 58px; width: 18%; margin-right: 5px; margin-top: 5px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                        <option selected value="">---All---</option>
                                        <c:forEach items="${list6}" var="g">
                                            <option value="${g.getIstatus()}">${g.getIstatus()}</option>
                                        </c:forEach>   
                                    </select>
                                    <input type="submit" class="btn btn-primary btn-submit" style="font-size: 17px; margin-left: 5px" value="Search"/>
                                </form>
                                <form action="searchissue" method="post">
                                    <input type="text" placeholder="Search by Title" name="title" value="${requestScope.title}" style="width: 40%; margin-top: 10px; margin-left: 30%" aria-controls="zero-conf" class="form-control form-control-sm"/>
                                </form>
                                <table class="display dataTable" style="width: 100%;" role="grid" aria-describedby="zero-conf_info">
                                    <thead>
                                        <tr role="row">
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width:90px;">Create Date</th>
                                            <th class="sorting_desc" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 100px;" aria-sort="descending">Issue Title</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 60px;">Issue Type</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 100px;">Project Name</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 120px;">Created By</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 120px;">In-charge</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 60px;">Status</th>
                                            <th class="sorting" tabindex="0" aria-controls="zero-conf" rowspan="1" colspan="1" style="width: 90px;">Updated Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.list}" var="u">
                                            <tr role="row" class="odd">
                                                <td class="">${u.getDate()}</td>
                                                <td class="sorting_1">${u.getTitle()}</td>
                                                <td>${u.getItype()}</td>
                                                <td>${u.getProjectName()}</td>
                                                <td>${u.getCreatedBy1()}</td>
                                                <td>${u.getIncharge1()}</td>
                                                <td>${u.getIstatus()}</td>
                                                <td>${u.getUDate1()}</td>
                                                <td>
                                                    <a href="#deleteModal" onclick="myFunction(${u.id})" data-toggle="modal" class="btn btn-secondary">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>                                           
                                </table>
                                <c:set var="page" value="${requestScope.page}"/>
                                <c:set var="num" value="${requestScope.num}"/>
                                <ul class="pagination">
                                    <li class="paginate_button page-item previous ${page==1?'disabled':''}" id="zero-conf_previous">
                                        <a href="issuelist?page=${page-1}" aria-controls="zero-conf" data-dt-idx="${i}" aria-controls="zero-conf" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                                    </li>
                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                        <li class="paginate_button page-item ${page==i?'active':''}">
                                            <a href="issuelist?page=${i}" aria-controls="zero-conf" data-dt-idx="${i}" tabindex="0" class="page-link">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="paginate_button page-item next ${page==num?'disabled':''}" id="zero-conf_next">
                                        <a href="issuelist?page=${page+1}" aria-controls="zero-conf" data-dt-idx="4" tabindex="0" class="page-link">Next</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="deleteModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="dele" method="get">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Alert</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <p>Are you sure you want to delete issue?</p>
                                    <input type="text" id="id" name="id" readonly="" style="display:none">
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-secondary" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-danger" value="Delete">
                                </div>
                            </form>
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
        <script>
            function myFunction(id) {
                $(".modal-body #id").val(id);
            }
        </script>
    </body>
</html>

