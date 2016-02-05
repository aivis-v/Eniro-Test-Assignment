package eniro.search.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eniro.search.api.SearchCriteria;
import eniro.search.api.response.SearchResponse;
import eniro.search.app.service.SearchService;

@Component
@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

	@Autowired
	private SearchService searchService;
	
    @POST
	public SearchResponse searchPhrases(SearchCriteria criteria) {
		return searchService.searchEniroAPI(criteria);
	}
 
}
