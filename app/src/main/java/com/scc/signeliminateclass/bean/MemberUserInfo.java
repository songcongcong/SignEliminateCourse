package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/9/11
 */
public class MemberUserInfo {
    /**
     * code : 200
     * data : {"store_id":3,"birthday":"2019-09-07 00:00:00","address":"测试","city":"110100","membership_id":845,"sex":"1","avatar":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190908135126","idNumber":"370777889889898888","emergency_name":"测试","emergency_rela":"测试","emergency_phone":"15146521354","province":"110000","phone":"15146521354","org_id":"234892mid","name":"啦啦啦","expired_state":"0","age":"11"}
     */

    private String code;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

        private int store_id;
        private String birthday;
        private String address;
        private String city;
        private int membership_id;
        private String sex;
        private String avatar;
        private String idNumber;
        private String emergency_name;
        private String emergency_rela;
        private String emergency_phone;
        private String province;
        private String phone;
        private String org_id;
        private String name;
        private String expired_state;
        private String age;

        public int getStore_id() {
            return store_id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "store_id=" + store_id +
                    ", birthday='" + birthday + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", membership_id=" + membership_id +
                    ", sex='" + sex + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", idNumber='" + idNumber + '\'' +
                    ", emergency_name='" + emergency_name + '\'' +
                    ", emergency_rela='" + emergency_rela + '\'' +
                    ", emergency_phone='" + emergency_phone + '\'' +
                    ", province='" + province + '\'' +
                    ", phone='" + phone + '\'' +
                    ", org_id='" + org_id + '\'' +
                    ", name='" + name + '\'' +
                    ", expired_state='" + expired_state + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getMembership_id() {
            return membership_id;
        }

        public void setMembership_id(int membership_id) {
            this.membership_id = membership_id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getEmergency_name() {
            return emergency_name;
        }

        public void setEmergency_name(String emergency_name) {
            this.emergency_name = emergency_name;
        }

        public String getEmergency_rela() {
            return emergency_rela;
        }

        public void setEmergency_rela(String emergency_rela) {
            this.emergency_rela = emergency_rela;
        }

        public String getEmergency_phone() {
            return emergency_phone;
        }

        public void setEmergency_phone(String emergency_phone) {
            this.emergency_phone = emergency_phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExpired_state() {
            return expired_state;
        }

        public void setExpired_state(String expired_state) {
            this.expired_state = expired_state;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
