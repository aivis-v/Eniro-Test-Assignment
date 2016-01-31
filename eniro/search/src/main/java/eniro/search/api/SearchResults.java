package eniro.search.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

import eniro.search.SearchResponse;

public class SearchResults implements SearchResponse {

	private List<JSONObject> results;
	
	public SearchResults() { 
		results = new ArrayList<JSONObject>();
	}
	
	public SearchResults(List<JSONObject> results) {
		this.results = results;
	}

	@JsonProperty
	public int getResultCount() {
		return results.size();
	}
	
	@JsonRawValue
	public List<JSONObject> getResults() {
		return results;
	}
	
	public void add(List<JSONObject> results) {
		this.results.addAll(results);
	}
	
}
