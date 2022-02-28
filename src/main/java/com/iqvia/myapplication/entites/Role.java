package com.iqvia.myapplication.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@DynamicInsert
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "roles")
	@ColumnDefault(value = "'ROLE_DUMMY'")	
	private String roles;
//	
	// @OneToOne(mappedBy = "roles")
//	@PrimaryKeyJoinColumn
	@JsonIgnoreProperties("roles")
//	@MapsId
	@JoinColumn(name = "user_name")
	@ManyToOne
	private User user;

}
