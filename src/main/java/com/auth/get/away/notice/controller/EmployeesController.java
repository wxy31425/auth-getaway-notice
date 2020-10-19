package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.controller.vm.EmployeesVM;
import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.entity.Employees;
import com.auth.get.away.notice.service.IEmployeesService;
import com.auth.get.away.notice.service.dto.EmployeesDTO;
import com.boostor.framework.rest.ResposeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * 部门管理
 * @author wxy
 *  2020 2-22
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    IEmployeesService employeesService;

    /**
     * 员工分页查询
     * @param pageVM
     * @return
     */
    @PostMapping("queryList")
    public Page<Employees> queryList(@RequestBody PageVM pageVM) {
        return employeesService.findByPage
                (pageVM.getDepartmentId(),pageVM.getKeyWord(), PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }

    /**
     * 新增修改员工信息
     * @param employeesVM
     * @return
     * @throws SQLException
     */
    @PostMapping("saveEmployees")
    private ResposeStatus saveEmployees(@RequestBody EmployeesVM employeesVM) throws SQLException {
        if(employeesVM.getId()==null){
            employeesService.saveAdd(employeesVM);
        } else {
            employeesService.saveEdit(employeesVM);
        }
        return ResposeStatus.success();
    }

    /**
     * 员工详情信息
     * @return
     */
    @PostMapping("getEmployees")
    public EmployeesDTO getEmployees(@RequestBody String id) {
       return employeesService.getEmployees(id);
    }
}
