package com.pensionrun.entity;
// Generated 2016. 4. 29 오후 9:11:17 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PensionRecommendationId generated by hbm2java
 */
@Embeddable
public class RecommendationPensionId implements java.io.Serializable {

	private int idfRecommendation;
	private int idfPension;

	public RecommendationPensionId() {
	}

	public RecommendationPensionId(int idfRecommendation, int idfPension) {
		this.idfRecommendation = idfRecommendation;
		this.idfPension = idfPension;
	}

	@Column(name = "idf_recommendation", nullable = false)
	public int getIdfRecommendation() {
		return this.idfRecommendation;
	}

	public void setIdfRecommendation(int idfRecommendation) {
		this.idfRecommendation = idfRecommendation;
	}

	@Column(name = "idf_pension", nullable = false)
	public int getIdfPension() {
		return this.idfPension;
	}

	public void setIdfPension(int idfPension) {
		this.idfPension = idfPension;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RecommendationPensionId))
			return false;
		RecommendationPensionId castOther = (RecommendationPensionId) other;

		return (this.getIdfRecommendation() == castOther.getIdfRecommendation())
				&& (this.getIdfPension() == castOther.getIdfPension());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdfRecommendation();
		result = 37 * result + this.getIdfPension();
		return result;
	}

}
