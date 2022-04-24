<%-- 
    Document   : groupdetail
    Created on : Feb 13, 2022, 3:36:23 AM
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
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Group detail</li>
                            </ol>
                        </nav>
                    </div>
                    <c:set var="g" value="${requestScope.group}"></c:set>
                        <div class="main-wrapper">
                            <div class="row">
                                <div class="col">
                                    <div class="card">
                                        <div class="card-body">
                                            <h1 style="text-align: center">Group Detail</h1>
                                            <form class="needs-validation" novalidate action="AddOrUpdate" method="post">

                                                <input type="hidden" name="id" value="${g.id}"/>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="validationCustom06">Group Name</label>
                                                    <input type="text" value="${g.name}" class="form-control" id="validationCustom06" name="name" placeholder="Group Name" required>
                                                    <div class="invalid-feedback">
                                                        Please Enter a Name
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect1">Manager</label>
                                                        <select class="js-states form-control" tabindex="-1" style="display: none; width: 100%" multiple="multiple" name="mid">
                                                            <c:set var="temp" value="0"></c:set>
                                                            <c:forEach var="a" items="${accList}">
                                                                <c:forEach var="m" items="${g.managers}">
                                                                    <c:if test="${m.id == a.id}">
                                                                        <option  value="${a.id}" selected >${a.fullName}</option>
                                                                        <c:set var="temp" value="1"></c:set>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <c:if test="${temp eq 0}">
                                                                    <option  value="${a.id}" >${a.fullName}</option>
                                                                </c:if>
                                                                <c:set var="temp" value="0"></c:set>   
                                                            </c:forEach>
                                                        </select>
                                                        <div class="invalid-feedback">
                                                            Please choose a Name
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect2">Parent Group Name</label>
                                                        <select class="form-control custom-select" id="exampleFormControlSelect2" name="pid" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${groupList}">
                                                                <option  value="${a.id}" ${g.parentId == a.id?'selected':''}>${a.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Group
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-3 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect3">Group Type:</label><br/>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <div class="form-check form-check-inline custom-radio">
                                                            <input class="custom-control-input" type="radio" name="type" id="exampleRadios3" value="22" ${g.typeId eq 22?'checked':''}>
                                                            <label class="custom-control-label" for="exampleRadios3">
                                                                BA
                                                            </label>
                                                            
                                                        </div>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <div class="form-check form-check-inline custom-radio">
                                                            <input class="custom-control-input" type="radio" name="type" id="exampleRadios4" value="23" ${g.typeId eq 23?'checked':''}>
                                                            <label class="custom-control-label" for="exampleRadios4">
                                                                Non-BA
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Group type
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-3 " style="border-left: 2px solid #e8e8e8 ">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect4">Group Status:</label><br/>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <div class="form-check form-check-inline custom-radio">
                                                            <input class="custom-control-input" type="radio" name="status" id="exampleRadios1" value="25" ${g.statusId eq 25?'checked':''}>
                                                            <label class="custom-control-label" for="exampleRadios1">
                                                                Working
                                                            </label>
                                                            
                                                        </div>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <div class="form-check form-check-inline custom-radio">
                                                            <input class="custom-control-input" type="radio" name="status" id="exampleRadios2" value="24" ${g.statusId eq 24?'checked':''}>
                                                            <label class="custom-control-label" for="exampleRadios2">
                                                                Deleted
                                                            </label>
                                                        </div>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Group status
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>

                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlTextarea1">Group Description: </label>
                                                        <textarea name="des" class="form-control" id="exampleFormControlTextarea1" rows="3">${g.description}</textarea>
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
