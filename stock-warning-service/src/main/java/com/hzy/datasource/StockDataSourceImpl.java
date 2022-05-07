package com.hzy.datasource;

import com.alibaba.fastjson.JSONArray;
import com.hzy.IStockDataSource;
import com.hzy.contants.StockContants;
import com.hzy.entity.AllStockInfo;
import com.hzy.entity.StockDetailsInfo;
import com.hzy.exception.StockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TxNowData
 * @Description 股票行情
 * @Author huzhuoyu
 * @Date 2022/4/21 11:20 上午
 */
@Slf4j
public class StockDataSourceImpl implements IStockDataSource {
    @Autowired
    StockDetailsInfo stockDetailsInfo;

    @Autowired
    AllStockInfo allStockInfo;

    /**
     * 获取所有股票信息
     * （单次查询最多返回100只股票）
     * 股票类型（深圳A股：sz_a  上海A股：sh_a）
     *
     * @return 返回所有符合类型的股票信息
     */
    @Override
    public JSONArray getAllStock() {
        AtomicInteger num = new AtomicInteger(1);
        JSONArray resultJson = null;
        RestTemplate restTemplate = new RestTemplate();
        resultJson = getWebDataSource(num, resultJson, restTemplate);
        return resultJson;
    }

    /**
     * 查询个股详细信息，上层需要转换为对象。
     *
     * @param code
     * @return
     */
    @Override
    public String getStockDetails(String code) {
        String result = null;
        RestTemplate restTemplate = new RestTemplate();
        result = getStockDetails(code, result, restTemplate);
//        ReflectUtils.stringCopyValueToEntity(result, stockDetailsInfo);
        return result;
    }

    /**
     * 从新浪财经获取数据
     *
     * @param num
     * @param resultJson
     * @param restTemplate
     * @return
     */
    private JSONArray getWebDataSource(AtomicInteger num, JSONArray resultJson, RestTemplate restTemplate) {
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
            URI uri = null;
            try {
                uri = new URI(url);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("URL错误");
                }
                throw new StockException(StockContants.FAILED, "URL错误", e);
            }
            resultJson = JSONArray.parseArray(restTemplate.getForObject(uri, String.class));
            for (int i1 = 0; i1 < resultJson.size(); i1++) {
                if (log.isInfoEnabled()) {
                    log.info("市场:[{}],第[{}]只股票为:{}", stockType, num.getAndIncrement(), resultJson.getString(i1));
                }
            }
            if (resultJson.size() == 0) {
                if (!flag) {
                    break;
                }
                flag = false;
                stockType = "sh_a";
                i = 1;
                if (log.isInfoEnabled()) {
                    log.info("====================开始打印上海A股股票====================");
                }
                continue;
            }
        }
        return resultJson;
    }

    /**
     * 从腾讯证券获取个股详细信息
     *
     * @param code
     * @param result
     * @param restTemplate
     * @return
     */
    private String getStockDetails(String code, String result, RestTemplate restTemplate) {
        String sh_url = StockContants.DETAILS_BASE_URL + StockContants.SHANG_HAI + code;
        String sz_url = StockContants.DETAILS_BASE_URL + StockContants.SHEN_ZHEN + code;
        //发送请求
        URI sh_uri = null;
        URI sz_uri = null;

        try {
            sh_uri = new URI(sh_url);
            sz_uri = new URI(sz_url);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("URL错误");
            }
            throw new StockException(StockContants.FAILED, "URL错误", e);
        }
        result = restTemplate.getForObject(sh_uri, String.class);
        if (result.contains("v_pv_none_match")) {
            result = restTemplate.getForObject(sz_uri, String.class);
        }
        log.info(result);
        return result;
    }


}
