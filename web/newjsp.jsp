<%-- 
    Document   : newjsp
    Created on : Feb 27, 2022, 1:18:31 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="#exampleModal" >
            <input type="submit" value="Enter">
        </form>
<div class="modal fade" id="exampleModal" 
            tabindex="-1" 
            aria-labelledby="exampleModalLabel" 
            aria-hidden="true">
              
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" 
                            id="exampleModalLabel">
                            Confirmation
                        </h5>
                          
                        <button type="button" 
                            class="close" 
                            data-dismiss="modal" 
                            aria-label="Close">
                            <span aria-hidden="true">
                                ×
                            </span>
                        </button>
                    </div>
  
                    <div class="modal-body">
  
                        <!-- Data passed is displayed 
                            in this part of the 
                            modal body -->
                        <h6 id="modal_body"></h6>
                        <button type="button" 
                            class="btn btn-success btn-sm" 
                            data-toggle="modal"
                            data-target="#exampleModal" 
                            id="submit">
                            Submit
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
