package com.cc.api.common.base.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.cc.api.common.annotation.Login;
import com.cc.api.common.base.param.InsertParam;
import com.cc.api.common.base.param.SelectParam;
import com.cc.api.common.base.param.UpdateParam;
import com.cc.api.common.base.service.BaseService;
import com.cc.api.common.config.TablesConfig;
import com.cc.api.common.constant.Constants;
import com.cc.api.common.response.ResponseBean;
import com.cc.api.common.response.ResponsePageBean;
import com.cc.api.common.response.ResponseStatus;
import com.cc.api.common.util.EncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: CRUDController
 */
@RestController
@RequestMapping({"/api"})
public class CRUDController {

    @Autowired
    private TablesConfig tablesConfig;
    @Autowired
    private BaseService service;

    public CRUDController() {
    }

    @Login
    @PostMapping
    public ResponseBean<Object> post(HttpServletRequest request, @RequestBody(required = false) Map<String, Object> item) throws IOException {
        String tableName = (String)this.tablesConfig.getMap().get(item.get("t").toString());
        item.remove("t");
        InsertParam p = new InsertParam();
        p.setTableName(tableName);
        Map<String, Object> values = new HashMap();
        item.forEach((k, v) -> {
            if (!"id".equals(k.toLowerCase())) {
                values.put(k, v);
            }

        });
        p.setValues(values);
        Object result = this.service.insertAndReturnId(request, p);

        return new ResponseBean<>(ResponseStatus.SUCCESS, result);
    }

    @Login
    @PutMapping
    public ResponseBean<Object> put(HttpServletRequest request, @RequestBody(required = false) Map<String, Object> item) throws IOException {
        String tableName = (String)this.tablesConfig.getMap().get(item.get("t").toString());
        item.remove("t");
        Object result = null;
        UpdateParam p = new UpdateParam();
        p.setTableName(tableName);
        item.forEach((k, v) -> {
            if ("id".equals(k.toLowerCase())) {
                p.addUniqueCondition(v.toString());
            } else {
                p.addValue(k, v);
            }

        });
        int row = this.service.update(request, p);
        if (row > 0) {
            result = item.get(Constants.ID_COLUMN_NAME).toString();
        }
        return new ResponseBean<>(ResponseStatus.SUCCESS, result);
    }

    @Login
    @DeleteMapping
    public ResponseBean<Integer> delete(HttpServletRequest request) throws IOException {
        String tableName = (String)this.tablesConfig.getMap().get(request.getParameter("t"));
        String encodeId = request.getParameter(Constants.ID_COLUMN_NAME);
        String id = new String((new BASE64Decoder()).decodeBuffer(encodeId));
        UpdateParam p = new UpdateParam();
        p.setTableName(tableName);
        p.addUniqueCondition(id);
        int result = this.service.logicDelete(request, p);
        return new ResponseBean<>(ResponseStatus.SUCCESS, result);
    }

    @Login
    @GetMapping
    public ResponseBean<Object> get(HttpServletRequest request) throws IOException {
        String flag = request.getParameter("f");
        String tableName = (String)this.tablesConfig.getMap().get(request.getParameter("t"));
        String encodeColumns = request.getParameter("cl");
        String columns = StringUtils.isBlank(encodeColumns) ? "*" : new String((new BASE64Decoder()).decodeBuffer(encodeColumns));
        String encodeConditions = request.getParameter("cd");
        String conditions = StringUtils.isBlank(encodeConditions) ? null : EncodeUtil.decodeUrl(new String((new BASE64Decoder()).decodeBuffer(encodeConditions)));
        String encodeSort = request.getParameter("s");
        String sort = StringUtils.isBlank(encodeSort) ? null : new String((new BASE64Decoder()).decodeBuffer(encodeSort));
        SelectParam p = new SelectParam();
        p.setTableName(tableName);
        p.setColumns(columns);
        if (StringUtils.isNotBlank(conditions)) {
            p.setConditions(new ArrayList(Arrays.asList(conditions.split(","))));
        }

        p.setSort(sort);
        Object result = null;
        if ("1".equals(flag)) {
            result = this.service.selectOne(p);
        } else if ("2".equals(flag)) {
            result = this.service.selectList(p);
        } else if ("3".equals(flag)) {
            Integer size = this.service.selectList(p).size();
            Integer pageSize = Integer.parseInt(request.getParameter("ps"));
            Integer pageNo = Integer.parseInt(request.getParameter("pn"));
            p.setPageSize(pageSize);
            p.setPageNo(pageNo);
            result = this.service.selectList(p);
            return new ResponsePageBean<>(ResponseStatus.SUCCESS, result, size);
        }

        return new ResponseBean<>(ResponseStatus.SUCCESS, result);
    }
}