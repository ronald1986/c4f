// To-fix Calendar not shown in grid issue
YAHOO.widget.DateCellEditor.prototype.resetForm =  function() {
    var value = new Date(this.value);
    var selectedValue = (value.getMonth()+1)+"/"+value.getDate()+"/"+value.getFullYear();
    this.calendar.cfg.setProperty("selected",selectedValue,false);
   this.calendar.render();
};