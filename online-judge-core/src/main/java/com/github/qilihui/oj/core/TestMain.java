package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerConfig;
import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:42 下午
 */
public class TestMain {
    static {
        System.loadLibrary("judge");
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.library.path"));
        JudgerCore core = new JudgerCore();
        JudgerConfig config = new JudgerConfig();
        JudgerResult result = core.run(config);
        System.out.println(result);
    }
}
