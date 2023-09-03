package com.nott.common;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Nott
 * @Date 2023/8/10
 */


@RestController
public abstract class CommonController<T extends IService<M>, M> {

    @Autowired
    private T service;
    @Autowired
    private CommonPageService<M> commonPageService;


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("saveOrUpdate")
    public R save(@RequestBody JSONObject req) throws Exception {
        Class<M> entityClass = service.getEntityClass();
        M m = entityClass.newInstance();
        Field[] declaredFields = entityClass.getDeclaredFields();

        Set<String> set = req.keySet();
        if (set.isEmpty()) {
            throw new RuntimeException("属性不能为空");
        }
        HashMap<Field, Object> map = new HashMap<>();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Field field = Arrays.stream(declaredFields).filter(r -> key.equals(r.getName())).findAny().orElse(null);
            if (field == null) {
                continue;
            }
            map.put(field, req.get(key));
        }

        if (!map.isEmpty()) {
            Set<Field> fields = map.keySet();
            Iterator<Field> fieldIterator = fields.iterator();
            while (fieldIterator.hasNext()) {
                Field next = fieldIterator.next();
                Object val = map.get(next);
                PropertyDescriptor descriptor = new PropertyDescriptor(next.getName(), entityClass);
                Method writeMethod = descriptor.getWriteMethod();
                writeMethod.invoke(m, val);
            }
            service.saveOrUpdate(m);
        }
        return R.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/delById/{id}")
    public R delById(@NotNull @PathVariable String id) {
        this.service.removeById(id);
        return R.ok();
    }

    @RequestMapping("/getById/{id}")
    public R getById(@NotNull @PathVariable String id) {
        M m = this.service.getById(id);
        return R.okData(m);
    }

    @PostMapping("/page")
    public R page(@RequestBody JSONObject req) throws Exception {
        QueryWrapper<M> wrapper = commonPageService.initMbpWrapper(req);
        IPage<M> page = service.page(new Page<>(), wrapper);
        return R.okData(page);
    }
}
