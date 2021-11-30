package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:42 下午
 */
public class RunTest {
    static {
        System.loadLibrary("judger");
    }

    public static void main(String[] args) {
        JudgerCore core = new JudgerCore();
        JudgerResult result = core.run(
                -1,
                -1,
                -1,
                1024 * 1024 * 10,
                -1,
                -1,
                1024 * 1024 * 10,
                "/home/qlh/project/testcase/demo/main",
                "/home/qlh/project/testcase/demo/1.in",
                "/home/qlh/project/testcase/demo/1.out",
                "/home/qlh/project/testcase/demo/err.out",
                null,
                0,
                null,
                0,
                "/home/qlh/project/testcase/demo/1.log",
                null,
                1001,
                1001);
        System.out.println(result);
    }
}
