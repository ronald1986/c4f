Hello!  Search here.
<p/>
<div id="moduleSearch"></div>
<script type="text/javascript">
var columns = new Array();
columns.push({id:'_id', label:'Id', editable: false, type: 'text'});
columns.push({id:'code', label:'Code', editable: false, type: 'text'});
columns.push({id:'name', label:'Name', editable: false, type: 'text'});
columns.push({id:'desc', label:'Description', editable: false, type: 'textarea'});
columns.push({id:'createDate', label:'Create Date', editable: true, type: 'date'});
columns.push({id:'updateDate', label:'Update Date', editable: true, type: 'date'});

Biz.Grid.createModuleSearchGrid('moduleSearch', columns);
</script>