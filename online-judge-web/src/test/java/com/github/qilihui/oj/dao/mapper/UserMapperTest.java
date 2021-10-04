package com.github.qilihui.oj.dao.mapper;


import com.github.qilihui.oj.BaseTest;
import com.github.qilihui.oj.dao.entity.OjUser;
import com.github.qilihui.oj.dao.entity.OjUserExample;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qilihui
 * @date 2021/7/24 13:59
 */
class UserMapperTest extends BaseTest {
    @Resource
    private OjUserMapper userMapper;

    @Test
    public void test(){
        OjUserExample example = new OjUserExample();
        example.createCriteria().andAgeBetween(10, 20);
        List<OjUser> userList = userMapper.selectByExample(example);
        userList.forEach(System.out::println);
    }
}