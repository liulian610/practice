package com.study.practice.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dingdangss on 17/5/26.
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("/Users/dingdangss/Documents/HelloWorld.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while(bytesRead != -1){
            System.out.println("Read: " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
