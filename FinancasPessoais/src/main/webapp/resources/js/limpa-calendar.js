//$(document).ready(function () {
//	
//	function cleanDatepicker() {
//	  var old_fn = $.datepicker._updateDatepicker;
//	
//	  $.datepicker._updateDatepicker = function(inst) {
//	     old_fn.call(this, inst);
//	
//	     var buttonPane = $(this).datepicker("widget").find(".ui-datepicker-buttonpane");
//	
//	     $("<button type='button' class='ui-datepicker-clean ui-state-default ui-priority-primary ui-corner-all'>Delete</button>").appendTo(buttonPane).click(function(ev) {
//	         $.datepicker._clearDate(inst.input);
//	     }) ;
//	  }
//	}
//});