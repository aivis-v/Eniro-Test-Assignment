/**
 * 
 */

function getJSON() {
	var phrases = document.getElementsByName("phrases")[0].value.split(","); 
	var filters = document.getElementsByName("filters")[0].value.split(",");

	var json = '{ "phrases" : ' + JSON.stringify(phrases) + ',' +
	'"filters" :  ' + JSON.stringify(filters) + ' }';
	alert(json);
	return json;
}



$(function () {

    $('form').on('submit', function (e) {

      e.preventDefault();
      $.ajax({
  	    type: 'POST',
  	    url: '/search',
  	    data: getJSON(), 
  	    success: function(data) { 
//  	    	alert('data: ' + ); 
  	    	console.log(JSON.stringify(data));
    	},
  	    contentType: "application/json",
  	    dataType: 'json'
  	});
    });
});
//function postData() {
//	$.ajax({
//	    type: 'POST',
//	    url: '/search',
//	    data: getJSON, 
//	    success: function(data) { alert('data: ' + data); },
//	    contentType: "application/json",
//	    dataType: 'json'
//	});
//}
//
//$(function() {
//	$(".submit").click(function() {
//		alert("submit it!");
//		postData();
//	});
//	return false;
//});

function handleResponse() {
	
}
