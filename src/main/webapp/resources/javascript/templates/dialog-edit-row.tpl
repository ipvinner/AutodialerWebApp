<h3>Edit Row Data</h3>

<dl id="row-content">
<%for(var i = 0; i < data.length; i++) {%>
    <%for(var value in data[i]) { %>
        <dt><%-value%></dt>
        <%if(index === "newString") {%>
        <dd class="empty" contenteditable="true"></dd>
        <%} else {%>
        <dd contenteditable="true"><%-data[i][value]%></dd>
        <% } %>
        <br>
    <% } %>
<% } %>
</dl>

<button data-index="<%-index%>" class="btn btn-primary" data-table-custom="update-row" type="button">Update</button>