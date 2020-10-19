package com.auth.get.away.notice.service;

import com.auth.get.away.notice.controller.vm.DepartmentVM;
import com.auth.get.away.notice.controller.vm.EmployeesVM;
import com.auth.get.away.notice.entity.DepartmentEmployees;
import com.auth.get.away.notice.entity.Employees;
import com.auth.get.away.notice.service.dto.EmployeesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;

/**
 * 员工管理 IEmployeesService
 * @author wxy
 * 2020 1-22
 */
public interface IEmployeesService {

    /**
     * 分页查询员工信息
     * @param pageable
     * @return
     */
    Page<Employees> findByPage(String departmentId,String keyword, Pageable pageable);


    /**
     * 保存员工新增信息
     * @param employeesVM
     * @return
     */
    Object saveAdd(EmployeesVM employeesVM) throws SQLException;

    /**
     * 保存员工更新信息
     * @param employeesVM
     * @return
     */
    Object saveEdit(EmployeesVM employeesVM) throws SQLException;


    /**
     * 员工详情
     * @param id
     * @return
     */
    EmployeesDTO getEmployees(String id);
}
