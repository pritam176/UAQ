package com.uaq.vo;

/**
 * 
 * @author G Sekhar
 * 
 *         VO class for status lookups in my request
 */
public class StatusLookupsOutputVO {

	private String serviceNameEn;
	private String serviceNameAr;

	private String statusAr;
	private String statusEn;

	public String getStatusAr() {
		return statusAr;
	}

	public void setStatusAr(String statusAr) {
		this.statusAr = statusAr;
	}

	public String getStatusEn() {
		return statusEn;
	}

	public void setStatusEn(String statusEn) {
		this.statusEn = statusEn;
	}

	public String getServiceNameEn() {
		return serviceNameEn;
	}

	public void setServiceNameEn(String serviceNameEn) {
		this.serviceNameEn = serviceNameEn;
	}

	public String getServiceNameAr() {
		return serviceNameAr;
	}

	public void setServiceNameAr(String serviceNameAr) {
		this.serviceNameAr = serviceNameAr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceNameAr == null) ? 0 : serviceNameAr.hashCode());
		result = prime * result + ((serviceNameEn == null) ? 0 : serviceNameEn.hashCode());
		result = prime * result + ((statusAr == null) ? 0 : statusAr.hashCode());
		result = prime * result + ((statusEn == null) ? 0 : statusEn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusLookupsOutputVO other = (StatusLookupsOutputVO) obj;
		if (serviceNameAr == null) {
			if (other.serviceNameAr != null)
				return false;
		} else if (!serviceNameAr.equals(other.serviceNameAr))
			return false;
		if (serviceNameEn == null) {
			if (other.serviceNameEn != null)
				return false;
		} else if (!serviceNameEn.equals(other.serviceNameEn))
			return false;
		if (statusAr == null) {
			if (other.statusAr != null)
				return false;
		} else if (!statusAr.equals(other.statusAr))
			return false;
		if (statusEn == null) {
			if (other.statusEn != null)
				return false;
		} else if (!statusEn.equals(other.statusEn))
			return false;
		return true;
	}

}
