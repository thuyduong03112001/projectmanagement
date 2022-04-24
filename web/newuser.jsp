
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <title>Create User</title>

        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">  


        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/admin2.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">
    <html>
        <head>
        <body class="auth-page sign-in">
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <body class="auth-page sign-in">

            <div class='loader'>
                <div class='spinner-grow text-primary' role='status'>
                    <span class='sr-only'>Loading...</span>
                </div>
            </div>
            <div class="connect-container align-content-stretch d-flex flex-wrap">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-5">
                            <div class="auth-form">
                                <div class="row">
                                    <div class="col">
                                        <div class="logo-box"><a href="#" class="logo-text">workflowBOX</a></div>
                                        <h4 style="color:red;font-weight:bold">${alert}</h4>
                                        <form action="load" method="post">
                                            <div class="form-group">
                                                <input required="" pattern="[A-Za-z\d\.}]{6,12}" title="Username must bettween 6-12 character,contains only letters,numbers" type="text" placeholder="Enter Username" name="account" class="form-control" id="account" value="${account1}">
                                            </div>
                                            <div class="form-group">
                                                <input required="" pattern="^[a-zA-Z\s]{15,}" title="Fullname cannot not contains any digits and more than 15 character"  type="text" name="name" class="form-control" id="name" placeholder="Enter Fullname" value=${name1}>
                                            </div>
                                            <div class="form-group">
                                                <input required="" type="email" name="email" class="form-control" id="email" placeholder="Enter Email"  value=${email1}>
                                            </div>
                                            <div class="form-group">
                                                <input required="" type="text" pattern="(?=0{1})[0-9]{10}$" title="PhoneNumber must start with 0,only numbers" name="phone" type="text" name="phone" class="form-control" id="phone" placeholder="Enter Phonenumber"  value=${phone1}>
                                            </div>
                                            <div class="form-group">
                                                <label required="" for="Male">Male</label> <input type="radio" name="gender" value="12"/>
                                                <label required="" style="padding-left: 100px" for="Female">Female</label> <input type="radio" name="gender" value="13"/>
                                            </div>
                                            <div class="form-group">
                                                <label required="" for="Admin">Admin</label> <input type="radio" name="position" value="4"/>
                                                <label required="" style="padding-left: 50px" for="HR">HR</label> <input type="radio" name="position" value="5"/>
                                                <label required="" style="padding-left: 50px" for="Manager">Manager</label> <input type="radio" name="position" value="6"/>
                                                <label required="" style="padding-left: 50px" for="Female">Staff</label> <input type="radio" name="postion" value="7"/>
                                            </div>
                                            <div class="row">
                                                <div class="col-xl">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <h5 class="card-title">Choose group</h5>
                                                            <select name="groups" class="js-states form-control" tabindex="-1" style="width: 100%">
                                                                <optgroup label="">
                                                                    <c:forEach items="${listG}" var="g">
                                                                    <option value=${g.id}>${g.name}</option>
                                                                    </c:forEach>
                                                                </optgroup>
                                                            </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                            <p id="message" style="color:red;font-weight: bold"></p>
                                                            <button type="submit" class="btn btn-primary btn-block btn-submit">Create</button>
                                                            <div class="auth-options">
                                                                <div class="custom-control custom-checkbox form-group">
                                                                    <input type="checkbox" class="custom-control-input" id="exampleCheck1">
                                                                    <label class="custom-control-label" for="exampleCheck1">Sign me in</label>
                                                                </div>
                                                                <a href="#" class="forgot-link">Already have an account?</a>
                                                            </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 d-none d-lg-block d-xl-block">
                                                <div class="auth-image"></div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                            <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
                            <script src="assets/plugins/bootstrap/popper.min.js"></script>
                            <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
                            <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
                            <script src="assets/js/connect.min.js"></script>
                             <script src="assets/js/pages/select2.js"></script>
                            </body>
                            </html>
