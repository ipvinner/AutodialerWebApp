<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top ">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--<a class="navbar-brand" href="#">Bootstrap theme</a>--%>
            <a href="/autodialer"><img src="resources/images/cartrack.png"/></a>
    </div>
<div id="navbar" class="navbar-collapse collapse navbar-right">
    <ul class="nav navbar-nav">
    <li class="active"><a href="/autodialer">Home</a></li>

    <li><a href="results">Statistics</a></li>
    <li><a href="clients">Clients</a></li>
    <li><a href="tasks">Tasks</a></li>
    <li><a href="asterisk">Asterisk settings</a></li>

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Numbers list <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="addList">Add new clients list</a></li>
                <li><a href="clientsList">Show clients lists</a></li>
            </ul>
        </li>

    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">John Smith <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#"><i class="fa fa-fw fa-user"></i>Profile</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">log out</a></li>
        </ul>
    </li>

        <%--<li class="dropdown">--%>
            <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${pageContext.response.locale} <span class="caret"></span></a>--%>
            <%--<ul class="dropdown-menu">--%>
                <%--<li><a href="?lang=en">English</a></li>--%>
                <%--<li><a href="?lang=ru">Russian</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <jsp:include page="lang.jsp"/>
    </ul>
</div><!--/.nav-collapse -->
</div>
</nav>


