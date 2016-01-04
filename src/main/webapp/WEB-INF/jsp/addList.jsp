<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
<body>

<jsp:include page="fragments/bodyHeader.jsp"/>


<div class="jumbotron">
    <div class="container">
        <div class="container">

            <div data-component="userUploadCSVFile">
                <label for="uploadFile">Add new list users</label>
                <input type="file" name="uploadFile" id="uploadFile"/>
            </div>
            <!-- end userUploadCSVFile -->
        </div>

        <script src="resources/javascript/parseCSVFile.js"></script>
        <script src="resources/javascript/table-custom.js"></script>
        <script src="resources/javascript/dialog.js"></script>
        <script src="resources/javascript/main.js"></script>
        </div>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

</body>



</html>

