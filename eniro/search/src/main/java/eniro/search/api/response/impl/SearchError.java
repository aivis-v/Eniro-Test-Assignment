package eniro.search.api.response.impl;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonRawValue;

import eniro.search.api.response.SearchResponse;

public class SearchError implements SearchResponse {
	
	private JSONObject error;
	
	public SearchError(String errorMessage) { 
		error = new JSONObject();
		error.put("Error", errorMessage);
	}
	
	@JsonRawValue
	public JSONObject getError() {
		return error;
	}
}
