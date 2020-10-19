package com.auth.get.away.notice.service;


import com.auth.get.away.notice.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * 考勤记录管理IAttendanceService
 * @author wxy
 * 2020 3-08
 */
public interface IAttendanceService {
    /**
     * 分页查询考勤记录信息
     * @param pageable
     * @return
     */
    Page<Attendance> findByPage(String employeesId, String attendDate, Integer timeStatus, Pageable pageable);


}
