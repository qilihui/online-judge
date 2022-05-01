package com.github.qilihui.oj.judge.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JudgerConfig.SeccompRuleEnum
 *
 * @author qilihui
 * @date 2021/11/30 10:26 下午
 */
@AllArgsConstructor
@Getter
public enum SeccompRuleEnum {
    NON(null),
    C_CPP("c_cpp"),
    C_CPP_FILE_IO("c_cpp_file_io"),
    GENERAL("general"),
    GOLANG("golang"),
    NODE("node");

    private final String ruleName;

    public static SeccompRuleEnum findByRuleName(String ruleName) {
        if (ruleName == null) {
            return NON;
        }
        for (SeccompRuleEnum value : SeccompRuleEnum.values()) {
            if (ruleName.equals(value.getRuleName())) {
                return value;
            }
        }
        return NON;
    }
}
