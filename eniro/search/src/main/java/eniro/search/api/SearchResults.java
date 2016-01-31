package eniro.search.api;

import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class SearchResults {

	private List<JSONObject> results;
	
	public SearchResults() { }
	
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
	
}
