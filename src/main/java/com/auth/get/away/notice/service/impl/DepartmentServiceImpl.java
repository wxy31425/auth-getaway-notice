package com.auth.get.away.notice.service.impl;

import com.auth.get.away.notice.controller.vm.DepartmentVM;
import com.auth.get.away.notice.entity.Department;
import com.auth.get.away.notice.repository.IDepartmentRepository;
import com.auth.get.away.notice.service.IDepartmentService;
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

/**
 * 部门管理IDepartmentService实现类
 * @author wxy
 * 2020 2-05
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements IDepartmentService {
   @Autowired
    IDepartmentRepository departmentRepository;

    /**
     * 分页查询主菜单
     * @param parentId
     * @param pageable
     * @return
     */
    @Override
    public Page<Department> findByPage(String parentId, Pageable pageable) {
        Specification<Department> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(parentId)) {
                predicates.add(builder.equal(root.get("parentId"), parentId));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            return query.getRestriction();
        };
        return departmentRepository.findAll(specification, pageable);
    }

    /**
     * 显示部门全部
     * @return
     */
    @Override
    public List<Department> getDepartment() {
        return departmentRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));
    }



    /**
     * 保存部门信息
     * @param departmentVM
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object save(DepartmentVM departmentVM) throws SQLException {
        Department department = new Department();
        if(departmentVM.getId() == null){
            departmentVM.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(departmentVM, department);
        department  =  departmentRepository.save(department);
        if(department  == null){
            throw new SQLException();
        } else {
            return true;
        }
    }
    /**
     * 删除该部门
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Boolean delete(String id) throws SQLException {
        List<Department> departments = departmentRepository.findByParentId(id);
        if (departments.size() != 0) {
            return false;
        } else {
            int i = departmentRepository.deleteDepartment(id);
            if (i < 1) {
                throw new SQLException();
            } else {
                return true;
            }
        }
    }

    /**
     * 禁用部门
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
            sonIds = departmentRepository.findBySonId(id);
        }
        if(sonIds.size()!=0){
            j = departmentRepository.disableAll(
                    sonIds);
        }
        int i = departmentRepository.disableAll(ids);
        if(i < 1&& j < 1 ){
            throw new SQLException();
        } else {
            return true;
        }
    }
    /**
     * 启用部门
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
             sonIds = departmentRepository.findBySonId(id);
        }
        if(sonIds.size()!=0){
            j = departmentRepository.enableAll(sonIds);
        }
        int i = departmentRepository.enableAll(ids);
        if(i < 1 && j < 1 ){
            throw new SQLException();
        } else {
            return true;
        }
    }
}
