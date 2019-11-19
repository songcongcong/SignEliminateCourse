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

    private String data;
    private boolean isHaveExit;
    private String code;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isIsHaveExit() {
        return isHaveExit;
    }

    public void setIsHaveExit(boolean isHaveExit) {
        this.isHaveExit = isHaveExit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
