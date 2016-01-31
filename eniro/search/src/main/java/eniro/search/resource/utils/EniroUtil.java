package eniro.search.resource.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class EniroUtil {

	public static List<JSONObject> extractFilteredData(JSONObject obj, List<String> filters) {
		List<JSONObject> results = new ArrayList<JSONObject>();
		JSONArray jsonResults = obj.getJSONArray("adverts");
		
		for(Object jsonObject : jsonResults) {
			JSONObject jsonResult = (JSONObject) jsonObject;
			
			JSONObject result = new JSONObject();
			
			for(String filter : filters) {
				if(jsonResult.has(filter)){
					result.accumulate(filter, jsonResult.get(filter));
				}
			}
			
			results.add(result);
		}
		
		return results;
	}	
}
