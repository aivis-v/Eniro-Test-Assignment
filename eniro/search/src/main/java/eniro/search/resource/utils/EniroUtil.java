package eniro.search.resource.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class EniroUtil {

	public static List<JSONObject> extractFilteredData(JSONObject obj, List<String> filters) {
		List<JSONObject> results = new ArrayList<JSONObject>();
		JSONArray jsonResults = obj.getJSONArray("adverts");//could move to a properties file
		
		for(Object jsonObject : jsonResults) {
			JSONObject jsonResult = (JSONObject) jsonObject;
		
			if(filters.isEmpty()){
				results.add(jsonResult);
			}
			
			if(containsAnyKey(jsonResult, filters)) {
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
	
	private static boolean containsAnyKey(JSONObject obj, List<String> keys) {
		for(String key : keys) {
			if(obj.has(key)) {
				return true;
			}
		}
		return false;
	}
}
