<%-- 
    Document   : grouplist
    Created on : Feb 12, 2022, 2:13:48 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Group List</title>
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">   
        <!--<link href="assets/plugins/DataTables/datatables.min.css" rel="stylesheet">-->   

        <!-- Theme Styles -->
        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            a.addordelete, form .submit-button {
                background: linear-gradient(90deg,#55c3b7 0,#5fd0a5 48%,#66da90 100%);
                color: #fff!important;
                border-color: #5fd0a5!important;
            }
            a.addordelete{
                padding: 5px;
            }
            div.tableFilter{
                display: inline-block;
            }
            div.tableFilter label {
                font-weight: normal;
                text-align: left;
                white-space: nowrap;
            }

            div.tableFilter select {
                width: 200px;
                display: inline-block
            }

            div.dataTables_filter {
                text-align: right
            }

            div.dataTables_filter label {
                font-weight: normal;
                white-space: nowrap;
                text-align: left
            }

            div.tableFilter input {
                margin-left: 0.5em;
                display: inline-block;
                width: auto
            }

            div.dataTables_info {
                padding-top: 0.85em;
                white-space: nowrap
            }

            div.dataTables_paginate {
                margin: 0;
                white-space: nowrap;
                text-align: right
            }

            div.dataTables_paginate ul.pagination {
                margin: 2px 0;
                white-space: nowrap;
                justify-content: flex-end
            }

            div.dataTables_processing {
                position: absolute;
                top: 50%;
                left: 50%;
                width: 200px;
                margin-left: -100px;
                margin-top: -26px;
                text-align: center;
                padding: 1em 0
            }
            @media screen and (max-width: 767px) {
                div.tableFilter,
                div.dataTables_filter,
                div.dataTables_info,
                div.dataTables_paginate {
                    text-align: center
                }
            }
            .card .card-title{
                text-align: center;
                font-size: 30px;
            }

        </style>
    </head>
    <body>
        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <jsp:include page="sidebar.jsp"/>
            <div class="page-container">
                <jsp:include page="header.jsp"/>
                <div class="page-content">
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Group List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 class="card-title" style="text-align: center; font-size: 30px;">Group list</h1>
                                        <a class="addordelete" href="AddOrUpdate"> Add/Update</a>
                                        <form action="GroupList" method="get">
                                            <div class="tableFilter" >
                                                <label>Parent group name:
                                                    <select name="pid"  aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                                        <option selected value="">--All--</option>
                                                        <c:forEach var="t" items="${parentGroupList}">
                                                            <option ${requestScope.pid eq t.id ? 'selected' : ''} value="${t.id}">${t.name}</option>
                                                        </c:forEach>
                                                    </select> </label>
                                            </div>
                                            <div class="tableFilter" >
                                                <label>Group type:
                                                    <select name="type"  aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                                        <option selected value="">--All--</option>
                                                        <c:forEach var="t" items="${typeGroupList}">
                                                            <option ${requestScope.typeId eq t.id ? 'selected' : ''} value="${t.id}">${t.value}</option>
                                                        </c:forEach>
                                                    </select> </label>
                                            </div>
                                            <div class="tableFilter" >
                                                <label>Status
                                                    <select name="status"  aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                                        <option selected value="">--All--</option>
                                                        <c:forEach var="s" items="${statusGroupList}">
                                                            <option ${requestScope.statusId eq s.id ? 'selected' : ''} value="${s.id}">${s.value}</option>
                                                        </c:forEach>
                                                    </select> </label>
                                            </div>
                                            <div  class="tableFilter">
                                                <label>Search: <input value="${requestScope.value}" type="search" class="form-control form-control-sm" aria-controls="zero-conf" name="value" placeholder="Search...">
                                                </label>
                                            </div>
<!--                                            <label>
                                                <select name="findby"  aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                                    <option ${requestScope.findby eq "fname" ? 'selected' : ''} value="fname">Search by name</option>
                                                    <option ${requestScope.findby eq "fid" ? 'selected' : ''} value="fid">Search by id</option>
                                                </select>
                                            </label>-->
                                            <button class="btn btn-primary" type="submit">Search</button>
                                        </form>
                                        <table id="zero-conf" class="display dataTable" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>Name</th>
                                                    <th>Type</th>
                                                    <th>Join Date</th>
                                                    <th>Manager</th>
                                                    <th>Parent</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="i" items="${groupList}" varStatus="status">
                                                    <tr role="row" class="${status.index % 2 == 0 ? 'even' : 'odd'}">
                                                        <td>${i.id}</td>
                                                        <td>${i.name}</td>
                                                        <td>${i.type.value}</td>
                                                        <td>${i.joinDate}</td>
                                                        <td>
                                                            <c:forEach var="a" items="${i.managers}" varStatus="status">
                                                                ${a.fullName}<br>
                                                            </c:forEach>
                                                        </td>
                                                        <td>${i.parent.name}</td>
                                                        <td>${i.status.value}</td>
                                                        <td>
                                                            <c:if test="${i.status.id == 25}">
                                                                <a class="addordelete delete" href="DeleteGroup?groupId=${i.id}">delete</a>
                                                            </c:if>
                                                            <c:if test="${i.status.id == 24}">
                                                                <a class="addordelete" href="RecoverGroup?groupId=${i.id}">recover</a>
                                                            </c:if>
                                                                <a class="addordelete" href="AddOrUpdate?groupId=${i.id}"> View/Update</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                        <c:set var="page" value="${requestScope.page}"/>
                                        <c:set var="n" value="${requestScope.num}"/>
                                        <ul class="pagination">
                                            <li class="paginate_button page-item previous ${page==1?'disabled':''}" id="zero-conf_previous">
                                                <a href="GroupList?pid=${requestScope.pid}&type=${requestScope.type}&status=${requestScope.status}&value=${requestScope.value}&findby=${requestScope.findby}&page=${page-1}" aria-controls="zero-conf" data-dt-idx="${i}" aria-controls="zero-conf" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                                            </li>
                                            <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                <li class="paginate_button page-item ${page==i?'active':''}">
                                                    <a href="GroupList?pid=${requestScope.pid}&type=${requestScope.type}&status=${requestScope.status}&value=${requestScope.value}&findby=${requestScope.findby}&page=${i}" aria-controls="zero-conf" data-dt-idx="${i}" tabindex="0" class="page-link">${i}</a>
                                                </li>
                                            </c:forEach>
                                            <li class="paginate_button page-item next ${page==n?'disabled':''}" id="zero-conf_next">
                                                <a href="GroupList?pid=${requestScope.pid}&type=${requestScope.type}&status=${requestScope.status}&value=${requestScope.value}&findby=${requestScope.findby}&page=${page+1}" aria-controls="zero-conf" data-dt-idx="4" tabindex="0" class="page-link">Next</a>
                                            </li>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

                <%@include file="footer.jsp"%>
            </div>





        </div>

        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
        <script src="assets/js/pages/datatables.js"></script>
    <script src="assets/js/pages/select2.js"></script>
    <script src="assets/plugins/select2/js/select2.full.min.js"></script>
        <!--<script src="assets/plugins/DataTables/datatables.min.js"></script>-->
    </body>
</html>
