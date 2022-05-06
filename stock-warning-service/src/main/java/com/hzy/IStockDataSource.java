package com.hzy;

import com.alibaba.fastjson.JSONArray;

import java.net.URISyntaxException;

/**
 * @ClassName IStockSource
 * @Description 股票接口
 * @Author huzhuoyu
 * @Date 2022/4/21 11:29 上午
 */
public interface IStockDataSource {
    /**
     * 获取所有股票信息
     * @return
     * @throws URISyntaxException
     */
    JSONArray getAllStock() throws URISyntaxException;

    /**
     * 获取指定股票的信息
     * @return
     * @throws URISyntaxException
     */
    String getStockDetails(String code) throws URISyntaxException;
}
