package com.oyz.www.andoidexample2018.demo.c;

/**
 * Created by ousir on 2018/1/30.
 */

public class JniTest {
    static {
        System.loadLibrary("jary");
    }
    public native String getString();
}
