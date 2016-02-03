
function getJSON() {
	var phrases = document.getElementsByName("phrases")[0].value.split(","); 
	var filters = document.getElementsByName("filters")[0].value.split(",");

	var json = '{ "phrases" : ' + JSON.stringify(phrases) + ',' +
	'"filters" :  ' + JSON.stringify(filters) + ' }';
	
	return json;
}

$(function () {
    $('form').on('submit', function (e) {
      e.preventDefault();
      clearResults();
      
      $.ajax({
  	    type: 'POST',
  	    url: '/search',
  	    data: getJSON(), 
  	    success: function(data) { 
  	    	displayResults(data);
    	},
  	    contentType: "application/json",
  	    dataType: 'json'
  	});
    });
});

function clearResults() {
	if(document.getElementById('results') != null) {
		document.getElementById('results').remove();
	}
}

function getResultsAsHTML(json) {
	"use strict";
	
	var html = '<br>';
	
	if(json == null || typeof json === 'undefined') {
		return '';
	}
	
	for (let key of Object.keys(json)) {
		html += '<b>'+ key +':</b> ';
		
		if(Array.isArray(json[key])) {
			for (let element of json[key]) {
				html += getResultsAsHTML(element); 
			}
		} else {
			html += json[key] + '<br>';
		}
	}
	
	return html;
}

function displayResults(json) {
	var openingDiv = '<div id="results">  ' + getResultsAsHTML(json);
	var resultsBody = '';
	var closingDiv = '</div>';
	$("body").append(openingDiv);
	$("body").append(closingDiv);
}
