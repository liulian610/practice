package com.study.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by dingdangss on 17/5/12.
 */
public class DecryptClassLoader extends ClassLoader{

    private String myLibPath;

    public DecryptClassLoader(String path){
        myLibPath = path;
    }

    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(myLibPath, fileName);
        try{
            FileInputStream is = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            try{
                while((len = is.read()) != -1){
                    bos.write(len ^ 2);
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();

            return defineClass(name, data, 0, data.length);
        }catch(IOException e){
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name + ".classen";
        }else{
            return name.substring(index) + ".classen";
        }
    }
}
