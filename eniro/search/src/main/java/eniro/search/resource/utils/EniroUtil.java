package eniro.search.resource.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class EniroUtil {

	public static List<JSONObject> extractFilteredData(JSONObject obj, List<String> filters) {
		List<JSONObject> results = new ArrayList<JSONObject>();
		JSONArray array = obj.getJSONArray("adverts");
		
		for(Object jobj : array) {
			JSONObject jSon = (JSONObject) jobj;
			
			JSONObject jsonResult = new JSONObject();
			
			for(String filter : filters) {
				if(jSon.has(filter)){
					jsonResult.accumulate(filter, jSon.get(filter));
				}
			}
			
			results.add(jsonResult);
		}
		
		return results;
	}	
}
