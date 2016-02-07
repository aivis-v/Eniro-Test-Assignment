package eniro.search.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import eniro.search.EniroAPISearch;
import eniro.search.api.SearchCriteria;
import eniro.search.api.response.SearchResponse;
import eniro.search.api.response.impl.SearchError;
import eniro.search.api.response.impl.SearchResults;
import eniro.search.app.service.SearchService;
import eniro.search.resource.utils.Utils;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Component
public class SearchServiceImpl implements SearchService {

	private int timeout;
	
    public SearchServiceImpl() { 
    	Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    	    public void run() {
    	    	threadpool.shutdown();
    	    }
    	}));
    	
    	timeout = Integer.parseInt(Utils.getPropertyValue("timeout", "config.properties"));
    }
    
    private static final Logger log = Logger.getLogger( ClassName.class.getName() );
    private static final ExecutorService threadpool = Executors.newCachedThreadPool();
        
	public SearchResponse search(SearchCriteria criteria) {
		SearchResponse result = null;

		SearchResults results = new SearchResults();
		List<Future> searches = new ArrayList<Future>();
		
		for(String phrase : criteria.getPhrases()) {
			searches.add(threadpool.submit(new EniroAPISearch(phrase, criteria.getFilters())));
		}
		
		boolean allFuturesDone = areAllFuturesDone(searches);
		
		if(allFuturesDone) {
			result = getAllResultsAsList(searches, results);
		} else {
			result = new SearchError("Problem with search results.");
		}
		
		return result;
	}
	
	private boolean areAllFuturesDone(List<Future> futures) {
		long start = System.currentTimeMillis();
		int searchesDone = 0;
		
		while(searchesDone != futures.size() && System.currentTimeMillis() < start + timeout * 1000) {
			searchesDone = 0;
			
			for(Future search : futures) {
				if(search.isDone()){
					searchesDone += 1;
				}
				
				if(search.isCancelled()) {
					return false;
				}
			}
		}
		
		return searchesDone == futures.size();
	}
	
	private SearchResponse getAllResultsAsList(final List<Future> futures, final SearchResults list) {
		SearchResults results = new SearchResults();
		
		for(Future<SearchResults> search : futures) {
			try {
				results.add(search.get().getResults());
			} catch (InterruptedException e) {
		        log.log( Level.SEVERE, e.getMessage(), e);
		        return new SearchError("Search was interrupted.");
		        
			} catch (ExecutionException e) {
				log.log( Level.SEVERE, e.getMessage(), e);
				return new SearchError("Problem with search execution.");
			}
		}
		return results;
	}

}
