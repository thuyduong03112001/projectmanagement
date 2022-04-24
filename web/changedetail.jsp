<%-- 
    Document   : changedetail
    Created on : Mar 12, 2022, 11:08:51 AM
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
            .AssignMe{
                padding: 12px 0px;
                height: auto;
                border-radius: 25px;
                border: none;
                font-size: 13px;
                font-weight: 700;
                color: #5fd0a5;
                text-decoration: underline;
            }
            .AssignMe:hover{
                cursor: pointer;
                color: green;
            }
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
                    <c:set var="rc" value="${requestScope.reqChange}"/>
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item"><a href="RequirementList">Requirement List</a></li>
                                <li class="breadcrumb-item"><a href="RequirementChange?rid=${rc.requirementId}">Requirement Changes</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Change detail</li>
                            </ol>
                        </nav>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <h1 style="text-align: center; font-size: 30px;">Change Detail</h1>
                                        <form class="needs-validation" novalidate action="ChangeDetail" method="post">

                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-4 mb-3">
                                                    <label for="validationCustom06">Title</label>
                                                    <input type="text" class="form-control" id="validationCustom06" name="title" value="${rc.title}" placeholder="Title" required>
                                                    <div class="invalid-feedback">
                                                        Please Enter a Title
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect3">Requirement Type:</label>
                                                        <select class="col-md-12 form-control custom-select" id="exampleFormControlSelect3" name="typeId" required>
                                                            <option class="is-invalid" selected disabled>--Choose One--</option>
                                                            <c:forEach var="a" items="${reqTypeList}">
                                                                <option ${rc.typeId eq a.id ? 'selected' : ''} value="${a.id}">${a.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <div class="invalid-feedback">
                                                            Please Choose a Change type
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlTextarea1">Detail: </label>
                                                        <textarea name="detail" class="form-control" id="exampleFormControlTextarea1" required rows="3">${rc.detail}</textarea>
                                                        <div class="invalid-feedback">
                                                            Please Enter a Title
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-6 mb-3">
                                                    <label for="validationCustom06">Version</label>
                                                    <input type="text" class="form-control" id="validationCustom06" name="version" value="${rc.version}" placeholder="Title" required>
                                                    <div class="invalid-feedback">
                                                        Please Enter a Title
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-3 mb-3"></div>
                                                <div class="col-md-3 mb-3">
                                                    <div class="form-group">
                                                        <label for="exampleFormControlSelect2">Editor's Name</label>
                                                        <select class="form-control custom-select" id="exampleFormControlSelect5" name="eid" required>
                                                            <option selected disabled value="">--Choose One--</option>
                                                            <c:forEach var="a" items="${accList}">
                                                                <option ${rc.editor.id eq a.id ? 'selected' : ''} value="${a.id}">${a.fullName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="invalid-feedback">
                                                        Please Choose a Project
                                                    </div>
                                                </div>
                                                <div class="col-md-2 mb-3">
                                                    <label for="ass" style="color: white">.</label>
                                                    <p id="ass" class="AssignMe" onclick="document.getElementById('exampleFormControlSelect5').value = ${requestScope.myId};
                                                           $('#exampleFormControlSelect5').select2().trigger('change');">Assigned me</p>
                                                </div>
                                            </div>


                                            <div class="form-row">
                                                <div class="col-md-5 mb-3"></div>
                                                <div class="col-md-2 mb-3">
                                                    <c:if test="${requestScope.action eq 'update'}">
                                                        <input type="hidden" name="action" value="update">
                                                        <input type="hidden" name="rcid" value="${rc.id}">
                                                        <input type="hidden" name="rid" value="${rc.requirementId}">
                                                    </c:if>
                                                    <c:if test="${!(requestScope.action eq 'update')}">
                                                        <input type="hidden" name="action" value="add">
                                                        <input type="hidden" name="rid" value="${requestScope.reqId}">
                                                        <input type="hidden" name="rcid" value="${rc.id}">

                                                    </c:if>
                                                    <button class="col-md-12 btn btn-primary" type="submit" onclick="this.form.submit()">
                                                        <c:if test="${requestScope.action eq 'update'}">
                                                            Update
                                                        </c:if>
                                                        <c:if test="${!(requestScope.action eq 'update')}">
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
        <script>
            function assignMe(var id) {
            alert(id);
            document.getElementById("exampleFormControlSelect5").value = id;
            $('#exampleFormControlSelect5').('refresh');
            }
        </script>
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
