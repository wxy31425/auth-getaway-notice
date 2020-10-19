package com.auth.get.away.notice.service.dto;

import com.auth.get.away.notice.entity.Account;
import com.auth.get.away.notice.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Setter
@Getter
public class LoginDTO {
    private String id;
    private String email;
    private String phone;
    private Integer status;
    private String ip;
    private Date loginTime;
    private List<Map> roles;

    public LoginDTO(String id,String email,String phone,Integer status,
                    String ip,Date loginTime){
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.ip = ip;
        this.loginTime = loginTime;
    }
    public void addRoles(List<Map> roles) {
        this.roles = roles;
    }
}
