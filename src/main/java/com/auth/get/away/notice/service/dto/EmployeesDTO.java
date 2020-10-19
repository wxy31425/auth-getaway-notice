package com.auth.get.away.notice.service.dto;

import com.auth.get.away.notice.entity.Department;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class EmployeesDTO {
    private String id;
    private Integer number;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String idcard;
    private String username;
    private Date birthday;
    private String job;
    private String departments;

    public EmployeesDTO(String id,Integer number,String name,String idcard,
                        String email,String phone,String address,String job,
                        String username, Date birthday){
        this.id = id;
        this.number = number;
        this.name = name;
        this.idcard = idcard;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.job = job;
        this.birthday = birthday;
    }

    public void addDepartment(String departments) {
        this.departments = departments;
    }
}
