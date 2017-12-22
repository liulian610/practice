package com.study.classloader;

import java.io.*;

/**
 * 文件加密工具类.
 * 使用异或运算进行加密
 *
 * Created by dingdangss on 17/5/12.
 */
public class FileUtils {

    public static void encrypt(String path){
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path + "en");
            int b;
            try {
                while ((b = fis.read()) != -1) {
                    fos.write(b ^ 2);
                }
                fos.close();
                fis.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileUtils.encrypt("/Users/dingdangss/Documents/HelloWorld.class");
    }
}
