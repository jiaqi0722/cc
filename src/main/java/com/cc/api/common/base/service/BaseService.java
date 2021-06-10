package com.cc.api.common.base.service;


import com.cc.api.common.base.mapper.BaseMapper;
import com.cc.api.common.base.param.DeleteParam;
import com.cc.api.common.constant.Constants;
import com.cc.api.common.base.param.InsertParam;
import com.cc.api.common.base.param.SelectParam;
import com.cc.api.common.base.param.UpdateParam;
import com.cc.api.common.config.TablesConfig;
import com.cc.api.common.util.JwtUtil;
import com.cc.api.common.util.StringUtil;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: BaseService
 */
@Service
public class BaseService {
    @Autowired
    private BaseMapper mapper;

    public BaseService() {
    }

    public List<Map<String, Object>> selectList(SelectParam p) {
        p.addCondition("soft_del = '1'");
        return this.mapper.selectList(p);
    }

    public List<Map<String, Object>> selectListBySql(String sql) {
        return this.mapper.selectListBySql(sql);
    }

    public Map<String, Object> selectOne(SelectParam p) {
        p.addCondition("soft_del = '1'");
        return this.mapper.selectOne(p);
    }

    public Map<String, Object> selectOneBySql(String sql) {
        return this.mapper.selectOneBySql(sql);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(InsertParam p) {
        if (p.getValues().containsKey("id")) {
            p.getValues().remove("id");
        }

        p.addValue("id", StringUtil.getUUID());
        if (p.getValues().containsKey("soft_del")) {
            p.getValues().remove("soft_del");
        }

        p.addValue("soft_del", "1");
        if (TablesConfig.isDefaultTable(p.getTableName())) {
            Date now = new Date();
            p.addValue("create_date", now);
            p.addValue("update_date", now);
        }

        return this.mapper.insert(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public String insertAndReturnId(InsertParam p) {
        return this.insert(p) > 0 ? p.getValues().get("id").toString() : null;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(HttpServletRequest request, InsertParam p) {
        if (p.getValues().containsKey(Constants.ID_COLUMN_NAME)) {
            p.getValues().remove("id");
        }

        p.addValue("id", StringUtil.getUUID());
        if (p.getValues().containsKey("soft_del")) {
            p.getValues().remove("soft_del");
        }

        p.addValue("soft_del", "1");
        if (TablesConfig.isDefaultTable(p.getTableName())) {
            Date now = new Date();
            Claims claims = null;

            try {
                claims = JwtUtil.parseJwtToken(request.getHeader("auth"), Constants.TOKEN_SECRET);
            } catch (Exception var6) {
                var6.printStackTrace();
            }
            assert claims != null;
            p.addValue("create_by", claims.get("user_id"));
            p.addValue("create_user_name", claims.get("user_name"));
            p.addValue("create_real_name", claims.get("real_name"));
            p.addValue("create_date", now);
            p.addValue("update_by", claims.get("user_id"));
            p.addValue("update_date", now);

        }

        return this.mapper.insert(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public String insertAndReturnId(HttpServletRequest request, InsertParam p) {
        return this.insert(request, p) > 0 ? p.getValues().get("id").toString() : null;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertBySql(String sql) {
        return this.mapper.insertBySql(sql);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(UpdateParam p) {
        if (TablesConfig.isDefaultTable(p.getTableName())) {
            Date now = new Date();
            p.addValue("update_date", now);
        }

        return this.mapper.update(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public int update(HttpServletRequest request, UpdateParam p) {
        if (TablesConfig.isDefaultTable(p.getTableName())) {
            Date now = new Date();
            String userId = null;

            try {
                userId = JwtUtil.getCurrentUserId(request, Constants.TOKEN_SECRET);
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            p.addValue("update_date", now);
            p.addValue("update_by", userId.toString());
        }

        return this.mapper.update(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateBySql(String sql) {
        return this.mapper.updateBySql(sql);
    }

    @Transactional(rollbackFor = Exception.class)
    public int logicDelete(UpdateParam p) {
        Date now = new Date();
        p.addValue("update_date", now);
        p.setValues(new HashMap());
        p.addValue("soft_del", "0");
        return this.mapper.update(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public int logicDelete(HttpServletRequest request, UpdateParam p) {
        Date now = new Date();
        String userId = null;

        try {
            userId = JwtUtil.getCurrentUserId(request, Constants.TOKEN_SECRET);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        p.setValues(new HashMap());
        p.addValue("update_date", now);
        p.addValue("soft_del", "0");
        return this.mapper.update(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(DeleteParam p) {
        return this.mapper.delete(p);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteBySql(String sql) {
        return this.mapper.deleteBySql(sql);
    }
}
