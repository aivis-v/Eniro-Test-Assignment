package eniro.search.api;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonRawValue;

import eniro.search.SearchResponse;

public class SearchError implements SearchResponse {
	
	private JSONObject error;
	
	public SearchError() { 
		error = new JSONObject();
		error.put("Error", "Problem with search.");
	}
	
	@JsonRawValue
	public JSONObject getError() {
		return error;
	}
}
