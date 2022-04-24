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
        <!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Title -->
        <title>Project Deliverable</title>
        <link rel="shortcut icon" href="imgs/logo.png">
        <!-- Styles -->
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/plugins/font-awesome/css/all.min.css" rel="stylesheet">
        <link href="assets/plugins/DataTables/datatables.min.css" rel="stylesheet">   
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
        <link rel="stylesheet" href="css/styleLogin.css">
        <style>
            form>div>div>span {
                width: 100%!important;
            }
            #zero-conf_wrapper>div:nth-child(odd), #add-row_wrapper>div:nth-child(odd) {
                display: none;
            }
            #zero-conf, #add-row{
                text-align: center;
            }

            td {
                word-break: break-all;               
            }

            #exampleModal .modal-dialog .modal-content .modal-body .form-group>span {
                /*display: none;*/
                width: 100%!important;
            }
        </style>
    </head>
    <body>

        <div class='loader'>
            <div class='spinner-grow text-primary' role='status'>
                <span class='sr-only'>Loading...</span>
            </div>
        </div>
        <div class="connect-container align-content-stretch d-flex flex-wrap">
            <%@include file="sidebar.jsp" %>
            <div class="page-container">
                <%@include file="header.jsp"%>
                <div class="page-content">
                    <div class="page-info">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">App</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Project Deliverable</li>
                            </ol>
                        </nav>
                        <div class="page-options" style="${sessionScope.account.roleId!=6?'display: none;':''}">
                            <button onclick="myAdd()" class="btn btn-primary">New Project Deliverable</button>
                        </div>
                    </div>
                    <div class="main-wrapper">
                        <div>
                            <div>
                                <div class="card">
                                    <div class="card-body">
                                        <div class="todo-sidebar">
                                            <div class="todo-search">
                                                <jsp:useBean id="stdb" class="dal.SettingDAO"/>
                                                <form action="deliverablelist" method="post">
                                                    <div class="row">
                                                        <div class="input-group col-lg-2" style=" height: 100%; ">
                                                            <input type="text" id="todo-search" value="${sessionScope.name3}" name="name3" class="form-control" placeholder="Name">
                                                        </div>
                                                        <jsp:useBean id="gdb" class="dal.GroupDAO"/>
                                                        <div class="col-lg-2">
                                                            <select name="group3" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; padding: 6%; ">
                                                                <optgroup label="Group">
                                                                    <option ${sessionScope.group3==-1?"selected":""} value="-1">All Groups</option>
                                                                    <c:forEach items="${gdb.all}" var="group">
                                                                        <option ${sessionScope.group3==group.id?"selected":""} value="${group.id}">${group.id} - ${group.name}</option>
                                                                    </c:forEach>
                                                            </select>
                                                        </div>
                                                        <jsp:useBean id="pdb" class="dal.ProjectDAO"/>
                                                        <div class="col-lg-2">
                                                            <select name="project3" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; padding: 6%; ">
                                                                <optgroup label="Project">
                                                                    <option ${sessionScope.project3==""?"selected":""} value="-1">All Projects</option>
                                                                    <c:forEach items="${pdb.allProject}" var="project">
                                                                        <option ${sessionScope.project3==project.id?"selected":""} value="${project.id}">${project.id} - ${project.name}</option>
                                                                    </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <select name="status3" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; padding: 6%; width: 100%;">
                                                                <optgroup label="Status">
                                                                    <option ${sessionScope.status3==-1?"selected":""} value="-1">All Status</option>
                                                                    <c:forEach items="${stdb.getAllByType('deliverable status')}" var="status">
                                                                        <option ${sessionScope.status3==status.id?"selected":""} value="${status.id}">${status.value}</option>
                                                                    </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-lg-3" style=" display: flex; ">
                                                            <div style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; margin-right: 2%;">
                                                                <p style=" font-weight: 700; margin-bottom: 0px; text-align: center; padding-right: 20%;">From</p>
                                                                <input type="date" value="${sessionScope.from3}" name="from3" style="width: 90%; background: transparent; font-weight: 900; color: #9c9c9c; border: none;transform: translateX(10px); "/>
                                                            </div>
                                                            <div style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; ">
                                                                <p style=" font-weight: 700; margin-bottom: 0px; text-align: center; padding-right: 20%;">To</p>
                                                                <input type="date" value="${sessionScope.to3}" name="to3" style="width: 90%; background: transparent; font-weight: 900; color: #9c9c9c; border: none;transform: translateX(10px); "/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-1">
                                                            <input type="submit" class="btn btn-outline-success" value="Filter"/>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-body">
                                        <table id="zero-conf" class="display" style="width:100%">
                                            <thead>
                                                <tr>
                                                    <th>Group Code</th>
                                                    <th>Project Code/Name</th>
                                                    <th>Deliverable</th>
                                                    <th>Planned Date</th>
                                                    <th>Previous Date</th>
                                                    <th>Actual Date</th>
                                                    <th>Status</th>
                                                    <th style="${sessionScope.account.roleId!=6?'display: none;':''}">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="i" value="0"></c:set>
                                                <c:forEach items="${list3}" var="deliverable">
                                                    <tr>
                                                        <td >${deliverable.groupId}</td>
                                                        <td >${deliverable.projectId} - ${deliverable.projectName}</td>
                                                        <td class="name">${deliverable.deliverable}</td>
                                                        <td >${deliverable.realPlanned}</td>
                                                        <td>${deliverable.realPrevious}</td>
                                                        <td >${deliverable.realActual}</td>
                                                        <td >${deliverable.status}</td>
                                                        <td class="status" style="display: none;">${deliverable.statusId}</td>
                                                        <td class="planned" style="display: none;">${deliverable.plannedDate}</td>
                                                        <td class="actual" style="display: none;">${deliverable.actualDate}</td>
                                                        <td class="id" style="display: none;">${deliverable.id}</td>
                                                        <td style="${sessionScope.account.roleId!=6?'display: none;':''}"><a class="btn btn-primary" style="display: ${deliverable.active==0||deliverable.actualDate!=null?'none':''}" onclick="toggleModal(this, ${i})">Edit</a></td>
                                                    </tr>     

                                                    <c:set var="i" value="${i+1}"></c:set>
                                                </c:forEach>     
                                            </tbody>
                                        </table>

                                        <c:if test="${sessionScope.num3>1}">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination justify-content-center">
                                                    <li class="page-item"><a class="page-link" href="deliverablelist?page=${sessionScope.page==1?1:sessionScope.page - 1}">Previous</a></li>
                                                        <c:forEach begin="${1}" end="${sessionScope.num3}" var="i">
                                                        <li class="page-item ${page==i?"active":""}"><a class="page-link" href="deliverablelist?page=${i}">${i}</a></li>
                                                        </c:forEach>
                                                    <li class="page-item"><a class="page-link" href="deliverablelist?page=${sessionScope.page==sessionScope.num3?sessionScope.num3:sessionScope.page + 1}">Next</a></li>
                                                </ul>
                                            </nav>
                                        </c:if>
                                        <form action="adddeliverable" method="post">
                                            <div id="addTable" style="margin-top: 50px; display: none;">
                                                <table id="add-row" class="display" style="width:100%">
                                                    <tbody>
                                                        <tr>
                                                            <th>Group Code</th>
                                                            <th style=" width: 20%; ">Project Code/Name</th>
                                                            <th>Deliverable</th>
                                                            <th style=" width: 15%; ">Planned Date</th>
                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>            
                                                        <tr>
                                                            <td >Auto Fill</td>
                                                            <td style=" width: 20%; "><select name="projectAdd" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; padding: 4%; width: 100%;">
                                                                    <optgroup label="Project">
                                                                    <c:forEach items="${pdb.getAllByStaff(sessionScope.account.id)}" var="project">
                                                                            <option value="${project.id}">${project.id} - ${project.name}</option>
                                                                        </c:forEach>
                                                                </select></td>
                                                            <td><input type="text" name="deliverableAdd" placeholder="Text" style="width: 90%; border: 2px solid #e8e8e8!important;border-radius: 25px!important;padding: 3%;text-align: center;"></td>
                                                            <td style=" width: 15%; "><div style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; margin-right: 2%;">
                                                                    <input required="" type="date" name="dateAdd" style="padding: 3% 0px; width: 90%; background: transparent; font-weight: 900; color: #9c9c9c; border: none;"/>
                                                                </div></td>
                                                            <td style=" width: 15%; "><select name="statusAdd" style=" border: 2px solid #e8e8e8!important; border-radius: 25px!important; padding: 6%; width: 100%;">
                                                                    <optgroup label="Status">
                                                                        <c:forEach items="${stdb.getAllByType('deliverable status')}" var="status">
                                                                            <option value="${status.id}">${status.value}</option>
                                                                        </c:forEach>
                                                                </select></td>
                                                            <td><input type="submit" class="btn btn-primary" value="Add"></td>  
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%@include file="footer.jsp" %>
            </div>
        </div>
        <form action="editdeliverable" method="post">
            <div class="modal" id="exampleModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Table</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <input type="text" id="id" class="form-control" name="id" style="display: none;">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" id="name" name="name" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="planned">planned</label>
                                <input type="date" id="planned" name="planned" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="actual">actual</label>
                                <input type="date" id="actual" name="actual" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="status">status</label><br>
                                <select id="status" name="status" class="form-control">
                                    <optgroup label="Status">
                                        <c:forEach items="${stdb.getAllByType('deliverable status')}" var="status">
                                            <option id="st${status.id}" value="${status.id}" >${status.value}</option>
                                        </c:forEach>
                                    </optgroup>
                                </select>
                                <!--<input type="text" id="status" class="form-control">-->
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-primary" value="Save changes">
                            <button type="button" class="btn btn-secondary" onclick="closeModal()">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <div class="modal-backdrop fade show" id="backdrop" style="display: none;"></div>
        <script>
            function myAdd() {
                if (document.getElementById("addTable").style.display == 'none') {
                    document.getElementById("addTable").style.display = 'block';
                    return;
                } else if (document.getElementById("addTable").style.display == 'block') {
                    document.getElementById("addTable").style.display = 'none';
                    return;
                }
            }

        </script>
        <script>
            let tableRowElement;
            function toggleModal(element) {

                tableRowElement = element.parentElement.parentElement;
                const name = tableRowElement.getElementsByClassName('name')[0].innerHTML;
                const planned = tableRowElement.getElementsByClassName('planned')[0].innerHTML;
                const actual = tableRowElement.getElementsByClassName('actual')[0].innerHTML;
                const status = tableRowElement.getElementsByClassName('status')[0].innerHTML;
                const id = tableRowElement.getElementsByClassName('id')[0].innerHTML;
                console.log(name);
//                console.log(planned);
//                console.log(actual);
                console.log(status);

                document.getElementById('name').value = name;
                document.getElementById('status').value = status;
                document.getElementById('st' + status).selected = true;
                document.getElementById('planned').value = planned;
                document.getElementById('actual').value = actual;
                document.getElementById('id').value = id;
                openModal();
            }

            function openModal() {
                document.getElementById("backdrop").style.display = "block"
                document.getElementById("exampleModal").style.display = "block"
                document.getElementById("exampleModal").classList.add("show");
            }

            function closeModal() {
                document.getElementById("backdrop").style.display = "none"
                document.getElementById("exampleModal").style.display = "none"
                document.getElementById("exampleModal").classList.remove("show");
            }
        </script>
        <!-- Javascripts -->
        <script src="assets/plugins/jquery/jquery-3.4.1.min.js"></script>
        <script src="assets/plugins/bootstrap/popper.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="assets/plugins/DataTables/datatables.min.js"></script>
        <script src="assets/js/connect.min.js"></script>
        <script src="assets/js/pages/datatables.js"></script>
        <script src="assets/js/pages/select2.js"></script>
        <script src="assets/plugins/select2/js/select.full.min.js"></script>
    </body>
</html>