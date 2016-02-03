package eniro.search.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eniro.search.EniroAPISearch;
import eniro.search.SearchResponse;
import eniro.search.api.SearchCriteria;
import eniro.search.api.SearchError;
import eniro.search.api.SearchResults;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    public SearchResource() { 
    	Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    	    public void run() {
    	    	threadpool.shutdown();
    	    }
    	}));
    }
    
    private static final ExecutorService threadpool = Executors.newCachedThreadPool();
        
    @POST
	public SearchResponse searchPhrases(SearchCriteria criteria) {
		SearchResponse result = null;
		
		if(!EniroAPISearch.isWorking()) {
			result = new SearchError();
		} else {
			SearchResults results = new SearchResults();
			List<Future> searches = new ArrayList<Future>();
			
			for(String phrase : criteria.getPhrases()) {
				searches.add(threadpool.submit(new EniroAPISearch(phrase, criteria.getFilters())));
			}
			
			int searchesDone = 0;
			while(searchesDone != searches.size()) {
				searchesDone = 0;
				for(Future search : searches) {
					if(search.isDone()){
						searchesDone += 1;
					}
				}
			}
			
			for(Future<SearchResults> search : searches) {
				try {
					results.add(search.get().getResults());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			result = results;
		}
		return result;
	}
 
}
