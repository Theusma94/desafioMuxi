#include <jni.h>
#include <stdio.h>
#include <string.h>



JNIEXPORT jfloat JNICALL Java_com_example_matheus_appfrutas_NativeCalc_converteDolar(JNIEnv* env, jobject obj, jfloat preco)
{
        return preco * 3.5;
}
