package com.itheima.reggie.dto;

import com.itheima.reggie.entity.Dish;
import lombok.Data;


/**
 * @author: ljs
 * @Pcakage: com.itheima.reggie.dto.DishDto
 * @Date: 2022年06月27日 19:37
 * @Description:
 */
@Data
public class DishDto extends Dish {

    private static final long serialVersionUID = 1L;

    private String categoryName;

}
