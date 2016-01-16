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
        <a class="btn btn-sm btn-info" id="add"><fmt:message key="users.users_list"/></a>


        <table class="table table-striped display" id="datatable">
          <thead>
          <tr>
            <th><fmt:message key="users.login"/></th>
            <th><fmt:message key="users.password"/></th>
            <th><fmt:message key="users.role"/></th>
            <th></th>
            <th></th>
          </tr>
          </thead>
        </table>

      </div>

      <h3>Add new user</h3>
      <div class="view-box">
        <form id="addUser" method="post">
          <input type="text" hidden="hidden" id="id" name="id">

          <div class="form-group">
            <label for="login" class="control-label col-xs-3">Login</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="login" name="login" placeholder="login">
            </div>
          </div>

          <div class="form-group">
            <label for="login" class="control-label col-xs-3">Password</label>

            <div class="col-xs-9">
              <input type="password" class="form-control" id="password" name="password" placeholder="password" >
            </div>
          </div>

          <div class="form-group">
            <label for="role" class="control-label col-xs-3">Role</label>
            <div class="col-xs-9">
              <select class="form-control" id="role" name="role">
                <option value="ROLE_ADMIN">Admin</option>
                <option value="ROLE_MANAGER">Manager</option>
                <option value="ROLE_USER">User</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-xs-offset-3 col-xs-3">
              <button type="submit" class="btn btn-primary">Update</button>
            </div>
          </div>
        </form>
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
        <h2 class="modal-title">edit user</h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post" id="detailsForm">
          <input type="text" hidden="hidden" id="id" name="id">

          <div class="form-group">
            <label for="login" class="control-label col-xs-3">login</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="login" name="login" placeholder="login">
            </div>
          </div>

          <div class="form-group">
            <label for="password" class="control-label col-xs-3">password</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="password" name="password" placeholder="password">
            </div>
          </div>

          <div class="form-group">
            <label for="role" class="control-label col-xs-3">role</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="role" name="role" placeholder="role">
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
<script type="text/javascript" src="resources/javascript/usersDatatables.js"></script>


</html>