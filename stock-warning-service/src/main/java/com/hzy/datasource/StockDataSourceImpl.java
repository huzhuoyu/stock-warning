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
public class StockDataSourceImpl implements IStockDataSource {

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

//    public static void main(String[] args) {
//        String s = "v_sh601658=\"1~邮储银行~601658~5.47~5.60~5.55~1127174~500115~627060~5.47~12244~5.46~10784~5.45~10739~5.44~8939~5.43~4726~5.48~6537~5.49~3231~5.50~1450~5.51~5127~5.52~5090~~20220506160001~-0.13~-2.32~5.59~5.46~5.47/1127174/621528316~1127174~62153~1.00~6.32~~5.59~5.46~2.32~616.71~5053.40~0.77~6.16~5.04~0.59~25997~5.51~5.06~6.63~~~0.62~62152.8316~0.0000~0~ ~GP-A~7.25~2.43~3.81~9.49~0.60~6.09~4.49~-2.32~-3.01~-2.67~11274461250~92383970000~37.75~13.49~11274461250~\";";
//        int start = s.indexOf("\"");
//        int end = s.lastIndexOf("\"");
//        String sub = s.substring(start + 1, end);
//        System.out.println(sub);
//        Class<?> StockInfo = StockDetailsInfo.getClass();
//
//    }

    @Override
    public String getStockDetails(String code) throws URISyntaxException {
        String result = null;
        RestTemplate restTemplate = new RestTemplate();
        result = getStockDetails(code, result, restTemplate);
        return result;
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

    private String getStockDetails(String code, String result, RestTemplate restTemplate) throws URISyntaxException {
        String sh_url = StockContants.DETAILS_BASE_URL + StockContants.SHANG_HAI + code;
        String sz_url = StockContants.DETAILS_BASE_URL + StockContants.SHEN_ZHEN + code;
        //发送请求
        URI sh_uri = new URI(sh_url);
        URI sz_uri = new URI(sz_url);
        result = restTemplate.getForObject(sh_uri, String.class);
        if (result.contains("v_pv_none_match")) {
            result = restTemplate.getForObject(sz_uri, String.class);
        }
        System.out.println(result);
        return result;
    }


}
