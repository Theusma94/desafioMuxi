#include <jni.h>
#include <stdio.h>
#include <string.h>

jstring Java_com_example_matheus_appfrutas_ActivityPerfilFruta_hello(JNIEnv* env, jobject obj){

    return (*env)->NewStringUTF(env,"Hello world");

}
