package com.cc.api.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
@Component
@PropertySource({"classpath:tables.properties"})
@ConfigurationProperties(
        prefix = "tables"
)
public class TablesConfig {
    private static Set<String> DEFAULT_TABLES_SET;
    private static Set<String> CUSTOM_TABLES_SET;
    private Map<String, String> map;

    public TablesConfig() {
    }

    public Map<String, String> getMap() {
        return this.map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public static void addDefaultSet(String tableName) {
        if (Objects.isNull(DEFAULT_TABLES_SET)) {
            DEFAULT_TABLES_SET = new HashSet();
        }

        DEFAULT_TABLES_SET.add(tableName);
    }

    public static void addCustomSet(String tableName) {
        if (Objects.isNull(CUSTOM_TABLES_SET)) {
            CUSTOM_TABLES_SET = new HashSet();
        }

        CUSTOM_TABLES_SET.add(tableName);
    }

    public static boolean isDefaultTable(String tableName) {
        return DEFAULT_TABLES_SET.contains(tableName);
    }

    public static boolean isCustomTable(String tableName) {
        return CUSTOM_TABLES_SET.contains(tableName);
    }
}
