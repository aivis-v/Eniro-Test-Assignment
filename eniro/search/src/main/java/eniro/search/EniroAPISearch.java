package eniro.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.json.JSONObject;

import eniro.search.api.SearchError;
import eniro.search.api.SearchResults;
import eniro.search.resource.utils.EniroUtil;

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
		apiUrl = EniroUtil.getPropertyValue("url", "config.properties");
	}
	
	private JSONObject getApiResults(String phrase) throws IOException {
		 URL obj = new URL(apiUrl+phrase);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", USER_AGENT);
	                
	        JSONObject jsonObj = null;
	        
	        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    con.getInputStream(),  "UTF-8"));
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
        try {
    	    jsonResults = getApiResults(phrase);
    	    if(jsonResults!=null) { 
    	    	results = new SearchResults(EniroUtil.extractFilteredData(jsonResults, filters));
    	    } else {
    	    	results = new SearchError();
    	    }
		} catch (IOException e) {
			e.printStackTrace();
		}
       return results;
	}	
	
	public static boolean isWorking(){
	    try {
	      HttpURLConnection con =
	         (HttpURLConnection) new URL(apiUrl).openConnection();
	      con.setRequestMethod("HEAD");
	      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
	  }  
	
}
