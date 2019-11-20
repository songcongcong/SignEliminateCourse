package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/12
 */
public class TestUserFacePassInfo {

    /**
     * memberMessage : {"membership_id":357,"name":"韩一芳","avatar":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20191119175047"}
     * code : 200
     * isPass : true
     * data : 操作成功
     */

    private MemberMessageBean memberMessage;
    private String code;
    private boolean isPass;
    private String data;

    public MemberMessageBean getMemberMessage() {
        return memberMessage;
    }

    public void setMemberMessage(MemberMessageBean memberMessage) {
        this.memberMessage = memberMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIsPass() {
        return isPass;
    }

    public void setIsPass(boolean isPass) {
        this.isPass = isPass;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static class MemberMessageBean {
        /**
         * membership_id : 357
         * name : 韩一芳
         * avatar : https://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20191119175047
         */

        private int membership_id;
        private String name;
        private String avatar;

        public int getMembership_id() {
            return membership_id;
        }

        public void setMembership_id(int membership_id) {
            this.membership_id = membership_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
