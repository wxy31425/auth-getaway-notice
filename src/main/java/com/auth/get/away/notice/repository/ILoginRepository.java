package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 登录日志实体持久化接口
 * @author wxy
 * 2020-1-23
 */
@Repository
public interface ILoginRepository extends JpaSpecificationExecutor<Login>, JpaRepository<Login, String> {

    /**
     * 批量启用管理员
     * @param ids
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_account_login_log set status = 1  where id in(:ids)", nativeQuery = true)
    Integer enableAll(@Param("ids") List<String> ids);

    /**
     * 批量禁用管理员
     * @param ids
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_account_login_log set status = 0  where id in(:ids)", nativeQuery = true)
    Integer disableAll(@Param("ids") List<String> ids);

    /**
     * 删除登录管理员账号
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_account_login_log where account_id =:id", nativeQuery = true)
    Integer deleteLoginAccount(@Param("id") String id);

    /**
     * 更新管理员信息
     * @param ip
     * @param loginTime
     * @param accountId
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_account_login_log set ip =:ip,login_time=:loginTime,status = 1  where account_id =:accountId", nativeQuery = true)
    Integer updateLogin(@Param("ip")String ip, @Param("loginTime") Date loginTime, @Param("accountId") String accountId);

    /**
     * 根据登录管理员Id查询
     * @param accountId
     * @return
     */
    @Query(value = "select id from auth_getaway_account_login_log  where account_id =:accountId", nativeQuery = true)
    String findLogin(@Param("accountId") String accountId);

    /**
     * 判断登录信息是否存在
     * @param userName
     * @return
     */
    @Query(value = "select lo.status from auth_getaway_account_login_log lo \n" +
            "left join auth_getaway_admin ad on ad.id = lo.account_id\n" +
            "where ad.username=:userName", nativeQuery = true)
    Boolean isExists(@Param("userName") String userName);

    /**
     * 获取登录信息状态
     * @param accountId
     * @return
     */
    Login findByAccountId(String accountId);



}
