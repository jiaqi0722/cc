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
 * @author teangtang
 */
public interface SqlProvider {
    String generateSql(String var1);

    String generateSelectSql(SelectParam var1);

    String generateInsertSql(InsertParam var1);

    String generateUpdateSql(UpdateParam var1);

    String generateDeleteSql(DeleteParam var1);

    String generateSelectTablesSql();

    String generateShowTableStructureSql(String var1);

    String getColumnKey();
}
