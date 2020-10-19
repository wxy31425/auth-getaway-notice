package com.auth.get.away.notice.service.impl;

import com.auth.get.away.notice.entity.Attendance;
import com.auth.get.away.notice.entity.Employees;
import com.auth.get.away.notice.entity.Login;
import com.auth.get.away.notice.repository.IAttendanceRepository;
import com.auth.get.away.notice.repository.IEmployeesRepository;
import com.auth.get.away.notice.service.IAttendanceService;
import com.auth.get.away.notice.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 考勤记录管理IAttendanceService实现类
 * @author wxy
 * 2020 1-22
 */
@Service
@Slf4j
public class AttendanceServiceImpl implements IAttendanceService {
    @Autowired
    IAttendanceRepository attendanceRepository;
    @Autowired
    IEmployeesRepository employeesRepository;

    /**
     * 考勤记录查询
     *
     * @param employeesId
     * @param attendDate
     * @param pageable
     * @return
     */
    @Override
    public Page<Attendance> findByPage(String employeesId, String attendDate,Integer timeStatus, Pageable pageable) {
        Specification<Attendance> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(employeesId)) {
                predicates.add(builder.equal(root.get("employees").get("id"), employeesId));
            }
            if(!StringUtils.isEmpty(attendDate)){
                predicates.add(builder.like(root.get("attendDate"), "%" +attendDate +"%"));
            }
            if(!StringUtils.isEmpty(timeStatus)){
                predicates.add(builder.equal(root.get("timeStatus"), +timeStatus));
            }
            query.where(predicates.toArray(new Predicate[]{}));
            query.orderBy(builder.desc(root.get("attendDate")));
            return query.getRestriction();
        };
        return attendanceRepository.findAll(specification, pageable);
    }
}
