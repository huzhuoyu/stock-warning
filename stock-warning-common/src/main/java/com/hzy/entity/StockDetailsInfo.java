package com.hzy.entity;

import lombok.Data;

/**
 * @ClassName StockDetailsInfo
 * @Description 指定某只股票的实体
 * @Author huzhuoyu
 * @Date 2022/5/5 4:51 下午
 */
@Data
public class StockDetailsInfo {
    //所属市场：1中国大陆 100香港 200美股
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
    private String todayOpenPrice;
    //成交量（手）
    private String dealNumber;
    //外盘:主动买入成交的数量
    private String activeBuy;
    //内盘:主动卖出成交的数量
    private String activeSell;
    //买1，2，3，4，5，卖1，2，3，4，5，以及成交量（手）
    private String buy1;
    private String buy1Number;
    private String buy2;
    private String buy2Number;
    private String buy3;
    private String buy3Number;
    private String buy4;
    private String buy4Number;
    private String buy5;
    private String buy5Number;
    private String sell1;
    private String sell1Number;
    private String sell2;
    private String sell2Number;
    private String sell3;
    private String sell3Number;
    private String sell4;
    private String sell4Number;
    private String sell5;
    private String sell5Number;
    //最近逐笔成交
    private String RecentTransaction;
    //时间戳
    private String time;
    //涨跌
    private String riseAndFall;
    //涨跌%
    private String riseAndFallRate;
    //最高
    private String max;
    //最低
    private String min;
    //价格/成交量（手）/成交额
    private String currentPriceAndDealNumberAndTurnover;
    //成交量（手）
    private String dealNumber2;
    //成交额（万）
    private String turnoverW;
    //换手率
    private String turnoverRate;
    //市盈率
    private String PERatio;
    //unknown
    private String unknown;
    //最高
    private String max1;
    //最低
    private String min1;
    //振幅
    private String amplitude;
    //流通市值（亿）
    private String circulationMarketValue;
    //总市值（亿）
    private String totalMarketValue;
    //市净率
    private String priceToBookRatio;
    //涨停价
    private String highLimit;
    //跌停价
    private String lowLimit;
    //量比
    private String volumeRatio;
    //委差：委买委卖的差值（即委差）指股市某品种当前买量之和减去卖量之和。 反映买卖双方的力量对比。 正数为买方较强，负数为抛压较重。
    private String weiCha;
    //均价
    private String averagePrice;
    //动态市盈率
    private String dynamicPERatio;
    //静态市盈率
    private String staticPERatio;
    //unknown
    private String unknown1;
    //unknown
    private String unknown2;
    //贝塔值
    private String Beta;
    //成交额（万），带小数点
    private String TurnoverWpoint;
    //todo：待确认
    private String todo;
    //todo：待确认
    private String todo1;
    //unknown
    private String unknown3;
    //类型：A股股票
    private String GPtype;
    //unknown
    private String unknown4;
    //5日涨幅
    private String fiveDaysIncrease;
    //todo：待确认
    private String todo2;
    //净资产收益率TTM
    private String returnOnNetAssets;
    //unknown
    private String unknown5;
    //小单净额占比
    private String smallOrderNetAmountRatio;
    //52周低
    private String FiftyTwoLow;
    //10日涨幅
    private String tenDaysIncrease;
    //unknown
    private String unknown6;
    //户均持股数
    private String averageNumberOfSharesPerHousehold;
    //流通股本
    private String circulatingShareCapital;
    //流通股本
    private String circulatingShareCapital1;
    //委比
    private String committee;
    //unknown
    private String unknown7;
    //流通股本
    private String circulatingShareCapital2;
}
