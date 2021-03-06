package com.pensionrun.entity;
// Generated 2016. 4. 27 오전 12:18:51 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.pensionrun.entity.Notice;

/**
 * Admin generated by hbm2java
 */
@Entity
@Table(name = "admin", catalog = "PensionRun", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Admin implements java.io.Serializable {

	private Integer idfAdmin;
	private String name;
	private String email;
	private String password;
	private Date regDate;
	private Set<Notice> notices = new HashSet<Notice>(0);
	public Admin() {
	}

	public Admin(String name, String email, String password, Date regDate) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.regDate = regDate;
	}
	public Admin(String name, String email, String password, Date regDate, Set<Notice> notices) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.regDate = regDate;
		this.notices = notices;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idf_admin", unique = true, nullable = false)
	public Integer getIdfAdmin() {
		return this.idfAdmin;
	}

	public void setIdfAdmin(Integer idfAdmin) {
		this.idfAdmin = idfAdmin;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", unique = true, nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_date", nullable = false, length = 19)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Notice> getNotices() {
		return this.notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
}
