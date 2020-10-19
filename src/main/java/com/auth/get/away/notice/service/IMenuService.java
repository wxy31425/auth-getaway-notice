package com.auth.get.away.notice.service;

import com.auth.get.away.notice.controller.vm.MenuVM;
import com.auth.get.away.notice.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理IMenuService
 * @author wxy
 * 2020 1-31
 */
public interface IMenuService {

    /**
     * 分页查询菜单信息
     * @param parentId
     * @param pageable
     * @return
     */
    Page<Menu> findByPage(String parentId,Pageable pageable);

    /**
     * 显示菜单全部
     * @return
     */
    List<Menu> getMenu();


    /**
     * 显示角色已有菜单
     * @param roleId
     * @return
     */
    List<Map> getRoleMenu(String roleId);
    /**
     * 保存菜单信息
     * @param menuVM
     * @return
     */
    Object save(MenuVM menuVM) throws SQLException;

    /**
     * 删除该菜单
     * @param id
     * @return
     * @throws SQLException
     */
    Boolean delete(String id) throws SQLException;

    /**
     * 禁用菜单
     * @param ids
     * @return
     */
    Object disable(List<String> ids) throws SQLException;

    /**
     * 启用菜单
     * @param ids
     * @return
     */
    Object enable(List<String> ids) throws SQLException;

}

