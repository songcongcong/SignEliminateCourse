package com.scc.signeliminateclass.bean;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @data 2019/11/14
 */
public class UserOutListInfo implements Serializable {
    /**
     * message : [{"employee_sign_image":
     * "https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111522","member_sign_image":
     * "https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111536","id":11,"sign_time":
     * "2019-11-14 11:12:28"}]
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

    @Override
    public String toString() {
        return "UserOutListInfo{"
                + "data='" + data + '\''
                + ", code='" + code + '\''
                + ", message=" + message
                + '}';
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
    public static class MessageBean implements Serializable {
        /**
         * employee_sign_image : https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111522
         * member_sign_image : https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111536
         * id : 11
         * sign_time : 2019-11-14 11:12:28
         */
        /**
         * employee_sign_image
         */
        private String employee_sign_image;
        /**
         * member_sign_image
         */
        private String member_sign_image;
        /**
         * id
         */
        private int id;
        /**
         * sign_time
         */
        private String sign_time;
        /**
         * isSelector
         */
        private boolean isSelector;

        /**
         * MessageBean
         * @param in in
         */
        protected MessageBean(Parcel in) {
            employee_sign_image = in.readString();
            member_sign_image = in.readString();
            id = in.readInt();
            sign_time = in.readString();
            isSelector = in.readByte() != 0;
        }

        /**
         * isSelector
         * @return boolean
         */
        public boolean isSelector() {
            return isSelector;
        }

        /**
         * setSelector
         * @param selector selector
         */
        public void setSelector(boolean selector) {
            isSelector = selector;
        }

        /**
         * getEmployee_sign_image
         * @return String
         */
        public String getEmployee_sign_image() {
            return employee_sign_image;
        }

        /**
         * setEmployee_sign_image
         * @param employee_sign_image employee_sign_image
         */
        public void setEmployee_sign_image(String employee_sign_image) {
            this.employee_sign_image = employee_sign_image;
        }

        /**
         * getMember_sign_image
         * @return String
         */
        public String getMember_sign_image() {
            return member_sign_image;
        }

        /**
         * setMember_sign_image
         * @param member_sign_image member_sign_image
         */
        public void setMember_sign_image(String member_sign_image) {
            this.member_sign_image = member_sign_image;
        }

        /**
         * getId
         * @return int
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

        /**
         * getSign_time
         * @return String
         */
        public String getSign_time() {
            return sign_time;
        }

        /**
         * setSign_time
         * @param sign_time sign_time
         */
        public void setSign_time(String sign_time) {
            this.sign_time = sign_time;
        }

    }
}
