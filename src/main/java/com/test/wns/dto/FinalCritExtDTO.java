package com.test.wns.dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(acceptcrit, addmod, changetimestamp, company, effectivedate, effectiveenddate,
				enddatetimestamp, internetjep, nvic, vehcat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinalCritExtDTO other = (FinalCritExtDTO) obj;
		return Objects.equals(acceptcrit, other.acceptcrit) && Objects.equals(addmod, other.addmod)
				&& Objects.equals(changetimestamp, other.changetimestamp) && Objects.equals(company, other.company)
				&& Objects.equals(effectivedate, other.effectivedate)
				&& Objects.equals(effectiveenddate, other.effectiveenddate)
				&& Objects.equals(enddatetimestamp, other.enddatetimestamp)
				&& Objects.equals(internetjep, other.internetjep) && Objects.equals(nvic, other.nvic)
				&& Objects.equals(vehcat, other.vehcat);
	}



	
	
	
}
