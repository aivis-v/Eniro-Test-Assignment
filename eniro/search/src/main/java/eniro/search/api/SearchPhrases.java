package eniro.search.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchPhrases {

	public SearchPhrases() { }
	
	public SearchPhrases(List<String> phrases) {
		this.phrases = phrases;
	}
	private List<String> phrases;
	
	@JsonProperty
	public List<String> getPhrases() {
		return phrases;
	}
}
