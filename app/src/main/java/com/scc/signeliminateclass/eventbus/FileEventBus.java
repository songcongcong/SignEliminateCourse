package com.scc.signeliminateclass.eventbus;

import java.io.ByteArrayOutputStream;

/**
 * 检测人脸的数据信息
 */
public class FileEventBus {
    /**
     * ByteArrayOutputStream
     */
    private ByteArrayOutputStream file;

    /**
     * FileEventBus
     * @param file file
     */
    public FileEventBus(ByteArrayOutputStream file) {
        this.file = file;
    }

    /**
     * getFile
     * @return ByteArrayOutputStream
     */
    public ByteArrayOutputStream getFile() {
        return file;
    }

    /**
     * setFile
     * @param file file
     */
    public void setFile(ByteArrayOutputStream file) {
        this.file = file;
    }
}
