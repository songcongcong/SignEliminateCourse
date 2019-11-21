package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/12
 */
public class TestUserFacePassInfo {

    /**
     * memberMessage : {"membership_id":357,"name":"韩一芳",
     * "avatar":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20191119175047"}
     * code : 200
     * isPass : true
     * data : 操作成功
     */

    /**
     * memberMessage
     */
    private MemberMessageBean memberMessage;
    /**
     * code
     */
    private String code;
    /**
     * isPass
     */
    private boolean isPass;
    /**
     * data
     */
    private String data;

    /**
     * getMemberMessage
     * @return MemberMessageBean
     */
    public MemberMessageBean getMemberMessage() {
        return memberMessage;
    }

    /**
     * setMemberMessage
     * @param memberMessage memberMessage
     */
    public void setMemberMessage(MemberMessageBean memberMessage) {
        this.memberMessage = memberMessage;
    }

    /**
     * getCode
     * @return String
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
     * isIsPass
     * @return boolean
     */
    public boolean isIsPass() {
        return isPass;
    }

    /**
     * setIsPass
     * @param isPass isPass
     */
    public void setIsPass(boolean isPass) {
        this.isPass = isPass;
    }

    /**
     * getData
     * @return String
     */
    public String getData() {
        return data;
    }

    /**
     * setData
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * MemberMessageBean
     */
    public static class MemberMessageBean {
        /**
         * membership_id : 357
         * name : 韩一芳
         * avatar : https://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20191119175047
         */
        /**
         * membership_id
         */
        private int membership_id;
        /**
         * name
         */
        private String name;
        /**
         * avatar
         */
        private String avatar;

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
    }
}
