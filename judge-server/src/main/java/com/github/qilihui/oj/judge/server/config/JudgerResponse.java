package com.github.qilihui.oj.judge.server.config;

import com.github.qilihui.oj.judge.core.model.JudgerResult;
import com.github.qilihui.oj.judge.server.exception.ResponseCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qilihui
 * @date 2022/5/1 10:14 下午
 */
@Data
public class JudgerResponse {

    private Integer code;
    private String msg;
    private String submissionId;
    private List<RunDTO> run;

    @NoArgsConstructor
    @Data
    public static class RunDTO {
        private Integer id;
        private JudgerResult judgerResult;
        private Boolean pass;
    }

    public JudgerResponse() {
        this.result(ResponseCode.SUCCESS);
    }

    public JudgerResponse result(ResponseCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        return this;
    }
}
