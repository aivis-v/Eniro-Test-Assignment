package eniro.search.resource;

import com.google.common.base.Optional;
import eniro.search.api.SearchResult;
import eniro.search.api.SearchResults;
import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/enirotest/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    public SearchResource() { }

    @GET
    @Timed
    public SearchResults search(@QueryParam("phrases") Optional<String> phrases) {
    	List<SearchResult> results = new ArrayList<SearchResult>();
    	//TODO - perform search
        return new SearchResults(results);
    }
}
