package com.cc.api.common.base.provider;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: SqlProviderFactory
 */
@Component("sqlProviderFactory")
public class SqlProviderFactory {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public SqlProviderFactory() {
    }

    public SqlProvider getSqlProvider() {
        return new MysqlProvider();
    }
}

