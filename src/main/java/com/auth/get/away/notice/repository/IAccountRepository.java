package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员实体持久化接口
 * @author wxy
 * 2020-1-15
 */
@Repository
public interface IAccountRepository extends JpaSpecificationExecutor<Account>, JpaRepository<Account, String> {
    /**
     * 获取email
     * @param email
     * @return
     */
      Account findByEmail(String email);

    /**
     * 获取username
     * @param username
     * @return
     */
    Account findByUsername(String username);

    /**
     * 设置管理员角色
     * @param roleId
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_admin set role_id = :roleId where id  = :id", nativeQuery = true)
    Integer setRole(@Param("roleId") String roleId ,@Param("id") String id);

    /**
     * 重置管理员角色
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_admin set role_id = NULL where id  = :id", nativeQuery = true)
    Integer resetRole(@Param("id") String id);

    /**
     * 删除管理员账号
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_admin where id =:id", nativeQuery = true)
    Integer deleteAccount(@Param("id") String id);

    /**
     * 根据角色查询管理员账号
     * @param id
     * @return
     */
    @Query(value = "select * from auth_getaway_admin  where role_id regexp(:id)", nativeQuery = true)
    List<Account> findAccount(@Param("id") String id);

    /**
     * 根据用户账号查询账号id
     * @param accountName
     * @return
     */
    @Query(value = "select id from auth_getaway_admin where username = :accountName",nativeQuery = true)
    String findByAccountId(@Param("accountName") String accountName);

    /**
     * 修改用户账号密码
     * @param password
     * @param username
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_admin set password = :password where username  = :username", nativeQuery = true)
    Integer updatePassword(@Param("password") String password,@Param("username") String username);


}
