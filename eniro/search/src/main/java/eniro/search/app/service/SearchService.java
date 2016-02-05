package eniro.search.app.service;

import eniro.search.api.SearchCriteria;
import eniro.search.api.response.SearchResponse;

public interface SearchService {

	public SearchResponse search(SearchCriteria criteria);
	
}
