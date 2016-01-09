<%for(var i = 0; i < data.length; i++) { %>
	<tr data-index="<%-i%>">
	    <%for(var value in data[i]) { %>
	    <%if(value === "index") continue;%>
	    <td><%-data[i][value]%></td>
	    <% } %>
	    <td>
	        <button class="btn btn-primary" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button>
	        <button class="btn btn-primary" data-table-custom="delete-row" type="button">Delete</button>
	    </td>
	</tr>
<% } %>