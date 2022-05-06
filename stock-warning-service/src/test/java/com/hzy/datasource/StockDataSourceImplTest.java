package com.hzy.datasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;

/**
 * @ClassName
 * @Description
 * @Author huzhuoyu
 * @Date 2022/5/5 10:42 上午
 */
@RunWith(MockitoJUnitRunner.class)
public class StockDataSourceImplTest {

    @InjectMocks
    StockDataSourceImpl stockDataSource;

    @Test
    public void getAllStock() throws URISyntaxException {
        stockDataSource.getAllStock();
    }

    @Test
    public void getStockDetails() throws URISyntaxException {
        stockDataSource.getStockDetails("600397");
        stockDataSource.getStockDetails("601658");
        stockDataSource.getStockDetails("002387");
        stockDataSource.getStockDetails("002384");
    }
}