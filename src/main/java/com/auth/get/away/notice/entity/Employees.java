package com.auth.get.away.notice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * 员工实体类
 * @author wxy
 * 2020-2-19
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_employees")
public class Employees {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 员工用户名
     */
    @Column(length = 120)
    private String username;

    /**
     * 员工工号
     */
    @Column(length = 120)
    private Integer number;

    /**
     * 员工部门
     */
    @Column(length = 120)
    private String departmentName;

    /**
     * 员工姓名
     */
    @Column(length = 120)
    private String name;

    /**
     * 员工性别
     * 0:女 1:男
     */
    @Column(length = 120)
    private Integer sex;

    /**
     * 手机号
     */
    @Column(length = 120)
    private String phone;

    /**
     * 身份证号码
     */
    @Column(length = 120)
    private String idcard;



    /**
     * 电子邮箱
     */
    @Column(length = 200)
    private String email;

    /**
     * 公司名称
     */
    @Column(length = 200)
    private String company="天然科技有限责任公司";

    /**
     * 工作岗位
     */
    @Column(length = 200)
    private String job;

    /**
     * 地址住址
     */
    @Column(length = 200)
    private String address;

    /**
     * 员工生日
     */
    @Column(length = 200)
    private Date birthday;

    /**
     * 录入时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime =  new Date();





}
