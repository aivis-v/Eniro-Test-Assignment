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
import eniro.search.api.response.impl.SearchResults;
import eniro.search.app.service.SearchService;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Component
public class SearchServiceImpl implements SearchService {

    public SearchServiceImpl() { 
    	Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    	    public void run() {
    	    	threadpool.shutdown();
    	    }
    	}));
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
		
		waitForFuturesToComplete(searches);
		
		addAllResultsToList(searches, results);

		result = results;

		return result;
	}
	
	private void waitForFuturesToComplete(List<Future> futures) {
		//TODO - take care of taking too long  / endless loop ?
		int searchesDone = 0;
		while(searchesDone != futures.size()) {
			searchesDone = 0;
			for(Future search : futures) {
				if(search.isDone()){
					searchesDone += 1;
				}
			}
		}
	}
	
	private void addAllResultsToList(List<Future> futures, SearchResults list) {
		for(Future<SearchResults> search : futures) {
			try {
				list.add(search.get().getResults());
			} catch (InterruptedException e) {
		        log.log( Level.SEVERE, e.getMessage(), e);
			} catch (ExecutionException e) {
				log.log( Level.SEVERE, e.getMessage(), e);
			}
		}
	}

}
