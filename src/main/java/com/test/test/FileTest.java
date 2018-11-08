package com.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class FileTest {
    public static void main(String[] args) throws Exception{
        File file=new File("D:\\test\\bloodborn.jpg");
        FileInputStream fis=new FileInputStream(file);
        File fo=new File("E:\\bloodborn.jpg");
        if (fo.exists()){
            fo.createNewFile();
        }
        FileOutputStream fos=new FileOutputStream(fo);
        FileChannel channel = fis.getChannel();
        long index=0;
        while (index<channel.size()){
            if (index+1024<channel.size()){
                channel.transferTo(index,1024,fos.getChannel());
                System.out.println(index);
                index+=1024;
            }else {
             channel.transferTo(index,channel.size()-index,fos.getChannel());
             index=channel.size();
            }
        }
        fos.flush();
        fos.close();
        fis.close();
    }
}
