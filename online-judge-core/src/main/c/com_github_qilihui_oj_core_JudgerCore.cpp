#include "com_github_qilihui_oj_core_JudgerCore.h"
#include "runner.h"
#include <iostream>
using namespace std;

// #include "runner.h"

JNIEXPORT jobject JNICALL Java_com_github_qilihui_oj_core_JudgerCore_run(JNIEnv *env,
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
                                                                         jint gid_)

{
    int maxCpuTime = (int)maxCpuTime_;
    cout << "Get field maxCpuTime(int) = " << maxCpuTime << endl;

    const char *exePath = env->GetStringUTFChars(exePath_, 0);
    cout << "Get field exePath(String) = " << exePath << endl;

    const char *arg0= env->GetStringUTFChars(static_cast<jstring>(env->GetObjectArrayElement(args_, 0)), 0);
    cout << "Get field args(jobjectArray) = " << arg0 << endl;
    const char *arg1= env->GetStringUTFChars(static_cast<jstring>(env->GetObjectArrayElement(args_, 1)), 0);
    cout << "Get field args(jobjectArray) = " << arg1 << endl;
    cout << "Get field argsLength_(int) = " << argsLength_ << endl;



    jclass judgerResultClass = env->FindClass("com/github/qilihui/oj/core/model/JudgerResult");
    jmethodID construction_ = env->GetMethodID(judgerResultClass, "<init>", "()V");
    jobject judgerResult_ = env->NewObject(judgerResultClass, construction_);

    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "cpuTime", "I"), 20);
    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "realTime", "I"), 30);
    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "memory", "J"), 40);
    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "signal", "I"), 50);
    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "exitCode", "I"), 60);
    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "error", "I"), 70);
    env->SetIntField(judgerResult_, env->GetFieldID(judgerResultClass, "result", "I"), 80);

    return judgerResult_;
}