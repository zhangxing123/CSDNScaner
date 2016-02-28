package com.example.csdnscaner.Util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.os.Environment;

public class FileService {  
    private Context context;  
    public FileService(Context context) {  
        super();  
        this.context = context;  
    }  
    /**  
     * �����ļ�  
     * @param fileName �ļ�����  
     * @param content  �ļ�����  
     * @throws IOException  
     */  
    public void save(String fileName, String content) throws IOException {  
        //��˽�з�ʽ��д���ݣ������������ļ�ֻ�ܱ���Ӧ�÷���  
        FileOutputStream out = null;  
        try {  
            out = context.openFileOutput(fileName, Context.MODE_PRIVATE);  
            out.write(content.getBytes("UTF-8"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            try {  
                out.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    } 
  
      
    /**  
     * �����ļ�  
     * @param fileName �ļ�����  
     * @param content  �ļ�����  
     * @throws IOException  
     */  
    public void saveToSDCard(String fileName, String content) throws IOException {  
        //File file = new File(new File("/mnt/sdcard"),fileName);  
        //���ǲ�ͬ�汾��sdCardĿ¼��ͬ������ϵͳ�ṩ��API��ȡSD����Ŀ¼  
        File file = new File(Environment.getExternalStorageDirectory(),fileName);  
        FileOutputStream fileOutputStream = new FileOutputStream(file);  
        fileOutputStream.write(content.getBytes());  
        fileOutputStream.close();  
    }  
    /**  
     * ��ȡ�ļ�����  
     * @param fileName �ļ�����  
     * @return �ļ�����  
     * @throws IOException  
     */  
    public String read(String fileName) throws IOException {  
        FileInputStream fileInputStream = context.openFileInput(fileName);  
        //��ÿ�ζ�ȡ������д�뵽�ڴ��У�Ȼ����ڴ��л�ȡ  
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len =0;  
        //ֻҪû���꣬���ϵĶ�ȡ  
        while((len=fileInputStream.read(buffer))!=-1){  
            outputStream.write(buffer, 0, len);  
        }  
        //�õ��ڴ���д�����������  
        byte[] data = outputStream.toByteArray();  
        return new String(data);  
    }  
}