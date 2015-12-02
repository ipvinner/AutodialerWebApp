<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">
<body>

<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
  <div class="container">
    <div class="shadow">
      <h3><fmt:message key="clients.title"/></h3>

      <div class="view-box">
        <a class="btn btn-sm btn-info" id="add"><fmt:message key="clients.add_clients_list"/></a>

        <div class="dropdown">
          <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            choose clients list
            <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

            <c:forEach items="${clientsLists}" var="list">
              <jsp:useBean id="list" scope="page" type="com.cartrack.autodialer.domain.ClientList"/>
              <li><a class="btn btn-sm btn-info chooseList" id="${list.id}">${list.name}</a></li>
            </c:forEach>

          </ul>
        </div>

        <table class="table table-striped display" id="datatable">
          <thead>
          <tr>
            <th><fmt:message key="clients.firstname"/></th>
            <th><fmt:message key="clients.lastname"/></th>
            <th><fmt:message key="clients.phone_number"/></th>
            <th><fmt:message key="clients.email"/></th>
            <th><fmt:message key="clients.list"/></th>
            <th></th>
            <th></th>
          </tr>
          </thead>
        </table>

      </div>
    </div>
  </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<div class="modal fade" id="editRow">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title">edit</h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post" id="detailsForm">
          <input type="text" hidden="hidden" id="id" name="id">

          <div class="form-group">
            <label for="firstName" class="control-label col-xs-3">firstName</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="firstName" name="firstName" placeholder="firstName">
            </div>
          </div>

          <div class="form-group">
            <label for="lastName" class="control-label col-xs-3">lastName</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="lastName" name="lastName" placeholder="lastName">
            </div>
          </div>

          <div class="form-group">
            <label for="phoneNumber" class="control-label col-xs-3">phoneNumber</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="phoneNumber">
            </div>
          </div>


          <div class="form-group">
            <label for="email" class="control-label col-xs-3">email</label>

            <div class="col-xs-9">
              <input type="email" class="form-control" id="email" name="email" placeholder="email">
            </div>
          </div>

          <div class="form-group">
            <div class="col-xs-offset-3 col-xs-9">
              <button type="submit" class="btn btn-primary">Save</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>


</body>
<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/javascript/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/javascript/clientsDatatables.js"></script>


</html>