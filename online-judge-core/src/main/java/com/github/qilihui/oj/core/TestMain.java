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
        String[] arr = {"a1", "a32"};
        JudgerResult result = core.run(1,
                2,
                3,
                4,
                5,
                6,
                7,
                "/et",
                "/et",
                "/et",
                "/et",
                arr,
                2,
                null,
                0,
                "/et",
                "/et",
                7,
                8);
        System.out.println(result);
    }
}
