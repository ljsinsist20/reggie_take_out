package com.itheima.reggie.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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
    private R login(HttpServletRequest request, @RequestBody Employee employee) {
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
        request.getSession().setAttribute("employee", queryEmployee.getId());
        return R.success(queryEmployee);
    }


    @PostMapping("/logout")
    private R logout(HttpServletRequest request) {
        request.getSession().setAttribute("employee", null);
        return R.success("退出成功");
    }

    @PostMapping
    private R addEmployee(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}",employee.toString());
        employee.setPassword(DigestUtil.md5Hex("123456"));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    private R getEmployeesPage(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}" ,page,pageSize,name);
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    private R putEmployeeStatus(@RequestBody Employee employee) {
        boolean flag = employeeService.updateById(employee);
        if (flag) {
            return R.success("更新成功");
        }
        return R.error("更新失败");
    }

    @GetMapping("/{id}")
    private R employeeById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }
}
