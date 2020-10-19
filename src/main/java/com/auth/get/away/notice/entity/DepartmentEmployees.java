package com.auth.get.away.notice.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 部门员工实体类
 * @author wxy
 * 2020-2-19
 */
@Setter
@Getter
@Entity
@Table(name = "auth_getaway_Department_Employees")
public class DepartmentEmployees {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "char(32)")
    private String id;

    /**
     * 部门Id
     */
    @Column(columnDefinition = "char(32)")
    private String departmentId;

    /**
     * 员工Id
     */
    @Column(columnDefinition = "char(32)")
    private String employeesId;

}
