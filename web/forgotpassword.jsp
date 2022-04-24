<%-- 
    Document   : forgotpassword
    Created on : Feb 15, 2022, 10:04:27 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:300i,400,700&display=swap" rel="stylesheet">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
      <style>
         body {
         background-color: #F8FAF9;
         font-family: Nunito Sans;
         color: #7D7D83;
         }
         .btn {
         background-color: #5fd0a5;
         width: 100%;
         color: #fff;
         padding: 5px;
         font-size: 18px;
         }
         .btn:hover {
         background-color: #2d3436;
         color: #fff;
         }
         input {
         height: 50px !important;
         }
         .form-control:focus {
         border-color: #18dcff;
         box-shadow: none;
         }
         h3 {
         color: #5fd0a5;
         font-size: 36px;
         }
         .cw {
         width: 35%;
         }
         @media(max-width: 992px) {
         .cw {
         width: 60%;
         }
         }
         @media(max-width: 768px) {
         .cw {
         width: 80%;
         }
         }
         @media(max-width: 492px) {
         .cw {
         width: 90%;
         }
         }
      </style>
    </head>
    <body>
        <div class="container d-flex justify-content-center align-items-center vh-100">
         <div class="bg-white text-center p-5 mt-3 center">
            <h3>Forgot Password </h3>
            <p>Please enter your login email, we'll send a new random password to your inbox:</p>
            <form class="pb-3" action="newpass" method="post">
               <div class="form-group">
                  <input type="email" class="form-control" placeholder="Your email" name="account" required>
               </div>
                <c:if test="${requestScope.msgCommon!=null}">
                    <div style="text-align: center;">
                        <p>${requestScope.msgCommon}</p>
                    </div>
                </c:if>
            </form>
            <button type="button" class="btn">Reset Password</button>
            <a href="login.jsp" class="btn" style="margin-top: 10px"> Sign in</a>
         </div>
      </div>
    </body>
</html>
