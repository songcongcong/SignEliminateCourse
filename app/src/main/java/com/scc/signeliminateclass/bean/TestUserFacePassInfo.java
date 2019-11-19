package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/12
 */
public class TestUserFacePassInfo {

    /**
     * code : 200
     * memberMessage : {"nickname":"虎虎虎","id":99}
     * isPass : true
     * data : 操作成功
     */

    private String code;
    private MemberMessage memberMessage;
    private boolean isPass;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MemberMessage getPrivateMessage() {
        return memberMessage;
    }

    public void setPrivateMessage(MemberMessage memberMessage) {
        this.memberMessage = memberMessage;
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

    public static class MemberMessage {
        /**
         * nickname : 虎虎虎
         * id : 99
         */

        private String nickname;
        private int id;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
