package com.uaq.vo;

import java.io.Serializable;

/**
 * Command Class for Registration.
 * 
 * @author mraheem
 * 
 */
public class ServiceVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String serviceCode; // eServiceMainCodeSubCode_n, n group 
	
	private Double unitPrice; // eServicePrice_n, n group 	
	
	private String descriptionEn;		
	
	private String descriptionAr;
	
		
	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the descriptionEn
	 */
	public String getDescriptionEn() {
		return descriptionEn;
	}
	/**
	 * @param descriptionEn the descriptionEn to set
	 */
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	/**
	 * @return the descriptionAr
	 */
	public String getDescriptionAr() {
		return descriptionAr;
	}
	/**
	 * @param descriptionAr the descriptionAr to set
	 */
	public void setDescriptionAr(String descriptionAr) {
		this.descriptionAr = descriptionAr;
	}	
	
}
