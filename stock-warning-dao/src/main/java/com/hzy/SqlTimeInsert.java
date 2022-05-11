package com.hzy;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hzy.utils.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @ClassName SqlTimeInsert
 * @Description 插入数据时，插入时间
 * @Author huzhuoyu
 * @Date 2022/5/11 5:02 下午
 */
@Component
public class SqlTimeInsert implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", DateUtils.getNowDate(), metaObject);
        this.setFieldValByName("lastModifyTime", DateUtils.getNowDate(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastModifyTime", DateUtils.getNowDate(), metaObject);
    }

}
