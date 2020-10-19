package com.auth.get.away.notice.service;


import com.auth.get.away.notice.entity.Account;
import com.auth.get.away.notice.entity.Login;
import com.auth.get.away.notice.service.dto.AccountDTO;
import com.auth.get.away.notice.service.dto.LoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * 管理员管理IAccountService
 * @author wxy
 * 2020 1-22
 */
public interface IAccountService {
    /**
     * 分页查询管理员信息
     * @param pageable
     * @return
     */
    Page<Login> findByPage(String keyword,Integer status, Pageable pageable);

    /**
     * 获取管理员账号
     * @param username
     * @return
     */
    Account findByUsername(String username);
    /**
     * 获取登录信息状态
     * @param accountId
     * @return
     */
    Login getStatus(String accountId);

    /**
     * 管理员账号保存
     * @param account
     * @return
     */
    Account save(Account account);

    /**
     * 登录保存日志信息
     * @param accountId
     * @return
     */
    Login save(String accountId);
    /**
     * 管理员详情
     * @param id
     * @return
     */
    LoginDTO getAccount(String id);
    /**
     * 启用管理员设置角色
     * @param id
     * @param ids
     * @return
     */
    Object setRole(String id,List<String> ids) throws SQLException;

    /**
     * 启用管理员重置角色
     * @param id
     * @return
     */
    Object resetRole(String id) throws SQLException;

    /**
     * 启用管理员登录用户
     * @param ids
     * @return
     */
    Object enable(List<String> ids) throws SQLException;

    /**
     * 禁用管理员登录用户
     * @param ids
     * @return
     */
    Object disable(List<String> ids) throws SQLException;

    /**
     * 删除管理员登录用户
     * @param id
     * @return
     */
    Boolean delete(String id) throws SQLException;

    /**
     * 获取登录人信息
     * @param username
     * @return
     */
   AccountDTO loadLoginByUserName(String username);

    /**
     * 判断登录信息是否存在
     * @param username
     * @return
     */
   Boolean isExists(String username);
    /**
     * 修改登录账号密码
     * @param username
     * @param password
     * @return
     */
    Object updatePassword(String username,String password) throws SQLException;

}
