package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:42 下午
 */
public class TestMain {
    static {
        System.loadLibrary("judger");
    }

    public static void main(String[] args) {
        JudgerCore core = new JudgerCore();
        String[] arr = {};
        JudgerResult result = core.run(
                -1,
                -1,
                -1,
                102400,
                -1,
                -1,
                102400,
                "/home/qlh/project/testcase/demo/a.out",
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
