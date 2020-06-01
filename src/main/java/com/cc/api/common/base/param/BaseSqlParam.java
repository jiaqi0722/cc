package com.cc.api.common.base.param;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: BaseSqlParam
 */
public abstract class BaseSqlParam {
    private String tableName;

    public BaseSqlParam() {
    }

    /**
     * 异常处理
     * @return boolean
     */
    public abstract boolean checkSqlInjection();

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

