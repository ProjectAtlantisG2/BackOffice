<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
      <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" >
      <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" >
      <link rel="stylesheet" href="css/mycss.css" />

      <title>List Devices - Atlantis</title>
    </head>
    <%@include file="baseTemplate.jsp" %>
    <body>
        <div class="container-fluid" >
            <div class="row">
                <%@include file="menu.jsp" %>
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                    <form class="needs-validation" novalidate  method="post" action="device<c:if test="${device.id != 0}" >?id=${device.id } </c:if>">    
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">Name </label>
                                <input type="text" class="form-control" name="name" id="name" placeholder="" value="${device.name}" required>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Mac Address</label>
                                <input type="text" class="form-control" name="macAddress" id="macAddress" placeholder="" value="${device.macAddress}" required>
                                <div class="invalid-feedback">
                                    Valid Mac Address is required.
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Employee</label>
                                <div class="row-fluid">
                                    <select class="selectpicker" data-show-subtext="true" data-live-search="true" name="idEmployee" id="idEmployee">
                                        <c:choose><c:when test="${device.idEmployee != null}"><option value="${device.idEmployee}">${device.nameEmployee}</option></c:when><c:otherwise><option value="">No employee</option></c:otherwise>   </c:choose>
                                        <c:forEach var="employee" items="${ employees }">
                                            <c:if test="${device.idEmployee != employee.id}"><option value="${employee.id}">${employee.name}</option></c:if>              
                                        </c:forEach>
                                    </select> 
                                </div>
                          </div>    
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Type</label>
                                <input type="text" class="form-control" name="type" id="type" placeholder="" value="${device.type}" required>
                                <div class="invalid-feedback">
                                  Valid Type is required.
                                </div>
                                <hr class="mb-4">
                                <button class="btn btn-primary  btn-block" type="submit">Save</button>
                            </div>
                        </div>
                    </form>
                </main>
            </div>
        </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    </body>
</html>