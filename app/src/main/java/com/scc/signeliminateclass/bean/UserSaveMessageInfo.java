package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/14
 */
public class UserSaveMessageInfo {
    /**
     * data : 操作成功
     * success : true
     * code : 200
     */

    /**
     * data
     */
    private String data;
    /**
     * success
     */
    private boolean success;
    /**
     * code
     */
    private String code;

    /**
     * getData
     * @return data
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
     * isSuccess
     * @return boolean
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * setSuccess
     * @param success success
     */
    public void setSuccess(boolean success) {
        this.success = success;
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
}
