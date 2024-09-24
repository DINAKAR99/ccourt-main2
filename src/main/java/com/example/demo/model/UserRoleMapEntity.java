package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tfiber_user_roles_mapping")
public class UserRoleMapEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleSequenceGenerator")
	@SequenceGenerator(name = "userRoleSequenceGenerator", allocationSize = 1, initialValue = 50000)
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "user_code")
	private Long userCode;

	@Column(name = "role_id")
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
