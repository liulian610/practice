package com.study.practice.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dingdangss on 17/5/11.
 */
public class MainTest extends ClassLoader {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ClassReader cr = new ClassReader(HelloWorld.class.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        MyVisitor myv = new MyVisitor(cw);
        cr.accept(myv, ClassReader.SKIP_DEBUG);
        byte[] code = cw.toByteArray();

        MainTest loader = new MainTest();
        Class<?> appClass = loader.defineClass(null, code, 0, code.length);
        appClass.getMethods()[0].invoke(appClass.newInstance(), new Object[]{});

        FileOutputStream f = new FileOutputStream(new File("/Users/dingdangss/Documents/NewHelloWorld.class"));
        f.write(code);
        f.close();
    }
}
