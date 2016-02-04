package eniro.search.resource.utils;

import java.io.IOException;
import java.io.InputStream;
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
	
	public static String getPropertyValue(String propertyName, String fileName){ 
		Properties prop = new Properties();
		InputStream input = null;
		
		String value = null;
		
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			input = loader.getResourceAsStream(fileName);
			prop.load(input);
			value = prop.getProperty(propertyName);
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
		
		return value;
	}
}
