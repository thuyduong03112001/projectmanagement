<%-- 
    Document   : settingList
    Created on : Jan 14, 2022, 9:16:10 AM
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
        <title>Setting List</title>
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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            form .submit-button {
                background: linear-gradient(90deg,#55c3b7 0,#5fd0a5 48%,#66da90 100%);
                color: #fff!important;
                border-color: #5fd0a5!important;
            }
            a.action{
                padding: 5px;
                background: linear-gradient(90deg,#55c3b7 0,#5fd0a5 48%,#66da90 100%);
                color: #fff!important;
                border-color: #5fd0a5!important;
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
            .Empty{
                font-style: italic;
                text-align: center;
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
                                <li class="breadcrumb-item active" aria-current="page">Setting List</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 class="card-title" style="text-align: center; font-size: 30px;">Setting list</h1>
                                        <form action="SettingList">
                                            <div class="tableFilter" >
                                                <label>Type:
                                                    <select name="type"  aria-controls="zero-conf" class=" custom-select custom-select-sm form-control form-control-sm" id="exampleFormControlSelect1">
                                                        <option selected value="">--All--</option>
                                                        <c:forEach var="t" items="${typeSettingList}">
                                                            <option value="${t.id}" ${requestScope.type == t.id? 'selected':''}>${t.typeName}</option>
                                                        </c:forEach>
                                                    </select> 
                                                </label>
                                            </div>
                                            <div class="tableFilter" >
                                                <label> Status: 
                                                    <select name="status" aria-controls="zero-conf" class="custom-select custom-select-lg form-control form-control-lg" id="exampleFormControlSelect2">
                                                        <option selected value="">--All--</option>
                                                        <option value="1" ${requestScope.status == 1? 'selected':''}>Activate</option>
                                                        <option value="2" ${requestScope.status == 2? 'selected':''}>Deactivate</option>
                                                    </select> 
                                                </label>
                                            </div>
                                            <div  class="tableFilter">
                                                <label>Value: <input value="${requestScope.value}" type="search" class="form-control form-control-sm" aria-controls="zero-conf" name="value" placeholder="Search...">
                                                </label>
                                            </div>
                                            <input class="submit-button" type="submit" value="Filter"/>
                                        </form>
                                        <table id="zero-conf" class="display dataTable" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th><a href="SettingList?status=${requestScope.status}&type=${requestScope.type}&value=${requestScope.value}&sortby=id">id</a></th>
                                                    <th><a href="SettingList?status=${requestScope.status}&type=${requestScope.type}&value=${requestScope.value}&sortby=typename">type</a></th>
                                                    <th><a href="SettingList?status=${requestScope.status}&type=${requestScope.type}&value=${requestScope.value}&sortby=value">value</a></th>
                                                    <th><a href="SettingList?status=${requestScope.status}&type=${requestScope.type}&value=${requestScope.value}&sortby=order">order</a></th>
                                                    <th><a href="SettingList?status=${requestScope.status}&type=${requestScope.type}&value=${requestScope.value}&sortby=status">status</a></th>
                                                    <th><a href="">action</a></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="i" items="${settingList}" varStatus="status">
                                                    <tr role="row" class="${status.index % 2 == 0 ? 'even' : 'odd'}">
                                                        <td>${i.id}</td>
                                                        <td>${i.type}</td>
                                                        <td>${i.value}</td>
                                                        <td>${i.order}</td>
                                                        <td>${i.status==1?'Active':'Deactive'}</td>
                                                        <td>
                                                            <a href="activateordeactivate?settingid=${i.id}" class="action">${i.status==1?'Deactivate':'Activate'}</a>
                                                            <a href="editsetting?settingid=${i.id}" class="action">Edit/View</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                        <c:set var="page" value="${requestScope.page}"/>
                                        <c:set var="n" value="${requestScope.num}"/>
                                        <c:if test="${n==0}">
                                            <p class="Empty">
                                                No Result Matches
                                            </p>
                                        </c:if>
                                        <c:if test="${n>0}">
                                            <ul class="pagination">
                                                <li class="paginate_button page-item previous ${page==1?'disabled':''}" id="zero-conf_previous">
                                                    <a href="SettingList?status=${requestScope.status}&sortby=${requestScope.sortby}&type=${requestScope.type}&value=${requestScope.value}&page=${page-1}" aria-controls="zero-conf" data-dt-idx="${i}" aria-controls="zero-conf" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                                                </li>
                                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                    <li class="paginate_button page-item ${page==i?'active':''}">
                                                        <a href="SettingList?status=${requestScope.status}&sortby=${requestScope.sortby}&type=${requestScope.type}&value=${requestScope.value}&page=${i}" aria-controls="zero-conf" data-dt-idx="${i}" tabindex="0" class="page-link">${i}</a>
                                                    </li>
                                                </c:forEach>
                                                <li class="paginate_button page-item next ${page==n?'disabled':''}" id="zero-conf_next">
                                                    <a href="SettingList?status=${requestScope.status}&sortby=${requestScope.sortby}&type=${requestScope.type}&value=${requestScope.value}&page=${page+1}" aria-controls="zero-conf" data-dt-idx="4" tabindex="0" class="page-link">Next</a>
                                                </li>
                                            </ul>
                                        </c:if>

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

        <footer>

        </footer>
    </body>
</html>
