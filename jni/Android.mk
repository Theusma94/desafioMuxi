LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := convertepreco
LOCAL_SRC_FILES := convertepreco.c

include $(BUILD_SHARED_LIBRARY)