package com.auth.get.away.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 考勤记录实体类
 * @author wxy
 * 2020-03-05
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_attendance")
public class Attendance {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 员工Id
     */
    @OneToOne
    @JoinColumn(name = "employees_id",foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnoreProperties("Employees")
    @NotFound(action= NotFoundAction.IGNORE)
    private Employees employees;

    /**
     * 打卡日期
     */
    @Column(length = 100)
    private String attendDate;

    /**
     * 打卡上班时间
     */
    @Column(length = 100)
    private String attendUpTime;

    /**
     * 打卡下班时间
     */
    @Column(length = 100)
    private String attendDownTime;

    /**
     * 打卡地址
     */
    @Column(length = 100)
    private String address;

    /**
     * 打卡状态
     * 1:正常打卡 2:外出打卡
     */
    @Column
    private Integer status;

    /**
     *  打卡时间状态
     * 0:正常1:异常
     */
    @Column
    private Integer timeStatus;
    /**
     *  打卡次数
     */
    @Column
    private Integer count;

    /**
     * 申诉小时
     */
    @Column(length = 100)
    private String complaintHour;

    /**
     * 申诉理由
     */
    @Column(length = 100)
    private String complaintReason;

    /**
     * 0 :无需申诉 1:已申诉 2:待申诉
     */
    @Column
    private Integer complaintStatus;










}

