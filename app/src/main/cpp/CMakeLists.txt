# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.22.1)

add_library(
        native-lib
        SHARED
        native-lib.cpp
)

add_library(
        app-glue
        STATIC
        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c )

find_library(
        native-lib
        log
)



target_link_libraries(
        native-lib
        app-glue
        ${log.lib}
)