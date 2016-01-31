package eniro.search.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
	
	private Address address;
	private CompanyInfo companyInfo;
	private List<PhoneNumber> phoneNumbers;
	
	public SearchResult(Address address, CompanyInfo companyInfo, List<PhoneNumber> phoneNumbers) { 
		this.address = address;
		this.companyInfo = companyInfo;
		this.phoneNumbers = phoneNumbers;
	}
	
	@JsonProperty
	public Address getAddress() {
		return address;
	}
	
	@JsonProperty
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	
	@JsonProperty
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
}
