package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.DepartmentEmployees;
import com.auth.get.away.notice.entity.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 部门员工实体持久化接口
 * @author wxy
 * 2020-2-19
 */
@Repository
public interface IDepartmentEmployeesRepository extends JpaSpecificationExecutor<DepartmentEmployees>, JpaRepository<DepartmentEmployees, String> {

    /**
     * 删除该部门员工
     * @param employeesId
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_department_employees where employees_id =:employeesId", nativeQuery = true)
    Integer deleteDepartmentEmployessById(@Param("employeesId") String employeesId);
}
