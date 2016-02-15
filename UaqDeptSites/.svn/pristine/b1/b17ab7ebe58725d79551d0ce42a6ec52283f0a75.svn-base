package com.uaq.vo;

import java.io.Serializable;

import com.uaq.payment.PaymentUtil;

/**
 * Command Class for PurchaseService.
 * 
 * @author mraheem
 * 
 */
public class PurchaseServiceVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String purchaseId;

	private String serviceCode;

	private String quantity; 
	
	private String price;
	
	private Double priceDecimal; 

	private String ownerFees;
	
	private Double ownerFeesDecimal;
	
	private String amountWithoutFees;
	
	private Double amountWithoutFeesDecimal;
	
	private String amountWithFees;
	
	private Double amountWithFeesDecimal;	
	
	protected Boolean isVariable;
	
	protected String description;
	
	
	/**
	 * @return the purchaseId
	 */
	public String getPurchaseId() {
		return purchaseId;
	}

	/**
	 * @param purchaseId
	 *            the purchaseId to set
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * @param serviceCode
	 *            the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
			
	/**
	 * @return the isVariable
	 */
	public Boolean getIsVariable() {
		return isVariable;
	}

	/**
	 * @param isVariable the isVariable to set
	 */
	public void setIsVariable(Boolean isVariable) {
		this.isVariable = isVariable;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the ownerFees
	 */
	public String getOwnerFees() {
		return ownerFees;
	}

	/**
	 * @param ownerFees the ownerFees to set
	 */
	public void setOwnerFees(String ownerFees) {
		this.ownerFees = ownerFees;
	}

	/**
	 * @return the amountWithoutFees
	 */
	public String getAmountWithoutFees() {
		return amountWithoutFees;
	}

	/**
	 * @param amountWithoutFees the amountWithoutFees to set
	 */
	public void setAmountWithoutFees(String amountWithoutFees) {
		this.amountWithoutFees = amountWithoutFees;
	}

	/**
	 * @return the amountWithFees
	 */
	public String getAmountWithFees() {
		return amountWithFees;
	}

	/**
	 * @param amountWithFees the amountWithFees to set
	 */
	public void setAmountWithFees(String amountWithFees) {
		this.amountWithFees = amountWithFees;
	}

	/**
	 * @return the ownerFeesDecimal
	 */
	public Double getOwnerFeesDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(ownerFees);		
	}

	/**
	 * @return the amountWithoutFeesDecimal
	 */
	public Double getAmountWithoutFeesDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(amountWithoutFees);		
	}

	/**
	 * @return the amountWithFeesDecimal
	 */
	public Double getAmountWithFeesDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(amountWithFees);		
	}

	/**
	 * @return the priceDecimal
	 */
	public Double getPriceDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(price);		
	}
	
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PurchaseServiceVO [purchaseId=" + purchaseId + ", serviceCode=" + serviceCode + ", quantity=" + quantity + ", price=" + price
				+ ", priceDecimal=" + priceDecimal + ", ownerFees=" + ownerFees + ", ownerFeesDecimal=" + ownerFeesDecimal + ", amountWithoutFees="
				+ amountWithoutFees + ", amountWithoutFeesDecimal=" + amountWithoutFeesDecimal + ", amountWithFees=" + amountWithFees
				+ ", amountWithFeesDecimal=" + amountWithFeesDecimal + ", isVariable=" + isVariable + "]";
	}

	
}
