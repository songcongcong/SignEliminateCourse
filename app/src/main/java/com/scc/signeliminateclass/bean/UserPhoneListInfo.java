package com.scc.signeliminateclass.bean;

import java.util.List;

/**
 * @author
 * @data 2019/11/12
 */
public class UserPhoneListInfo {
    /**
     * message : [{"membership_id":53,"name":"张三",
     * "avatar":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145"}]
     * data : 操作成功
     * code : 200
     */

    /**
     * data
     */
    private String data;
    /**
     * code
     */
    private String code;
    /**
     * message
     */
    private List<MessageBean> message;

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
     * getMessage
     * @return List<MessageBean>
     */
    public List<MessageBean> getMessage() {
        return message;
    }

    /**
     * setMessage
     * @param message message
     */
    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    /**
     * MessageBean
     */
    public static class MessageBean {
        /**
         * membership_id : 53
         * name : 张三
         * avatar : http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145
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
