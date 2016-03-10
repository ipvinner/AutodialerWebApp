<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">
<body>

<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
  <div class="container">
    <div class="shadow">
      <h3><fmt:message key="task.list"/></h3>

      <div class="view-box">
        <a class="btn btn-sm btn-info" id="add"><fmt:message key="task.add_task"/></a>



        <table class="table table-striped display" id="datatable">
          <thead>
          <tr>
            <th><fmt:message key="task.name"/></th>
            <th><fmt:message key="task.active"/></th>
            <th></th>
            <th></th>
            <th></th>
          </tr>
          </thead>
        </table>

      </div>

      <br/>
      <br/>
      <br/>

      <h3 class="text-center"><fmt:message key="task.add_task"/></h3>
      <div class="view-box">
        <form id="addTaskForm" method="post">
          <div class="form-group">
            <label for="task_name" class="control-label col-xs-3"><fmt:message key="task.name"/></label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="task_name" name="task_name" placeholder="task name">
            </div>
          </div>

          <div class="form-group">
            <label for="clients_list_id" class="control-label col-xs-3"><fmt:message key="clients.list"/></label>
            <div class="col-xs-9">
              <select class="form-control" id="clients_list_id" name="clients_list_id">
                <c:forEach items="${clientsLists}" var="list">
                  <jsp:useBean id="list" scope="page" type="com.cartrack.autodialer.domain.ClientList"/>
                  <li><a class="btn btn-sm btn-info chooseList" id="${list.id}">${list.name}</a></li>
                </c:forEach>
              </select>
            </div>
          </div>



          <div class="form-group">
            <label class="control-label col-xs-3"><fmt:message key="task.active"/></label>
            <label class = "checkbox-inline">
              <input type = "radio" name = "task_active" id = "task_active" value = "true" checked> Active
            </label>

            <label class = "checkbox-inline">
              <input type = "radio" name = "task_active" value = "false"> No Active
            </label>
          </div>

          <div class="form-group">
            <label for="task_originate_param_id" class="control-label col-xs-3"><fmt:message key="task.params"/></label>
            <div class="col-xs-9">
              <select class="form-control" id="task_originate_param_id" name="task_originate_param_id">
                <c:forEach items="${clientsLists}" var="list">
                  <li><a class="btn btn-sm btn-info chooseList" id="${list.id}">${list.name}</a></li>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-xs-offset-3 col-xs-3">
              <button type="submit" class="btn btn-primary"><fmt:message key="task.add_task"/></button>
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
        <h2 class="modal-title"><fmt:message key="task.add_task"/></h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post" id="detailsForm">
          <input type="text" hidden="hidden" id="id" name="id">

          <div class="form-group">
            <label for="name" class="control-label col-xs-3"><fmt:message key="task.name"/></label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="name" name="firstName">
            </div>
          </div>

          <div class="form-group">
            <label for="client_list_id" class="control-label col-xs-3"><fmt:message key="clients.list"/></label>

            <div class="col-xs-9">
              <input type="text" class="client_list_id" id="client_list_id" name="client_list_id" placeholder="client_list_id">
            </div>
          </div>

          <div class="form-group">
            <label for="active" class="control-label col-xs-3"><fmt:message key="task.active"/></label>

            <div class="col-xs-9">
              <input type="text" class="active" id="active" name="active" placeholder="active">
            </div>
          </div>

          <div class="form-group">
            <label for="originate_param_id" class="control-label col-xs-3"><fmt:message key="task.params"/></label>

            <div class="col-xs-9">
              <input type="text" class="originate_param_id" id="originate_param_id" name="originate_param_id" placeholder="originate_param_id">
            </div>
          </div>


          <div class="form-group">
            <div class="col-xs-offset-3 col-xs-9">
              <button type="submit" class="btn btn-primary"><fmt:message key="clients.add"/></button>
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
<script type="text/javascript" src="resources/javascript/tasksDatatables.js"></script>

</html>
