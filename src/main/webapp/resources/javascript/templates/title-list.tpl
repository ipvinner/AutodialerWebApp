<div class="chooseFields">
    <% for(var i = 0; i < items.length; i++) { %>
        <div class="list-titles-item">
            <div class="inline-block">
                <%-items[i].title%>
            </div>

            <div class="inline-block">
                <select class="field-select" data-field="<%-items[i].id%>" data-title="<%-items[i].title%>">
                    <option>Select your option</option>
                    <%for(var j = 0; j < options.length; j++) { %>
                        <option data-index="<%-j%>"><%-options[j]%></option>
                    <% } %>
                </select>
            </div>

        </div>
    <% } %>

    <button id="renderTable" class="btn btn-primary" type="button" >Render Table</button>
</div>