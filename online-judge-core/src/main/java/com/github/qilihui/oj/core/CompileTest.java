package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:42 下午
 */
public class CompileTest {
    static {
        System.loadLibrary("judger");
    }

    public static void main(String[] args) {
        JudgerCore core = new JudgerCore();
        String[] arr = {
                "/usr/bin/gcc",
                "-w",
                "-fmax-errors=3",
                "-std=c11",
                "/home/qlh/project/testcase/demo/main.c",
                "-lm",
                "-o",
                "/home/qlh/project/testcase/demo/main",
        };
        String[] env = {"PATH=" + System.getenv("PATH")};
        JudgerResult result = core.run(
                -1,
                -1,
                -1,
                1024 * 1024 * 10,
                -1,
                -1,
                1024 * 1024 * 10,
                "/usr/bin/gcc",
                "/dev/null",
                "/home/qlh/project/testcase/demo/compile_path",
                "/home/qlh/project/testcase/demo/error_path",
                arr,
                arr.length,
                env,
                env.length,
                "/home/qlh/project/testcase/demo/run.log",
                null,
                1001,
                1001
        );
        System.out.println(result);
    }
}
