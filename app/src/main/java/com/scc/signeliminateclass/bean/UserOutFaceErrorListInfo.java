package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/14
 */
public class UserOutFaceErrorListInfo {
    /**
     * message : {"employee_sign_image":"http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145","name":"曹明杰2"}
     * data : 操作成功
     * code : 200
     */

    private MessageBean message;
    private String data;
    private String code;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

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

    public static class MessageBean {
        /**
         * employee_sign_image : http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145
         * name : 曹明杰2
         */

        private String employee_sign_image;
        private String name;

        public String getEmployee_sign_image() {
            return employee_sign_image;
        }

        public void setEmployee_sign_image(String employee_sign_image) {
            this.employee_sign_image = employee_sign_image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
