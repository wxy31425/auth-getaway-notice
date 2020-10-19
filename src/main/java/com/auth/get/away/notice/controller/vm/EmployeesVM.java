package com.auth.get.away.notice.controller.vm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeesVM {
    private String id;
    private String name;
    private Integer number;
    private String sex;
    private String phone;
    private String email;
    private String address;
    private String idcard;
    private String job;
    private String departmentName;
    private String username;
    private Date birthday;
    private String departmentId;
    private Date createTime;
}
