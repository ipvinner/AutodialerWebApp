<div data-component="table-custom">
    <div class="table-custom__navigation">
        <button class="btn btn-primary" data-table-custom="add-row" data-component="dialog" type="button">Add</button>
        <button class="btn btn-primary" data-table-custom="submit" type="button">Submit</button>
    </div>

    <table id="customUserTableFromCSV" class="table" data-component="table-custom">
        <thead>
            <tr>
                <%for(var i = 0; i < title.length; i++) { %>
                    <th data-table-custom="<%-title[i].id%>"><%-title[i].title%></th>
                <% } %>
                <th>
                    Edit/Remove
                </th>
            </tr>
        </thead>

        <tbody>
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
        </tbody>
    </table>
</div>