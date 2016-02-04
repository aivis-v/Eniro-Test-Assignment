package eniro.search.api;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchHistory {
	
	private Date startDate;
	private Date endDate;
	
	private List<SearchCriteria> searches;
	
	public SearchHistory() { }

	@JsonProperty
	public Date getStartDate() {
		return startDate;
	}

	@JsonProperty
	public Date getEndDate() {
		return endDate;
	}
	
	
}
