package com.auth.get.away.notice.service.impl;

import com.auth.get.away.notice.controller.vm.EmployeesVM;
import com.auth.get.away.notice.entity.Department;
import com.auth.get.away.notice.entity.DepartmentEmployees;
import com.auth.get.away.notice.entity.Employees;
import com.auth.get.away.notice.repository.IDepartmentEmployeesRepository;
import com.auth.get.away.notice.repository.IDepartmentRepository;
import com.auth.get.away.notice.repository.IEmployeesRepository;
import com.auth.get.away.notice.service.IEmployeesService;
import com.auth.get.away.notice.service.dto.EmployeesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.sql.SQLException;
import java.util.*;

/**
 * 员工管理IEmployeesService实现类
 * @author wxy
 * 2020 2-22
 */
@Service
@Slf4j
public class EmployeesServiceImpl implements IEmployeesService {
    @Autowired
    IEmployeesRepository employeesRepository;
    @Autowired
    IDepartmentEmployeesRepository departmentEmployeesRepository;
    @Autowired
    IDepartmentRepository departmentRepository;
    /**
     * 分页查询员工信息
     * @param departmentId
     * @param keyword
     * @param pageable
     * @return
     */
    @Override
    public Page<Employees> findByPage(String departmentId, String keyword, Pageable pageable) {
        String name = null;
        String phone = null;
        if (!StringUtils.isEmpty(departmentId)) {
            List<Department> departments = departmentRepository.findByParentId(departmentId);
            if(departments.size() == 0){
                return employeesRepository.findByDepartmentIdAll(departmentId,pageable);
            }else {
                return employeesRepository.findByDepartmentParentIdAll(departmentId, pageable);
            }
        } else if(!StringUtils.isEmpty(keyword)) {
            boolean result = keyword.matches("[0-9]+");
            if (result == true) {
                phone = keyword;
            } else {
                name = keyword;
            }
            return employeesRepository.findByDepartmentSearch(name,phone,pageable);
        } else {
            return employeesRepository.findByAll(pageable);
        }
    }

    /**
     * 保存员工新增信息
     * @param employeesVM
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object saveAdd(EmployeesVM employeesVM) throws SQLException {
        Employees employees = new Employees();
        DepartmentEmployees departmentEmployees = new DepartmentEmployees();
        if(employeesVM.getId() == null){
            employeesVM.setCreateTime(new Date());
        }
        if("男".equals(employeesVM.getSex())){
          employees.setSex(1);
        } else if("女".equals(employeesVM.getSex())) {
            employees.setSex(0);
        }
        BeanUtils.copyProperties(employeesVM, employees);
        employees  =  employeesRepository.save(employees);
        if(employeesVM.getDepartmentId()!=null && employees.getId()!=null){
            departmentEmployees.setDepartmentId(employeesVM.getDepartmentId());
            departmentEmployees.setEmployeesId(employees.getId());
            BeanUtils.copyProperties(employeesVM, departmentEmployees);
            departmentEmployees  =  departmentEmployeesRepository.save(departmentEmployees);
        }
        if(employees  == null && departmentEmployees ==null){
            throw new SQLException();
        } else {
            return true;
        }
    }

    /**
     * 保存员工更新信息
     * @param employeesVM
     * @return
     * @throws SQLException
     */
    @Override
    @Transactional
    public Object saveEdit(EmployeesVM employeesVM) throws SQLException {
        Employees employees = new Employees();
        DepartmentEmployees departmentEmployeesEdit = new DepartmentEmployees();
        if(employeesVM.getDepartmentId()!=null) {
            departmentEmployeesRepository.deleteDepartmentEmployessById(employeesVM.getId());
            departmentEmployeesEdit.setDepartmentId(employeesVM.getDepartmentId());
            departmentEmployeesEdit.setEmployeesId(employeesVM.getId());
            BeanUtils.copyProperties(employeesVM, departmentEmployeesEdit);
            departmentEmployeesEdit = departmentEmployeesRepository.save(departmentEmployeesEdit);
        }

        if("1".equals(employeesVM.getSex())){
            employees.setSex(1);
        } else if("0".equals(employeesVM.getSex())) {
            employees.setSex(0);
        }
        BeanUtils.copyProperties(employeesVM, employees);
            employees = employeesRepository.save(employees);
                if(employees  == null && departmentEmployeesEdit ==null){
                throw new SQLException();
            } else {
                return true;
            }
        }

    /**
     * 员工信息详情
     * @param id
     * @return
     */
    @Override
    public EmployeesDTO getEmployees(String id) {
        Optional<Employees> employees = employeesRepository.findById(id);
        EmployeesDTO employeesDTO = new EmployeesDTO(employees.get().getId(),employees.get().getNumber(),
                             employees.get().getName(),employees.get().getIdcard(),employees.get().getEmail(),
                             employees.get().getPhone(),employees.get().getAddress(),employees.get().getJob(),
                             employees.get().getUsername(),employees.get().getBirthday());
        String departmentName = departmentRepository.findByDepartmentName(id);
        employeesDTO.addDepartment(departmentName);
        return employeesDTO;
    }
}
