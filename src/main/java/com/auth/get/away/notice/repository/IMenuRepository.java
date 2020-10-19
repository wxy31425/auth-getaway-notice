package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Account;
import com.auth.get.away.notice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单实体持久化接口
 * @author wxy
 * 2020-1-31
 */
@Repository
public interface IMenuRepository extends JpaSpecificationExecutor<Menu>, JpaRepository<Menu, String> {

    /**
     * 根据菜单主Id查询下级
     * @param id
     * @return
     */
    List<Menu> findByParentId(String id);

    /**
     * 根据菜单主Id查询下级子Id
     * @param id
     * @return
     */
    @Query(value = "select id from auth_getaway_menu  where parent_id =:id", nativeQuery = true)
    List<String> findBySonId(@Param("id") String id);


    /**
     * 删除该菜单
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_menu where id =:id", nativeQuery = true)
    Integer deleteMenu(@Param("id") String id);

    /**
     * 批量启用菜单
     * @param ids
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_menu set status = 1  where id in(:ids)", nativeQuery = true)
    Integer enableMenuAll(@Param("ids") List<String> ids);

    /**
     * 批量禁用菜单
     * @param ids
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_menu set status = 0  where id in(:ids)", nativeQuery = true)
    Integer disableMenuAll(@Param("ids") List<String> ids);




}
