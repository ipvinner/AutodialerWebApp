<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<head>
    <title></title>
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Autodialer <small>application Overview</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-info-circle"></i>
                    <p><b>Autodialer</b> is a web/java/asterisk application for automatic dialing customers/clients by list.</p>
                    <p>Autodialer application can be used for the following tasks:</p>
                    <ul>
                        <li>inform clients about current or new products and services or new prices</li>
                        <li>notify about balances</li>
                        <li>inform on debts</li>
                        <li>warnings about somethings</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-info alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-info-circle"></i> <b>Technical specification: </b>
                    <ul>
                        <li>Java 8 + Spring Framework</li>
                        <li>IP PBX system: Asterisk 1.8</li>
                        <li>Database: Database PostgreSQL</li>
                        <li>Frontend: html + css + javascript + jquery based on REST</li>
                        <li>Web-server: apache-tomcat</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-info-circle"></i> <b>Authorization roles: </b>
                    <ul>
                        <li>Admin - allow all parts of application</li>
                        <li>Manager - not allow Administration section(asterisk and users settings)</li>
                        <li>User - allow only show statistics and add new client at the existing list</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-warning  alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-info-circle"></i> <b>Features: </b>
                    <ul>
                        <li>Asterisk configuration <a href="asterisk">details</a>: Applicatin communication with asterisk server through AMI. AMI connections settings is autodialer.properties  </li>
                        <li>Results of calls are saving to db. <a href="results">show</a></li>
                        <li>Add list of numbers from csv file: <a href="addList">addList</a></li>
                        <li>After adding list of numbers create task and start process of autodialing <a href="tasks">addTask</a></li>
                        <li>Application is support miltilanguage: now russian and english are supported</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- /.row -->

        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-comments fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">26</div>
                                <div>Tasks</div>
                            </div>
                        </div>
                    </div>
                    <a href="tasks">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-tasks fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">12</div>
                                <div>Client Lists</div>
                            </div>
                        </div>
                    </div>
                    <a href="clients">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-shopping-cart fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">3</div>
                                <div>Users</div>
                            </div>
                        </div>
                    </div>
                    <a href="users">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6">
                <div class="panel panel panel-danger">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-support fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">13</div>
                                <div>Failed calls</div>
                            </div>
                        </div>
                    </div>
                    <a href="results">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
