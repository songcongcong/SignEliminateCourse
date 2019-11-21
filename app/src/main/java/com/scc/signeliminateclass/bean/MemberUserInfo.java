package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/9/11
 */
public class MemberUserInfo {
    /**
     * code : 200
     * data : {"store_id":3,"birthday":"2019-09-07 00:00:00","address":"测试","city":"110100",
     * "membership_id":845,"sex":"1","avatar":"https://yizutiyu.oss-cn-beijing.
     * aliyuncs.com/qianke/touxiang/20190908135126",idNumber":"370777889889898888",
     * "emergency_name":"测试","emergency_rela":"测试","emergency_phone":"15146521354",
     * "province":"110000","phone":"15146521354","org_id":"234892mid","name":"啦啦啦",
     * "expired_state":"0","age":"11"}
     */
    /**
     * code
     */
    private String code;
    /**
     * data
     */
    private DataBean data;

    /**
     * getCode
     * @return string
     */
    public String getCode() {
        return code;
    }

    /**
     * setCode
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * getData
     * @return DataBean
     */
    public DataBean getData() {
        return data;
    }

    /**
     * setData
     * @param data data
     */
    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * DataBean
     */
    public static class DataBean {
        /**
         * store_id : 3
         * birthday : 2019-09-07 00:00:00
         * address : 测试
         * city : 110100
         * membership_id : 845
         * sex : 1
         * avatar : https://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190908135126
         * idNumber : 370777889889898888
         * emergency_name : 测试
         * emergency_rela : 测试
         * emergency_phone : 15146521354
         * province : 110000
         * phone : 15146521354
         * org_id : 234892mid
         * name : 啦啦啦
         * expired_state : 0
         * age : 11
         */
        /**
         * store_id
         */
        private int store_id;
        /**
         * birthday
         */
        private String birthday;
        /**
         * address
         */
        private String address;
        /**
         * city
         */
        private String city;
        /**
         * membership_id
         */
        private int membership_id;
        /**
         * sex
         */
        private String sex;
        /**
         * avatar
         */
        private String avatar;
        /**
         * idNumber
         */
        private String idNumber;
        /**
         * emergency_name
         */
        private String emergency_name;
        /**
         * emergency_rela
         */
        private String emergency_rela;
        /**
         * emergency_phone
         */
        private String emergency_phone;
        /**
         * province
         */
        private String province;
        /**
         * phone
         */
        private String phone;
        /**
         * org_id
         */
        private String org_id;
        /**
         * name
         */
        private String name;
        /**
         * expired_state
         */
        private String expired_state;
        /**
         * age
         */
        private String age;

        /**
         *  getStore_id
         * @return int
         */
        public int getStore_id() {
            return store_id;
        }

        @Override
        public String toString() {
            return "DataBean{"
                    + "store_id=" + store_id
                    + ", birthday='" + birthday + '\''
                    + ", address='" + address + '\''
                    + ", city='" + city + '\''
                    + ", membership_id=" + membership_id
                    + ", sex='" + sex + '\''
                    + ", avatar='" + avatar + '\''
                    + ", idNumber='" + idNumber + '\''
                    + ", emergency_name='" + emergency_name + '\''
                    + ", emergency_rela='" + emergency_rela + '\''
                    + ", emergency_phone='" + emergency_phone + '\''
                    + ", province='" + province + '\''
                    + ", phone='" + phone + '\''
                    + ", org_id='" + org_id + '\''
                    + ", name='" + name + '\''
                    + ", expired_state='" + expired_state + '\''
                    + ", age='" + age + '\''
                    + '}';
        }

        /**
         * setStore_id
         * @param store_id store_id
         */
        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        /**
         * getBirthday
         * @return String
         */
        public String getBirthday() {
            return birthday;
        }

        /**
         * setBirthday
         * @param birthday birthday
         */
        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        /**
         * getAddress
         * @return String
         */
        public String getAddress() {
            return address;
        }

        /**
         * setAddress
         * @param address address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * getCity
         * @return String
         */
        public String getCity() {
            return city;
        }

        /**
         * setCity
         * @param city city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * getMembership_id
         * @return int
         */
        public int getMembership_id() {
            return membership_id;
        }

        /**
         * setMembership_id
         * @param membership_id membership_id
         */
        public void setMembership_id(int membership_id) {
            this.membership_id = membership_id;
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
         * getIdNumber
         * @return String
         */
        public String getIdNumber() {
            return idNumber;
        }

        /**
         * setIdNumber
         * @param idNumber idNumber
         */
        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        /**
         * getEmergency_name
         * @return String
         */
        public String getEmergency_name() {
            return emergency_name;
        }

        /**
         * setEmergency_name
         * @param emergency_name emergency_name
         */
        public void setEmergency_name(String emergency_name) {
            this.emergency_name = emergency_name;
        }

        /**
         * getEmergency_rela
         * @return String
         */
        public String getEmergency_rela() {
            return emergency_rela;
        }

        /**
         * setEmergency_rela
         * @param emergency_rela emergency_rela
         */
        public void setEmergency_rela(String emergency_rela) {
            this.emergency_rela = emergency_rela;
        }

        /**
         * getEmergency_phone
         * @return String
         */
        public String getEmergency_phone() {
            return emergency_phone;
        }

        /**
         * setEmergency_phone
         * @param emergency_phone emergency_phone
         */
        public void setEmergency_phone(String emergency_phone) {
            this.emergency_phone = emergency_phone;
        }

        /**
         * getProvince
         * @return String
         */
        public String getProvince() {
            return province;
        }

        /**
         * setProvince
         * @param province province
         */
        public void setProvince(String province) {
            this.province = province;
        }

        /**
         * getPhone
         * @return String
         */
        public String getPhone() {
            return phone;
        }

        /**
         * setPhone
         * @param phone phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         * getOrg_id
         * @return String
         */
        public String getOrg_id() {
            return org_id;
        }

        /**
         * setOrg_id
         * @param org_id org_id
         */
        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        /**
         * getName
         * @return String
         */
        public String getName() {
            return name;
        }

        /**
         * setName
         * @param name name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * getExpired_state
         * @return String
         */
        public String getExpired_state() {
            return expired_state;
        }

        /**
         * setExpired_state
         * @param expired_state expired_state
         */
        public void setExpired_state(String expired_state) {
            this.expired_state = expired_state;
        }

        /**
         * getAge
         * @return String
         */
        public String getAge() {
            return age;
        }

        /**
         * setAge
         * @param age age
         */
        public void setAge(String age) {
            this.age = age;
        }
    }
}
