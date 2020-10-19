package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工实体持久化接口
 * @author wxy
 * 2020-2-19
 */
@Repository
public interface IEmployeesRepository extends JpaSpecificationExecutor<Employees>, JpaRepository<Employees, String> {
    /**
     * 分页查询员工全部显示
     * @param pageable
     * @return
     */
    @Query(value = "select e.* from  auth_getaway_employees e\n" +
            "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
            "left join auth_getaway_department d on d.id = de.department_id\n",
            countQuery = "select count(*) from  auth_getaway_employees e\n" +
                    "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
                    "left join auth_getaway_department d on d.id = de.department_id",nativeQuery = true)
    Page<Employees> findByAll(Pageable pageable);

    /**
     * 根据部门Id分页查询员工显示
     * @param departmentId
     * @param pageable
     * @return
     */
    @Query(value = "select e.* from  auth_getaway_employees e \n" +
            "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
            "left join auth_getaway_department d on d.id = de.department_id\n" +
            "where d.id=:departmentId",
            countQuery = "select count(*) from  auth_getaway_employees e\n" +
                    "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
                    "left join auth_getaway_department d on d.id = de.department_id\n" +
                    "where d.id=:departmentId",nativeQuery = true)
    Page<Employees> findByDepartmentIdAll(@Param("departmentId") String departmentId, Pageable pageable);

    /**
     * 根据查询条件查询分页员工信息
     * @param name
     * @param phone
     * @param pageable
     * @return
     */
    @Query(value = "select e.* from  auth_getaway_employees e \n" +
            "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
            "left join auth_getaway_department d on d.id = de.department_id\n" +
            "where e.name like CONCAT('%',:name,'%') or e.phone like CONCAT('%',:phone,'%')",
            countQuery = "select count(*) from  auth_getaway_employees e\n" +
                    "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
                    "left join auth_getaway_department d on d.id = de.department_id\n" +
                    "where e.name like CONCAT('%',:name,'%') or e.phone like CONCAT('%',:phone,'%')",nativeQuery = true)
    Page<Employees> findByDepartmentSearch(@Param("name") String name,@Param("phone") String phone,Pageable pageable);

    /**
     * 根据部门父类Id分页查询员工显示
     * @param departmentId
     * @param pageable
     * @return
     */
    @Query(value = "select e.* from  auth_getaway_employees e \n" +
            "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
            "left join auth_getaway_department d on d.id = de.department_id\n" +
            "where d.parent_id=:departmentId",
            countQuery = "select count(*) from auth_getaway_employees e\n" +
                    "left join auth_getaway_department_employees de on de.employees_id = e.id\n" +
                    "left join auth_getaway_department d on d.id = de.department_id\n" +
                    "where d.parent_id=:departmentId" ,nativeQuery = true)
    Page<Employees> findByDepartmentParentIdAll(@Param("departmentId") String departmentId, Pageable pageable);

    /**
     * 根据员工Id查询记录
     * @param id
     * @return
     */
    @Query(value = "select id from auth_getaway_employees  where id =:id", nativeQuery = true)
    List<Employees> findByEmployeesId(String id);




}
