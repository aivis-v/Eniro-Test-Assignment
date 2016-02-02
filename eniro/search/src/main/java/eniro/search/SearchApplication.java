package eniro.search;

import eniro.search.healthchecks.EniroAPIHealthCheck;
import eniro.search.resource.SearchResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SearchApplication extends Application<SearchConfiguration> {
    
	public static void main(String[] args) throws Exception {
        new SearchApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SearchConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(SearchConfiguration configuration,
                    Environment environment) {
    	
    	final SearchResource resource = new SearchResource();
        environment.jersey().register(resource);
        
        environment.healthChecks().register("Eniro API", new EniroAPIHealthCheck());
    }

}