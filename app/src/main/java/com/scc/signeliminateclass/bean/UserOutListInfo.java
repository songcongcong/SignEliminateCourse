package com.scc.signeliminateclass.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @data 2019/11/14
 */
public class UserOutListInfo implements Serializable {
    /**
     * message : [{"employee_sign_image":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111522","member_sign_image":"https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111536","id":11,"sign_time":"2019-11-14 11:12:28"}]
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

    @Override
    public String toString() {
        return "UserOutListInfo{" +
                "data='" + data + '\'' +
                ", code='" + code + '\'' +
                ", message=" + message +
                '}';
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }


    public static class MessageBean implements Serializable {
        /**
         * employee_sign_image : https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111522
         * member_sign_image : https://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20191114111536
         * id : 11
         * sign_time : 2019-11-14 11:12:28
         */

        private String employee_sign_image;
        private String member_sign_image;
        private int id;
        private String sign_time;
        private boolean isSelector;

        protected MessageBean(Parcel in) {
            employee_sign_image = in.readString();
            member_sign_image = in.readString();
            id = in.readInt();
            sign_time = in.readString();
            isSelector = in.readByte() != 0;
        }

        public boolean isSelector() {
            return isSelector;
        }

        public void setSelector(boolean selector) {
            isSelector = selector;
        }

        public String getEmployee_sign_image() {
            return employee_sign_image;
        }

        public void setEmployee_sign_image(String employee_sign_image) {
            this.employee_sign_image = employee_sign_image;
        }

        public String getMember_sign_image() {
            return member_sign_image;
        }

        public void setMember_sign_image(String member_sign_image) {
            this.member_sign_image = member_sign_image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSign_time() {
            return sign_time;
        }

        public void setSign_time(String sign_time) {
            this.sign_time = sign_time;
        }

    }
}
