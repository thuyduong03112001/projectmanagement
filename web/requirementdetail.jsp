<%-- 
    Document   : requirementdetail
    Created on : Feb 27, 2022, 7:49:50 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Group Detail</title>
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">   

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
            .form-detail{
                width: 50%; 
                margin: auto;
            }
            table{
                width: 100%;
                text-align: left;
            }
            table th{
                width: 40%;
            }
            table td{
                width: 55%;
            }
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
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
                                <li class="breadcrumb-item"><a href="RequirementList">Requirement List</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Requirement detail</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 style="text-align: center">Requirement Detail</h1>
                                        <c:set var="r" value="${requestScope.req}"/>
                                        <form class="needs-validation" novalidate action="RequirementDetail" method="post">
                                            <c:if test="${requestScope.action eq 'update'}">
                                                <div class="form-row">
                                                    <div class="col-md-3 mb-3"></div>
                                                    <div class="col-md-4 mb-3">
                                                        <label for="validationCustom05">Requirement ID</label>
                                                        <input type="text" class="form-control" id="validationCustom05" name="rid" readonly value="${r.id}" required>
                                                    </div>
                                                    <div class="col-md-2 mb-3">
                                                        <label for="validationCustom05">Updated Date</label>
                                                        <input type="text" class="form-control" id="validationCustom05" name="date" readonly value="${r.joinDate}" required>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="validationCustom06">Requirement Title</label>
                                                    <input type="text" class="form-control" id="validationCustom06" name="title" value="${r.title}" placeholder="Requirement Title" required>
                                                    <div class="invalid-feedback">
                                                        Please Enter a Title
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect2">Project Name</label>
                                                        <select class="form-control custom-select" id="exampleFormControlSelect1" name="pid" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${projectList}">
                                                                <option ${r.projectId eq a.id ? 'selected' : ''} value="${a.id}">${a.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Project
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect2">Group Name</label>
                                                        <select class="form-control custom-select" id="exampleFormControlSelect2" name="gid" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${groupList}">
                                                                <option ${r.groupId eq a.id ? 'selected' : ''} value="${a.id}">${a.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Project
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect2">Owner's Name</label>
                                                        <select class="form-control custom-select" id="exampleFormControlSelect5" name="owner" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${ownerList}">
                                                                <option ${r.owner.id eq a.id ? 'selected' : ''} value="${a.id}">${a.fullName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Project
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-3 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect3">Requirement Type:</label>
                                                        <select class="col-md-12 form-control custom-select" id="exampleFormControlSelect3" name="typeId" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${reqTypeList}">
                                                                <option ${r.typeId eq a.id ? 'selected' : ''} value="${a.id}">${a.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Requirement type
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect4">Requirement Status:</label>
                                                        <select class="col-md-12 form-control custom-select" id="exampleFormControlSelect4" name="statusId" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${reqStatusList}">
                                                                <option ${r.statusId eq a.id ? 'selected' : ''} value="${a.id}">${a.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Requirement status
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlTextarea1">Requirement Detail: </label>
                                                        <textarea name="rdetail" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-5 mb-3"></div>
                                                <div class="col-md-2 mb-3">
                                                        <c:if test="${requestScope.action eq 'update'}">
                                                            <input type="hidden" name="action" value="update">
                                                        </c:if>
                                                        <c:if test="${requestScope.action eq 'add'}">
                                                            <input type="hidden" name="action" value="add">
                                                        </c:if>
                                                    <button class="col-md-12 btn btn-primary" type="submit">
                                                        <c:if test="${requestScope.action eq 'update'}">
                                                            Update
                                                        </c:if>
                                                        <c:if test="${requestScope.action eq 'add'}">
                                                            Add
                                                        </c:if>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
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
        <script src="assets/js/pages/select2.js"></script>
        <script src="assets/plugins/select2/js/select2.full.min.js"></script>


    </body>
</html>
