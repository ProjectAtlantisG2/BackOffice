<%-- 
    Document   : index2
    Created on : 27 juin 2018, 15:09:24
    Author     : Ollocip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link href="css/mycss.css" rel="stylesheet">
    <title>List Employees - Atlantis</title>
  </head>
  <%@include file="baseTemplate.jsp" %>
  <body>
 

    <div class="container-fluid" >
      <div class="row">
      <%@include file="menu.jsp" %>
 <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <h2>List Employees</h2>
          <div class="table-responsive">
            <table class="table table-striped table-sm">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Name</th>
                  <th>Update</th>
                  <th>Delete</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="employee" items="${ employees }">
                <tr>
                  <td>${employee.id}</td>
                  <td>${employee.name}</td>
                  <td><a href="${pageContext.request.contextPath}/employee?id=${employee.id}">Edit</a></td>
                  <td><a href="${pageContext.request.contextPath}/employee?id=${employee.id}/delete">Delete</a></td>
                </tr>
                </c:forEach>
                  
              </tbody>
            </table>
                  
          </div>
 </main>
      </div>
    </div>
    <!-- Optional JavaScript -->    
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <script src="js/myjs.js"></script>
  </body>
</html>