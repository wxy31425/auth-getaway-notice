package com.auth.get.away.notice.service.impl;


import com.auth.get.away.notice.core.TreeNode;
import com.auth.get.away.notice.entity.Account;
import com.auth.get.away.notice.entity.Login;
import com.auth.get.away.notice.entity.Role;
import com.auth.get.away.notice.repository.*;
import com.auth.get.away.notice.service.IAccountService;
import com.auth.get.away.notice.service.dto.AccountDTO;
import com.auth.get.away.notice.service.dto.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理员管理IAccountService实现类
 * @author wxy
 * 2020 1-22
 */
@Service
@Slf4j
public class AccountServiceImpl implements IAccountService {
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    ILoginRepository loginRepository;
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IRoleMenuRepository roleMenuRepository;

    /**
     * 分页查询管理员信息
     * @param keyword
     * @param status
     * @param pageable
     * @return
     */
    @Override
    public Page<Login> findByPage(String keyword,Integer status, Pageable pageable) {
        Specification<Login> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(status)) {
                predicates.add(builder.equal(root.get("status"), status));
            }
            if (!StringUtils.isEmpty(keyword)) {
                predicates.add(builder.like(root.get("account").get("phone"), "%" + keyword + "%"));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(builder.desc(root.get("account").get("createTime")));
            return query.getRestriction();
        };
        return loginRepository.findAll(specification, pageable);
    }

    /**
     * 获取管理员账号
     * @param username
     * @return
     */
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByEmail(username);
    }
    /**
     * 获取登录信息状态
     * @param accountId
     * @return
     */
    @Override
    public Login getStatus(String accountId) {
        return loginRepository.findByAccountId(accountId);
    }

    /**
     * 管理员账号保存
     * @param account
     * @return
     */
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    /**
     * 登录保存日志信息
     * @param accountId
     * @return
     */
    @Transactional
    @Override
    public Login save(String accountId) {
        Login login = new Login();
        Account account = new Account();
        String loginId = accountId;
        String ip = "127.0.0.1";
        Date loginTime = new Date();
        account.setId(loginId);
        login.setAccount(account);
        login.setIp(ip);
        login.setLoginTime(loginTime);
        if(!StringUtils.isEmpty(loginRepository.findLogin(accountId))){
             loginRepository.updateLogin(ip,loginTime,accountId);
        } else {
            return loginRepository.save(login);
        }
        return null;
    }

    /**
     * 管理员详情
     * @param id
     * @return
     */
    @Override
    public LoginDTO getAccount(String id) {
        Optional<Login> login = loginRepository.findById(id);
        LoginDTO loginDTO = new LoginDTO(login.get().getId(),
                login.get().getAccount().getEmail(),login.get().getAccount().getPhone(),
                 login.get().getStatus(),login.get().getIp(),login.get().getLoginTime());
        String [] stringList = login.get().getAccount().getRoleId().split(",");
        List<Map> roleList = new ArrayList<Map>();
        for(String roleId :stringList){
          Optional<Role> role = roleRepository.findById(roleId);
           Map map = new HashMap();
           map.put("roleName",role.get().getName());
           roleList.add(map);
        }
        loginDTO.addRoles(roleList);
        return loginDTO;
    }

    /**
     * 管理员设置角色
     * @param id
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object setRole(String id,List<String> ids) throws SQLException {
        String roleId = ids.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
        int i =accountRepository.setRole(roleId,id);
        if(i < 1){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 管理员重置角色
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object resetRole(String id) throws SQLException {
        int i =accountRepository.resetRole(id);
        if(i < 1){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 启用管理员登录用户
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Object enable(List<String> ids) throws SQLException {
        int i =loginRepository.enableAll(ids);
        if(i < 1){
            throw new SQLException();
        } else {
            return true;
        }
    }
    /**
     * 禁用管理员登录用户
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Object disable(List<String> ids) throws SQLException {
        int i = loginRepository.disableAll(ids);
        if(i < 1){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 删除管理员用户
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Boolean delete(String id) throws SQLException {
          Optional<Account> account = accountRepository.findById(id);
          if(!StringUtils.isEmpty(account.get().getRoleId())){
              return false;
          } else {
              int i = accountRepository.deleteAccount(id);
              int j = loginRepository.deleteLoginAccount(id);
              if(i < 1 && j < 1){
                  throw new SQLException();
              } else {
                  return true;
              }
          }
    }

    /**
     * 获取登录人信息以及权限
     * @param username
     * @return
     */
    @Override
    public AccountDTO loadLoginByUserName(String username) {
        AccountDTO accountDTO = new AccountDTO();
        Account account = accountRepository.findByUsername(username);
        String roleName = roleRepository.findByRoleName(account.getRoleId());
        List<Map> roleMenuList = roleMenuRepository.findRoleMenu(account.getRoleId());
        List<TreeNode> nodes = getChildren(roleMenuList, "0");
         accountDTO.setAccountId(account.getId());
         accountDTO.setEmail(account.getEmail());
         accountDTO.setRoleName(roleName);
         accountDTO.setAuthorities(nodes);
         return accountDTO;
    }

    /**
     * 判断登录信息是否存在
     * @param username
     * @return
     */
    @Override
    public Boolean isExists(String username) {
        Boolean flag = loginRepository.isExists(username);
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改登录账号密码
     * @param username
     * @param password
     * @return
     */
    @Transactional
    @Override
    public Object updatePassword(String username, String password) throws SQLException {
        int i = accountRepository.updatePassword(password,username);
        if(i < 1){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 查询子菜单
     * @param list
     * @param pid
     * @return
     */
    private List<TreeNode> getChildren(List<Map> list, String pid) {
        List<TreeNode> menus = new ArrayList<TreeNode>();
        for (Map<String, Object> map : list) {
            String menuId = map.get("id").toString();
            String parentId = map.get("parent_id").toString();
            String menuName = map.get("name").toString();
            String url = map.get("url").toString();
            String icon = (map.get("icon").toString());
            if (parentId.equals(pid)) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(menuId);
                treeNode.setName(menuName);
                treeNode.setUrl(url);
                treeNode.setIcon(icon);
                menus.add(treeNode);
                List<TreeNode> children = getChildren(list, menuId); // 父级 GUID
                if (children != null && children.size() > 0) {
                    treeNode.setChildren(children);
                }
            }
        }
        return menus;
    }

}
