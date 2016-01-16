<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>Autodialer</title>
    <c:set var="url">${pageContext.request.requestURL}</c:set>


    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="resources/css/singin.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="shortcut icon" href="resources/images/cartrack-logo.png">
</head>
<body>

<div class="container">

    <form class="form-signin" role="form" action="spring_security_check" method="post">
        <div class="text-center"><img src="resources/images/cartrack-logo-big.png"></div>

        <h2 class="form-signin-heading text-center">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Login</label>
                <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Login" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

    <c:if test="${error}">
        <div class="error">
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message">
            <fmt:message key="${message}"/>
        </div>
    </c:if>

</div> <!-- /container -->

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
