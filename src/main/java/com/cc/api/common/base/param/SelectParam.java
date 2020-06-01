package com.cc.api.common.base.param;


import com.cc.api.common.util.SqlUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: SelectParam
 */
public class SelectParam extends BaseSqlParam {
    private String columns;
    private List<String> conditions;
    private String sort;
    private Integer pageSize;
    private Integer pageNo;

    public SelectParam() {
    }

    @Override
    public boolean checkSqlInjection() {
        if (SqlUtil.checkSqlInjection(this.getTableName())) {
            return true;
        } else if (SqlUtil.checkSqlInjection(this.getColumns())) {
            return true;
        } else {
            if (Objects.nonNull(this.getConditions()) && !this.getConditions().isEmpty()) {
                Iterator var1 = this.getConditions().iterator();

                while(var1.hasNext()) {
                    String cond = (String)var1.next();
                    if (SqlUtil.checkSqlInjection(cond)) {
                        return true;
                    }
                }
            }

            return StringUtils.isNotBlank(this.getSort()) && SqlUtil.checkSqlInjection(this.getSort());
        }
    }

    public SelectParam addCondition(String condition) {
        if (StringUtils.isNotBlank(condition)) {
            if (Objects.isNull(this.conditions)) {
                this.conditions = new ArrayList();
            }

            if (!this.conditions.contains(condition)) {
                this.conditions.add(condition);
            }
        }

        return this;
    }

    public SelectParam addUniqueCondition(String id) {
        if (Objects.nonNull(id)) {
            if (Objects.isNull(this.conditions)) {
                this.conditions = new ArrayList();
            }

            String condition = "id = '" + id + "'";
            if (!this.conditions.contains(condition)) {
                this.conditions.add(condition);
            }
        }

        return this;
    }

    public String getColumns() {
        return this.columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public List<String> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
