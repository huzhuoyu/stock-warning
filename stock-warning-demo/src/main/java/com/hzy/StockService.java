package com.hzy;

import com.alibaba.fastjson.JSON;
import com.hzy.datasource.StockDataSourceImpl;
import com.hzy.entity.StockDetailsInfo;
import com.hzy.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StockService
 * @Description demo接口
 * @Author huzhuoyu
 * @Date 2022/5/7 4:59 下午
 */
@Slf4j
@RestController
@RequestMapping("/stock")
public class StockService {

    @PostMapping("getStockDetails")
    @ResponseBody
    public JSON getStockDetails(@RequestParam("code") String... code) {
        StockDataSourceImpl stockDataSource = new StockDataSourceImpl();
        StockDetailsInfo stockDetailsInfo = new StockDetailsInfo();
        List list = new ArrayList();

        JSON jsonResponse = null;
        for (int i = 0; i < code.length; i++) {
            String response = stockDataSource.getStockDetails(code[i]);
            ReflectUtils.stringCopyValueToEntity(response, stockDetailsInfo);
            list.add(stockDetailsInfo);

        }
        jsonResponse = (JSON) JSON.toJSON(list);
        return jsonResponse;
    }

}
