package eniro.search.resource.searchResultDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

	private String streetName;
	private String postCode;
	private String postArea;
	private String postBox;

	public Address(String streetName, String postCode, String postArea, String postBox) {
		this.streetName = streetName;
		this.postCode = postCode;
		this.postArea = postArea;
		this.postBox = postBox;
	}
	
	@JsonProperty
	public String getStreetName() {
		return streetName;
	}

	@JsonProperty
	public String getPostCode() {
		return postCode;
	}

	@JsonProperty
	public String getPostArea() {
		return postArea;
	}

	@JsonProperty
	public String getPostBox() {
		return postBox;
	}
}
