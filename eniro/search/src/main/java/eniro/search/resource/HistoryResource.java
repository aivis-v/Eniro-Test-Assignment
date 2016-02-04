package eniro.search.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eniro.search.api.SearchHistory;

@Path("/searchHistory")
@Produces(MediaType.APPLICATION_JSON)
public class HistoryResource {
	
	@GET
	public SearchHistory getSearchHistory(@QueryParam("start") Date start, @QueryParam("end") Date end) {
		
		Connection c = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:history.db");
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	    
		
	    return new SearchHistory();
	}
	
}
