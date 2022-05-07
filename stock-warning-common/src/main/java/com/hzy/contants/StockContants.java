package com.hzy.contants;

import lombok.Data;

/**
 * @ClassName com.hzy.contants.StockContants
 * @Description 常量
 * @Author huzhuoyu
 * @Date 2022/4/1 7:33 下午
 */
@Data
public class StockContants {
    public static final String SHEN_ZHEN = "sz";
    public static final String SHANG_HAI = "sh";
    public static final String DETAILS_BASE_URL = "http://qt.gtimg.cn/q=";
    public static final String SINA_BASE_URL = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?";
    public static final String SUCCESS = "200";
    public static final String FAILED = "500";
}
