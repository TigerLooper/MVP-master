package com.my.mvp.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lp on 2016/3/29.
 * IO系统的学习工具类
 */
public class IOutils {
    //内容：Java 流在处理上分为字符流和字节流。字符流处理的单元为 2 个字节的 Unicode 字符，分别操作字符、字符数组或字符串，
    // 而字节流处理单元为 1 个字节，操作字节和字节数组。
    //优点：字符流（一次可以处理一个缓冲区）一次操作比字节流（一次一个字节）效率高。

    // (一)以字节为导向的 Stream------InputStream/OutputStream
    public static void writeToFile(String content) throws IOException {
        OutputStream outputStream = null;
        outputStream = new BufferedOutputStream(new FileOutputStream(new File("test.text")));//往该文件里面write数据：
        // byte[] bytes = new byte[2048];//一次性读取2048字节缓存到数组中
//        for (int i = 0; i < 2048; i++)
//            bytes[i] = (byte) i;//往字节数组里赋值0,1,2.....2047
        byte[] bytes = content.getBytes();
        outputStream.write(bytes);
        outputStream.close();
    }

    /**
     * @throws IOException 读文件
     */
    public static void readToFile() throws IOException {
        InputStream inputStream = null;
        inputStream = new BufferedInputStream(new FileInputStream(new File("test.txt")));//将文件test.text转化为bis流；
        while (inputStream.read() != -1) {
            int length = inputStream.available();
            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            // EncodingUtils.getString(buffer, "UTF-8");
        }
        //效果图：我们在ch==4的时候调用了mark方法进行标记了，
        // 当ch==8的时候我们调用了reset方法，就让stream回到了我们标记的地方重新读取
        //mark和reset方法只有Buffered类的stream有效，
        // 所以stream中都会有一个markSupported方法来判断一个stream中的mark和reset方法是否可用

    }
    // (A)ByteArrayInputStream(byte[]) 创建一个新字节数组输入流（ ByteArrayInputStream ），它从指定字节数组中读取数据（ 使用 byte 作为其缓冲区数组）
    // (B)FileInputStream -- 把一个文件作为 InputStream ，实现对文件的读取操作,操作对象是文件
    // (C)BufferedInputStream:使用缓冲区的stream,对象可以是各种流的形式
}
