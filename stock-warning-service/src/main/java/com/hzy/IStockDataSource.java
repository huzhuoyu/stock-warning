package com.hzy;

import com.alibaba.fastjson.JSONArray;

/**
 * @ClassName IStockSource
 * @Description 股票接口
 * @Author huzhuoyu
 * @Date 2022/4/21 11:29 上午
 */
public interface IStockDataSource {
    /**
     * 获取所有股票信息
     *
     * @return
     */
    JSONArray getAllStock();

    /**
     * 获取指定股票的信息
     *
     * @return
     */
    String getStockDetails(String code);
}
