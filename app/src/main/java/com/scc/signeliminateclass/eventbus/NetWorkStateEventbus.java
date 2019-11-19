package com.scc.signeliminateclass.eventbus;

/**
 * @author
 * @data 2019/11/8
 */
public class NetWorkStateEventbus {
    private int isNetWork;

    public int getIsNetWork() {
        return isNetWork;
    }

    public void setIsNetWork(int isNetWork) {
        this.isNetWork = isNetWork;
    }

    public NetWorkStateEventbus(int isNetWork) {
        this.isNetWork = isNetWork;
    }
}
