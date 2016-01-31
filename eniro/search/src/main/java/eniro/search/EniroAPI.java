package eniro.search;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class EniroAPI {

	private static String apiUrl;
	private static final String USER_AGENT = "Mozilla/5.0";
	
	static {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
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
	 
	public static String search(String phrase) {
		String result = "";
		//Get results from API
       try {
    	   result = getApiResults(phrase);
		} catch (IOException e) {
			e.printStackTrace();
		}
       //Filter results.. get address, phoneNumbers, companyInfo
       //return SearchResult object 
       return result;
    }
	
	private static String getApiResults(String phrase) throws IOException {
		 URL obj = new URL(apiUrl+phrase);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", USER_AGENT);
	                
	        String rez = "";
	        
	        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();
	 
	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();
	            rez = response.toString();
	        }
	        return rez;
	}
}
