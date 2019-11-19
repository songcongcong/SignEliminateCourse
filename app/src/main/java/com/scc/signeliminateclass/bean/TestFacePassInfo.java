package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/12
 */
public class TestFacePassInfo {

    /**
     * code : 200
     * privateMessage : {"nickname":"虎虎虎","id":99}
     * isPass : true
     * data : 操作成功
     */

    private int code;
    private PrivateMessageBean privateMessage;

    @Override
    public String toString() {
        return "TestFacePassInfo{" +
                "code='" + code + '\'' +
                ", privateMessage=" + privateMessage +
                ", isPass=" + isPass +
                ", data='" + data + '\'' +
                '}';
    }

    private boolean isPass;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PrivateMessageBean getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(PrivateMessageBean privateMessage) {
        this.privateMessage = privateMessage;
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

    public static class PrivateMessageBean {
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
