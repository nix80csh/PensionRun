package com.pensionrun.entity;
// Generated 2016. 5. 19 오후 11:24:48 by Hibernate Tools 4.3.1.Final

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
 * AccountPension generated by hbm2java
 */
@Entity
@Table(name = "account_pension", catalog = "PensionRun")
public class AccountPension implements java.io.Serializable {

	private AccountPensionId id;
	private Account account;
	private Pension pension;
	private Date regDate;

	public AccountPension() {
	}

	public AccountPension(AccountPensionId id, Account account, Pension pension, Date regDate) {
		this.id = id;
		this.account = account;
		this.pension = pension;
		this.regDate = regDate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idfAccount", column = @Column(name = "idf_account", nullable = false)),
			@AttributeOverride(name = "idfPension", column = @Column(name = "idf_pension", nullable = false)) })
	public AccountPensionId getId() {
		return this.id;
	}

	public void setId(AccountPensionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_account", nullable = false, insertable = false, updatable = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idf_pension", nullable = false, insertable = false, updatable = false)
	public Pension getPension() {
		return this.pension;
	}

	public void setPension(Pension pension) {
		this.pension = pension;
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
