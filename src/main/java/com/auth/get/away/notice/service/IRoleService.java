package com.auth.get.away.notice.service;

import com.auth.get.away.notice.controller.vm.RoleVM;
import com.auth.get.away.notice.entity.Login;
import com.auth.get.away.notice.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 角色管理IRoleService
 * @author wxy
 * 2020 1-27
 */
public interface IRoleService {

    /**
     * 分页查询角色信息
     * @param pageable
     * @return
     */
    Page<Role> findByPage(String keyword, Pageable pageable);


    /**
     * 保存角色信息
     * @param roleVM
     * @return
     */
    Object save(RoleVM roleVM) throws SQLException;

    /**
     * 角色设置菜单
     * @param id
     * @param ids
     * @return
     */
    Object setMenu(String id,List<String> ids) throws SQLException;

    /**
     * 删除该角色
     * @param id
     * @return
     * @throws SQLException
     */
    Boolean delete(String id) throws SQLException;
}
