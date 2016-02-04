package eniro.search.resource.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {

	public static List<JSONObject> extractFilteredData(JSONObject obj, List<String> filters) {
		List<JSONObject> results = new ArrayList<JSONObject>();
		JSONArray jsonResults = obj.getJSONArray("adverts");//could move to a properties file

		for(Object jsonObject : jsonResults) {
			JSONObject jsonResult = (JSONObject) jsonObject;
		
			if(filters.isEmpty() || !containsValuableData(filters)){
				results.add(jsonResult);
			}
			
			if(containsAnyKey(jsonResult, filters) && containsValuableData(filters)) {
				JSONObject result = new JSONObject();
				
				for(String filter : filters) {
					if(jsonResult.has(filter)) {
						result.accumulate(filter, jsonResult.get(filter));
					}
				}
				
				results.add(result);
			}
		}
		
		return results;
	}	

	private static boolean containsValuableData(List<String> filters) {
		for(String filter : filters){
			if(!filter.trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean containsAnyKey(JSONObject obj, List<String> keys) {
		for(String key : keys) {
			if(obj.has(key)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getPropertyValue(String key, String fileName){ 
		Properties configProp = new Properties();
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	    try {
	        configProp.load(in);
	        configProp.clone();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	      
	    return configProp.getProperty(key);
	}

	public static boolean isUrlResponseOk(String url) {
	    try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
	}
	
}
