package eniro.search;

import eniro.search.healthchecks.EniroAPIHealthCheck;
import eniro.search.resource.SearchResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SearchApplication extends Application<SearchConfiguration> {
    
	public static void main(String[] args) throws Exception {
        new SearchApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SearchConfiguration> bootstrap) {
    	bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
    	bootstrap.addBundle(new AssetsBundle("/assets", "/enirotest", "index.html"));
    }

    @Override
    public void run(SearchConfiguration configuration,
                    Environment environment) {
    	
    	final SearchResource resource = new SearchResource();
    	environment.jersey().register(resource);
    	
        environment.healthChecks().register("Eniro API", new EniroAPIHealthCheck());
    	environment.jersey().setUrlPattern("/api/*");        
    }

}