package com.hzy.datasource;

import com.alibaba.fastjson.JSONArray;
import com.hzy.IStockDataSource;
import com.hzy.contants.StockContants;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TxNowData
 * @Description 股票行情
 * @Author huzhuoyu
 * @Date 2022/4/21 11:20 上午
 */
public class TxStockNow implements IStockDataSource {

    /**
     * 获取所有股票信息
     * （单次查询最多返回100只股票）
     * 股票类型（深圳A股：sz_a  上海A股：sh_a）
     *
     * @return 返回所有符合类型的股票信息
     */
    @Override
    public JSONArray getAllStock() throws URISyntaxException {
        AtomicInteger num = new AtomicInteger(1);
        JSONArray resultJson = null;
        RestTemplate restTemplate = new RestTemplate();
        resultJson = getWebDataSource(num, resultJson, restTemplate);
        return resultJson;
    }

    /**
     * 从新浪财经获取数据
     *
     * @param num
     * @param resultJson
     * @param restTemplate
     * @return
     * @throws URISyntaxException
     */
    private JSONArray getWebDataSource(AtomicInteger num, JSONArray resultJson, RestTemplate restTemplate) throws URISyntaxException {
        String stockType = "sz_a";
        boolean flag = true;
        for (int i = 1; i < 100; i++) {
            String url = StockContants.SINA_BASE_URL +
                    "page=" + i +
                    "&num=100" +
                    //排序的列
                    "&sort=symbol" +
                    //1：升序  0：降序
                    "&asc=1" +
                    "&node=" + stockType +
                    "&symbol=" +
                    //以symbol升序时 默认为init 根据其他排序时均为sort
                    "&_s_r_a=init";
            //发送请求
            URI uri = new URI(url);
            resultJson = JSONArray.parseArray(restTemplate.getForObject(uri, String.class));
            for (int i1 = 0; i1 < resultJson.size(); i1++) {
                System.out.println("市场" + stockType + "第" + num.getAndIncrement() + "只股票为：" + resultJson.getString(i1));
            }
            if (resultJson.size() == 0) {
                if (!flag) {
                    break;
                }
                flag = false;
                stockType = "sh_a";
                i = 1;
                continue;
            }
        }
        return resultJson;
    }
}
