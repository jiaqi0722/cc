package com.cc.api.common.util;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: FileEntry
 */
public class FileEntry {
    private String fileName;
    private String filePath;

    public FileEntry() {
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileEntry{fileName='" + this.fileName + '\'' + ", filePath='" + this.filePath + '\'' + '}';
    }
}
