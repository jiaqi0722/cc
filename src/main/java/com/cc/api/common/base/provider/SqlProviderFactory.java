package com.cc.api.common.base.provider;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
@Component("sqlProviderFactory")
public class SqlProviderFactory {
    private static final String MYSQL_DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DM_DB_DRIVER_NAME = "dm.jdbc.driver.DmDriver";
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public SqlProviderFactory() {
    }

    public SqlProvider getSqlProvider() {
        String var1 = this.driverClassName;
        byte var2 = -1;
        switch(var1.hashCode()) {
            case -1865851767:
                if (var1.equals("dm.jdbc.driver.DmDriver")) {
                    var2 = 1;
                }
                break;
            case 931983394:
                if (var1.equals("com.mysql.jdbc.Driver")) {
                    var2 = 0;
                }
        }

        switch(var2) {
            case 0:
                return new MysqlProvider();
            case 1:
                return new DMProvider();
            default:
                return new MysqlProvider();
        }
    }
}

