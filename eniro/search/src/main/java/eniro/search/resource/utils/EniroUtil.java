package eniro.search.resource.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import eniro.search.api.SearchResult;
import eniro.search.resource.searchResultDetails.Address;
import eniro.search.resource.searchResultDetails.CompanyInfo;
import eniro.search.resource.searchResultDetails.PhoneNumber;

public class EniroUtil {
	
	public static List<SearchResult> extractData(JSONObject obj) {
		List<SearchResult> results = new ArrayList<SearchResult>();
		JSONArray array = obj.getJSONArray("adverts");
		
		for(Object jobj : array) {
			JSONObject jSon = (JSONObject) jobj;
			
			List<PhoneNumber> phoneNumbers = extractPhoneNumbers(jSon);
			Address address = extractAddress(jSon);
			CompanyInfo companyInfo = extractCompanyInfo(jSon);
			
			SearchResult res = new SearchResult(address, companyInfo, phoneNumbers);
			results.add(res);
		}
		
		return results;
	}
	
	private static CompanyInfo extractCompanyInfo(JSONObject obj) {
		JSONObject companyInfo = (JSONObject) obj.get("companyInfo");
		
		String companyName = getJSONString(companyInfo, "companyName");
		String orgNumber = getJSONString(companyInfo, "orgNumber");
		String companyText = getJSONString(companyInfo, "companyText");
		
		return new CompanyInfo(companyName, orgNumber, companyText);
	}
	
	private static Address extractAddress(JSONObject obj) {
		JSONObject jsonAddress = (JSONObject) obj.get("address");
		
		String streetName = getJSONString(jsonAddress, "streetName");
		String postCode = getJSONString(jsonAddress, "postCode");
		String postArea = getJSONString(jsonAddress, "postArea");
		String postBox = getJSONString(jsonAddress, "postBox");
		
		return new Address(streetName, postCode, postArea, postBox);
	}
	
	private static List<PhoneNumber> extractPhoneNumbers(JSONObject obj) {
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		JSONArray jsonPhoneNumbers = obj.getJSONArray("phoneNumbers");
		
		for(Object nr : jsonPhoneNumbers) {
			JSONObject jsonNr = (JSONObject) nr;
			
			String type = getJSONString(jsonNr, "type");
			String number = getJSONString(jsonNr, "phoneNumber");
			String label = getJSONString(jsonNr, "label");
			
			phoneNumbers.add(new PhoneNumber(type, number, label));
		}
		return phoneNumbers;
	}
	
	private static String getJSONString(JSONObject json, String key) {
		String value = null;
		try {
			value = (String) json.get(key);
		} catch (Exception ex){ }
		return value;
	}
}
