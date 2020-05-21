package com.cc.api.common.base.param;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public abstract class BaseSqlParam {
    private String tableName;

    public BaseSqlParam() {
    }

    public abstract boolean checkSqlInjection();

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

