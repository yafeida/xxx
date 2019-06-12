package com.monkey.wx.dao;

import com.monkey.wx.domain.WxConfig;
import com.monkey.wx.domain.WxConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxConfigMapper {
    int countByExample(WxConfigExample example);

    int deleteByExample(WxConfigExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxConfig record);

    int insertSelective(WxConfig record);

    List<WxConfig> selectByExample(WxConfigExample example);

    WxConfig selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxConfig record, @Param("example") WxConfigExample example);

    int updateByExample(@Param("record") WxConfig record, @Param("example") WxConfigExample example);

    int updateByPrimaryKeySelective(WxConfig record);

    int updateByPrimaryKey(WxConfig record);
}