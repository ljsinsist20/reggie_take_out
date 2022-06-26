package com.itheima.reggie.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ljs
 * @Pcakage: com.itheima.reggie.controller.EmployeeController
 * @Date: 2022年06月26日 16:02
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    private R login(@RequestBody Employee employee) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", employee.getUsername());
        //根据用户名查询
        Employee queryEmployee = employeeService.getOne(queryWrapper);
        if (queryEmployee == null) {
            return R.error("该用户不存在");
        }
        //对比密码
        employee.setPassword(DigestUtil.md5Hex(employee.getPassword()));
        if (!employee.getPassword().equals(queryEmployee.getPassword())) {
            return R.error("密码错误");
        }
        //状态识别
        if (queryEmployee.getStatus() == 0) {
            return R.error("该用户已被禁用");
        }
        // TODO request.getSession().setAttribute("employee", employee.getId())
        return R.success(queryEmployee);
    }


    @PostMapping("/logout")
    private R logout() {
        return R.success("退出成功");
    }
}
