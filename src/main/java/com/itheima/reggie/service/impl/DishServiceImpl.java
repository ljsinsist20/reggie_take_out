package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-06-27 16:15:55
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
implements DishService{

    @Autowired
    private DishMapper dishMapper;

    @Override
    public Page<DishDto> page(int page, int pageSize, String name) {
        Page pageInfo = new Page(page, pageSize);
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(StrUtil.isNotEmpty(name), Dish::getName, name);
//        Page pageList = super.page(pageInfo, queryWrapper);
        Page<DishDto> pageList = dishMapper.selectPageMy(pageInfo, name);
        return pageList;
    }
}
