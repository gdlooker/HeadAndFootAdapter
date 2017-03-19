package com.example.administrator.swiperefreshlayout;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by acer-20130310 on 16-5-17.
 * 保存数据到文件的工具类
 */
public class FileUtilsMethod {


    public static String TAG="chent";
    public static File file;
    private static String result;
    private static FileOutputStream fos;
    private static ByteArrayOutputStream baos;
    private static FileInputStream fis;

    /**
     * 保存文件
     * @param context
     * @param data
     * @param fileName
     * @return
     */
    public static File saveDataToFile(Context context, String data, String fileName){

        try {
            //将数据保存于文件中
            file = new File(context.getFilesDir(),fileName);
            //将数据写入到文件中
            fos = new FileOutputStream(file);
            //写如数据到文件中
            fos.write(data.getBytes());
            //这里读取流
            result=readFromFile(context,fileName);
            Log.d(TAG,"result="+result);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 读取文件中到数据
     */

    public static String readFromFile(Context context,String fileName){


        try {
            //输入流
            file = new File(context.getFilesDir(),fileName);
            fis = new FileInputStream(file);
            //result=readByBufferStream(fis);
            result=readByteStreamFile(fis);
        }catch (Exception e){

        }
        return result;
    }


    /***
     * 读取输入流===>转化成字符串
     */
    public static String readByteStreamFile(FileInputStream fileInputStream){

        try {

            byte []buffer=new byte[1024];
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            int len=0;
            while ((len=fileInputStream.read(buffer))!=-1){
                //一边写啊 因为要显示出来啊
                baos.write(buffer,0,len);
            }

            result =baos.toString();
            return result;
        }catch (Exception e){

        }
        return result;
    }
    /**
     * 通过字节数组输出流
     */
    public static String readByteStream(FileInputStream fileInputStream){

        try{
            baos = new ByteArrayOutputStream();
            byte []buffer=new byte[1024];
            int len=0;
            while((len=fileInputStream.read(buffer))!=-1){

                //写入水缸中 从buffer
                baos.write(buffer,0,len);
            }
            result=baos.toString();
            Log.e(TAG,"readByteStream="+result);
            return result;
            
        }catch ( Exception e){

        }

        return result;
    }
    /**
     *  通过bufferReader
     */

    public static String readByBufferStream(FileInputStream fileInputStream){


        //读取输入流

        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(fileInputStream,"utf-8"));
            String line=" ";
            StringBuffer sb=new StringBuffer();
            //读取流
            while ((line=br.readLine())!=null){

                //搞到
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
