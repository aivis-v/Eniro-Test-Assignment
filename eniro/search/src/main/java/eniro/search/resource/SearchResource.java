package eniro.search.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eniro.search.api.SearchCriteria;
import eniro.search.api.response.SearchResponse;
import eniro.search.app.service.SearchService;
import eniro.search.app.service.impl.SearchServiceImpl;

//@Service
@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

//	@Autowired
//	@Qualifier("searchService")
	private SearchService searchService = new SearchServiceImpl();
	
    @POST
	public SearchResponse searchPhrases(SearchCriteria criteria) {
		return searchService.searchEniroAPI(criteria);
	}
 
}
