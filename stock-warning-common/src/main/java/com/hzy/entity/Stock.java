package com.hzy.entity;

import lombok.Data;

/**
 * @ClassName Stock
 * @Description 股票实体类
 * @Author huzhuoyu
 * @Date 2022/4/21 3:17 下午
 */
@Data
public class Stock {
    //所属市场
    private String national;
    //股票名称
    private String name;
    //股票代码
    private String code;
    //当前价格
    private String currentPrice;
    //昨收
    private String yesterdayClosePrice;
    //今开
    private String TodayOpenPrice;
    //成交量（手）
    private String dealNumber;
    //外盘:主动买入成交的数量
    private String B;
    //内盘:主动卖出成交的数量
    private String S;
}
