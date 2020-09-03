package com.test.wns.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This is entity class for fetching data from db
 * 
 * @author charusingla
 *
 */
@Entity
@Table(name = "FINAL_CRIT_EXT")
public class FinalCritExt {
	@EmbeddedId
	private  EmbeddedPrimaryKey pk;
	
	
	@Column
	private String addmod;
	@Column
	private String effectivedate;
	@Column
	private String changetimestamp;
	@Column
	private String effectiveenddate;
	@Column
	private String enddatetimestamp;
	@Column
	private String acceptcrit;
	@Column
	private String internetjep;
	public EmbeddedPrimaryKey getPk() {
		return pk;
	}
	public void setPk(EmbeddedPrimaryKey pk) {
		this.pk = pk;
	}
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
	
	
	
	

}

