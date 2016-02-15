package com.uaq.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class LandandPropertyVO {

	private String indicatePosition;
	private String landStatus;
	private String landType;
	private String sitePlanNumber;
	private String sector;
	private String sectorBlock;
	private String subSector;
	private String sectorPlotNumber;
	private String area;
	private String areaBlock;
	private String subArea;
	private String areaPlotNumber;
	private MultipartFile ownerCertificate;
	private MultipartFile sitePlanDocument;

	private String ownershipStatus;
	private Date sitePlanDate;
	private String requestStatus;
	private String requestType;
	private String ownerName;
	private BigDecimal familyBookID;
	private String ownerNationality;

	public Date getSitePlanDate() {
		return sitePlanDate;
	}

	public void setSitePlanDate(Date sitePlanDate) {
		this.sitePlanDate = sitePlanDate;
	}

	public String getOwnershipStatus() {
		return ownershipStatus;
	}

	public void setOwnershipStatus(String ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

	public String getIndicatePosition() {
		return indicatePosition;
	}

	public void setIndicatePosition(String indicatePosition) {
		this.indicatePosition = indicatePosition;
	}

	public String getLandStatus() {
		return landStatus;
	}

	public void setLandStatus(String landStatus) {
		this.landStatus = landStatus;
	}

	public String getLandType() {
		return landType;
	}

	public void setLandType(String landType) {
		this.landType = landType;
	}

	public String getSitePlanNumber() {
		return sitePlanNumber;
	}

	public void setSitePlanNumber(String sitePlanNumber) {
		this.sitePlanNumber = sitePlanNumber;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSectorBlock() {
		return sectorBlock;
	}

	public void setSectorBlock(String sectorBlock) {
		this.sectorBlock = sectorBlock;
	}

	public String getSubSector() {
		return subSector;
	}

	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}

	public String getSectorPlotNumber() {
		return sectorPlotNumber;
	}

	public void setSectorPlotNumber(String sectorPlotNumber) {
		this.sectorPlotNumber = sectorPlotNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaBlock() {
		return areaBlock;
	}

	public void setAreaBlock(String areaBlock) {
		this.areaBlock = areaBlock;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public String getAreaPlotNumber() {
		return areaPlotNumber;
	}

	public void setAreaPlotNumber(String areaPlotNumber) {
		this.areaPlotNumber = areaPlotNumber;
	}

	public MultipartFile getOwnerCertificate() {
		return ownerCertificate;
	}

	public void setOwnerCertificate(MultipartFile ownerCertificate) {
		this.ownerCertificate = ownerCertificate;
	}

	public MultipartFile getSitePlanDocument() {
		return sitePlanDocument;
	}

	public void setSitePlanDocument(MultipartFile sitePlanDocument) {
		this.sitePlanDocument = sitePlanDocument;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public BigDecimal getFamilyBookID() {
		return familyBookID;
	}

	public void setFamilyBookID(BigDecimal familyBookID) {
		this.familyBookID = familyBookID;
	}

	public String getOwnerNationality() {
		return ownerNationality;
	}

	public void setOwnerNationality(String ownerNationality) {
		this.ownerNationality = ownerNationality;
	}

}
