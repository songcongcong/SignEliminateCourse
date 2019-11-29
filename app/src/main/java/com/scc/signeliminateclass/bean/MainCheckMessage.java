package com.scc.signeliminateclass.bean;

/**
 * @author
 * @data 2019/11/13
 */
public class MainCheckMessage {
    /**
     * data : 操作成功
     * isHaveExit : true
     * code : 200
     */
    /**
     * data
     */
    private String data;
    /**
     * isHaveExit
     */
    private boolean isHaveExit;
    /**
     * code
     */
    private String code;

    /**
     * getData
     * @return string
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
     * isIsHaveExit
     * @return boolean
     */
    public boolean isIsHaveExit() {
        return isHaveExit;
    }

    /**
     * setIsHaveExit
     * @param isHaveExit isHaveExit
     */
    public void setIsHaveExit(boolean isHaveExit) {
        this.isHaveExit = isHaveExit;
    }

    /**
     * getCode
     * @return String
     */
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "MainCheckMessage{"
                + "data='" + data + '\''
                + ", isHaveExit=" + isHaveExit
                + ", code='" + code + '\''
                + '}';
    }

    /**
     * setCode
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
