package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-06-27 16:15:55
* @Entity generator.domain.Dish
*/
public interface DishMapper extends BaseMapper<Dish> {

    @Select("<script>" +
            "select d.id, d.name,d.price, d.code, d.image, d.description, d.status, d.sort, c.name as category_name " +
            "from dish d,category c " +
            "where d.category_id = c.id" +
            "<if test='name != null'>d.name=#{name}</if>" +
            "</script>")
    Page<DishDto> selectPageMy(Page pageInfo, @Param("name") String name);
}
