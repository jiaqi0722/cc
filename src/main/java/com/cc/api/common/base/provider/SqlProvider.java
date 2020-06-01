package com.cc.api.common.base.provider;


import com.cc.api.common.base.param.DeleteParam;
import com.cc.api.common.base.param.InsertParam;
import com.cc.api.common.base.param.SelectParam;
import com.cc.api.common.base.param.UpdateParam;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: SqlProvider
 */
public interface SqlProvider {
    /**
     * generateSql
     * @param var1 str
     * @return string
     */
    String generateSql(String var1);
    /**
     * generateSelectSql
     * @param var1 str
     * @return string
     */
    String generateSelectSql(SelectParam var1);
    /**
     * generateInsertSql
     * @param var1 str
     * @return string
     */
    String generateInsertSql(InsertParam var1);
    /**
     * generateUpdateSql
     * @param var1 str
     * @return string
     */
    String generateUpdateSql(UpdateParam var1);
    /**
     * generateDeleteSql
     * @param var1 str
     * @return string
     */
    String generateDeleteSql(DeleteParam var1);
    /**
     * generateSelectTablesSql
     * @return string
     */
    String generateSelectTablesSql();
    /**
     * generateShowTableStructureSql
     * @param var1 str
     * @return string
     */
    String generateShowTableStructureSql(String var1);
    /**
     * getColumnKey
     * @return string
     */
    String getColumnKey();
}
