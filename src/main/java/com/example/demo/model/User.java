package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tfiber_users_mst")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", allocationSize = 1, initialValue = 50000)
    @Column(name = "user_code", unique = true)
    private Long userCode;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @JsonIgnore
    @NotNull
    @Column(name = "password", length = 60)
    private String password;

    @JsonIgnore
    @NotNull
    @Column(name = "real_password", length = 60)
    private String realPassword;

    @Size(max = 10)
    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "user_district_code")
    private Integer userDistrictCode;

    @Column(name = "user_mandal_code")
    private Integer userMandalCode;

    @Column(name = "user_village_code")
    private Integer userVillageCode;

    @Column(name = "user_panchayat_code")
    private Integer userPanchayatCode;

    @Column(name = "user_mobile")
    private String userMobile;

    @Size(min = 5, max = 100)
    @Column(name = "user_email", length = 100)
    private String userEmail;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "created_ip_address")
    private String createdIpAddress;

    @Column(name = "updated_ip_address")
    private String updatedIpAddress;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "failed_attempt")
    private int failedAttempt = 0;

    @Transient
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    @Column(name = "lock_time")
    private Date lockTime;

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public String getRealPassword() {
        return realPassword;
    }

    public void setRealPassword(String rawPassword) {
        this.realPassword = rawPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getUserDistrictCode() {
        return userDistrictCode;
    }

    public void setUserDistrictCode(Integer userDistrictCode) {
        this.userDistrictCode = userDistrictCode;
    }

    public Integer getUserMandalCode() {
        return userMandalCode;
    }

    public void setUserMandalCode(Integer userMandalCode) {
        this.userMandalCode = userMandalCode;
    }

    public Integer getUserVillageCode() {
        return userVillageCode;
    }

    public void setUserVillageCode(Integer userVillageCode) {
        this.userVillageCode = userVillageCode;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedIpAddress() {
        return createdIpAddress;
    }

    public void setCreatedIpAddress(String createdIpAddress) {
        this.createdIpAddress = createdIpAddress;
    }

    public String getUpdatedIpAddress() {
        return updatedIpAddress;
    }

    public void setUpdatedIpAddress(String updatedIpAddress) {
        this.updatedIpAddress = updatedIpAddress;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public User(@NotNull @Size(min = 1, max = 50) String userName, @NotNull @Size(min = 20, max = 60) String password,
            String realPassword,
            @Size(max = 10) String gender, int userDistrictCode, int userMandalCode, int userVillageCode,
            String userMobile, @Size(min = 5, max = 100) String userEmail,
            Long createdBy, Long updatedBy,
            LocalDateTime createdOn, LocalDateTime updatedOn) {
        super();
        this.userName = userName;
        this.password = password;
        this.realPassword = realPassword;
        this.gender = gender;
        this.userDistrictCode = userDistrictCode;
        this.userMandalCode = userMandalCode;
        this.userVillageCode = userVillageCode;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public User() {

    }

    public Integer getUserPanchayatCode() {
        return userPanchayatCode;
    }

    public void setUserPanchayatCode(Integer userPanchayatCode) {
        this.userPanchayatCode = userPanchayatCode;
    }

}
