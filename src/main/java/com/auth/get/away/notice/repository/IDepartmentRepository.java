package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Department;
import com.auth.get.away.notice.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门实体持久化接口
 * @author wxy
 * 2020-2-05
 */
@Repository
public interface IDepartmentRepository extends JpaSpecificationExecutor<Department>, JpaRepository<Department, String> {

    /**
     * 根据部门主Id查询下级
     * @param id
     * @return
     */
    List<Department> findByParentId(String id);

    /**
     * 根据员工Id查询部门名称
     * @param
     * @return
     */
    @Query(value = "select d.name from  auth_getaway_department d\n" +
            "       left join auth_getaway_department_employees de on de.department_id" +
            "" +
            " = d.id\n" +
            "       where de.employees_id =:employeesId",nativeQuery = true)
    String findByDepartmentName(@Param("employeesId") String employeesId);

    /**
     * 根据部门主Id查询下级子Id
     * @param id
     * @return
     */
    @Query(value = "select id from auth_getaway_department  where parent_id =:id", nativeQuery = true)
    List<String> findBySonId(@Param("id") String id);

    /**
     * 删除该菜单
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from auth_getaway_department where id =:id", nativeQuery = true)
    Integer deleteDepartment(@Param("id") String id);

    /**
     * 批量启用部门
     * @param ids
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_department set status = 1  where id in(:ids)", nativeQuery = true)
    Integer enableAll(@Param("ids") List<String> ids);

    /**
     * 批量禁用部门
     * @param ids
     * @return
     */
    @Modifying
    @Query(value = "update auth_getaway_department set status = 0  where id in(:ids)", nativeQuery = true)
    Integer disableAll(@Param("ids") List<String> ids);




}
