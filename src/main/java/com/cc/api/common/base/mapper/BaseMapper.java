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
 * @author 特昂唐  2020/6/1  06:01
 * describe: BaseMapper
 */
@Mapper
public interface BaseMapper {

    /**
     * selectList
     * @param paramSelectParam SelectParam
     * @return list
     */
    @SelectProvider(type = SqlProviderProxy.class, method = "proxySelectSql")
    List<Map<String, Object>> selectList(SelectParam paramSelectParam);

    /**
     * selectListBySql
     * @param paramString String
     * @return list
     */
    @SelectProvider(type = SqlProviderProxy.class, method = "proxySql")
    List<Map<String, Object>> selectListBySql(String paramString);

    /**
     * selectOne
     * @param paramSelectParam SelectParam
     * @return map
     */
    @SelectProvider(type = SqlProviderProxy.class, method = "proxySelectSql")
    Map<String, Object> selectOne(SelectParam paramSelectParam);

    /**
     * selectOneBySql
     * @param paramString String
     * @return map
     */
    @SelectProvider(type = SqlProviderProxy.class, method = "proxySql")
    Map<String, Object> selectOneBySql(String paramString);

    /**
     * insert
     * @param paramInsertParam InsertParam
     * @return int
     */
    @InsertProvider(type = SqlProviderProxy.class, method = "proxyInsertSql")
    int insert(InsertParam paramInsertParam);

    /**
     * insertBySql
     * @param paramString String
     * @return int
     */
    @InsertProvider(type = SqlProviderProxy.class, method = "proxySql")
    int insertBySql(String paramString);

    /**
     * update
     * @param paramUpdateParam UpdateParam
     * @return int
     */
    @UpdateProvider(type = SqlProviderProxy.class, method = "proxyUpdateSql")
    int update(UpdateParam paramUpdateParam);

    /**
     * updateBySql
     * @param paramString String
     * @return int
     */
    @UpdateProvider(type = SqlProviderProxy.class, method = "proxySql")
    int updateBySql(String paramString);

    /**
     * delete
     * @param paramDeleteParam DeleteParam
     * @return int
     */
    @DeleteProvider(type = SqlProviderProxy.class, method = "generateDeleteSql")
    int delete(DeleteParam paramDeleteParam);

    /**
     * deleteBySql
     * @param paramString String
     * @return int
     */
    @DeleteProvider(type = SqlProviderProxy.class, method = "proxySql")
    int deleteBySql(String paramString);

    /**
     * selectTables
     * @return list
     */
    @SelectProvider(type = SqlProviderProxy.class, method = "generateSelectTablesSql")
    List<Map<String, Object>> selectTables();

    /**
     * showTableStructure
     * @param paramString String
     * @return list
     */
    @SelectProvider(type = SqlProviderProxy.class, method = "generateShowTableStructureSql")
    List<Map<String, Object>> showTableStructure(String paramString);
}

