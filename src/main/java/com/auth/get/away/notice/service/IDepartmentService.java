package com.auth.get.away.notice.service;

import com.auth.get.away.notice.controller.vm.DepartmentVM;
import com.auth.get.away.notice.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;

/**
 * 部门管理IAccountService
 * @author wxy
 * 2020 2-15
 */
public interface IDepartmentService {

    /**
     * 分页查询部门信息
     * @param parentId
     * @param pageable
     * @return
     */
    Page<Department> findByPage(String parentId, Pageable pageable);
    /**
     * 保存部门信息
     * @param departmentVM
     * @return
     */
    Object save(DepartmentVM departmentVM) throws SQLException;

    /**
     * 显示菜单全部
     * @return
     */
    List<Department> getDepartment();


    /**
     * 删除该部门
     * @param id
     * @return
     * @throws SQLException
     */
    Boolean delete(String id) throws SQLException;

    /**
     * 禁用部门
     * @param ids
     * @return
     */
    Object disable(List<String> ids) throws SQLException;

    /**
     * 启用部门
     * @param ids
     * @return
     */
    Object enable(List<String> ids) throws SQLException;


}
