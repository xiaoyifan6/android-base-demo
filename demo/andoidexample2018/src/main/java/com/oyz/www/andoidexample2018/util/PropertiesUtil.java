package com.oyz.www.andoidexample2018.util;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by ousir on 2018/1/27.
 */

public class PropertiesUtil {

    /**
     * 得到netconfig.properties配置文件中的所有配置属性
     *
     * @return Properties对象
     */
    public static Properties getNetConfigProperties(String filepath) {
        try {
            Properties props = new Properties();
            InputStream in = new FileInputStream(filepath);
            return getNetConfigProperties(in);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static Properties getNetConfigProperties( InputStream in) {
        Properties props = new Properties();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            props.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null ){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }

    /**
     * 保存
     * @param properties
     * @param targetPath
     */
    public static void save(Properties properties,String targetPath){
        OutputStream fos;
        try {
            fos = new FileOutputStream(targetPath);
            properties.store(fos, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
