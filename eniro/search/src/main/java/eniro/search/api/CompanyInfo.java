package eniro.search.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyInfo {
	
	private String companyName;
	private String orgNumber;
	private String companyText;
	
	public CompanyInfo(String companyName, String orgNumber, String companyText) {
		this.companyName = companyName;
		this.orgNumber = orgNumber;
		this.companyText = companyText;
	}

	@JsonProperty
	public String getCompanyName() {
		return companyName;
	}

	@JsonProperty
	public String getOrgNumber() {
		return orgNumber;
	}

	@JsonProperty
	public String getCompanyText() {
		return companyText;
	}
	
}
