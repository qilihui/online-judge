package com.github.qilihui.oj.dao.mapper;

import com.github.qilihui.oj.dao.entity.OjUser;
import com.github.qilihui.oj.dao.entity.OjUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OjUserMapper {
    long countByExample(OjUserExample example);

    int deleteByExample(OjUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OjUser record);

    int insertSelective(OjUser record);

    List<OjUser> selectByExample(OjUserExample example);

    OjUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OjUser record, @Param("example") OjUserExample example);

    int updateByExample(@Param("record") OjUser record, @Param("example") OjUserExample example);

    int updateByPrimaryKeySelective(OjUser record);

    int updateByPrimaryKey(OjUser record);
}