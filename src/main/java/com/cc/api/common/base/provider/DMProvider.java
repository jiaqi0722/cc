package com.cc.api.common.base.provider;


import com.cc.api.common.base.param.DeleteParam;
import com.cc.api.common.base.param.InsertParam;
import com.cc.api.common.base.param.SelectParam;
import com.cc.api.common.exception.SqlInjectionException;
import com.cc.api.common.util.DateUtil;
import com.cc.api.common.base.param.UpdateParam;

import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 * @author teangtang
 */
public class DMProvider implements SqlProvider {

    public DMProvider() {
    }

    @Override
    public String generateSql(String sql) {
        return sql;
    }

    @Override
    public String generateSelectSql(final SelectParam p) {
        if (p.checkSqlInjection()) {
            throw new SqlInjectionException();
        } else {
            String sql = (new SQL() {
                {
                    this.SELECT(p.getColumns());
                    this.FROM(p.getTableName());
                    if (Objects.nonNull(p.getConditions())) {
                        p.getConditions().forEach((cond) -> {
                            SQL var10000 = (SQL)this.WHERE(cond);
                        });
                    }

                    if (StringUtils.isNotBlank(p.getSort())) {
                        this.ORDER_BY(p.getSort());
                    }

                }
            }).toString();
            if (Objects.nonNull(p.getPageNo()) && Objects.nonNull(p.getPageSize())) {
                StringBuilder limit = new StringBuilder(" LIMIT ");
                limit.append((p.getPageNo() - 1) * p.getPageSize());
                limit.append(", ");
                limit.append(p.getPageSize());
                sql = sql + limit.toString();
            }

            return sql;
        }
    }

    @Override
    public String generateInsertSql(final InsertParam p) {
        if (p.checkSqlInjection()) {
            throw new SqlInjectionException();
        } else {
            return (new SQL() {
                {
                    this.INSERT_INTO(p.getTableName());
                    p.getValues().forEach((k, v) -> {
                        if (v instanceof String) {
                            this.VALUES(k, "'" + v + "'");
                        } else if (v instanceof Date) {
                            this.VALUES(k, "'" + DateUtil.getDateTimeFormat((Date)v, "YYYY-MM-dd HH:mm:ss") + "'");
                        } else {
                            this.VALUES(k, v.toString());
                        }

                    });
                }
            }).toString();
        }
    }

    @Override
    public String generateUpdateSql(final UpdateParam p) {
        if (p.checkSqlInjection()) {
            throw new SqlInjectionException();
        } else {
            return (new SQL() {
                {
                    this.UPDATE(p.getTableName());
                    p.getValues().forEach((k, v) -> {
                        if (!"id".equals(k.toLowerCase())) {
                            if (v instanceof String) {
                                this.SET(k + "='" + v + "'");
                            } else if (v instanceof Date) {
                                this.SET(k + "='" + DateUtil.getDateTimeFormat((Date)v, "YYYY-MM-dd HH:mm:ss") + "'");
                            } else {
                                this.SET(k + "=" + v);
                            }
                        }

                    });
                    if (Objects.nonNull(p.getConditions())) {
                        p.getConditions().forEach((cond) -> {
                            SQL var10000 = (SQL)this.WHERE(cond);
                        });
                    }

                }
            }).toString();
        }
    }

    @Override
    public String generateDeleteSql(final DeleteParam p) {
        if (p.checkSqlInjection()) {
            throw new SqlInjectionException();
        } else {
            return (new SQL() {
                {
                    this.DELETE_FROM(p.getTableName());
                    if (Objects.nonNull(p.getConditions())) {
                        p.getConditions().forEach((cond) -> {
                            SQL var10000 = (SQL)this.WHERE(cond);
                        });
                    }

                }
            }).toString();
        }
    }

    @Override
    public String generateSelectTablesSql() {
        return "SELECT table_name FROM user_tables";
    }

    @Override
    public String generateShowTableStructureSql(final String tableName) {
        return (new SQL() {
            {
                this.SELECT("*");
                this.FROM("user_tab_columns");
                this.WHERE("table_name = '" + tableName + "'");
            }
        }).toString();
    }

    @Override
    public String getColumnKey() {
        return "COLUMN_NAME";
    }
}
