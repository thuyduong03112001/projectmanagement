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
            .search-box{
                position: absolute;
                height: 40px;
                border-radius:40px;
                padding: 10px;
            }
            .search-box:hover > .search-txt{
                width: 240px;
                padding: 0 6px;
            }
            .search-box:hover > .search-btn{
                color: green
            }
            .search-btn{
                color:blue;
                width: 40px;
                height:40px;
                border-radius: 50%;
                transition: 0.4s;
            }
            .search-txt{
                border:none;
                background:#99ff99;
                outline:none;
                padding:0;
                color:black;
                font-size: 16px;
                transition: 0.4s;
                line-height: 40px;
                width: 0px;
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
                <a href="loadRS" style="margin-left:1260px" class="btn btn-success"><i class="material-icons"></i> <span>Risk Response</span></a>
                <div class="page-content">  
                    <form action="loadr" method="post">
                        <div class="search-box">
                            <input class="search-txt" type="text" style="border-radius:9px" name="search" placeholder="Search by titlte...">
                            <a class="search-btn" href="#">
                                <i class="fas fa-search"></i>
                            </a>
                        </div>
                    </form>
                    <div class="main-wrapper container" style="
                         transform: translate(60px, 80px);">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="page-title">                      
                                    <form action="filterR" method="get" style="margin-bottom: 20px">                        
                                        ProjectName:         
                                        <select name="projectName" style= "margin-left: 10px; width: 15%; margin-right: 20px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option>${pN}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="p">
                                                <option value="${p.projectName}">${p.projectName}</option>
                                            </c:forEach>   
                                        </select>
                                        Creator:
                                        <select name="createdBy" style= "width: 15%;margin-left: 10px;margin-right: 20px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option>${cB}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="c">
                                                <option value="${c.createdBy}">${c.createdBy}</option>
                                            </c:forEach>
                                        </select>
                                        Category:
                                        <select name="category" style= "margin-left: 20px; width: 15%; margin-right: 25px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option>${cG}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="c">
                                                <option value="${c.cateID}">${c.cateID}</option>
                                            </c:forEach>   
                                        </select>
                                        Owner:
                                        <select name="owner" style= "width: 15%;margin-left: 20px;" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">                  
                                            <option>${oN}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="o">
                                                <option value="${o.owner}">${o.owner}</option>
                                            </c:forEach>   
                                        </select>
                                        <br>
                                        UDate-From: 
                                        <select name="updatedFrom" id="datapicker" style= "margin-top: 15px;margin-left: 10px; width: 15%; margin-right: 15px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option>${uF}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="u">
                                                <option value="${u.updatedDate}">${u.updatedDate}</option>
                                            </c:forEach>   
                                        </select>
                                        UDate-To:
                                        <select name="updatedTo" id="datapicker" style= "margin-top: 15px;margin-left: 8px; width: 15%; margin-right: 30px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option>${uT}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="u">
                                                <option value="${u.updatedDate}">${u.updatedDate}</option>
                                            </c:forEach>   
                                        </select>
                                        Status:
                                        <select name="status" style= "margin-top: 15px;margin-left:28px; width: 15%; margin-right: 15px" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option>${sT}</option>
                                            <option value="">--All--</option>
                                            <c:forEach items="${listS}" var="s">
                                                <option value="${s.statusID}">${s.statusID}</option>
                                            </c:forEach>   
                                        </select>
                                        <br>
                                        <input type="submit" class="btn btn-primary btn-submit" style="margin-left: 420px;margin-top:20px ;font-size: 17px" value="Filter"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="main-wrapper">
                        <div class="row">
                            <div class="col-xl">
                                <div class="card">
                                    <div class="card-body" style="padding:15px">
                                        <c:if test="${sessionScope.account.roleId != 11}">                               
                                            <a href="#addEmployeeModal" style="margin-left:1260px" class="btn btn-success" data-toggle="modal"><i class="material-icons"></i> <span>Add</span></a>						
                                        </c:if>        
                                        <h5 class="card-title">Risk List</h5>
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">CreatedDate</th>
                                                        <th scope="col">ProjectName</th>
                                                        <th scope="col">Title</th>
                                                        <th scope="col">CateID</th>
                                                        <th scope="col">CreatedBy</th>
                                                        <th scope="col">Owner</th>
                                                        <th scope="col">Description</th>
                                                        <th scope="col">RootCause</th>
                                                        <th scope="col">Possibility</th>
                                                        <th scope="col">Impact</th>
                                                        <th scope="col">Rank</th>
                                                        <th scope="col">StatusID</th>
                                                        <th scope="col">UpdatedDate</th>
                                                        <th scope="col">Edit</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <tbody>
                                                    <c:forEach items="${listR}" var="i">
                                                        <tr>
                                                            <td scope="row">${i.createdDate}</td>
                                                            <td>${i.projectName}</td> 
                                                            <td>${i.title}</td> 
                                                            <td>${i.cateID}</td>
                                                            <td>${i.createdBy}</td>
                                                            <td>${i.owner}</td>
                                                            <td>${i.description}</td>
                                                            <td>${i.rootCause}</td>
                                                            <td>${i.possibility}</td>
                                                            <td>${i.impact}</td>
                                                            <td>${i.rank}</td>
                                                            <td>${i.statusID}</td>  
                                                            <td>${i.updatedDate}</td>
                                                            <td>
                                                                <c:set var="x" value="${i.createdBy}"/>
                                                                <c:set var="y" value="${i.owner}"/>
                                                                <c:set var="z" value="${sessionScope.account.fullName}"/>
                                                                <c:set var="role" value="${sessionScope.account.roleId}"/>
                                                                <c:if test="${z eq x || z eq y || role == 6}">
                                                                    <a href="#editModal" class="edit" data-name="${i.projectName}" data-title="${i.title}" data-cate="${i.cateID}" data-created="${i.createdBy}" data-owner="${i.owner}" data-description="${i.description}" data-status ="${i.statusID}" data-rootcause="${i.rootCause}" data-possibility="${i.possibility}" data-impact="${i.impact}" data-rank="${i.rank}" data-upDate="${i.updatedDate}" data-id="${i.ID}" data-toggle="modal"><i class="fas fa-pen"></i></a>
                                                                    </c:if>
                                                                <a href="#deleteModal" onclick="myFunction(${i.ID})" data-toggle="modal"><i class="fas fa-trash-alt"></i></a>
                                                            </td> 
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
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
                                        <li style="padding:10px" class="page-item disabled"><a href="loadr?index=${tag-1}">Previous</a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${endP}" var="i">  
                                        <li class="page-item"><a class="page-link ${tag == i?"active":""}" href="loadr?index=${i}"class="page-link">${i}</a></li>                                   
                                        </c:forEach>   
                                        <c:if test="${tag<endP}">
                                        <li style="padding:10px" class="page-item disabled"><a href="loadr?index=${tag+1}">Next</a></li>
                                        </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- Add Modal HTML -->
                <div id="addEmployeeModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="addR">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Add new risk</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">	
                                    <div class="form-group">
                                        <label>ProjectName:</label>
                                        <input name="name" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Title</label>
                                        <input name="title" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Category</label><br>
                                        <select name="category" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="49">Requirements</option>
                                            <option value="50">Design</option>
                                            <option value="51">Coding</option>
                                            <option value="52">Testing</option>
                                            <option value="53">Implementation</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>CreatedBy : </label>
                                        <input value="${sessionScope.account.fullName}" readonly="" name="createdBy" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Owner : </label>
                                        <select name="owner" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <c:forEach items="${listA}" var="a">
                                                <option value="${a.fullName}">${a.fullName}</option>
                                            </c:forEach> 
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Description : </label>
                                        <input name="des" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>RootCause : </label>
                                        <input name="cause" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Possibility : </label>
                                        <select name="possi" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Very low">Very Low</option>
                                            <option value="Low">Low</option>
                                            <option value="Medium">Medium</option>
                                            <option value="High">High</option>
                                            <option value="Very High">Very High</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Impact : </label>
                                        <select name="impact" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Requirement">Requirement</option>
                                            <option value="Technology">Technology</option>
                                            <option value="Complex/Interface">Complexcity&Interface</option>
                                            <option value="Performance/Realability">Perfomance&Realability</option>
                                            <option value="Quality">Quality</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Rank : </label>
                                        <select name="rank" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="4">Negiligible(Small)</option>
                                            <option value="3">Marginal(Medium)</option>
                                            <option value="2">Critical(Huge)</option>
                                            <option value="1">Catastrophy(Extremly dangerous)</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Status : </label>
                                        <select name="status" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="70">Assigned</option>
                                            <option value="71">Cancelled</option>
                                            <option value="72">Closed</option>
                                            <option value="73">Completed</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>UpdatedDate : </label>
                                        <input name="update" type="date" class="form-control" required>
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
                <!-- Edit Modal HTML -->
                <div id="editModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="editR">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Edit</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>ProjectName:</label>
                                        <input name="name" id="name" type="text" class="form-control" required>
                                    </div>
                                    <input type="text" id="id" name="id" readonly="" style="display:none">
                                    <div class="form-group">
                                        <label>Title</label>
                                        <input name="title" id="title" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Category</label><br>
                                        <select id="cateID" name="category" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="49">Requirements</option>
                                            <option value="50">Design</option>
                                            <option value="51">Coding</option>
                                            <option value="52">Testing</option>
                                            <option value="53">Implementation</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>CreatedBy : </label>
                                        <input id="createdBy" name="createdBy" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Owner : </label>
                                        <input id="owner" name="owner" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Description : </label>
                                        <input id="description" name="des" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>RootCause : </label>
                                        <input id="rootcause" name="cause" type="text" class="form-control" required>
                                    </div>
                                    <div class="form-group">
                                        <label>Possibility : </label>
                                        <select id="possibility" name="possi" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Very low">Very Low</option>
                                            <option value="Low">Low</option>
                                            <option value="Medium">Medium</option>
                                            <option value="High">High</option>
                                            <option value="Very High">Very High</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Impact : </label>
                                        <select id="impact" name="impact" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="Requirement">Requirement</option>
                                            <option value="Technology">Technology</option>
                                            <option value="Complexity & Interface">Complexity & Interface</option>
                                            <option value="Performance & Realability">Performance & Realability</option>
                                            <option value="Quality">Quality</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Rank : </label>
                                        <select id="rank" name="rank" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="4">Negiligible(Small)</option>
                                            <option value="3">Marginal(Medium)</option>
                                            <option value="2">Critical(Huge)</option>
                                            <option value="1">Catastrophy(Extremly dangerous)</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Status : </label>
                                        <select id="statusID" name="status" aria-controls="zero-conf" class="custom-select custom-select-sm form-control form-control-sm">
                                            <option value="70">Assigned</option>
                                            <option value="71">Cancelled</option>
                                            <option value="72">Closed</option>
                                            <option value="73">Completed</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>UpdatedDate : </label>
                                        <input id="updatedDate" name="update" type="date" id="datepicker" class="form-control" required>
                                    </div>
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
                <div id="deleteModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="deleteR">
                                <div class="modal-header">						
                                    <h4 class="modal-title">Delete risk</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">					
                                    <p>Are you sure you want to delete this item?</p>
                                    <input type="text" id="id" name="id" readonly="" style="display:none">
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-danger" value="Delete">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
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
                                                                    function myFunction(id) {
                                                                        $(".modal-body #id").val(id);
                                                                    }
                                                                    $(document).on("click", ".edit", function () {
                                                                        $(".modal-body #name").val($(this).data('name'));
                                                                        $(".modal-body #id").val($(this).data('id'));
                                                                        $(".modal-body #title").val($(this).data('title'));
                                                                        $(".modal-body #cateID").val($(this).data('cate'));
                                                                        $(".modal-body #createdBy").val($(this).data('created'));
                                                                        $(".modal-body #owner").val($(this).data('owner'));
                                                                        $(".modal-body #description").val($(this).data('description'));
                                                                        $(".modal-body #rootcause").val($(this).data('rootcause'));
                                                                        $(".modal-body #possibility").val($(this).data('possibility'));
                                                                        $(".modal-body #impact").val($(this).data('impact'));
                                                                        $(".modal-body #rank").val($(this).data('rank'));
                                                                        $(".modal-body #statusID").val($(this).data('status'));
                                                                        $(".modal-body #updatedDate").val($(this).data('upDate'));
                                                                    });
        </script>
    </body>
</html>