package com.cc.api.common.base.param;

import com.cc.api.common.util.SqlUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;


/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public class InsertParam extends BaseSqlParam {
    private Map<String, Object> values;

    public InsertParam() {
    }

    @Override
    public boolean checkSqlInjection() {
        if (SqlUtil.checkSqlInjection(this.getTableName())) {
            return true;
        } else {
            Iterator var1 = this.values.entrySet().iterator();

            Map.Entry entry;
            do {
                if (!var1.hasNext()) {
                    return false;
                }

                entry = (Map.Entry)var1.next();
            } while(!(entry.getValue() instanceof String) || !SqlUtil.checkSqlInjection(entry.getValue().toString()));

            return true;
        }
    }

    public InsertParam addValue(String column, Object value) {
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

    public Map<String, Object> getValues() {
        return this.values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }
}
