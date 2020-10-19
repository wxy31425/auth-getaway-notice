package com.auth.get.away.notice.service.impl;

import com.auth.get.away.notice.controller.vm.MenuVM;
import com.auth.get.away.notice.entity.Menu;
import com.auth.get.away.notice.repository.IMenuRepository;
import com.auth.get.away.notice.repository.IRoleMenuRepository;
import com.auth.get.away.notice.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理IMenuService实现类
 * @author wxy
 * 2020 1-31
 */
@Service
@Slf4j
public class MenuServiceImpl implements IMenuService {
    @Autowired
    IMenuRepository menuRepository;
    @Autowired
    IRoleMenuRepository roleMenuRepository;

    /**
     * 分页查询主菜单
     * @param parentId
     * @param pageable
     * @return
     */
    @Override
    public Page<Menu> findByPage(String parentId,Pageable pageable) {
        Specification<Menu> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(parentId)) {
                predicates.add(builder.equal(root.get("parentId"), parentId));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(builder.desc(root.get("createTime")));
            return query.getRestriction();
        };
        return menuRepository.findAll(specification, pageable);
    }

    /**
     * 显示菜单全部
     * @return
     */
    @Override
    public List<Menu> getMenu() {
        return menuRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));
    }

    @Override
    public List<Map> getRoleMenu(String roleId) {
        return roleMenuRepository.findRoleMenu(roleId);
    }

    /**
     * 保存菜单信息
     * @param menuVM
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object save(MenuVM menuVM) throws SQLException {
        Menu menu = new Menu();
        if(menuVM.getId() == null){
            menuVM.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(menuVM, menu);
        menu  =  menuRepository.save(menu);
        if(menu  == null){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 删除该菜单
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Boolean delete(String id) throws SQLException {
        List<Menu> menus = menuRepository.findByParentId(id);

        if (menus.size() != 0) {
            return false;
        } else {
            int i = menuRepository.deleteMenu(id);
            int j = roleMenuRepository.deleteByMenuId(id);
            if (i < 1 && j < 1) {
                throw new SQLException();
            } else {
                return true;
            }
        }
    }

    /**
     * 菜单禁用
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object disable(List<String> ids) throws SQLException {
        List<String> sonIds = new ArrayList<>();
        int j = 0;
        for(String id :ids){
            sonIds = menuRepository.findBySonId(id);
        }
        if(sonIds.size()!=0){
            j = menuRepository.disableMenuAll(sonIds);
        }
        int i = menuRepository.disableMenuAll(ids);
        if(i < 1&& j < 1 ){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 菜单启用
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object enable(List<String> ids) throws SQLException {
        List<String> sonIds = new ArrayList<>();
        int j = 0;
        for(String id :ids){
            sonIds = menuRepository.findBySonId(id);
        }
        if(sonIds.size()!=0){
            j = menuRepository.enableMenuAll(sonIds);
        }
        int i = menuRepository.enableMenuAll(ids);
        if(i < 1&& j < 1 ){
            throw new SQLException();
        } else {
            return true;
        }
    }
}
