package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色实体持久化接口
 * @author wxy
 * 2020-1-27
 */
@Repository
public interface IRoleRepository extends JpaSpecificationExecutor<Role>, JpaRepository<Role, String> {
    /**
     * 查询角色名
     * @param id
     * @return
     */
    @Query(value = "select name from auth_getaway_role where id =:id ",nativeQuery = true)
    String findByRoleName (@Param("id") String id);
    /**
     * 删除该角色
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_role where id =:id ", nativeQuery = true)
    Integer deleteRole(@Param("id") String id);


}
