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
      <h3>Originate params</h3>

      <div class="view-box">
        <a class="btn btn-sm btn-info" id="add">Add Originate param</a>


        <table class="table table-striped display" id="datatable">
          <thead>
          <tr>
            <th>name</th>
            <th>context</th>
            <th>extension</th>
            <th>priority</th>
            <th>async</th>
            <th>timeout</th>
            <th>trunk</th>
            <th>var1</th>
            <th>var2</th>
            <th></th>
            <th></th>
          </tr>
          </thead>
        </table>

      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="editRow">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title">edit originate param</h2>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" method="post" id="detailsForm">
          <input type="text" hidden="hidden" id="id" name="id">

          <div class="form-group">
            <label for="name" class="control-label col-xs-3">name</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="name" name="name" placeholder="name">
            </div>
          </div>

          <div class="form-group">
            <label for="context" class="control-label col-xs-3">context</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="context" name="context" placeholder="context">
            </div>
          </div>

          <div class="form-group">
            <label for="extension" class="control-label col-xs-3">extension</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="extension" name="extension" placeholder="extension">
            </div>
          </div>


          <div class="form-group">
            <label for="priority" class="control-label col-xs-3">priority</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="priority" name="priority" placeholder="priority">
            </div>
          </div>

          <div class="form-group">
            <label for="async" class="control-label col-xs-3">async</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="async" name="async" placeholder="async">
            </div>
          </div>

          <div class="form-group">
            <label for="timeout" class="control-label col-xs-3">timeout</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="timeout" name="timeout" placeholder="timeout">
            </div>
          </div>

          <div class="form-group">
            <label for="trunk" class="control-label col-xs-3">trunk</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="trunk" name="trunk" placeholder="trunk">
            </div>
          </div>

          <div class="form-group">
            <label for="var1" class="control-label col-xs-3">var1</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="var1" name="var1" placeholder="var1">
            </div>
          </div>

          <div class="form-group">
            <label for="var2" class="control-label col-xs-3">var2</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="var2" name="var2" placeholder="var2">
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
<jsp:include page="fragments/footer.jsp"/>

</body>
<script type="text/javascript" src="webjars/datetimepicker/2.3.4/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.9/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/javascript/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/javascript/asteriskDatatables.js"></script>


</html>

