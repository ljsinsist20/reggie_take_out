package com.itheima.reggie.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ljs
 * @Pcakage: com.itheima.reggie.controller.DishController
 * @Date: 2022年06月27日 17:24
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public R page(int page, int pageSize, String name) {
        Page<DishDto> pageList = dishService.page(page, pageSize, name);
        return R.success(pageList);
    }

    @GetMapping("/{id}")
    public R queryDishById(@PathVariable Long id) {
        return R.success(dishService.getById(id));
    }
}

