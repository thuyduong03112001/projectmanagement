<%-- 
    Document   : confirm.jsp
    Created on : Feb 10, 2022, 11:40:09 AM
    Author     : HP
--%>

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
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">


        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/admin2.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">
    </head>
    <body style="background-image:url('assets/images/form.jpg');height: 100%;background-position: center;background-repeat: no-repeat;background-size: cover">
        <div id="form">
            <h4 style="font-family: monospace;font-size: 20px;color: black;text-align: center">Please enter your new password</h4>
            <form action="reset" method="post">
                <div class="form-group">
                    <input required="" onClick="checkPass()" type="password" placeholder="Enter Password" name="password" class="form-control" id="password">
                </div>
                <div class="form-group">
                    <input required=""  type="password" name="repassword" class="form-control" id="repassword" placeholder="Verify your password">
                </div>
                <p style="color:red;font-size:20px;text-align: center;font-weight: bold">${text}</p>
                <input style="margin-left: 130px;width: 100px;height: 40px;border-radius: 10%" type="submit" value="RESET"> 
            </form>
            <style>
                #form{
                    border-radius: 10%;
                    margin-top: 200px;          
                    width: 350px;
                    height: 240px;
                    margin-left: 500px;
                }
                h3{
                    font-family:monospace;
                    font-size: 20px;
                    margin-left: 80px;
                    margin-bottom: 50px;
                    color: black;
                }
            </style>
    </body>
</html>
