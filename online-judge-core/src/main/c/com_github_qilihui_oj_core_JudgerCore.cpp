#include "com_github_qilihui_oj_core_JudgerCore.h"
// #include "runner.h"

JNIEXPORT jobject JNICALL Java_com_github_qilihui_oj_core_JudgerCore_run(JNIEnv *env, jobject instance, jobject config)
{

    jclass judgerResultClass = env->FindClass("com/github/qilihui/oj/core/model/JudgerResult");
    jmethodID construction_ = env->GetMethodID(judgerResultClass, "<init>", "()V");
    jobject judgerResult_ = env->NewObject(judgerResultClass, construction_);

    jfieldID cpuTime_ = env->GetFieldID(judgerResultClass, "cpuTime", "I");
    jfieldID realTime_ = env->GetFieldID(judgerResultClass, "realTime", "I");
    jfieldID memory_ = env->GetFieldID(judgerResultClass, "memory", "J");
    jfieldID signal_ = env->GetFieldID(judgerResultClass, "signal", "I");
    jfieldID exitCode_ = env->GetFieldID(judgerResultClass, "exitCode", "I");
    jfieldID error_ = env->GetFieldID(judgerResultClass, "error", "I");
    jfieldID result_ = env->GetFieldID(judgerResultClass, "result", "I");

    // env->SetObjectField(result_, cpuTime_, env->NewStringUTF("liuwei"));
    env->SetIntField(judgerResult_, cpuTime_, 20);
    env->SetIntField(judgerResult_, realTime_, 30);
    env->SetIntField(judgerResult_, memory_, 40);
    env->SetIntField(judgerResult_, signal_, 50);
    env->SetIntField(judgerResult_, exitCode_, 60);
    env->SetIntField(judgerResult_, error_, 70);
    env->SetIntField(judgerResult_, result_, 80);

    return judgerResult_;
}