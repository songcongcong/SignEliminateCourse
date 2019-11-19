package com.scc.signeliminateclass.eventbus;

/**
 * @author
 * @data 2019/11/11
 */
public class PrivateSuccessEventBus {
    private boolean isSuccess = false;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public PrivateSuccessEventBus(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
