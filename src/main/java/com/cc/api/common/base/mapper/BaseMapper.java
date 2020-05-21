package com.cc.api.common.base.mapper;


import java.util.List;
import java.util.Map;

import com.cc.api.common.base.param.DeleteParam;
import com.cc.api.common.base.param.InsertParam;
import com.cc.api.common.base.param.SelectParam;
import com.cc.api.common.base.param.UpdateParam;
import com.cc.api.common.base.provider.SqlProviderProxy;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
@Mapper
public interface BaseMapper {
    @SelectProvider(type = SqlProviderProxy.class, method = "proxySelectSql")
    List<Map<String, Object>> selectList(SelectParam paramSelectParam);

    @SelectProvider(type = SqlProviderProxy.class, method = "proxySql")
    List<Map<String, Object>> selectListBySql(String paramString);

    @SelectProvider(type = SqlProviderProxy.class, method = "proxySelectSql")
    Map<String, Object> selectOne(SelectParam paramSelectParam);

    @SelectProvider(type = SqlProviderProxy.class, method = "proxySql")
    Map<String, Object> selectOneBySql(String paramString);

    @InsertProvider(type = SqlProviderProxy.class, method = "proxyInsertSql")
    int insert(InsertParam paramInsertParam);

    @InsertProvider(type = SqlProviderProxy.class, method = "proxySql")
    int insertBySql(String paramString);

    @UpdateProvider(type = SqlProviderProxy.class, method = "proxyUpdateSql")
    int update(UpdateParam paramUpdateParam);

    @UpdateProvider(type = SqlProviderProxy.class, method = "proxySql")
    int updateBySql(String paramString);

    @DeleteProvider(type = SqlProviderProxy.class, method = "generateDeleteSql")
    int delete(DeleteParam paramDeleteParam);

    @DeleteProvider(type = SqlProviderProxy.class, method = "proxySql")
    int deleteBySql(String paramString);

    @SelectProvider(type = SqlProviderProxy.class, method = "generateSelectTablesSql")
    List<Map<String, Object>> selectTables();

    @SelectProvider(type = SqlProviderProxy.class, method = "generateShowTableStructureSql")
    List<Map<String, Object>> showTableStructure(String paramString);
}

