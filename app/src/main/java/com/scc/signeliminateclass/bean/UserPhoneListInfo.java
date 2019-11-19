package com.scc.signeliminateclass.bean;

import java.util.List;

/**
 * @author
 * @data 2019/11/12
 */
public class UserPhoneListInfo {
    /**
     * message : [{"membership_id":53,"name":"张三","avatar":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145"}]
     * data : 操作成功
     * code : 200
     */

    private String data;
    private String code;
    private List<MessageBean> message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * membership_id : 53
         * name : 张三
         * avatar : http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145
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
