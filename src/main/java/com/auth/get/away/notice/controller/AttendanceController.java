package com.auth.get.away.notice.controller;

import com.auth.get.away.notice.controller.vm.PageVM;
import com.auth.get.away.notice.entity.Attendance;
import com.auth.get.away.notice.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考勤记录管理
 * @author wxy
 *  2020 3-08
 */
@RestController
@RequestMapping("/attendance")

public class AttendanceController {

    @Autowired
    IAttendanceService attendanceService;

    /**
     * 考勤记录信息分页查询
     * @param pageVM
     * @return
     */
    @PostMapping("queryList")
    public Page<Attendance> queryList(@RequestBody PageVM pageVM) {
        return attendanceService.findByPage
                (pageVM.getEmployeesId(),pageVM.getKeyWord(),pageVM.getStatus(), PageRequest.of(pageVM.getCurPage()-1, pageVM.getPageSize()));
    }
}
