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

    private String data;
    private boolean success;
    private String code;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
