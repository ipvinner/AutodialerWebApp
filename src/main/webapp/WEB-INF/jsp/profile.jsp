<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
  <div class="container">
    <div class="shadow">
      <h3 class="text-center">Edit profile ${user.login}</h3>
      <div class="view-box">
        <form id="editUser" method="post">
          <input type="text" hidden="hidden" id="id" name="id" value="${user.id}" >

          <div class="form-group">
            <label for="login" class="control-label col-xs-3">Login</label>

            <div class="col-xs-9">
              <input type="text" class="form-control" id="login" name="login" placeholder="login" value="${user.login}">
            </div>
          </div>

          <div class="form-group">
            <label for="login" class="control-label col-xs-3">Password</label>

            <div class="col-xs-9">
              <input type="password" class="form-control" id="password" name="password" placeholder="password" value="${user.passwordHash}" >
            </div>
          </div>

          <input type="text" hidden="hidden" id="role" name="role" value="${user.role}" >

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

</body>
<script type="text/javascript" src="webjars/noty/2.2.4/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/javascript/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/javascript/updateProfile.js"></script>
</html>
