package eniro.search;

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
    public void run(SearchConfiguration configuration, Environment environment) {
        // nothing to do yet
    }

}