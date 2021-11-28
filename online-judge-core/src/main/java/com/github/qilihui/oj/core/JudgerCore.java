package com.github.qilihui.oj.core;

import com.github.qilihui.oj.core.model.JudgerConfig;
import com.github.qilihui.oj.core.model.JudgerResult;

/**
 * @author qilihui
 * @date 2021/11/28 12:48 上午
 */
public class JudgerCore {
    public native JudgerResult run(JudgerConfig config);
}
