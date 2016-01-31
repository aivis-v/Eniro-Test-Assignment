package eniro.search.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneNumber {
	
	private String type;
	private String phoneNumber;
	private String label;
	
	public PhoneNumber(String type, String number, String label) { 
		this.type = type;
		this.phoneNumber = number;
		this.label = label;
	}
	
	@JsonProperty
	public String getType() {
		return type;
	}
	
	@JsonProperty
	public String getNumber() {
		return phoneNumber;
	}
	
	@JsonProperty
	public String getLabel() {
		return label;
	}
	
}
