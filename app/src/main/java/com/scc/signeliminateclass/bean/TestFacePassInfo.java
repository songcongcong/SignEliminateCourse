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

    /**
     * code
     */
    private int code;
    /**
     * privateMessage
     */
    private PrivateMessageBean privateMessage;

    @Override
    public String toString() {
        return "TestFacePassInfo{"
                + "code='" + code + '\''
                + ", privateMessage=" + privateMessage
                + ", isPass=" + isPass
                + ", data='" + data + '\''
                + '}';
    }

    /**
     * isPass
     */
    private boolean isPass;
    /**
     * data
     */
    private String data;

    /**
     * getCode
     * @return int
     */
    public int getCode() {
        return code;
    }

    /**
     * setCode
     * @param code code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * getPrivateMessage
     * @return PrivateMessageBean
     */
    public PrivateMessageBean getPrivateMessage() {
        return privateMessage;
    }

    /**
     * setPrivateMessage
     * @param privateMessage privateMessage
     */
    public void setPrivateMessage(PrivateMessageBean privateMessage) {
        this.privateMessage = privateMessage;
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
     * PrivateMessageBean
     */
    public static class PrivateMessageBean {
        /**
         * nickname : 虎虎虎
         * id : 99
         */
        /**
         * nickname
         */
        private String nickname;
        /**
         * id
         */
        private int id;

        /**
         * getNickname
         * @return String
         */
        public String getNickname() {
            return nickname;
        }

        /**
         * setNickname
         * @param nickname nickname
         */
        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        /**
         * getId
         * @return return
         */
        public int getId() {
            return id;
        }

        /**
         * setId
         * @param id id
         */
        public void setId(int id) {
            this.id = id;
        }
    }
}
