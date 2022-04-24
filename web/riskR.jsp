<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Admin Dashboard Template">
        <meta name="keywords" content="admin,dashboard">
        <meta name="author" content="stacks">
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Connect - Responsive Admin Dashboard Template</title>

        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">


        <!-- Theme Styles -->
        <link href="assets/css/connect.min.css" rel="stylesheet">
        <link href="assets/css/dark_theme.css" rel="stylesheet">
        <link href="assets/css/custom.css" rel="stylesheet">

    </head>
    <body>
        <style>
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: #FFF44F;
                color: black;
            }
        </style>
        <div class='loader'>
            <div class='spinner-grow text-primary' role='status'>
                <span class='sr-only'>Loading...</span>
            </div>
        </div>
        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <%@include file="sidebar.jsp"%>
            <div class="page-container">
                <%@include file="header.jsp"%>
                <div class="page-content">  

                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-xl">
                                <div class="card">
                                    <div class="card-body" style="padding:15px">
                                        <a href="#addModal" style="margin-left:1260px" class="btn btn-success" data-toggle="modal"><i class="material-icons"></i> <span>Add</span></a>						
                                        <h5 class="card-title">Risk Response List</h5>
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Action</th>
                                                        <th scope="col">FromDate</th>
                                                        <th scope="col">ToDate</th>
                                                        <th scope="col">newPossibility</th>
                                                        <th scope="col">newImpact</th>
                                                        <th scope="col">Edit</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listR}" var="i">
                                                        <tr>
                                                            <td scope="row">${i.action}</td>
                                                            <td>${i.from}</td> 
                                                            <td>${i.to}</td> 
                                                            <td>${i.newPossibility}</td>
                                                            <td>${i.newImpact}</td>
                                                            <td>
                                                                <a href="#editModal" class="edit"data-id="${i.id}" data-possibility="${i.newPossibility}" data-impact="${i.newImpact}" data-action="${i.action}" data-from="${i.from}" data-to="${i.to}" data-riskid="${i.resID}" data-toggle="modal"><i class="fas fa-pen"></i></a>                                                     
                                                            </td> 
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>      
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="content" class="col-11 justify-content-center">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <c:if test="${tag > 1}">
                                        <li style="padding:10px" class="page-item disabled"><a href="loadRS?index=${tag-1}">Previous</a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endP}" var="i">  
                                        <li class="page-item"><a class="page-link ${tag == i?"active":""}" href="loadRS?index=${i}"class="page-link">${i}</a></li>                                   
                                        </c:forEach>   
                                        <c:if test="${tag<endP}">
                                        <li style="padding:10px" class="page-item disabled"><a href="loadRS?index=${tag+1}">Next</a></li>
                                        </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- Add Modal HTML -->
                <div id="addModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="addR" method="post">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Add new risk reponse</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">	
                                    <div class="form-group">
                                        <label>Risk ID:</label>
                                        <select name="riskID" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <c:forEach items="${listID}" var="r">
                                                <option>${r.ID}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Action:</label>
                                        <input name="action" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>From:</label>
                                        <input name="from" type="date" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>To:</label>
                                        <input name="to" type="date" class="form-control" required>
                                    </div>
                                     <div class="form-group">
                                        <label>Possibility : </label>
                                        <select id="newP" name="newP" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Very low">Very Low</option>
                                            <option value="Low">Low</option>
                                            <option value="Medium">Medium</option>
                                            <option value="High">High</option>
                                            <option value="Very High">Very High</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Impact : </label>
                                        <select id="newI" name="newI" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Requirement">Requirement</option>
                                            <option value="Technology">Technology</option>
                                            <option value="Complexity & Interface">Complexity & Interface</option>
                                            <option value="Performance & Realability">Performance & Realability</option>
                                            <option value="Quality">Quality</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-success" value="Add">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Edit Modal HTML -->
            <div id="editModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editR" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div class="modal-body">	
                                    <div class="form-group">
                                        <label>Risk ID:</label>
                                        <select name="riskID" id="riskID" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <c:forEach items="${listID}" var="r">
                                                <option>${r.ID}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Action:</label>
                                        <input name="action" id="action" type="text" class="form-control" required>
                                    </div>
                                    <input name="id" id="id" type="text" class="form-control" style="display:none">
                                    <div class="form-group">
                                        <label>From:</label>
                                        <input name="from" id="from" type="date" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>To:</label>
                                        <input name="to" id="to" type="date" class="form-control" required>
                                    </div>
                                     <div class="form-group">
                                        <label>Possibility : </label>
                                        <select id="newP" name="newP" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Very low">Very Low</option>
                                            <option value="Low">Low</option>
                                            <option value="Medium">Medium</option>
                                            <option value="High">High</option>
                                            <option value="Very High">Very High</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Impact : </label>
                                        <select id="newI" name="newI" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Requirement">Requirement</option>
                                            <option value="Technology">Technology</option>
                                            <option value="Complexity & Interface">Complexity & Interface</option>
                                            <option value="Performance & Realability">Performance & Realability</option>
                                            <option value="Quality">Quality</option>
                                        </select>
                                    </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-info" value="Save">
                                </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Delete Modal HTML -->
            <div class="page-footer">
                <div class="row">
                    <div class="col-md-12">
                        <span class="footer-text">2019 © stacks</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Javascripts -->
    <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
    <script src="assets/plugins/bootstrap/popper.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/js/connect.min.js"></script>
    <script>
        $(document).on("click", ".edit", function () {
            $(".modal-body #riskID").val($(this).data('riskid'));
            $(".modal-body #id").val($(this).data('id'));
            $(".modal-body #action").val($(this).data('action'));
            $(".modal-body #from").val($(this).data('from'));
            $(".modal-body #to").val($(this).data('to'));
            $(".modal-body #newP").val($(this).data('possibility'));
            $(".modal-body #newI").val($(this).data('impact'));
        });
    </script>
</body>
</html>