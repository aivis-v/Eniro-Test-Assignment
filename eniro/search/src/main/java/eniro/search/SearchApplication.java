package eniro.search;

import eniro.search.healthchecks.EniroAPIHealthCheck;
import eniro.search.resource.SearchResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SearchApplication extends Application<Configuration> {
    
	public static void main(String[] args) throws Exception {
        new SearchApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    	bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
    	bootstrap.addBundle(new AssetsBundle("/assets", "/enirotest", "index.html"));
    }

    @Override
    public void run(Configuration configuration,
                    Environment environment) {
    	
//		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    	
    	final SearchResource search = new SearchResource();
    	environment.jersey().register(search);
    	
        environment.healthChecks().register("Eniro API", new EniroAPIHealthCheck());
    	environment.jersey().setUrlPattern("/api/*");        
    }

}