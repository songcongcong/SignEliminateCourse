package com.scc.signeliminateclass.eventbus;

/**
 * @author
 * @data 2019/11/8
 */
public class NetWorkStateEventbus {
    /**
     * isNetWork
     */
    private int isNetWork;

    /**
     * getIsNetWork
     * @return int
     */
    public int getIsNetWork() {
        return isNetWork;
    }

    /**
     * setIsNetWork
     * @param isNetWork isNetWork
     */
    public void setIsNetWork(int isNetWork) {
        this.isNetWork = isNetWork;
    }

    /**
     * NetWorkStateEventbus
     * @param isNetWork isNetWork
     */
    public NetWorkStateEventbus(int isNetWork) {
        this.isNetWork = isNetWork;
    }
}
