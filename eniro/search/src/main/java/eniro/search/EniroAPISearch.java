package eniro.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.Callable;

import org.json.JSONObject;

import eniro.search.resource.utils.EniroUtil;
import eniro.search.api.SearchResults;

public class EniroAPISearch implements Callable<SearchResults> {

	private final String phrase;
	private static String apiUrl;
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public EniroAPISearch(String phrase) {
		this.phrase = phrase;
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			input = loader.getResourceAsStream("config.properties");
			prop.load(input);
			apiUrl = prop.getProperty("url");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
	
	

	public SearchResults call() throws Exception{
		JSONObject jsonResults = null;
		SearchResults results = null;
       try {
    	   jsonResults = getApiResults(phrase);
    	   results = new SearchResults(EniroUtil.extractData(jsonResults));
		} catch (IOException e) {
			e.printStackTrace();
		}
       return results;
	}
}
