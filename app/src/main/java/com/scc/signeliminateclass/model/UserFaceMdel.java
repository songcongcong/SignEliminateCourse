package com.scc.signeliminateclass.model;

/**
 * 人脸查询Model封装
 */
@BaseModel
public class UserFaceMdel {
    /**
     * userId
     */
    @BaseUnique
    private String userId;
    /**
     * loginName
     */
    @BaseUnique
    private String loginName;
    /**
     * userName
     */
    private String userName;
    /**
     * userType
     */
    private String userType;
    /**
     * email
     */
    private String email;
    /**
     * phonenumber
     */
    private String phonenumber;
    /**
     * sex
     */
    private String sex;
    /**
     * avatar
     */
    private String avatar;
    /**
     * password
     */
    private String password;
    /**
     * salt
     */
    private String salt;
    /**
     * status
     */
    private String status;
    /**
     * delFlag
     */
    private String delFlag;
    /**
     * loginIp
     */
    private String loginIp;
    /**
     * loginDate
     */
    private String loginDate;
    /**
     * createBy
     */
    private String createBy;
    /**
     * createTime
     */
    private String createTime;
    /**
     * updateBy
     */
    private String updateBy;
    /**
     * updateTime
     */
    private String updateTime;
    /**
     * remark
     */
    private String remark;
    /**
     * storeNum
     */
    private String storeNum;
    /**
     * orgId
     */
    private String orgId;
    /**
     * faceUserId
     */
    @BaseUnique
    private Integer faceUserId;
    /**
     * faceUserName
     */
    private String faceUserName;
    /**
     * faceAge
     */
    private String faceAge;
    /**
     * faceSex
     */
    private String faceSex;
    /**
     * faceIdcard
     */
    private String faceIdcard;
    /**
     * facePhnoeNum
     */
    private String facePhnoeNum;
    /**
     * faceBirthday
     */
    private String faceBirthday;
    /**
     * faceId
     */
    private String faceId;

    /**
     * getFaceId
     * @return String
     */
    public String getFaceId() {
        return faceId;
    }

    /**
     * setFaceId
     * @param faceId faceId
     */
    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    /**
     * getFaceUserId
     * @return Integer
     */
    public Integer getFaceUserId() {
        return faceUserId;
    }

    /**
     * setFaceUserId
     * @param faceUserId faceUserId
     */
    public void setFaceUserId(Integer faceUserId) {
        this.faceUserId = faceUserId;
    }

    /**
     * getFaceUserName
     * @return String
     */
    public String getFaceUserName() {
        return faceUserName;
    }

    /**
     * setFaceUserName
     * @param faceUserName faceUserName
     */
    public void setFaceUserName(String faceUserName) {
        this.faceUserName = faceUserName;
    }

    /**
     * getFaceAge
     * @return String
     */
    public String getFaceAge() {
        return faceAge;
    }

    /**
     * setFaceAge
     * @param faceAge faceAge
     */
    public void setFaceAge(String faceAge) {
        this.faceAge = faceAge;
    }

    /**
     * getFaceSex
     * @return  String
     */
    public String getFaceSex() {
        return faceSex;
    }

    /**
     * setFaceSex
     * @param faceSex faceSex
     */
    public void setFaceSex(String faceSex) {
        this.faceSex = faceSex;
    }

    /**
     * getFaceIdcard
     * @return String
     */
    public String getFaceIdcard() {
        return faceIdcard;
    }

    /**
     * setFaceIdcard
     * @param faceIdcard faceIdcard
     */
    public void setFaceIdcard(String faceIdcard) {
        this.faceIdcard = faceIdcard;
    }

    /**
     * getFacePhnoeNum
     * @return String
     */
    public String getFacePhnoeNum() {
        return facePhnoeNum;
    }

    /**
     * setFacePhnoeNum
     * @param facePhnoeNum facePhnoeNum
     */
    public void setFacePhnoeNum(String facePhnoeNum) {
        this.facePhnoeNum = facePhnoeNum;
    }

    /**
     * getFaceBirthday
     * @return String
     */
    public String getFaceBirthday() {
        return faceBirthday;
    }

    /**
     * setFaceBirthday
     * @param faceBirthday faceBirthday
     */
    public void setFaceBirthday(String faceBirthday) {
        this.faceBirthday = faceBirthday;
    }

    /**
     * getUserId
     * @return String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * setUserId
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * getLoginName
     * @return String
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * setLoginName
     * @param loginName loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * getUserName
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setUserName
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getUserType
     * @return String
     */
    public String getUserType() {
        return userType;
    }

    /**
     * setUserType
     * @param userType userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * getEmail
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getPhonenumber
     * @return String
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * setPhonenumber
     * @param phonenumber phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * getSex
     * @return String
     */
    public String getSex() {
        return sex;
    }

    /**
     * setSex
     * @param sex sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * getAvatar
     * @return String
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * setAvatar
     * @param avatar avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * getPassword
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getSalt
     * @return String
     */
    public String getSalt() {
        return salt;
    }

    /**
     * setSalt
     * @param salt salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * getStatus
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * setStatus
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * getDelFlag
     * @return String
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * setDelFlag
     * @param delFlag delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * getLoginIp
     * @return String
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * setLoginIp
     * @param loginIp loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * getLoginDate
     * @return String
     */
    public String getLoginDate() {
        return loginDate;
    }

    /**
     * setLoginDate
     * @param loginDate loginDate
     */
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * getCreateBy
     * @return String
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * setCreateBy
     * @param createBy createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * getCreateTime
     * @return String
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * setCreateTime
     * @param createTime createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * getUpdateBy
     * @return String
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * setUpdateBy
     * @param updateBy updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * getUpdateTime
     * @return String
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * setUpdateTime
     * @param updateTime updateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * getRemark
     * @return String
     */
    public String getRemark() {
        return remark;
    }

    /**
     * setRemark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * getStoreNum
     * @return String
     */
    public String getStoreNum() {
        return storeNum;
    }

    /**
     * setStoreNum
     * @param storeNum storeNum
     */
    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    /**
     * getOrgId
     * @return String
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * setOrgId
     * @param orgId orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        String sex = "其他";
        if ("0".equals(getSex())) {
            sex = "男";
        } else if ("1".equals(getSex())) {
            sex = "女";
        }
        return "姓名" + getUserName() + "性别 " + getSex() + " 电话号码 " + getPhonenumber();
    }

    /**
     * toRead
     * @return String
     */
    public String toRead() {
        String sex = "其他";
        if ("0".equals(getFaceAge())) {
            sex = "男";
        } else if ("1".equals(getFaceAge())) {
            sex = "女";
        }
        return "姓名" + getFaceUserName() + "性别 " + getFaceAge() + " 电话号码 "
                + getFacePhnoeNum() + " 生日" + getFaceBirthday() + "身份证号"
                + "" + getFaceIdcard() + "";
    }
}
