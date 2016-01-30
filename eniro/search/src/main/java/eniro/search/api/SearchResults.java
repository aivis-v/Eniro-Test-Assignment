package eniro.search.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResults {

	private List<SearchResult> results;
	
	public SearchResults() { }
	
	public SearchResults(List<SearchResult> results) {
		this.results = results;
	}
    
	@JsonProperty
	public List<SearchResult> getResults() {
		return results;
	}
}
