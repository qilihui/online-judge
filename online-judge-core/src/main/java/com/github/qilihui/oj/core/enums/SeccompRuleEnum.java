package com.github.qilihui.oj.core.enums;

import lombok.AllArgsConstructor;

/**
 * @author qilihui
 * @date 2021/11/30 10:26 下午
 */
@AllArgsConstructor
public enum SeccompRuleEnum {
    C_CPP("c_cpp"),
    C_CPP_FILE_IO("c_cpp_file_io"),
    GENERAL("general"),
    GOLANG("golang"),
    NODE("node");

    private final String name;
}
