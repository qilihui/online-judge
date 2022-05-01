import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.github.qilihui.oj.judge.core.enums.SeccompRuleEnum;
import com.github.qilihui.oj.judge.core.model.JudgerConfig;
import com.github.qilihui.oj.judge.server.JudgeServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;

/**
 * @author qilihui
 * @date 2022/5/1 4:20 下午
 */
@SpringBootTest(classes = JudgeServerApplication.class)
public class TestMain {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testSend() {
        kafkaTemplate.send("test", DateUtil.formatDateTime(new Date()));
    }

    @Test
    public void test1() {
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
                arr[0],
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
        String s = JSONUtil.toJsonStr(config);
        System.out.println(s);

        JudgerConfig c1 = JSONUtil.toBean(s, JudgerConfig.class);
        System.out.println(c1);
    }
}
