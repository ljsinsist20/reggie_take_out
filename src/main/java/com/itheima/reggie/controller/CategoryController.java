package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: ljs
 * @Pcakage: com.itheima.reggie.controller.CategoryController
 * @Date: 2022年06月27日 13:14
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/page")
    public R page(int page, int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);
        Page<Category> pageList = categoryService.page(pageInfo);
        return R.success(pageList);
    }

    @PostMapping
    public R save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    @DeleteMapping
    public R delete(Long id) {
        categoryService.remove(id);
        return R.success("分类信息删除成功");
    }

    @PutMapping
    public R update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    @GetMapping("/list")
    public R list(int type) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getType, type);
        List<Category> categoryList = categoryService.list(queryWrapper);
        return R.success(categoryList);
    }
}
