<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">
<body>

<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
  <div class="container">
    <div class="shadow">
      <h3><fmt:message key="result.list"/></h3>

      <div class="view-box">

        <form:form method="post" class="form-horizontal" role="form" id="filter">
          <div class="form-group">
            <label class="control-label col-sm-2" for="startDate"><fmt:message key="result.datetime"/></label>

            <div class="col-sm-2">
              <input name="startDate" id="startDate" class="date-picker">
            </div>

            <label class="control-label col-sm-2" for="endDate"><fmt:message key="result.datetime"/></label>

            <div class="col-sm-2">
              <input name="endDate" id="endDate" class="date-picker">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-2" for="startTime"><fmt:message key="result.datetime"/></label>

            <div class="col-sm-2">
              <input name="startTime" id="startTime" class="time-picker">
            </div>

            <label class="control-label col-sm-2" for="endTime"><fmt:message key="result.datetime"/></label>

            <div class="col-sm-2">
              <input name="endTime" id="endTime" class="time-picker">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-8">
              <button type="submit" class="btn btn-primary pull-right">Filter</button>
            </div>
          </div>
        </form:form>

        <table class="table table-striped display" id="datatable">
          <thead>
          <tr>
            <th><fmt:message key="result.datetime"/></th>
            <th><fmt:message key="result.task"/></th>
            <th><fmt:message key="result.client"/></th>
            <th><fmt:message key="result.number"/></th>
            <th><fmt:message key="result.result"/></th>
            <th><fmt:message key="result.reason"/></th>
          </tr>
          </thead>
        </table>

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
<script type="text/javascript" src="resources/javascript/resultsDatatables.js"></script>


</html>
