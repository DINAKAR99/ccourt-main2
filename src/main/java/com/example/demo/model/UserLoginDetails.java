package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tfiber_user_login_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserLoginDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userLoginDetailsSequenceGenerator")
	@SequenceGenerator(name = "userLoginDetailsSequenceGenerator", allocationSize = 1, initialValue = 1)
	// @Column(name="user_code", unique = true)
	private Long id;

	// @NotNull
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_code")
	private Long userCode;

	@Column(name = "session_id")
	private String sessionId;

	@Column(name = "first_session_id")
	private String firstSessionId;

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
	}

	@Column(name = "login_time")
	private LocalDateTime loginTime = LocalDateTime.now();

	@Column(name = "logout_time")
	private LocalDateTime logoutTime;

	@Column(name = "login_ip_address")
	private String loginIPAddress;

	@Column(name = "is_login")
	private boolean isLogin = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoginIPAddress() {
		return loginIPAddress;
	}

	public void setLoginIPAddress(String loginIPAddress) {
		this.loginIPAddress = loginIPAddress;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	@Override
	public String toString() {
		return "UserLoginDetails [id=" + id + ", userId=" + userId + ", loginTime=" + loginTime + ", logoutTime="
				+ logoutTime + ", loginIPAddress=" + loginIPAddress + ", isLogin=" + isLogin + "]";
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getFirstSessionId() {
		return firstSessionId;
	}

	public void setFirstSessionId(String firstSessionId) {
		this.firstSessionId = firstSessionId;
	}

}
