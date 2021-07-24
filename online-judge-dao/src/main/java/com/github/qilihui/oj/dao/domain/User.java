package com.github.qilihui.oj.dao.domain;

import lombok.Data;

/**
 * @author qilihui
 * @date 2021/7/24 13:55
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}