package com.cc.api.common.base.param;


import com.cc.api.common.util.SqlUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: UpdateParam
 */
public class UpdateParam extends BaseSqlParam {
    private Map<String, Object> values;
    private List<String> conditions;

    public UpdateParam() {
    }

    @Override
    public boolean checkSqlInjection() {
        if (SqlUtil.checkSqlInjection(this.getTableName())) {
            return true;
        } else {
            Iterator var1 = this.values.entrySet().iterator();

            while(var1.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var1.next();
                if (entry.getValue() instanceof String && SqlUtil.checkSqlInjection(entry.getValue().toString())) {
                    return true;
                }
            }

            if (Objects.nonNull(this.getConditions()) && !this.getConditions().isEmpty()) {
                var1 = this.getConditions().iterator();

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

    public UpdateParam addValue(String column, Object value) {
        if (StringUtils.isNotBlank(column) && Objects.nonNull(value)) {
            if (Objects.isNull(this.values)) {
                this.values = new HashMap();
            }

            if (!this.values.containsKey(column)) {
                this.values.put(column, value);
            }
        }

        return this;
    }

    public UpdateParam addCondition(String condition) {
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

    public UpdateParam addUniqueCondition(String id) {
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

    public Map<String, Object> getValues() {
        return this.values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

    public List<String> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }
}
