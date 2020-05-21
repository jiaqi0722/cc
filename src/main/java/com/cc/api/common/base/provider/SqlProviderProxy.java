package com.cc.api.common.base.provider;


import com.cc.api.common.base.param.DeleteParam;
import com.cc.api.common.base.param.InsertParam;
import com.cc.api.common.base.param.SelectParam;
import com.cc.api.common.util.SpringUtil;
import com.cc.api.common.base.param.UpdateParam;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public class SqlProviderProxy {
    private SqlProviderFactory sqlProviderFactory = (SqlProviderFactory) SpringUtil.getBean("sqlProviderFactory");

    public SqlProviderProxy() {
    }

    public String proxySql(String sql) {
        return this.sqlProviderFactory.getSqlProvider().generateSql(sql);
    }

    public String proxySelectSql(SelectParam p) {
        return this.sqlProviderFactory.getSqlProvider().generateSelectSql(p);
    }

    public String proxyInsertSql(InsertParam p) {
        return this.sqlProviderFactory.getSqlProvider().generateInsertSql(p);
    }

    public String proxyUpdateSql(UpdateParam p) {
        return this.sqlProviderFactory.getSqlProvider().generateUpdateSql(p);
    }

    public String generateDeleteSql(DeleteParam p) {
        return this.sqlProviderFactory.getSqlProvider().generateDeleteSql(p);
    }

    public String generateSelectTablesSql() {
        return this.sqlProviderFactory.getSqlProvider().generateSelectTablesSql();
    }

    public String generateShowTableStructureSql(String tableName) {
        return this.sqlProviderFactory.getSqlProvider().generateShowTableStructureSql(tableName);
    }
}
