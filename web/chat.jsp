<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="UTF-8">
        <title>Chat</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'><link rel="stylesheet" href="css/chat.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <!--<link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet">-->  

        <!-- Theme Styles -->
        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">
        <style>
            span.title>span {
                display: none;
            }
        </style>
    </head>
    <body>
        <!-- partial:index.partial.html -->
        <div class="floating-chat" style="z-index: 9999; background: snow;" onclick="setupChat()">
            <i class="fa fa-comments" aria-hidden="true" onclick="myCall()" style="color: black;"></i>
            <div class="chat">
                <div class="header" >
                    <span class="title">
                        <c:set var="count" value="${0}"/>
                        <jsp:useBean id="prdb" class="dal.ProjectDAO"/> 
                        <select class="boxchatselect" id="project" onchange="myCall()" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important;">
                            <c:forEach items="${prdb.getAllByStaff(sessionScope.account.id)}" var="pro">
                                <option ${count==0?'selected':''} value="${pro.id}">${pro.name}</option>
                                ${count = count+1}
                            </c:forEach>
                        </select>
                    </span>
                    <button>
                        <i class="fa fa-times" aria-hidden="true" style="color: black;"></i>
                    </button>

                </div>
                <ul class="messages" id="messages">
                </ul>
                <div class="footer" >
                    <form id="contactForm" style="display: flex;" onsubmit="getMyTime()">
                        <input type="text" style="display: none;" Value="${sessionScope.account.fullName}" class="form-control fullname" required>
                        <input type="text" style="display: none;" value="${sessionScope.account.id}" class="form-control id" required>
                        <input type="text" style="display: none;" value="1" id="projectid" class="form-control projectid" required>
                        <input type="text" style="display: none;" value="today" id="myTime" class="form-control time" required>
                        <input type="text" class="form-control message" placeholder="Enter Message" required/>
                        <input type="submit" style=" border-radius: 25px; " id="sendMessage" class="btn btn-primary" value="send">
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://www.gstatic.com/firebasejs/7.15.5/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/7.15.5/firebase-database.js"></script>
        <script src="js/config.js"></script>
        <script type="text/javascript">
                        // Reference messages collection
                        var messagesRef = firebase.database().ref('contactformmessages');
                        $('#contactForm').submit(function (e) {
                            e.preventDefault();
                            var newMessageRef = messagesRef.push();
                            newMessageRef.set({
                                name: $('.fullname').val(),
                                id: $('.id').val(),
                                message: $('.message').val(),
                                projectid: $('.projectid').val(),
                                time: $('.time').val()
                            });
                            $('#contactForm')[0].reset();
                        });

        </script>
        <script>
            function myCall() {
                document.getElementById("messages").innerHTML = "";
                firebase.database().ref("contactformmessages").on("child_added", function (snapshot) {
                    var html = "";
                    // give each message a unique ID     
                    // show delete button if message is sent by me
                    if (snapshot.val().projectid == document.getElementById("project").value) {
                        html += "<li id='abc" + snapshot.key + "'";
                        if (snapshot.val().id == ${sessionScope.account.id}) {
                            html += ' class="self ' + snapshot.key + '"' + "'>";
                        } else {
                            html += ' class="other ' + snapshot.key + '"' + "'>";
                        }
                        html += snapshot.val().name + ": " + snapshot.val().message + " <br> " + snapshot.val().time;
                        html += "</li>";
                    }

                    document.getElementById("messages").innerHTML += html;
                    removeElementsByClass(snapshot.key);
                });
            }

            function setupChat() {
                document.getElementById("project").className = "hihi";
                myCall();
            }
        </script>
        <script>
            function getMyTime() {
                var today = new Date();
                var date = today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear();
                var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
                var dateTime = date + ' ' + time;
                document.getElementById('myTime').value = dateTime;
                document.getElementById('projectid').value = document.getElementById('project').value;
            }
        </script>
        <script>
            function removeElementsByClass(className) {
                const elements = document.getElementsByClassName(className);
                while (elements.length > 1) {
                    elements[0].parentNode.removeChild(elements[0]);
                }
            }
            
        </script>
        <!-- partial -->
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js'></script><script  src="js/chat.js"></script>
        <!--<script src="assets/js/pages/select2.js"></script>-->
        <!--<script src="assets/plugins/select2/js/select2.full.min.js"></script>-->
    </body>
</html>
