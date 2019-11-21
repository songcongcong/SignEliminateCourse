package com.scc.signeliminateclass.bean;

/**
 * PictureInfo
 */
public class PictureInfo {

    /**
     * image : http://yizutiyu.oss-cn-beijing.aliyuncs.com/xunjian/20190828142318
     * success : true
     */
    /**
     * image
     */
    private String image;
    /**
     * success
     */
    private boolean success;

    /**
     * getImage
     * @return String
     */
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "PictureInfo{"
                + "image='" + image + '\''
                + ", success=" + success
                + '}';
    }

    /**
     * setImage
     * @param image image
     */
    public void setImage(String image) {
        this.image = image;
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
}
