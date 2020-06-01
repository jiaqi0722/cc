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
 * describe: DeleteParam
 */
public class DeleteParam extends BaseSqlParam {
    private List<String> conditions;

    public DeleteParam() {
    }

    @Override
    public boolean checkSqlInjection() {
        if (SqlUtil.checkSqlInjection(this.getTableName())) {
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

            return false;
        }
    }

    public DeleteParam addCondition(String condition) {
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

    public DeleteParam addUniqueCondition(String id) {
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

    public List<String> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
