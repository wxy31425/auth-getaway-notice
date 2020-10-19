package com.auth.get.away.notice.controller.vm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DepartmentVM {
    private String id;
    private String name;
    private String description;
    private Integer seq;
    private String parentId;
    private Date createTime;
}
