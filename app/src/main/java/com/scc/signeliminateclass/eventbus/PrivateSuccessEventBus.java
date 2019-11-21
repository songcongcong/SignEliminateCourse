package com.scc.signeliminateclass.eventbus;

/**
 * @author
 * @data 2019/11/11
 */
public class PrivateSuccessEventBus {
    /**
     * isSuccess
     */
    private boolean isSuccess = false;

    /**
     * isSuccess
     * @return boolean
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * setSuccess
     * @param success success
     */
    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    /**
     * PrivateSuccessEventBus
     * @param isSuccess isSuccess
     */
    public PrivateSuccessEventBus(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
