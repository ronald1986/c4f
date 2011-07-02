<table>
    <tr>
        <td>Code</td>
        <td><input type="text" id="code" class="dataField" onchange=""/></td>
        <td>Name</td>
        <td><input type="text" id="name" class="dataField"/></td>
    </tr>
</table>
<div id="attachmentsGrid"></div>
<script type="text/javascript">
var columns = new Array();
columns.push({id:'fileSize', label:'File Size', editable: false, type: 'text'});
columns.push({id:'fileName', label:'File Name', editable: false, type: 'text'});
columns.push({id:'desc', label:'Description', editable: true, type: 'textarea'});
columns.push({id:'test', label:'Test', editable: true, type: 'date'});
columns.push({id:'createDate', label:'Create Date', editable: false, type: 'date'});
columns.push({id:'updateDate', label:'Update Date', editable: false, type: 'date'});

Biz.Grid.createModuleChildGrid('attachmentsGrid', 'attachments', columns);
</script>