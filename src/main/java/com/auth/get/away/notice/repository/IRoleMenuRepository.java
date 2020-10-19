package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Menu;
import com.auth.get.away.notice.entity.Role;
import com.auth.get.away.notice.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单实体持久化接口
 * @author wxy
 * 2020-1-31
 */
@Repository
public interface IRoleMenuRepository extends JpaSpecificationExecutor<RoleMenu>, JpaRepository<RoleMenu, String> {
    /**
     * 根据角色Id查询拥有的菜单
     * @param roleId
     * @return
     */
    @Query(value = "select m.id,m.name,m.parent_id,m.icon,m.url from auth_getaway_menu m\n" +
            "left join  auth_getaway_role_menu rm on m.id = rm.menu_id\n" +
            "left join  auth_getaway_role r on rm.role_id = r.id\n" +
            "where r.id = :roleId and m.status = 1 order by m.seq asc " ,nativeQuery = true)
    List<Map> findRoleMenu(@Param("roleId") String roleId);

    /**
     * 根据角色删除
     * @param id
     * @return
     */
    Integer deleteByRoleId(String id);

    /**
     * 根据菜单删除
     * @param id
     * @return
     */
    Integer deleteByMenuId(String id);
}
