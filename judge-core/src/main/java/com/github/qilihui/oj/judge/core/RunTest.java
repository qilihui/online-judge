package com.github.qilihui.oj.judge.core;

import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:42 下午
 */
public class RunTest {

    public static void main(String[] args) {
        compile();
        run();
    }

    public static void compile() {
        JudgerCore core = JudgerCore.getInstance();
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
        JudgerConfig config = new JudgerConfig(
                -1,
                -1,
                -1,
                1024 * 1024 * 10,
                -1,
                -1,
                1024 * 1024 * 10,
                "/usr/bin/gcc",
                "/dev/null",
                "/dev/null",
                "/home/qlh/project/testcase/demo/compile_result",
                arr,
                env,
                "/dev/null",
                SeccompRuleEnum.NON,
                1001,
                1001
        );
        JudgerResult result = core.run(config);
        System.out.println(result);
    }

    public static void run() {
        JudgerCore core = JudgerCore.getInstance();
        JudgerConfig config = new JudgerConfig(
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
                null,
                "/dev/null",
                SeccompRuleEnum.NON,
                1001,
                1001);
        JudgerResult result = core.run(config);
        System.out.println(result);
    }
}
