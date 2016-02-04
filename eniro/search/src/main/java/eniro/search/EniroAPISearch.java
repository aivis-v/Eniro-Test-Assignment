package eniro.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

import org.json.JSONObject;

import eniro.search.api.response.SearchResponse;
import eniro.search.api.response.impl.SearchError;
import eniro.search.api.response.impl.SearchResults;
import eniro.search.resource.utils.Utils;

public class EniroAPISearch implements Callable<SearchResponse> {

	private String phrase;
	private List<String> filters;
	private static String apiUrl;
	private static final String USER_AGENT = "Mozilla/5.0";
	
	
	public EniroAPISearch(String phrase, List<String> filters) {
		this.phrase = phrase;
		this.filters = filters;
	}
	
	static {
		apiUrl = Utils.getPropertyValue("url", "config.properties");
	}
	
	private JSONObject getApiResults(String phrase) throws IOException {
		 URL obj = new URL(apiUrl+phrase);
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 con.setRequestMethod("GET");
		 con.setRequestProperty("User-Agent", USER_AGENT);
	            
		JSONObject jsonObj = null;
		
		if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
		    BufferedReader in = new BufferedReader(
		    		new InputStreamReader(con.getInputStream(),  "UTF-8"));
		    
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            jsonObj = new JSONObject(response.toString());
		} 
        return jsonObj;
	}

	public SearchResponse call() throws Exception {
		JSONObject jsonResults = null;
		SearchResponse results = null;
	    jsonResults = getApiResults(phrase);
	    
	    if(jsonResults != null) { 
	    	results = new SearchResults(Utils.extractFilteredData(jsonResults, filters));
	    } else {
	    	results = new SearchError("Problem getting results from API.");
	    }
       return results;
	}	
	
	public static boolean isWorking(){
		return Utils.isUrlResponseOk(apiUrl);
	}  
	
}
