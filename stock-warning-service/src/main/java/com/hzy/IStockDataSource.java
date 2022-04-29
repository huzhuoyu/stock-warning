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
    JSONArray getAllStock() throws URISyntaxException;
}
