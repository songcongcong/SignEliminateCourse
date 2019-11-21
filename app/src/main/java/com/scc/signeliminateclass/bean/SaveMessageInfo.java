package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/13
 */
public class SaveMessageInfo {

    /**
     * code : 200
     * data : 操作成功
     */

    /**
     * code
     */
    private int code;
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
}
