package com.test.wns.dto;

/**
 * This is data transfer object to take input from entity and write to csv file
 * 
 * @author charusingla
 *
 */
public class FinalCritExtDTO {

	private String company;
	private String nvic;
	private String vehcat;
	private String addmod;
	private String effectivedate;
	private String changetimestamp;
	private String effectiveenddate;
	private String enddatetimestamp;
	private String acceptcrit;
	private String internetjep;

	public String getAddmod() {
		return addmod;
	}

	public void setAddmod(String addmod) {
		this.addmod = addmod;
	}

	public String getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public String getChangetimestamp() {
		return changetimestamp;
	}

	public void setChangetimestamp(String changetimestamp) {
		this.changetimestamp = changetimestamp;
	}

	public String getEffectiveenddate() {
		return effectiveenddate;
	}

	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}

	public String getEnddatetimestamp() {
		return enddatetimestamp;
	}

	public void setEnddatetimestamp(String enddatetimestamp) {
		this.enddatetimestamp = enddatetimestamp;
	}

	public String getAcceptcrit() {
		return acceptcrit;
	}

	public void setAcceptcrit(String acceptcrit) {
		this.acceptcrit = acceptcrit;
	}

	public String getInternetjep() {
		return internetjep;
	}

	public void setInternetjep(String internetjep) {
		this.internetjep = internetjep;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNvic() {
		return nvic;
	}

	public void setNvic(String nvic) {
		this.nvic = nvic;
	}

	public String getVehcat() {
		return vehcat;
	}

	public void setVehcat(String vehcat) {
		this.vehcat = vehcat;
	}

}
