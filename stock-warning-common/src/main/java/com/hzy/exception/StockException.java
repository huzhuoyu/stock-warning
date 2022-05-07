package com.hzy.exception;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName StockException
 * @Description 异常类
 * @Author huzhuoyu
 * @Date 2022/5/7 10:48 上午
 */
@Slf4j
@Data
@ToString
public class StockException extends RuntimeException {
    private static final long serialVersionUID = 4502307755023603016L;

    private String errorMessage;
    private String errorCode;
    private String parameters;

    public StockException() {
        super();
    }

    public StockException(String errorCode, Object... parameters) {
        super();
    }

    public StockException(String errorCode) {
        this(errorCode, (Object[]) null);
    }


}
