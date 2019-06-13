package com.monkey.wx.dao;

import com.monkey.wx.domain.WxOrder;
import com.monkey.wx.domain.WxOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxOrderMapper {
    int countByExample(WxOrderExample example);

    int deleteByExample(WxOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxOrder record);

    int insertSelective(WxOrder record);

    List<WxOrder> selectByExample(WxOrderExample example);

    /**
     * 根据传入的数据，只查询出需要显示在页面的信息
     * @param example
     * @return
     */
    List<WxOrder> selectByExampleTwo(WxOrderExample example);

    WxOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxOrder record, @Param("example") WxOrderExample example);

    int updateByExample(@Param("record") WxOrder record, @Param("example") WxOrderExample example);

    int updateByPrimaryKeySelective(WxOrder record);

    int updateByPrimaryKey(WxOrder record);
}