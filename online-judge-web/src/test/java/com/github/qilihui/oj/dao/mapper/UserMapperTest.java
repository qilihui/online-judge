package com.github.qilihui.oj.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.gt(User::getAge, 20);
        List<User> userList = userMapper.selectList(wrapper);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}