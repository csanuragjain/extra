 #include <iostream>  
 #include <windows.h>  
 #include <fstream> 
 #include <cstdlib> 
 #include <jni.h>  
 #include "wallpaperchanger.h"  

 
 JNIEXPORT jint JNICALL Java_com_cooltrickshome_DesktopMovieWallpaper_changeWallpaper
  (JNIEnv *env, jclass, jstring wallpaper){  
  const char *wallpaper_file = env->GetStringUTFChars(wallpaper, JNI_FALSE);
  int return_value = SystemParametersInfo(SPI_SETDESKWALLPAPER, 0, (void *)wallpaper_file, SPIF_UPDATEINIFILE | SPIF_SENDCHANGE);
  env->ReleaseStringUTFChars(wallpaper, wallpaper_file);
 }  
