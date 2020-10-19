package com.auth.get.away.notice.service.impl;


import com.auth.get.away.notice.controller.vm.RoleVM;
import com.auth.get.away.notice.entity.Account;
import com.auth.get.away.notice.entity.Role;
import com.auth.get.away.notice.entity.RoleMenu;
import com.auth.get.away.notice.repository.IAccountRepository;
import com.auth.get.away.notice.repository.IRoleMenuRepository;
import com.auth.get.away.notice.repository.IRoleRepository;
import com.auth.get.away.notice.service.IRoleService;
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

/**
 * 角色管理IRoleService实现类
 * @author wxy
 * 2020 1-27
 */
@Service
@Slf4j
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    IRoleMenuRepository roleMenuRepository;

    /**
     * 分页查询角色信息
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<Role> findByPage(String keyword, Pageable pageable) {
        Specification<Role> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(keyword)) {
                predicates.add(builder.like(root.get("name"), "%" + keyword + "%"));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(builder.desc(root.get("createTime")));
            return query.getRestriction();
        };
        return roleRepository.findAll(specification, pageable);
    }

    /**
     * 保存角色信息
     * @param roleVM
     * @return
     */
    @Transactional
    @Override
    public Object save(RoleVM roleVM) throws SQLException {
        Role role = new Role();
        if(roleVM.getId() == null){
            roleVM.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(roleVM, role);
        role  =  roleRepository.save(role);
        if(role  == null){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 角色设置菜单
     * @param id
     * @param ids
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public Object setMenu(String id, List<String> ids) throws SQLException {
        if(!StringUtils.isEmpty(id)) {
             roleMenuRepository.deleteByRoleId(id);
                for (String menuId : ids) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setRoleId(id);
                    roleMenu.setMenuId(menuId);
                    roleMenuRepository.save(roleMenu);
                    if (roleMenu == null) {
                        throw new SQLException();
                    }
                }
            }
        return true;
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     * @throws SQLException
     */
    @Transactional
    @Override
    public Boolean delete(String id) throws SQLException {
        List<Account> account = accountRepository.findAccount(id);
        if(account.size()!=0){
            return false;
        } else {
            int i = roleRepository.deleteRole(id);
            int j = roleMenuRepository.deleteByRoleId(id);
            if(i < 1 && j < 1){
                throw new SQLException();
            } else {
                return true;
            }
        }
    }
}
