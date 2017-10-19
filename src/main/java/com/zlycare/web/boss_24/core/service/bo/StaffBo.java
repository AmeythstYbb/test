package com.zlycare.web.boss_24.core.service.bo;/**
 * Created by zhanglw on 2017/6/5.
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Author : zhanglianwei
 * Create : 2017/6/5 18:43
 * Update : 2017/6/5 18:43
 * Descriptions :
 */
public class StaffBo implements Serializable {


    private static final long serialVersionUID = -6306970621511077725L;


    private Integer id;

    private Integer areaId;

    private Integer businessId;

    private String jobNumber;

    private String userName;

    private Integer gender;

    private Integer deptId;
    /**
     * 二级部门id
     */
    private Integer deptChildrenId;
    /**
     * 一级部门对象
     */
    private DeptBo deptBo;
    /**
     * 二级部门对象
     */
    private DeptBo deptChildrenBo;

    private String staffLevel;

    private Integer status;

    private String kind;

    private Date birthday;

    private Date entryDate;

    private Date postDate;

    private Integer userId;

    private String photoUrl;

    private Integer maritalStatus;

    private String mobile;

    private String email;

    private Integer age;

    private String nation;

    private String politicalStatus;

    private Integer certificateType;

    private String certificateNo;

    private String householdType;

    private String household;

    private String householdAddress;

    private String address;

    private Integer education;

    private Date graduationDate;

    private String graduatedSchool;

    private String profession;

    private String workAddress;

    private String position;

    private Date departureDate;

    private Integer contractType;

    private Date contractStartDate;

    private Date contractEndDate;

    private Integer companyAge;

    private Integer workAge;

    private Integer annualLeaveDays;

    private Date fiveInsuranceEndDate;

    private Date housingFundEndDate;

    private Integer recruitChannel;

    private String introducer;

    private String debitCardNo;

    private String spouse;

    private Date spouseBirthday;

    private String spouseMobile;

    private String childrenInfo;

    private String remark;
    private String remark2;

    private Date createDate;

    private String loginIp;

    private Date loginDate;

    /**
     * 子女姓名
     */
    private String childrenName;
    /**
     * 子女生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date childrenBirthday;
    /**
     * 子女性别
     */
    private Integer childrenGender;

    public String getChildrenName() {
        return childrenName;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public Date getChildrenBirthday() {
        return childrenBirthday;
    }

    public void setChildrenBirthday(Date childrenBirthday) {
        this.childrenBirthday = childrenBirthday;
    }

    public Integer getChildrenGender() {
        return childrenGender;
    }

    public void setChildrenGender(Integer childrenGender) {
        this.childrenGender = childrenGender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getStaffLevel() {
        return staffLevel;
    }

    public void setStaffLevel(String staffLevel) {
        this.staffLevel = staffLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getHouseholdAddress() {
        return householdAddress;
    }

    public void setHouseholdAddress(String householdAddress) {
        this.householdAddress = householdAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Integer getCompanyAge() {
        return companyAge;
    }

    public void setCompanyAge(Integer companyAge) {
        this.companyAge = companyAge;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    public Integer getAnnualLeaveDays() {
        return annualLeaveDays;
    }

    public void setAnnualLeaveDays(Integer annualLeaveDays) {
        this.annualLeaveDays = annualLeaveDays;
    }

    public Date getFiveInsuranceEndDate() {
        return fiveInsuranceEndDate;
    }

    public void setFiveInsuranceEndDate(Date fiveInsuranceEndDate) {
        this.fiveInsuranceEndDate = fiveInsuranceEndDate;
    }

    public Date getHousingFundEndDate() {
        return housingFundEndDate;
    }

    public void setHousingFundEndDate(Date housingFundEndDate) {
        this.housingFundEndDate = housingFundEndDate;
    }

    public Integer getRecruitChannel() {
        return recruitChannel;
    }

    public void setRecruitChannel(Integer recruitChannel) {
        this.recruitChannel = recruitChannel;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getDebitCardNo() {
        return debitCardNo;
    }

    public void setDebitCardNo(String debitCardNo) {
        this.debitCardNo = debitCardNo;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public Date getSpouseBirthday() {
        return spouseBirthday;
    }

    public void setSpouseBirthday(Date spouseBirthday) {
        this.spouseBirthday = spouseBirthday;
    }

    public String getSpouseMobile() {
        return spouseMobile;
    }

    public void setSpouseMobile(String spouseMobile) {
        this.spouseMobile = spouseMobile;
    }

    public String getChildrenInfo() {
        return childrenInfo;
    }

    public void setChildrenInfo(String childrenInfo) {
        this.childrenInfo = childrenInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getDeptChildrenId() {
        return deptChildrenId;
    }

    public void setDeptChildrenId(Integer deptChildrenId) {
        this.deptChildrenId = deptChildrenId;
    }

    public DeptBo getDeptChildrenBo() {
        return deptChildrenBo;
    }

    public void setDeptChildrenBo(DeptBo deptChildrenBo) {
        this.deptChildrenBo = deptChildrenBo;
    }

    public DeptBo getDeptBo() {
        return deptBo;
    }

    public void setDeptBo(DeptBo deptBo) {
        this.deptBo = deptBo;
    }
}

