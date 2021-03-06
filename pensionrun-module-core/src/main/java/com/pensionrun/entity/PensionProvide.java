package com.pensionrun.entity;
// Generated 2016. 4. 27 오전 12:18:51 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PensionProvide generated by hbm2java
 */
@Entity
@Table(name = "pension_provide", catalog = "PensionRun")
public class PensionProvide implements java.io.Serializable {

	private PensionProvideId id;
	private Pension pension;
	private Provide provide;
	private float price;
	private String description;
	private Date regDate;

	public PensionProvide() {
	}

	public PensionProvide(PensionProvideId id, Pension pension, Provide provide, float price, Date regDate) {
		this.id = id;
		this.pension = pension;
		this.provide = provide;
		this.price = price;
		this.regDate = regDate;
	}

	public PensionProvide(PensionProvideId id, Pension pension, Provide provide, float price, String description,
			Date regDate) {
		this.id = id;
		this.pension = pension;
		this.provide = provide;
		this.price = price;
		this.description = description;
		this.regDate = regDate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idfPension", column = @Column(name = "idf_pension", nullable = false)),
			@AttributeOverride(name = "idfProvide", column = @Column(name = "idf_provide", nullable = false)) })
	public PensionProvideId getId() {
		return this.id;
	}

	public void setId(PensionProvideId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_pension", nullable = false, insertable = false, updatable = false)
	public Pension getPension() {
		return this.pension;
	}

	public void setPension(Pension pension) {
		this.pension = pension;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_provide", nullable = false, insertable = false, updatable = false)
	public Provide getProvide() {
		return this.provide;
	}

	public void setProvide(Provide provide) {
		this.provide = provide;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", nullable = false, length = 19, insertable=false, updatable=false)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
