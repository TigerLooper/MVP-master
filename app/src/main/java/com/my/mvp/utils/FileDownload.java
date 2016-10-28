package com.my.mvp.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lp on 2016/3/30.
 * 下载Thread类
 */
public class FileDownload extends Thread {

    private static final String TGA = FileDownload.class.getSimpleName();
    /**
     * 当前下载是否完成
     */
    private boolean isCompleted = false;
    /**
     * 当前下载文件长度
     */
    private int downloadLength = 0;
    /**
     * 文件保存路径
     */
    private File file;
    /**
     * 文件下载路径
     */
    private URL downloadUrl;
    /**
     * 当前下载线程ID
     */
    private int threadId;
    /**
     * 线程下载数据长度
     */
    private int blockSize;

    public FileDownload(URL downloadUrl, File file, int blockSize,
                        int threadId) {
        this.file = file;
        this.downloadUrl = downloadUrl;
        this.threadId = threadId;
        this.blockSize = blockSize;
    }

    @Override
    public void run() {
        BufferedInputStream bis = null;
        RandomAccessFile raf = null;
        try {
            URLConnection conn = downloadUrl.openConnection();
            conn.setAllowUserInteraction(true);
            int startPos = blockSize * (threadId - 1);//开始位置
            int endPos = blockSize * threadId - 1;//结束位置
            conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
            byte[] buffer = new byte[1024];
            bis = new BufferedInputStream(conn.getInputStream());//获取流
            // bis = new BufferedInputStream(new FileInputStream(new File("test.java")));
            raf = new RandomAccessFile(file, "rwd");
            raf.seek(startPos);
            int len;
            while ((len = bis.read(buffer, 0, 1024)) != -1) {//1024代表1M
                raf.write(buffer, 0, len);
                downloadLength += len;
            }
            isCompleted = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 线程文件是否下载完毕
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * 线程下载文件长度
     */
    public int getDownloadLength() {
        return downloadLength;
    }
}
