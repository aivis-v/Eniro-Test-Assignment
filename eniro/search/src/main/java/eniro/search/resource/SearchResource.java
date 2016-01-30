package eniro.search.resource;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eniro.search.api.SearchPhrases;
import eniro.search.api.SearchResult;
import eniro.search.api.SearchResults;

@Path("/enirotest/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    public SearchResource() { }
    
    @POST
    public SearchResults searchPhrases(SearchPhrases phrases) {
    	List<SearchResult> results = new ArrayList<SearchResult>();
    	//TODO - perform search
        return new SearchResults(results);
    }
}
