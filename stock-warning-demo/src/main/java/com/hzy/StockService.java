package com.hzy;

import com.hzy.datasource.StockDataSourceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    StockDataSourceImpl stockDataSource;

    @PostMapping("getStockDetails")
    @ResponseBody
    public String getStockDetails(@RequestParam("code") String code) {
        String response = stockDataSource.getStockDetails(code);
        return response;
    }

}
