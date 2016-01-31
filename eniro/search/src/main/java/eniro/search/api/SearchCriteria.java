package eniro.search.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchCriteria {
	
	private List<String> phrases;
	private List<String> filters;
	
	public SearchCriteria() { }
	
	public SearchCriteria(List<String> phrases, List<String> filters) {
		this.phrases = phrases;
		this.filters = filters;
	}

	@JsonProperty
	public List<String> getPhrases() {
		return phrases;
	}

	@JsonProperty
	public List<String> getFilters() {
		return filters;
	}
	
}
