package com.test.wns.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

/**
 * This class serves as embedded primary key for final_crit_ext entity
 * @author charusingla
 *
 */
@Embeddable
public class EmbeddedPrimaryKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String company;
	
	@Column
	private String nvic;
	@Column
	private String vehcat;
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

