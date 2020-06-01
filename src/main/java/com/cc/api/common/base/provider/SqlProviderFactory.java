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
    private static final String MYSQL_DB_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public SqlProviderFactory() {
    }

    public SqlProvider getSqlProvider() {
        return new MysqlProvider();
    }
}

