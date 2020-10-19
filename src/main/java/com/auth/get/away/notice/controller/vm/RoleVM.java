package com.auth.get.away.notice.controller.vm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RoleVM {
    private String id;
    private String name;
    private String description;
    private Date createTime;
}
