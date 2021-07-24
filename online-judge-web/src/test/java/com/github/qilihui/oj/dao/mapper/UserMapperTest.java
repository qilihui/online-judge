package com.github.qilihui.oj.dao.mapper;

import com.github.qilihui.oj.BaseTest;
import com.github.qilihui.oj.dao.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author qilihui
 * @date 2021/7/24 13:59
 */
class UserMapperTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}