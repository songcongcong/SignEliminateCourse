package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/14
 */
public class UserOutFaceErrorListInfo {
    /**
     * message : {"employee_sign_image"
     * :"http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145","name":"曹明杰2"}
     * data : 操作成功
     * code : 200
     */

    /**
     * message
     */
    private MessageBean message;
    /**
     * data
     */
    private String data;
    /**
     * code
     */
    private String code;

    /**
     * getMessage
     * @return MessageBean
     */
    public MessageBean getMessage() {
        return message;
    }

    /**
     * setMessage
     * @param message message
     */
    public void setMessage(MessageBean message) {
        this.message = message;
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
     * MessageBean
     */
    public static class MessageBean {
        /**
         * employee_sign_image : http://yizutiyu.oss-cn-beijing.aliyuncs.com/qianke/touxiang/20190715140145
         * name : 曹明杰2
         */
        /**
         * employee_sign_image
         */
        private String employee_sign_image;
        /**
         * name
         */
        private String name;

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
    }
}
