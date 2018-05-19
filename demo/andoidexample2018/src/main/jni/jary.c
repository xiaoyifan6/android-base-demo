#include "com_oyz_www_andoidexample2018_demo_c_JniTest.h"

JNIEXPORT jstring JNICALL Java_com_oyz_www_andoidexample2018_demo_c_JniTest_getString(
        JNIEnv *env,jobject jobject1
){
    return (*env)->NewStringUTF(env,"hello jni!");
}