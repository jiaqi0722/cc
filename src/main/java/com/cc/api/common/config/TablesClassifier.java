package com.cc.api.common.config;

import com.cc.api.common.base.mapper.BaseMapper;
import com.cc.api.common.base.provider.SqlProviderFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: TablesClassifier
 */
@Component
@Order(-2147483648)
public class TablesClassifier implements ApplicationRunner {
    @Autowired
    private BaseMapper mapper;
    @Autowired
    private SqlProviderFactory sqlProviderFactory;
    private static final String[] DEFAULT_COLUMNS = new String[]{"create_date",  "update_date", "soft_del"};
    private static Log log = LogFactory.getLog(TablesClassifier.class);

    public TablesClassifier() {
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("开始数据库表分类");
        List<Map<String, Object>> allTables = this.mapper.selectTables();
        allTables.forEach((t) -> {
            String tableName = null;
            Iterator var3 = t.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                Object obj = entry.getValue();
                if (Objects.nonNull(obj)) {
                    tableName = obj.toString();
                    break;
                }
            }

            List<Map<String, Object>> structure = this.mapper.showTableStructure(tableName);
            int matches = 0;
            Iterator var10 = structure.iterator();

            while(var10.hasNext()) {
                Map<String, Object> s = (Map)var10.next();
                if (matches >= DEFAULT_COLUMNS.length) {
                    break;
                }

                String columnName = s.get(this.sqlProviderFactory.getSqlProvider().getColumnKey()).toString();
                if (ArrayUtils.contains(DEFAULT_COLUMNS, columnName)) {
                    ++matches;
                }
            }

            if (matches >= DEFAULT_COLUMNS.length) {
                log.info("TABLE :" + tableName + "为默认结构");
                TablesConfig.addDefaultSet(tableName);
            } else {
                log.info("TABLE :" + tableName + "为自定义结构");
                TablesConfig.addCustomSet(tableName);
            }

        });
        log.info("结束数据库表分类");
    }
}
