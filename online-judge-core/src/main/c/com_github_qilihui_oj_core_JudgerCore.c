#include "com_github_qilihui_oj_core_JudgerCore.h"
#include "runner.h"
#include <stdio.h>

JNIEXPORT jobject

JNICALL Java_com_github_qilihui_oj_core_JudgerCore_run(JNIEnv *env,
                                                       jobject instance,
                                                       jint maxCpuTime_,
                                                       jint maxRealTime_,
                                                       jlong maxMemory_,
                                                       jlong maxStack_,
                                                       jint maxProcessNumber_,
                                                       jlong maxOutputSize_,
                                                       jint memoryLimitCheckOnly_,
                                                       jstring exePath_,
                                                       jstring inputPath_,
                                                       jstring outputPath_,
                                                       jstring errorPath_,
                                                       jobjectArray args_,
                                                       jint argsLength_,
                                                       jobjectArray env_,
                                                       jint envLength_,
                                                       jstring logPath_,
                                                       jstring seccompRuleName_,
                                                       jint uid_,
                                                       jint gid_) {
    char *reqArgs[ARGS_MAX_NUMBER];
    for (int i = 0; i < argsLength_; ++i) {
        reqArgs[i] = (char *) (*env)->GetStringUTFChars(env,
                                                        (jstring)((*env)->GetObjectArrayElement(env, args_, i)),
                                                        0);
    }
    char *reqEnv[ARGS_MAX_NUMBER];
    for (int i = 0; i < envLength_; ++i) {
        reqEnv[i] = (char *) (*env)->GetStringUTFChars(env,
                                                       (jstring)((*env)->GetObjectArrayElement(env, env_, i)),
                                                       0);
    }

    struct config req = {
            (int) maxCpuTime_,
            (int) maxRealTime_,
            (long) maxMemory_,
            (long) maxStack_,
            (int) maxProcessNumber_,
            (long) maxOutputSize_,
            (int) memoryLimitCheckOnly_,
            (char *) (*env)->GetStringUTFChars(env, exePath_, 0),
            (char *) (*env)->GetStringUTFChars(env, inputPath_, 0),
            (char *) (*env)->GetStringUTFChars(env, outputPath_, 0),
            (char *) (*env)->GetStringUTFChars(env, errorPath_, 0),
            {(char *) reqArgs},
            {(char *) reqEnv},
            (char *) (*env)->GetStringUTFChars(env, logPath_, 0),
            (char *) (*env)->GetStringUTFChars(env, seccompRuleName_, 0),
            (int) uid_,
            (int) gid_
    };
    struct result resp;
    run(&req, &resp);

    jclass judgerResultClass = (*env)->FindClass(env, "com/github/qilihui/oj/core/model/JudgerResult");
    jmethodID construction_ = (*env)->GetMethodID(env, judgerResultClass, "<init>", "()V");
    jobject judgerResult_ = (*env)->NewObject(env, judgerResultClass, construction_);

    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "cpuTime", "I"),
                        resp.cpu_time);
    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "realTime", "I"),
                        resp.real_time);
    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "memory", "J"),
                        resp.memory);
    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "signal", "I"),
                        resp.signal);
    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "exitCode", "I"),
                        resp.exit_code);
    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "error", "I"),
                        resp.error);
    (*env)->SetIntField(env, judgerResult_,
                        (*env)->GetFieldID(env, judgerResultClass, "result", "I"),
                        resp.result);

    return judgerResult_;
}