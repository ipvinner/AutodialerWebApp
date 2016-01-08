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
      <h3>Tasks List</h3>

      <div class="view-box">
        <a class="btn btn-sm btn-info" id="add">Add task</a>



        <table class="table table-striped display" id="datatable">
          <thead>
          <tr>
            <th>Name</th>
            <th>active</th>
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

      <h3 class="text-center">Add new task</h3>
      <div class="view-box">
        <form id="addTaskForm" method="post">
          <div class="form-group">
            <label for="task_name" class="control-label col-xs-3">Task Name</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="task_name" name="task_name" placeholder="task name">
            </div>
          </div>

          <div class="form-group">
            <label for="clients_list_id" class="control-label col-xs-3">Client List</label>
            <div class="col-xs-9">
              <select class="form-control" id="clients_list_id" name="clients_list_id">
                <option value="1">vip-clients</option>
                <option value="2">debts</option>
                <option value="3">credit cards</option>
                <option value="4">new clients</option>
              </select>
            </div>
          </div>



          <div class="form-group">
            <label class="control-label col-xs-3">Is active?</label>
            <label class = "checkbox-inline">
              <input type = "radio" name = "task_active" id = "task_active" value = "true" checked> Active
            </label>

            <label class = "checkbox-inline">
              <input type = "radio" name = "task_active" value = "false"> No Active
            </label>
          </div>

          <div class="form-group">
            <label for="task_originate_param_id" class="control-label col-xs-3">Originate params</label>
            <div class="col-xs-9">
              <select class="form-control" id="task_originate_param_id" name="task_originate_param_id">
                <option value="1">from-ami</option>
                <option value="2">test</option>
                <option value="3">test2</option>
                <option value="4">outbound-calls</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <div class="col-xs-offset-3 col-xs-3">
              <button type="submit" class="btn btn-primary">Add new task</button>
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
        <h2 class="modal-title">edit task</h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post" id="detailsForm">
          <input type="text" hidden="hidden" id="id" name="id">

          <div class="form-group">
            <label for="name" class="control-label col-xs-3">name</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="name" name="firstName" placeholder="name">
            </div>
          </div>

          <div class="form-group">
            <label for="client_list_id" class="control-label col-xs-3">client_list_id</label>

            <div class="col-xs-9">
              <input type="text" class="client_list_id" id="client_list_id" name="client_list_id" placeholder="client_list_id">
            </div>
          </div>

          <div class="form-group">
            <label for="active" class="control-label col-xs-3">active</label>

            <div class="col-xs-9">
              <input type="text" class="active" id="active" name="active" placeholder="active">
            </div>
          </div>

          <div class="form-group">
            <label for="originate_param_id" class="control-label col-xs-3">originate_param_id</label>

            <div class="col-xs-9">
              <input type="text" class="originate_param_id" id="originate_param_id" name="originate_param_id" placeholder="originate_param_id">
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
<script type="text/javascript" src="resources/javascript/tasksDatatables.js"></script>

</html>
