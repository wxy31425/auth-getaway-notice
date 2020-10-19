package com.auth.get.away.notice.repository;

import com.auth.get.away.notice.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 考勤记录实体持久化接口
 * @author wxy
 * 2020-3-08
 */
@Repository
public interface IAttendanceRepository extends JpaSpecificationExecutor<Attendance>, JpaRepository<Attendance, String> {
//    /**
//     * 分页查询员工全部显示
//     * @param pageable
//     * @return
//     */
//    @Query(value = "select a.* from  auth_getaway_attendance a\n" +
//            "left join auth_getaway_employees e on a.employees_id = e.id",
//            countQuery = "select count(*) from  auth_getaway_attendance a\n" +
//                    "left join auth_getaway_attendance e on a.employees_id = e.id\n"
//                    ,nativeQuery = true)
//    Page<String> findByAll(Pageable pageable);
//
//    /**
//     * 分页查询员工全部显示
//     * @param pageable
//     * @return
//     */
//    @Query(value = "select a.* from  auth_getaway_attendance a\n" +
//            "left join auth_getaway_employees e on a.employees_id = e.id\n" +
//            "where e.id=:employeesId",
//            countQuery = "select count(*) from  auth_getaway_attendance a\n" +
//                    "left join auth_getaway_attendance e on a.employees_id = e.id\n" +
//                    "where e.id=:employeesId"
//            ,nativeQuery = true)
//    Page<String> findByEmployeesIdAll(@Param("employeesId") String employeesId, Pageable pageable);


}
