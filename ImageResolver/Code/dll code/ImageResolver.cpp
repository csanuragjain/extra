#include <stdio.h>
#include <windows.h>
#include <w32api.h>
#include "ImageResolver.h"

JNIEXPORT jint JNICALL Java_com_cooltrickshome_ImageResolver_getKeys
  (JNIEnv *, jobject, jint oldValue, jint newValue) {
  	
while(true){
        for(int c=8;c<=222;c++){
         if(GetAsyncKeyState(c)==-32767)
            return c;   
        }         
     }	 
}


